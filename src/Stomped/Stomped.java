package Stomped;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import Boot.Window;
import Canvas.MainGameCanvas;
import Stomped.entity.*;
import Stomped.entity.EntityManager;
import Stomped.entity.enemys.Zombie;
import Stomped.world.World;
import Toolbox.*;
import UI.Button;
import UI.Label;
import UI.ProgressBar;
import UI.UIManager;
import input.KeyInput;
import input.MouseInput;

public class Stomped {

	private Window gameWindow;
	private MainGameCanvas mainGameCanvas;
	private World gameWorld;
	private EntityManager entityManager;
	
	private Player player;

	public Stomped(Window gw, MainGameCanvas mgc) {
		this.gameWindow = gw;
		this.mainGameCanvas = mgc;
		
		this.gameWorld = new World(938658241, new Dimension(20, 40));
		this.gameWorld.generateWorld();
		
		this.player = new Player(150, 150);
		
		// set up UI
		UIManager.add(new Label("x / y", new Point(20, 20), "cords-onscreen"));
		UIManager.add(new Label("x / y", new Point(20, 40), "cords-onmap"));
		UIManager.add(new Label("x / y", new Point(20, 60), "cords-player"));
		UIManager.add(new Label("", new Point(20, 80), "bullets-number"));
		UIManager.add(new Label("", new Point(20, 100), "entities-number"));
		UIManager.add(new ProgressBar(new Point((this.mainGameCanvas.getWidth()/2) - 150, /*this.mainGameCanvas.getHeight() - */50), "player-health", 300, 15, 100));
		UIManager.add(new Button(new Point((this.mainGameCanvas.getWidth()/2) - 150, this.mainGameCanvas.getHeight() - 20)));

		//set upp entitymanager
		this.entityManager = new EntityManager();
		this.entityManager.addEntity(new Zombie(400, 400));
		this.entityManager.addEntity(new Zombie(800, 400));
		this.entityManager.addEntity(new Zombie(400, 800));
		
		this.mainGameCanvas.setWorld(this.gameWorld);
		this.mainGameCanvas.setPlayer(this.player);
		this.mainGameCanvas.setEntityManager(this.entityManager);

		this.gameWindow.addKeyListener(new KeyListener());
		this.gameWindow.addMouseListener(new MouseListener());
	}
	
	public void init() {
	
		int windowWidth = this.gameWindow.getWidth();
		int windowHeight = this.gameWindow.getHeight();

		double globalOffsetX = (windowWidth/2) - this.player.getPosition().x;
		double globalOffsetY = (windowHeight/2) - this.player.getPosition().y;
		
		this.mainGameCanvas.setGlobalOffset(globalOffsetX, globalOffsetY);
		this.mainGameCanvas.setScreenCenter(new Point((windowWidth/2), (windowHeight/2)));
	}
	
	public void tick() {


		
	}
	
	public void frame() {
		// TODO: move to onResize
		this.mainGameCanvas.setScreenCenter(new Point((this.gameWindow.getWidth()/2), (this.gameWindow.getHeight()/2)));
		
		// move the player!!

		Position position = player.getPosition();
		
		if(player.getDirection() == Entity.DIRECTION.UP || player.getDirection() == Entity.DIRECTION.RIGHT_UP || player.getDirection() == Entity.DIRECTION.LEFT_UP) {
			position.y -= player.getSpeed();
		}

		if(player.getDirection() == Entity.DIRECTION.DOWN || player.getDirection() == Entity.DIRECTION.RIGHT_DOWN || player.getDirection() == Entity.DIRECTION.LEFT_DOWN) {
			position.y += player.getSpeed();
		}

		if(player.getDirection() == Entity.DIRECTION.LEFT || player.getDirection() == Entity.DIRECTION.LEFT_DOWN || player.getDirection() == Entity.DIRECTION.LEFT_UP) {
			position.x -= player.getSpeed();
		}

		if(player.getDirection() == Entity.DIRECTION.RIGHT || player.getDirection() == Entity.DIRECTION.RIGHT_UP || player.getDirection() == Entity.DIRECTION.RIGHT_DOWN) {
			position.x += player.getSpeed();
		}
		
		player.moveTo(position);
		
		double globalOffsetX = (this.gameWindow.getWidth()/2) - this.player.getPosition().x;
		double globalOffsetY = (this.gameWindow.getHeight()/2) - this.player.getPosition().y;
		

		Position mousePosition = Position.pointToPosition(this.mainGameCanvas.getMousePosition());
		
		if(mousePosition != null) {
			Label label = (Label)UIManager.getElementById("cords-onscreen");
			label.setText("Mouse on screen x / y : " + mousePosition.x + " / " + mousePosition.y);
			
			label = (Label)UIManager.getElementById("cords-onmap");
			label.setText("Mouse in world x / y : " + (mousePosition.x - mainGameCanvas.getGlobalOffsetX()) + " / " + (mousePosition.y - mainGameCanvas.getGlobalOffsetY()));

			label = (Label)UIManager.getElementById("cords-player");
			label.setText("Player x / y : " + player.getPosition().x + " / " + player.getPosition().y);

			label = (Label)UIManager.getElementById("entities-number");
			label.setText("entities : " + this.entityManager.count());

			label = (Label)UIManager.getElementById("bullets-number");
			label.setText("bullets : " + this.entityManager.count("Bullet"));

			mousePosition.x += mainGameCanvas.getGlobalOffsetX(); mousePosition.y += mainGameCanvas.getGlobalOffsetY();
			player.setAngel(Toolbox.getAngle(new Position((this.gameWindow.getWidth()/2) + globalOffsetX, (this.gameWindow.getHeight()/2) + globalOffsetY), mousePosition) - 90);
			
		}
		
		this.mainGameCanvas.setGlobalOffset(globalOffsetX, globalOffsetY);

		this.entityManager.update();

		this.mainGameCanvas.repaint();
	}

	public class MouseListener extends MouseInput {

		/**
		 * Invoked when the mouse button has been clicked (pressed
		 * and released) on a component.
		 *
		 * @param e the event to be processed
		 */
		@Override
		public void mouseClicked(MouseEvent e) {

		}

		/**
		 * Invoked when a mouse button has been pressed on a component.
		 *
		 * @param event the event to be processed
		 */
		@Override
		public void mousePressed(MouseEvent event) {

			//Left mouse button clicked
			if(event.getButton() == MouseEvent.BUTTON1) {
				double x = player.getPosition().x;
				double y = player.getPosition().y;
				entityManager.addEntity(new Bullet(x, y, player.getAngel()));

				ArrayList<Entity> entities = entityManager.getEntitiesInRadius(player, 3000);
				Iterator<Entity> itr = entities.iterator();
				while(itr.hasNext()) {
					Entity e = itr.next();
					if(e.getClass().getSimpleName().equals("Zombie")) {
						Zombie z = (Zombie) e;
						z.setDetectionArea(3000.0);
					}
				}
			}
			// Right mouse button clicked
			else if(event.getButton() == MouseEvent.BUTTON3) {
				entityManager.addEntity(new Zombie(event.getX() - mainGameCanvas.getGlobalOffsetX(), event.getY() - mainGameCanvas.getGlobalOffsetY()));
			}


		}

		/**
		 * Invoked when a mouse button has been released on a component.
		 *
		 * @param e the event to be processed
		 */
		@Override
		public void mouseReleased(MouseEvent e) {

		}

		/**
		 * Invoked when the mouse enters a component.
		 *
		 * @param e the event to be processed
		 */
		@Override
		public void mouseEntered(MouseEvent e) {

		}

		/**
		 * Invoked when the mouse exits a component.
		 *
		 * @param e the event to be processed
		 */
		@Override
		public void mouseExited(MouseEvent e) {

		}
	}

	public class KeyListener extends KeyInput {

		@Override
		public void pressed(KeyEvent event) {
			int keyCode = event.getKeyCode();



			if(keyCode == KeyEvent.VK_SHIFT) {
				player.setSpeed(6);
			}


			// Moment WASD
			if(keyCode == KeyEvent.VK_W) {

				if(player.getDirection() == Entity.DIRECTION.LEFT) {
					player.setDirection(Entity.DIRECTION.LEFT_UP);
				} else if(player.getDirection() == Entity.DIRECTION.RIGHT) {
					player.setDirection(Entity.DIRECTION.RIGHT_UP);
				} else if(player.getDirection() != Entity.DIRECTION.LEFT_UP && player.getDirection() != Entity.DIRECTION.RIGHT_UP) {
					player.setDirection(Entity.DIRECTION.UP);
				}
			}
			if(keyCode == KeyEvent.VK_A) {

				if(player.getDirection() == Entity.DIRECTION.UP) {
					player.setDirection(Entity.DIRECTION.LEFT_UP);
				} else if(player.getDirection() == Entity.DIRECTION.DOWN) {
					player.setDirection(Entity.DIRECTION.LEFT_DOWN);
				} else if(player.getDirection() != Entity.DIRECTION.LEFT_UP && player.getDirection() != Entity.DIRECTION.LEFT_DOWN) {
					player.setDirection(Entity.DIRECTION.LEFT);
				}

			}
			if(keyCode == KeyEvent.VK_S) {

				if(player.getDirection() == Entity.DIRECTION.LEFT) {
					player.setDirection(Entity.DIRECTION.LEFT_DOWN);
				} else if(player.getDirection() == Entity.DIRECTION.RIGHT) {
					player.setDirection(Entity.DIRECTION.RIGHT_DOWN);
				} else if(player.getDirection() != Entity.DIRECTION.LEFT_DOWN && player.getDirection() != Entity.DIRECTION.RIGHT_DOWN) {
					player.setDirection(Entity.DIRECTION.DOWN);
				}

			}
			if(keyCode == KeyEvent.VK_D) {

				if(player.getDirection() == Entity.DIRECTION.UP) {
					player.setDirection(Entity.DIRECTION.RIGHT_UP);
				} else if(player.getDirection() == Entity.DIRECTION.DOWN) {
					player.setDirection(Entity.DIRECTION.RIGHT_DOWN);
				} else if(player.getDirection() != Entity.DIRECTION.RIGHT_UP && player.getDirection() != Entity.DIRECTION.RIGHT_DOWN) {
					player.setDirection(Entity.DIRECTION.RIGHT);
				}
			}
		}

		@Override
		public void released(KeyEvent event) {
			int keyCode = event.getKeyCode();

			if(keyCode == KeyEvent.VK_SHIFT) {
				player.setSpeed(4);
			}

			//movement WASD
			if(keyCode == KeyEvent.VK_W) {

				if(player.getDirection() == Entity.DIRECTION.LEFT_UP) {
					player.setDirection(Entity.DIRECTION.LEFT);
				} else if(player.getDirection() == Entity.DIRECTION.RIGHT_UP) {
					player.setDirection(Entity.DIRECTION.RIGHT);
				} else if(player.getDirection() == Entity.DIRECTION.UP) {
					player.setDirection(null);
				}

			}
			if(keyCode == KeyEvent.VK_A) {

				if(player.getDirection() == Entity.DIRECTION.LEFT_UP) {
					player.setDirection(Entity.DIRECTION.UP);
				} else if(player.getDirection() == Entity.DIRECTION.LEFT_DOWN) {
					player.setDirection(Entity.DIRECTION.DOWN);
				} else if(player.getDirection() == Entity.DIRECTION.LEFT) {
					player.setDirection(null);
				}

			}
			if(keyCode == KeyEvent.VK_S) {

				if(player.getDirection() == Entity.DIRECTION.LEFT_DOWN) {
					player.setDirection(Entity.DIRECTION.LEFT);
				} else if(player.getDirection() == Entity.DIRECTION.RIGHT_DOWN) {
					player.setDirection(Entity.DIRECTION.RIGHT);
				} else if(player.getDirection() == Entity.DIRECTION.DOWN) {
					player.setDirection(null);
				}

			}
			if(keyCode == KeyEvent.VK_D) {

				if(player.getDirection() == Entity.DIRECTION.RIGHT_UP) {
					player.setDirection(Entity.DIRECTION.UP);
				} else if(player.getDirection() == Entity.DIRECTION.RIGHT_DOWN) {
					player.setDirection(Entity.DIRECTION.DOWN);
				} else if(player.getDirection() == Entity.DIRECTION.RIGHT) {
					player.setDirection(null);
				}

			}
		}
	}
	
}
