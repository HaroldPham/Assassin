package finalProject;
/* Harold Pham, CS 211(B), Professor James Livingston, Final Project
 * Creation Date: 6/9/2020
 * Details: Chapter 16 Project #4
 * File Description: This "Main" class will be where the client begins the game.
 * Overview: Assassin is a game that takes a file of names, stores it into a LinkedList, 
 * and slowly removes individual names as if they were killed. 
 */
import java.util.*;
public class Main 
{
//FIELDS
	private static Scanner sysScan = new Scanner(System.in);
	private static int numChoice;
	private static String strChoice;

//SYSTEM METHODS
	public static void main(String[] args) 
	{
		System.out.println("Welcome to Assassin:");
		System.out.println("Synopsis: Provided with a text file named \"Players\" by either player or system "
				+ "\nthis game will run through all the participants listed until one name is left. "
				+ "\nIn that case that person will be the winner of the game. Choose options as you progress to access "
				+ "\nother features of this game.");
		System.out.println("\nContinue? (Press any key and enter): ");
		strChoice = sysScan.next();
		if(strChoice != "")
		{
			start();
		}
	}
	
	private static void start()
	{
		
	}

}
