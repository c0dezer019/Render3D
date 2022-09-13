package com.codeinspace.render3d;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.codeinspace.render3d.lib.shapes.*;
import com.codeinspace.render3d.lib.transform.*;

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

				double heading = Math.toRadians(headingSlider.getValue());
				Matrix3 headingTransform = new Matrix3(new double[] {
						Math.cos(heading), 0, Math.sin(heading),
						0, 1, 0,
						-Math.sin(heading), 0, Math.cos(heading)
				});
				double pitch = Math.toRadians(pitchSlider.getValue());
				Matrix3 pitchTransform = new Matrix3(new double[] {
						1, 0, 0,
						0, Math.cos(pitch), Math.sin(pitch),
						0, -Math.sin(pitch), Math.cos(pitch)
				});
				Matrix3 transform = headingTransform.multiply(pitchTransform);

				g2.translate(getWidth() / 2, getHeight() / 2);
				g2.setColor(Color.WHITE);

				BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

				for(Triangle tri : tris) {
					Vertex v1 = transform.transform(tri.v1());
					Vertex v2 = transform.transform(tri.v2());
					Vertex v3 = transform.transform(tri.v3());
					v1.x(v1.x() + (double) getWidth() / 2);
					v1.y(v1.y() + (double) getHeight() /2 );
					v2.x(v2.x() + (double) getWidth() / 2);
					v2.y(v2.y() + (double) getHeight() / 2);
					v3.x(v3.x() + (double) getWidth() / 2);
					v3.y(v3.y() + (double) getHeight() / 2);

					int minX = (int) Math.max(0, Math.ceil(Math.min(v1.x(), Math.min(v2.x(), v3.x()))));
					int maxX = (int) Math.min(
									(double) img.getWidth() - 1,
									Math.floor(Math.max(v1.x(),
									Math.max(v2.x(), v3.x()))));

					int minY = (int) Math.max(0, Math.ceil(Math.min(v1.y(), Math.min(v2.y(), v3.y()))));
					int maxY = (int) Math.min(
									(double) img.getHeight() - 1,
									Math.floor(Math.max(v1.y(),
									Math.max(v2.y(), v3.y()))));

					double triArea = (v1.y() - v3.y()) * (v2.x() - v3.x()) + (v2.y() - v3.y()) * (v3.x() - v1.x());

					for (int y = minY; y <= maxY; y++) {
						for (int x = minX; x <= maxX; x++) {
							double b1 = ((y - v3.y()) * (v2.x() - v3.x()) + (v2.y() - v3.y()) * (v3.x() - x)) / triArea;
							double b2 = ((y - v1.y()) * (v3.x() - v1.x()) + (v3.y() - v1.y()) * (v1.x() - x)) / triArea;
							double b3 = ((y - v2.y()) * (v1.x() - v2.x()) + (v1.y() - v2.y()) * (v2.x() - x)) / triArea;
							if (b1 >= 0 && b1 <= 1 && b2 >= 0 && b2 <= 1 && b3 >= 0 && b3 <= 1) {
								img.setRGB(x, y, tri.color().getRGB());
							}
						}
					}
				}
				g2.drawImage(img, -200, -200, null);
			}
		};
		pane.add(renderPanel, BorderLayout.CENTER);
		headingSlider.addChangeListener(e -> renderPanel.repaint());
		pitchSlider.addChangeListener(e -> renderPanel.repaint());

		frame.setSize(400, 400);
		frame.setVisible(true);
	}

}
