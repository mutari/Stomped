package Stomped.world;

public class Tile {
	
	public static int TILE_WIDTH = 64, TILE_HEIGHT = 64;
	
	public int x, y;
	public TilePropertis propertis;
	
	public Tile(int x, int y, TilePropertis propertis) {
		this.x = x;
		this.y = y;
		this.propertis = propertis;
	}
	
}
