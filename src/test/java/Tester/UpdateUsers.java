
package Tester;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.librarysystem.LibrarySystem;
import com.librarysystem.objects.Book;
import com.librarysystem.objects.User;
import java.sql.*;
import com.librarysystem.handlers.ConnectionHandler;
import com.librarysystem.handlers.UserHandler;
import com.librarysystem.handlers.Utilities;

public class UpdateUsers {
    
    private static ArrayList<User> USERS = new ArrayList<>();
    private static final String BOOKS_PATH = "books.sav";
    private static final ConnectionHandler sql = new ConnectionHandler();
    private static UserHandler userManager;

    public static void main(String[] args) throws SQLException, IOException {
        sql.startConnection();
        userManager.startManager(sql.getConnection());
        USERS = UserHandler.getUsersList();
        passToBCrypt();
    }
    
        
    public static void passToBCrypt(){
        try {
            for (User user : USERS) {
                String updateBooks = "UPDATE user SET password = ? WHERE email = ?";
                PreparedStatement st = sql.getConnection().prepareCall(updateBooks);
                st.setString(1, Utilities.toBcrypt(user.getPassword().toCharArray()));
                st.setString(2, user.getEmail());
                st.executeUpdate();
            }
            
            System.out.println("Complete");
            
        } catch (SQLException ex) {
            Logger.getLogger(UpdateUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void passToNormal(){
        try {
            for (User user : USERS) {
                String updateBooks = "UPDATE user SET password = ? WHERE email = ?";
                PreparedStatement st = sql.getConnection().prepareCall(updateBooks);
                st.setString(1, Utilities.toBcrypt(user.getPassword().toCharArray()));
                st.setString(2, user.getEmail());
                st.executeUpdate();
            }
            
            System.out.println("Complete");
            
        } catch (SQLException ex) {
            Logger.getLogger(UpdateUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private static void loadBooksOffline(){
        
        System.out.println("Loading Books Offline...");
        try (FileInputStream fileInputStream = new FileInputStream(BOOKS_PATH)) {
            ObjectInputStream objOutStream = new ObjectInputStream(fileInputStream);
            
            ArrayList<Book> cacheBooks = new ArrayList<>();
            if (objOutStream.readObject() instanceof ArrayList<?> objects) {
                for (Object object : objects) {
                    if (object instanceof Book book) {
                        cacheBooks.add(book);
                    }
                    else throw new IOException("Invalid Book Object");
                }
            }
            else throw new IOException("Invalid Book Cache");
//            BOOKS = cacheBooks;
            
            System.out.println("Loading Books Cache Success.");
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Books cache not found.");
        } 
        catch (IOException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Books Cache Corrupted.");
        }
        
    }
    
}
