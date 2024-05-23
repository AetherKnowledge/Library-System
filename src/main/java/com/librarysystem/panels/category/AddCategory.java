
package com.librarysystem.panels.category;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import com.librarysystem.objects.Category;
import com.librarysystem.objects.ui.PalleteColors;
import com.librarysystem.objects.components.RoundedBorder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import com.librarysystem.handlers.CategoryHandler;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.panels.MyPanel;
import java.awt.Graphics;

public class AddCategory extends MyPanel {
    
    private Image categoryImg = Utilities.getImage("/textures/categories.png").getImage();

    public AddCategory() {
        initComponents();
 
    }
    
    @Override
    public void refreshItems(){
        categorySelector.removeAllItems();
        ArrayList<Category> categories = CategoryHandler.getCategoryList();
        
        for (Category category : categories) {
            categorySelector.addItem(category.getCategoryID() + " - " + category.getCategoryName());
        }
        
        categorySelector.revalidate();
        categorySelector.repaint();
        
        refreshCategoryNumSelector();
        Utilities.addChangePointer(addCategory);
    }
    
    public void refreshCategoryNumSelector(){
        
        SpinnerListModel model = new SpinnerListModel();
        ArrayList<String> list = new ArrayList<>();
        
        String categoryIDSelected = (String) categorySelector.getSelectedItem();
        
        if (categoryIDSelected != null) {
            String[] split = categoryIDSelected.split(" - ");
            categoryIDSelected = split[0];
        }
        else categoryIDSelected = "000";
        
        for (int i = 1; i < 10; i++) list.add(Integer.toString(i));
        for (Category category : CategoryHandler.getCategoryList()) {
            if (category.getParentCategoryID().equals(categoryIDSelected)) {
                list.remove(category.getCategory());
            }
        }
        
        if (list.isEmpty()) {
            list.add("None Available");
        }
        
        model.setList(list.reversed());
        model.setValue(list.get(0));
        categoryNumSelector.setModel(model);
        
        JFormattedTextField textField = ((JSpinner.DefaultEditor) categoryNumSelector.getEditor()).getTextField();
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        textField.setForeground(new Color(145,145,145));
        textField.setFont(new Font("Roboto",Font.PLAIN,12));
        
        System.out.println(categoryIDSelected);
        
    }
    
    public void addCategory(){
        if (categorySelector.getSelectedItem() == "" || categorySelector.getSelectedItem() == null || categorySelector.getSelectedItem() == "None" ) {
            JOptionPane.showMessageDialog(new JFrame(), "Parent Category is Empty");
            return;
        }
        if (categoryName.getText().equals("") || categoryName.getText().equals("Name of Category")) {
            JOptionPane.showMessageDialog(new JFrame(), "Category Name is Empty");
            return;
        }
        if (categoryNumSelector.getValue() == null || categoryNumSelector.getValue() == "None") {
            JOptionPane.showMessageDialog(new JFrame(), "No Category Number Available for Parent Category");
            return;
        }
        
        String parentCategory = "";
        String parentCategoryID = "";
        
        String categoryIDSelected = (String) categorySelector.getSelectedItem();
        String[] split = categoryIDSelected.split(" - ");
        categoryIDSelected = split[0];
        
        String categoryNum = (String)categoryNumSelector.getValue();
        
        ArrayList<Category> categories = CategoryHandler.getCategoryList();
        for (Category parent : categories) {
            if (parent.getCategoryID().equals(categoryIDSelected)) {
                if (parent.getParentCategory().equals("None")) {
                    parentCategory = parent.getCategory();
                }
                else parentCategory = parent.getParentCategory()+ parent.getCategory();
                
                parentCategoryID = parent.getCategoryID();
                break;
            }
        }
        
        Category category = new Category(parentCategory,parentCategoryID,categoryNum,categoryName.getText(),Timestamp.valueOf(LocalDateTime.now()));
        CategoryHandler.addCategory(category);
        System.out.println(category);
        
        refreshItems();
    }
    
    @Override
    public void resize() {
        topPanel.setSize(getWidth() - 70, 50);
        mainPanel.setSize(getWidth() - 70, 180);
        jPanel6.setLocation(mainPanel.getWidth() - 210, jPanel6.getY());
        addCategory.setLocation(mainPanel.getWidth() - 190, addCategory.getY());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        topPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        categoryName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        addCategory = new javax.swing.JButton(){
            @Override
            public void paint(Graphics g){
                Utilities.changeButtonColor(this, g);
            }
        };
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        categorySelector = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        categoryNumSelector = new javax.swing.JSpinner();

        jButton3.setText("jButton3");

        setBackground(new java.awt.Color(224, 224, 224));
        setForeground(new java.awt.Color(145, 145, 145));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        setLayout(null);

        topPanel.setBackground(new java.awt.Color(255, 255, 255));
        topPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Create Category");
        topPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, -1, 20));

        add(topPanel);
        topPanel.setBounds(30, 90, 910, 50);
        topPanel.setBorder(new RoundedBorder(8,0));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(145, 145, 145));
        jLabel10.setText("Categories / add_category");
        add(jLabel10);
        jLabel10.setBounds(30, 50, 142, 15);

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Add New Category");
        add(jLabel13);
        jLabel13.setBounds(30, 10, 203, 29);
        jLabel13.setForeground(PalleteColors.SIDEBAR_MAIN_COLOR);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(145, 145, 145));
        jLabel3.setText("icn");
        jLabel3.setPreferredSize(new java.awt.Dimension(15, 15));
        jPanel4.add(jLabel3);
        jLabel3.setBounds(10, 32, 15, 15);
        categoryImg = Utilities.changeImageColor(categoryImg, PalleteColors.DROPDOWN);
        categoryImg = categoryImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel3.setText("");
        jLabel3.setIcon(new ImageIcon(categoryImg));

        categoryName.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        categoryName.setForeground(new java.awt.Color(145, 145, 145));
        categoryName.setText("Name of Category");
        categoryName.setName("categoryName"); // NOI18N
        categoryName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryNameActionPerformed(evt);
            }
        });
        jPanel4.add(categoryName);
        categoryName.setBounds(40, 30, 380, 21);
        categoryName.setBorder(BorderFactory.createEmptyBorder());

        categoryName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (categoryName.getText().equals("Name of Category")) {
                    categoryName.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (categoryName.getText().isEmpty()) {
                    categoryName.setText("Name of Category");
                }
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Category Name");
        jPanel4.add(jLabel2);
        jLabel2.setBounds(10, 8, 90, 15);

        mainPanel.add(jPanel4);
        jPanel4.setBounds(20, 90, 440, 60);
        jPanel4.setBorder(new RoundedBorder(8,1));

        addCategory.setBackground(new java.awt.Color(65, 78, 101));
        addCategory.setForeground(new java.awt.Color(255, 255, 255));
        addCategory.setText("Create Category");
        addCategory.setBorder(null);
        addCategory.setFocusPainted(false);
        addCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCategoryActionPerformed(evt);
            }
        });
        mainPanel.add(addCategory);
        addCategory.setBounds(730, 110, 130, 40);
        addCategory.setBorder(new RoundedBorder(8,1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(145, 145, 145));
        jLabel4.setText("icn");
        jLabel4.setPreferredSize(new java.awt.Dimension(15, 15));
        jPanel5.add(jLabel4);
        jLabel4.setBounds(10, 32, 15, 15);
        categoryImg = Utilities.changeImageColor(categoryImg, PalleteColors.DROPDOWN);
        categoryImg = categoryImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel4.setText("");
        jLabel4.setIcon(new ImageIcon(categoryImg));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Parent Category");
        jPanel5.add(jLabel5);
        jLabel5.setBounds(10, 8, 90, 15);

        categorySelector.setBackground(new java.awt.Color(255, 255, 255));
        categorySelector.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        categorySelector.setForeground(new java.awt.Color(145, 145, 145));
        categorySelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", " " }));
        categorySelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorySelectorActionPerformed(evt);
            }
        });
        jPanel5.add(categorySelector);
        categorySelector.setBounds(40, 30, 380, 21);

        mainPanel.add(jPanel5);
        jPanel5.setBounds(20, 20, 440, 60);
        jPanel5.setBorder(new RoundedBorder(8,1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(145, 145, 145));
        jLabel6.setText("icn");
        jLabel6.setPreferredSize(new java.awt.Dimension(15, 15));
        jPanel6.add(jLabel6);
        jLabel6.setBounds(10, 32, 15, 15);
        categoryImg = Utilities.changeImageColor(categoryImg, PalleteColors.DROPDOWN);
        categoryImg = categoryImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel3.setText("");
        jLabel3.setIcon(new ImageIcon(categoryImg));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Category Number");
        jPanel6.add(jLabel7);
        jLabel7.setBounds(10, 8, 100, 15);

        categoryNumSelector.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        categoryNumSelector.setModel(new javax.swing.SpinnerNumberModel(1, null, 9, 1));
        jPanel6.add(categoryNumSelector);
        categoryNumSelector.setBounds(30, 30, 130, 21);

        mainPanel.add(jPanel6);
        jPanel6.setBounds(700, 30, 170, 60);
        jPanel6.setBorder(new RoundedBorder(8,1));

        add(mainPanel);
        mainPanel.setBounds(30, 150, 910, 180);
        mainPanel.setBorder(new RoundedBorder(8,0));
    }// </editor-fold>//GEN-END:initComponents

    private void categoryNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryNameActionPerformed

    private void addCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCategoryActionPerformed
        addCategory();
    }//GEN-LAST:event_addCategoryActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        refreshItems();
        resize();
    }//GEN-LAST:event_formComponentShown

    private void categorySelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorySelectorActionPerformed
        refreshCategoryNumSelector();
    }//GEN-LAST:event_categorySelectorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCategory;
    private javax.swing.JTextField categoryName;
    private javax.swing.JSpinner categoryNumSelector;
    private javax.swing.JComboBox<String> categorySelector;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables


}
