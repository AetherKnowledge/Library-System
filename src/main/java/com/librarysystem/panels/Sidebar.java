
package com.librarysystem.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import com.librarysystem.Frame;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.objects.ui.PalleteColors;
import com.librarysystem.objects.components.SideBarLogo;
import com.librarysystem.objects.User;
import com.librarysystem.objects.buttons.SidebarDropdownButton;
import com.librarysystem.objects.buttons.SidebarMainButton;

public class Sidebar extends JPanel{
    
    private final MainButtonType[] adminButtons = {MainButtonType.ADMINDASHBOARD,MainButtonType.ADMINBOOKS,MainButtonType.CATEGORIES,MainButtonType.MEMBERS};
    private final MainButtonType[] userButtons = {MainButtonType.CLIENTDASHBOARD,MainButtonType.CLIENTBOOKS};
    private final ArrayList<SidebarMainButton> currentMainButtons = new ArrayList<>();
    private final ArrayList<SidebarDropdownButton> currentDropdownButtons = new ArrayList<>();
    private final JPanel dropdownPanel = new JPanel();
    SideBarLogo sideBarLogo = new SideBarLogo();
    private SidebarMainButton currentMainButton = null;
    
    public Sidebar(){
        this.setBackground(PalleteColors.SIDEBAR_MAIN_COLOR);
        this.setPreferredSize(new Dimension(300,50));
        this.setLayout(new FlowLayout(0,0,0));
        addLabel();
        
        dropdownPanel.setPreferredSize(new Dimension(300,100));
        dropdownPanel.setLayout(new FlowLayout(0,0,0));
        dropdownPanel.setBackground(PalleteColors.DROPDOWN);

    }
    
    private void addLabel(){
        sideBarLogo.setPreferredSize(new Dimension(300,80));
        sideBarLogo.setForeground(PalleteColors.SIDEBAR_MAIN_COLOR);
        this.add(sideBarLogo);
    }
    
    public enum MainButtonType{
        ADMINDASHBOARD("Dashboard",Utilities.getImage("/textures/dashboard.png"),false,Frame.PanelTypes.ADMINDASHBOARD),
        CLIENTDASHBOARD("Dashboard",Utilities.getImage("/textures/dashboard.png"),false,Frame.PanelTypes.CLIENTDASHBOARD),
        ADMINBOOKS("Books",Utilities.getImage("/textures/books.png"),true,Frame.PanelTypes.NONE),
        CLIENTBOOKS("Books",Utilities.getImage("/textures/books.png"),true,Frame.PanelTypes.NONE),
        CATEGORIES("Categories",Utilities.getImage("/textures/categories.png"),true,Frame.PanelTypes.NONE),
        MEMBERS("Members",Utilities.getImage("/textures/members.png"),false,Frame.PanelTypes.MEMBERS);
        
        public String text;
        public ImageIcon icon;
        public Frame.PanelTypes panelName;
        public boolean hasDropdown;
        MainButtonType(String text,ImageIcon icon,boolean hasDropdown,Frame.PanelTypes panelName){
            this.text = text;
            this.icon = icon;
            this.hasDropdown = hasDropdown;
            this.panelName = panelName;
        }
    }
    
    public enum DropdownType{
        ADDBOOK("Add Book",Utilities.getImage("/textures/add.png"),MainButtonType.ADMINBOOKS,Frame.PanelTypes.ADDBOOK),
        ADMINBOOKLIST("Book List",Utilities.getImage("/textures/books.png"),MainButtonType.ADMINBOOKS,Frame.PanelTypes.BOOKLIST),
        ISSUEDBOOKS("Issued Books",Utilities.getImage("/textures/books.png"),MainButtonType.ADMINBOOKS,Frame.PanelTypes.ISSUEDBOOKS),
        RETURNEDBOOKS("Returned Books",Utilities.getImage("/textures/books.png"),MainButtonType.ADMINBOOKS,Frame.PanelTypes.RETURNEDBOOKS),
        CLIENTBOOKLIST("Book List",Utilities.getImage("/textures/books.png"),MainButtonType.CLIENTBOOKS,Frame.PanelTypes.BOOKLIST),
        CLIENTMYBOOKLIST("My Books List",Utilities.getImage("/textures/books.png"),MainButtonType.CLIENTBOOKS,Frame.PanelTypes.MYBOOKLIST),
        HISTORY("History",Utilities.getImage("/textures/books.png"),MainButtonType.CLIENTBOOKS,Frame.PanelTypes.HISTORY),
        ADDCATEGORY("Add Category",Utilities.getImage("/textures/add.png"),MainButtonType.CATEGORIES,Frame.PanelTypes.ADDCATEGORY),
        CATEGORYLIST("Category List",Utilities.getImage("/textures/categories.png"),MainButtonType.CATEGORIES,Frame.PanelTypes.CATEGORYLIST);
        
        
        public String text;
        public Frame.PanelTypes panelName;
        public ImageIcon icon;
        MainButtonType parent;
        private DropdownType(String text,ImageIcon icon, MainButtonType parent,Frame.PanelTypes panelName) {
            this.text = text;
            this.parent = parent;
            this.icon = icon;
            this.panelName = panelName;
        }
    }
    
    private void addMainButton(MainButtonType buttonType){
        SidebarMainButton button = new SidebarMainButton(buttonType);
        button.addActionListener(l -> {
            currentMainButton = button;
            buttonAction(buttonType);
            for (JToggleButton current : currentMainButtons) {
                current.setSelected(false);
            }
            button.setSelected(true);
        });
        
        Utilities.addChangePointer(button);
        
        currentMainButtons.add(button);
        this.add(button);
    }
    
    private void addDropdownButton(DropdownType buttonType){
        SidebarDropdownButton button = new SidebarDropdownButton(buttonType,300,50);
        button.addActionListener(l -> {
            Frame.switchPanel(buttonType.panelName);
            for (JToggleButton current : currentDropdownButtons) {
                current.setSelected(false);
            }
            button.setSelected(true);
        });
        
        Utilities.addChangePointer(button);
        
        currentDropdownButtons.add(button);
        dropdownPanel.add(button);
    }
    
    private void buttonAction(MainButtonType btn){
        Frame.switchPanel(btn.panelName);
        
        this.removeAll();
        this.add(sideBarLogo);
        
        int dropdownButtonsCount = 0;
        for (SidebarMainButton button : currentMainButtons) {
            this.add(button);
            if (button == currentMainButton && btn.hasDropdown) {
                addDropdownButtons();
                dropdownButtonsCount++;
                this.add(dropdownPanel);
            }
        }
        dropdownPanel.setPreferredSize(new Dimension(300, 50 * dropdownPanel.getComponents().length));
        revalidate();
        repaint();
    }
    
    private void addDropdownButtons(){
        dropdownPanel.removeAll();
        currentDropdownButtons.clear();
        for (DropdownType type : DropdownType.values()) {
            if (type.parent == currentMainButton.getButtonType()) {
                addDropdownButton(type);
            }
        }
    }
    
    public void addSidebarButtons(User.UserType type){
        removeAll();
        currentMainButtons.clear();
        addLabel();
        if (type == User.UserType.ADMIN) {
            for (MainButtonType btn : adminButtons) {
                addMainButton(btn);
            }
        }
        else {
            for (MainButtonType btn : userButtons) {
                addMainButton(btn);
            }
        }
        
        revalidate();
        repaint();
        currentMainButtons.get(0).setSelected(true);
        Frame.switchPanel(currentMainButtons.get(0).getButtonType().panelName);
    }
    
}
