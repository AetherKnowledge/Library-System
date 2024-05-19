
package com.librarysystem.panels.books;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import com.librarysystem.objects.ui.PalleteColors;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import com.librarysystem.Frame;
import com.librarysystem.handlers.CategoryHandler;
import com.librarysystem.handlers.IssuedBooksHandler;
import com.librarysystem.handlers.UserHandler;
import com.librarysystem.objects.Book;
import com.librarysystem.objects.IssuedBook;
import com.librarysystem.objects.Category;
import com.librarysystem.objects.User;
import com.librarysystem.objects.components.RoundedBorder;
import static com.librarysystem.handlers.IssuedBooksHandler.findIssuedBook;
import com.librarysystem.handlers.Utilities;
import java.awt.Graphics;
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BookPanel extends JPanel {
    
    private Image iconImg;
    private final Book book;
    private Category category;
    private JButton editButton;
    private IssuedBook borrowed;
    
    public BookPanel(Book book) {
        this.iconImg = book.getBookIcon();
        this.book = book;
        
        initComponents();
        this.setBorder(new RoundedBorder(8,0));
        this.setBackground(PalleteColors.DROPDOWN_PRESSED);
        authorTextField.setText("by " + book.getAuthor());
        
        iconImg = iconImg.getScaledInstance(getPreferredSize().width - 16, getPreferredSize().height - 80 - 16, Image.SCALE_SMOOTH);
        icon.setText("");
        icon.setIcon(new ImageIcon(iconImg));

        
        for (Category cat : CategoryHandler.getCategoryList()) {
            if (cat.getCategoryID().equals(book.getCategoryID())) {
                this.category = cat;
                break;
            }
        }
        
        if (Frame.getCurrentPanel() instanceof MyBookList) {
            addToListButton.addActionListener(l -> returnButtonAction());
            addToListButton.setText("Return");
            
            borrowed = findIssuedBook(book, UserHandler.getCurrentUser());
            
            switch(borrowed.getStatus()){
                case PENDING ->{
                    jPanel11.removeAll();
                    JLabel pendingLabel = new JLabel();
                    pendingLabel.setText("Pending Return");
                    pendingLabel.setOpaque(true);
                    pendingLabel.setBackground(new Color(220,225,124).darker());
                    pendingLabel.setBorder(new RoundedBorder(4,1));
                    pendingLabel.setFont(new Font("Roboto", 0, 12));
                    pendingLabel.setForeground(new Color(255, 255, 255));
                    jPanel11.add(pendingLabel);
                }
                case OVERDUE -> {
                    authorTextField.setText("Overdue, Please Return");
                    authorTextField.setForeground(new Color(171,37,51));
                    authorTextField.setFont(new Font("Roboto", Font.BOLD, 12));
                }
                case BORROWED -> {
                    LocalDateTime dateWritten = borrowed.getDateBorrowed().toLocalDateTime().minusDays(borrowed.getBorrowDuration());
                    String month = dateWritten.getMonth().toString().toLowerCase();
                    month = Character.toString(Character.toUpperCase(month.charAt(0))) + month.substring(1, month.length());
                    String day = Integer.toString(dateWritten.getDayOfMonth());
                    String year = Integer.toString(dateWritten.getYear());
                    String date = month + ", " + day + " " + year;
                    authorTextField.setText("Return by " + date);
                }
            }
        }
        else if (UserHandler.getCurrentUserType() == User.UserType.ADMIN && Frame.getCurrentPanel() instanceof BookList) {
            jPanel10.removeAll();
            editButton = new JButton(){
                @Override
                public void paint(Graphics g){
                    Utilities.changeButtonColor(this, g);
                }
            };
            editButton.addActionListener(l -> {
                editButtonAction();
            });
            editButton.setBackground(PalleteColors.GREEN);
            editButton.setForeground(Color.WHITE);
            editButton.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
            editButton.setText("Edit Book");
            editButton.setPreferredSize(new Dimension(100, 18));
            editButton.setBorder(BorderFactory.createEmptyBorder());
            
            jPanel10.add(editButton);
            
            addToListButton.addActionListener(l -> addToListButtonAction());
        }
        else if (UserHandler.getCurrentUserType() == User.UserType.USER && Frame.getCurrentPanel() instanceof BookList) {
            addToListButton.addActionListener(l -> addToListButtonAction());
        }
        
        titleTextField.setText(book.getTitle());
        tagTextField.setText(category.getCategoryName());
        
        //this.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
        
        Utilities.addChangePointer(addToListButton);
        
    }
    
    public Book getBook(){
        return book;
    }
    
    public Category getCategory(){
        return category;
    }
    
    public String getTitle(){
        return book.getTitle();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        tagTextField = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        titleTextField = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        authorTextField = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        addToListButton = new javax.swing.JButton(){
            @Override
            public void paint(Graphics g){
                Utilities.changeButtonColor(this, g);
            }
        };
        icon = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(260, 358));
        setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(65, 78, 101));
        jPanel6.setPreferredSize(new java.awt.Dimension(15, 18));
        jPanel6.setLayout(new java.awt.BorderLayout());

        tagTextField.setBackground(new java.awt.Color(52, 63, 82));
        tagTextField.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        tagTextField.setForeground(new java.awt.Color(255, 255, 255));
        tagTextField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tagTextField.setText("Action Drama Suspense");
        tagTextField.setPreferredSize(new java.awt.Dimension(16, 13));
        jPanel6.add(tagTextField, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6, java.awt.BorderLayout.SOUTH);
        jPanel6.setBackground(PalleteColors.DROPDOWN_PRESSED);

        jPanel7.setBackground(new java.awt.Color(65, 78, 101));
        jPanel7.setLayout(new java.awt.BorderLayout());

        titleTextField.setBackground(new java.awt.Color(255, 255, 255));
        titleTextField.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        titleTextField.setForeground(new java.awt.Color(255, 255, 255));
        titleTextField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleTextField.setText("Attack on Titan");
        jPanel7.add(titleTextField, java.awt.BorderLayout.CENTER);
        jPanel7.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);

        jPanel5.add(jPanel7, java.awt.BorderLayout.CENTER);

        add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel8.setBackground(new java.awt.Color(255, 255, 0));
        jPanel8.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(65, 78, 101));
        jPanel10.setForeground(new java.awt.Color(255, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(130, 170));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        authorTextField.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        authorTextField.setForeground(new java.awt.Color(255, 255, 255));
        authorTextField.setText("by Hajime Isayama");
        jPanel10.add(authorTextField, new java.awt.GridBagConstraints());

        jPanel8.add(jPanel10, java.awt.BorderLayout.WEST);
        jPanel8.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);

        jPanel11.setBackground(new java.awt.Color(65, 78, 101));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        addToListButton.setBackground(new java.awt.Color(95, 192, 170));
        addToListButton.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        addToListButton.setForeground(new java.awt.Color(255, 255, 255));
        addToListButton.setText("Details");
        addToListButton.setBorder(null);
        addToListButton.setPreferredSize(new java.awt.Dimension(100, 18));
        jPanel11.add(addToListButton, new java.awt.GridBagConstraints());
        addToListButton.setBackground(PalleteColors.BUTTON_PRESSED_TEXT);

        jPanel8.add(jPanel11, java.awt.BorderLayout.CENTER);
        jPanel8.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);

        add(jPanel8, java.awt.BorderLayout.SOUTH);

        icon.setText("icon");
        add(icon, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void editButtonAction(){
        Frame.removePopup();
        Frame.makePopup(Frame.PopupType.EDITBOOK,book);
    }
    
    private void addToListButtonAction(){
        Frame.removePopup();
        Frame.makePopup(Frame.PopupType.BOOKDETAILS, book);
    }
    private void returnButtonAction(){
        Frame.removePopup();
        IssuedBooksHandler.setIssuedBookPending(borrowed);
        JOptionPane.showMessageDialog(new JFrame(), "Book "+ book.getTitle() + " Return Pending");
        
        jPanel11.removeAll();
        JLabel pendingLabel = new JLabel();
        pendingLabel.setText("Pending Return");
        pendingLabel.setOpaque(true);
        pendingLabel.setBackground(new Color(220,225,124).darker());
        pendingLabel.setBorder(new RoundedBorder(4,1));
        pendingLabel.setFont(new Font("Roboto", 0, 12));
        pendingLabel.setForeground(new Color(255, 255, 255));
        jPanel11.add(pendingLabel);
        this.repaint();
        this.revalidate();
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToListButton;
    private javax.swing.JLabel authorTextField;
    private javax.swing.JLabel icon;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel tagTextField;
    private javax.swing.JLabel titleTextField;
    // End of variables declaration//GEN-END:variables
}
