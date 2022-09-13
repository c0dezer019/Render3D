package com.codeinspace.render3d.lib.transform;

import com.codeinspace.render3d.lib.shapes.Triangle;
import com.codeinspace.render3d.lib.shapes.Vertex;

/**
 * Handles multiplication of matrix-matrix and vector-matrix multiplication.
 * 
 * <pre>XY rotation: |cos() -sin() 0|
 * 	     |sin() cols() 0|
 * 	     | 0     0	   1|
 * 
 *YZ rotation: |1   0    -sin()|
 * 	     |0  cos()  sin()|
 * 	     |0 -sin()  cos()|
 *
 *XZ rotation: |cos() 0 -sin()|
 * 	     | 0    1   0   |
 *             |sin() 0  cos()|</pre>
 *             
 * Formula: (AT<sub>1</sub>)T<sub>2</sub> = A(T<sub>1</sub>T<sub>2</sub>)
 * <br><br>
 * @author Brian Blankenship
 */
public class Matrix3 {
	private double[] cValues;
	
	/**
	 * Creates a 3x3 matrix.
	 * <br><br>
	 * @author Brian Blankenship
	 * @param values double[]
	 */
	public Matrix3(double[] values) {
		this.cValues = values;
	}
	
	/**
	 * Overload 1 of 6.
	 * <br><br>
	 * Returns the entire matrix.
	 * <br><br>
	 * Represented as:
	 * <pre>
	 * [0, 0, 0,
	 *  0, 0, 0,
	 *  0, 0, 0]
	 * </pre>
	 * @author Brian Blankenship
	 * @return The {@link Matrix3} matrix.
	 */
	public double[] values() {
		return cValues;
	}
	
	/**
	 * Overload 2 of 6.
	 * <br><br>
	 * Returns a single value from the matrix.
	 * @author Brian Blankenship
	 * @param i int
	 * @param j	int
	 * @param k	int
	 * @return A double
	 */
	public double values(int i, int j, int k) {
		return this.cValues[i * j + k];
	}
	
	/**
	 * Overload 3 of 6.
	 * <br><br>
	 * Returns a single value from a given {@link Matrix3}.
	 * 
	 * @author Brian Blankenship
	 * @param other {@link Matrix3}
	 * @param i int
	 * @param j int
	 * @param k int
	 * @return A double at the calculated index of the given {@link Matrix3}.
	 */
	public static double values(Matrix3 other, int i, int j, int k) {
		return other.values(i, j, k);
	}
	
	/**
	 * Overload 4 of 6.
	 * <br><br>
	 * Simple getter without the math.
	 * 
	 * @author Brian Blankenship
	 * @param i int
	 * @return A single value from the matrix.
	 */
	public double values(int i) {
		return this.cValues[i];
	}
	
	/**
	 * Overload 5 of 6.
	 * <br><br>
	 * Setter for the matrix.
	 * 
	 * @author Brian Blankenship
	 * @param values double[]
	 */
	public void values(double[] values) {
		this.cValues = values;
	}
	
	/**
	 * Overload 6 of 6.
	 * <br><br>
	 * Setter for a single value in the matrix.
	 * 
	 * @author Brian Blankenship
	 * @param value double
	 * @param i int
	 */
	public void values(double value, int i) {
		this.cValues[i] = value;
	}
	
	/**
	 * Transforms the matrix with multiplication.
	 * 
	 * @author Brian Blankenship
	 * @param other {@link Matrix3}
	 * @return A new {@link Matrix3}
	 */
	public Matrix3 multiply(Matrix3 other) {
		double[] result = new double[9];
		
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				for (int i = 0; i < 3; i++) {
					result[row * 3 + col] +=
							this.values(row, 3, i) * Matrix3.values(other, i, 3, col);
				}
			}
		}
		
		return new Matrix3(result);
	}
	
	/**
	 * Transforms the matrix.
	 * 
	 * @author Brian Blankenship
	 * @param in {@link Vertex}
	 * @return A new Vertex
	 * @see Vertex
	 * @see Triangle
	 */
	public Vertex transform(Vertex in) {
		return new Vertex(
			in.x() * this.values(0) + in.y() * this.values(3) + in.z() * this.values(6),
			in.x() * this.values(1) + in.y() * this.values(4) + in.z() * this.values(7),
			in.x() * this.values(2) + in.y() * this.values(5) + in.z() * this.values(8)
		);
	}
}
