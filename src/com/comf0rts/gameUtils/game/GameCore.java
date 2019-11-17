package com.comf0rts.gameUtils.game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


public class GameCore implements Runnable{
	private final int WIDTH = 768; // width of the window
	private final int HEIGHT = 432; // height of the window
	private final String TITLE = "Canvas Test"; // Window title
	private final int FPS = 60; // How many frames per seconds
	private final int TRACKSPEED = 100; // How many frames per seconds
	private Graphics g;
	private Canvas canvas;
	private BufferStrategy bs;
	private boolean isRunning = true;
	private CopyOnWriteArrayList<GameObject> objList;
	
	
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
		objList = new CopyOnWriteArrayList<>();
		
		// start rendering
		Renderer r = new Renderer(objList, FPS, canvas);
		Thread t1 = new Thread(r);
		t1.start();
		
		// init movement tracking
		MovementHandler mh = new MovementHandler(objList, TRACKSPEED);
		Thread t2 = new Thread(mh);
		t2.start();
		
		
		// populate objects in the game
		int sizeCount = objList.size();
		while(isRunning) {
			if(sizeCount<= 0 || objList.size() < sizeCount) {
				drawPipes();
			}
			sizeCount = objList.size();
		}
	}
	
	
	private void drawBird() {
		
	}
	
	private void drawPipes() {
		Random r = new Random();
		int minHeight = 30;
		int pipeSpeed = -200;
		int survivalSpace = 100;
		int pipeHeight = r.nextInt(HEIGHT - minHeight - survivalSpace) + minHeight ;
		LocationProperties tempLp = new LocationProperties(0 , 0 , pipeSpeed , 0);
		GameObject upperPipe = new GameObject(WIDTH, 0, 100, pipeHeight, tempLp);
		GameObject lowerPipe = new GameObject(WIDTH, pipeHeight + survivalSpace, 100, HEIGHT - pipeHeight - survivalSpace, tempLp);
		objList.add(upperPipe);
		objList.add(lowerPipe);
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
