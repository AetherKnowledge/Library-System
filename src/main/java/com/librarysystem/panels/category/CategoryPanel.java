
package com.librarysystem.panels.category;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.librarysystem.Frame;
import com.librarysystem.handlers.CategoryHandler;
import com.librarysystem.objects.Category;
import com.librarysystem.objects.components.LineBorderSide;
import com.librarysystem.objects.components.LineBorderSide.Side;
import com.librarysystem.objects.components.RoundedBorder;

public class CategoryPanel extends javax.swing.JPanel {
    
    private final Category category;
    
    public CategoryPanel(Category category) {
        initComponents();
        this.category = category;
        categoryID.setText(category.getCategoryID());
        categoryName.setText(category.getCategoryName());
        booksTotal.setText(Integer.toString(category.getBooksInTotal()));
    }
    
    public String getCategoryName(){
        return category.getCategoryName();
    }
    
    public String getCategoryID(){
        return category.getCategoryID();
    }
    
    public Category getCategory(){
        return category;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        categoryID = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        categoryName = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        booksTotal = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(895, 65));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(65, 78, 101));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(101, 101));
        jPanel1.setLayout(new java.awt.BorderLayout());

        categoryID.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        categoryID.setForeground(new java.awt.Color(255, 255, 255));
        categoryID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        categoryID.setText("000");
        categoryID.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(categoryID, java.awt.BorderLayout.CENTER);
        categoryID.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setBackground(new java.awt.Color(65, 78, 101));
        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        jPanel4.setBackground(new java.awt.Color(65, 78, 101));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        categoryName.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        categoryName.setForeground(new java.awt.Color(255, 255, 255));
        categoryName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        categoryName.setText("Category Name");
        categoryName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(categoryName, java.awt.BorderLayout.CENTER);
        categoryName.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        jPanel2.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(65, 78, 101));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.BorderLayout());

        booksTotal.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        booksTotal.setForeground(new java.awt.Color(255, 255, 255));
        booksTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        booksTotal.setText("10");
        booksTotal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel5.add(booksTotal, java.awt.BorderLayout.CENTER);
        booksTotal.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        jPanel2.add(jPanel5);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(65, 78, 101));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(120, 120));

        jButton1.setBackground(new java.awt.Color(95, 192, 170));
        jButton1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Edit");
        jButton1.setPreferredSize(new java.awt.Dimension(80, 22));
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanel3.add(jButton1);
        jButton1.setBorder(new RoundedBorder(8,0));

        jButton2.setBackground(new java.awt.Color(171, 37, 51));
        jButton2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Remove");
        jButton2.setPreferredSize(new java.awt.Dimension(80, 22));
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jPanel3.add(jButton2);
        jButton2.setBorder(new RoundedBorder(8,0));

        add(jPanel3, java.awt.BorderLayout.EAST);
        jPanel3.setBorder(new LineBorderSide(Side.SOUTH,2,Color.WHITE));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        if (category.getBooksInTotal() > 0) {
            JOptionPane.showMessageDialog(null, "Cannot remove categories when it contains books please remove or move all books inside this category");
            return;
        }
        else if (category.getCategoryID().charAt(1) == '0' && category.getCategoryID().charAt(2) == '0') {
            JOptionPane.showMessageDialog(null, "Cannot remove main categories");
            return;
        }
        
        CategoryHandler.removeCategory(category);
        JPanel parent = (JPanel) getParent();
        parent.remove(this);
        parent.revalidate();
        parent.repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Frame.removePopup();
        Frame.makePopup(Frame.PopupType.CHANGECATEGORYNAME, category);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel booksTotal;
    private javax.swing.JLabel categoryID;
    private javax.swing.JLabel categoryName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
