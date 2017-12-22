/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Administrator
 */
public class PanelEntity extends JPanel {

    private int panelType;
    private String txtTop;
    private String txtBottom;
    private boolean isOnline;
    private int groupId;
    private boolean isFriend;
    private boolean isSendRequest;

    private JLabel lblIcon = new JLabel();
    private JLabel lblTop = new JLabel();
    private JLabel lblBottom = new JLabel();

    private final ImageIcon onlineIcon = new ImageIcon("src/image/online-icon.png");
    private final ImageIcon offlineIcon = new ImageIcon("src/image/offline-icon.png");
    private final ImageIcon groupIcon = new ImageIcon("src/image/group-icon.png");

    public PanelEntity(FormMainClient formParent, int panelType, String txtTop, String txtBottom, boolean isOnline, int groupId, boolean isFriend, boolean isSendRequest) {

        this.panelType = panelType;
        this.txtTop = txtTop;
        this.txtBottom = txtBottom;
        this.isOnline = isOnline;
        this.groupId = groupId;
        this.isFriend = isFriend;
        this.isSendRequest = isSendRequest;

        if (panelType == PanelType.PANEL_FRIEND) {
            lblIcon.setIcon((isOnline) ? onlineIcon : offlineIcon);
        } else if (panelType == PanelType.PANEL_GROUP) {
            lblIcon.setIcon(groupIcon);
        } else {
            return;
        }

        this.setBackground(new java.awt.Color(255, 255, 255));
        this.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(200, 200, 200)));
        this.setMaximumSize(new java.awt.Dimension(1920, 66));
        this.setMinimumSize(new java.awt.Dimension(200, 60));
        this.setPreferredSize(new java.awt.Dimension(200, 60));
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(new java.awt.Color(150, 150, 150));
                lblTop.setForeground(new java.awt.Color(255, 255, 255));
                lblBottom.setForeground(new java.awt.Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new java.awt.Color(255, 255, 255));
                lblTop.setForeground(new java.awt.Color(0, 0, 0));
                lblBottom.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                String friendUser = txtBottom;
                String friendName = txtTop;

                if (e.getClickCount() == 2) {                    
                    int type = 0;
                    
                    if (isFriend) {
                        type = 0;
                    } else if (isSendRequest) {
                        type = -1;
                    } else {
                        type = 1;
                    }
                    formParent.clickPanelEntity(type, friendUser, friendName);
                }

                if (SwingUtilities.isRightMouseButton(e)) {
                    PopUpDemo menu = new PopUpDemo();
                    menu.show(e.getComponent(), e.getX(), e.getY());
                    menu.anItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            formParent.delFriend(friendUser, friendName);
                        }
                    });
                }
            }
        });

        lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIcon.setText("");

        if (isFriend) {
            lblTop.setFont(new java.awt.Font("Dialog", 1, 14));
        } else {
            lblTop.setFont(new java.awt.Font("Dialog", 2, 14));
        }
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTop.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        if (isFriend) {
            lblTop.setText(txtTop);
        } else if (this.isSendRequest) {
            lblTop.setText(txtTop + " - Đã gửi yêu cầu");
        } else {
            lblTop.setText(txtTop + " - Lời mời kết bạn");
        }
        

        if (isFriend) {
            lblBottom.setFont(new java.awt.Font("Dialog", 1, 14));
        } else {
            lblBottom.setFont(new java.awt.Font("Dialog", 2, 14));
        }
        lblBottom.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBottom.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblBottom.setText(txtBottom);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(this);
        this.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                ))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        formParent.getPanelWrapper().add(this);

        // Thêm vào mảng panelFriendMap hoặc panelGroupMap
        if (panelType == PanelType.PANEL_FRIEND) {
            formParent.getPanelFriendMap().put(txtBottom, this);
        } else if (panelType == PanelType.PANEL_GROUP) {
            formParent.getPanelGroupMap().put(groupId, this);
        }
    }

    public int getPanelType() {
        return panelType;
    }

    public void setPanelType(int panelType) {
        this.panelType = panelType;
    }

    public String getTxtTop() {
        return txtTop;
    }

    public void setTxtTop(String txtTop) {
        this.txtTop = txtTop;
    }

    public String getTxtBottom() {
        return txtBottom;
    }

    public void setTxtBottom(String txtBottom) {
        this.txtBottom = txtBottom;
    }

    public boolean isIsLogin() {
        return isOnline;
    }

    public void setIsLogin(boolean isLogin) {
        this.isOnline = isLogin;
    }

    public int getGroupId() {
        return groupId;
    }

    public boolean isIsFriend() {
        return isFriend;
    }

    public void setIsFriend(boolean isFriend) {
        this.isFriend = isFriend;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public JLabel getLblIcon() {
        return lblIcon;
    }

    public boolean isIsSendRequest() {
        return isSendRequest;
    }

    public void setIsSendRequest(boolean isSendRequest) {
        this.isSendRequest = isSendRequest;
    }

    public void updateLblIcon(boolean isOnline) {
        this.lblIcon.setIcon((isOnline) ? onlineIcon : offlineIcon);
    }

}
