package Stomped.world;

import java.awt.Image;
import java.awt.Toolkit;

public class TilePropertis {
	
	private Image texture;
	private boolean canCollide;
	
	public TilePropertis() {}
	
	public TilePropertis(String texturePath) {
		this.texture = Toolkit.getDefaultToolkit().getImage(texturePath);
	}

	public void setCanCollide(boolean c) {
		this.canCollide = c;
	}

	public boolean getCanCollide() {
		return this.canCollide;
	}

	public Image getTexture() {
		return texture;
	}
	
	public void setTexture(Image texture) {
		this.texture = texture;
	}
	
	public void setTexture(String texturePath) {
		this.texture = Toolkit.getDefaultToolkit().getImage(texturePath);
	}
	
}
