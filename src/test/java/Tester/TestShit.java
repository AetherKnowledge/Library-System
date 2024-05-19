
package Tester;

import java.sql.SQLException;
import java.time.LocalDateTime;
import com.librarysystem.handlers.ConnectionHandler;
import com.librarysystem.handlers.IssuedBooksHandler;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.objects.IssuedBook;
import com.librarysystem.objects.buttons.ImageButton;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class TestShit {
    
    private static final ConnectionHandler sql = new ConnectionHandler();
    
    public static void main(String[] args) throws SQLException {
//        sql.startConnection();
//        IssuedBooksHandler.startManager(sql.getConnection());
//        removeAll();
        ImageIcon ayaya = Utilities.getImage("/textures/ayaya.png");
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(1280, 720);
        ImageButton button = new ImageButton(ayaya.getImage());
        button.setBounds(100, 100, 45, 45);
        frame.add(button);
        
        frame.setVisible(true);
    }
    
    public static void makeOverDue(){
        IssuedBook borrowedBook = new IssuedBook("user","wew", "wew", Timestamp.valueOf(LocalDateTime.now().minusDays(3)),null, 1,IssuedBook.BorrowedBookStatus.BORROWED,LocalDateTime.now());
        IssuedBooksHandler.addIssuedBook(borrowedBook);
    }
    
    public static void returnedTest(){
        IssuedBook borrowedBook = new IssuedBook("therumbling@gmail.com","Attack on Titan", "Attack on Titan", Timestamp.valueOf(LocalDateTime.now().minusDays(3)),LocalDateTime.now(), 1,IssuedBook.BorrowedBookStatus.RETURNED,LocalDateTime.now());
        IssuedBooksHandler.addIssuedBook(borrowedBook);
    }
    
    public static void removeTest(){
        IssuedBook borrowedBook = new IssuedBook("user","The Fellowship of the Ring", "The Fellowship of the Ring", Timestamp.valueOf(LocalDateTime.now().minusDays(3)),null, 1,IssuedBook.BorrowedBookStatus.BORROWED,LocalDateTime.now());
        IssuedBooksHandler.removeIssuedBook(borrowedBook);
    }
    
    public static void removeAll() throws SQLException{
        String deleteAll = "DELETE FROM issuedbook";
        Statement st = sql.getConnection().createStatement();
        st.execute(deleteAll);
    }
}
