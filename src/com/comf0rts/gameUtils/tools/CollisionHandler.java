package com.comf0rts.gameUtils.tools;

import com.comf0rts.gameUtils.game.GameObject;

public class CollisionHandler {

    // Check whether two specified Squares are colliding to each other
    // Return true if colliding; otherwise, return false
    public static boolean checkRectCol(GameObject a, GameObject b) {
        //Check whether the X position of two GameObjects have potential conflicts
        if (a.getX() <= (b.getX() + b.getWidth()) && (a.getX() + a.getWidth()) >= b.getX()) {
            //Same type of checking for Y position
            return a.getY() <= (b.getY() + b.getHeight()) && (a.getY() + a.getHeight()) >= b.getY();
        }
        return false;
    }
}
