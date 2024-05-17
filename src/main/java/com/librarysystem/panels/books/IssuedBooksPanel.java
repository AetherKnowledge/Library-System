
package com.librarysystem.panels.books;

import com.librarysystem.Frame;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.librarysystem.handlers.BookHandler;
import com.librarysystem.handlers.IssuedBooksHandler;
import com.librarysystem.handlers.UserHandler;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.objects.Book;
import com.librarysystem.objects.IssuedBook;
import com.librarysystem.objects.IssuedBook.BorrowedBookStatus;
import com.librarysystem.objects.User;
import com.librarysystem.objects.components.LineBorderSide;
import com.librarysystem.objects.components.LineBorderSide.Side;
import com.librarysystem.objects.components.RoundedBorder;
import java.awt.Graphics;

public class IssuedBooksPanel extends javax.swing.JPanel {

    private Book book;
    private IssuedBook borrowedBook;
    private User user;
    
    public IssuedBooksPanel(IssuedBook borrowedBook) {
        
        if (borrowedBook.getStatus() == BorrowedBookStatus.RETURNED && borrowedBook.getDateReturned() == null) {
            System.out.println("Invalid borrowed Book");
            IssuedBooksHandler.reloadIssuedBooksOnline();
            return;
        }
        
        initComponents();
        this.borrowedBook = borrowedBook;
        this.book = BookHandler.getBook(borrowedBook.getBookID());
        this.user = UserHandler.searchUser(borrowedBook.getEmail());
        
        bookID.setText(book.getBookID());
        bookTitle.setText(book.getTitle());
        borrower.setText(user.getEmail());
        String month = borrowedBook.getDateBorrowed().getMonth().toString().toLowerCase();
        month = Character.toString(Character.toUpperCase(month.charAt(0))) + month.substring(1, month.length());
        String day = Integer.toString(borrowedBook.getDateBorrowed().getDayOfMonth());
        String year = Integer.toString(borrowedBook.getDateBorrowed().getYear());
        borrowDate.setText(month + ", " + day + " " + year);
        
        LocalDateTime dateToReturn = borrowedBook.getDateBorrowed().plusDays(borrowedBook.getBorrowDuration());
        
        month = dateToReturn.getMonth().toString().toLowerCase();
        month = Character.toString(Character.toUpperCase(month.charAt(0))) + month.substring(1, month.length());
        day = Integer.toString(dateToReturn.getDayOfMonth());
        year = Integer.toString(dateToReturn.getYear());
        returnDate.setText(month + ", " + day + " " + year);
        
        status.setText(borrowedBook.getStatus().text);
        
        if (borrowedBook.getStatus() != BorrowedBookStatus.PENDING) {
            jPanel3.removeAll();
            JLabel label = new JLabel();
            label.setText("No Action");
            label.setBorder(BorderFactory.createEmptyBorder());
            label.setFont(new Font("Roboto", 0, 12));
            label.setForeground(new Color(255, 255, 255));
            jPanel3.add(label);
        }
        
        if (borrowedBook.getStatus() == BorrowedBookStatus.RETURNED && Frame.getCurrentPanel() instanceof ReturnedBooks) {
            jPanel2.remove(jPanel7);
            jPanel2.remove(jPanel8);
            jPanel3.removeAll();
            
            LocalDateTime dateReturned = borrowedBook.getDateReturned();
            month = dateReturned.getMonth().toString().toLowerCase();
            month = Character.toString(Character.toUpperCase(month.charAt(0))) + month.substring(1, month.length());
            day = Integer.toString(dateReturned.getDayOfMonth());
            year = Integer.toString(dateReturned.getYear());
            returnDate.setText(month + ", " + day + " " + year);
            jPanel3.add(returnDate);
        }
        else if (borrowedBook.getStatus() == BorrowedBookStatus.RETURNED && Frame.getCurrentPanel() instanceof History) {
            jPanel2.remove(jPanel5);
            jPanel2.remove(jPanel8);
            this.remove(jPanel3);
            
            LocalDateTime dateReturned = borrowedBook.getDateReturned();
            month = dateReturned.getMonth().toString().toLowerCase();
            month = Character.toString(Character.toUpperCase(month.charAt(0))) + month.substring(1, month.length());
            day = Integer.toString(dateReturned.getDayOfMonth());
            year = Integer.toString(dateReturned.getYear());
            returnDate.setText(month + ", " + day + " " + year);
            jPanel7.add(returnDate);
        }
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bookID = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        bookTitle = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        borrower = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        borrowDate = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        returnDate = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        status = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton(){
            @Override
            public void paint(Graphics g){
                Utilities.changeButtonColor(this, g);
            }
        };

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(895, 65));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(65, 78, 101));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(101, 101));
        jPanel1.setLayout(new java.awt.BorderLayout());

        bookID.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        bookID.setForeground(new java.awt.Color(255, 255, 255));
        bookID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookID.setText("000");
        bookID.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(bookID, java.awt.BorderLayout.CENTER);
        bookID.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setBackground(new java.awt.Color(65, 78, 101));
        jPanel2.setLayout(new java.awt.GridLayout(1, 5));

        jPanel4.setBackground(new java.awt.Color(65, 78, 101));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        bookTitle.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        bookTitle.setForeground(new java.awt.Color(255, 255, 255));
        bookTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookTitle.setText("Attack on Titan");
        bookTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(bookTitle, java.awt.BorderLayout.CENTER);
        bookTitle.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        jPanel2.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(65, 78, 101));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.BorderLayout());

        borrower.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        borrower.setForeground(new java.awt.Color(255, 255, 255));
        borrower.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        borrower.setText("JC");
        borrower.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel5.add(borrower, java.awt.BorderLayout.CENTER);
        borrower.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        jPanel2.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(65, 78, 101));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.BorderLayout());

        borrowDate.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        borrowDate.setForeground(new java.awt.Color(255, 255, 255));
        borrowDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        borrowDate.setText("1/11/24");
        borrowDate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(borrowDate, java.awt.BorderLayout.CENTER);
        borrowDate.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        jPanel2.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(65, 78, 101));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());

        returnDate.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        returnDate.setForeground(new java.awt.Color(255, 255, 255));
        returnDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        returnDate.setText("1/13/24");
        returnDate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(returnDate, java.awt.BorderLayout.CENTER);
        returnDate.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        jPanel2.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(65, 78, 101));
        jPanel8.setLayout(new java.awt.BorderLayout());

        status.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        status.setForeground(new java.awt.Color(255, 255, 255));
        status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        status.setText("Pending");
        status.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(status, java.awt.BorderLayout.CENTER);
        status.setBorder(new LineBorderSide(Side.EASTSOUTH,2,Color.WHITE));

        jPanel2.add(jPanel8);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(65, 78, 101));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(140, 140));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButton1.setBackground(new java.awt.Color(95, 192, 170));
        jButton1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Confirm Return");
        jButton1.setPreferredSize(new java.awt.Dimension(120, 22));
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanel3.add(jButton1, new java.awt.GridBagConstraints());
        jButton1.setBorder(new RoundedBorder(8,0));

        add(jPanel3, java.awt.BorderLayout.EAST);
        jPanel3.setBorder(new LineBorderSide(Side.SOUTH,2,Color.WHITE));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        borrowedBook.setStatus(BorrowedBookStatus.RETURNED);
        IssuedBooksHandler.returnIssuedBook(borrowedBook);
        JPanel parent = (JPanel) getParent();
        parent.remove(this);
        parent.revalidate();
        parent.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    public Book getBook() {
        return book;
    }

    public IssuedBook getBorrowedBook() {
        return borrowedBook;
    }
    
    public User getUser() {
        return user;
    }
    
    public String getTitle(){
        return book.getTitle();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bookID;
    private javax.swing.JLabel bookTitle;
    private javax.swing.JLabel borrowDate;
    private javax.swing.JLabel borrower;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel returnDate;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
}
