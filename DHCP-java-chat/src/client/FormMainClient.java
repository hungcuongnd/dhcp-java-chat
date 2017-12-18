package client;

import com.google.gson.Gson;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import utilities.FileConverter;
import utilities.FormConfirm;
import utilities.Request;
import utilities.RequestType;
import utilities.UserSimple;

public class FormMainClient extends javax.swing.JFrame {

    /**
     * Creates new form Form_Main_Client
     */
    private String id;
    public String user = null;
    private HashMap<String, FormChatPrivacy> friendHashMap = new HashMap<>();
    private PrintWriter os = null;
    private Gson gson = new Gson();
    private JList listFriend;
    DefaultListModel<String> listModel = new DefaultListModel<>();

    FormLogin formlogin;

    String friendToDel = null;

    // var for list friend
    HashMap<String, PanelEntity> panelFriendMap = new HashMap<>();
    HashMap<Integer, PanelEntity> panelGroupMap = new HashMap<>();

    FormRegister formRegister;
    public FormMainClient() {
        initComponents();
        // create login form
        this.formlogin = new FormLogin(this);
        this.formlogin.setLocationRelativeTo(null);
        this.formlogin.setVisible(true);
        txtFullname.setBorder(null);
        // end create login form
        backgroundThread();
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        panelWrapper = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblAvatar = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblUser1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        txtFullname = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtAddFriend = new javax.swing.JTextField();
        btnAddFriend = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("DHCP-chat");
        setMinimumSize(new java.awt.Dimension(300, 450));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane3.setBorder(null);

        panelWrapper.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelWrapper.setLayout(new javax.swing.BoxLayout(panelWrapper, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane3.setViewportView(panelWrapper);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        lblAvatar.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblAvatar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 2, true));
        lblAvatar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAvatar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAvatarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblAvatarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblAvatarMouseExited(evt);
            }
        });

        lblUser.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblUser.setText("user");

        lblUser1.setFont(new java.awt.Font("Dialog", 2, 13)); // NOI18N
        lblUser1.setText("Hôm nay bạn thế nào ?");

        jTextField1.setText("Trâtss");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        txtFullname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtFullname.setOpaque(false);
        txtFullname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFullnameActionPerformed(evt);
            }
        });
        txtFullname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFullnameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFullnameKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(lblUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtFullname)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblUser1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));

        txtAddFriend.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txtAddFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddFriendActionPerformed(evt);
            }
        });

        btnAddFriend.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAddFriend.setText("Add");
        btnAddFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFriendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtAddFriend)
                .addGap(18, 18, 18)
                .addComponent(btnAddFriend)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAddFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddFriend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public PrintWriter getOS() {
        return this.os;
    }

// begin thread in background
    public void backgroundThread() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                InetAddress address = null;
                Socket sk = null;
                BufferedReader is = null;

                try {
                    address = InetAddress.getLocalHost();
                    sk = new Socket(address, 6969);
                    is = new BufferedReader(new InputStreamReader(sk.getInputStream()));
                    os = new PrintWriter(sk.getOutputStream());

                    UUID randomUUID = UUID.randomUUID();
                    id = randomUUID.toString();

                    // Ngay khi kết nối đến server, gửi luôn id
                    Request rqLogin = new Request();
                    rqLogin.id = id;
                    String jsonLogin = gson.toJson(rqLogin);

                    os.println(jsonLogin);
                    os.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Client Address : " + address);

                try {
                    // Gửi tin nhắn: gửi tại form chat                    

                    // Nhận dữ liệu từ server dạng json                
                    while (true) {
                        String json = is.readLine();
                        Request rq = gson.fromJson(json, Request.class);

                        if(rq.getType() == RequestType.REGISTER){
                            if(rq.getIsIsRegisterSuccess()){
                                formRegister.throwMessage(true);
                            }else{
                                formRegister.throwMessage(false);
                            }
                        }
                        // Nếu là kiểu đăng nhập
                        if (rq.getType() == RequestType.LOGIN) {

                            // server báo login thất bại
                            if (!rq.isLogin()) {
                                formlogin.throwMessage();
                                System.out.println("login that bai");
                            } else {

                                // server báo login thành công
                                System.out.println("login thanh cong");

                                // Đặt fullname & username
                                if (user != null) {
                                    lblUser.setText(user);
                                }

                                // Lấy thông tin user
                                String fullname = rq.getFullName();
                                if (fullname != null && !fullname.isEmpty()) {
                                    txtFullname.setText(fullname);
                                }

                                // Đặt avatar có ảnh thì lấy ko thì lấy ảnh mặc định
                                if (rq.getAvatar() != null && !rq.getAvatar().isEmpty()) {
                                    ImageIcon avatar = FileConverter.stringToImage(rq.getAvatar());
                                    avatar = scaleImage(avatar);
                                    lblAvatar.setIcon(avatar);
                                } else {
                                    lblAvatar.setIcon(new ImageIcon("images/avatar-100.jpg"));
                                }

                                // Vẽ list bạn
                                for (UserSimple user : rq.getListFriend()) {
                                    // Khởi tạo panelEntity (tự động add vào panelWrapper)
                                    new PanelEntity(getParentForm(), PanelType.PANEL_FRIEND, user.getFullName(), user.getUser(), user.isOnline(), 0);
                                }

                                // Hiện FormMainClient, ẩn FormLogin
                                showForm();
                                formlogin.setVisible(false);
                            }
                            continue;
                        }

                        // Nếu là kiểu massage
                        if (rq.getType() == RequestType.MESSAGE) {
                            String userSend = rq.getFromUser();

                            // Nếu FormChat chưa từng được bật thì bật lên
                            if (friendHashMap.get(userSend) == null) {
                                String friendName = panelFriendMap.get(userSend).getName();
                                showFormChat(userSend, friendName);
                            }

                            // update lịch sử tin nhắn
                            friendHashMap.get(userSend).updateTxtContentReceive(rq);
                            continue;
                        }
                        // Nếu là kiểu yêu cầu kết bạn
                        if (rq.getType() == RequestType.ASK_FRIEND) {
                            String newFriend = rq.getFromUser();
                            listModel.addElement(newFriend);
                            continue;
                        }

                        // Nếu là kiểu lấy thông tin bạn (user + name đã có, cần có avatar)
                        if (rq.getType() == RequestType.GET_FRIEND_INFO) {
                            ImageIcon avatar = FileConverter.stringToImage(rq.getAvatar());
                            if (friendHashMap.get(rq.getToUser()) != null) {
                                if (avatar != null) {
                                    avatar = scaleImage(avatar);
                                    friendHashMap.get(rq.getToUser()).setAvatar(avatar);
                                } else {
                                    // Nhét ảnh mặc định vào FormChat
                                    friendHashMap.get(rq.getToUser()).setAvatar(new ImageIcon("images/avatar-100.jpg"));
                                }
                            }
                            continue;
                        }

                        // Nếu là kiểu thông báo trạng thái online
                        if (rq.getType() == RequestType.STATUS) {
                            String friendUser = rq.getFromUser();
                            boolean stt = rq.isLogin();
                            panelFriendMap.get(friendUser).updateLblIcon(stt);
                            panelWrapper.revalidate();
                            panelWrapper.repaint();
                            continue;
                        }

                        // Nếu là kiểu server trả kết quả đổi avatar
                        if (rq.getType() == RequestType.CHANGE_AVATAR) {
                            ImageIcon avatar = FileConverter.stringToImage(rq.getAvatar());
                            if (avatar != null) {
                                avatar = scaleImage(avatar);
                                lblAvatar.setIcon(avatar);
                            }
                        }

                        //Nếu trả về fullname
                        if (rq.getType() == RequestType.CHANGE_FULLNAME) {
                            txtFullname.setText(rq.getFullName());
                            continue;
                        }
                        // Nếu là kiểu lay lich su chat
                        if (rq.getType() == RequestType.HISTORY) {

                            //showFormChat(rq.getFromUser(), rq.getToUser());
                            friendHashMap.get(rq.getToUser()).setVisible(true);
                            friendHashMap.get(rq.getToUser()).checkScrollBarReachTop(rq);

                            // update lịch sử tin nhắn
                            continue;
                        }

                        // Nếu là kiểu lay lich su chat
                        if (rq.getType() == RequestType.UNREADMSG) {

                            //showFormChat(rq.getFromUser(), rq.getToUser());
                            if (rq.getChatHistory() != null) {
                                friendHashMap.get(rq.getToUser()).setVisible(true);
                            }
                            friendHashMap.get(rq.getToUser()).checkScrollBarReachTop(rq);

                            // update lịch sử tin nhắn
                            continue;
                        }

                    } // end while(true)

                } catch (IOException e) {
                    e.printStackTrace();

                } finally {
                    try {
                        is.close();
                        os.close();
                        sk.close();

                    } catch (IOException ex) {
                        Logger.getLogger(FormMainClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Connection Closed");
                }
            }
        }).start();
    }

    // end thread in background
//    private void askFriend() {
//        String newFriend = this.txtAddFriend.getText();
//        Request rqAskFriend = new Request(this.user, newFriend, null, RequestType.ASKFRIEND, null);
//        String jsonAskFriend = gson.toJson(rqAskFriend);
//        os.println(jsonAskFriend);
//        os.flush();
//        this.listModel.addElement(newFriend);
//    }    

    private void txtAddFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddFriendActionPerformed
//        this.askFriend();
    }//GEN-LAST:event_txtAddFriendActionPerformed

    private void btnAddFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFriendActionPerformed

    }//GEN-LAST:event_btnAddFriendActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
//        int confirmed = JOptionPane.showConfirmDialog(null,
//                "Bạn muốn thoát ?", "Exit",
//                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
//
//        if (confirmed == JOptionPane.YES_OPTION) {
//            System.exit(0);
//        }

//        Tạo nội dung câu hỏi ArrayList
        ArrayList<String> listText = new ArrayList<>();
        listText.add("Bạn có thực sự muốn thoát ?");
        listText.add("Bạn sẽ không thể gửi & nhận tin nhắn");

        new FormConfirm(this, listText, new Callable() {
            @Override
            public Object call() throws Exception {
                System.exit(0);
                return null;
            }
        });
    }//GEN-LAST:event_formWindowClosing

    // Click đổi avatar
    private void lblAvatarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAvatarMouseClicked
        if (evt.getClickCount() == 2) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
            fileChooser.setFileFilter(extensionFilter);

            int result = fileChooser.showOpenDialog(new JFrame());
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String absolutePath = selectedFile.getAbsolutePath();
                String extension = absolutePath.substring(absolutePath.lastIndexOf("."));
                extension = extension.replace(".", "");

                // Kiểm tra đúng file ảnh hay chưa
                if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif")) {
                    // Gửi request đòi thay avatar
                    Request rq = new Request(RequestType.CHANGE_AVATAR, this.user, null);
                    String imgString = FileConverter.fileToString(absolutePath);
                    rq.setAvatar(imgString);
                    rq.setExtension(extension);

                    String json = gson.toJson(rq);
                    this.os.println(json);
                    this.os.flush();
                }
            }
        }
    }//GEN-LAST:event_lblAvatarMouseClicked

    private void lblAvatarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAvatarMouseEntered
        lblAvatar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 0), 2, true));
    }//GEN-LAST:event_lblAvatarMouseEntered

    private void lblAvatarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAvatarMouseExited
        lblAvatar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 2, true));
    }//GEN-LAST:event_lblAvatarMouseExited

    private void txtFullnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFullnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFullnameActionPerformed

    private void txtFullnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFullnameKeyReleased

    }//GEN-LAST:event_txtFullnameKeyReleased

    private void txtFullnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFullnameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !evt.isControlDown()) {
            changeFullname(txtFullname.getText());

        }
    }//GEN-LAST:event_txtFullnameKeyPressed

    public void exit() {
        System.exit(0);
    }

    public FormMainClient getParentForm() {
        return this;
    }

    public void showForm() {
        setVisible(true);

        // Đặt vị trí FormMainClient sang bên phải màn hình
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int formWidth = this.getWidth();
        int formHeight = this.getHeight();

        setLocation(screenWidth - formWidth - 50, (screenHeight - formHeight) / 3);
    }

    public void changeFullname(String fullname) {
        Request rq = new Request(11, this.user, null);
        rq.setFullName(fullname);
        String json = gson.toJson(rq);
        //System.out.println(rq);
        this.os.println(json);
        this.os.flush();
    }

    // Bật FormChat với user đã chọn
    public void showFormChat(String friendUser, String friendName) {
        if (this.friendHashMap.get(friendUser) == null) {
            // Nếu trước đó chưa click
            // Tạo mới FormChat với thằng bạn
            this.friendHashMap.put(friendUser, new FormChatPrivacy(this.user, friendUser, friendName, this.os));
            this.friendHashMap.get(friendUser).setVisible(true);
            this.friendHashMap.get(friendUser).setLocationRelativeTo(null);

            // Gửi request lấy thông tin (ảnh) thằng bạn
            Request rq = new Request(RequestType.GET_FRIEND_INFO, this.user, friendUser);
            String json = gson.toJson(rq);
            this.os.println(json);
            this.os.flush();
        } else {
            this.friendHashMap.get(friendUser).setVisible(true);
        }
    }

    // Hàm resize lại ảnh
    public ImageIcon scaleImage(ImageIcon imgOld) {
        if (imgOld == null) {
            return null;
        }

        int w = imgOld.getIconWidth();
        int h = imgOld.getIconHeight();
        int side = this.lblAvatar.getWidth();

        if (w <= h) {
            h = side * h / w;
            w = side;
        } else {
            w = side * w / h;
            h = side;
        }

        Image image = imgOld.getImage();
        Image imgNew = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(imgNew);
    }

    public void delFriend(String friendUser, String friendName) {
        ArrayList<String> listText = new ArrayList<>();
        listText.add("Bạn có thực sự muốn xóa " + friendName + " khỏi danh sách bạn?");

        new FormConfirm(this, listText, new Callable() {
            @Override
            public Object call() throws Exception {
                Request rqDelfriend = new Request(7, user, friendUser);
                String jsonDelFriend = gson.toJson(rqDelfriend);
                os.println(jsonDelFriend);
                os.flush();
                return null;
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set look and feel */
        try {
            javax.swing.UIManager.setLookAndFeel(new AluminiumLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormMainClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMainClient();

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFriend;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblUser1;
    private javax.swing.JPanel panelWrapper;
    private javax.swing.JTextField txtAddFriend;
    private javax.swing.JTextField txtFullname;
    // End of variables declaration//GEN-END:variables

    public JPanel getPanelWrapper() {
        return panelWrapper;
    }

    public HashMap<String, PanelEntity> getPanelFriendMap() {
        return panelFriendMap;
    }

    public HashMap<Integer, PanelEntity> getPanelGroupMap() {
        return panelGroupMap;
    }

}

class PopUpDemo extends JPopupMenu {

    JMenuItem anItem;

    public PopUpDemo() {
        anItem = new JMenuItem("Xóa bạn");
        add(anItem);
    }

}

class PanelType {

    public static final int PANEL_FRIEND = 18;
    public static final int PANEL_GROUP = 12;
}
