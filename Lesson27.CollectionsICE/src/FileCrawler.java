import java.io.File;
import java.io.FileFilter;
import java.util.Queue;

public class FileCrawler implements Runnable {
	private final Queue<File> fileQueue;
	private final FileFilter fileFilter;
	private final File root;

	public FileCrawler(Queue<File> fileQueue, FileFilter fileFilter, File root) {
		this.fileQueue = fileQueue;
		this.fileFilter = fileFilter;
		this.root = root;
	}

	@Override
	public void run() {
		try {
			crawl(root);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}

	private void crawl(File root) throws InterruptedException {
		File[] entries = root.listFiles(fileFilter);
		if (entries != null) {
			for (File entry : entries)
				if (entry.isDirectory())
					crawl(entry);
				else if (!fileQueue.contains(entry)) {
					System.out.println("Found new entry " + entry.getName());
					// sleep to simulate system delay
					Thread.sleep(1);
					fileQueue.add(entry);
				}
		}
	}
}
