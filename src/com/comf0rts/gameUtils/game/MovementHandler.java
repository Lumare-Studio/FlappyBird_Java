package com.comf0rts.gameUtils.game;
import com.comf0rts.gameUtils.tools.PhysicsHandler;

import java.util.concurrent.CopyOnWriteArrayList;

// handles the movement of pipes and background
//(movement tracker)
public class MovementHandler implements Runnable{
	private CopyOnWriteArrayList<GameObject> obj;
	private boolean running = true;
	private final int updateSpeed = 10; // In miliseconds per cycle
	
	public MovementHandler(CopyOnWriteArrayList<GameObject> obj) {
		this.obj = obj;
	}
	
	
	@Override
	public void run() {
		while(running) {
			// update the location of all objects
			// needs debug
			for(GameObject o: this.obj) {
				if(o != null && !o.deleted) {
					LocationProperties lp = o.getLocationProperties();
					double timeLength = (double)updateSpeed / 1000;
					double horizontalDiff = PhysicsHandler.getDiff((double)lp.horizonAcc, (double)lp.horizonV, (double)timeLength);
					double verticalDiff = PhysicsHandler.getDiff((double)lp.verticalAcc, (double)lp.verticalV, (double)timeLength);
					o.horizonMove(horizontalDiff);
					o.verticalMove(verticalDiff);
					o.getLocationProperties().horizonV += o.getLocationProperties().horizonAcc;
					o.getLocationProperties().verticalV += o.getLocationProperties().verticalAcc;
				}else if(o != null) {
					if (!o.getType().equals("background")) {
						obj.remove(o);
					}
				}
			}
			try {
				Thread.sleep(updateSpeed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void stopThread() {
		this.running = false;
	}
	
}
