package Boot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import Canvas.Canvas;

public class Window extends JFrame {
	
	private int WIDTH, HEIGHT;
	
	public Window(int WIDTH, int HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setLayout(new BorderLayout());
		
		this.addComponentListener(new ComponentAdapter() 
		{ 
	        public void componentResized(ComponentEvent evt) {
	        	System.out.println("Resized");
	        }
		});
		
	}
	
	public void setCanvas(Canvas canvas) {
		this.add(canvas, BorderLayout.CENTER);
	}
	
	public void display() {
		this.setVisible(true);
	}
	
	public void hide() {
		this.setVisible(false);
	}
	
	public void setWidth(int w) {
		this.WIDTH = w; 
	}
	
	public void setHeight(int h) {
		this.HEIGHT = h;
	}
	
	public int getHeight() {
		return this.HEIGHT;
	}
	
	public int getWidth() {
		return this.WIDTH;
	}
	
}
