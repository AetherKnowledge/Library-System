
package com.librarysystem.objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.sql.Timestamp;

public class Book implements Serializable{

    private transient Image bookIcon;
    private byte[] imageData;
    private String bookID;
    private String title;
    private String author;
    private String categoryID;
    private String description;
    private Availability bookStatus;
    private LocalDate datePublished;
    private int maxDaysUserBorrowed;
    private int maxDaysAdminBorrowed;
    private int totalAmmount;
    private int ammountLeft;
    private boolean isImageDefault;
    
    private Timestamp lastUpdated;
    
    public Book(Image bookIcon,
            String bookID,
            String title,
            String author,
            String categoryID,
            String description,
            Availability bookStatus,
            LocalDate datePublished,
            Timestamp lastUpdated,
            int maxDaysAdminBorrowed,
            int maxDaysUserBorrowed,
            int totalAmmount,
            int ammountLeft,
            boolean isImageDefault
            ){
        
        this.bookID = bookID;
        this.bookIcon = bookIcon;
        this.title = title;
        this.author = author;
        this.categoryID = categoryID;
        this.description = description;
        this.bookStatus = bookStatus;
        this.datePublished = datePublished;
        this.lastUpdated = lastUpdated;
        this.maxDaysAdminBorrowed = maxDaysAdminBorrowed;
        this.maxDaysUserBorrowed = maxDaysUserBorrowed;
        this.totalAmmount = totalAmmount;
        this.ammountLeft = ammountLeft;
        this.isImageDefault = isImageDefault;
        
        try{
            imageData = serializeImage(bookIcon);
        }
        catch(IOException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public String toString(){
        String text = "\n";
        text += "BookID = " + bookID + "\n";
        text += "Title = " + title + "\n";
        text += "Author = " + author;
        text += "Category ID = " + categoryID + "\n";
        text += "Description = " + description + "\n";
        text += "Book Status = " + bookStatus + "\n";
        text += "Date Published = " + datePublished + "\n";
        text += "Last Updated = " + lastUpdated + "\n";
        
        return text;
    }
    
    private byte[] serializeImage(Image image) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        return baos.toByteArray();
    }
    
    private Image deserializeImage(byte[] imageData) throws IOException, NoSuchAlgorithmException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        return ImageIO.read(bais);
    }
    
    public Image getBookIcon() {
        if (bookIcon == null) {
            try {this.bookIcon = deserializeImage(imageData);}
            catch (IOException ex) {Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);} catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return bookIcon;
    }

    public String getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getDescription() {
        return description;
    }

    public Availability getBookStatus() {
        return bookStatus;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public int getMaxDaysUserBorrowed() {
        return maxDaysUserBorrowed;
    }
    
    public int getMaxDaysAdminBorrowed() {
        return maxDaysAdminBorrowed;
    }

    public int getTotalAmmount() {
        return totalAmmount;
    }

    public int getAmmountLeft() {
        return ammountLeft;
    }
    
    public void removeAmmountLeft(int num){
        ammountLeft -= num;
    }
    
    public void addAmmountLeft(int num){
        ammountLeft += num;
    }

    public boolean isImageDefault() {
        return isImageDefault;
    }

    public void setIsDefaultImage(boolean isDefaultImage) {
        this.isImageDefault = isDefaultImage;
    }
    
    public enum Availability{
        AVAILABLE,UNAVAILABLE;
    }
}
