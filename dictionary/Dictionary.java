package dictionary;
import java.io.*;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Dictionary {
	private String fileName;
	private ObservableList<String> collectionWords;

	/** Constructor establishes the association with a disk-based file, so that all tests are performed on the same set of data. */
	public Dictionary(String fileName) {
		this.fileName = fileName;
	}//end constructor

	/**
	 * Uses the predefined filename to open a file and populate the collection created here.
	 * @throws FileNotFoundException
	 */
	public void loadDictionary() throws FileNotFoundException {
		TreeSet<String> treeSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
		Scanner scanInput = new Scanner(new File(fileName));
		long startTime = System.currentTimeMillis();
		
		while (scanInput.hasNext()) {
			String word = scanInput.next();
			treeSet.add(word.toLowerCase());
		}//end while
		
		scanInput.close();
		collectionWords = FXCollections.observableList(new ArrayList<String>(treeSet));
		
		double elapseTime = (double)(System.currentTimeMillis() - startTime)/1000.0;
		System.out.printf("Collection Type:%s loadDictionary() Word Count:%d Elapse Time:%.3f seconds\n", collectionWords.getClass().getName(), collectionWords.size(), elapseTime);

		
	} // end loadDictionary()

	public ObservableList<String> getObservableList() {
		return collectionWords;
	}//end getObservableList

	public int getSize() {
		return collectionWords.size();
	}//end getSize

} // end class Dictionary