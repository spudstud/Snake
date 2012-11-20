package model;

import java.util.ArrayList; 
import java.util.Random;
/**
 * Game has a mode, a paused status, number of players and cube type
 * 
 * @author Spencer Owen
 * @version 1.0
 */
public class Game
{

    private int numberOfPlayers;
    private int cubeType;
    private int gameMode; 
    private boolean gamePaused;
    
    public  ArrayList<Apple> aListOfApples = new ArrayList<Apple>();
	public  ArrayList<Snake> aListOfSnakes = new ArrayList<Snake>();
	
    
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
    	 this.numberOfPlayers = numberOfPlayers;
         this.cubeType = cubeType;; 
         this.gameMode = gameMode;
         this.gamePaused = true;
    }
    
    public Game( int numberOfPlayers, int cubeType, int gameMode)
    {

        this.numberOfPlayers = numberOfPlayers;
        this.cubeType = cubeType;; 
        this.gameMode = gameMode;
        this.gamePaused = true;
                
    }


   public boolean isGamePaused()
   {
       return this.gamePaused;
   }

   public void setGamePaused(boolean gamePaused)
   {
       this.gamePaused = gamePaused;
   }

public ArrayList<Apple> getaListOfApples() {
	return aListOfApples;
}

public void setaListOfApples(ArrayList<Apple> aListOfApples) {
	this.aListOfApples = aListOfApples;
}

public ArrayList<Snake> getaListOfSnakes() {
	return aListOfSnakes;
}

public void setaListOfSnakes(ArrayList<Snake> aListOfSnakes) {
	this.aListOfSnakes = aListOfSnakes;
}
   

   

public void createSnakes(int NumberOfSnakesToCreate)
{
	   model.Snake aReusableSnake;
	   
	   if (NumberOfSnakesToCreate >= 1)
	   {
		   logger.info("Creating snake #1 of " + NumberOfSnakesToCreate);
		   //Create 4 snake objects, the 4 players are hard coded
		   int playerOneColor = controller.ConvertLedType.hexToInt("0000FF");
		   int playerOneDirection = 3; //Snake is traveling west
		   ArrayList<Integer> playerOneBodyPositions = new ArrayList<Integer>();
		   playerOneBodyPositions.add(13);
		   playerOneBodyPositions.add(14);
		   playerOneBodyPositions.add(15);
		   int playerOneSpeed = 750;
		   
		   aReusableSnake = new model.Snake(playerOneColor, playerOneDirection, playerOneBodyPositions, playerOneSpeed);
		   model.Arena.aListOfSnakes.add(aReusableSnake);
		  
	   }	
	   
	   if (NumberOfSnakesToCreate >= 2)
	   {
		   logger.info("Creating snake #4 of " + NumberOfSnakesToCreate);
		   //Create 4 snake objects, the 4 players are hard coded
		   int playerTwoColor = controller.ConvertLedType.hexToInt("00FF00");
		   int playerTwoDirection = 0; //Snake is traveling north
		   ArrayList<Integer> playerTwoBodyPositions = new ArrayList<Integer>();
		   playerTwoBodyPositions.add(512);
		   playerTwoBodyPositions.add(256);
		   playerTwoBodyPositions.add(0);
		   int playerTwoSpeed = 750;
		   aReusableSnake = new model.Snake(playerTwoColor, playerTwoDirection, playerTwoBodyPositions, playerTwoSpeed);
		   model.Arena.aListOfSnakes.add(aReusableSnake);
	
	   }
	   
	   if (NumberOfSnakesToCreate >= 3)
	   {
		   logger.info("Creating snake #3 of " + NumberOfSnakesToCreate);
		   //Create 4 snake objects, the 4 players are hard coded
		   int playerThreeColor = controller.ConvertLedType.hexToInt("FF00FF");
		   int playerThreeDirection = 0; //Snake is traveling north
		   ArrayList<Integer> playerThreeBodyPositions = new ArrayList<Integer>();
		   playerThreeBodyPositions.add(3840);
		   playerThreeBodyPositions.add(3841);
		   playerThreeBodyPositions.add(3842);
		   int playerThreeSpeed = 750;
		   aReusableSnake = new model.Snake(playerThreeColor, playerThreeDirection, playerThreeBodyPositions, playerThreeSpeed);
		   model.Arena.aListOfSnakes.add(aReusableSnake);
		   
	   }
	   
	   if (NumberOfSnakesToCreate == 4)
	   {
		   logger.info("Creating snake #4 of " + NumberOfSnakesToCreate);
		   //Create 4 snake objects, the 4 players are hard coded
		   int playerFourColor = controller.ConvertLedType.hexToInt("FF0000");
		   int playerFourDirection = 0; //Snake is traveling north
		   ArrayList<Integer> playerFourBodyPositions = new ArrayList<Integer>();
		   playerFourBodyPositions.add(3855);
		   playerFourBodyPositions.add(3601);
		   playerFourBodyPositions.add(3346);
		   int playerFourSpeed = 750;
		   aReusableSnake = new model.Snake(playerFourColor, playerFourDirection, playerFourBodyPositions, playerFourSpeed);
		   model.Arena.aListOfSnakes.add(aReusableSnake);
		  
	   }
	   
	   if( NumberOfSnakesToCreate <1 || NumberOfSnakesToCreate >4)
	   {
		  // controller.SnakeGame.logger.fatal("Game.createSnakes can only receive numbers between 1 and 4, received: " + NumberOfSnakesToCreate);
		   throw new IllegalArgumentException("Game.createSnakes can only receive numbers between 1 and 4, received: " + NumberOfSnakesToCreate);

	   }
	   
	   //Null out the object to mark it for garbage collection
	   aReusableSnake = null;
	   
	   
	   
}

public void createApple(int appleColor)
 {
 	
 	//TODO:Delete this test code
// 	model.Apple anApple = new model.Apple(10,255);
// 	model.Arena.aListOfApples.add(anApple);
// 	
// 	model.Apple anApple2 = new model.Apple(9,255);
// 	model.Arena.aListOfApples.add(anApple);
 	
 	int temporaryAppleLocation;
 	int minumumLed = 0;
// 	//Get the highest number in the Arena and generate a random number less than it
 	int maximumLed = Arena.getMaximumLedInCube();
 	logger.debug("Maximum led = " + maximumLed + ", Arena.xMaximum="+ Arena.xMaximum +", Arena.yMaximum="+ Arena.yMaximum + ", Arena.zMaximum="+ Arena.zMaximum);
 	
 	//Create a random number between 0 and the maximum number of LEDs
 	Random aRandomNumber = new Random();
 	temporaryAppleLocation = aRandomNumber.nextInt(maximumLed - minumumLed) + minumumLed;
// 	temporaryAppleLocation = aRandomNumber.nextInt(maximumLed ) ;
     logger.info("Created a random apple at " +  temporaryAppleLocation);
     
     
     boolean appleCollisionCheck = false;
     boolean snakeCollisionCheck = false;
     
     //Keep creating new Apples until we have determined it is random
     //do while statement are different then while because they do the evaluation at the end of the loop
     do {
     	//Assume the Apple is good unless we find a bad one
     	appleCollisionCheck = true;
     	
     	//Look at every other apple in the arena and see if it is a duplicate 
     	for( Apple otherApples : model.Arena.aListOfApples )
     	{
     		//If 
     		if(temporaryAppleLocation == otherApples.getAbsolutePosition() )
     		{
     			appleCollisionCheck = false;
     		}
     		
     	}
     	
     	//Assume the snake is good unless we find a bad one
     	snakeCollisionCheck = true;
     	
     	for( Snake otherSnakes : model.Arena.aListOfSnakes )
     	{
     		for(Integer bodyPositionsToCheck : otherSnakes.getBodyPositions())
     		{
	        		//Check each part of the snake
	        		if(temporaryAppleLocation == bodyPositionsToCheck )
	        		{
	        			snakeCollisionCheck = false;
	        		}
     		}
     	}
     	
     	
     	
     	if (appleCollisionCheck == false || snakeCollisionCheck == false)
     	{
     		
     		logger.warn("Creating a new Apple since there was a collision at: " + temporaryAppleLocation);
//     		temporaryAppleLocation = aRandomNumber.nextInt(maximumLed - minumumLed) + minumumLed;
     		temporaryAppleLocation = aRandomNumber.nextInt( maximumLed );

     		
     		//Since we had a collision, reset both of the checks to false
     		appleCollisionCheck = false;
     		snakeCollisionCheck = false;
     		
     	}
     	
     }while ( appleCollisionCheck == false && snakeCollisionCheck == false);
     
     Apple anApple = new Apple(temporaryAppleLocation, appleColor);
     logger.info("Added a new apple with color:  " + appleColor + " at location: " + temporaryAppleLocation);
     model.Arena.aListOfApples.add(anApple);

     
 }

 public static void destroyApple()
 {
     
 }
   
   
}
