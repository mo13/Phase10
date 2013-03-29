public class CrazyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
	public CrazyBoundedBuffer(int size) {
		super(size);
	}

	public void put(V v) {
		doPut(v);
	}

	public V take() {
		return doTake();

	}

}
