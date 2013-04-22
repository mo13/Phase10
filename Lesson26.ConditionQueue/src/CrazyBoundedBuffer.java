public class CrazyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
	public CrazyBoundedBuffer(int size) {
		super(size);
	}

	public synchronized void put(V v) throws InterruptedException {
		while (isFull()) 
			wait();
			doPut(v);
			notifyAll();
		
		doPut(v);
	}

	public synchronized V take() throws InterruptedException {
		while (isEmpty())
			wait();
			V v= doTake();
			notifyAll();
			return v;
		
	}

}
