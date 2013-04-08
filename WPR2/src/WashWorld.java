public class WashWorld {

	private static final int CARWASH_COUNT = 4;

	public static void main(String[] args) throws Exception {

		CarWash[] carWash = new CarWash[CARWASH_COUNT];
		Thread[] carWashThread = new Thread[CARWASH_COUNT];

		ParkingLot parkingLot = new ParkingLot("./data/Cars.csv");

		int startCarCount = parkingLot.count();
		int startDirtyCarCount = parkingLot.dirtyCount();
		int startCleanCarCount = parkingLot.cleanCount();

		for (int i = 0; i < CARWASH_COUNT; i++) {
			carWash[i] = new CarWash("carWash[" + i + "]", parkingLot);
			carWashThread[i] = new Thread(carWash[i]);
			carWashThread[i].start();
		}

		// make sure all the car washes are done
		for (int i = 0; i < CARWASH_COUNT; i++) {
			carWashThread[i].join();
		}

		int carWashCleanedTotal = 0;
		for (int i = 0; i < CARWASH_COUNT; i++) {

			System.out.println(carWash[i] + " cleaned "
					+ carWash[i].getFinalWashCount() + " cars.");
			carWashCleanedTotal += carWash[i].getFinalWashCount();

		}
		System.out.println("Washed a total of " + carWashCleanedTotal);

		System.out.println("startCarCount=" + startCarCount);
		System.out.println("endCarCount=" + parkingLot.count());
		System.out.println("startCleanCarCount=" + startCleanCarCount);
		System.out.println("endCleanCarCount=" + parkingLot.cleanCount());
		System.out.println("startDirtyCarCount=" + startDirtyCarCount);
		System.out.println("endDirtyCarCount=" + parkingLot.dirtyCount());

	}
}
