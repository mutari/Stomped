package Stomped.world.structure;

import java.awt.Graphics2D;
import java.awt.Point;

import Canvas.Canvas;
import Canvas.MainGameCanvas;
import Stomped.world.Tile;
import Stomped.world.TilePropertis;

public class Structure {
	
	public String name;
	public TilePropertis[][] structureTileMap;
	public Point cords;
	
	public Structure(Point cords, TilePropertis[][] structureTileMap) {
		
		this.name = "structure";
		this.structureTileMap = structureTileMap;
		this.cords = cords;
		
	}
	
	private TilePropertis getTileProperti(int x, int y) {
		return this.structureTileMap[x][y];
	}
	
	public void draw(Graphics2D g, Canvas canvas) {
		
		MainGameCanvas mainGameCanvas = (MainGameCanvas)canvas;
		
		for(int i = 0; i < this.structureTileMap.length; i++) {
			for(int j = 0; j < this.structureTileMap[0].length; j++) {
				
				g.drawImage(getTileProperti(i, j).getTexture(), (int)(mainGameCanvas.getGlobalOffsetX() + (j * Tile.TILE_WIDTH) + cords.x), (int)(mainGameCanvas.getGlobalOffsetY() + (i * Tile.TILE_HEIGHT) + cords.y), mainGameCanvas);
				
			}
		}
		
	}
	
}
