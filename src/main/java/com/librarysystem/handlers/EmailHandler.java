
package com.librarysystem.handlers;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailHandler {
    
    private static final String FROM = "noreply.librarysystem.app@gmail.com";
    private static final String PASSWORD = "qeqh qhnb uaac oimz";
    private static final String HOST = "smtp.gmail.com";
    private static Properties properties;
    
    public static void sendEmail(String to, String code){
        
        if (properties == null) {
            properties = System.getProperties();
            properties.setProperty("mail.smtp.host", HOST);
            properties.setProperty("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.smtp.starttls.enable", "true");
        }
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        });
            
        try {
            // Create a default MimeMessage object
            MimeMessage mimeMessage = new MimeMessage(session);

            // Set From: header field of the header
            mimeMessage.setFrom(new InternetAddress(FROM));

            // Set To: header field of the header
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            mimeMessage.setSubject("Forgot Password");
            
            // Now set the actual message
            String message = "Reset Key : " + code;
            mimeMessage.setText(message);

            // Send message
            Transport.send(mimeMessage);
            System.out.println("Sent message successfully...");
            return;
        }
        catch (AddressException ex) {
            Logger.getLogger(EmailHandler.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (MessagingException ex) {
            Logger.getLogger(EmailHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Sent message failed...");
    }
    
    
}
