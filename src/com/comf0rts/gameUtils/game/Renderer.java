package com.comf0rts.gameUtils.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.List;

public class Renderer implements Runnable{
	private List<GameObject> objList;
	private boolean isRunning = true;
	private Canvas c;
	private BufferStrategy b;
	private Graphics g;
	private int FPS;
	
	
	public Renderer(List<GameObject> objList, int FPS, Canvas canvas) {
		this.objList = objList;
		this.c = canvas;
		this.FPS = FPS;

	}
	
	@Override
	public void run() {
		while(isRunning) {
			this.b = c.getBufferStrategy();
			this.g = b.getDrawGraphics();
			g.clearRect(0, 0, (int)c.getSize().getWidth(),(int)c.getSize().getHeight()); // clear canvas
			g.setColor(Color.red);
			for(GameObject o: objList) {
				if(o != null){
					int x = o.getX();
					int y = o.getY();
					g.fillRect(x, y, o.getWidth(), o.getHeight());
					System.out.println(x + " " + y + " " + o.getWidth() + " " +o.getHeight()); // debug
				}
			}
			b.show();
			g.dispose();
			try {
				Thread.sleep(1000/ FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
