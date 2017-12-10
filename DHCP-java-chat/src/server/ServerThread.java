package server;

import com.google.gson.Gson;
import database.DAO.ListfriendDAO;
import database.DAO.LoginDAO;
import database.DAO.tblUserDAO;
import database.DAO.tblUserUserDAO;
import database.Entities.Tbluser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import utilities.FileConverter;
import utilities.Request;
import utilities.RequestType;
import utilities.UserSimple;

public class ServerThread extends Thread {

    String user = null;
    String json = null;
    BufferedReader is = null;
    PrintWriter os = null;
    public String id;
    public LoginDAO loginDAO = new LoginDAO();

    // thành phần chính
    Socket s = null;
    FormMainServer formMainServer = null;
    HashMap<String, ServerThread> hashMap = null;

    boolean loginStatus = false;

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
                        rqResponse.setAvatar(FileConverter.fileToString("images/" + userInfo.getAvartar()));

                        // Lấy list bạn
                        ListfriendDAO listFriendDAO = new ListfriendDAO();
                        List<Tbluser> listTblUser = listFriendDAO.getListFriend(user);
                        ArrayList<UserSimple> listUserSimple = new ArrayList<>();
                        // tbluser ko thể convert sang json, phải dùng UserSimple thay thế
                        for (Tbluser tbluser : listTblUser) {
                            if (hashMap.get(tbluser.getUserName()) != null) {
                                listUserSimple.add(new UserSimple(tbluser.getUserName(), tbluser.getFullName(), true));
                            } else {
                                listUserSimple.add(new UserSimple(tbluser.getUserName(), tbluser.getFullName(), false));
                            }
                        }
                        rqResponse.setListFriend(listUserSimple);
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

                // Client thoát, xóa khỏi mảng hashMap
                this.hashMap.remove(this.user);

                // Nếu trước đó đã đăng nhập, thì ghi log đăng xuất
                if (isLogin()) {
                    this.formMainServer.updateTextArea(this.user + " đăng xuất");

                    // Khi client thoát, update lại total connection (giảm đi 1)
                    this.formMainServer.totalConnection--;
                    this.formMainServer.updateTotalConnection();
                }

            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }//end finally
    }// end void run

}
