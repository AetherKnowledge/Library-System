
package com.librarysystem.objects;

import java.io.Serializable;
import java.time.LocalDateTime;

public class IssuedBook implements Serializable{

    public LocalDateTime getDateReturned() {
        return dateReturned;
    }
    
    private final String email;
    private final String bookID;
    private final String bookName;
    private final LocalDateTime dateBorrowed;
    private final LocalDateTime dateReturned;
    private final int borrowDuration;
    private BorrowedBookStatus status;
    private final LocalDateTime lastUpdated;
    
    public IssuedBook(String email, String bookID, String bookName, LocalDateTime dateBorrowed, LocalDateTime dateReturned, int borrowDuration, BorrowedBookStatus status, LocalDateTime lastUpdated){
        this.email = email;
        this.bookName = bookName;
        this.bookID = bookID;
        this.status = status;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = dateReturned;
        this.borrowDuration = borrowDuration;
        this.lastUpdated = lastUpdated;
    }
    
    @Override
    public String toString(){
        String str = "\n email : " + email;
        str += "\n bookID : " + bookID;
        str += "\n bookName : " + bookName;
        str += "\n dateBorrowed : " + dateBorrowed;
        str += "\n borrowDuration : " + borrowDuration;
        str += "\n status : " + status + "\n";
        
        return str;
    }
    
    public String getEmail() {
        return email;
    }

    public String getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public LocalDateTime getDateBorrowed() {
        return dateBorrowed;
    }

    public int getBorrowDuration() {
        return borrowDuration;
    }

    public BorrowedBookStatus getStatus() {
        return status;
    }

    public void setStatus(BorrowedBookStatus status) {
        this.status = status;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public enum BorrowedBookStatus{
        BORROWED("Borrowed"),PENDING("Pending Return"),RETURNED("Returned"),OVERDUE("Overdue");
        
        public String text;
        BorrowedBookStatus(String text){
            this.text = text;
        }
    }
    
}
