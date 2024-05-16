
package com.librarysystem.panels.popups;

import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import com.librarysystem.Frame;
import com.librarysystem.handlers.CategoryHandler;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.objects.Category;
import com.librarysystem.objects.components.RoundedBorder;
import com.librarysystem.objects.ui.PalleteColors;

public class ChangeCategoryNamePopup extends JDialog {
    
    private Image categoryImg = Utilities.getImage("/textures/categories.png").getImage();
    private final Category category;

    public ChangeCategoryNamePopup(Category category) {
        initComponents();
        this.category = category;
        catgoryName.setText(category.getCategoryName());

        Utilities.addChangePointer(cancelButton);
        Utilities.addChangePointer(changeButton);
        
        jPanel10.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel11.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel12.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel13.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        catgoryName = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        changeButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(419, 175));

        jPanel9.setBackground(new java.awt.Color(224, 224, 224));
        jPanel9.setLayout(null);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(145, 145, 145));
        jLabel18.setText("icn");
        jPanel15.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, -1, -1));
        categoryImg = Utilities.changeImageColor(categoryImg, PalleteColors.DROPDOWN);
        categoryImg = categoryImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel18.setText("");
        jLabel18.setIcon(new ImageIcon(categoryImg));

        catgoryName.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        catgoryName.setForeground(new java.awt.Color(145, 145, 145));
        catgoryName.setText("Category Name");
        catgoryName.setBorder(null);
        catgoryName.setMinimumSize(new java.awt.Dimension(64, 22));
        catgoryName.setName("bookName"); // NOI18N
        catgoryName.setPreferredSize(new java.awt.Dimension(380, 22));
        jPanel15.add(catgoryName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 250, -1));
        catgoryName.setBorder(BorderFactory.createEmptyBorder());

        catgoryName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (catgoryName.getText().equals("Category Name")) {
                    catgoryName.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (catgoryName.getText().isEmpty()) {
                    catgoryName.setText("Category Name");
                }
            }
        });

        jPanel9.add(jPanel15);
        jPanel15.setBounds(40, 70, 330, 40);
        jPanel15.setBorder(new RoundedBorder(8,1));

        jLabel20.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Category Name");
        jPanel9.add(jLabel20);
        jLabel20.setBounds(40, 50, 137, 15);

        title.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Change Category Name");
        title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(title);
        title.setBounds(0, 0, 410, 40);

        changeButton.setBackground(new java.awt.Color(65, 78, 101));
        changeButton.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        changeButton.setForeground(new java.awt.Color(255, 255, 255));
        changeButton.setText("Change");
        changeButton.setFocusable(false);
        changeButton.addActionListener(this::changeButtonActionPerformed);
        jPanel9.add(changeButton);
        changeButton.setBounds(10, 130, 90, 22);
        changeButton.setBorder(new RoundedBorder(8,0));

        cancelButton.setBackground(new java.awt.Color(65, 78, 101));
        cancelButton.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setText("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        jPanel9.add(cancelButton);
        cancelButton.setBounds(310, 130, 90, 22);
        cancelButton.setBorder(new RoundedBorder(8,0));

        getContentPane().add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel10.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
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
            .addGap(0, 419, Short.MAX_VALUE)
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
            .addGap(0, 165, Short.MAX_VALUE)
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
            .addGap(0, 165, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel13, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void changeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeButtonActionPerformed
        this.setAlwaysOnTop(false);
        CategoryHandler.updateCategory(category);
        Frame.removePopup();
        this.dispose();
    }//GEN-LAST:event_changeButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        Frame.removePopup();
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField catgoryName;
    private javax.swing.JButton changeButton;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
