package hw5;

import java.util.Random;

/**
 * (basic description of the program or class)
 * 
 * Completion time: (estimation of hours spent on this program)
 *
 * @author Somya Khare, Acuna, Sedgewick
 * @version 09/29/21
 */


public class KhareNonUniform implements SER222_02_01_HW02_Submission {
    
    /***************************************************************************
     * START - SORTING UTILITIES, DO NOT MODIFY (FROM SEDGEWICK)               *
     **************************************************************************/
    
    public static void insertionSort(Comparable[] a) {
        int N = a.length;
        
        for (int i = 1; i < N; i++)
        {
            // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..          
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
    }
    
    
    public static void shellsort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        
        while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
        
        while (h >= 1) {
            // h-sort the array.
            for (int i = h; i < N; i++) {
                // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                exch(a, j, j-h);
            }
            h = h/3;
        }
    }
    
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }
    
    /***************************************************************************
     * END - SORTING UTILITIES, DO NOT MODIFY                                  *
     **************************************************************************/

    //TODO: implement interface methods.

    public static void main(String args[]) {
        SER222_02_01_HW02_Submission me = new KhareNonUniform();
        int size = 4096;
        
        //NOTE: feel free to change size here. all other code must go in the
        //      methods.
        
        me.runBenchmarks(size);
    }
    
    public int random()
    {
    	Random r = new Random();
    	if (r.nextBoolean()) {
    		return 0;
    	 } 
    	else {
    		return 1;
    	 }
    }


	
	public Integer[] generateTestDataBinary(int size) {
		int half = size/2;
		Integer[] arr = new Integer[half*2];
		for(int i=0;i<=arr.length;i++)
		{
			arr[i]= random();
		}
		return arr;
	}


	public Integer[] generateTestDataHalfs(int size) {
		int half = size/2;
		Random r = new Random();
		Integer[] arr = new Integer[half*2];
		for(int i=0;i<=arr.length;i++)
		{
			arr[i]= random()*r.nextInt();
		}
		return arr;
	}



	public Integer[] generateTestDataHalfRandom(int size) {
		int half = size/2;
		Integer[] arr = new Integer[half*2];
		for(int i=0;i<=arr.length;i++)
		{
			arr[i]=random();
		}
		//if half are 1, then it will convert half of them into 2
		for(int i=0;i<=arr.length;i++)
		{
			if (arr[i]==1)
			{
				arr[i]=random()+1;
			}
		}
		//if half are 2 it will convert half of them into 3
		for(int i=0;i<=arr.length;i++)
		{
			if (arr[i]==2)
			{
				arr[i]=random()+1;
			}
		}
		return arr;
	}


	@Override
	public double computeDoublingFormula(double t1, double t2) {
		double b = Math.log(t2/t1);
		return b;
	}


	@Override
	public double benchmarkInsertionSort(Integer[] small, Integer[] large) {
		Stopwatch s= new Stopwatch();
		insertionSort(small);
		double t1= s.elapsedTime();
		Stopwatch l = new Stopwatch();
		insertionSort(large);
		double t2= l.elapsedTime();
		return computeDoublingFormula(t1,t2);
	}


	@Override
	public double benchmarkShellsort(Integer[] small, Integer[] large) {
		Stopwatch s= new Stopwatch();
		shellsort(small);
		double t1= s.elapsedTime();
		Stopwatch l = new Stopwatch();
		shellsort(large);
		double t2= l.elapsedTime();
		return computeDoublingFormula(t1,t2);
	}


	@Override
	public void runBenchmarks(int size) {
		Integer[] binary = generateTestDataBinary(size*100);
		Integer[] half = generateTestDataHalfs(size*100);
		Integer[] halfRandom= generateTestDataHalfRandom(size*100);
		Integer[] binaryL = generateTestDataBinary(size*200);
		Integer[] halfL = generateTestDataHalfs(size*200);
		Integer[] halfRandomL= generateTestDataHalfRandom(size*200);
		
		System.out.println("Insertion Sort: "+benchmarkInsertionSort(binary,binaryL));
		System.out.println("Shell Sort: "+benchmarkShellsort(binary,binaryL));
		System.out.println("Insertion Sort: "+benchmarkInsertionSort(half, halfL));
		System.out.println("Shell Sort: "+benchmarkShellsort(half,halfL));
		System.out.println("Insertion Sort: "+benchmarkInsertionSort(halfRandom,halfRandomL));
		System.out.println("Shell Sort: "+benchmarkShellsort(halfRandom,halfRandomL));
		
	}
}