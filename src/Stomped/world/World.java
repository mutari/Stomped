package Stomped.world;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import Stomped.world.biom.*;

public class World {

	private long seed;
	private Random random;
	
	private Tile[][] tiles;
	private ArrayList<Biom> bioms;
	
	public World(long seed, Dimension worldSize) {
		this.setSeed(seed);
		
		this.random = new Random();
		this.random.setSeed(seed);
	
		this.tiles = new Tile[worldSize.width][worldSize.height];
		
		this.bioms = new ArrayList<>();
		this.bioms.add(new Plains("plains", this.random));
		this.bioms.add(new Desert("desert", this.random));
	}
	
	public void generateWorld() {
		
		Biom biom = bioms.get(0);
		
		for(int i = 0; i < this.tiles[0].length; i++) {
			
			ArrayList<TilePropertis> stripTiles = biom.generateBiomStrip(this.tiles.length);
			
			for(int j = 0; j < this.tiles.length; j++) {
				
				tiles[j][i] = new Tile(i, j, stripTiles.get(j));
				
			}
			
		}
		
		
		/*
		for(int i = 0; i < this.tiles.length; i++) {
			for(int j = 0; j < this.tiles[0].length; j++) {
				tiles[i][j] = new Tile(i, j, "./res/Tiles/Terrain/Grass/grass_01.png");
			}
		}
		*/
		
	}
	
	public Tile[][] getMap() {
		return tiles;
	}
	
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}
	
	/**
	 * @return the seed
	 */
	public long getSeed() {
		return seed;
	}

	/**
	 * @param seed the seed to set
	 */
	public void setSeed(long seed) {
		this.seed = seed;
	}
	
}
