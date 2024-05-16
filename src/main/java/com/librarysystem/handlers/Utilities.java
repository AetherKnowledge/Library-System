
package com.librarysystem.handlers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.librarysystem.Frame;
import com.librarysystem.LibrarySystem;
import com.librarysystem.objects.Book;
import com.librarysystem.objects.Category;
import com.librarysystem.objects.IssuedBook;
import com.librarysystem.objects.IssuedBook.BorrowedBookStatus;
import com.librarysystem.objects.User;
import com.librarysystem.objects.ui.PalleteColors;
import com.librarysystem.panels.account.UserPanel;
import com.librarysystem.panels.books.BookPanel;
import com.librarysystem.panels.books.IssuedBooksPanel;
import com.librarysystem.panels.category.CategoryPanel;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.RenderingHints;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import org.apache.commons.validator.routines.EmailValidator;

public class Utilities {
    
    public static <T> byte[] serializeObj(T obj) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    public static Object deserializeObj(byte[] bytes) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }     
    
    public static byte[] serializeImage(Image image) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        return baos.toByteArray();
    }
    
    public static Image deserializeImage(byte[] imageData) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        return ImageIO.read(bais);
    }

    public final static boolean verifyPassword(String fromDB, char[] input) {
        return BCrypt.verifyer().verify(input, fromDB.toCharArray()).verified;
    }

    public final static String toBcrypt(char[] password) {
        return BCrypt.withDefaults().hashToString(12, password);
    }
    
    public final static String toSHA256(InputStream stream) throws NoSuchAlgorithmException, IOException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        DigestInputStream digestInputStream = new DigestInputStream(stream, md);
        while(digestInputStream.read() != -1){}
        byte[] hashBytes = md.digest();
        
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        
        return hexString.toString();
    }
    
    public static ArrayList<UserPanel> makeUserPanels(){
        ArrayList<UserPanel> userPanels = new ArrayList<>();
        for (User user : UserHandler.getUsersList()) {
            userPanels.add(new UserPanel(user));
        }
        return userPanels;
    }
    
    public static ArrayList<CategoryPanel> makeCategoryPanels(){
        ArrayList<CategoryPanel> categoryPanel = new ArrayList<>();
        for (Category category : CategoryHandler.getCategoryList()) {
            categoryPanel.add(new CategoryPanel(category));
        }
        return categoryPanel;
    }
    
    public static ArrayList<IssuedBooksPanel> makeIssuedBooksPanel(){
        ArrayList<IssuedBooksPanel> issuedBooksPanel = new ArrayList<>();
        for (IssuedBook book : IssuedBooksHandler.getIssuedBooksList()) {
            if (book.getStatus() != IssuedBook.BorrowedBookStatus.RETURNED) {
                issuedBooksPanel.add(new IssuedBooksPanel(book));
            }
        }
        return issuedBooksPanel;
    }
    
    public static ArrayList<IssuedBooksPanel> makeReturnedBooksPanel(){
        ArrayList<IssuedBooksPanel> returnedBooksPanel = new ArrayList<>();
        for (IssuedBook book : IssuedBooksHandler.getIssuedBooksList()) {
            if (book.getStatus() == IssuedBook.BorrowedBookStatus.RETURNED) {
                returnedBooksPanel.add(new IssuedBooksPanel(book));
            }
        }
        return returnedBooksPanel;
    }
    
    public static ArrayList<IssuedBooksPanel> makeUsersReturnedBooksPanel(User user){
        ArrayList<IssuedBooksPanel> returnedBooksPanel = new ArrayList<>();
        for (IssuedBook book : IssuedBooksHandler.getIssuedBooksList()) {
            if (book.getStatus() == IssuedBook.BorrowedBookStatus.RETURNED && book.getEmail().equals(user.getEmail())) {
                returnedBooksPanel.add(new IssuedBooksPanel(book));
            }
        }
        return returnedBooksPanel;
    }
    
    public static ArrayList<BookPanel> makeBookPanels(){
        ArrayList<BookPanel> bookPanels = new ArrayList<>();
        for (Book book : BookHandler.getBooksList()) {
            bookPanels.add(new BookPanel(book));
        }
        return bookPanels;
    }
    
    public static List<BookPanel> getUserBooksPanel(User user){
        
        List<String> userBookIDs = IssuedBooksHandler.getIssuedBooksList().stream()
                                .filter(book -> book.getEmail().equals(user.getEmail()) && book.getStatus() != BorrowedBookStatus.RETURNED)
                                .map(IssuedBook::getBookID)
                                .collect(Collectors.toList());
        
        List<String> invalidIds = userBookIDs.stream()
                                .filter(id -> !BookHandler.getBooksList().stream().anyMatch(book -> book.getBookID().equals(id)))
                                .collect(Collectors.toList());
        
        List<BookPanel> bookPanels = BookHandler.getBooksList().stream()
                        .filter(book -> userBookIDs.contains(book.getBookID()))
                        .map(BookPanel::new)
                        .collect(Collectors.toList());
        
        invalidIds.stream().forEach(bookID -> {
            System.out.println("Invalid Book " + bookID);
            IssuedBooksHandler.getIssuedBooksList().removeIf(book -> book.getBookID().equals(bookID) && book.getEmail().equals(user.getEmail()));
        });

        return bookPanels;
    }
    
    public static Image rescaleImage(Image image, int newWidth){
        
        int originalWidth = image.getWidth(null);
        int originalHeight = image.getHeight(null);
        
        double aspectRatio = (double) originalWidth / originalHeight;
        int newHeight = (int) (newWidth / aspectRatio);
        Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        
        return scaledImage;
    }
    
    public static void testImage(Image image){
        JFrame fr = new JFrame("File Chooser Example");
        fr.setLayout(new BorderLayout());
        
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(image));
        label.setBounds(0, 0, 127, 127);
        fr.add(label);
        
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);
    
    }

    public static ImageIcon getImage(String location) {
        return new ImageIcon(Frame.class.getResource(location));
    }
    
    
    @Deprecated //this shit takes so much memory lmao
    public static Image changeImageColorOld(Image image, Color toColor) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        
        BufferedImage drawImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = drawImage.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();
        
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = toColor.getRGB();
                int alpha = (drawImage.getRGB(x, y) >> 24) & 255; // Alpha channel value
                int red = (rgb >> 16) & 255;
                int green = (rgb >> 8) & 255;
                int blue = rgb & 255;
                int newRGB = (alpha << 24) | (red << 16) | (green << 8) | blue;
                bufferedImage.setRGB(x, y, newRGB);
            }
        }
        
        Image newImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return newImage;
    }
    
    public static Image changeImageColor(Image image, Color toColor) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        int[] pixels = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
        int toRGB = toColor.getRGB();
        int toRed = (toRGB >> 16) & 0xFF;
        int toGreen = (toRGB >> 8) & 0xFF;
        int toBlue = toRGB & 0xFF;

        for (int i = 0; i < pixels.length; i++) {
            int pixel = pixels[i];
            int alpha = (pixel >> 24) & 0xFF;
            int red = (pixel >> 16) & 0xFF;
            int green = (pixel >> 8) & 0xFF;
            int blue = pixel & 0xFF;

            int newRed = (toRed * alpha + red * (255 - alpha)) / 255;
            int newGreen = (toGreen * alpha + green * (255 - alpha)) / 255;
            int newBlue = (toBlue * alpha + blue * (255 - alpha)) / 255;

            pixels[i] = (alpha << 24) | (newRed << 16) | (newGreen << 8) | newBlue;
        }
        
        return bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public static void addChangePointer(JComponent component) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                component.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                component.setCursor(Cursor.getDefaultCursor());
            }
        });
    }
    
    public static boolean containsSpecial(String password){
        
        for (char ch : password.toCharArray()) {
            if (!Character.isAlphabetic(ch)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isEmailValid(String email){
        if (!EmailValidator.getInstance().isValid(email)) {
            return false;
        }
        
        String[] parts = email.split("@");
        String domain = parts[1];
        try {
            InetAddress address = InetAddress.getByName(domain);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }
    
    public static Image createUserLogo(char character){
        int width = 256;
        int height = 256;
        Color bgColor = PalleteColors.SIDEBAR_MAIN_COLOR;
        Color letterColor = Color.WHITE;
        Font font = new Font("Roboto",Font.BOLD,(int)(width*0.8));
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = image.createGraphics();
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2D.setColor(bgColor);
        g2D.fillRect(0, 0, width, width);
        
        g2D.setColor(letterColor);
        g2D.setFont(font);
        
        FontMetrics fm = g2D.getFontMetrics();
        int charWidth = fm.charWidth(character);
        int charHeight = fm.getHeight();

        int x = (width - charWidth) / 2;
        int y = ((height - charHeight) / 2) + fm.getAscent();
        
        g2D.drawChars(new char[]{character}, 0, 1, x, y);
        
        return new ImageIcon(image).getImage();
    }
    
}
