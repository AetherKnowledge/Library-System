
package com.librarysystem.objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class User implements Serializable{

    //pede dito gawing admin or something
    private final UserType userType;
    private transient Image icon;
    private byte[] imageData;
    private String email;
    private String password;
    private String studentNumber;
    private String fullName;
    private final LocalDateTime dateJoined;
    private final LocalDateTime lastUpdated;
    
    public User(UserType userType, String email, String password,String fullName,String studentNumber, Image icon, LocalDateTime dateJoined, LocalDateTime lastUpdated){
        this.dateJoined = dateJoined;
        this.userType = userType;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.studentNumber = studentNumber;
        this.lastUpdated = lastUpdated;
        
        try{
            imageData = serializeImage(icon);
        }
        catch(IOException e){Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);}
    }
    
    private byte[] serializeImage(Image image) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        return baos.toByteArray();
    }
    
    private Image deserializeImage(byte[] imageData) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        return ImageIO.read(bais);
    }

    public UserType getUserType() {
        return userType;
    }

    public String getUserName() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public Image getIcon() {
        if (icon == null) {
            try {this.icon = deserializeImage(imageData);}
            catch (IOException ex) {Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);}
        }
        
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }
    
    public byte[] getImageData() {
        return imageData;
    }
    
    public LocalDateTime getDateJoined() {
        return dateJoined;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    
    public enum UserType{
        ADMIN,USER;
    }
    
}
