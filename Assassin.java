package finalProject;
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

import java.util.*;
public class Assassin 
{
	//Player fields
	private static LinkedList<String> killRing;
	private static LinkedList<String> grave;
	private static int playerCount;
	
	//Default construction
	public Assassin()
	{
		  killRing = new LinkedList<>();
		  grave = new LinkedList<>();
		  playerCount = 0;
	}
	
	public Assassin(Scanner players)
	{
		killRing = new LinkedList<>();
		readPlayers(players); //Accounts for players and playerCount
		grave = new LinkedList<>();
		Collections.shuffle(killRing); //Randomizes the list of players.
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
	
	//If necessary manually add a player to the game.
	public void addPlayer(String name)
	{
		killRing.add(name);
	}
	
	//When someone dies, they get sent from the alive list to the graveyard list.
	public void assassinated(int indexFrom)
	{
		grave.add(killRing.get(indexFrom));
		killRing.remove(indexFrom);
	}
	
	
//ACCESSOR METHODS
	
	public String getAlivePlayer(int index)
	{
		return killRing.get(index);
	}
	
	public String getDeadPlayer(int index)
	{
		return grave.get(index);
	}
	//Returns a string representation of living people
	public String alive()
	{
		String list = "Kill Ring:\n" + killRing.get(0);
		
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
			return "Graveyard:\nThe graveyard is empty! Nobody is dead.";
		}
		
		String list = "Graveyard:\n" + grave.get(0);
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

	//Gives the amount of people alive
	public int killRingSize()
	{
		return killRing.size();
	}
	
	//Gives the amount of people dead
	public int tombstones()
	{
		return grave.size();
	}
	
//MISC. METHODS
	//Return a cluster of necessary fields for the client.
	public void statHUD()
	{
		System.out.println("Stats:");
		System.out.println(alive() + "\nAlive: " + killRingSize() + "\n");
		
		System.out.println(graveyard() +"\nDead: "+ tombstones() + "\n");
		
	}
	
	//For the client use, kill someone from the alive list.
	public String kill(int index)
	{
		String holder;
		
		if(killRingSize() == 2)
		{
			if(index == 1)
				holder = killRing.get(index) + " was killed by " + killRing.get(index-1) + "\n";
			else
				holder = killRing.get(index) + " was killed by " + killRing.get(index+1) + "\n";
			assassinated(index);
			
			return holder;
		}
		else
		{
			holder = killRing.get(index) + " was killed by " + killRing.get(index-1) + "\n";
			assassinated(index);
			return holder;
		}
	}
	
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
