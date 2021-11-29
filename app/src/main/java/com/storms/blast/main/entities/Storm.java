package com.storms.blast.main.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import static com.storms.blast.main.Constant.PLAYER_WIDTH;
import static com.storms.blast.main.Constant.WALL_X;
import static com.storms.blast.main.Constant.adaptiveVarY;

public class Storm {

    private Bitmap bitmap;
    private int frameWidth;
    private int frameHeight;

    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    private final double speed = adaptiveVarY(50);
    private double addedVelocity = 0;

    private double givenVelocityX;
    private double givenVelocityY;

    private final boolean isPlayer;

    public Storm(Bitmap bitmap, double positionX, double positionY, int height, int width, boolean isPlayer) {
        this.bitmap = bitmap;
        x = positionX;
        y = positionY;
        velocityX = 0;
        velocityY = 0;
        this.frameWidth = width;
        this.frameHeight = height;
        this.isPlayer = isPlayer;
    }

    public void update(int ms){
        if ((velocityX <= 200)&&(velocityX > -200)) {
            velocityX += addedVelocity;
        }
        else if (velocityX > 200){
            velocityX = 200;
        }
        else if(velocityX < -200){
            velocityX = -200;
        }
        if(isPlayer){
            x = x + velocityX * ms / 1000.0;
            y = y + velocityY * ms;
        }
        else{
            x = x + givenVelocityX * ms;
            y = y + givenVelocityY * ms;
        }
        if (x > WALL_X - (int)(PLAYER_WIDTH/2)){
            x = WALL_X - (int)(PLAYER_WIDTH/2);
        }
    }

    public void addVelocity(double addVelocity){
        if (addVelocity>0){
            addedVelocity = speed;
        } else if (addVelocity<0){
            addedVelocity = -speed;
        } else {
            addedVelocity = 0;
        }
    }

    public void stop(){
        addedVelocity = 0;
        while ((velocityX < 0)||(velocityX > 0)){
            velocityX = (int)(velocityX - velocityX/2);
        }

    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        Rect destination = new Rect((int)x, (int)y, (int)(x+frameWidth), (int)(y+frameHeight));
        canvas.drawBitmap(bitmap, null, destination, paint);
    }

    public Rect getBoundingBoxRect(){
        return new Rect((int)(x), (int)(y), (int)(x+frameWidth), (int)(y+frameHeight));
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }




    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getGivenVelocityX() {
        return givenVelocityX;
    }

    public void setGivenVelocityX(double givenVelocityX) {
        this.givenVelocityX = givenVelocityX;
    }

    public double getGivenVelocityY() {
        return givenVelocityY;
    }

    public void setGivenVelocityY(double givenVelocityY) {
        this.givenVelocityY = givenVelocityY;
    }

    public boolean isPlayer() {
        return isPlayer;
    }
}
