import java.io.File;
import java.util.*;

public class Indexer implements Runnable {

	private final Queue<File> blockingQueue;

	public Indexer(Queue<File> fileQueue) {
		super();
		this.blockingQueue = fileQueue;
	}

	private void indexFile(File file) throws InterruptedException {
		System.out.println("Indexing file " + file.getName());
		// sleep to simulate work
		Thread.sleep(1);

	}

	@Override
	public void run() {
		try {
			while (true)
				indexFile(blockingQueue.poll());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}

}
