import java.util.ArrayList;
import java.util.Arrays;

// Checks whether the given word is viable or not, and adds it to a list of either
// accepted or rejected words.
public class ViabilityChecker {

	static ArrayList<String> acceptedWordList = new ArrayList<String>();
	static ArrayList<String> rejectedWordList = new ArrayList<String>();

	public static void checkMyWord(String submittedWord) {

		// set the submitted word to all lowercase letters
		submittedWord = submittedWord.toLowerCase();

		// if the word is in the dictionary for this round, add it to the accepted words and increase points
		if (Logic.myViableDictionary.contains(submittedWord)) {
			
			if (!acceptedWordList.contains("\n" + submittedWord)) {
			
				updateAcceptedWordList(submittedWord);
			}		
		}
		// if the word is not in the dictionary, add it to the rejected words and decrease by 1 point
		else {
			updateRejectedWordList(submittedWord);

		}
	}

	// adds the word to the accepted word list then updates the graphic
	public static void updateAcceptedWordList(String word) {
		acceptedWordList.add("\n" + word);
		String wordList = Arrays.toString(acceptedWordList.toArray()).replace("[", "").replace("]", "");
		Panel.acceptedListGraphic.setText("Accepted words:" + wordList);
		
		allotMyPoints(true, word);
	}
	// adds the word to the rejected word list then updates the graphic
	public static void updateRejectedWordList(String word) {
		rejectedWordList.add("\n" + word);
		String wordList = Arrays.toString(rejectedWordList.toArray()).replace("[", "").replace("]", "");
		Panel.rejectedListGraphic.setText("Rejected words:" + wordList);
		
		allotMyPoints(false, word);
	}

	// change the player's points depending on if it's accepted/rejected, then update the point graphic
	public static void allotMyPoints(boolean viable, String word) {
		int wordPoints = 0;

		if (viable) {
			// 3-7 letter words are worth 1 point
			if (word.length() <= 7) {
				wordPoints = 1;
			}
			// 8-10 letter words are worth 2 points
			if (word.length() >= 8 && word.length() <= 10) {
				wordPoints = 2;
			}
			// 11+ letter words are worth 3 points
			if (word.length() >= 11) {
				wordPoints = 3;
			}
		}
		// rejected words are worth -1 points
		if (!viable) {
			wordPoints = -1;
		}
		
		// get the player's points, then set the value to the updated points
		Logic.setMyPlayerPoints(wordPoints);

		Panel.pointGraphic.setText(Logic.getMyPlayerName() + "'s Points: " + Logic.getMyPlayerPoints());
	}
}
