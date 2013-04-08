import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.Test;

public class ParkingLotTest {

	@Test(expected = FileNotFoundException.class)
	public void testParkingLotBadFileName() throws Exception {
		try {
			new ParkingLot("./data/non-existent-file.csv");
		} catch (Exception e) {
			throw e;
		}
	}

	@Test()
	public void testParkingLotEmptyFile() {
		ParkingLot pl = null;
		try {
			pl = new ParkingLot("./data/TestNoCars.csv");
		} catch (Exception e) {
			fail("unexpected exception " + e);
		}

		assertEquals(0, pl.count());
	}

	@Test()
	public void testParkingLotOneCleanCar() {
		ParkingLot pl = null;
		try {
			pl = new ParkingLot("./data/TestOneCleanCar.csv");
		} catch (Exception e) {
			fail("unexpected exception " + e);
		}

		assertEquals(1, pl.count());
		assertEquals(1, pl.cleanCount());
		assertEquals(0, pl.dirtyCount());
	}

	@Test()
	public void testParkingLotOneDirtyCar() {
		ParkingLot pl = null;
		try {
			pl = new ParkingLot("./data/TestOneDirtyCar.csv");
		} catch (Exception e) {
			fail("unexpected exception " + e);
		}

		assertEquals(1, pl.count());
		assertEquals(0, pl.cleanCount());
		assertEquals(1, pl.dirtyCount());
	}

	@Test
	public void testRemoveNextDirtyCar() {
		ParkingLot pl = null;
		try {
			pl = new ParkingLot("./data/TestCars.csv");
		} catch (Exception e) {
			fail("unexpected exception " + e);
		}

		assertEquals(8, pl.count());
		assertEquals(2, pl.cleanCount());
		assertEquals(6, pl.dirtyCount());
		
		Car dirtyCar = pl.removeNextDirtyCar();
		assertEquals("\"Bus\" \"Yellow\" \"BIGONE\"",dirtyCar.toString());
		assertEquals(7,pl.count());
		assertEquals(2,pl.cleanCount());
		assertEquals(5,pl.dirtyCount());
		
		
		dirtyCar = pl.removeNextDirtyCar();
		assertEquals("\"Fiat\" \"Tracer\" \"GRRRRR\"",dirtyCar.toString());
		assertEquals(6,pl.count());
		assertEquals(2,pl.cleanCount());
		assertEquals(4,pl.dirtyCount());
		
		dirtyCar = pl.removeNextDirtyCar();
		assertEquals("\"Wilson\" \"Track Master\" \"NY1234\"",dirtyCar.toString());
		assertEquals(5,pl.count());
		assertEquals(2,pl.cleanCount());
		assertEquals(3,pl.dirtyCount());
		
		dirtyCar = pl.removeNextDirtyCar();
		assertEquals("\"Pontiac\" \"T-1000\" \"LTL-T1\"",dirtyCar.toString());
		assertEquals(4,pl.count());
		assertEquals(2,pl.cleanCount());
		assertEquals(2,pl.dirtyCount());
		
		dirtyCar = pl.removeNextDirtyCar();
		assertEquals("\"Kish Khodro\" \"Tri-Force\" \"MYPWNY\"",dirtyCar.toString());
		assertEquals(3,pl.count());
		assertEquals(2,pl.cleanCount());
		assertEquals(1,pl.dirtyCount());
		
		dirtyCar = pl.removeNextDirtyCar();
		assertEquals("\"Tranvias-Cimex\" \"Grande\" \"TECATE\"",dirtyCar.toString());
		assertEquals(2,pl.count());
		assertEquals(2,pl.cleanCount());
		assertEquals(0,pl.dirtyCount());
		
		dirtyCar = pl.removeNextDirtyCar();
		assertEquals(null,dirtyCar);
		assertEquals(2,pl.count());
		assertEquals(2,pl.cleanCount());
		assertEquals(0,pl.dirtyCount());
		
		dirtyCar = pl.removeNextDirtyCar();
		assertEquals(null,dirtyCar);
		assertEquals(2,pl.count());
		assertEquals(2,pl.cleanCount());
		assertEquals(0,pl.dirtyCount());
	}


	//tests count, dirtyCount, and cleanCount
	@Test
	public void testCounts() {
		ParkingLot pl = null;
		try {
			pl = new ParkingLot("./data/TestCars.csv");
		} catch (Exception e) {
			fail("unexpected exception " + e);
		}

		assertEquals(8, pl.count());
		assertEquals(2, pl.cleanCount());
		assertEquals(6, pl.dirtyCount());

	}


	@Test
	public void testAdd() {
		ParkingLot pl = null;
		try {
			pl = new ParkingLot("./data/TestCars.csv");
		} catch (Exception e) {
			fail("unexpected exception " + e);
		}

		assertEquals(8, pl.count());
		assertEquals(2, pl.cleanCount());
		assertEquals(6, pl.dirtyCount());
		
		Car dirtyCar = pl.removeNextDirtyCar();
		assertEquals("\"Bus\" \"Yellow\" \"BIGONE\"",dirtyCar.toString());
		assertEquals(7,pl.count());
		assertEquals(2,pl.cleanCount());
		assertEquals(5,pl.dirtyCount());
		
		pl.add(new Car("0123456789", "TEST_MODEL", "TEST_MAKE", 2013, 1.00));
		assertEquals(8,pl.count());
		assertEquals(3,pl.cleanCount());
		assertEquals(5,pl.dirtyCount());
	}

}
