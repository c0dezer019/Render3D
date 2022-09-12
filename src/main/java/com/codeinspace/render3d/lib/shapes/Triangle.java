package com.codeinspace.render3d.lib.shapes;

import java.awt.Color;

/**
 * Creates a triangle.
 * 
 * @author Brian Blankenship
 * @see Vertex
 */
public class Triangle {
	private Vertex v1;
	private Vertex v2;
	private Vertex v3;
	private Color color;
	
	/**
	 * Creates a triangle with the 3 vertices.
	 * 
	 * @author Brian Blankenship
	 * @param v1 {@link Vertex}
	 * @param v2 {@link Vertex}
	 * @param v3 {@link Vertex}
	 * @param color {@link Color}
	 */
	public Triangle(Vertex v1, Vertex v2, Vertex v3, Color color) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.color = color;
	}
	
	/**
	 * Returns the 1st {@link Vertex}
	 * 
	 * @author Brian Blankenship
	 * @return {@link Vertex} 1.
	 */
	public Vertex v1() {
		return v1;
	}
	
	/**
	 * Sets the 1st {@link Vertex}
	 * 
	 * @author Brian Blankenship
	 * @param v1 {@link Vertex}
	 */
	public void v1(Vertex v1) {
		this.v1 = v1;
	}
	
	/**
	 * Returns the 2nd {@link Vertex}
	 * 
	 * @author Brian Blankenship
	 * @return Second {@link Vertex}
	 */
	public Vertex v2() {
		return v2;
	}
	
	/**
	 * Sets the 2nd {@link Vertex}
	 * 
	 * @author Brian Blankenship
	 * @param v2 {@link Vertex}
	 */
	public void v2(Vertex v2) {
		this.v2 = v2;
	}
	
	/**
	 * Returns the 3rd {@link Vertex}
	 * 
	 * @author Brian Blankenship
	 * @return Third {@link Vertex}
	 */
	public Vertex v3() {
		return v3;
	}
	
	/**
	 * Sets the 3rd {@link Vertex}.
	 * 
	 * @author Brian Blankenship
	 * @param v3 {@link Vertex}
	 */
	public void v3(Vertex v3) {
		this.v3 = v3;
	}
	
	/**
	 * Returns the color the {@link Triangle}
	 * 
	 * @author Brian Blankenship
	 * @return {@link Color} of the {@link Triangle}
	 */
	public Color color() {
		return color;
	}
	
	/**
	 * Sets the color of the {@link Triangle}
	 * 
	 * @author Brian Blankenship
	 * @param color Type of {@link Color}
	 */
	public void color(Color color) {
		this.color = color;
	}
}
