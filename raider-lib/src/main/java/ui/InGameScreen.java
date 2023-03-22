package ui;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;

/**
 * creates a class for ingame screen and initializes that screen. Allows for better screen management
 * @author Kevin Lorinc
 *
 */
public class InGameScreen extends GameScreen {
	  public static final String NAME = "INGAME-SCREEN";
	  
	  /**
	   * creates an ingame sreen with the name INGAME_SCREEN
	   */
	  public InGameScreen() {
	    super(NAME);
	  }
	}
