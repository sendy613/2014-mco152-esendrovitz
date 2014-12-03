package sendrovitz.triangle;


import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {

	@Test
	public void testTriangle() {
		Triangle triangle = new Triangle(3);
		String test = triangle.toString();
		Assert.assertEquals("  *\n * *\n*****", test);
	}

}
