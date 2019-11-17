package com.comf0rts.gameUtils.game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


import com.comf0rts.gameUtils.tools.PhysicsHandler;


public class GameCore implements Runnable{
	private final int WIDTH = 768; // width of the window
	private final int HEIGHT = 432; // height of the window
	private final String TITLE = "Canvas Test"; // Window title
	private final int FPS = 60; // How many frames per seconds
	private final int TRACKSPEED = 500; // How many frames per seconds
	private Graphics g;
	private Canvas canvas;
	private BufferStrategy bs;
	private boolean isRunning = true;
	private gameObject[] objList;
	
	public void run() {
		// Init canvas, Bufferstrategy, and Pen(Graphics g)
		canvas.setBackground(Color.gray);
		bs = canvas.getBufferStrategy();
		if(bs == null) {
			canvas.createBufferStrategy(1);
			return;
		}
		g = bs.getDrawGraphics();
		// init gameObject array
		objList = new gameObject[5];
		// add a pipe
		drawPipes();
		
		// start rendering
		renderer r = new renderer(objList, FPS, canvas);
		Thread t1 = new Thread(r);
		t1.start();
		
		// init movement tracking
		movementHandler mh = new movementHandler(objList, TRACKSPEED );
		Thread t2 = new Thread(mh);
		t2.start();
	}
	
	
	private void drawBird() {
		
	}
	
	private void drawPipes() {
		locationProperties tempLp = new locationProperties(0 , 0 , -1000 , 0);
		gameObject newPipe = new gameObject(700, 0, 100, 100, tempLp);
		objList[0] = newPipe;
	}
	
	public Dimension getDimension() {
		return new Dimension(WIDTH, HEIGHT);
	}
	
	public String getTitle() {
		return TITLE;
	}
	
	public void setCanvasAndGraphics(Canvas canvas) {
		this.canvas = canvas;
	}
}
