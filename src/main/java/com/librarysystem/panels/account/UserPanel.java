
package com.librarysystem.panels.account;

import com.librarysystem.objects.ui.PalleteColors;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.librarysystem.handlers.IssuedBooksHandler;
import com.librarysystem.handlers.UserHandler;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.objects.User;
import com.librarysystem.objects.components.RoundedBorder;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class UserPanel extends javax.swing.JPanel {
    
    private final User user;
    
    public UserPanel(User user) {
        initComponents();
        this.setBackground(PalleteColors.DROPDOWN_PRESSED);
        this.setBorder(new RoundedBorder(5,0));
        this.user = user;
        textButton(deleteBtn);
        textButton(detailsBtn);
        
        
        fullNameLabel.setText(user.getFullName());
        studentNumLabel.setText(user.getStudentNumber());
        bookCountLabel.setText(Integer.toString(IssuedBooksHandler.getIssuedBooksCountFromUser(user.getEmail())));
        Image userImage = user.getIcon();
        Dimension dim = userIcon.getPreferredSize();
        userImage = userImage.getScaledInstance(dim.width, dim.height, Image.SCALE_SMOOTH);
        
        userIcon.setIcon(new ImageIcon(userImage));
        
        Utilities.addChangePointer(deleteBtn);
        Utilities.addChangePointer(detailsBtn);
    }
    
    private void textButton(JButton btn){
        Rectangle bounds = btn.getBounds();
        String text = btn.getText();
        Font font = btn.getFont();
        
        JPanel parent = (JPanel) btn.getParent();
        parent.remove(btn);
        
        btn = new JButton(){
            @Override
            public void paint(Graphics g){
                super.paint(g);
                
                if (getModel().isPressed()) setForeground(PalleteColors.ENTRY_TEXT_LIGHT_COLOR);
                else if (getModel().isRollover()) setForeground(new Color(90,90,90));
                else setForeground(PalleteColors.DROPDOWN_PRESSED);
                
            }
        };
        
        btn.setText(text);
        btn.setFont(font);
        btn.setBounds(bounds);
        btn.setBackground(PalleteColors.TRANSPARENT);
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.addActionListener((ActionEvent evt) -> {
            buttonAction(this,text);
        });
        
        parent.add(btn);
    }
    
    public String getFullName(){
        return user.getFullName();
    }
    
    public User.UserType getUserType(){
        return user.getUserType();
    }
    
    private void buttonAction(JPanel panel, String type){
        switch(type){
            case "Delete" -> {
                int doRemove = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to Remove " + user.getFullName() + "'s Account?", "Remove Account",JOptionPane.YES_NO_OPTION);
                
                if (doRemove != 0) return;
                
                if (UserHandler.getCurrentUser() == user) {
                    System.out.println("Cannot delete self.");
                    return;
                }
                
                UserHandler.removeUser(user);
                this.removeAll();
                JPanel parent = (JPanel) panel.getParent();
                parent.remove(this);
                parent.revalidate();
                parent.repaint();
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userIcon = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        bookIcon = new javax.swing.JLabel();
        bookCountLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        fullNameLabel = new javax.swing.JLabel();
        studentNumLabel = new javax.swing.JLabel();
        btnPanel = new javax.swing.JPanel();
        deleteBtn = new javax.swing.JButton();
        detailsBtn = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(280, 80));
        setLayout(new java.awt.BorderLayout());

        userIcon.setText("icn");
        userIcon.setOpaque(true);
        userIcon.setPreferredSize(new java.awt.Dimension(80, 80));
        add(userIcon, java.awt.BorderLayout.WEST);

        jPanel4.setPreferredSize(new java.awt.Dimension(260, 80));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(102, 51, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 153));
        jPanel3.setPreferredSize(new java.awt.Dimension(48, 48));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bookIcon.setForeground(new java.awt.Color(43, 49, 65));
        bookIcon.setText("bookicn");
        bookIcon.setPreferredSize(new java.awt.Dimension(40, 16));
        jPanel1.add(bookIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 0, 32, 28));
        Image bookImg = Utilities.getImage("/textures/books.png").getImage();
        bookImg = Utilities.changeImageColor(bookImg, PalleteColors.DROPDOWN);
        bookImg = bookImg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        bookIcon.setText("");
        bookIcon.setIcon(new ImageIcon(bookImg));
        bookIcon.setIcon(new ImageIcon(bookImg));

        bookCountLabel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        bookCountLabel.setForeground(new java.awt.Color(43, 49, 65));
        bookCountLabel.setText("3");
        bookCountLabel.setPreferredSize(new java.awt.Dimension(7, 18));
        jPanel1.add(bookCountLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 5, 22, -1));

        jPanel3.add(jPanel1, java.awt.BorderLayout.EAST);

        jPanel5.setForeground(new java.awt.Color(187, 188, 187));
        jPanel5.setPreferredSize(new java.awt.Dimension(120, 120));
        jPanel5.setLayout(null);

        fullNameLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        fullNameLabel.setForeground(new java.awt.Color(43, 49, 65));
        fullNameLabel.setText("JC Rosuelo");
        jPanel5.add(fullNameLabel);
        fullNameLabel.setBounds(10, 4, 120, 15);

        studentNumLabel.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        studentNumLabel.setForeground(new java.awt.Color(43, 49, 65));
        studentNumLabel.setText("22-1720-360");
        jPanel5.add(studentNumLabel);
        studentNumLabel.setBounds(10, 20, 120, 13);

        jPanel3.add(jPanel5, java.awt.BorderLayout.WEST);

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        btnPanel.setLayout(null);

        deleteBtn.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(43, 49, 65));
        deleteBtn.setText("Delete");
        deleteBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteBtn.setPreferredSize(new java.awt.Dimension(60, 20));
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        btnPanel.add(deleteBtn);
        deleteBtn.setBounds(100, 0, 80, 20);

        detailsBtn.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        detailsBtn.setForeground(new java.awt.Color(43, 49, 65));
        detailsBtn.setText("Details");
        detailsBtn.setPreferredSize(new java.awt.Dimension(60, 20));
        btnPanel.add(detailsBtn);
        detailsBtn.setBounds(10, 0, 80, 20);

        jPanel2.add(btnPanel, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel2, java.awt.BorderLayout.CENTER);

        add(jPanel4, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteBtnActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bookCountLabel;
    private javax.swing.JLabel bookIcon;
    private javax.swing.JPanel btnPanel;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton detailsBtn;
    private javax.swing.JLabel fullNameLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel studentNumLabel;
    private javax.swing.JLabel userIcon;
    // End of variables declaration//GEN-END:variables
}
