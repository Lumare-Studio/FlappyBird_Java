package com.comf0rts.gameUtils.tools;

// Handle various physics calculation
// Collision, force calculation
public class PhysicsHandler {
	private int acc;
	private int velocity;
	private int timeLength;
	
	public PhysicsHandler(int acc, int velocity, int timeLength) {
		this.acc = acc;
		this.velocity = velocity;
		this.timeLength = timeLength;
	}
	
	public int getDiff() {
		double result = 0;
		result = (1/2)*acc*(timeLength ^ 2) + velocity * timeLength;
		System.out.println("getDiff: " + result + "int: " + (int) result);
		System.out.println("acc: " + acc + " velocity: " + velocity + " timelength:" + timeLength);
		return (int)result;
	}
}
