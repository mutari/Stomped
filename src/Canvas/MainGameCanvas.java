package Canvas;

import java.awt.Graphics2D;
import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;

import Stomped.entity.Bullet;
import Stomped.entity.Entity;
import Stomped.entity.EntityManager;
import Stomped.entity.Player;
import Stomped.world.Tile;
import Stomped.world.TilePropertis;
import Stomped.world.World;
import Stomped.world.structure.Structure;
import UI.UIManager;
import input.KeyInput;

public class MainGameCanvas extends Canvas {

	private World world;
	private Player player;
	private EntityManager entityManager;
	
	private double globalOffsetX, globalOffsetY;
	private Point screenCenter;
	
	//temp!!!
	private TilePropertis[][] prop = {{new TilePropertis("./res/Tiles/Props/tile_134.png"), new TilePropertis("./res/Tiles/Props/tile_320.png")}, {new TilePropertis("./res/Tiles/Props/tile_159.png"), new TilePropertis("./res/Tiles/Props/tile_160.png")}};
	private Structure s = new Structure(new Point(500, 100), prop);
	private Structure s1 = new Structure(new Point(500, 500), prop);
	private Structure s2 = new Structure(new Point(100, 100), prop);

	public MainGameCanvas() {}
	
	@Override
	public void draw(Graphics2D g) {
		
		if(this.world != null) {
			// i = column
			// j = row
			for(int i = 0; i < world.getMap().length; i++) {
				for(int j = 0; j < world.getMap()[0].length; j++) {
					
					g.drawImage(world.getTile(i, j).propertis.getTexture(), (int)(globalOffsetX + (j * Tile.TILE_WIDTH)), (int)(globalOffsetY + (i * Tile.TILE_HEIGHT)), this);
					
				}
			}

			g.drawRect((int)(globalOffsetX + (-5)), (int)(globalOffsetY + (-5)), 10, 10);
			
			//temp!!!!
			s.draw(g, this);
			s1.draw(g, this);
			s2.draw(g, this);
		
			//draw player
			if(this.player != null) {
				
				this.player.draw(g, this);
				Point mousePosition = this.getMousePosition();
				if(mousePosition != null)
					g.drawLine(this.screenCenter.x, this.screenCenter.y, mousePosition.x, mousePosition.y);
				
			}

			entityManager.draw(g, this);
			
			if(UIManager.getSize() != 0) {
				UIManager.draw(g);
			}
		
		}
		
	}
	
	public void setScreenCenter(Point point) {
		this.screenCenter = point;
	}
	
	public Point getScreenCenter() {
		return this.screenCenter;
	}
	
	public void setGlobalOffset(double x, double y) {
		this.globalOffsetX = x;
		this.globalOffsetY = y;
	}
	
	public double getGlobalOffsetX() {
		return this.globalOffsetX;
	}
	
	public double getGlobalOffsetY() {
		return this.globalOffsetY;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return this.player;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}

	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}
	
	public boolean isEven(int num) {
		if(num % 2 == 0)
			return true;
	     else
	    	 return false;
	}
	
}
