import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;

public class ParkingLot {
	final Queue<Car> carQueue = new LinkedList<Car>();

	public ParkingLot(String fileName) throws Exception {
		loadFromCSV(fileName);
	}

	public Car removeNextDirtyCar() {
		for (Car car : carQueue) {
			if (car.isDirty()) {
				carQueue.remove(car);
				return car;
			}
		}
		return null;
	}

	public int dirtyCount() {
		int dirty = 0;
		for (Car car : carQueue) {
			if (car.isDirty()) {
				dirty++;
			}
		}
		return dirty;
	}

	public int cleanCount() {
		int clean = 0;
		for (Car car : carQueue) {
			if (car.isClean()) {
				clean++;
			}
		}
		return clean;
	}

	public int count() {
		return carQueue.size();
	}

	public boolean add(Car car) {
		return carQueue.add(car);
	}

	private void loadFromCSV(String fileName) throws Exception {

		FileReader CSVReader = new FileReader(fileName);

		BufferedReader CSVFile = new BufferedReader(CSVReader);

		String dataRow = CSVFile.readLine();

		while (dataRow != null) {
			String[] dataArray = dataRow.split(",");
			String licensePlate = dataArray[0];
			String make = dataArray[1];
			String model = dataArray[2];
			int year = Integer.parseInt(dataArray[3]);
			double rate = Double.parseDouble(dataArray[4]);
			boolean clean = Boolean.parseBoolean(dataArray[5]);

			Car tmpCar = new Car(licensePlate, make, model, year, rate);
			if (clean) {
				tmpCar.clean();
			} else {
				tmpCar.dirty();
			}
			carQueue.add(tmpCar);

			dataRow = CSVFile.readLine();
		}

		CSVFile.close();

	}
}
