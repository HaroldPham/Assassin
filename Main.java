package finalProject;
import java.io.File;
import java.io.FileNotFoundException;
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
		//First get the players list
		boolean ok = false;
		Scanner temp = new Scanner(System.in); //Temporary instantiation
		
		while(ok == false)
		{
		    try //Try reading the Players.txt file
			{
		    	ok = true;
		    	temp = new Scanner(new File("Players.txt"));
			}
			catch(FileNotFoundException e) //If the file is not available then ask for input and try again.
			{
				System.out.println("Players.txt file not found, please import a Players.txt file into the project folder to be used.");
				System.out.println("Press any key and enter once Players.txt is added.");
				strChoice = sysScan.nextLine();
				if(strChoice != "") //and artificial way to create a pause and give the user enough time to import the txt file.
					ok = false;
				else 
					ok = false;
			}
		    
		} //If the loop is broken that means the Players.txt file was found.
		
		game = new Assassin(temp);
		
	}
	
	private static void start()
	{
		System.out.println("\n");
	}

}
