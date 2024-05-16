
package com.librarysystem.objects;
    
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GraphData implements Serializable{
    
    private final LocalDate date;
    private final int totalBooksCount;
    private final int totalUsersCount;
    private final int issuedBooksCount;
    private final int returnedBooksCount;
    private final LocalDateTime lastUpdated;
    
    public GraphData(LocalDate date, int totalBooksCount, int totalUsersCount, int issuedBooksCount, int returnedBooksCount, LocalDateTime lastUpdated){
        this.date = date;
        this.totalBooksCount = totalBooksCount;
        this.totalUsersCount = totalUsersCount;
        this.issuedBooksCount = issuedBooksCount;
        this.returnedBooksCount = returnedBooksCount;
        this.lastUpdated = lastUpdated;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTotalBooksCount() {
        return totalBooksCount;
    }

    public int getTotalUsersCount() {
        return totalUsersCount;
    }

    public int getIssuedBooksCount() {
        return issuedBooksCount;
    }

    public int getReturnedBooksCount() {
        return returnedBooksCount;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
            
}
