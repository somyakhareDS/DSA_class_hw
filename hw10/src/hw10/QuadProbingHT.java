package hw10;
/**
 * Quadratic Probing - increment the chain quadratically
 * 
 * @author Somya Khare
 * @version 10/1/21
 */
public class QuadProbingHT<Key, Value>extends LinearProbingHT<Key, Value>{
	
	private int M;
	
	public QuadProbingHT() {
		this(997);
	}
	private QuadProbingHT(int M) {
		super(M);
		this.M= M;
	}
	public int hash(Key key, int i) {
		return (((key.hashCode() & 0x7fffffff) + i * i) % M);
	}

}
