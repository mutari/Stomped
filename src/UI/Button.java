package UI;

import java.awt.*;

public class Button extends Element {

    private int width = 100;
    private int height = 25;

    public Button(Point point) {
        super(point);
    }

    @Override
    public void drawElement(Graphics2D g) {
        g.fillRect(this.point.x, this.point.y, width, height);
    }

    @Override
    public void eventListener(EVENT event) {

    }
}
