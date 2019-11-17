package com.comf0rts.gameUtils.game;
import com.comf0rts.gameUtils.tools.PhysicsHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// handles the movement of pipes and background
//(movement tracker)
public class MovementHandler implements Runnable{
	private int trackSpeed;
	private CopyOnWriteArrayList<GameObject> obj;
	private boolean running = true;
	
	public MovementHandler(CopyOnWriteArrayList<GameObject> obj, int trackSpeed) {
		this.obj = obj;
		this.trackSpeed = trackSpeed; // check how many times per second
	}
	
	
	@Override
	public void run() {
		while(running) {
			// update the location of all objects
			// needs debug
			for(GameObject o: this.obj) {
				if(o != null && !o.deleted) {
					LocationProperties lp = o.getLocationProperties();
					int timeLength = 1000 / trackSpeed;
					if(timeLength <= 0) {
						timeLength = 1;
					}
					PhysicsHandler p = new PhysicsHandler((double)lp.horizonAcc, (double)lp.horizonV, (double)timeLength);
					o.horizonMove(p.getDiff());
					o.getLocationProperties().horizonV += o.getLocationProperties().horizonAcc;
				}else if(o != null && o.deleted) {
					obj.remove(o);
				}
			}
			try {
				Thread.sleep(1000 / trackSpeed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void endTracking() {
		running = false;
	}
	
}
