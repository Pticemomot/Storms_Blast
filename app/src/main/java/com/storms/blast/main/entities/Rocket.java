package com.storms.blast.main.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import static com.storms.blast.main.Constant.BULLET_SIDE_X;
import static com.storms.blast.main.Constant.BULLET_SIDE_Y;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class Rocket {

    private Bitmap bitmap;
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
        this.x = x - (int)(BULLET_SIDE_X);
        this.y = y - (int)(BULLET_SIDE_Y);
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
        m.postTranslate((float)(x + BULLET_SIDE_X /2), (float)(y + BULLET_SIDE_Y /2));
        position.set(m);
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, position, paint);
    }

    public Rect getBoundingBoxRect(){
        return new Rect((int)(x), (int)(y), (int)(x+ BULLET_SIDE_X *1.5), (int)(y+ BULLET_SIDE_Y *1.5));
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getG() {
        return g;
    }
}
