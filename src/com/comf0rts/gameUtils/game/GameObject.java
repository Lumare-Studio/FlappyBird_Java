package com.comf0rts.gameUtils.game;

public class GameObject {
	private int x;
	private int iniX;
	private double xDiffCum;
	private int y;
	private int iniy;
	private double yDiffCum;
	private String skin; // Skin files(not implemented)
	private int height;
	private int width;
	private LocationProperties lp;
	
	public GameObject(int x, int y, int width, int height, LocationProperties lp) {
		this.x = x;
		this.iniX = x;
		this.y = y;
		this.iniy = y;
		this.height = height;
		this.width = width;
		this.lp  = lp;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void horizonMove(double diff) {
		this.xDiffCum += diff;
		if(Math.abs(xDiffCum)>=1) {
			this.x+= (int)xDiffCum;
			xDiffCum = 0.0;
			System.out.println("Obj loca updated");
		}
		checkUnload();
	}
	
	public void verticalMove(double diff) {
		this.yDiffCum += diff;
		if(Math.abs(yDiffCum) >= 1) {
			this.y+= (int)xDiffCum;
			yDiffCum = 0.0;
		}
		checkUnload();
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public LocationProperties getLocationProperties() {
		return this.lp;
	}
	
	public void checkUnload() {
		// debug
		if(x < 0) {
			this.x = iniX;
		}
	}
}
