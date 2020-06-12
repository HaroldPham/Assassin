package finalProject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
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
	private static int currentRound;
//SYSTEM METHODS
	public static void main(String[] args) 
	{
		System.out.println("Welcome to Assassin:");
		System.out.println("Synopsis: Provided with a text file named \"Players\" by either player or system "
				+ "\nthis game will run through all the participants listed until one name is left. "
				+ "\nIn that case that person will be the winner of the game. Choose options as you progress to access "
				+ "\nother features of this game.");
		System.out.println("\nContinue? (Press any key and enter): ");
		strChoice = sysScan.nextLine();
		if(strChoice != "")
		{
			optimize();
			start();
		}
	}
	
	private static void optimize() //acts as if it were the constructor of the class
	{
		currentRound = 1;
		//First get the players list
		boolean ok = false;
		Scanner temp = new Scanner(System.in); //Temporary instantiation
		while(ok == false)
		{
		    try //Try reading the Players.txt file
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
		
		System.out.println("Players shuffled.\n");
		
		while(ok == false)
		{
			System.out.println("These are your players, would you like to add more? (Choose a number): \n" + game.alive());
			System.out.println("\n1: Yes\n2: No");
			numChoice = sysScan.nextInt();
			
			if(numChoice == 1) //Attempt to added in the name to the killRing                                                 Come back to this
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
	
	private static void start()
	{
		System.out.println("\nRules: As the rounds progress and as you make your choices please be sure to only enter your choice \nthrough whole numbers unless"
				+ " otherwise noted. Do you agree?\n");
		System.out.println("1: Yes\n2: No");
		
		numChoice = sysScan.nextInt();
		
		if(numChoice != 1) //Recurse until agreed upon or error is set.
		{
			start();
		}
		
		System.out.println("Game Starting...\n");
		
		while(!game.lastPersonStanding())
		{
			System.out.println("Round " + currentRound + ":");
			game.statHUD();
			round();
		}
		
		System.out.println("\nAll are gone except for one..." + game.getAlivePlayer(0) + " is the last one standing! (Press any key and enter to continue): ");
		strChoice = sysScan.next();
		System.out.println("\nPost game status: ");
		game.statHUD();
	}
	
	//Will facilitate each round and handle the game.
	public static void round()
	{
		
		System.out.println("1: Proceed\n2: Check Game Progress\n3: End Game\n");
		
		numChoice = sysScan.nextInt();
			
		if(numChoice == 1)
		{
			int killZone = (int) (Math.random() * game.killRingSize()); //Generate the index of the killed person
			System.out.println(game.kill(killZone));
			currentRound++;
		}
		else if(numChoice == 2)
		{
			game.statHUD();
			round();
		}
		else if(numChoice == 3)
		{
			System.out.println("Game Ending...");
			System.exit(0);         //Taught to me by Aleks G. on StackOverflow. 
									//https://stackoverflow.com/questions/22452930/terminating-a-java-program
		}
		else
		{
			System.out.println("Please enter a valid number.");
			round();
		}
		
		
		
	}

}
