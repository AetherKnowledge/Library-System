
package com.librarysystem;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.librarysystem.objects.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import com.librarysystem.objects.components.CustomScrollBar;
import javax.swing.SwingUtilities;
import com.librarysystem.handlers.BookHandler;
import com.librarysystem.handlers.CategoryHandler;
import com.librarysystem.handlers.ConnectionHandler;
import com.librarysystem.handlers.GraphHandler;
import com.librarysystem.handlers.IssuedBooksHandler;
import com.librarysystem.handlers.ObjectHandler;
import com.librarysystem.handlers.OfflineHandler;
import com.librarysystem.handlers.UserHandler;
import com.librarysystem.panels.AdminDashboard;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LibrarySystem{

    private static Frame frame;
    private static final Timer timer = new Timer(10000,l -> {checkSQLUpdates();});
    private static boolean updating = false;
    
    private static final ConnectionHandler sql = new ConnectionHandler();
        
    private static final Object lock = new Object();
    private static boolean connected = false;
    
    public static void main(String[] args) throws SQLException, IOException {
        UIManager.put("ScrollBarUI", CustomScrollBar.class.getName());
        UIManager.put("ComboBox.disabledForeground", new Color(145,145,145));
        UIManager.put("ComboBox.disabledBackground", Color.WHITE);
        UIManager.put("TextField.inactiveBackground", Color.WHITE);
        
        Thread startConnectionThread = new Thread(() -> {
            try{
                updating = true;
                sql.startConnection();
                loadObjects();
                updating = false;
                
                synchronized (lock){
                    connected = true;
                    lock.notify();
                }
                
            }
            catch (SQLException e){
                Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, e);
                loadOffline();
            }
        });
        startConnectionThread.start();
        
        frame = new Frame();
        setupFrame();

        timer.start();
        
    }
    
    private static void loadOffline(){
        try {
            throw new Exception("Cannot connect to database");
        } catch (Exception ex) {
            Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
    }
    
    private static final Object timeLock = new Object();
    private static void loadObjects(){
        
        long startTime = System.currentTimeMillis();
        
        try (ExecutorService executor = Executors.newFixedThreadPool(5)) {
            executor.submit(() -> startManager((new UserHandler())));
            executor.submit(() -> startManager((new CategoryHandler())));
            executor.submit(() -> startManager((new BookHandler())));
            executor.submit(() -> startManager((new IssuedBooksHandler())));
            executor.submit(() -> startManager((new GraphHandler())));
            
            synchronized (timeLock) {
                while(!UserHandler.hasStarted() || !BookHandler.hasStarted() || !CategoryHandler.hasStarted() || !IssuedBooksHandler.hasStarted() || !GraphHandler.hasStarted()){
                    try{
                        timeLock.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("\nTime taken to connect : " + (endTime - startTime));
    }
    
    private static void startManager(ObjectHandler handler){
        handler.startManager(sql.getConnection());
        synchronized(timeLock){
            timeLock.notify();
        }
    }
    
    private static void setupFrame(){
        frame.setSize(1320, 740);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent et){
                OfflineHandler.saveUsersOffline(UserHandler.getUsersList());
                OfflineHandler.saveBooksOffline(BookHandler.getBooksList());
                OfflineHandler.saveCategoriesOffline(CategoryHandler.getCategoryList());
                OfflineHandler.saveIssuedBooksOffline(IssuedBooksHandler.getIssuedBooksList());
                OfflineHandler.saveGraphDataOffline(GraphHandler.getGraphData());
            }
        });
        
        frame.addWindowStateListener((WindowEvent e) -> {
            if ((e.getNewState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                frame.resize();
            } 
            else if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
                Frame.removePopup();
            }
            else {
                frame.resize();
            }
        });
        
        //frame.setResizable(false);
        frame.setVisible(true);
        
    }
    
    public static void changeFrameSize(int width,int height){
        frame.setResizable(true);
        frame.setSize(width, height);
        //frame.setResizable(false);
    }  
    
    private static int onlineCount = 0;
    public static void checkSQLUpdates(){
        
        System.out.println("Active Threads : " + Thread.activeCount());
//        Thread updateOnlineStatus = new Thread(()->{
//            
//        });
//        updateOnlineStatus.start();
//        
        if (updating || UserHandler.isUsersUpdating() || BookHandler.isBookUpdating() || CategoryHandler.isCategoryUpdating() || IssuedBooksHandler.isIssuedBooksUpdating() || GraphHandler.isGraphUpdating()) {
            System.out.println("Updating data please wait");
            return;
        }
        
        Thread sqlUpdateThread = new Thread(() -> {
            updating = true;
            
            if (UserHandler.getCurrentUser() != null) {
                UserHandler.updateOnlineStatus(UserHandler.getCurrentUser());
                System.out.println(UserHandler.getCurrentUser().getFullName() + " is Online.");
            }
            
            System.out.println("Checking database if it updated");
            boolean hasUpdated = false;
            if (BookHandler.hasBooksUpdated()) hasUpdated = true;
            if (CategoryHandler.hasCategoriesUpdated()) hasUpdated = true;
            if (IssuedBooksHandler.hasIssuedBookUpdated()) hasUpdated = true;
            if (UserHandler.hasUsersUpdated()) hasUpdated = true;
            if (GraphHandler.hasGraphUpdated()) hasUpdated = true;
            
            boolean usersUpdated = false;
            if (UserHandler.updateAllOnlineUsers()) usersUpdated = true;
            int newOnlineCount = UserHandler.getOnlineCount();
            if (onlineCount != newOnlineCount) {
                onlineCount = newOnlineCount;
                usersUpdated = true;
                System.out.println("Online Count Changed");
            }
            if (usersUpdated && Frame.getCurrentPanel() instanceof AdminDashboard) {
                updateData();
            }
            
            if (hasUpdated) {
                System.out.print("Data Changed \n");
                updateData();
            }
            updating = false;
        });
        sqlUpdateThread.start();
    }
    
    public static void updateData(){
        SwingUtilities.invokeLater(() -> frame.update());
    }  
    
    public static Object getLock() {
        return lock;
    }
    
    public static boolean isConnected() {
        return connected;
    }
    
    public static void changeUser(User user){
        frame.changeSideBar(user.getUserType());
        frame.changeUser(user);
    }
    
}
