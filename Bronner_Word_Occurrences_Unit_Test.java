package bronner_word_occurrences;

//Author Name: Devon Bronner
//Date: 10/30/2021
//Program Name: Bronner_Word_Occurrences_Unit_Test
//Purpose: Add unit testing to our word occurrence application to be able to enhance testing the program

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class Bronner_Word_Occurrences_Unit_Test {
	/**
	 * Unit testing for the count words method
	 */
	@Test
	public void testCountWords(){
		//create the maps to test 
		Map<String, Integer> words = new HashMap<>();
		Map<String, Integer> testWords = new HashMap<>();
		 
		try {
			//fill first map with countWords
			Bronner_Word_Occurrences.countWords("MacBeth.txt", words);
			
			//Check to see if the maps are equal 
			assertEquals("Maps do not contain same contents", words, Bronner_Word_Occurrences.countWords("MacBeth.txt", testWords));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Unit testing for the map sorting method
	 */
	@Test
	public void testSortMap() {
		//create maps to test sortMap
		Map<String, Integer> words = new HashMap<>();
		Map<String, Integer> testWords = new HashMap<>();
		 
		try {
			//fill both maps
			Bronner_Word_Occurrences.countWords("MacBeth.txt", words);
			Bronner_Word_Occurrences.countWords("MacBeth.txt", testWords);
			
			//sort first map
			Bronner_Word_Occurrences.sortMap(words);
			
			//test to see if both maps are equal after sorting
			assertEquals("Maps not sorted the same",words, Bronner_Word_Occurrences.sortMap(testWords));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
