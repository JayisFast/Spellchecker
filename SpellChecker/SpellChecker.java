package SpellChecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.TreeSet;

import book.Book;
import dictionary.Dictionary;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SpellChecker extends Application {
	private Dictionary mainDictionary;
	private Book book;
	private final double PREF_HEIGHT_LISTVIEW = 900.0;
	private ObservableList<String> collectionMisspelledWords;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("SpellChecker Version 1.0");
		mainDictionary = new Dictionary("main.txt");
		mainDictionary.loadDictionary();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text", "*.txt"));
		
		ListView<String> listViewDictionary = new ListView<>(mainDictionary.getObservableList()); listViewDictionary.setPrefHeight(PREF_HEIGHT_LISTVIEW);
	
		
		Text dictionaryTitle = new Text("\nDictionary: " + mainDictionary.getSize());
		Text fileNotFoundWarning = new Text("File not found");

		VBox vBoxDictionary = new VBox();
			vBoxDictionary.getChildren().addAll(dictionaryTitle, listViewDictionary);
			vBoxDictionary.setVisible(false);
		VBox vBoxText = new VBox();
			vBoxText.setVisible(false);
		VBox vBoxWords = new VBox();
			vBoxWords.setVisible(false);
		HBox hBoxContentsPane = new HBox();
			hBoxContentsPane.getChildren().addAll(vBoxDictionary, vBoxWords, vBoxText);
		VBox containsAll = new VBox();
			containsAll.setPrefSize(1600, 800);
			containsAll.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		
			
			
		//Creates MenuBar 
		MenuBar menuBar = new MenuBar(); 
    
    Menu fileMenu = new Menu("File");
    Menu chooseFile = new Menu("Choose File...");

    MenuItem oliverTxt = new MenuItem("Oliver.txt");
		oliverTxt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				book = new Book("Oliver.txt");
				try {
					book.loadPredifinedBook();
					final int numMisspelledWords = simpleSpellCheck();
					Text wordTitle = new Text("Unique Misspelled Words: " + getWordSize() + "\nTotal:\t\t\t\t" + numMisspelledWords);
					Text paragraphTitle = new Text("\nParagraphs: " + book.getParagraphSize());
					ListView<String> listViewBook = new ListView<>(book.getObservableListParagraphs());
					ListView<String> listViewWords = new ListView<>(getObservableListWords());

					listViewBook.setPrefSize(1200, 900);
					listViewWords.setPrefSize(200, 900);
					vBoxText.getChildren().clear();
					vBoxWords.getChildren().clear();
					vBoxText.getChildren().addAll(paragraphTitle, listViewBook);
					vBoxWords.getChildren().addAll(wordTitle, listViewWords);
					containsAll.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
				}//end try 
				catch (FileNotFoundException e) {
					vBoxText.getChildren().add(fileNotFoundWarning);
				}//end catch
			}//end handle
		});//end EventHandler
		
    MenuItem oliverSmallTxt = new MenuItem("Oliver-small.txt");
		oliverSmallTxt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				book = new Book("Oliver-small.txt");

				try {
					book.loadPredifinedBook();
					final int numMisspelledWords = simpleSpellCheck();
					Text wordTitle = new Text("Unique Misspelled Words: " + getWordSize() + "\nTotal:\t\t\t\t" + numMisspelledWords);
					Text paragraphTitle = new Text("\nParagraphs: " + book.getParagraphSize());
					ListView<String> listViewBook = new ListView<>(book.getObservableListParagraphs());
					ListView<String> listViewWords = new ListView<>(getObservableListWords());

					listViewBook.setPrefSize(1400, 900);
					listViewWords.setPrefSize(200, 900);
					vBoxText.getChildren().clear();
					vBoxWords.getChildren().clear();
					vBoxText.getChildren().addAll(paragraphTitle, listViewBook);
					vBoxWords.getChildren().addAll(wordTitle, listViewWords);
					containsAll.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
				}//end try 
				catch (FileNotFoundException e) {
					vBoxText.getChildren().add(fileNotFoundWarning);
				}//end catch
			}//end handle
		});//end EventHandler
    	
    MenuItem other = new MenuItem("Other...");
		other.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file != null) {
					book = new Book();

					try {
						book.loadBook(file);
						final int numMisspelledWords = simpleSpellCheck();
						Text wordTitle = new Text("Unique Misspelled Words: " + getWordSize() + "\nTotal:\t\t\t\t" + numMisspelledWords);
						Text paragraphTitle = new Text("\nParagraphs: " + book.getParagraphSize());
						ListView<String> listViewBook = new ListView<>(book.getObservableListParagraphs());
						ListView<String> listViewWords = new ListView<>(getObservableListWords());

						listViewBook.setPrefSize(1400, 900);
						listViewWords.setPrefSize(200, 900);
						vBoxText.getChildren().clear();
						vBoxWords.getChildren().clear();
						vBoxText.getChildren().addAll(paragraphTitle, listViewBook);
						vBoxWords.getChildren().addAll(wordTitle, listViewWords);
						containsAll.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
					} catch (FileNotFoundException e) {
						vBoxText.getChildren().add(fileNotFoundWarning);
					}//end catch
				}//end if
			}//end handle
		});//end EventHandler
   
    chooseFile.getItems().addAll(oliverTxt, oliverSmallTxt, other);
    fileMenu.getItems().add(chooseFile);
    
    
    Menu displayMenu = new Menu("Display");
    Menu show = new Menu("Show...");
    
    MenuItem showDictionary = new MenuItem("Show Dictionary");
    	showDictionary.setOnAction(new EventHandler<ActionEvent>()			{ @Override public void handle(ActionEvent arg0) {vBoxDictionary.setVisible(true);}});
    MenuItem showText = new MenuItem("Show Paragraphs");
    	showText.setOnAction(new EventHandler<ActionEvent>()			{ @Override public void handle(ActionEvent arg0) {vBoxText.setVisible(true);}});
    MenuItem showWords = new MenuItem("Show Words");
    	showWords.setOnAction(new EventHandler<ActionEvent>()			{ @Override public void handle(ActionEvent arg0) {vBoxWords.setVisible(true);}});
    MenuItem showAll = new MenuItem("Show All");
			showAll.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					vBoxDictionary.setVisible(true);
					vBoxText.setVisible(true);
					vBoxWords.setVisible(true);
			}//end handle
		});//end EventHandler

    show.getItems().addAll(showDictionary, showWords, showText,  showAll);
    
    Menu hide = new Menu("Hide...");
    
    MenuItem hideDictionary = new MenuItem("Hide Dictionary");
    	hideDictionary.setOnAction(new EventHandler<ActionEvent>()			{ @Override public void handle(ActionEvent arg0) {vBoxDictionary.setVisible(false);}});
    MenuItem hideText = new MenuItem("Hide Paragraphs");
    	hideText.setOnAction(new EventHandler<ActionEvent>()			{ @Override public void handle(ActionEvent arg0) {vBoxText.setVisible(false);}});
    MenuItem hideWords = new MenuItem("Hide Words");
     	hideWords.setOnAction(new EventHandler<ActionEvent>()			{ @Override public void handle(ActionEvent arg0) {vBoxWords.setVisible(false);}});
    MenuItem hideAll = new MenuItem("Hide All");
		hideAll.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vBoxDictionary.setVisible(false);
				vBoxText.setVisible(false);
				vBoxWords.setVisible(false);
			}//end handle
		});//end EventHandler
    
    hide.getItems().addAll(hideDictionary, hideWords, hideText, hideAll);

    
    displayMenu.getItems().addAll(show, hide);
    menuBar.getMenus().addAll(fileMenu, displayMenu);
    containsAll.getChildren().addAll(menuBar, hBoxContentsPane);
		Scene scene = new Scene(containsAll);
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}//end start

	public int simpleSpellCheck() {
		TreeSet<String> treeSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
		int misspelledWords = 0;
	  long startTime = System.currentTimeMillis();
		
		for (String paragraph : book.getObservableListParagraphs()) {
			String[] wordList = paragraph.split ("[^a-zA-Z_0-9']+"); //list of sig chars (a-z, A-Z, numbers, and ')
			for (String word : wordList) {
				if (word.length() > 0  && ! checkWord(word)){
					treeSet.add(word);
					misspelledWords++;
				}//end if
			}//end for
		}//end for
		collectionMisspelledWords = FXCollections.observableArrayList(new TreeSet<String>(treeSet));
		
		double elapseTime = (double)(System.currentTimeMillis() - startTime)/1000.0;
		System.out.printf("Collection Type:%s simpleSpellCheck() Word Count:%d Elapse Time:%.3f seconds\n", collectionMisspelledWords.getClass().getName(), collectionMisspelledWords.size(), elapseTime);
    
		return misspelledWords;
	}//end simplifiedCheckSpelling
	
	public boolean checkWord(String word) {
			boolean check;
      int indexPos = Collections.binarySearch(mainDictionary.getObservableList(), word.toLowerCase());
    
      if (indexPos >= 0)
      	check = true;
      else 
      	check = false;
      return check;
	}//end checkWord
	
	public ObservableList<String> getObservableListWords() {
		return collectionMisspelledWords;
	}//end getObservableListParagraphs
	
	
	public int getWordSize() {
		return collectionMisspelledWords.size();
	}//end getWordSize
	
	//For jUnit testing ONLY. Does all calls programmatically, as opposed to being user-driven.
	public void testJUnit() throws FileNotFoundException{
		book = new Book("Oliver.txt");
		mainDictionary = new Dictionary("main.txt");
		mainDictionary.loadDictionary();
		
		book.loadPredifinedBook();
		simpleSpellCheck();
	}//end testJUnit
	
	public static void main(String[] args) { launch(args); }
}//end main 