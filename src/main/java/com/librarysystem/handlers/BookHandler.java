
package com.librarysystem.handlers;

import java.awt.Image;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.librarysystem.LibrarySystem;
import com.librarysystem.objects.Book;
import com.librarysystem.objects.Category;
import com.librarysystem.objects.ui.Icons;
import com.librarysystem.objects.ui.PalleteColors;

public final class BookHandler implements ObjectHandler{
    private static Connection con;
    private static boolean bookUpdating = false;
    private static boolean hasStarted = false;
    private static ArrayList<Book> booksList = new ArrayList<>();
    
    @Override
    public void startManager(Connection connection){
        con = connection;
        booksList = OfflineHandler.loadBooksOffline();
        if (booksList.isEmpty()) BookHandler.loadBooksOnline();
        
//        if (booksList.isEmpty()) {
//            BookHandler.addStandardBooks();
//            CategoryHandler.updateCategory(CategoryHandler.getCategoryList().get(2));
//            CategoryHandler.updateCategory(CategoryHandler.getCategoryList().get(8));
//        }
        hasStarted = true;
    }
    
    public static void addBook(Book book){
        bookUpdating = false;
        
        try {
            byte[] imageData = Utilities.serializeImage(book.getBookIcon());
            java.sql.Date datePublished = java.sql.Date.valueOf(book.getDatePublished());
        
            String queryRegister = "INSERT into book(bookID, title, author, categoryID, description, bookStatus, imageData, datePublished, lastUpdated, totalAmmount, ammountLeft, maxDaysAdminBorrowed, maxDaysUserBorrowed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            if (book.isImageDefault()) {
                imageData = null;
            }
            
            PreparedStatement st = con.prepareStatement(queryRegister);
            st.setString(1, book.getBookID());
            st.setString(2, book.getTitle());
            st.setString(3, book.getAuthor());
            st.setString(4, book.getCategoryID());
            st.setString(5, book.getDescription());
            st.setString(6, book.getBookStatus().name());
            st.setBytes(7, imageData);
            st.setDate(8,datePublished);
            st.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            st.setInt(10, book.getTotalAmmount());
            st.setInt(11, book.getAmmountLeft());
            st.setInt(12, book.getMaxDaysAdminBorrowed());
            st.setInt(13, book.getMaxDaysUserBorrowed());
            st.executeUpdate();
            
            booksList.add(book);
            OfflineHandler.saveBooksOffline(booksList);
            System.out.println("Book " + book.getTitle()+ " added successfully");
            
            JOptionPane.showMessageDialog(new JFrame(), "Book Added.","Book Adding",1);
        } catch (SQLException ex) {
            Logger.getLogger(BookHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), "Invalid Book","Error",0);
        } catch (IOException ex) {
            Logger.getLogger(BookHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (Category category : CategoryHandler.getCategoryList()) {
            if (category.getCategoryName().equals(book.getCategoryID())) {
                category.addBook();
                CategoryHandler.updateCategory(category);
                break;
            }
        }
        
        bookUpdating = false;
    }
    
    public static void updateBook(Book book, String oldID){
        bookUpdating = true;
        
        String query = "UPDATE book SET title = ?, author = ?, categoryID = ?, description = ?, bookStatus = ?, imageData = ?,maxDaysAdminBorrowed = ?, maxDaysUserBorrowed = ?, totalAmmount = ?, ammountLeft = ?, bookID = ? WHERE bookID = ?";
        
        
        
        try {
            byte[] imageData = null;
            if (!book.isImageDefault()) {
                imageData = Utilities.serializeImage(book.getBookIcon());
            }
            
            PreparedStatement st;
            st = con.prepareCall(query);
            st.setString(1, book.getTitle());
            st.setString(2, book.getAuthor());
            st.setString(3, book.getCategoryID());
            st.setString(4, book.getDescription());
            st.setString(5, book.getBookStatus().name());
            st.setBytes(6, imageData);
            st.setInt(7, book.getMaxDaysAdminBorrowed());
            st.setInt(8, book.getMaxDaysUserBorrowed());
            st.setInt(9, book.getTotalAmmount());
            st.setInt(10, book.getAmmountLeft());
            st.setString(11, book.getBookID());
            st.setString(12, oldID);
            
            st.executeUpdate();
            booksList.stream().filter(oldBook -> oldBook.getBookID().equals(oldID)).collect(Collectors.toList()).forEach(oldBook -> oldBook = book);
            OfflineHandler.saveBooksOffline(booksList);
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(BookHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (Book newBook : booksList) {
            if (newBook.getBookID().equals(oldID)) {
                newBook = book;
            }
        }
        
        System.out.println("Updated book " + oldID);
        LibrarySystem.updateData();
        bookUpdating = false;
    }
    
    public static void removeBook(Book book){
        bookUpdating = true;

        String queryRegister = "DELETE FROM book WHERE bookID = '" + book.getBookID()+ "'";
        try {
            PreparedStatement st = con.prepareStatement(queryRegister);
            st.executeUpdate();
            
            booksList.remove(book);
            OfflineHandler.saveBooksOffline(booksList);
            
            System.out.println("Book " + book.getTitle() + " removed");
        } catch (SQLException ex) {
            Logger.getLogger(BookHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), "Invalid Book","Error",0);
        }
        
        bookUpdating = false;
    }
    
    private static void loadBooksOnline(){
        bookUpdating = true;
        
        try {
            String query = "SELECT * FROM book";
            ResultSet resultSet = con.createStatement().executeQuery(query);
            
            while (resultSet.next()) {
                booksList.add(getBook(resultSet));
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(BookHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bookUpdating = false;
    }
    
    private static Book getBook(ResultSet rs) throws SQLException, IOException{
        String bookID = rs.getString("bookID");
        String title = rs.getString("title");
        String author = rs.getString("author");
        String categoryID = rs.getString("categoryID");
        String description = rs.getString("description");
        Book.Availability availability = Book.Availability.UNAVAILABLE;
        for (Book.Availability type : Book.Availability.values()) {
            if (type.name().equals(rs.getString("bookStatus"))) {
                availability = type;
            }
        }
        byte[] imgArray = rs.getBytes("imageData");
        LocalDate datePublished = rs.getDate("datePublished").toLocalDate();
        Timestamp lastUpdated = rs.getTimestamp("lastUpdated");
        int maxDaysAdminBorrowed = rs.getInt("maxDaysAdminBorrowed");
        int maxDaysUserBorrowed = rs.getInt("maxDaysUserBorrowed");
        int totalAmmount = rs.getInt("totalAmmount");
        int ammountLeft = rs.getInt("ammountLeft");
        System.out.println(lastUpdated);
        
        Image img;
        boolean isDefaultImage = imgArray == null;
        if (isDefaultImage) {
            img = Icons.noImageIcon.getImage();
            img = Utilities.changeImageColor(img, PalleteColors.DROPDOWN);
        }
        else img = Utilities.deserializeImage(imgArray);

        return new Book(img,bookID, title, author, categoryID, description, availability,datePublished,lastUpdated,maxDaysAdminBorrowed ,maxDaysUserBorrowed,totalAmmount,ammountLeft,isDefaultImage);
    }
    
    public static boolean hasBooksUpdated(){
        boolean hasBooksUpdated = false;
        bookUpdating = true;
        try {
            for (int i = 0; i < booksList.size(); i++) {
                Book book = booksList.get(i);
                String query = "SELECT lastUpdated FROM book WHERE bookID = '" + book.getBookID() + "'";
                ResultSet resultSet = con.createStatement().executeQuery(query);

                while (resultSet.next()) {
                    if (!book.getLastUpdated().equals(resultSet.getTimestamp("lastUpdated"))) {
                        String getBook = "SELECT * FROM book WHERE bookID = '" + book.getBookID() + "'";
                        ResultSet getBookResult = con.createStatement().executeQuery(getBook);
                        getBookResult.next();

                        booksList.set(i, getBook(getBookResult));
                        System.out.println("Book "+ getBookResult.getString("title") + " Changed");
                        hasBooksUpdated = true;
                    }
                }
            }
            
            String query = "SELECT bookID FROM book";
            ResultSet resultSet = con.createStatement().executeQuery(query);
            
            ArrayList<String> cacheBookIDs = new ArrayList<>();
            for (Book book : booksList) {
                cacheBookIDs.add(book.getBookID());
            }
            
            ArrayList<String> serverBookIDs = new ArrayList<>();
            while (resultSet.next()){
                serverBookIDs.add(resultSet.getString("bookID"));
            }
            
            if (booksList.removeIf(book -> !serverBookIDs.contains(book.getBookID()))) {
                hasBooksUpdated = true;
                System.out.println("Book Removed");
            }
            
            if (serverBookIDs.size() > booksList.size()) {
                for (String id : serverBookIDs) {
                    if (!cacheBookIDs.contains(id)) {
                        String getBook = "SELECT * FROM book WHERE bookID = '" + id + "'";
                        ResultSet getBookResult = con.createStatement().executeQuery(getBook);
                        getBookResult.next();
                        
                        booksList.add(getBook(getBookResult));
                        hasBooksUpdated = true;
                        System.out.println("Book "+ getBookResult.getString("title") + " Added");
                    }
                }
            }
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(BookHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bookUpdating = false;
        
        if (hasBooksUpdated) {
            System.out.println("Books updated");
        }
        return hasBooksUpdated;
    }
    
    public static void addStandardBooks() {
        Image lor = Utilities.getImage("/textures/lor.png").getImage();
        Image aot = Utilities.getImage("/textures/aot.png").getImage();
        LocalDate lorDate = LocalDate.of(1954, Month.JULY, 29);
        LocalDate aotDate = LocalDate.of(2009, Month.SEPTEMBER, 9);
        String description = "The Fellowship of the Ring is the first of three volumes in The Lord of the Rings, an epic set in the fictional world of Middle-earth. The Lord of the Rings is an entity named Sauron, the Dark Lord, who long ago lost the One Ring that contains much of his power. His overriding desire is to reclaim the Ring and use it to enslave all of Middle-earth.";
        Book t = new Book(lor, "The Fellowship of the Ring", "The Fellowship of the Ring", "J.R.R Tolkien", "800", description, Book.Availability.AVAILABLE, lorDate, Timestamp.valueOf(LocalDateTime.now()),10, 10, 10, 10,false);
        booksList.add(t);
        String description2 = "Centuries ago, mankind was slaughtered to near extinction by monstrous humanoid creatures called Titans, forcing humans to hide in fear behind enormous concentric walls. What makes these giants truly terrifying is that their taste for human flesh is not born out of hunger but what appears to be out of pleasure. To ensure their survival, the remnants of humanity began living within defensive barriers, resulting in one hundred years without a single titan encounter. However, that fragile calm is soon shattered when a colossal Titan manages to breach the supposedly impregnable outer wall, reigniting the fight for survival against the man-eating abominations.\n\nAfter witnessing a horrific personal loss at the hands of the invading creatures, Eren Yeager dedicates his life to their eradication by enlisting into the Survey Corps, an elite military unit that combats the merciless humanoids outside the protection of the walls. Eren, his adopted sister Mikasa Ackerman, and his childhood friend Armin Arlert join the brutal war against the Titans and race to discover a way of defeating them before the last walls are breached.\n";
        Book test2 = new Book(aot, "Attack on Titan", "Attack on Titan", "Hajime Isayama", "200", description2, Book.Availability.AVAILABLE, aotDate, Timestamp.valueOf(LocalDateTime.now()),10, 10, 10, 10,false);
        booksList.add(test2);
        CategoryHandler.getCategoryList().get(2).addBook();
        CategoryHandler.getCategoryList().get(8).addBook();
        for (Book book : booksList) {
            addBook(book);
        }
    }
    
    public static Book getBook(String bookID){
        for (Book book : booksList) {
            if (book.getBookID().equals(bookID)) {
                return book;
            }
        }
        return null;
    }

    public static boolean isBookUpdating() {
        return bookUpdating;
    }

    public static ArrayList<Book> getBooksList() {
        return booksList;
    }
    
    public static int getBookCount(){
        return booksList.size();
    }
    
    public static boolean hasStarted() {
        return hasStarted;
    }
    
}
