 public class Car {

	private boolean clean = true;
	final private String licensePlate;
	final private String make;
	final private String model;

	// rental rate in dollars/hour. Must be > 0;
	final private double rate;

	final private int year;

	public Car(String licensePlate, String model, String make, int year,
			double rate) {

		if (licensePlate.length() > 10) {
			throw new IllegalArgumentException(
					"licensePlate is over 10 characters");
		}
		if (licensePlate.length() < 6) {
			throw new IllegalArgumentException(
					"licensePlate is under 6 characters");
		}
		this.licensePlate = licensePlate;

		if (model.length() > 20) {
			throw new IllegalArgumentException("model is over 20 characters");
		}
		this.model = model;

		if (make.length() > 20) {
			throw new IllegalArgumentException("make is over 20 characters");
		}
		this.make = make;
		if (year < 0 || year > 3000) {
			throw new IllegalArgumentException(
					"year must be between 0 and 3000 (inclusive)");
		}
		this.year = year;
		
		if (rate < 0) {
			throw new IllegalArgumentException("rate must not be < 0");
		}
		this.rate = rate;
	}

	public   void clean() {
		this.clean = true;
	}

	public  void dirty() {
		this.clean = false;
	}

	public  String getLicensePlate() {
		return licensePlate;
	}

	public  String getMake() {
		return make;
	}

	public  String getModel() {
		return model;
	}

	public double getRate() {
		return rate;
	}

	public int getYear() {
		return year;
	}
// should probably synchronize the isClean() and isDirty()
	public  boolean isClean() {
		return clean;
	}

	public boolean isDirty() {
		return !clean;
	}

	@Override
	public String toString() {
		return new String(model + " " + make + " " + licensePlate);
	}

}
