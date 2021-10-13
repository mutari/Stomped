package Stomped.entity;

import Canvas.Canvas;
import Canvas.MainGameCanvas;

import java.awt.*;
import java.util.Iterator;

public class Bullet extends Entity {

    private int aliveLength = 1000;
    private double startX;
    private double startY;
    private int damage = 10;

    public Bullet(double x, double y, double angel) {
        super(x, y);
        this.startX = x;
        this.startY = y;
        this.angel = angel;
        this.speed = 10;
    }

    public int step() {
        if(Math.sqrt((this.x-startX)*(this.x-startX) + (this.y-startY)*(this.y-startY)) >= aliveLength)
            return 1;

        Iterator<Entity> itr = EntityManager.getEntitiesInRadius(this, this.collisionDetectionArea).iterator();
        while(itr.hasNext()) {
            Entity e = itr.next();
            if(Math.sqrt((this.x - e.x)*(this.x - e.x) + (this.y - e.y)*(this.y - e.y)) <= radius) {
                e.doDamage(this.damage);
                return 1;
            }
        }

        calculateNewPos();
        return 0;
    }

    @Override
    public void draw(Graphics2D g, Canvas canvas) {
        MainGameCanvas mainGameCanvas = (MainGameCanvas) canvas;

        int x2 = (int)(this.x + ((this.speed * 4) * Math.cos(this.angel * (Math.PI / 180))));
        int y2 = (int)(this.y + ((this.speed * 4) * Math.sin(this.angel * (Math.PI / 180))));

        g.setColor(Color.RED);
        g.drawLine((int)(this.x + mainGameCanvas.getGlobalOffsetX()), (int)(this.y + mainGameCanvas.getGlobalOffsetY()), (int)(x2 + mainGameCanvas.getGlobalOffsetX()), (int)(y2 + mainGameCanvas.getGlobalOffsetY()));
    }
}
