package Toolbox;

import java.awt.*;

public class Position {

    public double x, y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Position pointToPosition(Point point) {
        if(point != null)
            return new Position(point.x, point.y);
        return null;
    }

}
