package UI;

import Toolbox.Toolbox;
import UI.Event.Event;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class Button extends Element {

    private String text;

    private Font font;
    private int textWidth, textHeight;

    public Button(String text, Point point) {
        this(point);
        this.setText(text);
        if(this.dimension == null)
            this.dimension = new Dimension(textWidth, textHeight);
    }

    public Button(Point point) {
        super(point);
        this.font = Toolbox.getFont();
    }

    @Override
    public void drawElement(Graphics2D g) {
        g.drawRoundRect(this.point.x, this.point.y, this.dimension.width, this.dimension.height, 20, 20);

        if(this.text != null) {
            int textX = this.point.x + ((this.dimension.width - this.textWidth) / 2);
            int textY = this.point.y + this.textHeight + ((this.dimension.height - this.textHeight) / 2);

            g.setFont(this.font);
            g.drawString(this.text, textX, textY);
        }

    }

    public void setText(String text) {
        this.text = text;
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        this.textWidth = (int)(this.font.getStringBounds(text, frc).getWidth());
        this.textHeight = (int)(this.font.getStringBounds(text, frc).getHeight());
    }

    public String getText() {
        return this.text;
    }
}
