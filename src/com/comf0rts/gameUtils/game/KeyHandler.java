package com.comf0rts.gameUtils.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	

	private GameObject bird;
	private final int KEYCODE = 32; // keycode of spacebar
	private boolean started = false;
	
	public KeyHandler(GameObject bird) {
		this.bird = bird;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KEYCODE) {
			bird.getLocationProperties().verticalV = -400;
			bird.getLocationProperties().verticalAcc = 15;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
