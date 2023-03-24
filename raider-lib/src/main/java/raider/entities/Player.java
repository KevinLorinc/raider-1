package raider.entities;

import java.awt.event.KeyEvent;


import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.IMovementController;

/**
 * the class that creates the entity of player
 * @author Kevin Lorinc
 *
 */
@EntityInfo(width = 11, height = 20)
@MovementInfo(velocity = 30)
@CollisionInfo(collisionBoxWidth = 5, collisionBoxHeight = 8, collision = true)
@CombatInfo(hitpoints = 5, team = 1)
public class Player extends Creature implements IUpdateable{
	/**
	 * various constants for the state of a player which will be more deeply dealt with in the UI package.
	 * @author Kevin Lorinc
	 *
	 */
	public enum PlayerState {
		CONTROLLABLE,
		LOCKED
	}
	
	private static Player instance;
	private PlayerState state = PlayerState.CONTROLLABLE;//for testing purposes might need to be changed to Controllable once we get litidata in
	
	private Player() {
		super("raider");
		
		KeyboardEntityController<Player> movementController = new KeyboardEntityController<>(this);
		movementController.addUpKey(KeyEvent.VK_W);
		movementController.addDownKey(KeyEvent.VK_S);
		movementController.addLeftKey(KeyEvent.VK_A);
		movementController.addRightKey(KeyEvent.VK_D);
	  
		this.setController(IMovementController.class, movementController);
		movementController.onMovementCheck(e -> {//this line may cause the whole thing to not work properly
	      return this.getState() == PlayerState.CONTROLLABLE;
	    });
	}
	
	/**
	 * creates a new instance or returns the existing instance
	 * @return the instance of the player class 
	 */
	public static Player instance() {
		if(instance == null) {instance = new Player();}
		
		return instance;
	}
	
	//Nothing to be done here yet
	@Override
	public void update() {};
	
	/**
	 * gets the state of the player
	 * @return the state of the player
	 */
	public PlayerState getState() {
	  return state;
	}
	
	/**
	 * allows you to change the state of the player
	 * @param state the state to change to
	 */
	public void setState(PlayerState state) {
	  this.state = state;
	}
}
