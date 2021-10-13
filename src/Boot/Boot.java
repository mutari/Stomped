package Boot;

import Canvas.MainGameCanvas;
import Stomped.Stomped;

public class Boot {
	
	private Window window;
	private MainGameCanvas mainGameCanvas;
	private Stomped stomped;
	
	public Boot() {
		this.window = new Window(1800, 1200);
		this.window.display();
		
		this.mainGameCanvas = new MainGameCanvas();
		
		this.window.setCanvas(this.mainGameCanvas);
		
		this.stomped = new Stomped(this.window, this.mainGameCanvas);
		this.stomped.init();
		
		// set upp game loop!
		long fps = 60;
		long compareTime = 1000000000/fps;
		long lastFrameTime = 0;
		
		// game loop
		while(true) {
			
			long now = System.nanoTime();
			
			this.stomped.tick();
			
			//lock it to x fps
			if((now - lastFrameTime) >= compareTime) {
				this.stomped.frame();
				
				lastFrameTime = now;
			}
			
		}
	
	}
	
	public static void main(String [] args) {
		new Boot();
	}
	
}
