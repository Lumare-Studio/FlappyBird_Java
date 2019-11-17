package com.comf0rts.gameUtils.game;
import com.comf0rts.gameUtils.tools.PhysicsHandler;
// handles the movement of pipes and background
//(movement tracker)
public class movementHandler implements Runnable{
	private int trackSpeed;
	private gameObject obj[];
	private boolean running = true;
	
	public movementHandler(gameObject[] obj, int trackSpeed) {
		this.obj = obj;
		this.trackSpeed = trackSpeed; // check how many times per second
	}
	
	
	@Override
	public void run() {
		while(running) {
			// update the location of all objects
			// needs debug
			for(gameObject o: this.obj) {
				if(o != null) {
					locationProperties lp = o.getLocationProperties();
					int timeLength = 1000 / trackSpeed;
					if(timeLength <= 0) {
						timeLength = 1;
					}
					PhysicsHandler p = new PhysicsHandler((double)lp.horizonAcc, (double)lp.horizonV, (double)timeLength);
					o.horizonMove(p.getDiff());
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
