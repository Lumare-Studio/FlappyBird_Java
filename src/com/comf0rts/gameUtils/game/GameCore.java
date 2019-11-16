package com.comf0rts.gameUtils.game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


import com.comf0rts.gameUtils.tools.PhysicsHandler;


public class GameCore implements Runnable{
	private final int WIDTH = 432; // width of the window
	private final int HEIGHT = 768; // height of the window
	private final String TITLE = "Canvas Test"; // Window title
	private final int FPS = 60; // How many frames per seconds
	private Graphics g;
	private Canvas canvas;
	private BufferStrategy bs;
	
	public void run() {
		canvas.setBackground(Color.gray);
		bs = canvas.getBufferStrategy();
		if(bs == null) {
			canvas.createBufferStrategy(1);
			return;
		}
	    
		// debug
		int x = 0;
	    int y = 0;
		while(1==1) {
			g = bs.getDrawGraphics();
			//draw the bird
			g.clearRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.red);
			g.fillRect(x, y, 20, 20);
			// end drawing
			bs.show();
			g.dispose();
			if(x >= WIDTH) {
				x = 0;
				y+=20;
				if( y >= HEIGHT) {
					y = 0;
				}
			}else {
				x+=10;
			}
			try {
				Thread.sleep(1000 / FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
