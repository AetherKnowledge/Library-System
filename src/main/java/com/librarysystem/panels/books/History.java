
package com.librarysystem.panels.books;

import com.librarysystem.handlers.UserHandler;
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
import com.librarysystem.objects.components.LineBorderSide;
import com.librarysystem.objects.components.LineBorderSide.Side;
import com.librarysystem.objects.ui.PalleteColors;
import com.librarysystem.objects.components.RoundedBorder;
import com.librarysystem.panels.MyPanel;

public class History extends MyPanel {
    
    private Image categoryImg = Utilities.getImage("/textures/categories.png").getImage();
    private Image refreshIcon = Utilities.getImage("/textures/refresh.png").getImage();
    private Image userImg = Utilities.getImage("/textures/user.png").getImage();
    private ArrayList<IssuedBooksPanel> returnedBooksPanel = new ArrayList<>();
    private final GridLayout layout = new GridLayout(returnedBooksPanel.size(),1);
    
    public History() {
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
        
        Utilities.addChangePointer(refreshButton);
        returnedBooksList.setLayout(layout);
    }
    
    private void search(){
        returnedBooksList.removeAll();
        
        for (IssuedBooksPanel issuedBooksPanel : returnedBooksPanel) {
            boolean searchContainsBook = issuedBooksPanel.getBook().getTitle().toLowerCase().contains(searchBook.getText().toLowerCase());
            
            if (searchBook.getText().equals("") || searchBook.getText().equals("Search Book")) searchContainsBook = true;
            
            if (searchContainsBook) {
                returnedBooksList.add(issuedBooksPanel);
            }
        }
        
        returnedBooksList.revalidate();
        returnedBooksList.repaint();
    }
    
    @Override
    public void refreshItems(){
        returnedBooksList.removeAll();
        returnedBooksPanel = Utilities.makeUsersReturnedBooksPanel(UserHandler.getCurrentUser());
        returnedBooksPanel.sort(Comparator.comparing(IssuedBooksPanel::getTitle));
        searchBook.setText("Search Book");
        search();
        resize();
    }
    
    @Override
    public void resize(){
        
        topPanel.setSize(getWidth() - 70, 50);
        issuedBooksMainPanel.setSize(getWidth() - 70, getHeight() - 190); 
        
        int columnHeight = ((65 * returnedBooksPanel.size()));
        
        boolean hasScrollWheel = returnedBooksPanel.size() * 65 < jPanel11.getHeight();
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
        returnedBooksList.setSize(new Dimension(jPanel11.getWidth(), columnHeight));
        layout.setRows(returnedBooksPanel.size());
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
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        issuedBooksMainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        returnedBooksList = new javax.swing.JPanel(){
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

        add(topPanel);
        topPanel.setBounds(30, 90, 910, 50);
        topPanel.setBorder(new RoundedBorder(8,0));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(145, 145, 145));
        jLabel10.setText("Books / my_book_history");
        add(jLabel10);
        jLabel10.setBounds(30, 50, 160, 15);

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("My Book History");
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

        jPanel3.setBackground(PalleteColors.DROPDOWN_PRESSED);
        jPanel3.setPreferredSize(new java.awt.Dimension(140, 140));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Returned On");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(jLabel7, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel3);
        jPanel3.setBorder(new LineBorderSide(Side.SOUTH,2,Color.WHITE));

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        issuedBooksMainPanel.add(jPanel1, java.awt.BorderLayout.NORTH);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(null);

        returnedBooksList.setBackground(new java.awt.Color(255, 255, 255));
        returnedBooksList.setLayout(new java.awt.GridLayout(1, 0));
        jPanel11.add(returnedBooksList);
        returnedBooksList.setBounds(0, 0, 910, 360);

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel issuedBooksMainPanel;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JPanel returnedBooksList;
    private javax.swing.JTextField searchBook;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
