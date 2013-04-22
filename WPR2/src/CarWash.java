public class CarWash implements Runnable {
	private static final int MAX_CARS = 5;
	private final ParkingLot parkingLot;
	private final String name;
	private int count = 0;
	private boolean running = false;
	private boolean complete = false;

	public CarWash(String name, ParkingLot pl) {
		this.name = name;
		this.parkingLot = pl;
	}

	private void spray(Car car) throws InterruptedException {
		System.out.println(this + " Start spray: " + car);
		// simulate delay
		Thread.sleep(1000);
		System.out.println(this + " End spray: " + car);
	}

	private void scrub(Car car) throws InterruptedException {
		System.out.println(this + " Start scrub: " + car);
		// simulate delay
		Thread.sleep(1500);
		System.out.println(this + " End scrub: " + car);
	}

	private void rinse(Car car) throws InterruptedException {
		System.out.println(this + " Start rinse: " + car);
		// simulate delay
		Thread.sleep(250);
		System.out.println(this + " End rinse: " + car);
	}

	private void dry(Car car) throws InterruptedException {
		System.out.println(this + " Start dry: " + car);
		// simulate delay
		Thread.sleep(100);
		System.out.println(this + " End dry: " + car);
	}

	@Override
	public void run() {
		running = true;
		try {
			// The car wash can only wash MAX_CARS and it should shut off when
			// there are no more dirty cars
			while (count < MAX_CARS && parkingLot.dirtyCount() > 0) {
				// Get a dirty car from the parking lot and wash it
				// Thread confinement. the car is instantiated in the runnable part
				Car car = parkingLot.removeNextDirtyCar();
				if (car != null) {
					System.out.println(this + " Starting");
					synchronized(this){
					spray(car);
					scrub(car);
					rinse(car);
					dry(car);
					car.clean();
					count++;
					parkingLot.add(car);
					System.out.println(this + " Finished car #" + count
							+ " of " + MAX_CARS);
					}
				} else {
					System.out.println(this + " Waiting for dirty car.");
					Thread.sleep(2000);
				}
			}
			running = false;
			complete = true;
			System.out.println(this + " Washed " + count
					+ " cars. Shutting Down.");
		} catch (InterruptedException e) {
			running = false;
			complete = true;
			Thread.currentThread().interrupt();
		}

	}

	public int getFinalWashCount() throws InterruptedException {
		while (running && !complete) {
			// busy wait until running is false and complete is true.
		}
		return count;
	}

	@Override
	public String toString() {
		return name + " in " + Thread.currentThread().getName();
	}

}
