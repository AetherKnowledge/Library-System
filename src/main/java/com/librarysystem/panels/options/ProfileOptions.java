
package com.librarysystem.panels.options;

import com.librarysystem.Frame;
import com.librarysystem.handlers.UserHandler;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.objects.User;
import com.librarysystem.objects.components.RoundedBorder;
import com.librarysystem.objects.ui.PalleteColors;
import com.librarysystem.panels.account.Register;
import java.awt.Color;
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
import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.validator.routines.EmailValidator;

public class ProfileOptions extends javax.swing.JPanel {
    
    private User user;
    private Image userImg;
    private Image personIcon = Utilities.getImage("/textures/user.png").getImage();
    private Image showPasswordIcon = Utilities.getImage("/textures/showPassword.png").getImage();
    private Image hidePasswordIcon = Utilities.getImage("/textures/hidePassword.png").getImage();
    private Image emailIcon = Utilities.getImage("/textures/emailIcon.png").getImage();
    private Image passwordIcon = Utilities.getImage("/textures/passwordIcon.png").getImage();
    private Image backImg = Utilities.getImage("/textures/back.png").getImage();

    
    public ProfileOptions(User user) {
        showPasswordIcon = showPasswordIcon.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
        showPasswordIcon = new ImageIcon(showPasswordIcon).getImage();
        hidePasswordIcon = hidePasswordIcon.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
        hidePasswordIcon = new ImageIcon(hidePasswordIcon).getImage();
        this.user = user;
        initComponents();
        
        fullNameTextField.setText(user.getFullName());
        studNumTextField.setText(user.getStudentNumber());
        emailTextField.setText(user.getEmail());
        userImg = new ImageIcon(user.getIcon()).getImage();
        userImg = userImg.getScaledInstance(userImgLabel.getPreferredSize().width, userImgLabel.getPreferredSize().height, Image.SCALE_SMOOTH);
        userImgLabel.setIcon(new ImageIcon(userImg));
        
        changeButton(backButton);
        togglePassVisible.setText("");
        toggleRePassVisible.setText("");
        
        fixShowPassword();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        fullNameTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JPasswordField();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        rePasswordTextField = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        studNumTextField = new javax.swing.JTextField();
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
        jPanel11 = new javax.swing.JPanel();
        userImgLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        uploadImageButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        signInButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(830, 610));
        setLayout(null);

        jLabel1.setBackground(PalleteColors.DROPDOWN_PRESSED);
        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setText("Edit Profile");
        add(jLabel1);
        jLabel1.setBounds(30, 10, 210, 60);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(65, 78, 101));
        jLabel2.setText("Full Name");
        jPanel6.add(jLabel2);
        jLabel2.setBounds(20, 10, 260, 15);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(145, 145, 145));
        jLabel3.setText("icn");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        personIcon = Utilities.changeImageColor(personIcon, PalleteColors.DROPDOWN);
        personIcon = personIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        jLabel3.setText("");
        jLabel3.setIcon(new ImageIcon(personIcon));

        fullNameTextField.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        fullNameTextField.setForeground(new java.awt.Color(145, 145, 145));
        fullNameTextField.setText("Full Name");
        fullNameTextField.setName("fullNameTextField"); // NOI18N
        jPanel4.add(fullNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 380, -1));
        fullNameTextField.setBorder(BorderFactory.createEmptyBorder());

        fullNameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fullNameTextField.getText().equals("Full Name")) {
                    fullNameTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (fullNameTextField.getText().isEmpty()) {
                    fullNameTextField.setText("Full Name");
                }
            }
        });

        jPanel6.add(jPanel4);
        jPanel4.setBounds(20, 30, 430, 40);
        jPanel4.setBorder(new RoundedBorder(8,1));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(65, 78, 101));
        jLabel4.setText("Email");
        jPanel6.add(jLabel4);
        jLabel4.setBounds(20, 150, 290, 15);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(145, 145, 145));
        jLabel5.setText("icn");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 0, 30, 40));
        emailIcon = Utilities.changeImageColor(emailIcon, PalleteColors.DROPDOWN);
        emailIcon = emailIcon.getScaledInstance(25, 15, Image.SCALE_SMOOTH);
        jLabel5.setText("");
        jLabel5.setIcon(new ImageIcon(emailIcon));

        emailTextField.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        emailTextField.setForeground(new java.awt.Color(145, 145, 145));
        emailTextField.setText("Email");
        emailTextField.setBorder(null);
        emailTextField.setName("bookName"); // NOI18N
        emailTextField.setPreferredSize(new java.awt.Dimension(64, 22));
        jPanel5.add(emailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 380, -1));
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

        jPanel6.add(jPanel5);
        jPanel5.setBounds(20, 170, 430, 40);
        jPanel5.setBorder(new RoundedBorder(8,1));

        jLabel6.setBackground(new java.awt.Color(65, 78, 101));
        jLabel6.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(65, 78, 101));
        jLabel6.setText("Password");
        jPanel6.add(jLabel6);
        jLabel6.setBounds(20, 220, 270, 15);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(145, 145, 145));
        jLabel7.setText("icn");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        passwordIcon = Utilities.changeImageColor(passwordIcon, PalleteColors.DROPDOWN);
        passwordIcon = passwordIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        jLabel7.setText("");
        jLabel7.setIcon(new ImageIcon(passwordIcon));

        passwordTextField.setBackground(new java.awt.Color(255, 255, 255));
        passwordTextField.setForeground(new java.awt.Color(145, 145, 145));
        passwordTextField.setText("Password");
        jPanel7.add(passwordTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 12, 380, -1));
        passwordTextField.setBorder(BorderFactory.createEmptyBorder());

        passwordTextField.setEchoChar((char) 0);
        passwordTextField.setFont(new Font("Roboto", 0, 13));
        passwordTextField.setText("Password");
        passwordTextField.setBorder(BorderFactory.createEmptyBorder());

        jPanel6.add(jPanel7);
        jPanel7.setBounds(20, 240, 430, 40);
        jPanel7.setBorder(new RoundedBorder(8,1));

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
        jPanel8.add(rePasswordTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 12, 380, -1));
        rePasswordTextField.setBorder(BorderFactory.createEmptyBorder());

        rePasswordTextField.setEchoChar((char) 0);
        rePasswordTextField.setFont(new Font("Roboto", 0, 13));
        rePasswordTextField.setText("Password");
        rePasswordTextField.setBorder(BorderFactory.createEmptyBorder());

        jPanel6.add(jPanel8);
        jPanel8.setBounds(20, 310, 430, 40);
        jPanel8.setBorder(new RoundedBorder(8,1));

        jLabel9.setBackground(new java.awt.Color(65, 78, 101));
        jLabel9.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(65, 78, 101));
        jLabel9.setText("Re-Type Password");
        jPanel6.add(jLabel9);
        jLabel9.setBounds(20, 290, 270, 15);

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(65, 78, 101));
        jLabel10.setText("Student Number");
        jPanel6.add(jLabel10);
        jLabel10.setBounds(20, 80, 330, 15);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(145, 145, 145));
        jLabel11.setText("icn");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        personIcon = Utilities.changeImageColor(personIcon, PalleteColors.DROPDOWN);
        personIcon = personIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        jLabel11.setText("");
        jLabel11.setIcon(new ImageIcon(personIcon));

        studNumTextField.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        studNumTextField.setForeground(new java.awt.Color(145, 145, 145));
        studNumTextField.setText("Student Number");
        studNumTextField.setBorder(null);
        studNumTextField.setName("bookName"); // NOI18N
        studNumTextField.setPreferredSize(new java.awt.Dimension(64, 22));
        jPanel9.add(studNumTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 380, -1));
        studNumTextField.setBorder(BorderFactory.createEmptyBorder());

        studNumTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (studNumTextField.getText().equals("Student Number")) {
                    studNumTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (studNumTextField.getText().isEmpty()) {
                    studNumTextField.setText("Student Number");
                }
            }
        });

        jPanel6.add(jPanel9);
        jPanel9.setBounds(20, 100, 430, 40);
        jPanel9.setBorder(new RoundedBorder(8,1));

        togglePassVisible.setText("jToggleButton1");
        togglePassVisible.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        togglePassVisible.setContentAreaFilled(false);
        togglePassVisible.setFocusPainted(false);
        togglePassVisible.setFocusable(false);
        togglePassVisible.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel6.add(togglePassVisible);
        togglePassVisible.setBounds(457, 250, 23, 23);

        toggleRePassVisible.setText("jToggleButton1");
        toggleRePassVisible.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        toggleRePassVisible.setContentAreaFilled(false);
        toggleRePassVisible.setFocusPainted(false);
        toggleRePassVisible.setFocusable(false);
        toggleRePassVisible.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel6.add(toggleRePassVisible);
        toggleRePassVisible.setBounds(457, 320, 23, 23);

        add(jPanel6);
        jPanel6.setBounds(30, 70, 500, 400);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(null);

        userImgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/noImage.png"))); // NOI18N
        userImgLabel.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel11.add(userImgLabel);
        userImgLabel.setBounds(40, 40, 200, 200);

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Change Profile Image");
        jPanel11.add(jLabel12);
        jLabel12.setBounds(80, 20, 115, 15);

        uploadImageButton.setBackground(new java.awt.Color(65, 78, 101));
        uploadImageButton.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        uploadImageButton.setForeground(new java.awt.Color(255, 255, 255));
        uploadImageButton.setText("Upload Image");
        uploadImageButton.setFocusable(false);
        uploadImageButton.addActionListener(this::uploadImageButtonActionPerformed);
        jPanel11.add(uploadImageButton);
        uploadImageButton.setBounds(80, 250, 130, 22);
        uploadImageButton.setBorder(new RoundedBorder(8,0));

        add(jPanel11);
        jPanel11.setBounds(530, 70, 270, 300);

        cancelButton.setBackground(new java.awt.Color(171, 37, 51));
        cancelButton.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setText("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        add(cancelButton);
        cancelButton.setBounds(700, 560, 110, 30);
        cancelButton.setBorder(new RoundedBorder(8,0));

        signInButton.setBackground(new java.awt.Color(95, 192, 170));
        signInButton.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        signInButton.setForeground(new java.awt.Color(255, 255, 255));
        signInButton.setText("Save");
        signInButton.setFocusable(false);
        signInButton.addActionListener(this::signInButtonActionPerformed);
        add(signInButton);
        signInButton.setBounds(560, 560, 110, 30);
        signInButton.setBorder(new RoundedBorder(8,0));

        backButton.setText("jButton1");
        backButton.setPreferredSize(new java.awt.Dimension(30, 23));
        add(backButton);
        backButton.setBounds(801, 0, 30, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void uploadImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadImageButtonActionPerformed
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e);
        }

        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images files", "jpg","png");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(new JFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            userImg = new ImageIcon(selectedFile.getAbsolutePath()).getImage();
            userImg = userImg.getScaledInstance(userImgLabel.getWidth(), userImgLabel.getHeight(), Image.SCALE_SMOOTH);
            userImgLabel.setIcon(new ImageIcon(userImg));
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("No file selected.");
        }
    }//GEN-LAST:event_uploadImageButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        fullNameTextField.setText(user.getFullName());
        studNumTextField.setText(user.getStudentNumber());
        emailTextField.setText(user.getEmail());
        passwordTextField.setText("Password");
        rePasswordTextField.setText("Password");
        userImg = user.getIcon();
        userImg = userImg.getScaledInstance(userImgLabel.getPreferredSize().width, userImgLabel.getPreferredSize().height, Image.SCALE_SMOOTH);
        userImgLabel.setIcon(new ImageIcon(userImg));
        togglePassVisible.setSelected(false);
        toggleRePassVisible.setSelected(false);

        togglePassVisible.repaint();
        toggleRePassVisible.repaint();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInButtonActionPerformed
        String fullName = fullNameTextField.getText();
        String studentNum = studNumTextField.getText();
        String email = emailTextField.getText();
        String password = String.valueOf(passwordTextField.getPassword());
        String rePassword = String.valueOf(rePasswordTextField.getPassword());
        
        if (!email.equals(user.getEmail()) && UserHandler.doesUserExist(email, "") ) {
            JOptionPane.showMessageDialog(new JFrame(), "Email already Exists.");
            return;
        }
        else if (!studentNum.equals(user.getStudentNumber()) && UserHandler.doesUserExist("", studentNum) ) {
            JOptionPane.showMessageDialog(new JFrame(), "Student Number already Exists.");
            return;
        }
        else if (fullName.isEmpty() || fullName.equals("Full Name")) {
            JOptionPane.showMessageDialog(new JFrame(), "Full Name Field Empty.");
            return;
        }
        else if (studentNum.isEmpty() || studentNum.equals("Student Number")) {
            JOptionPane.showMessageDialog(new JFrame(), "Student Number Field Empty.");
            return;
        }
        else if (email.isEmpty() || email.equals("Email")) {
            JOptionPane.showMessageDialog(new JFrame(), "Email Field Empty.");
            return;
        }
        else if (!EmailValidator.getInstance().isValid(email)) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid Email.");
            return;
        }
        else if (password.isEmpty() || password.equals("Password")) {
            JOptionPane.showMessageDialog(new JFrame(), "Password Field Empty.");
            return;
        }
        else if (password.length() < 8) {
            JOptionPane.showMessageDialog(new JFrame(), "Password must be greater than 8 characters.");
            return;
        }
        else if (!containsSpecial(password)) {
            JOptionPane.showMessageDialog(new JFrame(), "Password must contain special character.");
            return;
        }
        else if (!password.equals(rePassword)) {
            JOptionPane.showMessageDialog(new JFrame(), "Password not the same.");
            return;
        }

        password = Utilities.toBcrypt(passwordTextField.getPassword());
        User newUser = new User(User.UserType.USER, email, password,fullName,studentNum,userImg,user.getDateJoined(),LocalDateTime.now());
        
        UserHandler.updateUser(newUser,user.getEmail());

        if (UserHandler.isLoginSuccessful(email, password.toCharArray())) {
            System.out.println("User change successful");
        }
        
        user = newUser;

        fullNameTextField.setText(user.getFullName());
        studNumTextField.setText(user.getStudentNumber());
        emailTextField.setText(user.getEmail());
        passwordTextField.setText("Password");
        rePasswordTextField.setText("Password");
        userImg = user.getIcon();
        userImg = userImg.getScaledInstance(userImgLabel.getPreferredSize().width, userImgLabel.getPreferredSize().height, Image.SCALE_SMOOTH);
        userImgLabel.setIcon(new ImageIcon(userImg));
        togglePassVisible.setSelected(false);
        toggleRePassVisible.setSelected(false);

        togglePassVisible.repaint();
        toggleRePassVisible.repaint();
    }//GEN-LAST:event_signInButtonActionPerformed
    
    private boolean containsSpecial(String password){
        
        for (char ch : password.toCharArray()) {
            if (!Character.isAlphabetic(ch)) {
                return true;
            }
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField fullNameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JPasswordField rePasswordTextField;
    private javax.swing.JButton signInButton;
    private javax.swing.JTextField studNumTextField;
    private javax.swing.JToggleButton togglePassVisible;
    private javax.swing.JToggleButton toggleRePassVisible;
    private javax.swing.JButton uploadImageButton;
    private javax.swing.JLabel userImgLabel;
    // End of variables declaration//GEN-END:variables
}
