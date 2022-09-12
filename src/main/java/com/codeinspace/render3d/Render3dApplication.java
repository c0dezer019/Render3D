package com.codeinspace.render3d;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import com.codeinspace.render3d.lib.shapes.*;

public class Render3dApplication {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		
		// horizontal rotation control.
		JSlider headingSlider = new JSlider(0, 360, 180);
		pane.add(headingSlider, BorderLayout.SOUTH);
		
		// vertical rotation control.
		JSlider pitchSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
		pane.add(pitchSlider, BorderLayout.EAST);
		
		// render results display.
		JPanel renderPanel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.BLACK);
				g2.fillRect(0,  0,  getWidth(), getHeight());
				
				ArrayList<Triangle> tris = new ArrayList<>();
				
				tris.add(new Triangle(new Vertex(100, 100, 100),
						 			  new Vertex(-100, -100, 100),
						 			  new Vertex(-100, 100, -100),
						 			  Color.WHITE));
				
				tris.add(new Triangle(new Vertex(100, 100, 100),
			 			  			  new Vertex(-100, -100, 100),
			 			  			  new Vertex(100, -100, -100),
			 			  			  Color.RED));
				
				tris.add(new Triangle(new Vertex(-100, 100, -100),
			 			  		  	  new Vertex(100, -100, -100),
			 			  		  	  new Vertex(100, 100, 100),
			 			  		  	  Color.GREEN));
				
				tris.add(new Triangle(new Vertex(-100, 100, -100),
			 			  			  new Vertex(100, -100, -100),
			 			  			  new Vertex(-100, -100, 100),
			 			  			  Color.BLUE));
				
				g2.translate(getWidth() / 2, getHeight() / 2);
				g2.setColor(Color.WHITE);

				for(Triangle tri : tris) {
					Path2D path = new Path2D.Double();
					path.moveTo(tri.v1().x(), tri.v1().y());
					path.lineTo(tri.v2().x(), tri.v2().y());
					path.lineTo(tri.v3().x(), tri.v3().y());
					path.closePath();
					g2.draw(path);
				}
			}
		};
		pane.add(renderPanel, BorderLayout.CENTER);
		
		frame.setSize(400, 400);
		frame.setVisible(true);
	}

}
