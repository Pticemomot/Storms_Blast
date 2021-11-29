package com.storms.blast.main.control;

import android.graphics.Bitmap;

public class ButtonFire extends AbstractButton {

    public boolean isFiring = false;
    public int timer = 0;

    public ButtonFire(Bitmap bitmap, int positionX, int positionY) {
        super(bitmap, positionX, positionY);
    }
    public void update(){
        if (isFiring){
            timer += 1;
        }
        if (timer > 60){
            timer = 60;
        }
        if (!isFiring){
            timer = 0;
        }
    }
}
