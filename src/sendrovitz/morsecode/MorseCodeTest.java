package sendrovitz.morsecode;


import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

public class MorseCodeTest {

	@Test
	public void testToMorseCode() throws FileNotFoundException{
		MorseCode code = new MorseCode();
		String encoded= code.toMorseCode("SOS");
		Assert.assertEquals("... --- ...",encoded); //("what you expect to have", actual code)
	}
	@Test
	public void testToPlainText() throws FileNotFoundException{
		MorseCode code = new MorseCode();
		String encoded= code.toPlainText("... --- ...");
		Assert.assertEquals("SOS", encoded);

	}
	}
