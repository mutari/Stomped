package Stomped.entity;

import java.awt.*;

import Canvas.Canvas;
import Canvas.MainGameCanvas;

import Toolbox.*;
import UI.ProgressBar;
import UI.UIManager;

public class Player extends Entity {

	public Player(double x, double y) {
		super(x, y);
		
		this.setSpeed(4);

		this.setRadius(30);

		this.setHealth(100);
		this.setMaxHealth(100);
	
		//this.setTexture("./res/Entity/Survivor/survivor_stand.png");
		this.setTexture("./res/Entity/Survivor/survivor_silencer.png");
	}

	@Override
	public int step() {
		return 0;
	}

	@Override
	public void draw(Graphics2D g, Canvas canvas) {
		MainGameCanvas mainGameCanvas = (MainGameCanvas)canvas;

		Image texture = this.texture;
		if(this.texture.getWidth(null) > 0)
			texture = ImageTool.rotate(this.texture, this.angel);

		double xOffset = (x + mainGameCanvas.getGlobalOffsetX());
		double yOffset = (y + mainGameCanvas.getGlobalOffsetY());

		double drawX = (xOffset - (texture.getWidth(null)/2));
		double drawY = (yOffset - (texture.getHeight(null)/2));

		//update health
		ProgressBar pb = (ProgressBar) UIManager.getElementById("player-health");
		pb.setPercent((int)(((double) health / (double) maxHealth) * 100));

		g.drawImage(texture, (int)drawX, (int)drawY, mainGameCanvas);
		g.drawOval((int)(xOffset - this.radius), (int)(yOffset - this.radius), (int)(this.radius * 2), (int)(this.radius * 2));

		g.setColor(Color.RED);
		g.fillRect((int)xOffset, (int)yOffset, 5 , 5);
		g.setColor(Color.BLACK);

	}

}
