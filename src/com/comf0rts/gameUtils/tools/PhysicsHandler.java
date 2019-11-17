package com.comf0rts.gameUtils.tools;

// Handle various physics calculation
// Collision, force calculation
public class PhysicsHandler {
	private double acc;
	private double velocity;
	private double timeLength;
	
	public PhysicsHandler(double acc, double velocity, double timeLength) {
		this.acc = acc;
		this.velocity = velocity;
		this.timeLength = timeLength / 1000;
	}
	
	public double getDiff() {
		double result = 0;
		result = (1/2)*acc*(timeLength * timeLength) + velocity * timeLength;
		return result;
	}
}
