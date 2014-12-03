package sendrovitz.scrabble;


import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

public class ScrabbleDictionaryTest {

	@Test //need to have an annotation 
	public void testContainsTrue() throws FileNotFoundException { //methods begins with word test
		ScrabbleDictionary dictionary = new ScrabbleDictionary();
		Assert.assertTrue(
				dictionary.contains("HELLO"));
		Assert.assertTrue(
				dictionary.contains("hello"));
		Assert.assertTrue(
				dictionary.contains("Hello"));
		

	}

	@Test //need to have an annotation 
	public void testContainsFalse() throws FileNotFoundException { //methods begins with word test
		ScrabbleDictionary dictionary = new ScrabbleDictionary();
		Assert.assertFalse(
				dictionary.contains("fiwehofiwe"));
		Assert.assertFalse(
				dictionary.contains("FIGPOK"));
		
		
	}
	@Test //need to have an annotation 
	public void testContainsNull() throws FileNotFoundException { //methods begins with word test
		ScrabbleDictionary dictionary = new ScrabbleDictionary();
		Assert.assertFalse(
				dictionary.contains(null)); //assertFalse because shouldn't have any nulls. 
		                                    //says null pointer exception because your passing a null to the contains method. and then it 
											//it tries to do toUpperCase() but it cant. so add in an if statement to protect yourself (defensive programming)
		
		
	}


}
