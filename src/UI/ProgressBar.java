package UI;

import java.awt.*;

public class ProgressBar extends Element {

    private int width = 200;
    private int height = 20;
    private int percent = 0;

    public ProgressBar(Point point, String id, int width, int height, int percent) {
        this(point, id, width, height);
        this.percent = percent;
    }

    public ProgressBar(Point point, String id, int width, int height) {
        this(point, id);
        this.width = width;
        this.height = height;
    }

    public ProgressBar(Point point, String id) {
        super(point);
        this.elementId = id;
        this.color = Color.RED;
    }

    @Override
    public void drawElement(Graphics2D g) {
        Color temp = g.getColor();
        g.setColor(this.color);
        g.fillRect(this.point.x, this.point.y, (int)(this.width * ((double) this.percent / 100)), this.height);
        g.setColor(this.borderColor);
        g.drawRect(this.point.x, this.point.y, this.width, this.height);
        g.setColor(temp);
    }

    @Override
    public void eventListener(EVENT event) {

    }

    public void setPercent(int percent) {
        if(percent < 0) percent = 0;
        this.percent = percent;
    }

    public int getPercent() {
        return this.percent;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
