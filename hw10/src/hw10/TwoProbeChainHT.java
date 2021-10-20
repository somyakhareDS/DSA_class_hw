package hw10;

import java.util.LinkedList;

/**
 * Two Probe Chain - A normal chaining approach
 * 
 * @author Somya Khare
 * @version 10/1/21
 */
public class TwoProbeChainHT<Key, Value> implements SymbolTable<Key, Value> {

	private int N;
	private int M;
	private LinkedList<Entry>[] entries;
	
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
	
	public TwoProbeChainHT() {
		this(997);
	}
	
	
	public TwoProbeChainHT(int M) {
		this.M = M;
		entries = (LinkedList<Entry>[]) new LinkedList[M];
		
		for(int i=0;i<M;i++) {
			entries[i]= new LinkedList<>();
		}
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	//Second Hash function
	private int hashTwo(Key key) {
        return (((key.hashCode() & 0x7fffffff) % M) * 31) % M;
    }


	@Override
	public void put(Key key, Value val) {
		if(UpdatePut(key,val)) {
			return;
		}
		smallerChain(key).add(new Entry(key,val));
		N++;
	}

	private LinkedList<Entry> smallerChain(Key key) {
		if(entries[hash(key)].size()<entries[hashTwo(key)].size()) {
			return entries[hash(key)];
		}
		return entries[hashTwo(key)];
	}


	private boolean UpdatePut(Key key, Value val) {
		for(int i=0; i<entries[hash(key)].size();i++) {
			if(i<entries[hash(key)].size()) {
				return true;
			}
		}
		
		for(int i=0; i<entries[hashTwo(key)].size();i++) {
			if(i<entries[hashTwo(key)].size()) {
				return true;
			}
		}
		return false;
	}


	 @Override
	    public Value get(Key key) {
	        for (int i = 0; i < entries[hash(key)].size(); i++) {
	            if (entries[hash(key)].get(i).key.equals(key)) {
	                return (Value) entries[hash(key)].get(i).value;
	            }
	        }
	        for (int i = 0; i < entries[hashTwo(key)].size(); i++) {
	            if (entries[hashTwo(key)].get(i).key.equals(key)) {
	                return (Value) entries[hashTwo(key)].get(i).value;
	            }
	        }
	        return null;
	    }



	    @Override
	    public void delete(Key key) {
	        for (int i = 0; i < entries[hash(key)].size(); i++) {
	            if (entries[hash(key)].get(i).key.equals(key)) {
	                entries[hash(key)].remove(i);
	                N--;
	            }

	        }

	        for (int i = 0; i < entries[hashTwo(key)].size(); i++) {
	            if (entries[hashTwo(key)].get(i).equals(key)) {
	                entries[hashTwo(key)].remove(i);
	                N--;
	            }
	        }
	    }

	    @Override
	    public boolean contains(Key key) {
	        return get(key) != null;
	    }

	    @Override
	    public boolean isEmpty() {
	        return size() == 0;
	    }

	    @Override
	    public int size() {
	        return N;
	    }

	    @Override
	    public Iterable<Key> keys() {
	        LinkedList<Key> keys = new LinkedList<Key>();
	        for (int i = 0; i < M; i++) {
	            for (int j = 0; j < entries[i].size(); j++)
	                keys.add((Key) entries[i].get(j).key);
	        }
	        return keys;
	    }

}
