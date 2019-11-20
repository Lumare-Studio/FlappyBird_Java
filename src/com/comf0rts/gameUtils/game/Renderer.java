package com.comf0rts.gameUtils.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

public class Renderer implements Runnable{
	private CopyOnWriteArrayList<GameObject> objList;
	private boolean isRunning = true;
	private Canvas c;
	private BufferStrategy b;
	private Graphics g;
	private int FPS;
	
	
	public Renderer(CopyOnWriteArrayList<GameObject> objList, int FPS, Canvas canvas) {
		this.objList = objList;
		this.c = canvas;
		this.FPS = FPS;

	}
	
	@Override
	public void run() {
		this.b = c.getBufferStrategy();
		this.g = b.getDrawGraphics();
		while(isRunning) {
			g.clearRect(0, 0, (int)c.getSize().getWidth(),(int)c.getSize().getHeight()); // clear canvas
			g.setColor(Color.red);
			for(GameObject o: objList) {
				if(o != null){
					int x = o.getX();
					int y = o.getY();
					if(o.hasSkin()) {
						String skin = o.getSkin();
						try {
							Image skinImg = ImageIO.read(new File(skin));
							g.drawImage(skinImg,x,y,o.getWidth(),o.getHeight(), null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							g.fillRect(x, y, o.getWidth(), o.getHeight());
							e.printStackTrace();
						}
						
					}else {
						g.fillRect(x, y, o.getWidth(), o.getHeight());
					}
				}
			}
			b.show();
			try {
				Thread.sleep(1000/ FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
