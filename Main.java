import java.io.*;
import java.util.*;
/* Harold Pham, CS 211(B), Professor James Livingston, Final Project
 * Creation Date: 6/9/2020
 * Details: Chapter 16 Project #4
 * File Description: This "Main" class will be where the client begins the game.
 * Overview: Assassin is a game that takes a file of names, stores it into a LinkedList, 
 * and slowly removes individual names as if they were killed. 
 */

public class Main 
{
//FIELDS
	//System fields
	private static Scanner sysScan = new Scanner(System.in);
	private static int numChoice;
	private static String strChoice;
	
	private static Assassin game;
	
	
//SYSTEM METHODS
	
	public static void main(String[] args) 
	{
		//Get the user started first with the game idea
		System.out.println("Welcome to Assassin:");
		System.out.println("Synopsis: Provided with a text file named \"Players\" by either player or system "
				+ "\nthis game will run through all the participants listed until one name is left. "
				+ "\nIn that case that person will be the winner of the game. Choose options as you progress to access "
				+ "\nother features of this game.");
		System.out.println("\nContinue? (Press any key and enter): ");
		strChoice = sysScan.nextLine();
		if(strChoice != "") //Start the game sequences
		{
			optimize();
			start();
		}
	}
	
	
	//acts as if it were the constructor of the class
	private static void optimize() 
	{
		//First get the players list
		boolean ok = false;
		Scanner temp = new Scanner(System.in); //Temporary instantiation
		
		while(ok == false) //Read the Players.txt file into the game
		{
		    try
			{
		    	temp = new Scanner(new File("Players.txt"));
		    	ok = true;
			}
			catch(FileNotFoundException e) //If the file is not available then ask for input and try again.
			{
				System.out.println("Players.txt file not found, please import a Players.txt file into the project folder to be used.");
				System.out.println("Press any key and enter once Players.txt is added.");
				strChoice = sysScan.nextLine();
				if(strChoice != "") //an artificial way to create a pause and give the user enough time to import the txt file.
					ok = false;
				else 
					ok = false;
			}
		    
		} //If the loop is broken that means the Players.txt file was found.
		
		ok = false;
		
		game = new Assassin(temp);
		
		System.out.println("Players shuffled.\n"); //The Assassin constructor should shuffle the players automatically.
		
		while(ok == false) //Display the players and ask if there are more.
		{
			System.out.println("These are your players, would you like to add more? (Choose a number): \n\t" + game.alive());
			System.out.println("\n1: Yes\n2: No");
			numChoice = sysScan.nextInt();
			
			if(numChoice == 1) //Attempt to add in the name to the killRing                                                 Come back to this
			{
				System.out.println("Type a persons name to be added: ");
				try 
				{
					strChoice = sysScan.next();
					game.addPlayer(strChoice);
					System.out.println(strChoice + " has been added.\n");
				}
				catch(InputMismatchException e)
				{
					System.out.println("Please enter a valid single token name." + e);
				}
			}
			else
				ok = true;
		}
		
	}
	
	
	//Game starts.
	private static void start()
	{
		//Give the user the rules of the game
		System.out.println("\nRules: As the rounds progress and as you make your choices please be sure to only enter your choice \nthrough whole numbers unless"
				+ " otherwise noted. (Press any key and enter to continue):\n");	
		strChoice = sysScan.next();
		
		System.out.println("Game Starting...\n");
		
		while(!game.lastPersonStanding()) //The actual game mechanics start here, loop until there is one person left
		{
			System.out.println("Round " + game.getRound() + ":");
			game.statHUD();
			round(); //This will display the game options for the user
		} 
		
		//Show the winner to the client.
		System.out.println("\nAll are gone except for one..." + game.getAlivePlayer(0) + " is the last one standing! (Press any key and enter to continue): ");
		strChoice = sysScan.next(); //Create a pause in the system for time to read
		
		System.out.println("\nPost game results: "); //Show all of stats.
		System.out.println("\tFinal Round: " + (game.getRound()-1) + "\n");
		game.statHUD();
	}
	
	
	//Will facilitate each round and handle the game. Asks for user input each round.
	public static void round()
	{
		System.out.println("1: Proceed\n2: End Game\n");
		
		numChoice = sysScan.nextInt();
			
		if(numChoice == 1) //If the user wants to continue, kill someone and move on.
		{
			int killZone = (int) (Math.random() * game.killRingSize()); //Generate the index of the killed person
			System.out.println(game.kill(killZone)); //Kill person at that index.
			game.nextRound();
		}
		else if(numChoice == 2) //If the user needs to, end the game.
		{
			System.out.println("Game Ending...");
			System.exit(0);         //Taught to me by Aleks G. on StackOverflow. 
									//https://stackoverflow.com/questions/22452930/terminating-a-java-program
		} 
		else //If a number outside of expectations is entered restart the round choices.
		{
			System.out.println("Please enter a valid number.");
			round();
		}
		
	}

}
