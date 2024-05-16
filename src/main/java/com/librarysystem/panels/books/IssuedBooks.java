
package com.librarysystem.panels.books;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.objects.IssuedBook.BorrowedBookStatus;
import com.librarysystem.objects.components.LineBorderSide;
import com.librarysystem.objects.components.LineBorderSide.Side;
import com.librarysystem.objects.ui.PalleteColors;
import com.librarysystem.objects.components.RoundedBorder;
import com.librarysystem.panels.MyPanel;

public class IssuedBooks extends MyPanel {
    
    private Image categoryImg = Utilities.getImage("/textures/categories.png").getImage();
    private Image refreshIcon = Utilities.getImage("/textures/refresh.png").getImage();
    private Image userImg = Utilities.getImage("/textures/user.png").getImage();
    private ArrayList<IssuedBooksPanel> issuedBooks = new ArrayList<>();
    private final GridLayout layout = new GridLayout(issuedBooks.size(),1);
    
    public IssuedBooks() {
        initComponents();
        
        searchBook.getDocument().addDocumentListener(new DocumentListener() {
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
        issuedList.setLayout(layout);
        
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
    }
    
    private void search(){
        issuedList.removeAll();
        
        for (IssuedBooksPanel issuedBooksPanel : issuedBooks) {
            boolean searchContainsBook = issuedBooksPanel.getBook().getTitle().toLowerCase().contains(searchBook.getText().toLowerCase());
            boolean searchContainsUser = issuedBooksPanel.getUser().getUserName().toLowerCase().contains(searchUser.getText().toLowerCase());
            BorrowedBookStatus type = null;
            
            if (userTypeSearch.getSelectedItem() == BorrowedBookStatus.BORROWED.text) type = BorrowedBookStatus.BORROWED;
            else if (userTypeSearch.getSelectedItem() == BorrowedBookStatus.OVERDUE.text) type = BorrowedBookStatus.OVERDUE;
            else if (userTypeSearch.getSelectedItem() == BorrowedBookStatus.PENDING.text) type = BorrowedBookStatus.PENDING;
            
            if (searchBook.getText().equals("") || searchBook.getText().equals("Search Book")) searchContainsBook = true;
            if (searchUser.getText().equals("") || searchUser.getText().equals("Search User")) searchContainsUser = true;
            
            if (searchContainsBook && searchContainsUser) {
                if (type == null) {
                    issuedList.add(issuedBooksPanel);
                }
                else if (issuedBooksPanel.getBorrowedBook().getStatus() == type) {
                    issuedList.add(issuedBooksPanel);
                }
            }
        }
        
        issuedList.revalidate();
        issuedList.repaint();
    }
    
    @Override
    public void refreshItems(){
        issuedList.removeAll();
        issuedBooks = Utilities.makeIssuedBooksPanel();
        issuedBooks.sort(Comparator.comparing(IssuedBooksPanel::getTitle));
        searchBook.setText("Search Book");
        searchUser.setText("Search User");
        search();
        resize();
    }
    
    @Override
    public void resize(){
        
        topPanel.setSize(getWidth() - 70, 50);
        issuedBooksMainPanel.setSize(getWidth() - 70, getHeight() - 190); 
        jPanel6.setLocation(topPanel.getWidth() - 180, 10);
        
        int columnHeight = ((65 * issuedBooks.size()));
        
        boolean hasScrollWheel = issuedBooks.size() * 65 < jPanel11.getHeight();
        if (hasScrollWheel) {
            jPanel3.setPreferredSize(new Dimension(141,141));
            jPanel1.revalidate();
            jPanel1.repaint();
        }
        else {
            jPanel3.setPreferredSize(new Dimension(160,160));
            jPanel1.revalidate();
            jPanel1.repaint();
        }
        
        jPanel11.setPreferredSize(new Dimension(870, columnHeight));
        issuedList.setSize(new Dimension(jPanel11.getWidth(), columnHeight));
        layout.setRows(issuedBooks.size());
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
        searchBook = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        userTypeSearch = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        searchUser = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        issuedBooksMainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        issuedList = new javax.swing.JPanel(){
            @Override
            public void paint(Graphics g){
                super.paint(g);

            }
        };

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
        jLabel1.setText("Issued Books Table");
        topPanel.add(jLabel1);
        jLabel1.setBounds(50, 14, 105, 20);

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
        categoryImg = Utilities.changeImageColor(categoryImg, PalleteColors.DROPDOWN);
        categoryImg = categoryImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel2.setText("");
        jLabel2.setIcon(new ImageIcon(categoryImg));

        searchBook.setBackground(new java.awt.Color(255, 255, 255));
        searchBook.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        searchBook.setForeground(new java.awt.Color(145, 145, 145));
        searchBook.setText("Search Book");
        searchBook.setName("searchBook"); // NOI18N
        searchBook.setPreferredSize(new java.awt.Dimension(120, 21));
        searchBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBookActionPerformed(evt);
            }
        });
        jPanel5.add(searchBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 5, -1, -1));
        searchBook.setBorder(BorderFactory.createEmptyBorder());

        searchBook.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBook.getText().equals("Search Book")) {
                    searchBook.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchBook.getText().isEmpty()) {
                    searchBook.setText("Search Book");
                }
            }
        });

        topPanel.add(jPanel5);
        jPanel5.setBounds(170, 10, 160, 30);
        jPanel5.setBorder(new RoundedBorder(8,1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(145, 145, 145));
        jLabel3.setText("icn");
        jLabel3.setPreferredSize(new java.awt.Dimension(15, 15));
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));
        categoryImg = Utilities.changeImageColor(categoryImg, PalleteColors.DROPDOWN);
        categoryImg = categoryImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel3.setText("");
        jLabel3.setIcon(new ImageIcon(categoryImg));

        userTypeSearch.setBackground(new java.awt.Color(255, 255, 255));
        userTypeSearch.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        userTypeSearch.setForeground(new java.awt.Color(145, 145, 145));
        userTypeSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Borrowed", "Pending Return", "Returned", "Overdue" }));
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

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(145, 145, 145));
        jLabel11.setText("icn");
        jLabel11.setPreferredSize(new java.awt.Dimension(15, 15));
        jPanel12.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));
        userImg = Utilities.changeImageColor(userImg, PalleteColors.DROPDOWN);
        userImg = userImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel11.setText("");
        jLabel11.setIcon(new ImageIcon(userImg));

        searchUser.setBackground(new java.awt.Color(255, 255, 255));
        searchUser.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        searchUser.setForeground(new java.awt.Color(145, 145, 145));
        searchUser.setText("Search User");
        searchUser.setName("searchCategory"); // NOI18N
        searchUser.setPreferredSize(new java.awt.Dimension(120, 21));
        searchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchUserActionPerformed(evt);
            }
        });
        jPanel12.add(searchUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 5, -1, -1));
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

        topPanel.add(jPanel12);
        jPanel12.setBounds(360, 10, 160, 30);
        jPanel12.setBorder(new RoundedBorder(8,1));

        add(topPanel);
        topPanel.setBounds(30, 90, 910, 50);
        topPanel.setBorder(new RoundedBorder(8,0));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(145, 145, 145));
        jLabel10.setText("Books / issued_books_list");
        add(jLabel10);
        jLabel10.setBounds(30, 50, 160, 15);

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Issued Books List");
        add(jLabel13);
        jLabel13.setBounds(30, 10, 250, 29);
        jLabel13.setForeground(PalleteColors.SIDEBAR_MAIN_COLOR);

        issuedBooksMainPanel.setBackground(PalleteColors.DROPDOWN_PRESSED);
        issuedBooksMainPanel.setPreferredSize(new java.awt.Dimension(910, 390));
        issuedBooksMainPanel.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(PalleteColors.DROPDOWN_PRESSED);
        jPanel2.setPreferredSize(new java.awt.Dimension(102, 101));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Book ID");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setPreferredSize(new java.awt.Dimension(70, 17));
        jPanel2.add(jLabel4, java.awt.BorderLayout.CENTER);
        jLabel4.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        jPanel1.add(jPanel2, java.awt.BorderLayout.WEST);

        jPanel3.setBackground(PalleteColors.DROPDOWN_PRESSED);
        jPanel3.setPreferredSize(new java.awt.Dimension(140, 140));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Actions");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(jLabel7, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.EAST);
        jPanel3.setBorder(new LineBorderSide(Side.SOUTH,2,Color.WHITE));

        jPanel4.setBackground(PalleteColors.DROPDOWN_PRESSED);
        jPanel4.setLayout(new java.awt.GridLayout(1, 4));

        jPanel7.setBackground(PalleteColors.DROPDOWN_PRESSED);
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Book Name");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(jLabel5, java.awt.BorderLayout.CENTER);
        jLabel5.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        jPanel4.add(jPanel7);

        jPanel8.setBackground(PalleteColors.DROPDOWN_PRESSED);
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Issued By");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel6, java.awt.BorderLayout.CENTER);
        jLabel6.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        jPanel4.add(jPanel8);

        jPanel9.setBackground(PalleteColors.DROPDOWN_PRESSED);
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Issued On");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(jLabel8, java.awt.BorderLayout.CENTER);
        jLabel8.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        jPanel4.add(jPanel9);

        jPanel10.setBackground(PalleteColors.DROPDOWN_PRESSED);
        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Return By");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel9, java.awt.BorderLayout.CENTER);
        jLabel9.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        jPanel4.add(jPanel10);

        jPanel13.setBackground(PalleteColors.DROPDOWN_PRESSED);
        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Status");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel13.add(jLabel12, java.awt.BorderLayout.CENTER);
        jLabel12.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        jPanel4.add(jPanel13);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        issuedBooksMainPanel.add(jPanel1, java.awt.BorderLayout.NORTH);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(null);

        issuedList.setBackground(new java.awt.Color(255, 255, 255));
        issuedList.setLayout(new java.awt.GridLayout(1, 0));
        jPanel11.add(issuedList);
        issuedList.setBounds(0, 0, 910, 360);

        jScrollPane1.setViewportView(jPanel11);

        issuedBooksMainPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(issuedBooksMainPanel);
        issuedBooksMainPanel.setBounds(30, 150, 910, 390);
        issuedBooksMainPanel.setBorder(new RoundedBorder(8,0));
    }// </editor-fold>//GEN-END:initComponents

    private void searchBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBookActionPerformed

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

    private void searchUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchUserActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel issuedBooksMainPanel;
    private javax.swing.JPanel issuedList;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField searchBook;
    private javax.swing.JTextField searchUser;
    private javax.swing.JPanel topPanel;
    private javax.swing.JComboBox<String> userTypeSearch;
    // End of variables declaration//GEN-END:variables
}
