
package com.librarysystem.panels.popups;

import com.librarysystem.objects.buttons.DropdownButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import com.librarysystem.Frame;
import com.librarysystem.Frame.PanelTypes;
import com.librarysystem.handlers.Utilities;

public class Dropdown extends JPanel {

    public Dropdown() {
        initComponents();
        this.removeAll();
        
        for (DropdownButtonType type : DropdownButtonType.values()) {
            DropdownButton button = new DropdownButton(type, 200, 50);
            button.addActionListener(l->{
                Frame.switchPanel(type.panelName);
                Frame.toggleDropdown();
            });
            Utilities.addChangePointer(button);
            this.add(button);
        }
    }
    
    public enum DropdownButtonType{
        PROFILE("Profile",Utilities.getImage("/textures/profile.png"),PanelTypes.PROFILE),
        SETTINGS("Settings",Utilities.getImage("/textures/settings.png"),PanelTypes.SETTINGS),
        ENTRY("Log out",Utilities.getImage("/textures/logout.png"),PanelTypes.ENTRY);
        
        public String text;
        public ImageIcon icon;
        public PanelTypes panelName;
        private DropdownButtonType(String text,ImageIcon icon,Frame.PanelTypes panelName) {
            this.text = text;
            this.icon = icon;
            this.panelName = panelName;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(65, 78, 101));
        setPreferredSize(new java.awt.Dimension(200, 150));
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jButton1.setText("Profile");
        jButton1.setPreferredSize(new java.awt.Dimension(200, 50));
        add(jButton1);

        jButton2.setText("Settings");
        jButton2.setPreferredSize(new java.awt.Dimension(200, 50));
        add(jButton2);

        jButton3.setText("Log out");
        jButton3.setPreferredSize(new java.awt.Dimension(200, 50));
        add(jButton3);
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables
}
