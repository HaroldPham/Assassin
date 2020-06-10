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
	private static LinkedList<String> players;
	private static LinkedList<String> grave;
	private static int playerCount;
	
	//Default construction
	public Assassin()
	{
		  players = new LinkedList<>();
		  grave = new LinkedList<>();
		  playerCount = 0;
	}
	
	public Assassin(Scanner temp)
	{
		readPlayers(temp);
	}
	
	//Reads players into the game from "Players.txt"
	public void readPlayers(Scanner temp) 
	{
		while(temp.hasNext()) //Add all the scanner names into the players list.
		{
			players.add(temp.next());
			playerCount++;
		}
	}
	
}
