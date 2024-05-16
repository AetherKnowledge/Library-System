
package com.librarysystem.objects.buttons;

import com.librarysystem.panels.Sidebar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import com.librarysystem.handlers.Utilities;
import com.librarysystem.objects.ui.PalleteColors;
import com.librarysystem.panels.Sidebar.MainButtonType;

public class SidebarMainButton extends JToggleButton{
    
    private final MainButtonType buttonType;
    private final Font font = new Font("Roboto",Font.PLAIN,18);
    private final Font sign_font = new Font("Roboto",Font.BOLD,24);
    private final ImageIcon imgIcon;
    Image icon;

    public SidebarMainButton(Sidebar.MainButtonType buttonType) {
        this.buttonType = buttonType;
        setFont(font);
        setText(buttonType.text);
        setName(buttonType.name());
        setPreferredSize(new Dimension(300,50));
        setFocusable(false);
        imgIcon = buttonType.icon;
        icon = imgIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(icon));
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        if (getModel().isSelected()) {
            setBackground(g2D,PalleteColors.DROPDOWN);
            setText(g2D, PalleteColors.BUTTON_PRESSED_TEXT);
            icon = Utilities.changeImageColor(icon, PalleteColors.BUTTON_PRESSED_TEXT);
        }
        else if (getModel().isRollover()) {
            setBackground(g2D,PalleteColors.SIDEBAR_MAIN_COLOR);
            setText(g2D, Color.WHITE);
            icon = Utilities.changeImageColor(icon,Color.WHITE);
        }
        else {
            setBackground(g2D,PalleteColors.SIDEBAR_MAIN_COLOR);
            setText(g2D, Color.WHITE);
            icon = Utilities.changeImageColor(icon,Color.WHITE);
        }
        
        g2D.drawImage(icon, 10,10, this);
        
    }
    
    private Graphics2D setBackground(Graphics2D g2D, Color color){
        g2D.setColor(color);
        g2D.fillRect(0, 0, getWidth(), getHeight());
        return g2D;
    }
    
    private Graphics2D setText(Graphics2D g2D, Color color){
        g2D.setColor(color);
        FontMetrics metrics = g2D.getFontMetrics();
        int y = (getHeight() - metrics.getHeight())/2 + metrics.getAscent();
        int x = getHeight();
        g2D.drawString(getText(), x, y);
        
        if (!buttonType.hasDropdown) return g2D;
        
        g2D.setFont(sign_font);
        metrics = g2D.getFontMetrics();
        y = (getHeight() - metrics.getHeight())/2 + metrics.getAscent();
        if (getModel().isSelected()) g2D.drawString("-", getWidth()-19, y);
        else if (getModel().isRollover()) g2D.drawString("+", getWidth()-20, y);
        else g2D.drawString("+", getWidth()-20, y);
        
        return g2D;
    }

    public MainButtonType getButtonType() {
        return buttonType;
    }
    
}
