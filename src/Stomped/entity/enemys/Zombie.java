package Stomped.entity.enemys;

import Stomped.Animation.Animation;
import Stomped.Animation.Frame;
import Stomped.entity.Entity;

import java.awt.*;
import java.util.ArrayList;

import Canvas.MainGameCanvas;
import Canvas.Canvas;
import Stomped.entity.Player;
import Toolbox.ImageTool;
import Toolbox.Position;
import Toolbox.Toolbox;
import UI.ProgressBar;
import UI.UIManager;


public class Zombie extends Entity {

    private Player player = null;
    private double detectionArea = 1000.0;

    private Animation onHitAnimation;

    public Zombie(double x, double y) {
        super(x, y);

        this.setSpeed(1.2);
        this.setRadius(30);
        this.setMaxHealth(150);
        this.setHealth(150);

        // set upp animation
        ArrayList<Frame> onHitFrames = new ArrayList<>();
        onHitFrames.add(new Frame(new double[]{21}));
        onHitFrames.add(new Frame(new double[]{20}));
        onHitAnimation = new Animation(onHitFrames, 10) {
            @Override
            public void step(Frame frame) {
                onHitFrame(frame);
            }
        };

        this.setTexture("./res/Entity/Zombie/zombie_hold.png");

        System.out.println((int)(((double) this.health / (double) this.maxHealth)*100));
        ProgressBar pb = new ProgressBar(new Point((int)x, (int)y), "Zombie_" + this.identifier, 200, 10, (int)(((double) this.health / (double) this.maxHealth)*100));
        pb.setVisible(false);
        UIManager.add(pb);

    }

    public void onHitFrame(Frame frame) {
        double speed = frame.vars[0];
        double temp = this.speed;
        this.speed = -speed;
        calculateNewPos();
        this.speed = temp;
    }

    public int step() {
        if(this.player != null) {
            if(this.health <= 0) {
                UIManager.removeById("Zombie_" + this.identifier);
                return 1;
            }
            double lengthBetween = Math.sqrt((this.x - player.getPosition().x)*(this.x - player.getPosition().x) + (this.y - player.getPosition().y)*(this.y - player.getPosition().y));
            if(lengthBetween <= (detectionArea/2)) {
                detectionArea = 2000.0;
                this.angel = Toolbox.getAngle(new Position(x, y), new Position(player.getPosition().x, player.getPosition().y)) - 90;
                double tempx = this.x;
                double tempy = this.y;
                calculateNewPos();
                if(lengthBetween <= (this.radius + this.player.getRadius())) {
                    this.x = tempx;
                    this.y = tempy;
                    player.doDamage(10);
                    onHitAnimation.run();
                }
            } else {
                detectionArea = 1000.0;
            }
        }
        return 0;
    }


    @Override
    public void draw(Graphics2D g, Canvas canvas) {
        MainGameCanvas mainGameCanvas = (MainGameCanvas)canvas;
        player = mainGameCanvas.getPlayer();

        Image texture = this.texture;
        if(this.texture.getWidth(null) > 0)
            texture = ImageTool.rotate(this.texture, this.angel);

        double xOffset = (x + mainGameCanvas.getGlobalOffsetX());
        double yOffset = (y + mainGameCanvas.getGlobalOffsetY());

        double drawX = (xOffset - (texture.getWidth(null)/2));
        double drawY = (yOffset - (texture.getHeight(null)/2));

        ProgressBar healthBar = (ProgressBar) UIManager.getElementById("Zombie_" + this.identifier);
        if(healthBar != null) {
            healthBar.setPoint(new Point((int) xOffset - healthBar.getWidth()/2, (int) yOffset - (texture.getHeight(null)/2) - healthBar.getHeight() * 2));
            healthBar.setPercent((int)(((double) this.health / (double) this.maxHealth)*100));
            if(this.health < this.maxHealth)
                healthBar.setVisible(true);
        }

        g.drawImage(texture, (int)drawX, (int)drawY, mainGameCanvas);
        g.drawOval((int)(xOffset - this.radius), (int)(yOffset - this.radius), (int)(this.radius * 2), (int)(this.radius * 2));
        g.drawLine((int)(player.getPosition().x + mainGameCanvas.getGlobalOffsetX()), (int)(player.getPosition().y + mainGameCanvas.getGlobalOffsetY()), (int)xOffset, (int)yOffset);

        g.setColor(Color.RED);
        //draws detection radius
        g.drawOval((int)(xOffset - (detectionArea/2)), (int)(yOffset - (detectionArea/2)), (int)detectionArea, (int)detectionArea);

        g.fillRect((int)xOffset, (int)yOffset, 5 , 5);
        g.setColor(Color.BLACK);

    }
}
