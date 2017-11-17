import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GetViableDictionaryAndCharList {
	
	// Returns three random characters from the English alphabet as an ArrayList
	public static void getACharList() {
		
		// make a new list of the random characters
		ArrayList<Character> listOfChars = new ArrayList<Character>();
		
		// get the random characters from the standard English alphabet
		for (int count = 0; count < 3; count++) {
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			Random randomizer = new Random();
			listOfChars.add(alphabet.charAt(randomizer.nextInt(26)));
		}	
		// update the static char list to the newly created list
		Logic.myCharList = listOfChars;
	}
	
	// creates a viable dictionary (must be at least one word to be viable)
	public static void createMyDictionary() throws IOException {
		// get three random characters
		getACharList();
		// create a dictionary with the char list
		Logic.myViableDictionary = (CreateDictionary.createMyDictionary());
		// do until a viable dictionary is created
		do {	
			getACharList();
			Logic.myViableDictionary = CreateDictionary.createMyDictionary();

		} while (Logic.myViableDictionary.isEmpty());	
	}
}
