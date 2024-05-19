
package com.librarysystem.panels.popups;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.logging.Logger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.librarysystem.Frame;
import com.librarysystem.handlers.BookHandler;
import com.librarysystem.handlers.CategoryHandler;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.objects.Book;
import com.librarysystem.objects.Category;
import com.librarysystem.objects.components.RoundedBorder;
import com.librarysystem.objects.ui.Icons;
import com.librarysystem.objects.ui.PalleteColors;

public class EditBookPopup extends JDialog{
    
    private Image userImg = Utilities.getImage("/textures/user.png").getImage();
    private Image bookImg = Utilities.getImage("/textures/books.png").getImage();
    private Image bookIconImg = Icons.noImageIcon.getImage();
    private Image categoryImg = Utilities.getImage("/textures/categories.png").getImage();
    private Image calendarImg = Utilities.getImage("/textures/calendar.png").getImage();
    private Image backImg = Utilities.getImage("/textures/back.png").getImage();
    private boolean changedImage = false;
    private LocalDate dateSelected = null;
    private final Book book;

    public EditBookPopup(Book book) {
        this.book = book;
        initComponents();
        changeCalendarDesign();
        
        jPanel10.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel11.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel12.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        jPanel13.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        
        ArrayList<Category> categories = CategoryHandler.getCategoryList();
        categorySelector.removeAllItems();
        for (Category category : categories) {
            String s = category.getCategoryID() + " - " + category.getCategoryName();
            categorySelector.addItem(s);
        }
        categorySelector.setSelectedIndex(0);
        
        titleTextField.setText(book.getTitle());
        authorTextField.setText(book.getAuthor());
        statusTextField.setSelectedItem(book.getBookStatus().name());
        categorySelector.setSelectedItem(book.getCategoryID() + " - " + CategoryHandler.getCategory(book.getCategoryID()).getCategoryName());
        
        Date date = Date.from(book.getDatePublished().atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
        jDateChooser1.setDate(date);
        bookIDTextField.setText(book.getBookID());
        descriptionTextField.setText(book.getDescription());
        
        maxDaysAdminBorrowedSpinner.setValue(book.getMaxDaysAdminBorrowed());
        maxDaysUserBorrowedSpinner.setValue(book.getMaxDaysUserBorrowed());
        totalAmmountSpinner.setValue(book.getTotalAmmount());
        
        Image image = book.getBookIcon();
        image = image.getScaledInstance(bookIconLabel.getPreferredSize().width, bookIconLabel.getPreferredSize().height, Image.SCALE_SMOOTH);
        
        bookIconLabel.setIcon(new ImageIcon(image));
        
        titleTextField.setDisabledTextColor(new Color(145,145,145));
        authorTextField.setDisabledTextColor(new Color(145,145,145));
        bookIDTextField.setDisabledTextColor(new Color(145,145,145));
        descriptionTextField.setDisabledTextColor(new Color(145,145,145));
        
        titleTextField.setEnabled(false);
        authorTextField.setEnabled(false);
        bookIDTextField.setEnabled(false);
        descriptionTextField.setEnabled(false);
        
        editBtn.setEnabled(true);
        saveButton.setEnabled(false);
        titleTextField.setEditable(false);
        authorTextField.setEditable(false);
        statusTextField.setEnabled(false);
        categorySelector.setEnabled(false); 
        jDateChooser1.setEnabled(false);
        bookIDTextField.setEditable(false);
        descriptionTextField.setEditable(false);
        uploadImage.setEnabled(false);
        totalAmmountSpinner.setEnabled(false); 
        maxDaysAdminBorrowedSpinner.setEnabled(false); 
        maxDaysUserBorrowedSpinner.setEnabled(false); 
        
        changeButton(backButton);
        
        Utilities.addChangePointer(editBtn);
        Utilities.addChangePointer(saveButton);
        
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
    
    private void changeCalendarDesign(){
        jDateChooser1.getJCalendar().getDayChooser().setWeekOfYearVisible(false);
        jDateChooser1.setIsBtnRounded(true);
        JToggleButton button = jDateChooser1.getCalendarButton();
        Utilities.addChangePointer(button);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
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
        jPanel14 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        maxDaysUserBorrowedSpinner = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        totalAmmountSpinner = new javax.swing.JSpinner();
        jPanel16 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        maxDaysAdminBorrowedSpinner = new javax.swing.JSpinner();
        jLabel21 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextField = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        statusTextField = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        bookIconLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        uploadImage = new javax.swing.JButton(){
            @Override
            public void paint(Graphics g){
                Utilities.changeButtonColor(this, g);
            }
        };
        saveButton = new javax.swing.JButton(){
            @Override
            public void paint(Graphics g){
                Utilities.changeButtonColor(this, g);
            }
        };
        backButton = new javax.swing.JButton();
        editBtn = new javax.swing.JButton(){
            @Override
            public void paint(Graphics g){
                Utilities.changeButtonColor(this, g);
            }
        };
        deleteBook = new javax.swing.JButton(){
            @Override
            public void paint(Graphics g){
                Utilities.changeButtonColor(this, g);
            }
        };
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(224, 224, 224));

        jPanel9.setBackground(new java.awt.Color(224, 224, 224));
        jPanel9.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Title");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 40, 23, 15);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(220, 40));
        jPanel3.setPreferredSize(new java.awt.Dimension(220, 40));
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
        titleTextField.setForeground(new java.awt.Color(0, 0, 0));
        titleTextField.setText("Title");
        titleTextField.setDisabledTextColor(new java.awt.Color(145, 145, 145));
        titleTextField.setName("titleTextField"); // NOI18N
        jPanel3.add(titleTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 170, -1));
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

        jPanel1.add(jPanel3);
        jPanel3.setBounds(20, 60, 220, 40);
        jPanel3.setBorder(new RoundedBorder(8,1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(220, 40));
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
        authorTextField.setForeground(new java.awt.Color(0, 0, 0));
        authorTextField.setText("Author");
        authorTextField.setBorder(null);
        authorTextField.setDisabledTextColor(new java.awt.Color(145, 145, 145));
        authorTextField.setName("bookName"); // NOI18N
        authorTextField.setPreferredSize(new java.awt.Dimension(170, 22));
        jPanel4.add(authorTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));
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

        jPanel1.add(jPanel4);
        jPanel4.setBounds(20, 130, 220, 40);
        jPanel4.setBorder(new RoundedBorder(8,1));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Author");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 110, 37, 15);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(220, 40));
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
        bookIDTextField.setForeground(new java.awt.Color(0, 0, 0));
        bookIDTextField.setText("Book ID");
        bookIDTextField.setBorder(null);
        bookIDTextField.setDisabledTextColor(new java.awt.Color(145, 145, 145));
        bookIDTextField.setMinimumSize(new java.awt.Dimension(64, 22));
        bookIDTextField.setName("bookName"); // NOI18N
        bookIDTextField.setPreferredSize(new java.awt.Dimension(170, 22));
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

        jPanel1.add(jPanel5);
        jPanel5.setBounds(20, 200, 220, 40);
        jPanel5.setBorder(new RoundedBorder(8,1));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Book ID");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 180, 43, 15);

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Category");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 250, 48, 15);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(220, 40));
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
        categorySelector.setForeground(new java.awt.Color(0, 0, 0));
        categorySelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        categorySelector.setPreferredSize(new java.awt.Dimension(170, 22));
        jPanel7.add(categorySelector, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jPanel1.add(jPanel7);
        jPanel7.setBounds(20, 270, 220, 40);
        jPanel7.setBorder(new RoundedBorder(8,1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(220, 40));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(145, 145, 145));
        jLabel14.setText("icn");
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, -1, -1));
        calendarImg = Utilities.changeImageColor(calendarImg, PalleteColors.DROPDOWN);
        calendarImg = calendarImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel14.setText("");
        jLabel14.setIcon(new ImageIcon(calendarImg));

        jDateChooser1.setForeground(new java.awt.Color(0, 0, 0));
        jDateChooser1.setPreferredSize(new java.awt.Dimension(170, 22));
        jPanel8.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jPanel1.add(jPanel8);
        jPanel8.setBounds(20, 340, 220, 40);
        jPanel8.setBorder(new RoundedBorder(8,1));

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Date Published");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(20, 320, 81, 15);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(145, 145, 145));
        jLabel16.setText("icn");
        jPanel14.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, -1, -1));
        calendarImg = Utilities.changeImageColor(calendarImg, PalleteColors.DROPDOWN);
        calendarImg = calendarImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel16.setText("");
        jLabel16.setIcon(new ImageIcon(calendarImg));

        maxDaysUserBorrowedSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 30, 1));
        maxDaysUserBorrowedSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(maxDaysUserBorrowedSpinner, ""));
        jPanel14.add(maxDaysUserBorrowedSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 170, -1));

        jPanel1.add(jPanel14);
        jPanel14.setBounds(20, 410, 220, 40);
        jPanel14.setBorder(new RoundedBorder(8,1));

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Total Ammount");
        jPanel1.add(jLabel19);
        jLabel19.setBounds(20, 550, 83, 15);

        jLabel20.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Maximum Days Borrowed for Users");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(20, 390, 210, 15);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(145, 145, 145));
        jLabel18.setText("icn");
        jPanel15.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, -1, -1));
        bookIconImg = Utilities.changeImageColor(calendarImg, PalleteColors.DROPDOWN);
        bookIconImg = calendarImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel18.setText("");
        jLabel18.setIcon(new ImageIcon(calendarImg));

        totalAmmountSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 30, 1));
        totalAmmountSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(totalAmmountSpinner, ""));
        jPanel15.add(totalAmmountSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 170, -1));

        jPanel1.add(jPanel15);
        jPanel15.setBounds(20, 570, 220, 40);
        jPanel15.setBorder(new RoundedBorder(8,1));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(145, 145, 145));
        jLabel17.setText("icn");
        jPanel16.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, -1, -1));
        calendarImg = Utilities.changeImageColor(calendarImg, PalleteColors.DROPDOWN);
        calendarImg = calendarImg.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        jLabel16.setText("");
        jLabel16.setIcon(new ImageIcon(calendarImg));

        maxDaysAdminBorrowedSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 30, 1));
        maxDaysAdminBorrowedSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(maxDaysAdminBorrowedSpinner, ""));
        jPanel16.add(maxDaysAdminBorrowedSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 170, -1));

        jPanel1.add(jPanel16);
        jPanel16.setBounds(20, 490, 220, 40);
        jPanel16.setBorder(new RoundedBorder(8,1));

        jLabel21.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Maximum Days Borrowed for Staff");
        jPanel1.add(jLabel21);
        jLabel21.setBounds(20, 470, 200, 15);

        jPanel9.add(jPanel1);
        jPanel1.setBounds(0, 0, 250, 660);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        descriptionTextField.setColumns(20);
        descriptionTextField.setForeground(new java.awt.Color(0, 0, 0));
        descriptionTextField.setLineWrap(true);
        descriptionTextField.setRows(5);
        descriptionTextField.setText("Description");
        descriptionTextField.setDisabledTextColor(new java.awt.Color(145, 145, 145));
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

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(20, 270, 270, 170);

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Book Description");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(20, 250, 92, 15);

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Status");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(20, 200, 34, 15);

        statusTextField.setBackground(new java.awt.Color(255, 255, 255));
        statusTextField.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        statusTextField.setForeground(new java.awt.Color(0, 0, 0));
        statusTextField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));
        jPanel2.add(statusTextField);
        statusTextField.setBounds(20, 220, 270, 21);

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Choose Photo (Optional)");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(20, 40, 160, 15);

        bookIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/noImage.png"))); // NOI18N
        bookIconLabel.setPreferredSize(new java.awt.Dimension(130, 175));
        jPanel2.add(bookIconLabel);
        bookIconLabel.setBounds(160, 30, 130, 175);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(null);

        uploadImage.setBackground(new java.awt.Color(65, 78, 101));
        uploadImage.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        uploadImage.setForeground(new java.awt.Color(255, 255, 255));
        uploadImage.setText("Upload Image");
        uploadImage.setFocusable(false);
        uploadImage.addActionListener(this::uploadImageActionPerformed);
        jPanel6.add(uploadImage);
        uploadImage.setBounds(10, 10, 106, 22);
        uploadImage.setBorder(new RoundedBorder(8,0));

        jPanel2.add(jPanel6);
        jPanel6.setBounds(20, 60, 130, 110);
        jPanel6.setBorder(new RoundedBorder(8,1));

        saveButton.setBackground(new java.awt.Color(65, 78, 101));
        saveButton.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Save");
        saveButton.setEnabled(false);
        saveButton.setFocusable(false);
        saveButton.addActionListener(this::saveButtonActionPerformed);
        jPanel2.add(saveButton);
        saveButton.setBounds(200, 450, 90, 22);
        saveButton.setBorder(new RoundedBorder(8,0));

        backButton.setText("jButton1");
        backButton.setPreferredSize(new java.awt.Dimension(30, 23));
        backButton.addActionListener(this::backButtonActionPerformed);
        jPanel2.add(backButton);
        backButton.setBounds(280, 0, 30, 23);

        editBtn.setBackground(new java.awt.Color(65, 78, 101));
        editBtn.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setText("Edit");
        editBtn.setFocusable(false);
        editBtn.addActionListener(this::editBtnActionPerformed);
        jPanel2.add(editBtn);
        editBtn.setBounds(20, 450, 90, 22);
        editBtn.setBorder(new RoundedBorder(8,0));

        jPanel9.add(jPanel2);
        jPanel2.setBounds(270, 0, 310, 490);

        deleteBook.setBackground(new java.awt.Color(171, 37, 51));
        deleteBook.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        deleteBook.setForeground(new java.awt.Color(255, 255, 255));
        deleteBook.setText("Delete Book");
        deleteBook.setFocusable(false);
        deleteBook.addActionListener(this::deleteBookActionPerformed);
        jPanel9.add(deleteBook);
        deleteBook.setBounds(440, 520, 120, 22);
        editBtn.setBorder(new RoundedBorder(8,0));

        getContentPane().add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel10.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
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
            .addGap(0, 589, Short.MAX_VALUE)
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
            .addGap(0, 633, Short.MAX_VALUE)
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
            .addGap(0, 633, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel13, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed

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
        else if (!(totalAmmountSpinner.getValue() instanceof Integer)) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid Ammount");
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

        if (!changedImage) {
            bookIconImg = book.getBookIcon();
        }
        
        int maxDaysAdminBorrowed = (int) maxDaysAdminBorrowedSpinner.getValue();
        int maxDaysUserBorrowed = (int) maxDaysUserBorrowedSpinner.getValue();
        int totalAmmount = (int) totalAmmountSpinner.getValue();
        int ammountLeft = book.getAmmountLeft() + totalAmmount - book.getTotalAmmount();
        if (ammountLeft < 0) {
            JOptionPane.showMessageDialog(new JFrame(), "Ammount Left Cannot be less than 0");
            return;
        }
        
        bookIconImg = bookIconImg.getScaledInstance(130, 174, Image.SCALE_SMOOTH);
        System.out.println("");
        
        Book newBook = new Book(new ImageIcon(bookIconImg).getImage(),bookId,title,author,tagID,description,bookStatus,dateSelected,Timestamp.valueOf(LocalDateTime.now()),maxDaysAdminBorrowed,maxDaysUserBorrowed,totalAmmount,ammountLeft,book.isImageDefault());

        BookHandler.updateBook(newBook, book.getBookID());
        
        editBtn.setEnabled(true);
        saveButton.setEnabled(false);
        titleTextField.setEditable(false);
        authorTextField.setEditable(false);
        statusTextField.setEnabled(false);
        categorySelector.setEnabled(false);
        jDateChooser1.setEnabled(false);
        descriptionTextField.setEditable(false);
        uploadImage.setEnabled(false);
        
        titleTextField.setEnabled(false);
        authorTextField.setEnabled(false);
        descriptionTextField.setEnabled(false);
        totalAmmountSpinner.setEnabled(false); 
        maxDaysAdminBorrowedSpinner.setEnabled(false); 
        maxDaysUserBorrowedSpinner.setEnabled(false); 
        
        ((JSpinner.NumberEditor) totalAmmountSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.NumberEditor) maxDaysAdminBorrowedSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.NumberEditor) maxDaysUserBorrowedSpinner.getEditor()).getTextField().setEditable(false);
    }//GEN-LAST:event_saveButtonActionPerformed

    private void uploadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadImageActionPerformed
        
        LookAndFeel old = UIManager.getLookAndFeel();
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(EditBookPopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        this.setAlwaysOnTop(false);
        
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
            book.setIsDefaultImage(false);
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("No file selected.");
        }
        
        try {
            UIManager.setLookAndFeel(old);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(EditBookPopup.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        this.setAlwaysOnTop(true);
    }//GEN-LAST:event_uploadImageActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        editBtn.setEnabled(false);
        saveButton.setEnabled(true);
        titleTextField.setEditable(true);
        authorTextField.setEditable(true);
        statusTextField.setEnabled(true);
        categorySelector.setEnabled(true);
        jDateChooser1.setEnabled(true);
        descriptionTextField.setEditable(true);
        uploadImage.setEnabled(true);
        
        titleTextField.setEnabled(true);
        authorTextField.setEnabled(true);
        descriptionTextField.setEnabled(true);
        totalAmmountSpinner.setEnabled(true); 
        maxDaysAdminBorrowedSpinner.setEnabled(true); 
        maxDaysUserBorrowedSpinner.setEnabled(true); 
        
        ((JSpinner.NumberEditor) maxDaysAdminBorrowedSpinner.getEditor()).getTextField().setEditable(true);
        ((JSpinner.NumberEditor) maxDaysUserBorrowedSpinner.getEditor()).getTextField().setEditable(true);
        ((JSpinner.NumberEditor) totalAmmountSpinner.getEditor()).getTextField().setEditable(true);
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBookActionPerformed
        BookHandler.removeBook(book);
        Frame.removePopup();
        this.dispose();
    }//GEN-LAST:event_deleteBookActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField authorTextField;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField bookIDTextField;
    private javax.swing.JLabel bookIconLabel;
    private javax.swing.JComboBox<String> categorySelector;
    private javax.swing.JButton deleteBook;
    private javax.swing.JTextArea descriptionTextField;
    private javax.swing.JButton editBtn;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
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
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> statusTextField;
    private javax.swing.JTextField titleTextField;
    private javax.swing.JSpinner totalAmmountSpinner;
    private javax.swing.JButton uploadImage;
    // End of variables declaration//GEN-END:variables
}
