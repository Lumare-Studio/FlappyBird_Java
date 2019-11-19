package com.comf0rts.gameUtils.tools;

// Handle various physics calculation
// Collision, force calculation
public class PhysicsHandler {

	public static double getDiff(double acc, double velocity, double timeLength) {
		double result = 0;
		result = (1/2)*acc*(timeLength * timeLength) + velocity * timeLength;
		return result;
	}
}
