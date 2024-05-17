
package com.librarysystem.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import com.librarysystem.handlers.UserHandler;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.objects.ui.PalleteColors;
import com.librarysystem.objects.components.RoundedBorder;
import com.librarysystem.objects.User;
import com.librarysystem.objects.ui.Icons;

public class ClientDashboard extends MyPanel {
    
    Image emailIcon = Icons.emailIcon.getImage();
    Image person = Utilities.getImage("/textures/user.png").getImage();
    Image numberIcon = Utilities.getImage("/textures/number.png").getImage();
    Image locationIcon = Utilities.getImage("/textures/location.png").getImage();
    Image calendarIcon = Utilities.getImage("/textures/calendar.png").getImage();
    Image ayaya = Utilities.getImage("/textures/ayaya.png").getImage();
    Image showMore = Utilities.getImage("/textures/showMore.png").getImage();
    Image showLess = Utilities.getImage("/textures/showLess.png").getImage();

    public ClientDashboard() {
        initComponents();
        toggleButton(arrow);
    }
    
    public void setDashboardItems(){
        User currentUser = UserHandler.getCurrentUser();
        Image userIcon = currentUser.getIcon();
        Dimension userIconDim = userImgLabel.getPreferredSize();
        userIcon = userIcon.getScaledInstance(userIconDim.width, userIconDim.height, Image.SCALE_SMOOTH);
        LocalDateTime date = currentUser.getDateJoined();
        
        String month = date.getMonth().toString();
        String monthResized = "";
        monthResized += month.charAt(0);
        for (int i = 1; i < month.length(); i++) {
            char ch = month.charAt(i);
            ch = Character.toLowerCase(ch);
            monthResized += ch;
        }
        
        fullNameLabel.setText(currentUser.getFullName());
        emailLabel.setText(currentUser.getEmail());
        studentNumber.setText(currentUser.getStudentNumber());
        locationLabel.setText("Philippines");
        userImgLabel.setIcon(new ImageIcon(userIcon));
        dateJoinedLabel.setText(monthResized + " " + date.getDayOfMonth() + ", " + date.getYear());
        
    }
    
    private void toggleButton(JToggleButton btn){
        Dimension dim = btn.getPreferredSize();
        
        showMore = Utilities.changeImageColor(showMore, Color.WHITE);
        showMore = showMore.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        showLess = Utilities.changeImageColor(showLess, Color.WHITE);
        showLess = showLess.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel bruh = new JLabel();
        bruh.setIcon(new ImageIcon(showMore));
        bruh.setIcon(new ImageIcon(showLess));
        
        JPanel parent = (JPanel) btn.getParent();
        parent.remove(btn);
        
        btn = new JToggleButton(){
            @Override
            public void paint(Graphics g){
                super.paint(g);
                Graphics2D g2D = (Graphics2D) g;
                
                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Image toDraw;
                if (getModel().isSelected()) toDraw = showMore;
                else toDraw = showLess;
                
                if (getModel().isRollover()) {
                    toDraw = Utilities.changeImageColor(toDraw, PalleteColors.ENTRY_TEXT_LIGHT_COLOR);
                }
                
                g2D.drawImage(toDraw, 0,8, null);
            }
        };
        
        btn.setPreferredSize(dim);
        btn.setBackground(PalleteColors.TRANSPARENT);
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        addListener(btn);
        
        parent.add(btn,BorderLayout.EAST);
    }
    
    private void addListener(JToggleButton btn){
        btn.addActionListener((ActionEvent e) -> {
            if (btn.getModel().isSelected()) {
                recentBooksPanel.setSize(410, 100);
                revalidate();
                repaint();
            }
            else {
                recentBooksPanel.setSize(410, 350);
                revalidate();
                repaint();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        fullNameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        studentNumber = new javax.swing.JLabel();
        locationLabel = new javax.swing.JLabel();
        userImgLabel = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        dateJoinedLabel = new javax.swing.JLabel();
        recentBooksPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        arrow = new javax.swing.JToggleButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(224, 224, 224));
        setLayout(null);

        panel1.setBackground(new java.awt.Color(224, 224, 224));
        panel1.setPreferredSize(new java.awt.Dimension(930, 80));
        panel1.setLayout(null);

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Dashboard");
        panel1.add(jLabel13);
        jLabel13.setBounds(30, 10, 260, 29);
        jLabel13.setForeground(PalleteColors.SIDEBAR_MAIN_COLOR);

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(145, 145, 145));
        jLabel10.setText("Dashboard / client_dashboard");
        panel1.add(jLabel10);
        jLabel10.setBounds(30, 50, 190, 15);

        add(panel1);
        panel1.setBounds(0, 0, 980, 80);

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

        locationLabel.setBackground(new java.awt.Color(255, 255, 255));
        locationLabel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        locationLabel.setForeground(new java.awt.Color(65, 78, 101));
        locationLabel.setText("Location");
        jPanel1.add(locationLabel);
        locationIcon = Utilities.changeImageColor(locationIcon, PalleteColors.DROPDOWN);
        locationIcon = locationIcon.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        locationLabel.setIcon(new ImageIcon(locationIcon));

        jPanel23.add(jPanel1, java.awt.BorderLayout.EAST);

        userImgLabel.setText("image");
        userImgLabel.setPreferredSize(new java.awt.Dimension(225, 225));
        jPanel23.add(userImgLabel, java.awt.BorderLayout.WEST);
        userImgLabel.setText("");
        ayaya = ayaya.getScaledInstance(225, 225, Image.SCALE_SMOOTH);
        userImgLabel.setIcon(new ImageIcon(ayaya));

        add(jPanel23);
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

        add(jPanel24);
        jPanel24.setBounds(30, 310, 450, 50);
        jPanel24.setBorder(new RoundedBorder(12,0));

        recentBooksPanel.setBackground(new java.awt.Color(43, 49, 65));
        recentBooksPanel.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(43, 49, 65));
        jPanel3.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel8.setBackground(new java.awt.Color(65, 78, 101));
        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("     Recently Added Books");
        jPanel3.add(jLabel8, java.awt.BorderLayout.WEST);

        arrow.setText("arrow");
        arrow.setPreferredSize(new java.awt.Dimension(50, 50));
        arrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrowActionPerformed(evt);
            }
        });
        jPanel3.add(arrow, java.awt.BorderLayout.EAST);

        recentBooksPanel.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(120, 120, 120));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("No Recent Books");
        jPanel5.add(jLabel9, java.awt.BorderLayout.CENTER);

        recentBooksPanel.add(jPanel5, java.awt.BorderLayout.CENTER);

        add(recentBooksPanel);
        recentBooksPanel.setBounds(530, 90, 410, 350);
        recentBooksPanel.setBorder(new RoundedBorder(4,0));
    }// </editor-fold>//GEN-END:initComponents

    private void arrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrowActionPerformed
        if (arrow.getModel().isSelected()) {
            recentBooksPanel.setSize(410, 100);
            revalidate();
            repaint();
        }
        else {
            recentBooksPanel.setSize(410, 350);
            revalidate();
            repaint();
        }

    }//GEN-LAST:event_arrowActionPerformed

    @Override
    public void refreshItems() {
        setDashboardItems();
    }

    @Override
    public void resize() {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton arrow;
    private javax.swing.JLabel dateJoinedLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel fullNameLabel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel locationLabel;
    private java.awt.Panel panel1;
    private javax.swing.JPanel recentBooksPanel;
    private javax.swing.JLabel studentNumber;
    private javax.swing.JLabel userImgLabel;
    // End of variables declaration//GEN-END:variables

}
