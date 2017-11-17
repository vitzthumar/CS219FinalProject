import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

public class PlayMyGame {

	public static void main(String[] args) throws IOException {
		
		// print the introductory information
		System.out.println("Enter words that have the given three random letters in consecutive order to earn points!");
		System.out.println("Incorrently entered words are worth -1 point. 7 letter words are +1 point, 8-10 letters are +2, 11+ letters are +3.");
		System.out.println("Type '/end' to end the current round at any time.\n");

		// creates an array list of players and their points values
		ArrayList<String> playerList = new ArrayList<String>();
		ArrayList<Player> playerAndPointList = new ArrayList<Player>();
		
		// asks for each player's name and puts them in an ArrayList
		Scanner myScanner = new Scanner(System.in);
		System.out.println("Enter each player's name, then press Enter.");
		boolean allPlayers = false;
		
		int playerNumber = 1;
		// enters the name of the player into a string (if it already isn't there)
		while (!allPlayers) {
			System.out.print("Player " + playerNumber + "'s name: ");
			String playerName = myScanner.nextLine();
			
			// if just enter wasn't pressed and the list of players doesn't already contain that name
			if (!playerName.equals("") && !playerList.contains(playerName)) {
				playerNumber ++;
				playerList.add(playerName);
				Player player = new Player(playerName, 0);
				playerAndPointList.add(player);
			}
			else {
				// if no names are entered, Player 1's name will be asked for again
				if (!(playerNumber == 1)) {
					allPlayers = true;
					System.out.println("The list of players is: " + playerList);
				}
			}
		}
		
		// set the player and point list as well as the list size
		Logic.playerAndPointListSize = playerList.size();
		Logic.myPlayerAndPointList = playerAndPointList;
		
		// asks for the number of rounds to be played
		System.out.print("Enter the number of rounds you would like to play: ");
		Logic.myNumberOfRounds = myScanner.nextInt();
		
		// asks for the time (in seconds) for each round
		System.out.print("Enter the time (in seconds) for each round: ");
		Logic.myTime = myScanner.nextInt();
		
		// close the scanner
		myScanner.close();	
		
		// creates a JFrame and JPanels, creating the graphics
		JFrame myFrame = new JFrame("ABC123");		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create the components for myPanel
		Panel myPanel = new Panel();
		
		myFrame.getContentPane().add(myPanel);
		myFrame.pack();
		// make it so the window is not resizable and is visible
		myFrame.setVisible(true);
		myFrame.setResizable(false);
		
		
		// set the first viable dictionary and char list
		GetViableDictionaryAndCharList.createMyDictionary();
		// Run the first timer (temporary)
		TemporaryStopwatch.setMyStopwatch();
		

		

	}

}
