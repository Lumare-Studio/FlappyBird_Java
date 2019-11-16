// Create a window
// Provide a window, a canvas, and Graphics

package com.comf0rts.gameUtils.tools;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

public class WindowHandler {
	private JFrame frame;
	private Canvas canvas;
	private Graphics g;
	private String title;
	private int width;
	private int height;
	
	
	public WindowHandler(String title, Dimension d) {
		this.width = d.width;
		this.height = d.height;
		this.title = title;
		initWindow();
	}
	
	// Initiate the window, set width,height, and visibility. And add a canvas inside;
	private void initWindow() {
		frame = new JFrame(title);
		frame.setSize(new Dimension(width, height));
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		this.canvas = new CanvasHandler(width, height).getCanvas();
		frame.add(this.canvas);
		frame.pack();
		canvas.createBufferStrategy(2);
		this.g = canvas.getBufferStrategy().getDrawGraphics();		
	}
	
	// Return a pointer that points to the JFrame of this window
	public JFrame getFrame() {
		return this.frame;
	}
	
	public Canvas getCanvas() {
		return this.canvas;
	}
	
	public Graphics getGraphics() {
		return this.g;
	}
	
	public void setSize(Dimension d) {
		this.frame.setSize(d);
	}
	
}
