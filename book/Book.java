package book;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.LinkedList;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Book {
	private String fileName;
	private ObservableList<String> collectionParagraphs;
	
	public Book(){};//end default constructor
	
	public Book(String fileName) {
		this.fileName = fileName;
	}//end constructor
	
	public void loadPredifinedBook() throws FileNotFoundException {
		loadBook(new File(fileName));
	}//end loadPredefinedBook
	
	public void loadBook(File file) throws FileNotFoundException {
		LinkedList<String> linkedList = new LinkedList<>();
		Scanner scanInput = new Scanner(file);
		long startTime = System.currentTimeMillis();

		while (scanInput.hasNext()) {
			String paragraph = scanInput.nextLine();
			linkedList.add(paragraph);
		}//end while
		
		scanInput.close();
		collectionParagraphs = FXCollections.observableList(linkedList);
		
		double elapseTime = (double)(System.currentTimeMillis() - startTime)/1000.0;
		System.out.printf("Collection Type:%s loadBook() Word Count:%d Elapse Time:%.3f seconds\n", collectionParagraphs.getClass().getName(), collectionParagraphs.size(), elapseTime);

	} // end loadBook()

	public ObservableList<String> getObservableListParagraphs() {
		return collectionParagraphs;
	}// end getObservableListParagraphs

	public int getParagraphSize() {
		return collectionParagraphs.size();
	}// end getParagraphSize

}//end class Book
