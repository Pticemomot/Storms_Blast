package com.storms.blast.main.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import static com.storms.blast.main.Constant.BULLET_SIDE;
import static com.storms.blast.main.Constant.GUN_SIDE;
import static com.storms.blast.main.Constant.PLAYER_X;
import static com.storms.blast.main.Constant.PLAYER_Y;
import static com.storms.blast.main.Constant.adaptiveVar;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class Rocket {

    private Bitmap bitmap;
    private int frameWidth;
    private int frameHeight;
    private int power;
    private int speedX;
    private int speedY;
    private int angle = 0;
    private final int g = 1;

    Matrix position;

    private double x;
    private double y;

    public Rocket(Bitmap bitmap, int power, int angle, int x, int y) {
        this.bitmap = bitmap;
        this.x = x - (int)(BULLET_SIDE);
        this.y = y - (int)(BULLET_SIDE);
        position = new Matrix();
        this.power = power + 20;
        this.angle = angle - 90;
        speedX = (int) (cos(toRadians(this.angle))*this.power);
        speedY = (int) (sin(toRadians(this.angle))*this.power);
    }

    public void update(){
        Matrix m = new Matrix();
        speedY += g;
        x = x + speedX;
        y = y + speedY;
        m.postTranslate((float)(x + BULLET_SIDE/2), (float)(y + BULLET_SIDE/2));
        position.set(m);
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, position, paint);
    }//рисует

    public Rect getBoundingBoxRect(){
        return new Rect((int)(x), (int)(y), (int)(x+BULLET_SIDE*1.5), (int)(y+BULLET_SIDE*1.5));
    }

    public void destroy(boolean intersect, Bitmap bitmap, Canvas canvas){
        if (intersect) {
            Paint paint = new Paint();
            canvas.drawBitmap(bitmap, position, paint);
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Matrix getPosition() {
        return position;
    }

    public void setPosition(Matrix position) {
        this.position = position;
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
}
