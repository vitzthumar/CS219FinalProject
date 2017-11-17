import java.io.IOException;
import java.util.ArrayList;

public class Logic {

	// initialize various static variables to be used in a various classes
	static ArrayList<Player> myPlayerAndPointList;
	static int playerAndPointListSize;
	static int myTime;
	static int myPlayerNumber = 0;
	static int myRoundNumber = 1;
	static int myNumberOfRounds;
	
	// static character and viable dictionary list
	static ArrayList<Character> myCharList = new ArrayList<Character>();
	static ArrayList<String> myViableDictionary;
	
	// gets the player's name at a certain index of the player point list
	public static String getMyPlayerName() {
		Player myPlayer = (Player) myPlayerAndPointList.get(myPlayerNumber);
		return myPlayer.getPlayerName();
	}
	
	// gets the player's name at a certain index of the player point list
	public static int getMyPlayerPoints() {
		Player myPlayer = (Player) myPlayerAndPointList.get(myPlayerNumber);
		return myPlayer.getPlayerPoints();
	}
	
	// sets the player's point value to an updated version
	public static int setMyPlayerPoints(int wordPoints) {
		Player myPlayer = (Player) myPlayerAndPointList.get(myPlayerNumber);
		return myPlayer.setPlayerPoints(wordPoints);
	}
	
	// updates the player number, and round number (if applicable)
	public static boolean updateNumberAndRound() throws IOException {
		myPlayerNumber++;
		
		// if all the players have played, increment the round number
		if (myPlayerNumber == playerAndPointListSize) {
			
			// update the viable dictionary and char list
			GetViableDictionaryAndCharList.createMyDictionary();
			
			// increase the round number, then set the player number back to the first player
			myRoundNumber++;
			myPlayerNumber = 0;
			
			// if all the rounds have been played, display winner's final points
			if (myRoundNumber == myNumberOfRounds + 1) {
				
				// print the winner's points and exit the window
				Gameover.declareTheWinner(myPlayerAndPointList);
				
				// the game is over
				return true;
			}
		}
		
		// update the players points
		Panel.pointGraphic.setText(getMyPlayerName() + "'s Points: " + String.valueOf(getMyPlayerPoints()));
		
		// clear the accepted and rejected word list
		ViabilityChecker.acceptedWordList.clear();
		ViabilityChecker.rejectedWordList.clear();
		// update the accepted and rejected word list
		Panel.acceptedListGraphic.setText("Accepted words: \n");
		Panel.rejectedListGraphic.setText("Rejected words: \n");
		
		// the game is not over
		return false;
	}
	
	// update the player name, round, and char graphics
	public static void updateRoundGraphics() {
		
		// set the player name graphic
		Panel.playerNameGraphic.setText(getMyPlayerName());
		
		// set the round number graphic
		Panel.roundNumberGraphic.setText("Round " + String.valueOf(myRoundNumber) + " of " + myNumberOfRounds);
		
		// set the character graphics to "GET/READY/(PLAYER NAME)"
		updateCharGraphics(false);
	}
	
	// set the round's characters if the turn is running, else displays "GET/READY/(PLAYER NAME)"
	public static void updateCharGraphics(boolean isPlaying) {
		
		// checks if the round is being played or not
		if (isPlaying) {
		
			// set the chars from left to right
			Panel.firstCharGraphic.setText(String.valueOf(myCharList.get(0)));
			Panel.secondCharGraphic.setText(String.valueOf(myCharList.get(1)));
			Panel.thirdCharGraphic.setText(String.valueOf(myCharList.get(2)));
		}
		else {
			// set the char graphic to "GET/READY/(PLAYER NAME)"
			Panel.firstCharGraphic.setText("GET");
			Panel.secondCharGraphic.setText("READY");
			Panel.thirdCharGraphic.setText(getMyPlayerName() + "!");
		}
	}	
}