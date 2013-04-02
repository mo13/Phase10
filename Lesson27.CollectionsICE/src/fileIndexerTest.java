import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.Queue;

public class fileIndexerTest {

	public final static int CONSUMER_COUNT = 2;

	public static void main(String[] args) {
		File[] root = new File[10];
		// The users home directory
		// root[0] = new File(System.getProperty("user.home"));

		// The current working directory
		// When running the project from Eclipse this will be the root directory
		// of the project
		root[0] = new File(".");

		root[1] = new File("./src");

		startIndexing(root);

	}

	public static void startIndexing(File[] roots) {
		final Queue<File> fileQueue = new LinkedList<File>();

		// accept all files
		FileFilter filter = new FileFilter() {
			public boolean accept(File file) {
				return true;
			}
		};

		for (File root : roots)
			new Thread(new FileCrawler(fileQueue, filter, root)).start();

		for (int i = 0; i < CONSUMER_COUNT; i++)
			new Thread(new Indexer(fileQueue)).start();
	}
}
