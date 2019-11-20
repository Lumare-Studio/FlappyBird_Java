package com.comf0rts.gameUtils.game;
import javax.xml.stream.Location;

import com.comf0rts.gameUtils.tools.WindowHandler;

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
	private final String TITLE = "Crappy Bird"; // Window title
	private final int FPS = 60; // How many frames per seconds
	private Graphics g;
	private Canvas canvas;
	private BufferStrategy bs;
	private boolean isRunning = true;
	private KeyHandler key;
	private WindowHandler window;
	private Renderer r;
	
	private GameObject bird;
	private PipeDrawer piDrawer;
	private CopyOnWriteArrayList<GameObject> objList;
	private MovementHandler mvH;
	
	private Thread rendT;
	private Thread piDrawT;
	private Thread  mvT;

	
	
	public void run() {		
		reset();
		boolean gameOver = false;
		while(isRunning) {
			// game over condition
			if(Math.abs(bird.getY()) >= HEIGHT) {
				gameOver = true;
				
			}
			
			// TODO: add collision detection
			if(false) {
				gameOver = true;
			}
			
			//gameOver
			if (gameOver) {
				piDrawer.stopThread();
				r.stopThread();
				mvH.stopThread();
				reset();
				gameOver = false;
			}
			
			// wait the copyOnWriteList to update
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// reset
	public void reset() {
		
		// init GameObject array
		this.objList = new CopyOnWriteArrayList<>();
		
		// Init canvas, Bufferstrategy, and Pen(Graphics g)
		this.bs = canvas.getBufferStrategy();
		if(bs == null) {
			canvas.createBufferStrategy(1);
			return;
		}
		g = bs.getDrawGraphics();
		
		// start rendering
		this.r = new Renderer(objList, FPS, canvas);
		this.rendT = new Thread(r);
		rendT.start();
				
		// init movement tracking
		this.mvH = new MovementHandler(objList);
		this.mvT = new Thread(mvH);
		mvT.start();

		// init drawPipe	
		this.piDrawer = new PipeDrawer(HEIGHT, WIDTH, objList);
		this.piDrawT = new Thread(piDrawer);
		piDrawT.start();
		
		this.bird = drawBird();
		// add key Handler
		key = new KeyHandler(bird);
		window.getFrame().addKeyListener(key);
	}
	
	
	//Draw bird based on developer's preferences
	private GameObject drawBird() {
		int width = 34;
		int height = 24;
		int xPos = (int) Math.ceil((double) WIDTH * 1/ 3) - width;
		int yPos = (int) Math.ceil((double) HEIGHT / 2) - height;
		String skin = "assets/bird.png";
		LocationProperties birdLp = new LocationProperties(0, 0, 0, 0);
		GameObject bird = new GameObject(xPos, yPos, width, height, birdLp, skin);
		objList.add(bird);
		return bird;
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
	
	public void setWindow(WindowHandler window) {
		this.window = window;
	}
}
