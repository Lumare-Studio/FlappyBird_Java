package com.comf0rts.gameUtils.game;

public class GameObject {
	private int x;
	private int iniX;
	private double xDiffCum;
	private int y;
	private int iniy;
	private double yDiffCum;
	private String skin = null; // Skin files(not implemented)
	private String type;
	private int height;
	private int width;
	private LocationProperties lp;
	public boolean deleted = false; // Marks if the object should be deleted
	
	/**
	 * @param x initial x coordinate
	 * @param y initial y coordinate
	 * @param width width of the object
	 * @param height height of the object
	 * @param lp an object that described veritical and horizontal speed and acceleration of the object
	 */
	public GameObject(int x, int y, int width, int height, LocationProperties lp, String type) {
		this.x = x;
		this.iniX = x;
		this.y = y;
		this.iniy = y;
		this.height = height;
		this.width = width;
		this.lp  = lp;
		this.type = type;
	}
	
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param lp
	 * @param skin location of the skin file of this object
	 */
	public GameObject(int x, int y, int width, int height, LocationProperties lp,String type, String skin) {
		this(x, y, width, height, lp, type);
		this.skin = skin;
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
			this.x+= (int)(xDiffCum - xDiffCum % 1);
			xDiffCum = xDiffCum % 1;
		}
		checkUnload();
	}
	
	public void verticalMove(double diff) {
		this.yDiffCum += diff;
		if(Math.abs(yDiffCum) >= 1) {
			this.y+= (int)(yDiffCum - yDiffCum % 1);
			yDiffCum = yDiffCum % 1;
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
		if(x < 0 - width) {
			this.deleted = true;
		}
	}
	
	public boolean hasSkin() {
		return !(this.skin == null);
	}
	
	
	/**
	 * @return return the location of the assests image file
	 */
	public String getSkin() {
		return this.skin;
	}

	public String getType() {return this.type;}
}
