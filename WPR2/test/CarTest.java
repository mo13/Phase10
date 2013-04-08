import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CarTest {

	@Test(expected = IllegalArgumentException.class)
	public void testLicensePlateToLongFail() {
		new Car("0123456789ABCDEF", "TEST_MODEL", "TEST_MAKE", 2013, 1.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLicensePlateToShortFail() {
		new Car("01234", "TEST_MODEL", "TEST_MAKE", 2013, 1.00);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testModelFail() {
		new Car("0123456789", "TEST_MODEL_0123456789ABCDEF", "TEST_MAKE", 2013,
				1.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMakeFail() {
		new Car("0123456789", "TEST_MODEL", "TEST_MAKE_0123456789ABCDEF", 2013,
				1.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testYearLowFail() {
		new Car("0123456789", "TEST_MODEL", "TEST_MAKE", -1, 1.00);
	}

	@Test()
	public void testYearLowBoundaryPass() {
		Car goodCar = new Car("0123456789", "TEST_MODEL", "TEST_MAKE", 0, 1.00);
		assertTrue("0 is a valid year", goodCar.getYear() == 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testYearHighFail() {
		new Car("0123456789", "TEST_MODEL", "TEST_MAKE", 3001, 1.00);
	}

	@Test()
	public void testYearHighBoundaryPass() {
		Car goodCar = new Car("0123456789", "TEST_MODEL", "TEST_MAKE", 3000,
				1.00);
		assertTrue("3000 is a valid year", goodCar.getYear() == 3000);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRateLowFail() {
		new Car("0123456789", "TEST_MODEL", "TEST_MAKE", 2013, -1);
	}

	@Test()
	public void testRateLowBoundaryPass() {
		Car goodCar = new Car("0123456789", "TEST_MODEL", "TEST_MAKE", 2013, 0);
		assertTrue("0 is a valid rate", goodCar.getRate() == 0);

	}

	@Test
	public void testCleanAndDirty() {
		Car goodCar = new Car("0123456789", "TEST_MODEL", "TEST_MAKE", 2013, 0);
		assertTrue("car is clean on construction", goodCar.isClean());
		goodCar.dirty();
		assertTrue("car is now dirty", goodCar.isDirty());
		goodCar.clean();
		assertTrue("car is now clean", goodCar.isClean());

	}

	@Test
	public void testGetters() {
		Car goodCar = new Car("0123456789", "TEST_MODEL", "TEST_MAKE", 2013, 0);
		assertEquals("0123456789", goodCar.getLicensePlate());
		assertEquals("TEST_MODEL", goodCar.getModel());
		assertEquals("TEST_MAKE", goodCar.getMake());
		assertEquals(2013, goodCar.getYear());
		assertTrue("rate", goodCar.getRate() == 0);
	}

	@Test
	public void testToString() {
		Car goodCar = new Car("0123456789", "TEST_MODEL", "TEST_MAKE", 2013, 0);
		assertEquals("TEST_MODEL TEST_MAKE 0123456789", goodCar.toString());
	}

}
