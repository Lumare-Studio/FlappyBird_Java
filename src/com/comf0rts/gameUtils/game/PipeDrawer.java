package com.comf0rts.gameUtils.game;

import java.util.List;
import java.util.Random;

public class PipeDrawer implements Runnable {
    private int height;
    private int width;
    private List<GameObject> objList;

    public PipeDrawer(int height, int width, List<GameObject> objList) {
        this.height = height;
        this.width = width;
        this.objList = objList;
    }

    @Override
    public void run() {
        // populate objects in the game
        boolean isRunning = true;
        int sizeCount = (int) Math.ceil((double) width / 100) + 1; //How many pipes we need for running the game
        int distanceInterVal = 200; //Distance between pipes
        int sleepTime = 1000 * (int) Math.ceil((double) distanceInterVal / 100); //distanceInterVal / pipeSpeed (milisec)
        while(isRunning) {
            //Movement of bird and pipers might need to be added into different lists
            if(objList.size() <= 0 || objList.size() < sizeCount * 2) {
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
        int minHeight = 30;
        int pipeSpeed = -300;
        int survivalSpace = 150;
        int pipeHeight = r.nextInt(height - minHeight - survivalSpace) + minHeight ;
        LocationProperties tempLp = new LocationProperties(0, 0, pipeSpeed, 0);
        GameObject upperPipe = new GameObject(width, 0, 100, pipeHeight, tempLp);
        GameObject lowerPipe = new GameObject(width, pipeHeight + survivalSpace, 100, height - pipeHeight - survivalSpace, tempLp);
        objList.add(upperPipe);
        objList.add(lowerPipe);
    }

}
