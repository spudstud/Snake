package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import controller.ConvertLedType;

/**
 * Game has a mode, a paused status, number of players and cube type
 * 
 * @author Spencer Owen
 * @version 1.0
 */
public class Game {
	public Logger logger = Logger.getLogger(this.getClass());

	private int numberOfPlayers;
	private int cubeType;
	private int gameMode;
	private boolean gamePaused;

	public List<Apple> aListOfApples = new ArrayList<Apple>();
	public List<Snake> aListOfSnakes = new ArrayList<Snake>();

	/**
	 * Constructor for objects of class Game
	 */
	public Game() {
		this.numberOfPlayers = 1;
		this.cubeType = cubeType;
		;
		this.gameMode = gameMode;
		this.gamePaused = true;
	}

	public Game(int numberOfPlayers, int cubeType, int gameMode) {

		this.numberOfPlayers = numberOfPlayers;
		this.cubeType = cubeType;
		;
		this.gameMode = gameMode;
		this.gamePaused = true;

	}

	public boolean isGamePaused() {
		return this.gamePaused;
	}

	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}

	public List<Apple> getaListOfApples() {
		return aListOfApples;
	}

	public void setaListOfApples(ArrayList<Apple> aListOfApples) {
		this.aListOfApples = aListOfApples;
	}

	public List<Snake> getaListOfSnakes() {
		return aListOfSnakes;
	}

	public void setaListOfSnakes(ArrayList<Snake> aListOfSnakes) {
		this.aListOfSnakes = aListOfSnakes;
	}

	public void createSnakes(int NumberOfSnakesToCreate) {
		model.Snake aReusableSnake;

		if (NumberOfSnakesToCreate >= 1) {
			logger.info("Creating snake #1 of " + NumberOfSnakesToCreate);
			// Create 4 snake objects, the 4 players are hard coded
			int playerOneColor = controller.ConvertLedType.hexToInt("0000FF");
			int playerOneDirection = 3; // Snake is traveling west
			ArrayList<Integer> playerOneBodyPositions = new ArrayList<Integer>();
			playerOneBodyPositions.add(13);
			playerOneBodyPositions.add(14);
			playerOneBodyPositions.add(15);
			int playerOneSpeed = 750;

			aReusableSnake = new model.Snake(playerOneColor,
					playerOneDirection, playerOneBodyPositions, playerOneSpeed);
			// No real need to specify this. but I like verbosity in code
			this.aListOfSnakes.add(aReusableSnake);

		}

		if (NumberOfSnakesToCreate >= 2) {
			logger.info("Creating snake #2 of " + NumberOfSnakesToCreate);
			// Create 4 snake objects, the 4 players are hard coded
			int playerTwoColor = controller.ConvertLedType.hexToInt("00FF00");
			int playerTwoDirection = 0; // Snake is traveling north
			ArrayList<Integer> playerTwoBodyPositions = new ArrayList<Integer>();
			playerTwoBodyPositions.add(512);
			playerTwoBodyPositions.add(256);
			playerTwoBodyPositions.add(0);
			int playerTwoSpeed = 750;
			aReusableSnake = new model.Snake(playerTwoColor,
					playerTwoDirection, playerTwoBodyPositions, playerTwoSpeed);
			this.aListOfSnakes.add(aReusableSnake);

		}

		if (NumberOfSnakesToCreate >= 3) {
			logger.info("Creating snake #3 of " + NumberOfSnakesToCreate);
			// Create 4 snake objects, the 4 players are hard coded
			int playerThreeColor = controller.ConvertLedType.hexToInt("FF00FF");
			int playerThreeDirection = 0; // Snake is traveling north
			ArrayList<Integer> playerThreeBodyPositions = new ArrayList<Integer>();
			playerThreeBodyPositions.add(3840);
			playerThreeBodyPositions.add(3841);
			playerThreeBodyPositions.add(3842);
			int playerThreeSpeed = 750;
			aReusableSnake = new model.Snake(playerThreeColor,
					playerThreeDirection, playerThreeBodyPositions,
					playerThreeSpeed);
			this.aListOfSnakes.add(aReusableSnake);

		}

		if (NumberOfSnakesToCreate == 4) {
			logger.info("Creating snake #4 of " + NumberOfSnakesToCreate);
			// Create 4 snake objects, the 4 players are hard coded
			int playerFourColor = controller.ConvertLedType.hexToInt("FF0000");
			int playerFourDirection = 0; // Snake is traveling north
			ArrayList<Integer> playerFourBodyPositions = new ArrayList<Integer>();
			playerFourBodyPositions.add(3855);
			playerFourBodyPositions.add(3601);
			playerFourBodyPositions.add(3346);
			int playerFourSpeed = 750;

			aReusableSnake = new model.Snake(playerFourColor,
					playerFourDirection, playerFourBodyPositions,
					playerFourSpeed);
			this.aListOfSnakes.add(aReusableSnake);

		}

		if (NumberOfSnakesToCreate < 1 || NumberOfSnakesToCreate > 4) {
			// controller.SnakeGame.logger.fatal("Game.createSnakes can only receive numbers between 1 and 4, received: "
			// + NumberOfSnakesToCreate);
			throw new IllegalArgumentException(
					"Game.createSnakes can only receive numbers between 1 and 4, received: "
							+ NumberOfSnakesToCreate);

		}

		// Null out the object to mark it for garbage collection
		aReusableSnake = null;

	}

	public void createApple(int appleColor) {

		int temporaryAppleLocation;
		int minumumLed = 0;
		// //Get the highest number in the Arena and generate a random number
		// less than it
		int maximumLed = CubeAttributes.getNumberOfLedsPerCube() - 1;
		// logger.debug("Maximum led = " + maximumLed + ", Arena.xMaximum="+
		// CubeAttributes.xNumberOfLedsPerRow-1 +", Arena.yMaximum="+
		// Arena.yMaximum + ", Arena.zMaximum="+ Arena.zMaximum);

		// Create a random number between 0 and the maximum number of LEDs
		Random aRandomNumber = new Random();
		temporaryAppleLocation = aRandomNumber.nextInt(maximumLed - minumumLed)
				+ minumumLed;
		// temporaryAppleLocation = aRandomNumber.nextInt(maximumLed ) ;
		logger.info("Created a random apple at " + temporaryAppleLocation);

		boolean appleCollisionCheck = false;
		boolean snakeCollisionCheck = false;

		// Keep creating new Apples until we have determined it is unique
		// do while statement are different then while because they do the
		// evaluation at the end of the loop
		do {
			// Assume the Apple is good unless we find a bad one
			appleCollisionCheck = true;

			// Look at every other apple in the arena and see if it is a
			// duplicate
			for (Apple otherApples : aListOfApples) {
				// If
				if (temporaryAppleLocation == otherApples.getAbsolutePosition()) {
					appleCollisionCheck = false;
				}

			}

			// Assume the snake is good unless we find a bad one
			snakeCollisionCheck = true;

			for (Snake otherSnakes : aListOfSnakes) {
				for (Integer bodyPositionsToCheck : otherSnakes
						.getBodyPositions()) {
					// Check each part of the snake
					if (temporaryAppleLocation == bodyPositionsToCheck) {
						snakeCollisionCheck = false;
					}
				}
			}

			if (appleCollisionCheck == false || snakeCollisionCheck == false) {

				logger.warn("Creating a new Apple since there was a collision at: "
						+ temporaryAppleLocation + " This is expected every 1 out of 4096 apples or so");
				// temporaryAppleLocation = aRandomNumber.nextInt(maximumLed -
				// minumumLed) + minumumLed;
				temporaryAppleLocation = aRandomNumber.nextInt(maximumLed);

				// Since we had a collision, reset both of the checks to false
				// This will go back to the beginning of the for loop and try again
				appleCollisionCheck = false;
				snakeCollisionCheck = false;

			}

		} while (appleCollisionCheck == false && snakeCollisionCheck == false);

		Apple anApple = new Apple(temporaryAppleLocation, appleColor);
		logger.info("Added a new apple with color:  " + appleColor
				+ " at location: " + temporaryAppleLocation);
		//Add the newly created apple to the array
		aListOfApples.add(anApple);

	}

	
	/**
	 * Removes the apple from the ArrayList of apple objects
	 * 
	 * @param anApple
	 * @see createApple
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public  void destroyApple(Apple anApple) {
		//A null apple throws a null pointer exception
		//A nonexistent apple throws a serious error and logs
		if(anApple==null) {
			logger.error("Game.destroyApple(anApple) was equal to Null");
			throw new NullPointerException("anApple is equal to Null");
		}
		
		//Checks if an apple object exists but is not in apple Array(aListOfApples)
		//A apple object not in the array of apples throws IllegalArgumentException and logs
		boolean appleFound = false;
		//Loop through the ArrayList and remove anApple
		for (int i = 0; i < aListOfApples.size(); i++){
			if (anApple.equals(aListOfApples.get(i))){
				//Take an apple and remove it from the array of apples
				aListOfApples.remove(i);
				logger.warn(ConvertLedType.intToHex(anApple.getColor())+" has been removed from aListOfApples");
				appleFound = true;
			}
		}
		if (appleFound==false){
			logger.error("Game.destroyApple(anApple) found an apple that wasn't in the aListOfApples array");
			throw new IllegalArgumentException("Game.destroyApple(anApple) found an apple that wasn't in the aListOfApples array");
		}		
	}

	public void checkHasCollidedWithSnakeBody(Snake aSnake) {

		// Iterates through all the bodies of all the snakes and sees it if it
		// has gone inside (collided) with another snake
		// If it has, then kill the snake, add to the logging level error
		logger.error("Snake be dead");
		// If it has not then add to the logging level debug
		logger.debug("Snake not be dead");

	}

	/**
	 * Returns whether the snake has collided with a wall Checks
	 * isSolidWalls==true to see if wall collisions kill the snake If the snake
	 * attempts to go past the wall and isSolidWalls = true; the snake is dead
	 * If isSolidWalls = false this method always returns false since the snake
	 * can never die from a wall collision in that situation.
	 * 
	 * A snake dies if the head position goes to -1 or greater than there are
	 * leds, either 8 or 16 for most cubes The cube is always assumed to be base
	 * 0. The first led in the cube is 0,0,0
	 * 
	 * @return true
	 * @return false
	 * @see bodyCollisionCheck
	 * @see appleCheck
	 */
	/*
	 * public boolean hasCollidedWithWall() { //Arena.getInstance(); //If the
	 * mode has wall collisions , check if ( Arena.isSolidWalls() == true) { if(
	 * ConvertLedType.absoluteToXPositionInRow(currentHeadPosition) >= 0 &&
	 * ConvertLedType.absoluteToXPositionInRow(currentHeadPosition) <=
	 * Arena.xMaximum &&
	 * ConvertLedType.absoluteToYPositionInPanel(currentHeadPosition) >= 0 &&
	 * ConvertLedType.absoluteToYPositionInPanel(currentHeadPosition) <=
	 * Arena.yMaximum &&
	 * ConvertLedType.absoluteToZPositionInCube(currentHeadPosition) >= 0 &&
	 * ConvertLedType.absoluteToZPositionInCube(currentHeadPosition) <=
	 * Arena.zMaximum ) {
	 * 
	 * //Player is inside all bounds return false;
	 * 
	 * } //Player has attempted to go outside walls, player is dead else return
	 * true;
	 * 
	 * } //If the walls are turned off else { //If the walls are invisible, then
	 * we cant possibly die by hitting them now can we. return false; }
	 * 
	 * 
	 * }//end wallCollisionCheck()
	 */

	public boolean checkTimeout(model.Snake aSnake) {
		// Get the current Time in milliseconds
		long currentTime = System.currentTimeMillis();

		// Get the snake last advance time
		long lastMove = aSnake.getLastMoveTime();

		// Get the snake speed
		int snakeSpeed = aSnake.getSpeed();

		// If it has been a while since the snake moved...
		if (currentTime > lastMove + snakeSpeed) {
			logger.debug("We have waited for " + snakeSpeed
					+ " milliseconds, it is time to advance snake "
					+ aSnake.getColor());
			aSnake.setLastMoveTime(System.currentTimeMillis());
			return true;
		} else {
			// It hasn't been long enough
			return false;
		}

	}

}
