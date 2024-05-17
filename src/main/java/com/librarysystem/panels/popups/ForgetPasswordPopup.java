
package com.librarysystem.panels.popups;

import com.librarysystem.Frame;
import com.librarysystem.handlers.EmailHandler;
import com.librarysystem.handlers.UserHandler;
import com.librarysystem.handlers.Utilities;
import javax.swing.JDialog;
import com.librarysystem.objects.User;
import com.librarysystem.objects.components.RoundedBorder;
import com.librarysystem.objects.ui.PalleteColors;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ForgetPasswordPopup extends JDialog{
    private Image backImg = Utilities.getImage("/textures/back.png").getImage();
    private Image emailIcon = Utilities.getImage("/textures/emailIcon.png").getImage();
    private Image passwordIcon = Utilities.getImage("/textures/passwordIcon.png").getImage();
    private Image showPasswordIcon = Utilities.getImage("/textures/showPassword.png").getImage();
    private Image hidePasswordIcon = Utilities.getImage("/textures/hidePassword.png").getImage();
    private Image keyIcon = Utilities.getImage("/textures/key.png").getImage();
    private final CardLayout layout;
    private String email;
    private String key;

    public ForgetPasswordPopup() {
        showPasswordIcon = showPasswordIcon.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
        showPasswordIcon = new ImageIcon(showPasswordIcon).getImage();
        hidePasswordIcon = hidePasswordIcon.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
        hidePasswordIcon = new ImageIcon(hidePasswordIcon).getImage();
        initComponents();
        layout = (CardLayout) cardPanel.getLayout();
        layout.show(cardPanel, "EMAIL");
        fixShowPassword();
        
        changeButton(backButton);
        jPanel10.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel11.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel12.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel13.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
    }
    
    private void fixShowPassword(){
        togglePassVisible.setBackground(PalleteColors.TRANSPARENT);
        toggleRePassVisible.setBackground(PalleteColors.TRANSPARENT);
        togglePassVisible.setText("");
        toggleRePassVisible.setText("");
        
        togglePassVisible.addActionListener((ActionEvent e) -> {
            if (!togglePassVisible.isSelected() && !String.valueOf(passwordTextField.getPassword()).equals("Password")) {
                passwordTextField.setEchoChar('\u2022');
            }
            else {
                passwordTextField.setEchoChar((char) 0);
            }
        });
        
        toggleRePassVisible.addActionListener((ActionEvent e) -> {
            if (!toggleRePassVisible.isSelected() && !String.valueOf(rePasswordTextField.getPassword()).equals("Password")) {
                rePasswordTextField.setEchoChar('\u2022');
            }
            else {
                rePasswordTextField.setEchoChar((char) 0);
            }
        });
        
        passwordTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Arrays.toString(passwordTextField.getPassword()).equals("[P, a, s, s, w, o, r, d]")) {
                    passwordTextField.setText("");
                    passwordTextField.setEchoChar((char) 0);
                }
                passwordTextField.setEchoChar('\u2022');
                if (togglePassVisible.isSelected()) passwordTextField.setEchoChar((char) 0);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordTextField.getPassword().length == 0) {
                    passwordTextField.setText("Password");
                    passwordTextField.setEchoChar((char) 0);
                }
                else passwordTextField.setEchoChar('\u2022');
                if (togglePassVisible.isSelected() || String.valueOf(passwordTextField.getPassword()).equals("Password")) passwordTextField.setEchoChar((char) 0);
            }
        });
        
        rePasswordTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Arrays.toString(rePasswordTextField.getPassword()).equals("[P, a, s, s, w, o, r, d]")) {
                    rePasswordTextField.setText("");
                    rePasswordTextField.setEchoChar((char) 0);
                }
                rePasswordTextField.setEchoChar('\u2022');
                if (toggleRePassVisible.isSelected()) rePasswordTextField.setEchoChar((char) 0);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (rePasswordTextField.getPassword().length == 0) {
                    rePasswordTextField.setText("Password");
                    rePasswordTextField.setEchoChar((char) 0);
                }
                else rePasswordTextField.setEchoChar('\u2022');
                if (toggleRePassVisible.isSelected() || String.valueOf(rePasswordTextField.getPassword()).equals("Password")) rePasswordTextField.setEchoChar((char) 0);
            }
        });
        
        togglePassVisible.repaint();
        togglePassVisible.revalidate();
        
        toggleRePassVisible.repaint();
        toggleRePassVisible.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        cardPanel = new javax.swing.JPanel();
        emailPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        emailConfirm = new javax.swing.JButton(){
            @Override
            public void paint(Graphics g){
                Utilities.changeButtonColor(this, g);
            }
        };
        keyPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        keyTextField = new javax.swing.JTextField();
        resendCode = new javax.swing.JButton(){
            @Override
            public void paint(Graphics g){
                Utilities.changeButtonColor(this, g);
            }
        };
        keyConfirm = new javax.swing.JButton(){
            @Override
            public void paint(Graphics g){
                Utilities.changeButtonColor(this, g);
            }
        };
        passwordPanel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        togglePassVisible = new javax.swing.JToggleButton(){
            @Override
            public void paint(Graphics g){
                super.paint(g);
                Graphics2D g2D = (Graphics2D) g;

                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isSelected()) g2D.drawImage(showPasswordIcon, 0,0, null);
                else g2D.drawImage(hidePasswordIcon, 0,0, null);
            }
        };
        toggleRePassVisible = new javax.swing.JToggleButton(){
            @Override
            public void paint(Graphics g){
                super.paint(g);
                Graphics2D g2D = (Graphics2D) g;

                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isSelected()) g2D.drawImage(showPasswordIcon, 0,0, null);
                else g2D.drawImage(hidePasswordIcon, 0,0, null);
            }
        };
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        rePasswordTextField = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        changePass = new javax.swing.JButton(){
            @Override
            public void paint(Graphics g){
                Utilities.changeButtonColor(this, g);
            }
        };

        setBackground(new java.awt.Color(224, 224, 224));
        setPreferredSize(new java.awt.Dimension(399, 285));

        jPanel10.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
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
            .addGap(0, 399, Short.MAX_VALUE)
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
            .addGap(0, 275, Short.MAX_VALUE)
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
            .addGap(0, 275, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel13, java.awt.BorderLayout.WEST);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel1.setLayout(null);

        backButton.setText("jButton1");
        backButton.setPreferredSize(new java.awt.Dimension(30, 23));
        jPanel1.add(backButton);
        backButton.setBounds(360, 0, 30, 23);

        title.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        title.setForeground(new java.awt.Color(65, 78, 101));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Forgot Password");
        title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(title);
        title.setBounds(0, 0, 390, 50);

        jPanel9.add(jPanel1, java.awt.BorderLayout.NORTH);

        cardPanel.setBackground(new java.awt.Color(255, 255, 255));
        cardPanel.setLayout(new java.awt.CardLayout());

        emailPanel.setBackground(new java.awt.Color(255, 255, 255));
        emailPanel.setLayout(null);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(145, 145, 145));
        jLabel3.setText("icn");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 0, 30, 40));
        emailIcon = Utilities.changeImageColor(emailIcon, PalleteColors.DROPDOWN);
        emailIcon = emailIcon.getScaledInstance(25, 15, Image.SCALE_SMOOTH);
        jLabel3.setText("");
        jLabel3.setIcon(new ImageIcon(emailIcon));

        emailTextField.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        emailTextField.setForeground(new java.awt.Color(145, 145, 145));
        emailTextField.setText("Email");
        emailTextField.setBorder(null);
        emailTextField.setName("bookName"); // NOI18N
        emailTextField.setPreferredSize(new java.awt.Dimension(64, 22));
        jPanel5.add(emailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 280, -1));
        emailTextField.setBorder(BorderFactory.createEmptyBorder());

        emailTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailTextField.getText().equals("Email")) {
                    emailTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailTextField.getText().isEmpty()) {
                    emailTextField.setText("Email");
                }
            }
        });

        emailPanel.add(jPanel5);
        jPanel5.setBounds(30, 80, 330, 40);
        jPanel5.setBorder(new RoundedBorder(8,1));

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(65, 78, 101));
        jLabel10.setText("Email");
        emailPanel.add(jLabel10);
        jLabel10.setBounds(30, 60, 290, 20);

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(65, 78, 101));
        jLabel1.setText("you the key to reset your password");
        emailPanel.add(jLabel1);
        jLabel1.setBounds(30, 30, 330, 16);

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(65, 78, 101));
        jLabel2.setText("Enter the email associated with your account and we'll send");
        emailPanel.add(jLabel2);
        jLabel2.setBounds(30, 10, 330, 16);

        emailConfirm.setBackground(new java.awt.Color(95, 192, 170));
        emailConfirm.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        emailConfirm.setForeground(new java.awt.Color(255, 255, 255));
        emailConfirm.setText("Confirm");
        emailConfirm.setPreferredSize(new java.awt.Dimension(100, 18));
        emailConfirm.addActionListener(this::emailConfirmActionPerformed);
        emailPanel.add(emailConfirm);
        emailConfirm.setBounds(240, 180, 120, 30);
        changePass.setBackground(PalleteColors.BUTTON_PRESSED_TEXT);

        cardPanel.add(emailPanel, "EMAIL");

        keyPanel.setBackground(new java.awt.Color(255, 255, 255));
        keyPanel.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(65, 78, 101));
        jLabel7.setText("Reset Key");
        keyPanel.add(jLabel7);
        jLabel7.setBounds(30, 20, 290, 20);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(145, 145, 145));
        jLabel4.setText("icn");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 0, 30, 40));
        keyIcon = Utilities.changeImageColor(keyIcon, PalleteColors.DROPDOWN);
        keyIcon = keyIcon.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel4.setText("");
        jLabel4.setIcon(new ImageIcon(keyIcon));

        keyTextField.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        keyTextField.setForeground(new java.awt.Color(145, 145, 145));
        keyTextField.setText("Reset Key");
        keyTextField.setBorder(null);
        keyTextField.setName("bookName"); // NOI18N
        keyTextField.setPreferredSize(new java.awt.Dimension(64, 22));
        jPanel6.add(keyTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 170, -1));
        keyTextField.setBorder(BorderFactory.createEmptyBorder());

        keyTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (keyTextField.getText().equals("Reset Key")) {
                    keyTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (keyTextField.getText().isEmpty()) {
                    keyTextField.setText("Reset Key");
                }
            }
        });

        resendCode.setBackground(new java.awt.Color(65, 78, 101));
        resendCode.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        resendCode.setForeground(new java.awt.Color(255, 255, 255));
        resendCode.setText("Resend Code");
        resendCode.setPreferredSize(new java.awt.Dimension(100, 18));
        resendCode.addActionListener(this::resendCodeActionPerformed);
        jPanel6.add(resendCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 100, 20));
        changePass.setBackground(PalleteColors.BUTTON_PRESSED_TEXT);

        keyPanel.add(jPanel6);
        jPanel6.setBounds(30, 40, 330, 40);
        jPanel6.setBorder(new RoundedBorder(8,1));

        keyConfirm.setBackground(new java.awt.Color(95, 192, 170));
        keyConfirm.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        keyConfirm.setForeground(new java.awt.Color(255, 255, 255));
        keyConfirm.setText("Confirm");
        keyConfirm.setPreferredSize(new java.awt.Dimension(100, 18));
        keyConfirm.addActionListener(this::keyConfirmActionPerformed);
        keyPanel.add(keyConfirm);
        keyConfirm.setBounds(240, 180, 120, 30);
        changePass.setBackground(PalleteColors.BUTTON_PRESSED_TEXT);

        cardPanel.add(keyPanel, "KEY");

        passwordPanel.setBackground(new java.awt.Color(255, 255, 255));
        passwordPanel.setLayout(null);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(145, 145, 145));
        jLabel5.setText("icn");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        passwordIcon = Utilities.changeImageColor(passwordIcon, PalleteColors.DROPDOWN);
        passwordIcon = passwordIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        jLabel5.setText("");
        jLabel5.setIcon(new ImageIcon(passwordIcon));

        passwordTextField.setBackground(new java.awt.Color(255, 255, 255));
        passwordTextField.setForeground(new java.awt.Color(145, 145, 145));
        passwordTextField.setText("Password");
        jPanel7.add(passwordTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 12, 260, -1));
        passwordTextField.setBorder(BorderFactory.createEmptyBorder());

        passwordTextField.setEchoChar((char) 0);
        passwordTextField.setFont(new Font("Roboto", 0, 13));
        passwordTextField.setText("Password");
        passwordTextField.setBorder(BorderFactory.createEmptyBorder());

        passwordPanel.add(jPanel7);
        jPanel7.setBounds(20, 40, 310, 40);
        jPanel7.setBorder(new RoundedBorder(8,1));

        jLabel6.setBackground(new java.awt.Color(65, 78, 101));
        jLabel6.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(65, 78, 101));
        jLabel6.setText("New Password");
        passwordPanel.add(jLabel6);
        jLabel6.setBounds(20, 20, 140, 15);

        togglePassVisible.setText("jToggleButton1");
        togglePassVisible.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        togglePassVisible.setContentAreaFilled(false);
        togglePassVisible.setFocusPainted(false);
        togglePassVisible.setFocusable(false);
        togglePassVisible.setPreferredSize(new java.awt.Dimension(23, 23));
        passwordPanel.add(togglePassVisible);
        togglePassVisible.setBounds(340, 50, 23, 23);

        toggleRePassVisible.setText("jToggleButton1");
        toggleRePassVisible.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        toggleRePassVisible.setContentAreaFilled(false);
        toggleRePassVisible.setFocusPainted(false);
        toggleRePassVisible.setFocusable(false);
        toggleRePassVisible.setPreferredSize(new java.awt.Dimension(23, 23));
        passwordPanel.add(toggleRePassVisible);
        toggleRePassVisible.setBounds(340, 120, 23, 23);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(145, 145, 145));
        jLabel8.setText("icn");
        jPanel8.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        passwordIcon = Utilities.changeImageColor(passwordIcon, PalleteColors.DROPDOWN);
        passwordIcon = passwordIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        jLabel8.setText("");
        jLabel8.setIcon(new ImageIcon(passwordIcon));

        rePasswordTextField.setBackground(new java.awt.Color(255, 255, 255));
        rePasswordTextField.setForeground(new java.awt.Color(145, 145, 145));
        rePasswordTextField.setText("Password");
        jPanel8.add(rePasswordTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 12, 260, -1));
        rePasswordTextField.setBorder(BorderFactory.createEmptyBorder());

        rePasswordTextField.setEchoChar((char) 0);
        rePasswordTextField.setFont(new Font("Roboto", 0, 13));
        rePasswordTextField.setText("Password");
        rePasswordTextField.setBorder(BorderFactory.createEmptyBorder());

        passwordPanel.add(jPanel8);
        jPanel8.setBounds(20, 110, 310, 40);
        jPanel8.setBorder(new RoundedBorder(8,1));

        jLabel9.setBackground(new java.awt.Color(65, 78, 101));
        jLabel9.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(65, 78, 101));
        jLabel9.setText("Re-Type New Password");
        passwordPanel.add(jLabel9);
        jLabel9.setBounds(20, 90, 270, 15);

        changePass.setBackground(new java.awt.Color(95, 192, 170));
        changePass.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        changePass.setForeground(new java.awt.Color(255, 255, 255));
        changePass.setText("Change Password");
        changePass.setPreferredSize(new java.awt.Dimension(100, 18));
        changePass.addActionListener(this::changePassActionPerformed);
        passwordPanel.add(changePass);
        changePass.setBounds(240, 180, 120, 30);
        changePass.setBackground(PalleteColors.BUTTON_PRESSED_TEXT);

        cardPanel.add(passwordPanel, "PASSWORD");

        jPanel9.add(cardPanel, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel9, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void emailConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailConfirmActionPerformed
        if (UserHandler.searchUser(emailTextField.getText()) != null) {
            Random rand = new Random();
            int num = rand.nextInt(100000, 999998) + 1;
            key = Integer.toString(num);
            email = emailTextField.getText();
            EmailHandler.sendEmail(email, key);
            layout.show(cardPanel, "KEY");
        }
        else {
            this.setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(new JFrame(), "Email does not exist.");
            this.setAlwaysOnTop(true);
        }
        emailTextField.setText("");
    }//GEN-LAST:event_emailConfirmActionPerformed

    private void resendCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resendCodeActionPerformed
        Random rand = new Random();
        int num = rand.nextInt(100000, 999998) + 1;
        key = Integer.toString(num);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        EmailHandler.sendEmail(email, key);
        this.setCursor(Cursor.getDefaultCursor());
        this.setAlwaysOnTop(false);
        JOptionPane.showMessageDialog(new JFrame(), "Key resent.");
        this.setAlwaysOnTop(true);
    }//GEN-LAST:event_resendCodeActionPerformed

    private void keyConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyConfirmActionPerformed
        this.setAlwaysOnTop(false);
        if (keyTextField.getText().equals(key)) {
            layout.show(cardPanel, "PASSWORD");
        }
        else JOptionPane.showMessageDialog(new JFrame(), "Invalid Key");
        this.setAlwaysOnTop(true);
        keyTextField.setText("");
    }//GEN-LAST:event_keyConfirmActionPerformed

    private void changePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePassActionPerformed
        String password = String.valueOf(passwordTextField.getPassword());
        String rePassword = String.valueOf(rePasswordTextField.getPassword());
        this.setAlwaysOnTop(false);

        if (password.isEmpty() || password.equals("Password")) {
            JOptionPane.showMessageDialog(new JFrame(), "Password Field Empty.");
            this.setAlwaysOnTop(true);
            return;
        }
        else if (password.length() < 8) {
            JOptionPane.showMessageDialog(new JFrame(), "Password must be greater than 8 characters.");
            this.setAlwaysOnTop(true);
            return;
        }
        else if (!Utilities.containsSpecial(password)) {
            JOptionPane.showMessageDialog(new JFrame(), "Password must contain special character.");
            this.setAlwaysOnTop(true);
            return;
        }
        else if (!password.equals(rePassword)) {
            JOptionPane.showMessageDialog(new JFrame(), "Password not the same.");
            this.setAlwaysOnTop(true);
            return;
        }
        
        password = Utilities.toBcrypt(passwordTextField.getPassword());
        User user = UserHandler.searchUser(email);
        user.setPassword(password);
        
        UserHandler.updateUser(user, email);
        
        Frame.removePopup();
        this.dispose();
    }//GEN-LAST:event_changePassActionPerformed

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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JButton changePass;
    private javax.swing.JButton emailConfirm;
    private javax.swing.JPanel emailPanel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton keyConfirm;
    private javax.swing.JPanel keyPanel;
    private javax.swing.JTextField keyTextField;
    private javax.swing.JPanel passwordPanel;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JPasswordField rePasswordTextField;
    private javax.swing.JButton resendCode;
    private javax.swing.JLabel title;
    private javax.swing.JToggleButton togglePassVisible;
    private javax.swing.JToggleButton toggleRePassVisible;
    // End of variables declaration//GEN-END:variables


}


