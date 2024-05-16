
package com.librarysystem.panels.popups;

import com.librarysystem.Frame;
import com.librarysystem.handlers.CategoryHandler;
import com.librarysystem.handlers.IssuedBooksHandler;
import com.librarysystem.handlers.UserHandler;
import com.librarysystem.handlers.Utilities;
import javax.swing.JDialog;
import com.librarysystem.objects.Book;
import com.librarysystem.objects.User;
import com.librarysystem.objects.components.RoundedBorder;
import com.librarysystem.objects.ui.PalleteColors;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BookDetailsPopup extends JDialog{
    
    private Image backImg = Utilities.getImage("/textures/back.png").getImage();
    private final Book book;

    public BookDetailsPopup(Book book) {
        initComponents();
        
        this.book = book;
        Image img = new ImageIcon(book.getBookIcon()).getImage();
        img = img.getScaledInstance(234, 274, Image.SCALE_SMOOTH);
        
        icon.setIcon(new ImageIcon(img));
        title.setText(book.getTitle());
        tag.setText(CategoryHandler.getCategory(book.getCategoryID()).getCategoryName());
        description.setText(book.getDescription());
        remaining.setText("Ammount Remaining : " + Integer.toString(book.getAmmountLeft()));
        author.setText("Written by : " + book.getAuthor());
        
        LocalDate dateWritten = book.getDatePublished();
        String month = dateWritten.getMonth().toString().toLowerCase();
        month = Character.toString(Character.toUpperCase(month.charAt(0))) + month.substring(1, month.length());
        String day = Integer.toString(dateWritten.getDayOfMonth());
        String year = Integer.toString(dateWritten.getYear());
        String date = month + ", " + day + " " + year;
        datePublished.setText("Written by : " + date);
        changeButton(backButton);
        
        jPanel10.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel11.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel12.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel13.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        
    }
    
    private void addToListButtonAction(){
        int dateBorrowed;
        if (UserHandler.getCurrentUser().getUserType() == User.UserType.ADMIN) dateBorrowed = book.getMaxDaysAdminBorrowed();
        else dateBorrowed = book.getMaxDaysUserBorrowed();
        
        this.setAlwaysOnTop(false);
        IssuedBooksHandler.addIssuedBookToCurrentUser(book,dateBorrowed);
        Frame.removePopup();
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        backButton = new javax.swing.JButton();
        datePublished = new javax.swing.JLabel();
        author = new javax.swing.JLabel();
        remaining = new javax.swing.JLabel();
        tag = new javax.swing.JLabel();
        addToListButton = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        icon = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(224, 224, 224));
        setPreferredSize(new java.awt.Dimension(644, 563));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(null);

        description.setEditable(false);
        description.setBackground(new java.awt.Color(255, 255, 255));
        description.setColumns(20);
        description.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        description.setLineWrap(true);
        description.setRows(5);
        description.setBorder(null);
        jScrollPane1.setViewportView(description);

        jPanel9.add(jScrollPane1);
        jScrollPane1.setBounds(270, 100, 350, 290);

        backButton.setText("jButton1");
        backButton.setPreferredSize(new java.awt.Dimension(30, 23));
        backButton.addActionListener(this::backButtonActionPerformed);
        jPanel9.add(backButton);
        backButton.setBounds(605, 0, 30, 23);

        datePublished.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        datePublished.setForeground(new java.awt.Color(0, 0, 0));
        datePublished.setText("Date Published : ");
        jPanel9.add(datePublished);
        datePublished.setBounds(11, 420, 240, 15);

        author.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        author.setForeground(new java.awt.Color(0, 0, 0));
        author.setText("Written by : ");
        jPanel9.add(author);
        author.setBounds(11, 400, 240, 15);

        remaining.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        remaining.setForeground(new java.awt.Color(0, 0, 0));
        remaining.setText("Ammount Remaning : ");
        jPanel9.add(remaining);
        remaining.setBounds(271, 400, 350, 40);

        tag.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        tag.setForeground(new java.awt.Color(0, 0, 0));
        tag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tag.setText("Tag : ");
        tag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(tag);
        tag.setBounds(0, 50, 635, 20);

        addToListButton.setBackground(new java.awt.Color(95, 192, 170));
        addToListButton.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        addToListButton.setForeground(new java.awt.Color(255, 255, 255));
        addToListButton.setText("Add to My List");
        addToListButton.setPreferredSize(new java.awt.Dimension(100, 18));
        addToListButton.addActionListener(this::addToListButtonActionPerformed);
        jPanel9.add(addToListButton);
        addToListButton.setBounds(490, 510, 120, 30);
        addToListButton.setBackground(PalleteColors.BUTTON_PRESSED_TEXT);

        title.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Days To Borrow");
        title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(title);
        title.setBounds(0, 0, 633, 50);

        jPanel1.setBackground(new java.awt.Color(65, 78, 101));
        jPanel1.setBorder(new RoundedBorder(8,0));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 290));
        jPanel1.setLayout(new java.awt.BorderLayout());

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/noImage.png"))); // NOI18N
        jPanel1.add(icon, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel1);
        jPanel1.setBounds(10, 100, 250, 290);

        getContentPane().add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel10.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 644, Short.MAX_VALUE)
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
            .addGap(0, 644, Short.MAX_VALUE)
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
            .addGap(0, 553, Short.MAX_VALUE)
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
            .addGap(0, 553, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel13, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backButtonActionPerformed

    private void addToListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToListButtonActionPerformed
        addToListButtonAction();
    }//GEN-LAST:event_addToListButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToListButton;
    private javax.swing.JLabel author;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel datePublished;
    private javax.swing.JTextArea description;
    private javax.swing.JLabel icon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel remaining;
    private javax.swing.JLabel tag;
    private javax.swing.JLabel title;
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


