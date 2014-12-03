package sendrovitz.test1;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class AirplaneTest {

//	@Test
//	public void testToStringWithEmptyPlane() {
//		Airplane plane = new Airplane("AB_CD_EF", 20);
//		Assert.assertEquals(
//				"    AB_CD_EF\n" +
//				"001 .._.._..\n" + 
//				"002 .._.._..\n" + 
//				"003 .._.._..\n", plane.toString());
//	}
	@Test
	public void testToStringWithEmptyPlane() {
		Airplane plane = new Airplane("AB_CD_EF", 21);
		Assert.assertEquals(
				"    AB_CD_EF\n" +
				"001 .._.._..\n" + 
				"002 .._.._..\n" +
				"003 .._.._..\n" +
				"004 .._.._..\n" +
				"005 .._.._..\n" +
				"006 .._.._..\n" +
				"007 .._.._..\n" +
				"008 .._.._..\n" +
				"009 .._.._..\n" +
				"010 .._.._..\n" +
				"011 .._.._..\n" +
				"012 .._.._..\n" +
				"013 .._.._..\n" +
				"014 .._.._..\n" +
				"015 .._.._..\n" +
				"016 .._.._..\n" +
				"017 .._.._..\n" +
				"018 .._.._..\n" +
				"019 .._.._..\n" +
				"020 .._.._..\n" +
				"021 .._.._..\n" 
									,plane.toString());
	}
	
	@Test
	public void testToStringWithFullPlane() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy(
				"1A", "1B", "1C", "1D", "1E", "1F",
				"2A", "2B", "2C", "2D", "2E", "2F",
				"3A", "3B", "3C", "3D", "3E", "3F");
		Assert.assertEquals(
				"    AB_CD_EF\n" +
				"001 OO_OO_OO\n" + 
				"002 OO_OO_OO\n" + 
				"003 OO_OO_OO\n", plane.toString());
	}
	
	@Test
	public void testGetNumSeats() {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Assert.assertEquals(18,plane.getNumSeats());
	}
	
	@Test
	public void testGetNumEmptySeats() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EFG", 5);
		plane.occupy("1A", "2G");
		Assert.assertEquals(33,plane.getNumEmptySeats());
	}

	@Test
	public void testIsFull() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD", 2);
		plane.occupy("1A", "2D");
		Assert.assertEquals(false,plane.isFull());
		plane.occupy("1B","1C","1D","2A","2B","2C");
		Assert.assertEquals(true, plane.isFull());
	}
	
	@Test
	public void testGetSeatThrowsUnknownSeatException() {
		Airplane plane = new Airplane("AB_CD_EFG", 5);
		try{
			plane.getSeat("5J");
			plane.getSeat("6A");
			Assert.fail("UnknownSeatException should throw here");
		}
		catch(UnknownSeatException s){
			
		}
	}
	
	@Test
	public void testOccupySeats() 
			throws UnknownSeatException, FullPlaneException, NotEnoughSeatsTogeatherException {
		Airplane plane = new Airplane("AB_CDEF", 1);
		int numSeatsTogeather = 2;
		plane.occupy("1A", "1D");
		List<Seat> list = plane.occupySeats(numSeatsTogeather);
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void testOccupySeatsThrowsNotEnoughSeatsTogeatherException() 
			throws FullPlaneException {
		Airplane plane = new Airplane("AB_C", 1);
		try{
		plane.occupySeats(3);
		Assert.fail("NotEnoughSeatsTogeatherException should throw here");
		}
		catch(NotEnoughSeatsTogeatherException e){
			
		}
	}
	
	@Test
	//can i add throws unknown seat exception
	public void testOccupySeatsThrowsFullPlaneException() throws NotEnoughSeatsTogeatherException, UnknownSeatException {
		Airplane plane = new Airplane("AB_C", 1);
		try{
		plane.occupy("1A","1B","1C");
		plane.occupySeats(2);
		Assert.fail("FullPlaneException should throw here");
		}
		catch(FullPlaneException e){
			
		}
	}

}
