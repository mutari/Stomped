package UI;

import java.awt.*;

public abstract class Element {

	enum EVENT {
		CLICK, HOVER
	}
	
	protected Point point;
	protected Dimension dimension;
	protected String elementId = "";
	protected Color color = Color.BLACK;
	protected Color borderColor = Color.BLACK;
	protected boolean visible = true;



	public Element(Point point) {
		this.point = point;
	}
	
	public void setPoint(Point point) {
		this.point = point;
	}
	
	public Point getPoint() {
		return this.point;
	}
	
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	
	public Dimension getDimension() {
		return this.dimension;
	}
	
	public String getId() {
		return this.elementId;
	}
	
	public void setId(String id) {
		this.elementId = id;
	}

	public void setColor(Color c) {
		this.color = c;
	}

	public Color getColor() {
		return this.color;
	}

	public void setBorderColor(Color bc) {
		this.borderColor = bc;
	}

	public Color getBorderColor() {
		return this.borderColor;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean getVisible() {
		return this.visible;
	}

	public void draw(Graphics2D g) {
		if(this.visible)
			drawElement(g);
	}

	public abstract void drawElement(Graphics2D g);

	public abstract void eventListener(EVENT event);
}
