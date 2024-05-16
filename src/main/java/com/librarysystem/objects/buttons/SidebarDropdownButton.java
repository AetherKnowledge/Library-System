
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
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import com.librarysystem.objects.ui.PalleteColors;

public class SidebarDropdownButton extends JToggleButton{
    
    private final Sidebar.DropdownType buttonType;
    private final Font font = new Font("Roboto",Font.PLAIN,18);
    private final ImageIcon imgIcon;
    Image icon;

    public SidebarDropdownButton(Sidebar.DropdownType buttonType,int width, int height) {
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
            setBackground(g2D,PalleteColors.DROPDOWN_PRESSED);
            setText(g2D, PalleteColors.BUTTON_PRESSED_TEXT);
            changeImageColor(PalleteColors.BUTTON_PRESSED_TEXT);
        }
        else if (getModel().isRollover()) {
            setBackground(g2D,PalleteColors.DROPDOWN);
            setText(g2D, Color.WHITE);
            changeImageColor(Color.WHITE);
        }
        else {
            setBackground(g2D,PalleteColors.DROPDOWN);
            setText(g2D, Color.WHITE);
            changeImageColor(Color.WHITE);
        }

        g2D.drawImage(icon,50,10, this);
        
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
        int x = 50 + 40;
        g2D.drawString(getText(), x, y);
        
        return g2D;
    }
    
    private void changeImageColor(Color toColor){
        int width = icon.getWidth(this);
        int height = icon.getHeight(this);
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = image.createGraphics();
        bGr.drawImage(icon, 0, 0, null);
        bGr.dispose();
        
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = toColor.getRGB();
                
                int alpha = (image.getRGB(x, y) >> 24) & 0xFF; // Alpha channel value
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                
                int newRGB = (alpha << 24) | (red << 16) | (green << 8) | blue;
                bufferedImage.setRGB(x,y,newRGB);
                
            }
        }
        
        Image newImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon = newImage;
    }
    
}
