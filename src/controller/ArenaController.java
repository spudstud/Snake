package controller;

import java.util.Random;

import org.apache.log4j.Logger;

import model.Apple;
import model.Arena;
import model.CubeAttributes;
import model.Snake;

public class ArenaController {
	Logger logger = Logger.getLogger(this.getClass() );
	
	ArenaController()
	{
		
		
	}
	 public void createApple(int appleColor)
	    {
	    	
	    	//TODO:Delete this test code
//	    	model.Apple anApple = new model.Apple(10,255);
//	    	model.Arena.aListOfApples.add(anApple);
//	    	
//	    	model.Apple anApple2 = new model.Apple(9,255);
//	    	model.Arena.aListOfApples.add(anApple);
	    	
	    	int temporaryAppleLocation;
	    	int minumumLed = 0;
//	    	//Get the highest number in the Arena and generate a random number less than it
	    	int maximumLed = Arena.getMaximumLedInCube();
	    	logger.debug("Maximum led = " + maximumLed + ", Arena.xMaximum="+ Arena.xMaximum +", Arena.yMaximum="+ Arena.yMaximum + ", Arena.zMaximum="+ Arena.zMaximum);
	    	
	    	//Create a random number between 0 and the maximum number of LEDs
	    	Random aRandomNumber = new Random();
	    	temporaryAppleLocation = aRandomNumber.nextInt(maximumLed - minumumLed) + minumumLed;
//	    	temporaryAppleLocation = aRandomNumber.nextInt(maximumLed ) ;
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
//	        		temporaryAppleLocation = aRandomNumber.nextInt(maximumLed - minumumLed) + minumumLed;
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