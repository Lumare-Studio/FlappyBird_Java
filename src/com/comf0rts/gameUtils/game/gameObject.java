package com.comf0rts.gameUtils.game;

import java.awt.Dimension;

public class gameObject {
	private int x;
	private int y;
	private String skin; // Skin files(not implemented)
	private int height;
	private int width;
	private locationProperties lp;
	
	public gameObject(int x, int y, int width, int height, locationProperties lp) {
		this.x = x;
		this.y = y;
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
	
	public void horizonMove(int diff) {
		this.x += diff;
	}
	
	public void verticalMove(int diff) {
		this.y += diff;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public locationProperties getLocationProperties() {
		return this.lp;
	}
}
