package com.comf0rts.gameUtils.tools;

import java.awt.Canvas;
import java.awt.Dimension;

public class CanvasHandler {
	private Canvas canvas;
	private int width;
	private int height;
	
	public CanvasHandler(int width, int height) {
		this.width = width;
		this.height = height;
		canvas = new Canvas();
		canvas.setSize(new Dimension(this.width, this.height));
	}
	
	public Canvas getCanvas() {
		return this.canvas;
	}
}
