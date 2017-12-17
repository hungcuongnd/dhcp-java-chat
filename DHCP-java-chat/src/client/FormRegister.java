/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import database.DAO.tblUserDAO;
import database.Entities.Tbluser;
import utilities.Request;
import utilities.RequestType;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyễn Trung Hiếu
 */
public class FormRegister extends javax.swing.JFrame {

    /**
     * Creates new form FormRegister
     */
    FormMainClient formMainClient;
    FormLogin formLogin;
    tblUserDAO daoUser = new tblUserDAO();
    private Gson gson = new Gson();
    private PrintWriter os = null;
    InetAddress address = null;
    Socket sk = null;
    BufferedReader is = null;

    public FormRegister(FormLogin formLogin) {
        initComponents();
        this.formLogin = formLogin;
        this.formLogin.setVisible(false);
        this.setResizable(false);
    }

    private FormRegister() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtFullName = new javax.swing.JTextField();
        txtRePassword = new javax.swing.JPasswordField();
        txtPassword = new javax.swing.JPasswordField();
        txtLoginName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbAgree = new javax.swing.JCheckBox();
        btnRegisterSend = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnBackToLogin = new javax.swing.JButton();
        msgFullName = new javax.swing.JLabel();
        msgRePassword = new javax.swing.JLabel();
        msgPassword = new javax.swing.JLabel();
        msgLoginName = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);
        jPanel1.add(txtFullName);
        txtFullName.setBounds(80, 40, 390, 30);

        txtRePassword.setPreferredSize(new java.awt.Dimension(6, 20));
        jPanel1.add(txtRePassword);
        txtRePassword.setBounds(80, 270, 390, 30);

        txtPassword.setPreferredSize(new java.awt.Dimension(6, 20));
        jPanel1.add(txtPassword);
        txtPassword.setBounds(80, 190, 390, 30);
        jPanel1.add(txtLoginName);
        txtLoginName.setBounds(80, 110, 390, 30);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NHẬP LẠI MẬT KHẨU");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(80, 240, 150, 30);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TÊN HIỂN THỊ");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(80, 14, 150, 30);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TÊN ĐĂNG NHẬP");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(80, 84, 150, 30);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("MẬT KHẨU");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(80, 164, 150, 20);

        cbAgree.setBackground(new java.awt.Color(102, 102, 102));
        cbAgree.setForeground(new java.awt.Color(255, 255, 255));
        cbAgree.setText("Đồng ý với mọi điều khoản của chúng tôi (nếu có)");
        jPanel1.add(cbAgree);
        cbAgree.setBounds(80, 330, 390, 30);

        btnRegisterSend.setText("ĐĂNG KÍ");
        btnRegisterSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterSendActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegisterSend);
        btnRegisterSend.setBounds(80, 390, 110, 30);

        btnReset.setText("HỦY BỎ TẤT CẢ");
        jPanel1.add(btnReset);
        btnReset.setBounds(200, 390, 120, 30);

        btnBackToLogin.setText("ĐĂNG NHẬP");
        jPanel1.add(btnBackToLogin);
        btnBackToLogin.setBounds(330, 390, 140, 30);

        msgFullName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        msgFullName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(msgFullName);
        msgFullName.setBounds(480, 40, 390, 30);

        msgRePassword.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        msgRePassword.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(msgRePassword);
        msgRePassword.setBounds(480, 270, 400, 30);

        msgPassword.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        msgPassword.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(msgPassword);
        msgPassword.setBounds(480, 190, 400, 30);

        msgLoginName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        msgLoginName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(msgLoginName);
        msgLoginName.setBounds(480, 110, 400, 30);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/register-background.jpg"))); // NOI18N
        jLabel5.setText("jLabel5");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(0, 0, 950, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterSendActionPerformed
        msgFullName.setText("");
        msgLoginName.setText("");
        msgPassword.setText("");
        msgRePassword.setText("");
        boolean canRegister = true;
        if (txtFullName.getText().equalsIgnoreCase("")) {
            msgFullName.setText("Không được bỏ trống");
            canRegister = false;
        }
        if (txtLoginName.getText().equalsIgnoreCase("")) {
            msgLoginName.setText("Không được bỏ trống");
            canRegister = false;
        } else {
            if (checkUsername()) { // ton tai thi tra ve true
                msgLoginName.setText("Tên đăng nhập đã tồn tại");
                canRegister = false;
            }
        }
        if (txtPassword.getPassword().length == 0) {
            msgPassword.setText("Không được bỏ trống");
            canRegister = false;
        }
        if (txtRePassword.getPassword().length == 0) {
            msgRePassword.setText("Không được bỏ trống");
            canRegister = false;
        } else {
            if (!String.valueOf(txtPassword.getPassword()).equals(String.valueOf(txtRePassword.getPassword()))) {
                msgRePassword.setText("Mật khẩu nhập lại không khớp");
                canRegister = false;
            }
        }
        if (canRegister) {
            try {
                registerUser(txtLoginName.getText(), txtFullName.getText(), String.valueOf(txtPassword.getPassword()));
            } catch (IOException ex) {
                Logger.getLogger(FormRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnRegisterSendActionPerformed
    private boolean checkUsername() {

        return false;
    }

    public PrintWriter getOS() {
        return this.os;
    }

    private void registerUser(String username, String fullname, String password) throws IOException {
        try {
            address = InetAddress.getLocalHost();
            sk = new Socket(address, 6969);
            is = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            os = new PrintWriter(sk.getOutputStream());
            Tbluser user = new Tbluser();
            user.setFullName(fullname);
            user.setPassWord(password);
            user.setUserName(username);
            user.setAvartar("");
            Request rq = new Request(RequestType.REGISTER, null, null);
            rq.setUser(user);
            String json = this.gson.toJson(rq);
            this.os.println(json);
            this.os.flush();
        } catch (UnknownHostException ex) {
            Logger.getLogger(FormRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormRegister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackToLogin;
    private javax.swing.JButton btnRegisterSend;
    private javax.swing.JButton btnReset;
    private javax.swing.JCheckBox cbAgree;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel msgFullName;
    private javax.swing.JLabel msgLoginName;
    private javax.swing.JLabel msgPassword;
    private javax.swing.JLabel msgRePassword;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtLoginName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtRePassword;
    // End of variables declaration//GEN-END:variables
}
