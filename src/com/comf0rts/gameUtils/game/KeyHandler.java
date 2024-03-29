package com.comf0rts.gameUtils.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	

	private GameObject bird;
	private final int KEYCODE = 32; // keycode of spacebar
	private boolean released = true;
	
	public KeyHandler(GameObject bird) {
		this.bird = bird;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KEYCODE && released) {
			bird.getLocationProperties().verticalV = -450;
			bird.getLocationProperties().verticalAcc = 15;
			released = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KEYCODE) {
			released = true;
		}
	}

}
