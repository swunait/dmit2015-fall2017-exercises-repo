package ca.nait.dmit;

import static org.junit.Assert.*;

import org.junit.Test;

public class CircleTest {

	@Test
	public void testGetArea() {
//		fail("Not yet implemented");
		// Create a circle with radius 1
		Circle circle1 = new Circle();
		assertEquals( 1, circle1.radius, 0);
		assertEquals( 3.14, circle1.getArea(), 0.002);
		assertEquals( 6.28, circle1.getPerimeter(), 0.01);
	}

}
