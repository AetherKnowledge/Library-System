
package com.librarysystem.objects.buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import com.librarysystem.objects.ui.PalleteColors;

public class TagButton extends JButton{
    
    public TagButton(String text){
        this.setText(text);
        this.setName(text);
        this.setContentAreaFilled(false);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(PalleteColors.TRANSPARENT);
        this.setFont(new Font("Roboto",Font.PLAIN,8));
        this.setFocusPainted(false);
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setColor(new Color(224,224,224));
        g2D.fillRoundRect(0, 0, getWidth(), getHeight(),8,8);
        
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2D.setColor(PalleteColors.DROPDOWN_PRESSED);
        FontMetrics metrics = g2D.getFontMetrics();
        int width = metrics.stringWidth(getText() + " x");
        int y = (getHeight() - metrics.getHeight())/2 + metrics.getAscent();
        g2D.drawString(getText() + " x", 5, y);
        
        if (getWidth() != width || getHeight() != 12) {
            this.setSize(new Dimension(width + 10,12));
            this.setPreferredSize(new Dimension(width + 10,12));
        }
    }
    
}
