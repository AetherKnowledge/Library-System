
package com.librarysystem;

import com.librarysystem.handlers.ConnectionHandler;
import com.librarysystem.panels.Sidebar;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.objects.Book;
import com.librarysystem.objects.Category;
import com.librarysystem.panels.popups.Dropdown;
import com.librarysystem.objects.buttons.ImageButton;
import com.librarysystem.objects.ui.PalleteColors;
import com.librarysystem.objects.User;
import com.librarysystem.panels.popups.EditBookPopup;
import com.librarysystem.panels.AdminDashboard;
import com.librarysystem.panels.ClientDashboard;
import com.librarysystem.panels.account.Members;
import com.librarysystem.panels.MyPanel;
import com.librarysystem.panels.account.Entry;
import com.librarysystem.panels.books.AddBook;
import com.librarysystem.panels.books.BookList;
import com.librarysystem.panels.books.History;
import com.librarysystem.panels.books.IssuedBooks;
import com.librarysystem.panels.books.MyBookList;
import com.librarysystem.panels.books.ReturnedBooks;
import com.librarysystem.panels.category.AddCategory;
import com.librarysystem.panels.category.CategoryList;
import com.librarysystem.panels.popups.ChangeCategoryNamePopup;
import com.librarysystem.panels.popups.BookDetailsPopup;
import com.librarysystem.panels.popups.ForgetPasswordPopup;
import com.librarysystem.panels.popups.OptionsPopup;
import com.librarysystem.panels.popups.UserDetailsPopup;

public class Frame extends JFrame implements ComponentListener{
    
    private static final Image icon = Utilities.getImage("/textures/readingLogo.png").getImage();
    private static Image toggleSidebarIcon = Utilities.getImage("/textures/toggleSidebar.png").getImage();
    private static final CardLayout cardlayout = new CardLayout();
    private static final JPanel framePanel = new JPanel();
    private static JFrame me;
    
    private static boolean panelsLoaded = false;
    
    private static Entry entryPanel;
    private static AdminDashboard adminDashboard;
    private static BookList bookListPanel;
    private static AddBook addBook;
    private static ClientDashboard clientDasboard;
    private static Members members;
    private static CategoryList categoryList;
    private static AddCategory addCategory;
    private static MyBookList myBookList;
    private static IssuedBooks issuedBooksList;
    private static ReturnedBooks returnedBooks;
    private static History history;
    private static MyPanel currentPanel;
    
    private static Dropdown dropdown;
    private static Sidebar sideBar;

    private static final JLayeredPane layeredPane = new JLayeredPane();
    private static final JPanel topPanel = new JPanel();
    private static final JPanel bottomPanel = new JPanel();
    private static final JPanel mainPanel = new JPanel();
    private static final ImageButton imageButton = new ImageButton(Utilities.getImage("/textures/ayaya.png").getImage());
    private static final JLabel email = new JLabel();
    private static int panelWidth = 1320;
    private static int panelHeight = 740;
    private static JDialog popup;
    
    public Frame(){
        this.setContentPane(new BlankPanel());
        this.setLayout(new BorderLayout());
        this.setTitle("Library Manager");
        this.setIconImage(icon);
        this.add(layeredPane,BorderLayout.CENTER);
        this.setSize(panelWidth, panelHeight);
        this.setMinimumSize(new Dimension(panelWidth,panelHeight));
        this.setLocationRelativeTo(null);
        try {
            SwingUtilities.invokeAndWait(() -> {
                createPanels();
                entryPanel = new Entry();
            });
            initComponents();
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                Frame.closeDropdown();
            }
        });
        
        me = this;
    }
    
    public void createPanels(){
        
        Thread thread = new Thread(() -> {
            
            long startTime = System.currentTimeMillis();
            
            dropdown = new Dropdown();
            dropdown.setVisible(false);
            layeredPane.add(dropdown,Integer.valueOf(2));

            sideBar = new Sidebar();
            sideBar.setVisible(false);
            this.add(sideBar,BorderLayout.WEST);
            
            adminDashboard = new AdminDashboard();
            framePanel.add(PanelTypes.ADMINDASHBOARD.name(),adminDashboard);

            clientDasboard = new ClientDashboard();
            framePanel.add(PanelTypes.CLIENTDASHBOARD.name(),clientDasboard);
            
            panelsLoaded = true;
            bookListPanel = new BookList();
            framePanel.add(PanelTypes.BOOKLIST.name(),bookListPanel);

            addBook = new AddBook();
            framePanel.add(PanelTypes.ADDBOOK.name(),addBook);

            members = new Members();
            framePanel.add(PanelTypes.MEMBERS.name(),members);

            categoryList = new CategoryList();
            framePanel.add(PanelTypes.CATEGORYLIST.name(),categoryList);

            addCategory = new AddCategory();
            framePanel.add(PanelTypes.ADDCATEGORY.name(),addCategory);

            myBookList = new MyBookList();
            framePanel.add(PanelTypes.MYBOOKLIST.name(),myBookList);
            
            issuedBooksList = new IssuedBooks();
            framePanel.add(PanelTypes.ISSUEDBOOKS.name(),issuedBooksList);
            
            returnedBooks = new ReturnedBooks();
            framePanel.add(PanelTypes.RETURNEDBOOKS.name(),returnedBooks);
            
            history = new History();
            framePanel.add(PanelTypes.HISTORY.name(),history);
            
            synchronized(LibrarySystem.getLock()){
                LibrarySystem.getLock().notify();
            }
            
            long endTime = System.currentTimeMillis();
            System.out.println("Time taken to load panels : " + (endTime - startTime));
            
        });
        thread.start();
        System.out.println("Active Threads : " + Thread.activeCount());
    }
    
    private void initComponents(){
        this.addComponentListener(this);
        mainPanel.setLayout(new BorderLayout());
        dropdown.setSize(dropdown.getPreferredSize());
        dropdown.setLocation(780, 50);
        
        mainPanel.add(framePanel,BorderLayout.CENTER);
        mainPanel.setSize(1280, 720);
        layeredPane.setLayout(null);
        
        setupTopPanel();
        setupBottomPanel();
        
        framePanel.setLayout(cardlayout);
        framePanel.add(PanelTypes.ENTRY.name(),entryPanel);
        
        cardlayout.show(framePanel, PanelTypes.ENTRY.name());
        layeredPane.add(mainPanel,Integer.valueOf(1));
        currentPanel = entryPanel;
        
    }
    
    private void setupTopPanel(){
        topPanel.setPreferredSize(new Dimension(200,50));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createLineBorder(new Color(185,185,185), 1));
        topPanel.setLayout(null);
        topPanel.setVisible(false);

        toggleSidebarIcon = toggleSidebarIcon.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(toggleSidebarIcon));
        JToggleButton toggleSidebarButton = new JToggleButton(){
            @Override
            public void paint(Graphics g){
                super.paint(g);
                Graphics2D g2D = (Graphics2D) g;
                
                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2D.drawImage(toggleSidebarIcon, 0,0, null);
            }
        };
        toggleSidebarButton.setBorder(BorderFactory.createLineBorder(new Color(185,185,185), 1));
        toggleSidebarButton.setBounds(0, 0, 50, 50);
        toggleSidebarButton.setBackground(PalleteColors.TRANSPARENT);
        toggleSidebarButton.setContentAreaFilled(false);
        toggleSidebarButton.setFocusPainted(false);
        topPanel.add(toggleSidebarButton);
        
        imageButton.setSize(45, 45);
        imageButton.setLocation(890, 3);
        imageButton.addActionListener(l -> toggleDropdown());
        Utilities.addChangePointer(imageButton);
        topPanel.add(imageButton);

        email.setText("JCRosuelo@gmail.com");
        email.setFont(new Font("Roboto",Font.PLAIN,14));
        email.setForeground(PalleteColors.DROPDOWN_PRESSED);
        email.setHorizontalAlignment(JLabel.RIGHT);
        email.setSize(200, 20);
        email.setLocation(680, 15);
        topPanel.add(email);
        
        mainPanel.add(topPanel,BorderLayout.NORTH);
    }
    
    private void setupBottomPanel(){
        bottomPanel.setPreferredSize(new Dimension(200,70));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setLayout(null);
        JLabel label = new JLabel();
        label.setFont(new Font("Roboto", Font.PLAIN,12));
        label.setText("2024 © " + ConnectionHandler.getAddress() + " By JC Rosuelo");
        label.setLocation(20, 0);
        label.setSize(600, 50);
        label.setHorizontalAlignment(JLabel.LEFT);
        bottomPanel.add(label);
        bottomPanel.setVisible(false);
        
        mainPanel.add(bottomPanel,BorderLayout.SOUTH);
    }
    
    public enum PanelTypes{
        ENTRY,
        ADMINDASHBOARD,
        CLIENTDASHBOARD,
        ADDBOOK,
        BOOKLIST,
        MYBOOKLIST,
        ADDCATEGORY,
        CATEGORYLIST,
        PROFILE,
        SETTINGS,
        MEMBERS,
        ISSUEDBOOKS,
        RETURNEDBOOKS,
        HISTORY,
        NONE;
        
    }

    public static void switchPanel(PanelTypes name){
        cardlayout.show(framePanel, name.name());
        
        removePopup();
        
        if (name == PanelTypes.ENTRY) {
            topPanel.setVisible(false);
            bottomPanel.setVisible(false);
            sideBar.setVisible(false);
            mainPanel.setSize(panelWidth, panelHeight-20);
        }
        else{
            topPanel.setVisible(true);
            bottomPanel.setVisible(true);
            sideBar.setVisible(true);
            mainPanel.setSize(panelWidth-300, panelHeight-20);
        }
        
        switch (name) {
            case ENTRY -> currentPanel = entryPanel;
            case ADMINDASHBOARD -> currentPanel = adminDashboard;
            case CLIENTDASHBOARD -> currentPanel = clientDasboard;
            case ADDBOOK -> currentPanel = addBook;
            case BOOKLIST -> currentPanel = bookListPanel;
            case MYBOOKLIST -> currentPanel = myBookList;
            case ADDCATEGORY -> currentPanel = addCategory;
            case CATEGORYLIST -> currentPanel = categoryList;
            case PROFILE -> {
                currentPanel = (MyPanel) me.getContentPane();
                removePopup();
                makePopup(PopupType.SETTINGS, OptionsPopup.OptionsPanels.PROFILE);
                popup.setLocationRelativeTo(me);
                System.out.println("create popup");
            }
            case SETTINGS -> {
                currentPanel = (MyPanel) me.getContentPane();
                removePopup();
                makePopup(PopupType.SETTINGS, OptionsPopup.OptionsPanels.ACCOUNT);
                popup.setLocationRelativeTo(me);
                System.out.println("create popup");
            }
            case MEMBERS -> currentPanel = members;
            case ISSUEDBOOKS -> currentPanel = issuedBooksList;
            case RETURNEDBOOKS -> currentPanel = returnedBooks;
            case HISTORY -> currentPanel = history;
            case NONE -> currentPanel = null;
        }

    }
    
    public void changeSideBar(User.UserType type){
        sideBar.addSidebarButtons(type);
    }
    
    private static boolean isDropdownShown = false;
    public static void toggleDropdown(){
//        removeDetailsPopup();
//        removePopup();
        
        dropdown.setVisible(!isDropdownShown);
        isDropdownShown = !isDropdownShown;
    }
    
    public static void closeDropdown(){
        dropdown.setVisible(false);
        
        isDropdownShown = false;
    }

    public void changeUser(User user){
        imageButton.changeImage(user.getIcon());
        email.setText(user.getEmail());
        clientDasboard.setDashboardItems();
    }
    
    public void resize(){
        panelWidth = getWidth();
        panelHeight = getHeight();
        
        if (currentPanel instanceof Entry) {
            mainPanel.setSize(panelWidth, panelHeight-20);
        }
        else{
            mainPanel.setSize(panelWidth-300, panelHeight-20);
        }
        
        imageButton.setLocation(getWidth() - 390, 3);
        email.setLocation(getWidth() - 600, 15);
        
        if (currentPanel != null) currentPanel.resize();
        if (popup != null) popup.setLocationRelativeTo(currentPanel);
        dropdown.setLocation(getWidth()-540, 50);
    }
    
    public static boolean hasPanelsLoaded() {
        return panelsLoaded;
    }
    
    public void update(){
        Thread panelUpdateThread = new Thread(() -> {
            if (currentPanel != null) currentPanel.refreshItems();
        });
        panelUpdateThread.start();
    }
        
    @Override
    public void componentResized(ComponentEvent e) {
        resize();
    }
    
    public static <T> void makePopup(PopupType type, T obj){
        switch(type){
            case EDITBOOK -> popup = new EditBookPopup((Book) obj);
            case BOOKDETAILS -> popup = new BookDetailsPopup((Book) obj);
            case CHANGECATEGORYNAME -> popup = new ChangeCategoryNamePopup((Category) obj);
            case SETTINGS -> popup = new OptionsPopup((OptionsPopup.OptionsPanels) obj);
            case FORGETPASSWORD -> popup = new ForgetPasswordPopup();
            case USERDETAILS -> popup = new UserDetailsPopup((User) obj);
        }
        
        popup.setUndecorated(true);
        popup.setAlwaysOnTop(true);
        popup.setSize(popup.getPreferredSize().width,popup.getPreferredSize().height);
        popup.setLocationRelativeTo(currentPanel);
        popup.setVisible(true);
    }
    
    public enum PopupType{
        BOOKDETAILS, EDITBOOK, CHANGECATEGORYNAME, SETTINGS, FORGETPASSWORD, USERDETAILS;
    }
    
    public static void removePopup(){
        if (popup != null) {
            popup.dispose();
            popup = null;
        }
    }
    
    @Override
    public void componentMoved(ComponentEvent e) {
        if (popup != null) {
            popup.setLocationRelativeTo(currentPanel);
        }
        
    }

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}    

    public static MyPanel getCurrentPanel() {
        return currentPanel;
    }

    public static JDialog getPopup() {
        return popup;
    }
    
}
