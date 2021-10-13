package Stomped.entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import Canvas.Canvas;
import Toolbox.Position;
import Toolbox.Toolbox;

public abstract class Entity {
	
	public static enum DIRECTION {
		RIGHT, RIGHT_DOWN, RIGHT_UP,
		LEFT, LEFT_DOWN, LEFT_UP,
		DOWN, 
		UP
	};
	
	protected double x, y;
	protected int maxHealth;
	protected int health;
	protected double speed;
	protected DIRECTION direction;
	protected Image texture;
	protected double angel;
	protected double radius = 30;
	protected double collisionDetectionArea = 300;
	protected String identifier;
	
	public Entity(double x, double y) {
		this.x = x;
		this.y = y;
		identifier = Toolbox.generateRandomString(10);
	}

	public void moveTo(Position position) {
		this.x = position.x;
		this.y = position.y;
	}

	public void calculateNewPos() {
		this.x = (this.x + (this.speed * Math.cos(this.angel * (Math.PI / 180))));
		this.y = (this.y + (this.speed * Math.sin(this.angel * (Math.PI / 180))));
	}
	
	public abstract void draw(Graphics2D g, Canvas canvas);
	public abstract int step();

	public Position getPosition() {
		return new Position(x, y);
	}

	public Position getCenterPosition() {
		return new Position(this.x + (this.texture.getWidth(null)/2), this.y + (this.texture.getHeight(null)/2));
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void doDamage(int amount) {
		this.health -= amount;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setDirection(DIRECTION direction) {
		this.direction = direction;
	}
	
	public DIRECTION getDirection() {
		return this.direction;
	}
	
	public void setTexture(Image texture) {
		this.texture = texture;
	}
	
	public void setTexture(String texturePath) {
		this.texture = Toolkit.getDefaultToolkit().getImage(texturePath);
	}
	
	public Image getTexture() {
		return this.texture;
	}
	
	public double getAngel() {
		return this.angel;
	}
	
	public void setAngel(double angel) {
		this.angel = angel;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getCollisionDetectionArea() {
		return this.collisionDetectionArea;
	}

	public void setCollisionDetectionArea(double c) {
		this.collisionDetectionArea = c;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getMaxHealth() {
		return this.maxHealth;
	}

	public double getRadius() {
		return this.radius;
	}

}
