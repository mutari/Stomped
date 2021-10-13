package Stomped.world.biom;

import java.util.ArrayList;
import java.util.Random;

import Stomped.world.TilePropertis;

public class Plains extends Biom {

	public Plains(String biomName, Random random) {
		super(biomName, random);
		
		this.addTilePropertis(new TilePropertis("./res/Tiles/Terrain/Grass/tile_01.png"));
		this.addTilePropertis(new TilePropertis("./res/Tiles/Terrain/Grass/tile_02.png"));
		this.addTilePropertis(new TilePropertis("./res/Tiles/Terrain/Grass/tile_03.png"));
		this.addTilePropertis(new TilePropertis("./res/Tiles/Terrain/Grass/tile_04.png"));
		
	}

	@Override
	public ArrayList<TilePropertis> generateBiomStrip(int height) {
		
		ArrayList<TilePropertis> propertis = new ArrayList<TilePropertis>();
		
		for(int i = 0; i < height; i++) {
			int randomIndex = this.random.nextInt(this.getAmountOfTilePropertis());
			propertis.add(this.getTilePropertis(randomIndex));
		}
		
		return propertis;
		
	}
	
}
