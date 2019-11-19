package com.comf0rts.gameUtils;

import com.comf0rts.gameUtils.tools.WindowHandler;
import com.comf0rts.gameUtils.game.GameCore;
// The game launcher
public class Launcher {
	public static void main(String[] args) {
		GameCore game = new GameCore(); // run the game
		WindowHandler window = new WindowHandler(game.getTitle(),game.getDimension());
		game.setWindow(window);
		game.setCanvasAndGraphics(window.getCanvas()); // give the game a canvas and pen(Graphics)
		Thread t = new Thread(game);
		t.start();
	}
}
