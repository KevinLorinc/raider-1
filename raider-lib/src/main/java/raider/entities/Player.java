package raider.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.graphics.CreatureShadowImageEffect;
import de.gurkenlabs.litiengine.graphics.IRenderable;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.CreatureAnimationController;
import de.gurkenlabs.litiengine.graphics.animation.IAnimationController;
import de.gurkenlabs.litiengine.graphics.animation.IEntityAnimationController;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.gurkenlabs.litiengine.resources.Resource;
import de.gurkenlabs.litiengine.resources.Resources;

/**
 * the class that creates the entity of player
 * @author Kevin Lorinc
 *
 */
@EntityInfo(width = 11, height = 20)
@MovementInfo(velocity = 80)
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
		LOCKED,
		INCOMBAT
	}
	
	private static Player instance;
	private PlayerState state = PlayerState.CONTROLLABLE;//for testing purposes might need to be changed to Controllable once we get litidata in
	
	/**
	 * creates the instance of the player class and its movement controller
	 */
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
	
	@Override
	protected IEntityAnimationController<?> createAnimationController() {
		Spritesheet idle = Resources.spritesheets().get("raider-idle-right");
		Spritesheet walk = Resources.spritesheets().get("raider-walk-right");
		
		IEntityAnimationController<?> animationController = new CreatureAnimationController<Player>(this,new Animation(idle,false));
		
	    animationController.add(new Animation(walk,true));
	    
		animationController.addRule(x -> (this.getFacingDirection() == Direction.LEFT) && this.isIdle(), x -> "raider-idle-left");
		animationController.addRule(x -> (this.getFacingDirection() == Direction.RIGHT) && this.isIdle(), x -> "raider-idle-right");
		animationController.addRule(x -> (this.getFacingDirection() == Direction.UP) && !this.isIdle(), x -> "raider-walk-right");
		
	    CreatureShadowImageEffect effect = new CreatureShadowImageEffect(this, new Color(24, 30, 28, 100));
	    effect.setOffsetY(1);
	    animationController.add(effect);
	    
	    return animationController;
	}
	
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

	@Override
	public void update() {
	}
}
