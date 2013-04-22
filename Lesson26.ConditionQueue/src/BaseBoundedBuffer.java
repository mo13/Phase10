public abstract class BaseBoundedBuffer<V> {
	private final V[] buf;
	private int tail; // the end of the Buffer
	private int head; // the beginnning of the Buffer
	private int count; // the size of the buffer
	
	static final int SLEEP_GRANULARITY = 1;

	@SuppressWarnings("unchecked")
	protected BaseBoundedBuffer(int capacity) {
		this.buf = (V[]) new Object[capacity];
	}

	protected synchronized final void doPut(V v) {
		buf[tail] = v; // add what ever you are adding to the end of the buffer
		if (++tail == buf.length) // if incrementing the tail is equal to the length then set it equal to 0 
			tail = 0;
		++count; // increment the size by 1. 
	}

	protected synchronized final V doTake() {
		V v = buf[head];
		buf[head] = null;
		if (++head == buf.length)
			head = 0;
		--count;
		return v;
	}

	public synchronized final boolean isFull() {
		return count == buf.length;
	}

	public synchronized final boolean isEmpty() {
		return count == 0;
	}
}
