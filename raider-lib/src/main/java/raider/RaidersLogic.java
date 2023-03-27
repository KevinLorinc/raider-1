package raider;


import java.awt.Color;
import java.awt.event.KeyEvent;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.environment.EnvironmentListener;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.CreatureShadowImageEffect;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.CreatureAnimationController;
import de.gurkenlabs.litiengine.graphics.animation.IEntityAnimationController;
import de.gurkenlabs.litiengine.gui.Appearance;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.gurkenlabs.litiengine.resources.ResourceBundle;
import de.gurkenlabs.litiengine.resources.Resources;
import raider.entities.Player;

/**
 * a class that handles the logic for the Raiders game
 * @author Kevin Lorinc
 *
 */
public final class RaidersLogic{
	/**
	 * creates various constants for the state in a game
	 * @author Kevin Lorinc
	 *
	 */
	public enum GameState {
		INGAME,
		MENU,
		INGAME_MENU,
		INVENTORY
	}
	
	private static GameState state = GameState.INGAME;//this will be subject to change
	
	private RaidersLogic() {};
	
	/**
	 * initializes the logic for the Raiders game
	 */
	public static void init() {
	    /*Game.world().addListener(new EnvironmentListener() {
	    	 @Override
	         public void initialized(Environment e) {
	    		 Camera camera = new PositionLockCamera(Player.instance());
	    		 camera.setClampToMap(true);
	    		 Game.world().setCamera(camera);
	    		 
	    		 Spawnpoint spawn = e.getSpawnpoint("enter");
	    	     if (spawn != null) {
	    	       spawn.spawn(Player.instance());
	    	     }
	    	 }
	    });*/
	    
	    Game.world().onLoaded(e -> {
	    	Camera camera = new PositionLockCamera(Player.instance());
   		 	camera.setClampToMap(true);
   		 	Game.world().setCamera(camera);
	    	setState(GameState.INGAME);
	        Player.instance().getHitPoints().setToMax();
	        Player.instance().setIndestructible(false);
	        Player.instance().setCollision(true);

	        // spawn the player instance on the spawn point with the name "enter"
	        Spawnpoint enter =  e.getSpawnpoint("enter");
	        if (enter != null) {
	          enter.spawn(Player.instance());
	          
	          System.out.println(Resources.spritesheets().contains("raider-idle-right"));
	          System.out.println(Player.instance().getLocation());
	          System.out.println(Player.instance().animations());
	        }
	      });
	}
	
	/**
	 * gets the state of the game
	 * @return the game state
	 */
	public static GameState getState() {
	  return state;
	}
	
	/**
	 * allows you to change the state of the game and applies the logic behind it like opening the inventory or menu
	 * @param state the state you wish to change to
	 */
	public static void setState(GameState state) {
		RaidersLogic.state = state;
	}
}
