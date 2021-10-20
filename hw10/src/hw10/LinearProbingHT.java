package hw10;

import java.util.LinkedList;

/**
 * Linear Probing - incrementing the chaining system by 1
 * 
 * @author Somya Khare
 * @version 10/1/21
 */
public class LinearProbingHT<Key, Value> implements SymbolTable<Key, Value> {

	private int N;
	private int M;
	private Entry<Key, Value>[] entries;
	
	public class Entry<Key, Value>{
		private Key key;
		private Value value;
		
		private Entry(Key key, Value value) {
			if(value==null||key==null)
				throw new IllegalArgumentException("Null Key");
			
			this.key=key;
			this.value= value;
		}
	}
	
	
	
	public LinearProbingHT(){
		this(97);
	}
	public LinearProbingHT(int M) {
		this.M= M;
		this.N=0;
		
		this.entries= new Entry[M];
	}
	
	 private int hash(Key key) {
	    return (key.hashCode() & 0x7fffffff) % M;
	}
	@Override
	public void put(Key key, Value val) {
		int i=0;
		
		for(i=hash(key); entries[i]!=null;i=i+1% M) {
			if((entries[i].key).equals(key)) {
				entries[i].value=val;
				return;
			}
		}
		entries[i] = new Entry<Key, Value>(key, val);
		N++;
	}

	@Override
	public Value get(Key key) {
		for(int i= hash(key); entries[i]!=null;i=(i+1)%M) {
			if((entries[i].key).equals(key)){
				return entries[i].value;
			}
		}
		return null;
	}

	@Override
	public void delete(Key key) {
		for(int i =hash(key); entries[i]!=null;i=(i+1)%M) {
			if((entries[i].key).equals(key)) {
				for(int j=i+1; entries[j]!=null&&j>hash(entries[j].key); j=(j+1)%M) {
					entries[i]=entries[j];
					i=(i+1)% M;
				}
				entries[i]=null;
				N--;
			}
		}
	}

	@Override
	public boolean contains(Key key) {
		return get(key) !=null;
	}

	@Override
	public boolean isEmpty() {
		return N==0;
	}

	@Override
	public int size() {
		return N;
	}

	@Override
	public Iterable<Key> keys() {
		LinkedList<Key> list= new LinkedList<Key>();
		for(int i=0;i<M;i++) {
			if(entries[i]!=null) {
				list.add(entries[i].key);
			}
		}
		return list;
	}
}
