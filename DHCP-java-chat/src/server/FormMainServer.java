package server;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Tran Cuong
 */
public class FormMainServer extends javax.swing.JFrame {

    /**
     * Creates new form FormMainServer
     */
    public int totalConnection = 0;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    
    // Mảng hashMap quản lý các ServerThread, mỗi ServerThread có 1 socket phục vụ 1 client
    private HashMap<String, ServerThread> hashMap = new HashMap<>();

    public FormMainServer() {
        initComponents();
        this.txtCount.setOpaque(false);

        // Tiến trình song song (làm thế này mới chạy được form)
        backgroundThread(this);
    }

    public HashMap<String, ServerThread> getHashMap() {
        return hashMap;
    }
    
    public JTextField getTxtCount() {
        return txtCount;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
    
    public void backgroundThread(FormMainServer formMainServer) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                textArea.setText("");

                Socket sk = null;
                ServerSocket serverSk = null;

                System.out.println("Server Listening......");
                updateTextArea("Server started !");

                try {
                    serverSk = new ServerSocket(6969);

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Server error");

                }

                // Tiến trình chạy song song, cứ có lient đòi kết nối là tạo ra ServerThread để phục vụ
                while (true) {
                    try {
                        sk = serverSk.accept();
                        ServerThread st = new ServerThread(sk, formMainServer);

                        // Luôn thêm thread vào mảng hashMap bất kể trạng thái đăng nhập
                        hashMap.put(st.id, st);
                        hashMap.get(st.id).start();

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Connection Error");

                    }
                }
            }
        }).start();
    }

    public void updateTextArea(String newText) {
        Date now = new Date();
        textArea.setText(textArea.getText() + this.dateFormat.format(now) + ": " + newText + "\n");
    }

    public void updateTotalConnection() {
        this.txtCount.setText(this.totalConnection + "");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        txtCount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");

        scrollPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        textArea.setEditable(false);
        textArea.setBackground(new java.awt.Color(51, 51, 51));
        textArea.setColumns(20);
        textArea.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        textArea.setForeground(new java.awt.Color(204, 204, 204));
        textArea.setRows(5);
        scrollPane.setViewportView(textArea);

        label.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        label.setText("Tổng số kết nối:");

        txtCount.setEditable(false);
        txtCount.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        txtCount.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCount.setText("0");
        txtCount.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addComponent(txtCount))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
//                System.out.println(info.getClassName());

//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    System.out.println(info.getClassName());
//                    break;
//                }
            }

//            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//            javax.swing.UIManager.setLookAndFeel(new WebLookAndFeel());
//            javax.swing.UIManager.setLookAndFeel(new NapkinLookAndFeel());
//            javax.swing.UIManager.setLookAndFeel(new InfoNodeLookAndFeel());
//            javax.swing.UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
//            javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
//            javax.swing.UIManager.setLookAndFeel(new AeroLookAndFeel());
            javax.swing.UIManager.setLookAndFeel(new AluminiumLookAndFeel());

//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FormMainServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FormMainServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FormMainServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMainServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMainServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextField txtCount;
    // End of variables declaration//GEN-END:variables
}
