// a class which creates a Player object with their name and points
public class Player {

	// instance variables
	public String playerName;
	public int playerPoints;

	// constructors
	public Player(String aPlayerName, int aPlayerPoints) {
		this.playerName = aPlayerName;
		this.playerPoints = aPlayerPoints;
	}

	// instance methods
	// gets the player's name
	public String getPlayerName() {
		return playerName;
	}

	// gets the player's points
	public int getPlayerPoints() {
		return playerPoints;
	}
	
	// sets the player points to a new value
	public int setPlayerPoints(int wordPoints) {
		playerPoints += wordPoints;
		return playerPoints;
	}
	
}
	