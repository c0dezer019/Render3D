package com.codeinspace.render3d.lib.shapes;

/**
 * Single point in space.
 * @author Brian Blankenship
 * @see Triangle
 * @see com.codeinspace.render3d.lib.transform.Matrix3
 */
public class Vertex {
	private double x;
	private double y;
	private double z;
	
	/**
	 * Sets a vertex at a given point in space.
	 * 
	 * @param x double
	 * @param y double
	 * @param z double
	 */
	public Vertex(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Returns the x coordinate.
	 * 
	 * @author Brian Blankenship
	 * @return x coordinate.
	 */
	public double x() {
		return x;
	}
	
	/**
	 * Sets the x coordinate.
	 * 
	 * @author Brian Blankenship
	 * @param x coordinate
	 */
	public void x(double x) {
		this.x = x;
	}
	
	/**
	 * Returns the y coordinate.
	 * 
	 * @author Brian Blankenship
	 * @return y coordinate.
	 */
	public double y() {
		return y;
	}
	
	/**
	 * Sets the y coordinate.
	 * 
	 * @author Brian Blankenship
	 * @param y coordinate
	 */
	public void y(double y) {
		this.y = y;
	}
	
	/**
	 * Returns the z coordinate.
	 * 
	 * @author Brian Blankenship
	 * @return z coordinate.
	 */
	public double z() {
		return z;
	}
	
	/**
	 * Sets the z coordinate.
	 * 
	 * @author Brian Blankenship
	 * @param z coordinate
	 */
	public void z(double z) {
		this.z = z;
	}
}
