
package com.librarysystem.panels.popups;

import com.librarysystem.Frame;
import com.librarysystem.handlers.IssuedBooksHandler;
import com.librarysystem.handlers.UserHandler;
import com.librarysystem.handlers.Utilities;
import javax.swing.JDialog;
import com.librarysystem.objects.User;
import com.librarysystem.objects.components.RoundedBorder;
import com.librarysystem.objects.ui.Icons;
import com.librarysystem.objects.ui.PalleteColors;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UserDetailsPopup extends JDialog{
    
    private Image backImg = Utilities.getImage("/textures/back.png").getImage();
    Image emailIcon = Icons.emailIcon.getImage();
    Image person = Utilities.getImage("/textures/user.png").getImage();
    Image numberIcon = Utilities.getImage("/textures/number.png").getImage();
    Image booksIcon = Utilities.getImage("/textures/books.png").getImage();
    Image calendarIcon = Utilities.getImage("/textures/calendar.png").getImage();
    Image ayaya = Utilities.getImage("/textures/ayaya.png").getImage();
    private final User user;

    public UserDetailsPopup(User user) {
        initComponents();
        this.user = user;
        
        if (UserHandler.getCurrentUserType() == User.UserType.ADMIN && UserHandler.getCurrentUser() != user) {
            if (user.getUserType() == User.UserType.ADMIN) promoteBtn.setEnabled(false);
            else demoteBtn.setEnabled(false);
        }
        else{
            promoteBtn.setEnabled(false);
            demoteBtn.setEnabled(false);
        }
        
        Image userIcon = user.getIcon();
        Dimension userIconDim = userImgLabel.getPreferredSize();
        userIcon = userIcon.getScaledInstance(userIconDim.width, userIconDim.height, Image.SCALE_SMOOTH);
        LocalDateTime date = user.getDateJoined();
        
        String month = date.getMonth().toString();
        String monthResized = "";
        monthResized += month.charAt(0);
        for (int i = 1; i < month.length(); i++) {
            char ch = month.charAt(i);
            ch = Character.toLowerCase(ch);
            monthResized += ch;
        }
        
        usernameLabel.setText(user.getFullName());
        fullNameLabel.setText(user.getFullName());
        emailLabel.setText(user.getEmail());
        studentNumber.setText(user.getStudentNumber());
        bookCountLabel.setText(Integer.toString(IssuedBooksHandler.getIssuedBooksCountFromUser(user.getEmail())));
        userImgLabel.setIcon(new ImageIcon(userIcon));
        dateJoinedLabel.setText(monthResized + " " + date.getDayOfMonth() + ", " + date.getYear());
        
        
        changeButton(backButton);
        
        jPanel10.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel11.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel12.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel13.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        tag = new javax.swing.JLabel();
        promoteBtn = new javax.swing.JButton(){
            @Override
            public void paint(Graphics g){
                Utilities.changeButtonColor(this, g);
            }
        };
        usernameLabel = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        fullNameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        studentNumber = new javax.swing.JLabel();
        bookCountLabel = new javax.swing.JLabel();
        userImgLabel = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        dateJoinedLabel = new javax.swing.JLabel();
        demoteBtn = new javax.swing.JButton(){
            @Override
            public void paint(Graphics g){
                Utilities.changeButtonColor(this, g);
            }
        };
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(224, 224, 224));
        setPreferredSize(new java.awt.Dimension(518, 457));

        jPanel9.setBackground(new java.awt.Color(224, 224, 224));
        jPanel9.setLayout(null);

        backButton.setText("jButton1");
        backButton.setPreferredSize(new java.awt.Dimension(30, 23));
        backButton.addActionListener(this::backButtonActionPerformed);
        jPanel9.add(backButton);
        backButton.setBounds(480, 0, 30, 23);

        tag.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        tag.setForeground(new java.awt.Color(0, 0, 0));
        tag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tag.setText("Tag : ");
        tag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(tag);
        tag.setBounds(0, 50, 635, 20);

        promoteBtn.setBackground(new java.awt.Color(95, 192, 170));
        promoteBtn.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        promoteBtn.setForeground(new java.awt.Color(255, 255, 255));
        promoteBtn.setText("Promote to Admin");
        promoteBtn.setPreferredSize(new java.awt.Dimension(100, 18));
        promoteBtn.addActionListener(this::promoteBtnActionPerformed);
        jPanel9.add(promoteBtn);
        promoteBtn.setBounds(30, 390, 120, 30);
        promoteBtn.setBackground(PalleteColors.BUTTON_PRESSED_TEXT);

        usernameLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(0, 0, 0));
        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameLabel.setText("Username");
        usernameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(usernameLabel);
        usernameLabel.setBounds(0, 0, 510, 50);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setPreferredSize(new java.awt.Dimension(450, 200));
        jPanel23.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(190, 190));
        jPanel1.setLayout(new java.awt.GridLayout(6, 1));

        fullNameLabel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        fullNameLabel.setForeground(new java.awt.Color(65, 78, 101));
        fullNameLabel.setText("Full Name");
        jPanel1.add(fullNameLabel);
        person = Utilities.changeImageColor(person, PalleteColors.DROPDOWN);
        person = person.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        fullNameLabel.setIcon(new ImageIcon(person));

        emailLabel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(65, 78, 101));
        emailLabel.setText("Email");
        jPanel1.add(emailLabel);
        emailIcon = Utilities.changeImageColor(emailIcon, PalleteColors.DROPDOWN);
        emailIcon = emailIcon.getScaledInstance(10, 7, Image.SCALE_SMOOTH);
        emailLabel.setIcon(new ImageIcon(emailIcon));

        studentNumber.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        studentNumber.setForeground(new java.awt.Color(65, 78, 101));
        studentNumber.setText("Student Number");
        jPanel1.add(studentNumber);
        numberIcon = Utilities.changeImageColor(numberIcon, PalleteColors.DROPDOWN);
        numberIcon = numberIcon.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        studentNumber.setIcon(new ImageIcon(numberIcon));

        bookCountLabel.setBackground(new java.awt.Color(255, 255, 255));
        bookCountLabel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        bookCountLabel.setForeground(new java.awt.Color(65, 78, 101));
        bookCountLabel.setText("Total Books");
        jPanel1.add(bookCountLabel);
        booksIcon = Utilities.changeImageColor(booksIcon, PalleteColors.DROPDOWN);
        booksIcon = booksIcon.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        bookCountLabel.setIcon(new ImageIcon(booksIcon));

        jPanel23.add(jPanel1, java.awt.BorderLayout.EAST);

        userImgLabel.setText("image");
        userImgLabel.setPreferredSize(new java.awt.Dimension(225, 225));
        jPanel23.add(userImgLabel, java.awt.BorderLayout.WEST);
        userImgLabel.setText("");
        ayaya = ayaya.getScaledInstance(225, 225, Image.SCALE_SMOOTH);
        userImgLabel.setIcon(new ImageIcon(ayaya));

        jPanel9.add(jPanel23);
        jPanel23.setBounds(30, 90, 450, 200);
        jPanel23.setBorder(new RoundedBorder(12,0));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(65, 78, 101));
        jLabel6.setText("Joined with us");
        jLabel6.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel24.add(jLabel6, java.awt.BorderLayout.WEST);
        calendarIcon = Utilities.changeImageColor(calendarIcon, PalleteColors.DROPDOWN);
        calendarIcon = calendarIcon.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel6.setIcon(new ImageIcon(calendarIcon));

        dateJoinedLabel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        dateJoinedLabel.setForeground(new java.awt.Color(65, 78, 101));
        dateJoinedLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dateJoinedLabel.setText("3/24/2024");
        dateJoinedLabel.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel24.add(dateJoinedLabel, java.awt.BorderLayout.EAST);

        jPanel9.add(jPanel24);
        jPanel24.setBounds(30, 310, 450, 50);
        jPanel24.setBorder(new RoundedBorder(12,0));

        demoteBtn.setBackground(new java.awt.Color(171, 37, 51));
        demoteBtn.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        demoteBtn.setForeground(new java.awt.Color(255, 255, 255));
        demoteBtn.setText("Demote to User");
        demoteBtn.setPreferredSize(new java.awt.Dimension(100, 18));
        demoteBtn.addActionListener(this::demoteBtnActionPerformed);
        jPanel9.add(demoteBtn);
        demoteBtn.setBounds(360, 390, 120, 30);
        promoteBtn.setBackground(PalleteColors.BUTTON_PRESSED_TEXT);

        getContentPane().add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel10.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel10, java.awt.BorderLayout.NORTH);

        jPanel11.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel11, java.awt.BorderLayout.SOUTH);

        jPanel12.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 447, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel12, java.awt.BorderLayout.EAST);

        jPanel13.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 447, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel13, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        
    }//GEN-LAST:event_backButtonActionPerformed

    private void promoteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_promoteBtnActionPerformed
        this.setAlwaysOnTop(false);
        UserHandler.promoteUser(user);
        Frame.removePopup();
        this.dispose();
    }//GEN-LAST:event_promoteBtnActionPerformed

    private void demoteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoteBtnActionPerformed
        this.setAlwaysOnTop(false);
        UserHandler.demoteAdmin(user);
        Frame.removePopup();
        this.dispose();
    }//GEN-LAST:event_demoteBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel bookCountLabel;
    private javax.swing.JLabel dateJoinedLabel;
    private javax.swing.JButton demoteBtn;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel fullNameLabel;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton promoteBtn;
    private javax.swing.JLabel studentNumber;
    private javax.swing.JLabel tag;
    private javax.swing.JLabel userImgLabel;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables

    private void changeButton(JButton btn){
        Rectangle bounds = btn.getBounds();
        Dimension dim = btn.getPreferredSize();
        
        backImg = Utilities.changeImageColor(backImg, Color.WHITE);
        backImg = backImg.getScaledInstance(dim.width/2, dim.height/2, Image.SCALE_SMOOTH);
        ImageIcon backImgNormal = new ImageIcon(backImg);
        
        Image backHovered = Utilities.changeImageColor(backImg, PalleteColors.BUTTON_PRESSED_TEXT);
        backHovered = backHovered.getScaledInstance(dim.width/2, dim.height/2, Image.SCALE_SMOOTH);
        ImageIcon backImgHovered = new ImageIcon(backHovered);
        
        
        JPanel parent = (JPanel) btn.getParent();
        parent.remove(btn);
        
        btn = new JButton(){
            @Override
            public void paint(Graphics g){
                super.paint(g);
                Graphics2D g2D = (Graphics2D) g;
                ImageIcon toDraw;
                Color color;
                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    toDraw = backImgHovered;
                    color = PalleteColors.DROPDOWN_PRESSED;
                }
                else if (getModel().isRollover()) {
                    toDraw = backImgHovered;
                    color = PalleteColors.DROPDOWN;
                }
                else{
                    toDraw = backImgNormal;
                    color = PalleteColors.SIDEBAR_MAIN_COLOR;
                }
                
                g2D.setColor(color);
                g2D.fillRect(0, 0, getWidth()-1, getHeight()-1);
                
                int x = (getWidth() - toDraw.getIconWidth())/2;
                int y = (getHeight()- toDraw.getIconHeight())/2;
                g2D.drawImage(toDraw.getImage(), x,y, null);
            }
        };
        
        btn.setBounds(bounds);
        btn.setBackground(PalleteColors.DROPDOWN);
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.addActionListener(l -> {
            Frame.removePopup();
            this.dispose();
        });
        Utilities.addChangePointer(btn);
        parent.add(btn);
    }

}


