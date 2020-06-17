import java.util.*;
/* Harold Pham, CS 211(B), Professor James Livingston, Final Project
 * Creation Date: 6/9/2020
 * Details: Chapter 16 Project #4
 * File Description: This "Assassin" Class will hold the technical code of the game.
 * Overview: Assassin is a game that takes a file of names, stores it into a LinkedList, 
 * and slowly removes individual names as if they were killed. 
 */

/*
 * Creator Notes: 
 * Have a "Graveyard" for killed players that can be accessed anytime.
 * Have a method that shows the current stats of the game.
 * If there is one person left alive then they are the winner and game over.
 * 
 */

public class Assassin 
{
	//Player fields
	private static LinkedList<String> killRing;
	private static LinkedList<String> grave;
	private static int playerCount; //Holds the overall player count
	private static int currentRound;
	
	//Default construction
	public Assassin()
	{
		  killRing = new LinkedList<>();
		  grave = new LinkedList<>();
		  playerCount = 0;
		  currentRound = 1;
	}
	
	public Assassin(Scanner players)
	{
		killRing = new LinkedList<>();
		readPlayers(players); //Accounts for players and playerCount
		grave = new LinkedList<>();
		Collections.shuffle(killRing); //Randomizes the list of players.
		currentRound = 1;
	}
	
	//Reads players into the game from "Players.txt"
	public void readPlayers(Scanner players) 
	{
		while(players.hasNext()) //Add all the scanner names into the players list.
		{
			killRing.add(players.next());
			playerCount++;
		}
	}
	
//MUTATOR METHODS
	
	//Move the round up by 1
	public void nextRound()
	{
		currentRound++;
	}
	
	//If necessary manually add a player to the game.
	public void addPlayer(String name)
	{
		killRing.add(name);
		playerCount++;
	}
	
	//When someone dies, they get sent from the alive list to the graveyard list.
	public void assassinated(int person)
	{
		grave.add(killRing.get(person));
		killRing.remove(person);
	}
	
	
//ACCESSOR METHODS
	
	//Returns the current round
	public int getRound()
	{
		return currentRound;
	}
	
	//Returns a specific living player
	public String getAlivePlayer(int player)
	{
		return killRing.get(player);
	}
	
	//Return a specific dead player
	public String getDeadPlayer(int player)
	{
		return grave.get(player);
	}
	
	//Returns a string representation of living people
	public String alive()
	{
		String list = "Kill Ring:\n\t" + killRing.get(0);
		
		for(int i = 1; i < killRing.size(); i++)
		{
			if(i > 10)
			{
				list += "\n" + killRing.get(i);
				
			}
			else
				list += ", " + killRing.get(i);
		}
		return list;
	}
	
	//Returns a string representation of dead people
	public String graveyard()
	{
		if(grave.size() <= 0)
		{
			return "Graveyard:\n\tThe graveyard is empty! Nobody is dead.";
		}
		
		String list = "Graveyard:\n\t" + grave.get(0);
		for(int i = 1; i < grave.size(); i++)
		{
			if(i > 10)
			{
				list += "\n" + grave.get(i);
				
			}
			else
				list += ", " + grave.get(i);
		}
		return list;
	}
	
	//Gives the amount of all players playing
	public int playerCount()
	{
		return playerCount;
	}

	//Gives the count of people alive
	public int killRingSize()
	{
		return killRing.size();
	}
	
	//Gives the count of people dead
	public int tombstones()
	{
		return grave.size();
	}
	
//MISC. METHODS
	
	//Return a cluster of necessary fields for the client.
	public void statHUD()
	{
		System.out.println("\t"+alive() + "\n\tAlive: " + killRingSize() + "\n");
		
		System.out.println("\t"+graveyard() +"\n\tDead: "+ tombstones() + "\n");
		
	}
	
	//For the client use, kill someone from the alive list.
	public String kill(int person)
	{
		
	//The index solution to this method
		/*
		String holder;
		
		if(person == 0) //If the person is at the beginning of the LinkedList then they will be killed by the person at the end of the list.
			holder = killRing.get(person) + " was killed by " + killRing.get(killRing.size()-1) + "!\n";
		else //Returns that the person was killed by the person prior in the LinkedList.
			holder = killRing.get(person) + " was killed by " + killRing.get(person-1) + "!\n";
			
		assassinated(person);
		return holder;
		*/
		
		
	//The Iterator solution to this method
		
		String first = "";
		String killer = "";
		int iterate = person;
		Iterator<String> hold1 = killRing.iterator();
		
	    if(person == 0) //If its the first person, save the first and last person in the list for later
	    {
	    	first = hold1.next(); //This will hold the first person in the list
			
			for(int i = 1; i < killRing.size()-1; i++) //Stops before the last person
			{
				hold1.next();
			}
			killer = hold1.next();
		}
		else if (person > 0 && person < killRing.size()) //If the person is within the list, find them
		{
			while(iterate > 0) //Stops before last person
			{
				iterate--;
	        	killer = hold1.next();
			}
		}
		else //if the person isn't within the list
		{
			return "Choose a valid person.";
		}
	    
		
		String call;
		if(person == 0)
			call = first + " was killed by " + killer + "\n";
		else
			call = hold1.next() + " was killed by " + killer + "\n";
		assassinated(person);
		return call;
	}
	
	//Checks if there is only one person left in the killRing
	public boolean lastPersonStanding()
	{
		if(killRingSize() == 1)
		{
			return true;
		}
		else
			return false;
	}
}
