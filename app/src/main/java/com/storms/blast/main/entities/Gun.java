package com.storms.blast.main.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import static com.storms.blast.main.Constant.GUN_SIDE_X;
import static com.storms.blast.main.Constant.GUN_SIDE_Y;
import static com.storms.blast.main.Constant.PLAYER_X;
import static com.storms.blast.main.Constant.PLAYER_Y;

public class Gun {

    private Bitmap bitmap;
    private int frameWidth;
    private int frameHeight;

    Matrix position;

    private double x;
    private double y;
    private int angle = 0;
    private int rotate = 0;

    public Gun(Bitmap bitmap) {
        this.bitmap = bitmap;
        x = (int)(PLAYER_X/2 - GUN_SIDE_X /2);
        y = (int)(PLAYER_Y + GUN_SIDE_Y /2);
        this.frameWidth = GUN_SIDE_X;
        this.frameHeight = GUN_SIDE_Y;
        position = new Matrix();
    }

    public void update(int x, int y){
        Matrix m = new Matrix();
        angle += rotate;
        m.postRotate(angle, (int)(bitmap.getWidth()/2), (int)(bitmap.getHeight()/2));
        m.postTranslate((float)(x + GUN_SIDE_X /2), (float)(this.y));
        position.set(m);
        this.x = (int)(x + GUN_SIDE_X /2);
        this.y = (int)(y + GUN_SIDE_Y /2);
        if (angle > 90){
            angle = 90;
        }
        else if (angle < -90){
            angle = -90;
        }
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, position, paint);
    }//рисует

    public Rect getBoundingBoxRect(){
        return new Rect((int)(x), (int)(y), (int)(x+frameWidth), (int)(y+frameHeight));
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

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
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

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}