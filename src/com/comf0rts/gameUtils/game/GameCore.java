package com.comf0rts.gameUtils.game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.List;


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
	private List<GameObject> objList;
	
	public void run() {
		// Init canvas, Bufferstrategy, and Pen(Graphics g)
		canvas.setBackground(Color.gray);
		bs = canvas.getBufferStrategy();
		if(bs == null) {
			canvas.createBufferStrategy(1);
			return;
		}
		g = bs.getDrawGraphics();
		// init GameObject array
		objList = new LinkedList<>();
		// add a pipe
		drawPipes();
		
		// start rendering
		Renderer r = new Renderer(objList, FPS, canvas);
		Thread t1 = new Thread(r);
		t1.start();
		
		// init movement tracking
		MovementHandler mh = new MovementHandler(objList, TRACKSPEED);
		Thread t2 = new Thread(mh);
		t2.start();
	}
	
	
	private void drawBird() {
		
	}
	
	private void drawPipes() {
		LocationProperties tempLp = new LocationProperties(0 , 0 , -1000 , 0);
		GameObject newPipe = new GameObject(700, 0, 100, 100, tempLp);
		objList.add(newPipe);
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
