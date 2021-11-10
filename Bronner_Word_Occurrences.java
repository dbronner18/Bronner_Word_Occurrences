package bronner_word_occurrences;

//Author Name: Devon Bronner
//Date: 10/17/2021
//Program Name: Bronner_Word_Occurrences
//Purpose: Count how many times each word in the play MacBeth occur.

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.List;

/**
 * Takes in a file and adds words in the file to the map. Words already added will have their count increased.
 * Uses a GUI to display the words and counts in the map in descending order.
 * @author DEVON
 *
 */
public class Bronner_Word_Occurrences {
	/**
	 * Loads the GUI
	 * @param args - array of the objects in main
	 * @throws FileNotFoundException - exception thrown if file not found
	 */
	public static void main (String[] args) throws FileNotFoundException {
		//Load GUI
		GUI();
	}
	
	/**
	 * Reads a file into a map word by word and counts how many times specific words appear
	 * @param fileName - name of file added to the map 
	 * @param words - map that the words and counts of each word are saved in
	 * @return - returns the map
	 * @throws FileNotFoundException - exception thrown if file not found
	 */
	public static Map<String, Integer> countWords (String fileName, Map<String, Integer> words) throws FileNotFoundException {
		Scanner file = new Scanner(new File(fileName));
		String word = null;
		
		//WHILE(file has a next element)
		while(file.hasNext()) {
			//set word to next element
			word = file.next();
			
			//set count equal to the value of the HashMap key for the word
			Integer count = words.get(word);
			
			//IF(value has been incremented)
			if(count != null)
				//increment value by 1
				count++;
			//ELSE(value has not been incremented before)
			else {
				//set count to 1
				count = 1;
			}
			//submit key and new value into map
			words.put(word, count);
		}
		//close file
		file.close();
		return words;
	}
	
	/**
	 * Sorts the map to display the words in descending order based on count of each word
	 * @param words - map of the words and counts of each word to be sorted 
	 * @return - returns the sorted map
	 */
	public static HashMap<String, Integer> sortMap(Map<String, Integer> words){
		List<Map.Entry<String, Integer> > listWords = new LinkedList<Map.Entry<String, Integer>>(words.entrySet());
		
		//sort the collection of the map entries using a Comparator that puts the entries in descending order
		Collections.sort(listWords, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
				return(obj2.getValue().compareTo(obj1.getValue()));
			}
		});
		
		
		HashMap<String, Integer> tempMap = new LinkedHashMap<>();
		
		//FOR(every entry in the linked list list of sorted entries)
		for(Map.Entry<String, Integer> entry : listWords) {
			//populate linked hash map with new entries maintaining sorted order
			tempMap.put(entry.getKey(), entry.getValue());
		}
		return tempMap;
	}
	
	/**
	 * Creates a GUI and calls methods to create the map from the file given 
	 * @throws FileNotFoundException - exception thrown if file not found 
	 */
	public static void GUI() throws FileNotFoundException {
		//create all variables for the GUI
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JTextArea textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


		Map<String, Integer> words = new HashMap<>();
		
		//call countWords with the .txt file 
		countWords("MacBeth.txt", words);
		
		//sort and set words map 
		words = sortMap(words);
		
		//FOR(all entries in the map)
		for(Map.Entry entry : words.entrySet()) {
			//append to the GUI text area
			textArea.append(entry.toString() + "\n");
		}
	
		//set up the frame to contain the text area and proper functionality
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setTitle("Word Occurrences");
		frame.add(scroll);
		frame.setVisible(true);
	}
}



