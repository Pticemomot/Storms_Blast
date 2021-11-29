package com.storms.blast.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import com.storms.blast.R;
import com.storms.blast.main.control.ButtonDown;
import com.storms.blast.main.control.ButtonLeft;
import com.storms.blast.main.control.ButtonRight;
import com.storms.blast.main.control.ButtonUp;
import com.storms.blast.main.entities.Gun;
import com.storms.blast.main.entities.Storm;
import com.storms.blast.main.entities.Wall;

import static com.storms.blast.main.Constant.BUTTON_DOWN_X;
import static com.storms.blast.main.Constant.BUTTON_DOWN_Y;
import static com.storms.blast.main.Constant.BUTTON_LEFT_X;
import static com.storms.blast.main.Constant.BUTTON_LEFT_Y;
import static com.storms.blast.main.Constant.BUTTON_RIGHT_X;
import static com.storms.blast.main.Constant.BUTTON_RIGHT_Y;
import static com.storms.blast.main.Constant.BUTTON_UP_X;
import static com.storms.blast.main.Constant.BUTTON_UP_Y;
import static com.storms.blast.main.Constant.PLAYER_X;
import static com.storms.blast.main.Constant.PLAYER_Y;
import static com.storms.blast.main.Constant.WALL_HEIGHT;
import static com.storms.blast.main.Constant.WALL_WIDTH;
import static com.storms.blast.main.Constant.WALL_X;
import static com.storms.blast.main.Constant.WALL_Y;
import static com.storms.blast.main.MainActivity.screenHeight;
import static com.storms.blast.main.MainActivity.screenWidth;
import static com.storms.blast.main.Constant.PLAYER_HEIGHT;
import static com.storms.blast.main.Constant.PLAYER_WIDTH;
import static com.storms.blast.main.Constant.adaptiveVar;

public class Game extends View {

    private Storm storm;
    private Wall wall;
    protected SurfaceHolder holder;
    private ButtonLeft buttonLeft;
    private ButtonRight buttonRight;
    private ButtonUp buttonUp;
    private ButtonDown buttonDown;
    private Gun gun;

    private final int timerInterval = 30;

    Bitmap bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
    Rect destination = new Rect(0, 0, screenWidth, screenHeight);
    Paint paint = new Paint();

    public Game(Context context) {
        super(context);

        Bitmap stormBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.storm);
        Bitmap buttonUpBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.button_up);
        Bitmap buttonDownBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.button_down);
        Bitmap buttonRightBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.button_right);
        Bitmap buttonLeftBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.button_left);
        Bitmap wallBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
        Bitmap gunBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gun);

        storm = new Storm(stormBitmap, PLAYER_X, PLAYER_Y, PLAYER_HEIGHT, PLAYER_WIDTH, true);
        wall = new Wall(wallBitmap, WALL_X, WALL_Y, WALL_HEIGHT, WALL_WIDTH);
        buttonLeft = new ButtonLeft(buttonLeftBitmap, BUTTON_LEFT_X, BUTTON_LEFT_Y);
        buttonRight = new ButtonRight(buttonRightBitmap, BUTTON_RIGHT_X, BUTTON_RIGHT_Y);
        buttonUp = new ButtonUp(buttonDownBitmap, BUTTON_UP_X, BUTTON_UP_Y);
        buttonDown = new ButtonDown(buttonUpBitmap, BUTTON_DOWN_X, BUTTON_DOWN_Y);
        gun = new Gun(gunBitmap);

        Timer t = new Timer();
        t.start();
    } //распределяет все битмапы и распологает обьекты на сцене

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: // если коснулся
                if (buttonLeft.isTouched((int) (event.getX()), (int) (event.getY()))) {
                    storm.addVelocity(-1);
                } else if (buttonRight.isTouched((int) (event.getX()), (int) (event.getY()))) {
                    storm.addVelocity(1);
                } else if (buttonUp.isTouched((int) (event.getX()), (int) (event.getY()))) {
                    gun.setAngle(gun.getAngle() + 5);
                } else if (buttonDown.isTouched((int) (event.getX()), (int) (event.getY()))) {
                    gun.setAngle(gun.getAngle() - 5);
                }
                return true;
            case MotionEvent.ACTION_UP: // палец поднят
                storm.addVelocity(0);
                return true;

        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bgBitmap, null, destination, paint);
        storm.draw(canvas);
        wall.draw(canvas);
        buttonLeft.draw(canvas);
        buttonRight.draw(canvas);
        buttonUp.draw(canvas);
        buttonDown.draw(canvas);
        gun.draw(canvas);
    }

    public void update() {

        storm.update(timerInterval);
        gun.update((int) storm.getX());

        invalidate();
    }
    class Timer extends CountDownTimer {

        public Timer() {
            super(Integer.MAX_VALUE, timerInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            update ();
        }

        @Override
        public void onFinish() {

        }
    }
}
