
package com.librarysystem.panels.category;

import java.awt.Color;
import java.awt.Dimension;
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

public class CategoryList extends MyPanel {
    
    private Image categoryImg = Utilities.getImage("/textures/categories.png").getImage();
    private Image refreshIcon = Utilities.getImage("/textures/refresh.png").getImage();
    private ArrayList<CategoryPanel> categories = new ArrayList<>();
    private final GridLayout layout = new GridLayout(categories.size(),1);
    
    public CategoryList() {
        initComponents();
        
        searchCategory.getDocument().addDocumentListener(new DocumentListener() {
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
        categoryList.setLayout(layout);
        
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
    }
    
    private void search(){
        categoryList.removeAll();
        
        for (CategoryPanel categoryPanel : categories) {
            boolean searchContainsCategory = categoryPanel.getCategoryName().toLowerCase().contains(searchCategory.getText().toLowerCase());
            
            if (searchCategory.getText().equals("") || searchCategory.getText().equals("Search Category")) searchContainsCategory = true;
            
            if (searchContainsCategory) {
                categoryList.add(categoryPanel);
            }
        }
        
        categoryList.revalidate();
        categoryList.repaint();
    }
    
    @Override
    public void refreshItems(){
        categoryList.removeAll();
        categories = Utilities.makeCategoryPanels();
        categories.sort(Comparator.comparing(CategoryPanel::getCategoryID));
        searchCategory.setText("Search Category");
        search();
        resize();
    }
    
    @Override
    public void resize(){
        
        topPanel.setSize(getWidth() - 70, 50);
        categoryMainPanel.setSize(getWidth() - 70, getHeight() - 190); 
        jPanel6.setLocation(topPanel.getWidth() - 180, jPanel6.getY());
        
        int categoryWidth = ((65 * categories.size()));
        
        boolean hasScrollWheel = categories.size() * 65 < jPanel15.getHeight();
        if (hasScrollWheel) {
            jPanel3.setPreferredSize(new Dimension(121,121));
            jPanel1.revalidate();
            jPanel1.repaint();
        }
        else {
            jPanel3.setPreferredSize(new Dimension(140,140));
            jPanel1.revalidate();
            jPanel1.repaint();
        }
        
        jScrollPane1.setPreferredSize(new Dimension(categoryMainPanel.getWidth(),categoryMainPanel.getHeight()-30));
        jPanel15.setPreferredSize(new Dimension(870, categoryWidth));
        categoryList.setSize(new Dimension(jPanel15.getWidth(), categoryWidth));
        layout.setRows(categories.size());
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
        searchCategory = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        userTypeSearch = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        categoryMainPanel = new javax.swing.JPanel();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel15 = new javax.swing.JPanel();
        categoryList = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

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
        jLabel1.setText("Category Data Table");
        topPanel.add(jLabel1);
        jLabel1.setBounds(50, 14, 107, 20);

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

        searchCategory.setBackground(new java.awt.Color(255, 255, 255));
        searchCategory.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        searchCategory.setForeground(new java.awt.Color(145, 145, 145));
        searchCategory.setText("Search Category");
        searchCategory.setName("searchCategory"); // NOI18N
        searchCategory.setPreferredSize(new java.awt.Dimension(120, 21));
        searchCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCategoryActionPerformed(evt);
            }
        });
        jPanel5.add(searchCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 5, -1, -1));
        searchCategory.setBorder(BorderFactory.createEmptyBorder());

        searchCategory.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchCategory.getText().equals("Search Category")) {
                    searchCategory.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchCategory.getText().isEmpty()) {
                    searchCategory.setText("Search Category");
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
        jLabel10.setText("Categories / category_list");
        add(jLabel10);
        jLabel10.setBounds(30, 50, 160, 15);

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Category List");
        add(jLabel13);
        jLabel13.setBounds(30, 10, 144, 29);
        jLabel13.setForeground(PalleteColors.SIDEBAR_MAIN_COLOR);

        categoryMainPanel.setBackground(PalleteColors.DROPDOWN_PRESSED);
        categoryMainPanel.setPreferredSize(new java.awt.Dimension(910, 390));
        categoryMainPanel.setLayout(new java.awt.BorderLayout());

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
        jLabel4.setText("Category ID");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setPreferredSize(new java.awt.Dimension(70, 17));
        jPanel2.add(jLabel4, java.awt.BorderLayout.CENTER);
        jLabel4.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        jPanel1.add(jPanel2, java.awt.BorderLayout.WEST);

        jPanel3.setBackground(PalleteColors.DROPDOWN_PRESSED);
        jPanel3.setPreferredSize(new java.awt.Dimension(140, 120));
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
        jPanel4.setLayout(new java.awt.GridLayout(1, 2));

        jPanel7.setBackground(PalleteColors.DROPDOWN_PRESSED);
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Category Name");
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
        jLabel6.setText("Book Count");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel6, java.awt.BorderLayout.CENTER);
        jLabel6.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        jPanel4.add(jPanel8);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        categoryMainPanel.add(jPanel1, java.awt.BorderLayout.NORTH);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setLayout(null);

        categoryList.setBackground(new java.awt.Color(255, 255, 255));
        categoryList.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(910, 65));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setForeground(new java.awt.Color(255, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(101, 101));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(65, 78, 101));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("000");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel8, java.awt.BorderLayout.CENTER);
        jLabel8.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.BLACK));

        jPanel9.add(jPanel10, java.awt.BorderLayout.WEST);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new java.awt.GridLayout(1, 2));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(65, 78, 101));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Category Name");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel12.add(jLabel9, java.awt.BorderLayout.CENTER);
        jLabel9.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.BLACK));

        jPanel11.add(jPanel12);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setForeground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(65, 78, 101));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("10");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel13.add(jLabel11, java.awt.BorderLayout.CENTER);
        jLabel11.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.BLACK));

        jPanel11.add(jPanel13);

        jPanel9.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setForeground(new java.awt.Color(255, 255, 255));
        jPanel14.setPreferredSize(new java.awt.Dimension(120, 120));

        jButton1.setBackground(new java.awt.Color(65, 78, 101));
        jButton1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Edit");
        jPanel14.add(jButton1);

        jButton2.setBackground(new java.awt.Color(65, 78, 101));
        jButton2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Remove");
        jPanel14.add(jButton2);

        jPanel9.add(jPanel14, java.awt.BorderLayout.EAST);
        jPanel14.setBorder(new LineBorderSide(Side.SOUTH,2,Color.BLACK));

        categoryList.add(jPanel9);

        jPanel15.add(categoryList);
        categoryList.setBounds(0, 0, 910, 360);

        jScrollPane1.setViewportView(jPanel15);

        categoryMainPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(categoryMainPanel);
        categoryMainPanel.setBounds(30, 150, 910, 390);
        categoryMainPanel.setBorder(new RoundedBorder(8,0));
    }// </editor-fold>//GEN-END:initComponents

    private void searchCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchCategoryActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        refreshItems();
        resize();
    }//GEN-LAST:event_formComponentShown

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
//	resize();	
    }//GEN-LAST:event_formComponentResized

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        refreshItems();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void userTypeSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTypeSearchActionPerformed
        search();
    }//GEN-LAST:event_userTypeSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel categoryList;
    private javax.swing.JPanel categoryMainPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
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
    private javax.swing.JTextField searchCategory;
    private javax.swing.JPanel topPanel;
    private javax.swing.JComboBox<String> userTypeSearch;
    // End of variables declaration//GEN-END:variables
}
