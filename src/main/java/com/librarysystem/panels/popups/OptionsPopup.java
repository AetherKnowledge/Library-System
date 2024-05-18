
package com.librarysystem.panels.popups;

import com.librarysystem.Frame;
import com.librarysystem.handlers.UserHandler;
import com.librarysystem.handlers.Utilities;
import javax.swing.JDialog;
import com.librarysystem.objects.ui.PalleteColors;
import com.librarysystem.panels.options.ProfileOptions;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OptionsPopup extends JDialog {
    
    private final ProfileOptions profileOptions = new ProfileOptions(UserHandler.getCurrentUser());
    private Image backImg = Utilities.getImage("/textures/back.png").getImage();
    private final CardLayout layout;

    public OptionsPopup(OptionsPanels panel) {
        initComponents();
        layout = (CardLayout) cardPanel.getLayout();
        cardPanel.add(profileOptions, OptionsPanels.PROFILE.name());
       
        profileBtn.setSelected(true);
        
        jPanel10.setBackground(PalleteColors.DROPDOWN_PRESSED);
        jPanel11.setBackground(PalleteColors.DROPDOWN_PRESSED);
        jPanel12.setBackground(PalleteColors.DROPDOWN_PRESSED);
        jPanel13.setBackground(PalleteColors.DROPDOWN_PRESSED);
        
        System.out.println(panel);
        layout.show(cardPanel, panel.name());
        
        switch(panel){
            case ACCOUNT -> {
                profileBtn.setEnabled(true);
                profileBtn.setSelected(false);
                settingsBtn.setEnabled(false);
                settingsBtn.setSelected(true);
            }
            case PROFILE -> {
                profileBtn.setEnabled(false);
                profileBtn.setSelected(true);
                settingsBtn.setEnabled(true);
                settingsBtn.setSelected(false);
            }
        }
        
        changeButton(backButton);
    }
    
    public enum OptionsPanels{
        ACCOUNT,PROFILE;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        profileBtn = new javax.swing.JToggleButton(){
            @Override
            public void paint(Graphics g){
                super.paint(g);
                Graphics2D g2D = (Graphics2D) g;

                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                if (getModel().isSelected()) {
                    setBackground(g2D,PalleteColors.DROPDOWN);
                    setText(g2D, PalleteColors.BUTTON_PRESSED_TEXT);
                }
                else if (getModel().isRollover()) {
                    setBackground(g2D,PalleteColors.SIDEBAR_MAIN_COLOR);
                    setText(g2D, Color.WHITE);
                }
                else {
                    setBackground(g2D,PalleteColors.SIDEBAR_MAIN_COLOR);
                    setText(g2D, Color.WHITE);
                }
            }

            private Graphics2D setBackground(Graphics2D g2D, Color color){
                g2D.setColor(color);
                g2D.fillRect(0, 0, getWidth(), getHeight());
                return g2D;
            }

            private Graphics2D setText(Graphics2D g2D, Color color){
                g2D.setColor(color);
                FontMetrics metrics = g2D.getFontMetrics();
                int y = (getHeight() - metrics.getHeight())/2 + metrics.getAscent();
                int x = (getWidth() - metrics.stringWidth(getText()))/2;
                g2D.drawString(getText(), x, y);

                return g2D;
            }
        };
        settingsBtn = new javax.swing.JToggleButton(){

            @Override
            public void paint(Graphics g){
                super.paint(g);
                Graphics2D g2D = (Graphics2D) g;

                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                if (getModel().isSelected()) {
                    setBackground(g2D,PalleteColors.DROPDOWN);
                    setText(g2D, PalleteColors.BUTTON_PRESSED_TEXT);
                }
                else if (getModel().isRollover()) {
                    setBackground(g2D,PalleteColors.SIDEBAR_MAIN_COLOR);
                    setText(g2D, Color.WHITE);
                }
                else {
                    setBackground(g2D,PalleteColors.SIDEBAR_MAIN_COLOR);
                    setText(g2D, Color.WHITE);
                }
            }

            private Graphics2D setBackground(Graphics2D g2D, Color color){
                g2D.setColor(color);
                g2D.fillRect(0, 0, getWidth(), getHeight());
                return g2D;
            }

            private Graphics2D setText(Graphics2D g2D, Color color){
                g2D.setColor(color);
                FontMetrics metrics = g2D.getFontMetrics();
                int y = (getHeight() - metrics.getHeight())/2 + metrics.getAscent();
                int x = (getWidth() - metrics.stringWidth(getText()))/2;
                g2D.drawString(getText(), x, y);

                return g2D;
            }
        };
        cardPanel = new javax.swing.JPanel();
        accountPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(840, 620));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel1.setPreferredSize(new java.awt.Dimension(140, 140));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        profileBtn.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        profileBtn.setText("Profile");
        profileBtn.setPreferredSize(new java.awt.Dimension(140, 50));
        profileBtn.addActionListener(this::profileBtnActionPerformed);
        jPanel1.add(profileBtn);

        settingsBtn.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        settingsBtn.setText("Password");
        settingsBtn.setPreferredSize(new java.awt.Dimension(140, 50));
        settingsBtn.addActionListener(this::settingsBtnActionPerformed);
        jPanel1.add(settingsBtn);

        jPanel9.add(jPanel1, java.awt.BorderLayout.WEST);
        jPanel1.setVisible(false);

        cardPanel.setBackground(new java.awt.Color(255, 255, 255));
        cardPanel.setLayout(new java.awt.CardLayout());

        accountPanel.setBackground(new java.awt.Color(255, 255, 255));
        accountPanel.setLayout(null);

        jLabel1.setText("ALA PA LMAO");
        accountPanel.add(jLabel1);
        jLabel1.setBounds(320, 190, 300, 16);

        backButton.setText("jButton1");
        backButton.setPreferredSize(new java.awt.Dimension(30, 23));
        accountPanel.add(backButton);
        backButton.setBounds(660, 0, 30, 23);

        cardPanel.add(accountPanel, "ACCOUNT");

        jPanel9.add(cardPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel10.setBackground(new java.awt.Color(51, 102, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 840, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel10, java.awt.BorderLayout.NORTH);

        jPanel11.setBackground(new java.awt.Color(51, 102, 255));
        jPanel11.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 840, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel11, java.awt.BorderLayout.SOUTH);

        jPanel12.setBackground(new java.awt.Color(51, 102, 255));
        jPanel12.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel12, java.awt.BorderLayout.EAST);

        jPanel13.setBackground(new java.awt.Color(51, 102, 255));
        jPanel13.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel13, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void settingsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsBtnActionPerformed
        profileBtn.setSelected(false);
        profileBtn.setEnabled(true);
        settingsBtn.setEnabled(false);
        layout.show(cardPanel, OptionsPanels.ACCOUNT.name());
    }//GEN-LAST:event_settingsBtnActionPerformed

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
        settingsBtn.setSelected(false);
        settingsBtn.setEnabled(true);
        profileBtn.setEnabled(false);
        layout.show(cardPanel, OptionsPanels.PROFILE.name());
    }//GEN-LAST:event_profileBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel accountPanel;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JToggleButton profileBtn;
    private javax.swing.JToggleButton settingsBtn;
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
        });
        Utilities.addChangePointer(btn);
        parent.add(btn);
    }

}
