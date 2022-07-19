package Canvas;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

public abstract class Canvas extends JPanel {
	
	public Canvas() {}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D)g;
        
        // draw custom content
        draw(g2d);
        
        //draw custom HUD
        //drawHUD(g);
    }  
	
	public abstract void draw(Graphics2D g);
	
	//public abstract void drawHUD(Graphics g);
	
}
