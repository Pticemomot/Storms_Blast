package com.storms.blast.main.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import static com.storms.blast.main.Constant.WALL_HEIGHT;
import static com.storms.blast.main.Constant.WATER_HEIGHT;
import static com.storms.blast.main.Constant.WATER_WIDTH;
import static com.storms.blast.main.Constant.adaptiveVarY;

public class Water {

    private Bitmap bitmap;

    Matrix position;

    private double x;
    private double y;

    private final double velocityY = -adaptiveVarY(0.1);

    public Water(Bitmap bitmap, int x, int y) {
        this.bitmap = bitmap;
        this.x = x - (int)(WATER_WIDTH);
        this.y = y - (int)(WALL_HEIGHT);
        position = new Matrix();
    }

    public void update(){
        Matrix m = new Matrix();
        y = y + velocityY;
        m.postTranslate((float)(x), (float)(y));
        position.set(m);
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, position, paint);
    }//рисует

    public Rect getBoundingBoxRect(){
        return new Rect((int)(x), (int)(y), (int)(WATER_WIDTH), (int)(WATER_HEIGHT));
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
}
