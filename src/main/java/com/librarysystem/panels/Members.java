
package com.librarysystem.panels;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.objects.ui.PalleteColors;
import com.librarysystem.objects.components.RoundedBorder;
import com.librarysystem.objects.User;
import com.librarysystem.panels.account.UserPanel;

public class Members extends MyPanel {
    
    private Image userImg = Utilities.getImage("/textures/user.png").getImage();
    private Image refreshIcon = Utilities.getImage("/textures/refresh.png").getImage();
    private ArrayList<UserPanel> users = new ArrayList<>();
    
    public Members() {
        initComponents();
        
        searchUser.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {}
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }
        });
        
        Utilities.addChangePointer(refreshButton);
        Utilities.addChangePointer(userTypeSearch);
    }
    
    private void search(){
        userList.removeAll();
        
        for (UserPanel userPanel : users) {
            boolean searchContainsTitle = userPanel.getFullName().toLowerCase().contains(searchUser.getText().toLowerCase());
            User.UserType type;
            
            if (userTypeSearch.getSelectedItem() == "Admin") type = User.UserType.ADMIN;
            else if (userTypeSearch.getSelectedItem() == "User") type = User.UserType.USER;
            else type = null;
            
            if (searchUser.getText().equals("") || searchUser.getText().equals("Search User")) searchContainsTitle = true;
            
            if (searchContainsTitle) {
                if (type == null) {
                    userList.add(userPanel);
                }
                else if (userPanel.getUserType() == type) {
                    userList.add(userPanel);
                }
            }
        }
        
        userList.revalidate();
        userList.repaint();
        
        bottomPanel.getVerticalScrollBar().setUnitIncrement(16);
    }
    
    @Override
    public void refreshItems(){
        userList.removeAll();
        users = Utilities.makeUserPanels();
        searchUser.setText("Search User");
        search();
        resize();
    }
    
    @Override
    public void resize(){
        
        topPanel.setSize(getWidth() - 70, 50);
        bottomPanel.setSize(getWidth() - 70, getHeight() - 190); 
        jPanel6.setLocation(topPanel.getWidth() - 180, 10);
        
        int userListWidth = userList.getWidth() - 15;
        int userListLayers = (userList.getComponentCount() * 267)/userListWidth + 1;
        int userListHeight = (userListLayers * (90) + 5);
        
        userList.setPreferredSize(new Dimension(userListWidth, userListHeight));
        this.revalidate();
        this.repaint();
    }    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        topPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        refreshButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        searchUser = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        userTypeSearch = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        bottomPanel = new javax.swing.JScrollPane();
        userList = new javax.swing.JPanel();

        jButton3.setText("jButton3");

        setBackground(new java.awt.Color(224, 224, 224));
        setForeground(new java.awt.Color(145, 145, 145));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        setLayout(null);

        topPanel.setBackground(new java.awt.Color(255, 255, 255));
        topPanel.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Users Data Table");
        topPanel.add(jLabel1);
        jLabel1.setBounds(50, 20, 89, 10);

        refreshButton.setBackground(new java.awt.Color(255, 255, 255));
        refreshButton.setForeground(new java.awt.Color(0, 0, 0));
        refreshButton.setText("R");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        topPanel.add(refreshButton);
        refreshButton.setBounds(10, 10, 30, 30);
        refreshButton.setBorder(new RoundedBorder(8,1));

        refreshButton.setText("");
        refreshIcon = Utilities.changeImageColor(refreshIcon, PalleteColors.DROPDOWN);
        refreshIcon = refreshIcon.getScaledInstance(refreshButton.getPreferredSize().width, refreshButton.getPreferredSize().height, Image.SCALE_SMOOTH);
        refreshButton.setIcon(new ImageIcon(refreshIcon));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(145, 145, 145));
        jLabel2.setText("icn");
        jLabel2.setPreferredSize(new java.awt.Dimension(15, 15));
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));
        userImg = Utilities.changeImageColor(userImg, PalleteColors.DROPDOWN);
        userImg = userImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel2.setText("");
        jLabel2.setIcon(new ImageIcon(userImg));

        searchUser.setBackground(new java.awt.Color(255, 255, 255));
        searchUser.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        searchUser.setForeground(new java.awt.Color(145, 145, 145));
        searchUser.setText("Search User");
        searchUser.setName("searchUser"); // NOI18N
        searchUser.setPreferredSize(new java.awt.Dimension(120, 21));
        searchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchUserActionPerformed(evt);
            }
        });
        jPanel5.add(searchUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 5, -1, -1));
        searchUser.setBorder(BorderFactory.createEmptyBorder());

        searchUser.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchUser.getText().equals("Search User")) {
                    searchUser.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchUser.getText().isEmpty()) {
                    searchUser.setText("Search User");
                }
            }
        });

        topPanel.add(jPanel5);
        jPanel5.setBounds(150, 10, 160, 30);
        jPanel5.setBorder(new RoundedBorder(8,1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(145, 145, 145));
        jLabel3.setText("icn");
        jLabel3.setPreferredSize(new java.awt.Dimension(15, 15));
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));
        userImg = Utilities.changeImageColor(userImg, PalleteColors.DROPDOWN);
        userImg = userImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel3.setText("");
        jLabel3.setIcon(new ImageIcon(userImg));

        userTypeSearch.setBackground(new java.awt.Color(255, 255, 255));
        userTypeSearch.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        userTypeSearch.setForeground(new java.awt.Color(145, 145, 145));
        userTypeSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Admin", "User" }));
        userTypeSearch.setPreferredSize(new java.awt.Dimension(120, 21));
        userTypeSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTypeSearchActionPerformed(evt);
            }
        });
        jPanel6.add(userTypeSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 5, -1, -1));

        topPanel.add(jPanel6);
        jPanel6.setBounds(730, 10, 160, 30);
        jPanel6.setBorder(new RoundedBorder(8,1));

        add(topPanel);
        topPanel.setBounds(30, 90, 910, 50);
        topPanel.setBorder(new RoundedBorder(8,0));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(145, 145, 145));
        jLabel10.setText("Members / member_list");
        add(jLabel10);
        jLabel10.setBounds(30, 50, 127, 15);

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Members Records");
        add(jLabel13);
        jLabel13.setBounds(30, 10, 199, 29);
        jLabel13.setForeground(PalleteColors.SIDEBAR_MAIN_COLOR);

        userList.setBackground(new java.awt.Color(255, 255, 255));
        userList.setPreferredSize(new java.awt.Dimension(865, 300));
        userList.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        bottomPanel.setViewportView(userList);
        userList.setBorder(new RoundedBorder(8,0));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder());

        add(bottomPanel);
        bottomPanel.setBounds(30, 150, 910, 390);
    }// </editor-fold>//GEN-END:initComponents

    private void searchUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchUserActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        refreshItems();
        resize();
    }//GEN-LAST:event_formComponentShown

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
//        resize();
	}//GEN-LAST:event_formComponentResized

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        refreshItems();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void userTypeSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTypeSearchActionPerformed
        search();
    }//GEN-LAST:event_userTypeSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane bottomPanel;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField searchUser;
    private javax.swing.JPanel topPanel;
    private javax.swing.JPanel userList;
    private javax.swing.JComboBox<String> userTypeSearch;
    // End of variables declaration//GEN-END:variables
}
