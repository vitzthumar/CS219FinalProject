import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

// Reads through the american-english.txt text file and returns a dictionary as an ArrayList
public class CreateDictionary {

    public static ArrayList<String> createMyDictionary() throws IOException {
    	ArrayList<String> updatedDictionary = new ArrayList<String>();
    	
    	for (String line : Files.readAllLines(Paths.get("american-english.txt"))) {
    		
    		// if the word is greater than three letters and the first letter is not uppercase
    		if (!Character.isUpperCase(line.charAt(0)) && line.length() >= 3) {
    			
    			// check if the 3 given characters appear in sequential order, then add them to the dictionary
    			for (int firstCount = 0; firstCount < line.length(); firstCount ++) {
    				 if (line.charAt(firstCount) == Logic.myCharList.get(0)){
    					 
    					 for (int secondCount = 1; secondCount < line.length(); secondCount++) {
    						 if (line.charAt(secondCount) == Logic.myCharList.get(1) && secondCount > firstCount) {
    							 
    							 for (int thirdCount = 2; thirdCount < line.length(); thirdCount++) {
    								 if (line.charAt(thirdCount) == Logic.myCharList.get(2) && thirdCount > secondCount) {
    									 
    									 if (!updatedDictionary.contains(line)) {
    										 updatedDictionary.add(line);  
    									 }
    								 }
    							 }
    						 }
    					 }
    				 }			
    			}
    	    }
    	}
    	return updatedDictionary;
    }

}
