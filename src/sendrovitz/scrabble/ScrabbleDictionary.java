package sendrovitz.scrabble;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ScrabbleDictionary {
	private Set<String> word; //private means that only that class can access it
	
	public ScrabbleDictionary() throws FileNotFoundException{
	 try{
	String filename = "OWL.txt";
	File file = new File(filename);
	Scanner inputFile = new Scanner(file);
	
	
	this.word= new HashSet<String>(); //referencing this array to word ArrayList outside of constructor
	while(inputFile.hasNext()){
		String tempWord = inputFile.next();
		word.add(tempWord);
		inputFile.nextLine();
		
	}
	 inputFile.close();
	 }
	 catch(FileNotFoundException ex1){
		 System.out.println("Cannot find file");
	 }
}
public boolean contains(String checkWord){
	if(checkWord== null){
		return false;
	}
	String upperCase = checkWord.toUpperCase();
	return word.contains(upperCase); //instead of using an if statement to test something to see if its true or false. this way its one statement
}

public static void main(String args[])throws FileNotFoundException{
//	ScrabbleDictionary scrabbleDictionary1 = new ScrabbleDictionary();
//	System.out.println("Enter a word");
//	Scanner keyboard = new Scanner(System.in);
//	String testWord = keyboard.nextLine();
//	boolean test =scrabbleDictionary1.contains(testWord);
//	System.out.println(test);
	
	
	ScrabbleDictionary dictionary = new ScrabbleDictionary();
	long startTime = System.currentTimeMillis(); //static method
	for(int i=0; i<1000000; i++){
		dictionary.contains("pepper");
	}
	long endTime = System.currentTimeMillis();
	System.out.println(endTime-startTime);
}


}
