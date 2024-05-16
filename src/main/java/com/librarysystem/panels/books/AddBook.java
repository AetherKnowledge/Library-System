
package com.librarysystem.panels.books;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.librarysystem.LibrarySystem;
import com.librarysystem.objects.Book;
import com.librarysystem.objects.Category;
import com.librarysystem.objects.ui.PalleteColors;
import com.librarysystem.objects.components.RoundedBorder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.LookAndFeel;
import com.librarysystem.handlers.BookHandler;
import com.librarysystem.handlers.CategoryHandler;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.panels.MyPanel;
import com.librarysystem.panels.popups.EditBookPopup;

public class AddBook extends MyPanel {
    
    private Image userImg = Utilities.getImage("/textures/user.png").getImage();
    private Image bookImg = Utilities.getImage("/textures/books.png").getImage();
    private Image bookIconImg = Utilities.getImage("/textures/noImage.png").getImage();
    private Image categoryImg = Utilities.getImage("/textures/categories.png").getImage();
    private Image calendarImg = Utilities.getImage("/textures/calendar.png").getImage();
    private boolean changedImage = false;
    private LocalDate dateSelected = null;

    public AddBook() {
        
        initComponents();
        changeCalendarDesign();
        
        Utilities.addChangePointer(uploadImage);
        Utilities.addChangePointer(addBookBtn);
        
        ((JSpinner.NumberEditor) maxDaysUserBorrowedSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.NumberEditor) maxDaysUserBorrowedSpinner.getEditor()).getTextField().setCaretColor(Color.WHITE);
        ((JSpinner.NumberEditor) maxDaysUserBorrowedSpinner.getEditor()).getTextField().setBackground(Color.WHITE);
        ((JSpinner.NumberEditor) maxDaysUserBorrowedSpinner.getEditor()).getTextField().setDisabledTextColor(new Color(145,145,145));
        ((JSpinner.NumberEditor) maxDaysUserBorrowedSpinner.getEditor()).getTextField().setFont(new Font("Roboto",Font.PLAIN,12));
        
        ((JSpinner.NumberEditor) maxDaysAdminBorrowedSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.NumberEditor) maxDaysAdminBorrowedSpinner.getEditor()).getTextField().setCaretColor(Color.WHITE);
        ((JSpinner.NumberEditor) maxDaysAdminBorrowedSpinner.getEditor()).getTextField().setBackground(Color.WHITE);
        ((JSpinner.NumberEditor) maxDaysAdminBorrowedSpinner.getEditor()).getTextField().setDisabledTextColor(new Color(145,145,145));
        ((JSpinner.NumberEditor) maxDaysAdminBorrowedSpinner.getEditor()).getTextField().setFont(new Font("Roboto",Font.PLAIN,12));
        
        ((JSpinner.NumberEditor) totalAmmountSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.NumberEditor) totalAmmountSpinner.getEditor()).getTextField().setCaretColor(Color.WHITE);
        ((JSpinner.NumberEditor) totalAmmountSpinner.getEditor()).getTextField().setBackground(Color.WHITE);
        ((JSpinner.NumberEditor) totalAmmountSpinner.getEditor()).getTextField().setDisabledTextColor(new Color(145,145,145));
        ((JSpinner.NumberEditor) totalAmmountSpinner.getEditor()).getTextField().setFont(new Font("Roboto",Font.PLAIN,12));
    }
    
    private void changeCalendarDesign(){
        jDateChooser1.getJCalendar().getDayChooser().setWeekOfYearVisible(false);
        jDateChooser1.setIsBtnRounded(true);
        JToggleButton button = jDateChooser1.getCalendarButton();
        Utilities.addChangePointer(button);
    }
        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        authorTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        bookIDTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        categorySelector = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        maxDaysUserBorrowedSpinner = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        totalAmmountSpinner = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        maxDaysAdminBorrowedSpinner = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextField = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        statusTextField = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        bookIconLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        uploadImage = new javax.swing.JButton();
        addBookBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(224, 224, 224));
        setForeground(new java.awt.Color(145, 145, 145));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Title");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(145, 145, 145));
        jLabel2.setText("icn");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        bookImg = Utilities.changeImageColor(bookImg, PalleteColors.DROPDOWN);
        bookImg = bookImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        jLabel2.setText("");
        jLabel2.setIcon(new ImageIcon(bookImg));

        titleTextField.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        titleTextField.setForeground(new java.awt.Color(145, 145, 145));
        titleTextField.setText("Title");
        titleTextField.setName("titleTextField"); // NOI18N
        jPanel3.add(titleTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 380, -1));
        titleTextField.setBorder(BorderFactory.createEmptyBorder());

        titleTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (titleTextField.getText().equals("Title")) {
                    titleTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (titleTextField.getText().isEmpty()) {
                    titleTextField.setText("Title");
                }
            }
        });

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 430, 40));
        jPanel3.setBorder(new RoundedBorder(8,1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(145, 145, 145));
        jLabel3.setText("icn");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        userImg = Utilities.changeImageColor(userImg, PalleteColors.DROPDOWN);
        userImg = userImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        jLabel3.setText("");
        jLabel3.setIcon(new ImageIcon(userImg));

        authorTextField.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        authorTextField.setForeground(new java.awt.Color(145, 145, 145));
        authorTextField.setText("Author");
        authorTextField.setBorder(null);
        authorTextField.setName("bookName"); // NOI18N
        authorTextField.setPreferredSize(new java.awt.Dimension(64, 22));
        jPanel4.add(authorTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 380, -1));
        titleTextField.setBorder(BorderFactory.createEmptyBorder());

        authorTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (authorTextField.getText().equals("Author")) {
                    authorTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (authorTextField.getText().isEmpty()) {
                    authorTextField.setText("Author");
                }
            }
        });

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 430, 40));
        jPanel4.setBorder(new RoundedBorder(8,1));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Author");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(145, 145, 145));
        jLabel5.setText("icn");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        bookImg = Utilities.changeImageColor(bookImg, PalleteColors.DROPDOWN);
        bookImg = bookImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        jLabel5.setText("");
        jLabel5.setIcon(new ImageIcon(bookImg));

        bookIDTextField.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        bookIDTextField.setForeground(new java.awt.Color(145, 145, 145));
        bookIDTextField.setText("Book ID");
        bookIDTextField.setBorder(null);
        bookIDTextField.setMinimumSize(new java.awt.Dimension(64, 22));
        bookIDTextField.setName("bookName"); // NOI18N
        bookIDTextField.setPreferredSize(new java.awt.Dimension(380, 22));
        jPanel5.add(bookIDTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));
        titleTextField.setBorder(BorderFactory.createEmptyBorder());

        bookIDTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (bookIDTextField.getText().equals("Book ID")) {
                    bookIDTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (bookIDTextField.getText().isEmpty()) {
                    bookIDTextField.setText("Book ID");
                }
            }
        });

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 430, 40));
        jPanel5.setBorder(new RoundedBorder(8,1));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Book ID");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Category");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(145, 145, 145));
        jLabel12.setText("icn");
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, -1, -1));
        categoryImg = Utilities.changeImageColor(categoryImg, PalleteColors.DROPDOWN);
        categoryImg = categoryImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel12.setText("");
        jLabel12.setIcon(new ImageIcon(categoryImg));

        categorySelector.setBackground(new java.awt.Color(255, 255, 255));
        categorySelector.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        categorySelector.setForeground(new java.awt.Color(145, 145, 145));
        categorySelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        categorySelector.setPreferredSize(new java.awt.Dimension(380, 22));
        jPanel7.add(categorySelector, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 430, 40));
        jPanel7.setBorder(new RoundedBorder(8,1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(145, 145, 145));
        jLabel14.setText("icn");
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, -1, -1));
        calendarImg = Utilities.changeImageColor(calendarImg, PalleteColors.DROPDOWN);
        calendarImg = calendarImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel14.setText("");
        jLabel14.setIcon(new ImageIcon(calendarImg));

        jDateChooser1.setPreferredSize(new java.awt.Dimension(380, 22));
        jPanel8.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 430, 40));
        jPanel8.setBorder(new RoundedBorder(8,1));

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Total Ammount");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(145, 145, 145));
        jLabel16.setText("icn");
        jPanel9.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, -1, -1));
        calendarImg = Utilities.changeImageColor(calendarImg, PalleteColors.DROPDOWN);
        calendarImg = calendarImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel16.setText("");
        jLabel16.setIcon(new ImageIcon(calendarImg));

        maxDaysUserBorrowedSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 30, 1));
        maxDaysUserBorrowedSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(maxDaysUserBorrowedSpinner, ""));
        jPanel9.add(maxDaysUserBorrowedSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 150, -1));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 190, 40));
        jPanel9.setBorder(new RoundedBorder(8,1));

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Date Published");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(145, 145, 145));
        jLabel18.setText("icn");
        jPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, -1, -1));
        bookIconImg = Utilities.changeImageColor(calendarImg, PalleteColors.DROPDOWN);
        bookIconImg = calendarImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel18.setText("");
        jLabel18.setIcon(new ImageIcon(calendarImg));

        totalAmmountSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));
        totalAmmountSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(totalAmmountSpinner, ""));
        jPanel10.add(totalAmmountSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 100, -1));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 150, 40));
        jPanel10.setBorder(new RoundedBorder(8,1));

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Maximum Days Borrowed for Users");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        jLabel21.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Maximum Days Borrowed for Staff");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, -1, -1));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(145, 145, 145));
        jLabel20.setText("icn");
        jPanel11.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, -1, -1));
        calendarImg = Utilities.changeImageColor(calendarImg, PalleteColors.DROPDOWN);
        calendarImg = calendarImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel20.setText("");
        jLabel20.setIcon(new ImageIcon(calendarImg));

        maxDaysAdminBorrowedSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 30, 1));
        maxDaysAdminBorrowedSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(maxDaysAdminBorrowedSpinner, ""));
        jPanel11.add(maxDaysAdminBorrowedSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 140, -1));

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 380, 190, 40));
        jPanel11.setBorder(new RoundedBorder(8,1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 472, 500));
        jPanel1.setBorder(new RoundedBorder(8,0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        descriptionTextField.setColumns(20);
        descriptionTextField.setForeground(new java.awt.Color(145, 145, 145));
        descriptionTextField.setLineWrap(true);
        descriptionTextField.setRows(5);
        descriptionTextField.setText("Description");
        jScrollPane1.setViewportView(descriptionTextField);
        descriptionTextField.setBorder(new RoundedBorder(8,1));
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());

        descriptionTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (descriptionTextField.getText().equals("Description")) {
                    descriptionTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (descriptionTextField.getText().isEmpty()) {
                    descriptionTextField.setText("Description");
                }
            }
        });

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 370, 170));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Book Description");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Status");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        statusTextField.setBackground(new java.awt.Color(255, 255, 255));
        statusTextField.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        statusTextField.setForeground(new java.awt.Color(145, 145, 145));
        statusTextField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));
        jPanel2.add(statusTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 370, -1));

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Choose Photo (Optional)");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        bookIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/noImage.png"))); // NOI18N
        bookIconLabel.setPreferredSize(new java.awt.Dimension(130, 175));
        jPanel2.add(bookIconLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        uploadImage.setBackground(new java.awt.Color(65, 78, 101));
        uploadImage.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        uploadImage.setForeground(new java.awt.Color(255, 255, 255));
        uploadImage.setText("Upload Image");
        uploadImage.setFocusable(false);
        uploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadImageActionPerformed(evt);
            }
        });
        jPanel6.add(uploadImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));
        uploadImage.setBorder(new RoundedBorder(8,0));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 220, 110));
        jPanel6.setBorder(new RoundedBorder(8,1));

        addBookBtn.setBackground(new java.awt.Color(65, 78, 101));
        addBookBtn.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        addBookBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBookBtn.setText("Save");
        addBookBtn.setFocusable(false);
        addBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookBtnActionPerformed(evt);
            }
        });
        jPanel2.add(addBookBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 90, -1));
        addBookBtn.setBorder(new RoundedBorder(8,0));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 410, 470));
        jPanel2.setBorder(new RoundedBorder(8,0));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(145, 145, 145));
        jLabel10.setText("Books / add_book");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Add Books to System");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));
        jLabel13.setForeground(PalleteColors.SIDEBAR_MAIN_COLOR);
    }// </editor-fold>//GEN-END:initComponents
    
    private void addBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookBtnActionPerformed
        
        String title = titleTextField.getText();
        String author = authorTextField.getText();
        String bookId = bookIDTextField.getText();
        String description = descriptionTextField.getText();
        
        String tagID = (String) categorySelector.getSelectedItem();
        String[] split = tagID.split(" - ");
        tagID = split[0];
        
        Calendar date = jDateChooser1.getCalendar();
        if (date != null) {
            dateSelected = LocalDate.of(date.get(Calendar.YEAR), date.get(Calendar.MONTH)+1, date.get(Calendar.DAY_OF_MONTH));
        }

        Book.Availability bookStatus;
        if (statusTextField.getSelectedItem().equals("Available")) bookStatus = Book.Availability.AVAILABLE;
        else bookStatus = Book.Availability.UNAVAILABLE;
        
        if (title.isEmpty() || title.equals("Name of the Book")) {
            JOptionPane.showMessageDialog(new JFrame(), "Book Name.");
            return;
        }
        else if (author.isEmpty() || author.equals("Author")) {
            JOptionPane.showMessageDialog(new JFrame(), "Author Field Empty.");
            return;
        }
        else if (bookId.isEmpty() || bookId.equals("Book ID")) {
            JOptionPane.showMessageDialog(new JFrame(), "Book ID Field Empty.");
            return;
        }
        else if (dateSelected == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Select Date Published.");
            return;
        }
        else if (!(maxDaysAdminBorrowedSpinner.getValue() instanceof Integer)) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid Maximum Days");
            return;
        }
        else if (!(maxDaysUserBorrowedSpinner.getValue() instanceof Integer)) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid Maximum Days");
            return;
        }
        else if (!(totalAmmountSpinner.getValue() instanceof Integer)) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid Ammount");
            return;
        }
        
        if (!changedImage) {
            bookIconImg = Utilities.getImage("/textures/noImage.png").getImage();
            bookIconImg = Utilities.changeImageColor(bookIconImg, PalleteColors.DROPDOWN);
        }
        
        int maxDaysAdminBorrowed = (int) maxDaysAdminBorrowedSpinner.getValue();
        int maxDaysUserBorrowed = (int) maxDaysUserBorrowedSpinner.getValue();
        int totalAmmount = (int) totalAmmountSpinner.getValue();
        
        bookIconImg = bookIconImg.getScaledInstance(130, 174, Image.SCALE_SMOOTH);
        Book book = new Book(new ImageIcon(bookIconImg).getImage(),bookId,title,author,tagID,description,bookStatus,dateSelected,Timestamp.valueOf(LocalDateTime.now()),maxDaysAdminBorrowed,maxDaysUserBorrowed,totalAmmount,totalAmmount);
        BookHandler.addBook(book);
        
        bookIconImg = Utilities.getImage("/textures/noImage.png").getImage();
        bookIconLabel.setIcon(Utilities.getImage("/textures/noImage.png"));
        changedImage = false;
        
        titleTextField.setText("Title");
        authorTextField.setText("Author");
        bookIDTextField.setText("Book ID");
        descriptionTextField.setText("Description");
        categorySelector.setSelectedIndex(0);
        jDateChooser1.setDate(null);
        maxDaysAdminBorrowedSpinner.setValue(1);
        maxDaysUserBorrowedSpinner.setValue(1);
        totalAmmountSpinner.setValue(1);
    }//GEN-LAST:event_addBookBtnActionPerformed
    
    private void uploadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadImageActionPerformed
        LookAndFeel old = UIManager.getLookAndFeel();
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, e);
        }
        
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images files", "jpg","png");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(new JFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            bookIconImg = new ImageIcon(selectedFile.getAbsolutePath()).getImage();
            bookIconImg = bookIconImg.getScaledInstance(bookIconLabel.getPreferredSize().width, bookIconLabel.getPreferredSize().height, Image.SCALE_SMOOTH);
            bookIconLabel.setIcon(new ImageIcon(bookIconImg));
            bookIconLabel.setBorder(new RoundedBorder(8,1));
            changedImage = true;
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("No file selected.");
        }
        
        try {
            UIManager.setLookAndFeel(old);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(EditBookPopup.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_uploadImageActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        refreshItems();
    }//GEN-LAST:event_formComponentShown
    
    @Override
    public void refreshItems() {
        ArrayList<Category> categories = CategoryHandler.getCategoryList();
        categorySelector.removeAllItems();
        for (Category category : categories) {
            String s = category.getCategoryID() + " - " + category.getCategoryName();
            categorySelector.addItem(s);
        }
        categorySelector.setSelectedIndex(0);
    }

    @Override
    public void resize() {
    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBookBtn;
    private javax.swing.JTextField authorTextField;
    private javax.swing.JTextField bookIDTextField;
    private javax.swing.JLabel bookIconLabel;
    private javax.swing.JComboBox<String> categorySelector;
    private javax.swing.JTextArea descriptionTextField;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner maxDaysAdminBorrowedSpinner;
    private javax.swing.JSpinner maxDaysUserBorrowedSpinner;
    private javax.swing.JComboBox<String> statusTextField;
    private javax.swing.JTextField titleTextField;
    private javax.swing.JSpinner totalAmmountSpinner;
    private javax.swing.JButton uploadImage;
    // End of variables declaration//GEN-END:variables
}
