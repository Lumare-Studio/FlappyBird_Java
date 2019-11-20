package com.comf0rts.gameUtils.game;

import java.util.List;
import java.util.Random;

public class PipeDrawer implements Runnable {
    private int height;
    private int width;
    private List<GameObject> objList;
    private final int PIPE_SPEED = -300;
    private final int distanceInterVal = 400; //Distance between pipes
    private final int minHeight = 15;
    private boolean isRunning = true;

    public PipeDrawer(int height, int width, List<GameObject> objList) {
        this.height = height;
        this.width = width;
        this.objList = objList;
    }

    @Override
    public void run() {
        // populate objects in the game
        int sizeCount = (int) Math.ceil((double) width / 100) + 1; //How many pipes we need for running the game
        int sleepTime = (int) Math.ceil((1000 * (double) distanceInterVal / (double) (-PIPE_SPEED))); //distanceInterVal / pipeSpeed (milisec)
        while(isRunning) {
            //Movement of bird and pipers might need to be added into different lists
            if(objList.size() == 0 || objList.size() < sizeCount * 2) {
                drawPipes();
            }
            try {
                //Better to have another thread handling this
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void drawPipes() {
        Random r = new Random();
        int pipeSpeed = PIPE_SPEED;
        int survivalSpace = 150;
        int pipeHeight = r.nextInt(height - minHeight - survivalSpace) + minHeight ;
        LocationProperties tempLp = new LocationProperties(0, 0, pipeSpeed, 0);
        String upperSkin = "assets/pipeUp.png";
        String lowerSkin = "assets/pipeDown.png";
        GameObject upperPipe = new GameObject(width, 0, 100, pipeHeight, tempLp, upperSkin);
        GameObject lowerPipe = new GameObject(width, pipeHeight + survivalSpace, 100, height - pipeHeight - survivalSpace, tempLp, lowerSkin);
        objList.add(upperPipe);
        objList.add(lowerPipe);
    }
    
    public void stopThread() {
		this.isRunning = false;
	}

}
