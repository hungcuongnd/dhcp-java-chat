package server;

import com.google.gson.Gson;
import database.DAO.ListfriendDAO;
import database.DAO.LoginDAO;
import database.DAO.tblFriendDAO;
import database.DAO.tblUserDAO;
import database.DAO.tblUserUserDAO;
import database.Entities.Tbluser;
import database.Entities.TbluserUser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import utilities.FileConverter;
import utilities.HistoryChat;
import utilities.Request;
import utilities.RequestType;
import utilities.UserSimple;

public class ServerThread extends Thread {

    String user = null;
    String json = null;
    BufferedReader is = null;
    PrintWriter os = null;
    public String id;
    private LoginDAO loginDAO = new LoginDAO();
    private ListfriendDAO listFriendDAO = new ListfriendDAO();
    private List<Tbluser> listTblUser = null;
    private tblFriendDAO tblfriend = new tblFriendDAO();

    // thành phần chính
    Socket s = null;
    FormMainServer formMainServer = null;
    HashMap<String, ServerThread> hashMap = null;

    boolean loginStatus = false;
    boolean registerStatus = false;
    private tblUserUserDAO userUserDAO = new tblUserUserDAO();

    // thư viện hỗ trợ
    Gson gson = new Gson();

    // Khởi tạo thread, tạo socket phục vụ cho client vừa kết nối
    public ServerThread(Socket s, FormMainServer formMainServer) {
        this.s = s;
        this.formMainServer = formMainServer;
        this.hashMap = formMainServer.getHashMap();

        try {
            is = new BufferedReader(new InputStreamReader(s.getInputStream()));
            os = new PrintWriter(s.getOutputStream());

            String json = is.readLine();
            Request rq = gson.fromJson(json, Request.class);
            this.id = rq.id;

        } catch (IOException e) {
            System.out.println("IO error in server thread");
        }
    }

    public String getUser() {
        return user;
    }

    public BufferedReader getIs() {
        return is;
    }

    public PrintWriter getOs() {
        return os;
    }

    public boolean isLogin() {
        return this.loginStatus;
    }

    public boolean isRegisterSucces() {
        return this.registerStatus;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Nhận json từ client gửi
                json = is.readLine();
                Request rq = gson.fromJson(json, Request.class);

                // Nếu là kiểu đăng nhập
                // Nhận thông tin đăng nhập từ FormLogin
                if (rq.getType() == RequestType.LOGIN) {
                    this.user = rq.getFromUser();
                    String pass = rq.getPassword();

                    // Kiểm tra tài khoản trong db
                    // loginStatus mặc định là false, thông tin đúng thì đổi sang true
                    Tbluser userInfo = loginDAO.checkLogin(user, pass);

                    // Chuẩn bị request trả về client, kiểu login
                    Request rqResponse = new Request();
                    rqResponse.setType(RequestType.LOGIN);

                    if (userInfo != null) {
                        this.loginStatus = true;

                        // Thay đổi trên FormMainServer
                        // Thông báo tài khoản đăng nhập                        
                        this.formMainServer.updateTextArea(this.user + " đăng nhập");
                        hashMap.put(user, this);
                        hashMap.remove(id);

                        // tăng số connection                        
                        this.formMainServer.totalConnection++;
                        this.formMainServer.updateTotalConnection();

                        // Cài đặt thông tin user tìm thấy cho rqResponse
                        rqResponse.setFullName(userInfo.getFullName());
                        rqResponse.setSlogan(userInfo.getSlogan());
                        rqResponse.setAvatar(FileConverter.fileToString("images/" + userInfo.getAvartar()));

                        // Lấy list bạn                        
                        this.listTblUser = listFriendDAO.getListFriend(user);

                        // tbluser ko thể convert sang json, phải dùng UserSimple thay thế
                        ArrayList<UserSimple> listUserSimple = new ArrayList<>();

                        // Kiểm tra từng đứa bạn có online ko, rồi gửi cho client để vẽ list bạn
                        for (Tbluser tbluser : listTblUser) {
                            if (hashMap.get(tbluser.getUserName()) != null) {
                                // Nếu online
                                listUserSimple.add(new UserSimple(tbluser.getUserName(), tbluser.getFullName(), true, true));
                            } else {
                                // Nếu offline
                                listUserSimple.add(new UserSimple(tbluser.getUserName(), tbluser.getFullName(), false, true));
                            }
                        }
                        rqResponse.setListFriend(listUserSimple);

                        // Báo cho tất cả bạn bè biết nó đang online
                        this.alertStatusToFriends(true);
                    }

                    // Gửi kết quả đăng nhập về client
                    rqResponse.setLogin(this.loginStatus);
                    String json = gson.toJson(rqResponse);
                    this.os.println(json);
                    this.os.flush();

                    continue;
                }

                // Nếu là kiểu nhắn tin
                if (rq.getType() == RequestType.MESSAGE) {
                    String friend = rq.getToUser();

                    System.out.println("server da nhan tn");

                    if (this.hashMap.get(friend) != null) {
                        this.hashMap.get(friend).getOs().println(json);
                        this.hashMap.get(friend).getOs().flush();
                        System.out.println("server gui tn");

                        // Lưu vào db
                        userUserDAO.saveMassage1v1(rq.getFromUser(), rq.getToUser(), rq.getContent().getContent(), 0, rq.getContent().getSas().toString());
                    }
                    continue;
                }
                if (rq.getType() == RequestType.MESSAGE) {
                    String friend = rq.getToUser();

                    System.out.println("server da nhan tn");

                    if (this.hashMap.get(friend) != null) {
                        this.hashMap.get(friend).getOs().println(json);
                        this.hashMap.get(friend).getOs().flush();
                        System.out.println("server gui tn");

                        // Lưu vào db
                        userUserDAO.saveMassage1v1(rq.getFromUser(), rq.getToUser(), rq.getContent().getContent(), 0, rq.getContent().getSas().toString());
                    }
                    continue;
                }
                if (rq.getType() == RequestType.MESSAGE) {
                    String friend = rq.getToUser();

                    System.out.println("server da nhan tn");

                    if (this.hashMap.get(friend) != null) {
                        this.hashMap.get(friend).getOs().println(json);
                        this.hashMap.get(friend).getOs().flush();
                        System.out.println("server gui tn");

                        // Lưu vào db
                        userUserDAO.saveMassage1v1(rq.getFromUser(), rq.getToUser(), rq.getContent().getContent(), 0, rq.getContent().getSas().toString());
                    }
                    continue;
                }
                if (rq.getType() == RequestType.REGISTER) {
                    tblUserDAO userDAO = new tblUserDAO();                    
                    Tbluser userGet = new Tbluser();
                    userGet.setUserName(rq.getFromUser());
                    userGet.setFullName(rq.getFullName());
                    userGet.setPassWord(rq.getPassword());
                    userGet.setAvartar("");
                    userGet.setSlogan("");
                    this.registerStatus = userDAO.createUser(userGet);   
                    Request rqResponse = new Request();
                    rqResponse.setType(RequestType.REGISTER);
                    rqResponse.setIsRegisterSuccess(this.registerStatus);
                    String json = gson.toJson(rqResponse);
                    this.os.println(json);
                    this.os.flush();
                    // cần một thông báo ở đây để trả lại client biết đăng kí ok hay không
                    continue;
                }
                // Gửi file
                if (rq.getType() == RequestType.SEND_FILE) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String friend = rq.getToUser();
                    // 1. Lưu ảnh và folde images
                    // Lấy file ảnh
                    byte[] decode = Base64.getDecoder().decode(rq.getStringOfFile());
                    // Tạo tên file ảnh: user + extension
                    String fileName = rq.getFromUser() +"-"+ rq.getToUser()+ "-" + timestamp.getTime() + "." + rq.getExtension()+"";
                    Path path = Paths.get("images/" + fileName);
                    Files.write(path, decode);
                    System.err.println("Đã nhận file từ "+rq.getFromUser()+ " gửi tới "+rq.getToUser());
                    // 3. Truyền lại avatar mới
                    // Xài lại cái request vừa nhận   
                    rq.setStringOfFile(FileConverter.fileToString("images/" + fileName));
                    if (this.hashMap.get(friend) != null) {
                        String jsonResponse = gson.toJson(rq);
                        this.hashMap.get(friend).getOs().println(jsonResponse);
                        this.hashMap.get(friend).getOs().flush();
                        System.err.println("Đang gửi file từ "+rq.getFromUser()+ " gửi tới "+rq.getToUser());
                    }
                    continue;
                }
                // Nếu là kiểu lấy thông tin bạn
                if (rq.getType() == RequestType.GET_FRIEND_INFO) {
                    tblUserDAO userDAO = new tblUserDAO();
                    // Lấy ảnh bạn
                    Tbluser userFriend = userDAO.findByName(rq.getToUser());
                    rq.setAvatar(FileConverter.fileToString("images/" + userFriend.getAvartar()));
                    String jsonResponse = gson.toJson(rq);

                    // Gửi lại cho chính client gửi request thông tin của bạn hắn
                    if (this.hashMap.get(rq.getFromUser()) != null) {
                        this.hashMap.get(rq.getFromUser()).getOs().println(jsonResponse);
                        this.hashMap.get(rq.getFromUser()).getOs().flush();
                    }
                    continue;
                }

                // Nếu là kiểu đổi avatar
                if (rq.getType() == RequestType.CHANGE_AVATAR) {
                    // 1. Lưu ảnh và folde images
                    // Lấy file ảnh
                    byte[] decode = Base64.getDecoder().decode(rq.getAvatar());
                    // Tạo tên file ảnh: user + extension
                    String fileName = rq.getFromUser() + "." + rq.getExtension();
                    Path path = Paths.get("images/" + fileName);
                    Files.write(path, decode);

                    // 2. Update db
                    tblUserDAO userDAO = new tblUserDAO();
                    Tbluser tblUser = userDAO.findByName(rq.getFromUser());
                    tblUser.setAvartar(fileName);
                    userDAO.updateUser(tblUser);

                    // 3. Truyền lại avatar mới
                    // Xài lại cái request vừa nhận
                    rq.setAvatar(FileConverter.fileToString("images/" + fileName));
                    String jsonResponse = gson.toJson(rq);
                    this.os.println(jsonResponse);
                    this.os.flush();
                    continue;
                }

                //Nếu là xóa friend thì xóa.
                if (rq.getType() == RequestType.DELETE_FRIEND) {
                    if (tblfriend.deleteFriend(rq.getFromUser(), rq.getToUser())) {
                        rq.setIsDeletedFriend(true);
                        String jsonResponse = gson.toJson(rq);
                        this.os.println(jsonResponse);
                        this.os.flush();
                        continue;
                    }
                }

                //Nếu là đổi Fullname thì.
                if(rq.getType() == RequestType.CHANGE_FULLNAME){
                    tblUserDAO DAOuser = new tblUserDAO();
                    Tbluser newuser = DAOuser.findByName(rq.getFromUser());
                    newuser.setFullName(rq.getFullName());
                    DAOuser.updateUser(newuser);
                    newuser = DAOuser.findByName(rq.getFromUser());

                    rq.setFullName(newuser.getFullName());
                    String json = gson.toJson(rq);
                    this.os.println(json);
                    this.os.flush();
                }

                if (rq.getType() == RequestType.CHANGE_SLOGAN) {
                    tblUserDAO DAOuser = new tblUserDAO();
                    Tbluser newuser = DAOuser.findByName(rq.getFromUser());
                    newuser.setSlogan(rq.getSlogan());
                    DAOuser.updateUser(newuser);
                    newuser = DAOuser.findByName(rq.getFromUser());

                    rq.setSlogan(newuser.getSlogan());
                    System.out.println(newuser.getSlogan());
                    String json = gson.toJson(rq);
                    this.os.println(json);
                    this.os.flush();
                }

                // Nếu là kiểu lấy lịch sử chat
                if (rq.getType() == RequestType.HISTORY) {

                    tblUserUserDAO userUserDAO = new tblUserUserDAO();
                    List<TbluserUser> listChatHistory = userUserDAO.getAllMessage1v1(rq.getFromUser(), rq.getToUser(), rq.getLoadMessageNum());
                    //loadNumber += 10;
                    if (listChatHistory != null) {
                        List<HistoryChat> historyChatList = new ArrayList<>();
                        for (TbluserUser tbluserUser : listChatHistory) {
                            historyChatList.add(new HistoryChat(tbluserUser.getTbluser().getUserName() //, tbluserUser.getTbluser1().getUserName()
                                //, tbluserUser.getTbluser1().getUserName()
                                , tbluserUser.getContent()
                                , tbluserUser.getStatus()
                                , tbluserUser.getSas()));
                        }
                        rq.setChatHistory(historyChatList);
                    }
                }
                // Nếu là kiểu lấy lịch sử chat chua doc
                if (rq.getType() == RequestType.UNREADMSG) {

                    tblUserUserDAO userUserDAO1 = new tblUserUserDAO();
                    List<TbluserUser> listChatHistoryUnRead = userUserDAO.checkUnreadMessage1v1(rq.getFromUser());
                    //loadNumber += 10;
                    if (listChatHistoryUnRead != null) {
                        List<HistoryChat> historyChatList = new ArrayList<>();
                        for (TbluserUser tbluserUser : listChatHistoryUnRead) {
                            historyChatList.add(new HistoryChat(tbluserUser.getTbluser().getUserName()
                                //, tbluserUser.getTbluser1().getUserName()
                                , tbluserUser.getContent()
                                , tbluserUser.getStatus()
                                , tbluserUser.getSas()));
                        }
                        rq.setChatHistory(historyChatList);
                    }

                    // 2. Update db
                    // 3. Truyền lại lich su chat
                    // Xài lại cái request vừa nhận
                    String jsonResponse = gson.toJson(rq);
                    this.os.println(jsonResponse);
                    this.os.flush();
                    continue;
                }

                // Nếu là kiểu search friend
                if (rq.getType() == RequestType.GET_SEARCH_LIST) {
                    String keyword = rq.getKeyword();
                    // vào db lấy ra list user thỏa mãn từ khóa
                    tblUserDAO userDAO = new tblUserDAO();
                    List<Tbluser> tbluserList = userDAO.findByFullName(keyword,rq.getFromUser());

                    // trả lại kết quả cho client dạng List<UserSimple>
                    ArrayList<UserSimple> userSimpleList = new ArrayList<>();
                    if (tbluserList != null) {
                        for (Tbluser tbluser : tbluserList) {
                            userSimpleList.add(new UserSimple(tbluser.getUserName(), tbluser.getFullName(), true, true));
                        }
                    }

                    rq.setListFriend(userSimpleList);

                    String json = gson.toJson(rq);
                    this.os.println(json);
                    this.os.flush();
                    continue;
                }

                // Nếu là kiểu ask friend
                if (rq.getType() == RequestType.ASK_FRIEND) {
                    String friendUser = rq.getToUser();
                    // check xem có user đó trong db ko

                    // Nếu ko có, báo về client
                    if (!friendUser.equals("ly")) {
                        rq.setUserExist(false);
                    } else {
                        rq.setUserExist(true);
                        // Nếu lưu thành công
                        rq.setAskFriend(true);
                    }

                    String json = gson.toJson(rq);
                    this.os.println(json);
                    this.os.flush();
                    continue;
                }
            }

        } catch (IOException e) {
            System.out.println("IO Error/ Client " + user + " terminated abruptly");

        } catch (NullPointerException e) {
            System.out.println("Client " + user + " Closed");

        } finally {
            try {
                if (is != null) {
                    is.close();
                }

                if (os != null) {
                    os.close();
                }

                if (s != null) {
                    s.close();
                }

                // Nếu trước đó đã đăng nhập, thì ghi log đăng xuất
                if (isLogin()) {
                    // xóa khỏi mảng hashMap
                    this.hashMap.remove(this.user);

                    this.formMainServer.updateTextArea(this.user + " đăng xuất");

                    // update lại total connection (giảm đi 1)
                    this.formMainServer.totalConnection--;
                    this.formMainServer.updateTotalConnection();

                    // Thông báo cho bạn bè thằng client này biết nó đã đăng xuất
                    this.alertStatusToFriends(false);

                } else {
                    // xóa khỏi mảng hashMap
                    this.hashMap.remove(this.id);
                }

            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }//end finally
    }// end void run

    public void alertStatusToFriends(boolean status) {
        // Lấy lại list bạn (ko tận dụng lại được listTblUser trước đó vì có thể đã thay đổi)
        this.listTblUser = this.listFriendDAO.getListFriend(this.user);

        // Tạo request, user nhận để trống, tí điền
        Request rq = new Request(RequestType.STATUS, this.user, null);
        rq.setLogin(status);

        // Gửi cho từng đứa
        for (Tbluser tbluser : this.listTblUser) {
            String userFriend = tbluser.getUserName();
            if (this.hashMap.get(userFriend) != null) {
                rq.setToUser(userFriend);
                String json = gson.toJson(rq);
                this.hashMap.get(userFriend).getOs().println(json);
                this.hashMap.get(userFriend).getOs().flush();
            }
        }
    }

}
