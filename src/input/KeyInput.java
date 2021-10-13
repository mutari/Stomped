package input;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Stomped.entity.Entity;
import Stomped.entity.Player;

public abstract class KeyInput extends KeyAdapter {
	
	private Player player;
	
	@Override
    public void keyPressed(KeyEvent event) {
		pressed(event);
    }
	
	@Override
    public void keyReleased(KeyEvent event) {
		released(event);
    }

	public void setPlayer(Player player) {
		this.player = player;
	}

	public abstract void pressed(KeyEvent event);
	public abstract void released(KeyEvent event);

}
