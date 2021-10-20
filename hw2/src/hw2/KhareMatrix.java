package hw2;

import java.util.Arrays;

/**
 * An implementation of the Matrix ADT. Provides four basic operations over an
 * immutable type.
 * 
 * @author Somya Khare, Ruben Acuna
 * @version 09/28/21
 */
public class KhareMatrix implements Matrix {
	
	private int[][] Matrix;
    
    public KhareMatrix(int[][] matrix) {
    	
    	Matrix = new int[matrix.length][];
    	for(int i =0;i<matrix.length;i++)
    	{
    		Matrix[i]= Arrays.copyOf(matrix[i], matrix[i].length);
    	}

    }
    
	/**
     * Entry point for matrix testing.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int[][] data1 = new int[0][0];
        int[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] data3 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] data4 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        
        Matrix m1 = new KhareMatrix(data1);
        Matrix m2 = new KhareMatrix(data2);
        Matrix m3 = new KhareMatrix(data3);
        Matrix m4 = new KhareMatrix(data4);
        
        System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
        System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
        System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());
        
        //check for reference issues
        System.out.println("m2 -->\n" + m2);
        data2[1][1] = 101;
        System.out.println("m2 -->\n" + m2);

        //test equals
        System.out.println("m2==null: " + m2.equals(null));             //false
        System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX"));   //false
        System.out.println("m2==m1: " + m2.equals(m1));                 //false
        System.out.println("m2==m2: " + m2.equals(m2));                 //true
        System.out.println("m2==m3: " + m2.equals(m3));                 //false
        System.out.println("m3==m4: " + m3.equals(m4));                 //true
        
        //test operations (valid)
        System.out.println("2 * m2:\n" + m2.scale(2));
        System.out.println("m2 + m3:\n" + m2.plus(m3));
        System.out.println("m2 - m3:\n" + m2.minus(m3));
        
        //not tested... multiply(). you know what to do.
        
        //test operations (invalid)
        //System.out.println("m1 + m2" + m1.plus(m2));
        //System.out.println("m1 - m2" + m1.minus(m2));
    }

	@Override
	public int getElement(int y, int x) {
		return this.Matrix[x][y];
	}

	@Override
	public int getRows() {
		int rows=0;
		try {
			rows= Matrix.length;
		}catch(ArrayIndexOutOfBoundsException a) {
			System.out.println("No rows!");
		}
		return rows;
	}

	@Override
	public int getColumns() {
		int col=0;
		try {
			col= Matrix[0].length;
		}catch(ArrayIndexOutOfBoundsException a) {
			System.out.println("No columns!");
		}
		return col;
	}

	@Override
	public Matrix scale(int scalar) {
		int[][] result = new int[getRows()][getColumns()];
		for(int i=0;i<getRows();i++)
		{
			for(int j=0;j<getColumns();j++)
			{
				result[i][j]= Matrix[i][j]*scalar;
			}
		}
		return new KhareMatrix(result);
	}
	
	@Override
	public Matrix plus(Matrix other) {
		int[][] result = new int[getRows()][getColumns()];
		try {
			for(int i=0;i<getRows();i++)
			{
				for(int j=0;j<getColumns();j++)
				{
					result[i][j]= this.getElement(j, i)+other.getElement(j, i);
				}
			}
	        
			
		}catch(RuntimeException a) {
			System.out.println("Matrices do not have matching dimensions");
		}
		return new KhareMatrix(result);
	}

	@Override
	public Matrix minus(Matrix other) {
		int[][] result = new int[getRows()][getColumns()];
		try {
			for(int i=0;i<getRows();i++)
			{
				for(int j=0;j<getColumns();j++)
				{
					result[i][j]= this.getElement(j, i)-other.getElement(j, i);
				}
			}
	        
			
		}catch(RuntimeException a) {
			System.out.println("Matrices do not have matching dimensions");
		}
		return new KhareMatrix(result);
	}

	@Override
	public Matrix multiply(Matrix other) {
		int[][] result = new int[getRows()][getColumns()];
		try {
			for(int i=0;i<getRows();i++)
			{
				for(int j=0;j<getColumns();j++)
				{
					result[i][j]= this.getElement(j, i)*other.getElement(j, i);
				}
			}
	        
			
		}catch(RuntimeException a) {
			System.out.println("Matrices do not have matching dimensions");
		}
		return new KhareMatrix(result);
	}
	
	public boolean equals(Object other) {
		if(this.getClass()!=other.getClass())
		{
			return false;
		}
		Matrix m= (KhareMatrix)other;
		if(this.getRows()!=m.getRows()&& this.getColumns()!=m.getColumns())
		{
			return false;
		}
		for(int i=0;i<getRows();i++)
		{
			for(int j=0;j<getColumns();j++)
			{
				if(this.getElement(j, i)!=m.getElement(j,i))
				{
					return false;
				}
			}
		}
		return true;
	}
	public String toString() {
		String s="";
		for(int i=0;i<getRows();i++)
		{
			for(int j=0;j<getColumns();j++)
			{
				s= s+ this.getElement(j, i)+" ";
			}
			s= s+ System.lineSeparator();
		}
		
		return s;
	}
	    
}