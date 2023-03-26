package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.RenderEngine;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import raider.entities.Player;
import raider.entities.Player.PlayerState;

/**
 * a class that creates the Hud for the game. This will have arrows, probably inventory, and health related things in it.
 * This will have some animation controllers in it
 * @author Kevin Lorinc
 *
 */
public class Hud extends GuiComponent{
	
	/**
	 * creates an instance of the Hud class
	 */
	protected Hud() {
		super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());
		//add amination controllers here
	}

	/**
	 * renders all the components of the hud
	 */
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		
		this.renderHP(g);
		//when we add enemies uncomment the next line
		this.renderEnemyHP(g);
	}
	
	
	/**
	 * renders the enemies hp which is situated right above them when they are in combat
	 * @param g the graphic to render to
	 */
	private void renderEnemyHP(Graphics2D g) {
		//uncomment once we create the enemy class
		/*for (Enemy enemy : Game.world().environment().getByTag(Enemy.class)) {
		      if (enemy.isEngaged() && !enemy.isDead()) {
		      	RenderEngine renderEngine = new RenderEngine();//may cause problems
		        final double width = 16;
		        final double height = 2;
		        double x = enemy.getX() - (width - enemy.getWidth()) / 2.0;
		        double y = enemy.getY() - height * 2;
		        RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, 1.5, 1.5);

		        final double currentWidth = width * (enemy.getHitPoints().getCurrentValue() / (double) enemy.getHitPoints().getMaxValue());
		        RoundRectangle2D actualRect = new RoundRectangle2D.Double(x, y, currentWidth, height, 1.5, 1.5);

		        g.setColor(Color.BLACK);
		        renderEngine.renderShape(g, rect);

		        g.setColor(new Color(228, 59, 68));
		        renderEngine.renderShape(g, actualRect);
		      }*/
		
	}

	/**
	 * renders the players hp
	 * @param g the graphic to render the players hp to
	 */
	private void renderHP(Graphics2D g) {
		if(Player.instance().getState() == PlayerState.INCOMBAT) {
			RenderEngine renderEngine = new RenderEngine();//may cause problems
			
			final double width = 64;//16
	        final double height = 6;//2
	        double x = 16;//Player.instance().getX() - (width - Player.instance().getWidth()) / 2.0;
	        double y = 16;//Player.instance().getY() - height * 2;
	        RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, 1.5, 1.5);
	        
	        final double currentWidth = width * (Player.instance().getHitPoints().getRelativeCurrentValue() / (double) Player.instance().getHitPoints().getMax());
	        RoundRectangle2D actualRect = new RoundRectangle2D.Double(x, y, currentWidth, height, 1.5, 1.5);

	        g.setColor(Color.BLACK);
	        renderEngine.renderShape(g, rect);

	        g.setColor(new Color(228, 59, 68));
			renderEngine.renderShape(g, actualRect);
		}
	}
}
