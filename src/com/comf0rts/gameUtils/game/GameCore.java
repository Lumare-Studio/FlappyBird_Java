package com.comf0rts.gameUtils.game;
import javax.xml.stream.Location;
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
		MovementHandler mh = new MovementHandler(objList);
		Thread t2 = new Thread(mh);
		t2.start();
		
		drawBird();
		// populate objects in the game
		int sizeCount = (int) Math.ceil((double) WIDTH / 100) + 1; //How many pipes we need for running the game
		int distanceInterVal = 200; //Distance between pipes
		int sleepTime = 1000 * (int) Math.ceil((double) distanceInterVal / 100); //distanceInterVal / pipeSpeed (milisec)
		while(isRunning) {
			//Movement of bird and pipers might need to be added into different lists
			if(objList.size() <= 0 || objList.size() < sizeCount * 2) {
				drawPipes();
			}
			try {
				//Better to have another thread handling this
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//Draw bird based on developer's preferences
	private void drawBird() {
		int birdVerAcc = 50; //Gravity
		int width = 50;
		int height = 50;
		int xPos = (int) Math.ceil((double) WIDTH * 1/ 3) - width;
		int yPos = (int) Math.ceil((double) HEIGHT / 2) - height;
		LocationProperties birdLp = new LocationProperties(0, birdVerAcc, 0, 0);
		GameObject bird = new GameObject(xPos, yPos, width, height, birdLp);
		objList.add(bird);
	}
	
	private void drawPipes() {
		Random r = new Random();
		int minHeight = 30;
		int pipeSpeed = -1;
		int survivalSpace = 150;
		int pipeHeight = r.nextInt(HEIGHT - minHeight - survivalSpace) + minHeight ;
		LocationProperties tempLp = new LocationProperties(0, 0, pipeSpeed, 0);
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
