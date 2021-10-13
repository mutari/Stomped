package Stomped.world.biom;

import java.util.ArrayList;
import java.util.Random;

import Stomped.world.TilePropertis;

public abstract class Biom {
	
	private ArrayList<TilePropertis> TilesPropertis;
	private String biomName;
	protected Random random;
	
	public Biom(String biomName, Random random) {
		this.biomName = biomName;
		this.TilesPropertis = new ArrayList<>();
		this.random = random;
	}
	
	public abstract ArrayList<TilePropertis> generateBiomStrip(int height);
	
	public String getBiomName() {
		return this.biomName;
	}
	
	protected void addTilePropertis(TilePropertis propertis) {
		this.TilesPropertis.add(propertis);
	}
	
	protected int getAmountOfTilePropertis() {
		return this.TilesPropertis.size();
	}
	
	protected TilePropertis getTilePropertis(int index) {
		return TilesPropertis.get(index);
	}
	
}
