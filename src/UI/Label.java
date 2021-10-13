package UI;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class Label extends Element {
	
	private AffineTransform affinetransform = new AffineTransform();     
	private FontRenderContext frc = new FontRenderContext(affinetransform,true,true);  
	private String text;
	private Font font; 
	private int fontSize = 16;
	
	public Label(String text, Point point, String id) {
		super(point);
		this.text = text;
		this.font = new Font("TimesRoman", Font.PLAIN, fontSize);
		this.elementId = id;
		this.color = Color.BLACK;
		
		this.dimension = new Dimension((int)(font.getStringBounds(text, frc).getWidth()), (int)(font.getStringBounds(text, frc).getHeight()));
	}
	
	public Label(String text, Point point) {
		super(point);
		this.text = text;
		this.font = new Font("TimesRoman", Font.PLAIN, fontSize);
		
		this.dimension = new Dimension((int)(font.getStringBounds(text, frc).getWidth()), (int)(font.getStringBounds(text, frc).getHeight()));
	}

	@Override
	public void drawElement(Graphics2D g) {
		g.setFont(this.font);
		g.setColor(this.color);
		g.drawString(this.text, point.x, point.y + this.dimension.height);
	}
	
	public void setFont(Font font) {
		this.font = font;
		this.dimension = new Dimension((int)(font.getStringBounds(text, frc).getWidth()), (int)(font.getStringBounds(text, frc).getHeight()));
	}
	
	public Font getFont() {
		return this.font;
	}
		
	public void setText(String text) {
		this.text = text;
		this.dimension = new Dimension((int)(font.getStringBounds(text, frc).getWidth()), (int)(font.getStringBounds(text, frc).getHeight()));
	}
	
	public String getText() {
		return this.text;
	}
	
}
