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
	private CopyOnWriteArrayList<GameObject> objList;
	private KeyHandler key;
	private WindowHandler window;
	
	
	public void run() {
		// Init canvas, Bufferstrategy, and Pen(Graphics g)
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
		Thread rendT = new Thread(r);
		rendT.start();
		
		// init movement tracking
		MovementHandler mvT = new MovementHandler(objList);
		Thread t2 = new Thread(mvT);
		t2.start();

		PipeDrawer piDrawer = new PipeDrawer(HEIGHT, WIDTH, objList);
		Thread piDrawT = new Thread(piDrawer);
		piDrawT.start();

		
		
		GameObject bird = drawBird();
		key = new KeyHandler(bird);
		window.getFrame().addKeyListener(key);
		boolean gameOver = false;
		while(isRunning) {
			if (gameOver) {
				removeLists();
				gameOver = false;
			}


		}
	}

	//GameOver method
	private void removeLists() {
		while(!objList.isEmpty()) {
			objList.remove(0);
		}
	}
	
	//Draw bird based on developer's preferences
	private GameObject drawBird() {
		int birdVerAcc = 15; //Gravity
		int width = 34;
		int height = 24;
		int xPos = (int) Math.ceil((double) WIDTH * 1/ 3) - width;
		int yPos = (int) Math.ceil((double) HEIGHT / 2) - height;
		String skin = "assets/bird.png";
		LocationProperties birdLp = new LocationProperties(0, birdVerAcc, 0, 0);
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
