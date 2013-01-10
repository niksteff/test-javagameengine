package novelPacket;

import gameReals.*;
import gameAbstracts.*;
import Handler.*;
import Handler.EntityHandler.EntityTypes;
import NovelHelper.*;

import java.util.LinkedList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Controllers;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.*;

/**
 * 
 * @author DSteffen
 */
public class NovelPacket {

	private static final boolean _DEBUG = true;

	// System Stuff
	String _OSTYPE = null;
	
	// Display Stuff
	private static final int _SCALE = 3;
	private static final int _ASPECTW = 4;
	private static final int _ASPECTH = 3;
	private static final int _WIDTH = 320;
	private static final int _HEIGHT = _WIDTH / _ASPECTW * _ASPECTH;
	private static String _TITLE = "GameTest";

	// Render Stuff
	private static final int _FPS = 60;
	private static final int _TPS = 60;
	private LinkedList<gameAbstracts.Character> _Lcharacter = new LinkedList();
	private LinkedList<gameAbstracts.Entity> _Lentities = new LinkedList();

	// Input Stuff
	private Controller _MainGamepad;
	private static final int _BUTTONSC = 4;
	private static final int _AXISC = 4;
	private static final float _GAMEPAD_DEADZONE = 0.3F;
	private ControllerType _MainGamepadType;
	private float[] _InitialAxisValues;

	// Game mechanic
	private Player _PLAYER;
	private float _SPEEDADDMODIFY = 2;
	private CollisionHandler _CollHandler;
	
	private TextureTile _BACKGROUND;
	private Wall _w1;
	/**
	 * Main start Function for the Game
	 * 
	 * @param args
	 */
	public void StartGame() {
		System.out.println("Width: " + _WIDTH);
		System.out.println("Height: " + _HEIGHT);
		
		try {
			Display.setDisplayMode(new DisplayMode(_WIDTH * _SCALE, _HEIGHT * _SCALE));
			Display.setTitle(_TITLE);
			Display.create();

			// Set up OpenGL
			InitializeOpenGL();

			// Initialize controller
			_MainGamepadType = new ControllerType();
			InitializeGamepads();
			System.out.println("Controller Type: " + this._MainGamepadType.GetGamepadType());

			// Create the background for testing
			//TODO Should not be here
			_BACKGROUND = ((TextureTile) EntityHandler.GetEntityFromEnum(EntityTypes.GRAS)).InitializeTile(0, 0, _WIDTH * _SCALE, _HEIGHT * _SCALE);

			// Create a player object here and than draw it :D
			_PLAYER = new Player("Player", "Player", 0, false, 100, 32, 32, "res/player_default.png", "PNG", 0, 0);
			this._Lcharacter.addLast(_PLAYER);
			
			//Initialize the handler for characters
			_CollHandler = new CollisionHandler(_PLAYER);
			
			// Create a wall for testing
			_w1 = new Wall(200, 200, 48, 48, "res/wall_default.png", "PNG", "WALL", "Wall", 0);
			_CollHandler.InsertWallTile(_w1);			
			
			// FPS Counter
			long lastTime = System.nanoTime();
			long timer = System.currentTimeMillis();
			double delta = 0;
			double ns = 1000000000.0 / 60.0;
			int ticks = 0;
			int frames = 0;

			while (!Display.isCloseRequested()) {

				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;

				// Will only happen 60 times a second
				while (delta >= 1) {
					// Do this 60 times a second
					// TODO - think this is already done because of the limiter
					GameLoop();
					// Get all the inputs from user
					PullInput();

					ticks++;
					delta--;
				}
				frames++;

				if (System.currentTimeMillis() - timer > 1000) {
					Display.setTitle(_TITLE + " | TPS: " + ticks + " , " + "FPS: " + frames);
					frames = 0;
					ticks = 0;
					timer += 1000;
				}

				// TODO Render the screen stuff
				// Prepare the lists etc...
				// Should prepare the buffers etc
				// Render();

				// Draw all the graphics
				Draw();

				// Update the screen
				Display.update();
				Display.sync(_FPS);
			}
			Display.destroy();
			System.exit(0);
		} catch (LWJGLException e) {
			if (_DEBUG) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Contains all the enemy action etc ... Collision etc
	 */
	private void GameLoop() {
		_CollHandler.CheckWorldWithPlayer();
	}

	/*
	 * Draws all the characters and tiles in the level array to the screen
	 */
	private void Draw() {
		// Clear the screen before drawing something
		glClear(GL_COLOR_BUFFER_BIT);

		_BACKGROUND.Draw();
		_w1.Draw();
		ListIterator<gameAbstracts.Character> listIT = _Lcharacter.listIterator();
		while (listIT.hasNext()) {
			listIT.next().Draw();
		}
		listIT = null;
	}

	/**
	 * User Input
	 */
	private void PullInput() {
		/*
		 * Keyboard inputs Use this for action buttons rather then movement
		 * buttons because the movement will just react once when the button is
		 * pressed not loop like
		 */
		// while(Keyboard.next())
		// {
		// if(Keyboard.getEventKeyState())
		// {
		// if(Keyboard.getEventKey() == Keyboard.KEY_UP)
		// {
		// // Do ^ stuff
		// System.out.println("Going UP!");
		// c
		// }
		// else if(Keyboard.getEventKey() == Keyboard.KEY_DOWN)
		// {
		// // Do v stuff
		// System.out.println("Going DOWN!");
		// _PLAYER.set_Y(_PLAYER.get_Y()+1);
		// }
		// else if(Keyboard.getEventKey() == Keyboard.KEY_LEFT)
		// {
		// // Do < stuff
		// System.out.println("Going LEFT!");
		// _PLAYER.set_X(_PLAYER.get_X()-1);
		// }
		// else if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT)
		// {
		// // Do > stuff
		// System.out.println("Going RIGHT!");
		// _PLAYER.set_X(_PLAYER.get_X()+1);
		// }
		//
		// //TODO Buttons like jump etc
		// }
		// }

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			_PLAYER.set_Y(_PLAYER.get_Y() - _SPEEDADDMODIFY);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			_PLAYER.set_Y(_PLAYER.get_Y() + _SPEEDADDMODIFY);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			_PLAYER.set_X(_PLAYER.get_X() - _SPEEDADDMODIFY);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			_PLAYER.set_X(_PLAYER.get_X() + _SPEEDADDMODIFY);
		}

		/*****************************************************************************/

		// Gamepad
		// TODO make sure this only works for xbox 360 controller
		if (_MainGamepad != null) {
			if (_MainGamepad.isButtonPressed(0)) {
				System.out.println("Button A pressed");
			} else if (_MainGamepad.isButtonPressed(1)) {
				System.out.println("Button B pressed");
			} else if (_MainGamepad.isButtonPressed(2)) {
				System.out.println("Button X pressed");
			} else if (_MainGamepad.isButtonPressed(3)) {
				System.out.println("Button Y pressed");
			} else if (_MainGamepad.isButtonPressed(4)) {
				System.out.println("Button Left Button pressed");
			} else if (_MainGamepad.isButtonPressed(5)) {
				System.out.println("Button Right Button pressed");
			} else if (_MainGamepad.isButtonPressed(6)) {
				System.out.println("Button BACK pressed");
			} else if (_MainGamepad.isButtonPressed(7)) {
				System.out.println("Button START pressed");
			} else if (_MainGamepad.isButtonPressed(8)) {
				System.out.println("Button LEFT STICK  pressed");
			} else if (_MainGamepad.isButtonPressed(9)) {
				System.out.println("Button RIGHT STICK pressed");
			}

			/*****************************************************************************/

			/*
			 * The two analog sticks
			 */

			// Right Analog Stick
			float RXVAL = _MainGamepad.getRXAxisValue();
			if (RXVAL >= _GAMEPAD_DEADZONE) /* Right Stick > Movement */
			{
				// System.out.println("RXVAL > " + RXVAL);
			} else if (RXVAL <= (_GAMEPAD_DEADZONE * -1)) /*
														 * Right Stick <
														 * Movement
														 */
			{
				// System.out.println("RXVAL < " + RXVAL);
			}
			float RYVAL = _MainGamepad.getRYAxisValue();
			if (RYVAL >= _GAMEPAD_DEADZONE) /* Right Stick v Movement */
			{
				// System.out.println("RYVAL V" + RYVAL);
			} else if (RYVAL <= (_GAMEPAD_DEADZONE * -1)) /*
														 * Right Stick ^
														 * Movement
														 */
			{
				// System.out.println("RYVAL ^" + RYVAL);
			}

			/*****************************************************************************/

			// Left Analog Stick
			float XVAL = _MainGamepad.getXAxisValue();
			if (XVAL >= _GAMEPAD_DEADZONE) /* Left Stick > Movement */
			{
				// System.out.println("XVAL: > " + XVAL);
				_PLAYER.set_X(_PLAYER.get_X() + _SPEEDADDMODIFY);
			} else if (XVAL <= (_GAMEPAD_DEADZONE * -1)) /* Left Stick < Movement */
			{
				// System.out.println("XVAL: < " + XVAL);
				_PLAYER.set_X(_PLAYER.get_X() - _SPEEDADDMODIFY);
			}
			float YVAL = _MainGamepad.getYAxisValue();
			if (YVAL >= _GAMEPAD_DEADZONE) /* Left Stick v Movement */
			{
				// System.out.println("YVAL: V " + YVAL);
				_PLAYER.set_Y(_PLAYER.get_Y() + _SPEEDADDMODIFY);
			} else if (YVAL <= (_GAMEPAD_DEADZONE * -1)) /* Left Stick ^ Movement */
			{
				// System.out.println("YVAL: ^ " + YVAL);
				_PLAYER.set_Y(_PLAYER.get_Y() - _SPEEDADDMODIFY);
			}

			/*****************************************************************************/

			// Trigger on the top of the Gamepad
			float ZVAL = _MainGamepad.getZAxisValue();
			if (ZVAL >= _GAMEPAD_DEADZONE) /* Left Trigger */
			{
				// System.out.println("ZVAL: V " + ZVAL);
			} else if (ZVAL <= (_GAMEPAD_DEADZONE * -1)) /* Right Trigger */
			{
				// System.out.println("ZVAL: ^ " + ZVAL);
			}

			/*****************************************************************************/

			// The DPAD
			float PovY = _MainGamepad.getPovY();
			float PovX = _MainGamepad.getPovX();

			if (PovY >= _GAMEPAD_DEADZONE) /* DPad Movement V */
			{
				// System.out.println("PovY: V " + PovY);
				_PLAYER.set_Y(_PLAYER.get_Y() + _SPEEDADDMODIFY);
			} else if (PovY <= (_GAMEPAD_DEADZONE * -1)) /* Dpad Movement ^ */
			{
				// System.out.println("PovY: ^ " + PovY);
				_PLAYER.set_Y(_PLAYER.get_Y() - _SPEEDADDMODIFY);
			} else if (PovX >= _GAMEPAD_DEADZONE) /* DPad Movement > */
			{
				// System.out.println("PovX: > " + PovX);
				_PLAYER.set_X(_PLAYER.get_X() + _SPEEDADDMODIFY);
			} else if (PovX <= (_GAMEPAD_DEADZONE * -1)) /* Dpad Movement < */
			{
				// System.out.println("PovX: < " + PovX);
				_PLAYER.set_X(_PLAYER.get_X() - _SPEEDADDMODIFY);
			}

			/*****************************************************************************/

		}
	}

	/*
	 * Initializes the OpenGL for this Display
	 */
	private void InitializeOpenGL() {
		// Initialize OpenGL
		// GL11.glMatrixMode(mode) only if GL11 not imported static
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, _WIDTH * _SCALE, _HEIGHT * _SCALE, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		// TODO Not sure if this is needed, does not seem so ...
		// glEnable(GL_BLEND);
		glEnable(GL_ALPHA_TEST);
		glAlphaFunc(GL_GREATER, 0);
	}

	/**
	 * Initializes the gamepad for the system
	 */
	private void InitializeGamepads() {
		// Create the controllers
		try {
			Controllers.create();
			int controllersC = Controllers.getControllerCount();
			System.out.println("Controllers count: " + controllersC);

			for (int i = 0; i < controllersC; i++) {
				Controller gamepad = Controllers.getController(i);
				// Check for 'so much' buttons
				if (gamepad.getAxisCount() >= _AXISC && gamepad.getButtonCount() >= _BUTTONSC) {
					// Set the dead zones for the gamepad
					for (int j = 0; j < 4; j++) {
						gamepad.setDeadZone(j, _GAMEPAD_DEADZONE);
					}

					_MainGamepad = gamepad;
					// Check for controller type here
					CheckGamepadType();

					_InitialAxisValues = new float[4];
					for (int j = 0; j < 4; j++) {
						_InitialAxisValues[j] = gamepad.getAxisValue(j);
					}
				}
			}
		} catch (Exception e) {
			if (_DEBUG) {
				System.out.println("Could not initialize a gamepad because of " + e);
			}
		}
	}

	/**
	 * Check for the gamepad type ... Xbox Controller or different one
	 */
	private void CheckGamepadType() {
		Set<String> axes = new HashSet<String>();
		for (int i = 0; i < _MainGamepad.getAxisCount(); i++) {
			axes.add(_MainGamepad.getAxisName(i).toLowerCase());
		}
		if (axes.contains("y-achse") && axes.contains("x-achse") && axes.contains("y-rotation") && axes.contains("x-rotation")) {
			_MainGamepadType.SetXBOX360();
		} else if (axes.contains("y axis") && axes.contains("x axis") && axes.contains("y rotation") && axes.contains("x rotation")) {
			_MainGamepadType.SetXBOX360();
		} else {
			_MainGamepadType.SetGENERIC();
		}
	}

	/**
	 * Checks if the controller is initialized probably
	 */
	private boolean ControllerIsInitialized() {
		if (this._MainGamepad == null) {
			return false;
		} else if (_InitialAxisValues == null) {
			return true;
		}

		for (int i = 0; i < _AXISC; i++) {
			if (_MainGamepad.getAxisValue(i) == _InitialAxisValues[i] && _InitialAxisValues[i] != 0.0f) {
				return false;
			}
		}
		_InitialAxisValues = null;
		return true;
	}

	/**
	 * Returns the Gamepad for the game
	 */
	private Controller GetGamepad() {
		if (!ControllerIsInitialized()) {
			return null;
		} else {
			return this._MainGamepad;
		}
	}

	/**
	 * Returns the Type of the controller Either XBOX360 Or GENERIC
	 */
	private ControllerType GetControllerType() {
		return this._MainGamepadType;
	}

	/**
	 * @return the Debug
	 */
	public static boolean isDebug() {
		return _DEBUG;
	}

	/**
	 * Main starting point
	 * @param args
	 */
	public static void main(String[] args) {
		NovelPacket displayTest = new NovelPacket();
		displayTest.StartGame();
	}

}
