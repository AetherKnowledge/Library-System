
package com.librarysystem.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.librarysystem.objects.Book;
import com.librarysystem.objects.IssuedBook;
import com.librarysystem.objects.IssuedBook.BorrowedBookStatus;
import com.librarysystem.objects.User;
import com.librarysystem.LibrarySystem;

public final class IssuedBooksHandler implements ObjectHandler{
    
    private static Connection con;
    private static boolean issuedBooksUpdating = false;
    private static boolean hasStarted = false;
    private static ArrayList<IssuedBook> issuedBooksList = new ArrayList<>();
    
    @Override
    public void startManager(Connection connection){
        con = connection;
        issuedBooksList = OfflineHandler.loadIssuedBooksOffline();
        if (issuedBooksList.isEmpty()) loadIssuedBooksOnline();
        System.out.println(issuedBooksList.size());
        hasStarted = true;
    }
    
    public static void addIssuedBook(IssuedBook book){
        issuedBooksUpdating = true;
        try {
            String queryRegister = "INSERT into issuedbook(email, bookID, bookName, dateBorrowed, dateReturned, borrowDuration, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement st = con.prepareStatement(queryRegister);
            st.setString(1, book.getEmail());
            st.setString(2, book.getBookID());
            st.setString(3, book.getBookName());
            st.setTimestamp(4, book.getDateBorrowed());
            if (book.getDateReturned() == null) st.setTimestamp(5,null);
            else st.setTimestamp(5, Timestamp.valueOf(book.getDateReturned()));
            st.setInt(6, book.getBorrowDuration());
            st.setString(7, book.getStatus().name());
            st.executeUpdate();
            
            issuedBooksList.add(book);
            OfflineHandler.saveIssuedBooksOffline(issuedBooksList);
            loadIssuedBooksOnline();
        }
        catch (SQLException ex) {
            Logger.getLogger(IssuedBooksHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), "Issuing Book Failed","Error",0);
        }
        
        issuedBooksUpdating = false;
    }
    
    public static void updateIssuedBook(IssuedBook book){
        issuedBooksUpdating = true;
        String query = "UPDATE issuedbook SET status = ? WHERE email = ? AND bookID = ? AND dateBorrowed = ?";
        try {
            PreparedStatement st = con.prepareCall(query);
            st.setString(1, book.getStatus().name());
            st.setString(2, book.getEmail());
            st.setString(3, book.getBookID());
            st.setTimestamp(4, book.getDateBorrowed());
            System.out.println(book);
            st.execute();
            
            OfflineHandler.saveIssuedBooksOffline(issuedBooksList);
        } catch (SQLException ex) {
            Logger.getLogger(IssuedBooksHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        issuedBooksUpdating = false;
    }
    
    public static void returnIssuedBook(IssuedBook book){
        issuedBooksUpdating = true;
        String query = "UPDATE issuedbook SET dateReturned = ?, status = ? WHERE email = ? AND bookID = ? AND dateBorrowed = ?";
        try {
            PreparedStatement st;
            
            st = con.prepareCall(query);
            
            st.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            st.setString(2, book.getStatus().name());
            st.setString(3, book.getEmail());
            st.setString(4, book.getBookID());
            st.setTimestamp(5, book.getDateBorrowed());
            st.executeUpdate();
            
            OfflineHandler.saveIssuedBooksOffline(issuedBooksList);
            JOptionPane.showMessageDialog(new JFrame(), "Issued Book Returned","Return Book",1);
        } catch (SQLException ex) {
            Logger.getLogger(IssuedBooksHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), "Issued Book Return Failed","Return Book",1);
        }
        
        issuedBooksUpdating = false;
    }
    
    public static void removeIssuedBook(IssuedBook book){
        issuedBooksUpdating = true;
        String query = "DELETE FROM issuedbook WHERE email = ? AND bookID = ? AND dateBorrowed = ?";
        try {
            PreparedStatement st;
            
            st = con.prepareCall(query);
            st.setString(1, book.getEmail());
            st.setString(2, book.getBookID());
            st.setTimestamp(3, book.getDateBorrowed());
            st.executeUpdate();
            
            issuedBooksList.remove(book);
            OfflineHandler.saveIssuedBooksOffline(issuedBooksList);
            
            JOptionPane.showMessageDialog(new JFrame(), "Issued Book Removed","Return Book",1);
        } catch (SQLException ex) {
            Logger.getLogger(IssuedBooksHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), "Issued Book Removal Failed","Error",0);
        }
        
        issuedBooksUpdating = false;
    }
    
    public static boolean hasIssuedBookUpdated(){
        boolean hasBorrowedBookUpdated = false;
        issuedBooksUpdating = true;
        
        try {
            for (int i = 0; i < issuedBooksList.size(); i++) {
                IssuedBook borrowedBook = issuedBooksList.get(i);
                String query = "SELECT lastUpdated FROM issuedbook WHERE email = ? AND bookID = ? AND dateBorrowed = ?";
                PreparedStatement pst = con.prepareCall(query);
                pst.setString(1, borrowedBook.getEmail());
                pst.setString(2, borrowedBook.getBookID());
                pst.setTimestamp(3, borrowedBook.getDateBorrowed());
                pst.execute();
                
                ResultSet resultSet = pst.getResultSet();
                
                while (resultSet.next()) {
                    
                    if (!borrowedBook.getLastUpdated().equals(resultSet.getTimestamp("lastUpdated").toLocalDateTime())) {
                        String getCategory = "SELECT * FROM issuedbook WHERE email = ? AND bookID = ? AND dateBorrowed = ?";
                        PreparedStatement pst2 = con.prepareCall(getCategory);
                        pst2.setString(1, borrowedBook.getEmail());
                        pst2.setString(2, borrowedBook.getBookID());
                        pst2.setTimestamp(3, borrowedBook.getDateBorrowed());
                        pst2.execute();
                        
                        ResultSet getCategoryResult = pst2.getResultSet();
                        getCategoryResult.next();
                        
                        issuedBooksList.set(i, getIssuedBook(getCategoryResult));
                        System.out.println("BorrowedBook "+ getCategoryResult.getString("bookID") + " Changed");
                        hasBorrowedBookUpdated = true;
                    }
                }
            }
            
            String query = "SELECT bookID, email, dateBorrowed FROM issuedbook";
            ResultSet bookIDResult = con.createStatement().executeQuery(query);
            
            ArrayList<ArrayList<String>> cacheBooks = new ArrayList<>();
            for (IssuedBook book : issuedBooksList) {
                ArrayList<String> id = new ArrayList<>();
                id.add(book.getBookID());
                id.add(book.getEmail());
                id.add(book.getDateBorrowed().toString());
                cacheBooks.add(id);
            }
            
            ArrayList<ArrayList<String>> serverBooks = new ArrayList<>();
            while (bookIDResult.next()){
                ArrayList<String> id = new ArrayList<>();
                id.add(bookIDResult.getString("bookID"));
                id.add(bookIDResult.getString("email"));
                id.add(bookIDResult.getTimestamp("dateBorrowed").toString());
                serverBooks.add(id);
            }
            
            List<ArrayList<String>> invalidBookIDs = new ArrayList<>();
            for (ArrayList<String> cacheBook : cacheBooks) {
                boolean found = false;
                for (ArrayList<String> serverBook : serverBooks) {
                    if (cacheBook.get(0).equals(serverBook.get(0)) && cacheBook.get(1).equals(serverBook.get(1))) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    invalidBookIDs.add(cacheBook);
                }
            }
            
            for (ArrayList<String> invalidBookID : invalidBookIDs) {
                for (int i = 0; i < issuedBooksList.size(); i++) {
                    IssuedBook book = issuedBooksList.get(i);
                    if (book.getBookID().equals(invalidBookID.get(0)) && book.getEmail().equals(invalidBookID.get(1)) && book.getDateBorrowed().toString().equals(invalidBookID.get(2))) {
                        issuedBooksList.remove(i);
                        i--;
                        System.out.println(book.getBookID() + " Removed from Issued Books");
                    }
                }
            }
            
            if (serverBooks.size() > issuedBooksList.size()) {
                for (ArrayList<String> id : serverBooks) {
                    if (!cacheBooks.contains(id)) {
                        String getCategory = "SELECT * FROM issuedbook WHERE bookID = '" + id.get(0) + "' AND email = '" + id.get(1) + "' AND dateBorrowed = '" +id.get(2) + "'" ;
                        ResultSet getCategoryResult = con.createStatement().executeQuery(getCategory);
                        getCategoryResult.next();
                        
                        issuedBooksList.add(getIssuedBook(getCategoryResult));
                        hasBorrowedBookUpdated = true;
                        System.out.println("Book "+ getCategoryResult.getString("bookID") + " Added");
                    }
                }
            }
            
            if (issuedBooksList.size() > serverBooks.size()) {
                issuedBooksList = new ArrayList<>();
                loadIssuedBooksOnline();
                System.out.println("Extra shit again why????");
            }
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(IssuedBooksHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        issuedBooksUpdating = false;
        if (hasBorrowedBookUpdated) {
            System.out.println("Issued Books updated");
        }
        return hasBorrowedBookUpdated;
    }
    
    public static void reloadIssuedBooksOnline(){
        issuedBooksList.removeAll(issuedBooksList);
        loadIssuedBooksOnline();
        LibrarySystem.updateData();
    }
    
    private static void loadIssuedBooksOnline(){
        issuedBooksUpdating = true;
        issuedBooksList.removeAll(issuedBooksList);
        try {
            String query = "SELECT * FROM issuedbook";
            ResultSet resultSet = con.createStatement().executeQuery(query);
            
            while (resultSet.next()) {
                issuedBooksList.add(getIssuedBook(resultSet));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IssuedBooksHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        issuedBooksUpdating = false;
    }
    
    private static IssuedBook getIssuedBook(ResultSet rs) throws SQLException{
        String email = rs.getString("email");
        String bookID = rs.getString("bookID");
        String bookName = rs.getString("bookName");
        Timestamp dateBorrowed = rs.getTimestamp("dateBorrowed");
        LocalDateTime dateReturned;
        Timestamp ts = rs.getTimestamp("dateReturned");
        if (ts == null) dateReturned = null;
        else dateReturned = rs.getTimestamp("dateReturned").toLocalDateTime();

        int borrowDuration = rs.getInt("borrowDuration");
        String statusStr = rs.getString("status");
        BorrowedBookStatus status = null;
        LocalDateTime lastUpdated = rs.getTimestamp("lastUpdated").toLocalDateTime();

        if (statusStr.equals(BorrowedBookStatus.BORROWED.name())) status = BorrowedBookStatus.BORROWED;
        else if (statusStr.equals(BorrowedBookStatus.PENDING.name())) status = BorrowedBookStatus.PENDING;
        else if (statusStr.equals(BorrowedBookStatus.RETURNED.name())) status = IssuedBook.BorrowedBookStatus.RETURNED;
        else if (statusStr.equals(BorrowedBookStatus.OVERDUE.name())) status = BorrowedBookStatus.OVERDUE;

        return new IssuedBook(email,bookID,bookName,dateBorrowed,dateReturned,borrowDuration,status,lastUpdated);
    }
    
    public static int getIssuedBooksCount(){
        issuedBooksUpdating = true;
        int count = 0;
        
        try {
            String query = "SELECT COUNT(*) AS count FROM issuedbook";
            ResultSet rs = con.createStatement().executeQuery(query);
            rs.next();
            count = rs.getInt("count");
        } catch (SQLException ex) {
            Logger.getLogger(IssuedBooksHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        issuedBooksUpdating = false;
        return count;
    }
    
    public static ArrayList<IssuedBook> getIssuedBooksFromUser(String email){
        issuedBooksUpdating = true;
        ArrayList<IssuedBook> books = new ArrayList<>();
        try {
            String query = "SELECT * FROM issuedbook WHERE email = '" + email + "'";
            ResultSet rs = con.createStatement().executeQuery(query);
            while(rs.next()){
                books.add(getIssuedBook(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssuedBooksHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        issuedBooksUpdating = false;
        return books;
    }
    
    public static int getIssuedBooksCountFromUser(String email){
        issuedBooksUpdating = true;
        int count = 0;
        try {
            String query = "SELECT COUNT(*) AS count FROM issuedbook WHERE email = '" + email + "'";
            ResultSet rs = con.createStatement().executeQuery(query);
            rs.next();
            count = rs.getInt("count");
        } catch (SQLException ex) {
            Logger.getLogger(IssuedBooksHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        issuedBooksUpdating = false;
        return count;
    }
    
    public static IssuedBook findIssuedBook(Book book, User user){
        for (IssuedBook borrowedBook : issuedBooksList) {
            if (borrowedBook.getBookID().equals(book.getBookID()) && borrowedBook.getEmail().equals(user.getEmail()) && borrowedBook.getStatus() != BorrowedBookStatus.RETURNED) {
                return borrowedBook;
            }
        }
        return null;
    }
    
    public static void setIssuedBookPending(IssuedBook book){
        issuedBooksUpdating = true;
        
        if (book == null) {
            throw new NullPointerException();
        }
        

        book.setStatus(BorrowedBookStatus.PENDING);
        updateIssuedBook(book);
        issuedBooksUpdating = false;
    }
    
    public static void addIssuedBookToCurrentUser(Book book, int daysToBorrow){
        issuedBooksUpdating = true;
        if (book.getAmmountLeft() < 1) {
            JOptionPane.showMessageDialog(new JFrame(), "No Books Left");
            return;
        }
        
        if (book.getBookStatus() == Book.Availability.UNAVAILABLE) {
            JOptionPane.showMessageDialog(new JFrame(), "Book Unavailable");
            return;
        }
        
        for(IssuedBook borrowedBook : getIssuedBooksFromUser(UserHandler.getCurrentUser().getEmail())){
            if (book.getBookID().equals(borrowedBook.getBookID()) && borrowedBook.getStatus() != BorrowedBookStatus.RETURNED) {
                JOptionPane.showMessageDialog(new JFrame(), "You already have this book on your list");
                return;
            }
        }
        
        IssuedBook borrowedBook = new IssuedBook(UserHandler.getCurrentUser().getEmail(),book.getBookID(), book.getTitle(), Timestamp.valueOf(LocalDateTime.now()),null, daysToBorrow,IssuedBook.BorrowedBookStatus.BORROWED, LocalDateTime.now());
        addIssuedBook(borrowedBook);
        
        book.removeAmmountLeft(1);
        BookHandler.updateBook(book, book.getBookID());
        JOptionPane.showMessageDialog(new JFrame(), "Book Issued Successfully");
        issuedBooksUpdating = false;
    }

    public static boolean isIssuedBooksUpdating() {
        return issuedBooksUpdating;
    }
    
    public static ArrayList<IssuedBook> getIssuedBooksList() {
        return issuedBooksList;
    }
    
    public static boolean hasStarted() {
        return hasStarted;
    }
    
}
