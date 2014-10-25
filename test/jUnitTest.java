package test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import dictionary.Dictionary;
import SpellChecker.SpellChecker;
import book.Book;


public class jUnitTest {
	private static Book book;
	private static Dictionary dictionary;
	private static SpellChecker spellchecker;

	

	@BeforeClass
	public static void setUpClass() throws FileNotFoundException {
		spellchecker = new SpellChecker();
		book = new Book("Oliver.txt");
		dictionary = new Dictionary("main.txt");
		
		book.loadPredifinedBook();
		dictionary.loadDictionary();
		spellchecker.testJUnit();
		
		
	}
	
	@Ignore
	@AfterClass
	// performed once-and-only once after the test class is instantiated and after all test routines have terminated
	public static void tearDownClass() {
	}

	@Ignore
	@Before
	// performed before each test
	public void setUp() {
	}

	@Ignore
	@After
	// performed after each test
	public void tearDown() {
	}

	@Test
	// Ensures that dictionary is both sorted correctly, and retains no duplicates
	public void sortDictionaryTest() {
		int check;
		
		String previous = null;
		for (String current : dictionary.getObservableList()){
			if (previous == null){
					previous = current;
					continue;
			}//end if

			else{
				
				check = current.compareTo(previous);
				assertTrue(check > 0);
				previous = current;
			}//end else
		}//end for
	}//end sortDictionaryTest()
	
	@Test
	public void spellCheckTest(){
		for (String word : spellchecker.getObservableListWords()){
			assertFalse(dictionary.getObservableList().contains(word));
		}//end for
	}//end spellCheckTest
}
