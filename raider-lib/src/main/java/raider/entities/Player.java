package raider.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.graphics.CreatureShadowImageEffect;
import de.gurkenlabs.litiengine.graphics.IRenderable;
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
@MovementInfo(velocity = 60)
@CollisionInfo(collisionBoxWidth = 5, collisionBoxHeight = 8, collision = true)
@CombatInfo(hitpoints = 5, team = 1)
public class Player extends Creature implements IRenderable, IUpdateable{
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
	
	//Nothing to be done here yet
	@Override
	public void update() {};
	
	@Override
	protected IEntityAnimationController<?> createAnimationController() {
		IEntityAnimationController<?> controller = new CreatureAnimationController<>(this, false);
		System.out.println(Resources.spritesheets().getAll());
	    controller.add(new Animation("raider-idle-right.png", true, true));
	    controller.add(new Animation("raider-walk-right.png", true, true));

	    controller.addRule(x -> Player.instance().isIdle(), x -> "raider-idle-right.png");
	    //controller.addRule(x -> GameManager.isHarvesting(), x -> "keeper-celebrate");
	    //controller.addRule(x -> GameManager.isLevelFailed(), x -> "keeper-pout"); can add rules such as this later
	
	    CreatureShadowImageEffect effect = new CreatureShadowImageEffect(this, new Color(24, 30, 28, 100));
	    effect.setOffsetY(1);
	    controller.add(effect);
	    return controller;
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
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
}
