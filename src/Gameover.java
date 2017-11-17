import java.util.ArrayList;

public class Gameover {
	static ArrayList<Player> winnerList = new ArrayList<Player>();

	//check for the max point value among all remaining players
	public static int getMaxPoints(ArrayList<Player> playerList) {
		
		// set the initial point value to the first player's points
		Player myPlayer = (Player) playerList.get(0);
		int maxPoints = myPlayer.getPlayerPoints();

		// check if there are greater point values in the list of players
		for (int count = 0; count < playerList.size(); count++) {	
			myPlayer = (Player) playerList.get(count);
			
			// set maxPoints to the new value if greater
			if (myPlayer.getPlayerPoints() > maxPoints) {
				maxPoints = myPlayer.getPlayerPoints();;
			}
		}
		return maxPoints;
	}
	
	// for all the players in the winner list, print them out
	public static void printMyPlayers() {
		for (int count = 0; count < winnerList.size(); count++) {
			Player aPlayer = (Player) winnerList.get(count);
			System.out.print(aPlayer.getPlayerName() + ", ");
		}
	}
	
	// take the winning players out of the list
	public static void removeMyWinners(ArrayList<Player> playerList) {
		for (int count = 0; count < winnerList.size(); count++) {
			
			Player aPlayer = (Player) winnerList.get(count);
			playerList.remove(aPlayer);
			
		}
	}

	// stops all the timers and prints out their winners with their respective point values
	public static void declareTheWinner(ArrayList<Player> playerList) {
		Stopwatch.timer.cancel();
		TemporaryStopwatch.timer.cancel();

		// check if there was just one person playing
		if (playerList.size() == 1) {
			System.out.println("\n" + Logic.getMyPlayerName() + ", you ended the game with " + Logic.getMyPlayerPoints() + " points!");
		}
		else {
			// get the winning point value for first place
			int placePoints = getMaxPoints(playerList);

			System.out.print("\nFirst place goes to ");
			for (int firstCount = 0; firstCount < playerList.size(); firstCount++) {
				Player aPlayer = (Player) playerList.get(firstCount);
				if (aPlayer.getPlayerPoints() == placePoints) {
					winnerList.add(aPlayer);
				}
			}
			printMyPlayers();
			removeMyWinners(playerList);
			winnerList.clear();
			System.out.println("with " + placePoints + " points! Congratulations!");

			// if there are > 2 players and they didn't tie in points
			if (playerList.size() > 0) {
				// get the second place point value
				placePoints = getMaxPoints(playerList);

				System.out.print("Second place goes to ");
				for (int secondCount = 0; secondCount < playerList.size(); secondCount++) {
					Player aPlayer = (Player) playerList.get(secondCount);
					if (aPlayer.getPlayerPoints() == placePoints) {
						winnerList.add(aPlayer);
					}
				}
				printMyPlayers();
				removeMyWinners(playerList);
				winnerList.clear();
				System.out.println("with " + placePoints + " points!");
				
				// if there are still players left in the list
				if (playerList.size() > 0) {
					// get the third place point value
					placePoints = getMaxPoints(playerList);
					
					System.out.print("Third place goes to ");
					for (int thirdCount = 0; thirdCount < playerList.size(); thirdCount++) {
						Player aPlayer = (Player) playerList.get(thirdCount);
						if (aPlayer.getPlayerPoints() == placePoints) {
							winnerList.add(aPlayer);
						}
					}
					printMyPlayers();
					removeMyWinners(playerList);
					winnerList.clear();
					System.out.println("with " + placePoints + " points!");
				}
			}
		}
		System.out.println("\nThanks for playing!\nA game by August Vitzthum");
		// close the window and terminate the program
		System.exit(0);
	}
}
