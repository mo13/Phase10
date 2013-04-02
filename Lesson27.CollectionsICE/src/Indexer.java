import java.io.File;
import java.util.Queue;

public class Indexer implements Runnable {

	private final Queue<File> fileQueue;

	public Indexer(Queue<File> fileQueue) {
		super();
		this.fileQueue = fileQueue;
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
				indexFile(fileQueue.poll());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}

}
