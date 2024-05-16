
package Tester;

import javax.swing.JFrame;
import com.librarysystem.panels.books.BookList;

public class TestPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        BookList bookList = new BookList();
        
        frame.add(bookList);
        frame.setSize(980, 630);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    
}
