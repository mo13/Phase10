public class BoundedBufferTest {

	public static void main(String[] args) {

		// create a bounded buffer that can hold at least 10 integers
		final CrazyBoundedBuffer<Integer> buffer = new CrazyBoundedBuffer<Integer>(
				10);

		// create a task to fill up a bounded buffer with 20 integers
		Runnable producer = new Runnable() {
			public void run() {
				for (int i = 0; i < 20; i++) {
					buffer.put(i);
				}
			}
		};

		// create a task to consume and print the integers from the bounded
		// buffer
		Runnable consumer = new Runnable() {
			public void run() {
				for (int i = 0; i < 20; i++) {
					System.out.println("Buffer #" + i + " = "
							+ buffer.take());
				}
			}
		};

		// start both threads, initiating the run() for each task
		Thread t1 = new Thread(producer);
		t1.start();

		Thread t2 = new Thread(consumer);
		t2.start();
	}

}
