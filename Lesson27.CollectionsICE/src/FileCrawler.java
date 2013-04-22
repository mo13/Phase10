import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.concurrent.BlockingQueue;
public class FileCrawler implements Runnable {
	private final BlockingQueue<File> blockingQueue;
	private final FileFilter fileFilter;
	private final File root;

	public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
		this.blockingQueue = fileQueue;
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

	private  void  crawl(File root) throws InterruptedException {
		File[] entries = root.listFiles(fileFilter);
		if (entries != null) {
			for (File entry : entries)
				if (entry.isDirectory())
					crawl(entry);
				else if (!blockingQueue.contains(entry)) {
					System.out.println("Found new entry " + entry.getName());
					// sleep to simulate system delay
					Thread.sleep(1);
					blockingQueue.add(entry);
				}
		}
	}
}
