package com.comf0rts.gameUtils.game;

// A class to track horizon and vertical velocity and acceleration
public class locationProperties {
	public int horizonAcc;
	public int verticalAcc;
	public int horizonV;
	public int verticalV;
	
	public locationProperties(int horizonAcc, int verticalAcc, int horizonV, int verticalV) {
		this.horizonAcc = horizonAcc;
		this.verticalAcc = verticalAcc;
		this.horizonV = horizonV;
		this.verticalV = verticalV;
	}
	
}
