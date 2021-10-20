package hw6;

import java.util.Random;

/**
 * Implements various merge style algorithms.
 * 
 * Completion time: 5:30 am
 *
 * @author Somya Khare, Acuna, Sedgewick and Wayne
 * @verison 09/29/21
 */

public class KhareMerging {
     
    /**
     * Entry point for sample output.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Queue q1 = new ListQueue(); q1.enqueue("T"); q1.enqueue("R"); q1.enqueue("O"); q1.enqueue("L"); q1.enqueue("E");
        Queue q2 = new ListQueue(); q2.enqueue("X"); q2.enqueue("S"); q2.enqueue("P"); q2.enqueue("M"); q2.enqueue("E"); q2.enqueue("A");        
        Queue q3 = new ListQueue(); q3.enqueue(20); q3.enqueue(17); q3.enqueue(15); q3.enqueue(12); q3.enqueue(5);
        Queue q4 = new ListQueue(); q4.enqueue(18); q4.enqueue(16); q4.enqueue(13); q4.enqueue(12); q4.enqueue(4); q4.enqueue(1);       
        
        //Q1 - sample test cases
        Queue merged1 = mergeQueues(q1, q2);
        System.out.println(merged1.toString());
        Queue merged2 = mergeQueues(q3, q4);
        System.out.println(merged2.toString());
        
        //Q2 - sample test cases
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(a);
        assert isSorted(a);
        show(a);
        
        //Q3 - sample test cases
        String[] b = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        shuffle(b);
        show(b);
        
        shuffle(b);
        show(b);
    }
    
    public static Queue mergeQueues(Queue<Comparable> q1, Queue<Comparable> q2) {
        Queue<Comparable> result = new ListQueue<Comparable>();
        
        while(!q1.isEmpty()&& !q2.isEmpty()){
        	if(less(q2.front(), q1.front())){
        		result.enqueue(q1.dequeue());
        	}
        	else {
        		result.enqueue(q2.dequeue());
        	}
        }
        
        while(!q1.isEmpty()) {
        	result.enqueue(q1.dequeue());
        }
        
        while(!q2.isEmpty()) {
        	result.enqueue(q2.dequeue());
        }
        return result;
    }
    
    public static void sort(Comparable[] a) {
        Comparable[] b = mergesort(a);
        
        for(int i=0;i<a.length;i++) {
        	a[i]=b[i];
        }
    }
    
    public static Comparable[] mergesort(Comparable[] a) {
    	int mid= a.length/2;
    	if(a.length==1) {
    		return a;
    	}
    	Comparable[] left=new Comparable[mid];
    	Comparable[] right= new Comparable[a.length-mid];
    	for(int i=0;i<mid;i++){
    		left[i]=a[i];
    	}
    	for(int i=0;i<a.length-mid;i++) {
    		right[i]=a[i+mid];
    	}
    	
    	left= mergesort(left);
    	right= mergesort(right);
    	
    	a=merge(left, right);
    	
    	return a;
    }
    
    public static Comparable[] merge(Comparable[] a, Comparable[] b) {
    	
    	int mid = a.length;
    	int high = b.length+mid;
    	
    	Comparable[] result = new Comparable[high];
    	int k=0;	//counter for a
    	int j=0;	//counter for b
    	for(int i=0;i<high;i++) {
    		if(k>mid) 					result[i]=b[j++];
    		else if(j>b.length) 		result[i]=a[k++];
    		else if(less(a[k],b[j])) 	result[i]=a[k++];
    		else						result[i]=b[j++];
    	}
    	
		return result;
    }
    /*
     * This function is O(nlog(n)) because it uses a single loop which is O(n), 
     * and the loop contains a single if statement which is at most O(log(n)),
     * which makes the function O(nlog(n)) in total.
     */
    public static void shuffle(Object[] a) {
        Random r = new Random();
        
        for(int i=0;i<a.length;i++) {
        	int randomIndex = i+ r.nextInt(a.length-i);
        	
        	Object swap = a[i];
        	if(i!= randomIndex) {
        		a[i]=a[randomIndex];
        		a[randomIndex]= swap;
        	}
        }
    }

    //sorting helper from text
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //sorting helper from text
    private static void show(Comparable[] a) {
        for (Comparable a1 : a)
            System.out.print(a1 + " ");

        System.out.println();
    }
    
    //sorting helper from text
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;
        
        return true;
    }
}