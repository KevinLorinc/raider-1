package raider;

import de.gurkenlabs.litiengine.*;
import de.gurkenlabs.litiengine.resources.Resources;
import ui.InGameScreen;

/**
 * runs the game Raiders
 * @author Kevin Lorinc
 *
 */
public class Main {
	/**
	 * the main program that executes raiders
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		Game.info().setName("Raider");
		Game.info().setVersion("v1.0.0");
		
		Game.init(args);
		
		RaidersLogic.init();
		PlayerInput.init();
		
		Resources.load("game.litidata");
		
		Game.screens().add(new InGameScreen());
		
		Game.world().loadEnvironment("level0.tmx"); 
		
		Game.start();
	}

}
