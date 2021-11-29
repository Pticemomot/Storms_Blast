package com.storms.blast.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.storms.blast.R;
import com.storms.blast.main.control.ButtonDown;
import com.storms.blast.main.control.ButtonFire;
import com.storms.blast.main.control.ButtonLeft;
import com.storms.blast.main.control.ButtonRight;
import com.storms.blast.main.control.ButtonUp;
import com.storms.blast.main.entities.Gun;
import com.storms.blast.main.entities.Rocket;
import com.storms.blast.main.entities.Storm;
import com.storms.blast.main.entities.Wall;
import com.storms.blast.main.entities.Water;

import java.util.ArrayList;

import static com.storms.blast.main.Constant.BULLET_SIDE_X;
import static com.storms.blast.main.Constant.BULLET_SIDE_Y;
import static com.storms.blast.main.Constant.BUTTON_DOWN_X;
import static com.storms.blast.main.Constant.BUTTON_DOWN_Y;
import static com.storms.blast.main.Constant.BUTTON_FIRE_X;
import static com.storms.blast.main.Constant.BUTTON_FIRE_Y;
import static com.storms.blast.main.Constant.BUTTON_LEFT_X;
import static com.storms.blast.main.Constant.BUTTON_LEFT_Y;
import static com.storms.blast.main.Constant.BUTTON_RIGHT_X;
import static com.storms.blast.main.Constant.BUTTON_RIGHT_Y;
import static com.storms.blast.main.Constant.BUTTON_UP_X;
import static com.storms.blast.main.Constant.BUTTON_UP_Y;
import static com.storms.blast.main.Constant.GUN_SIDE_X;
import static com.storms.blast.main.Constant.GUN_SIDE_Y;
import static com.storms.blast.main.Constant.PLAYER_X;
import static com.storms.blast.main.Constant.PLAYER_Y;
import static com.storms.blast.main.Constant.WALL_HEIGHT;
import static com.storms.blast.main.Constant.WALL_WIDTH;
import static com.storms.blast.main.Constant.WALL_X;
import static com.storms.blast.main.Constant.WALL_Y;
import static com.storms.blast.main.Constant.WATER_HEIGHT;
import static com.storms.blast.main.Constant.WATER_WIDTH;
import static com.storms.blast.main.MainActivity.screenHeight;
import static com.storms.blast.main.MainActivity.screenWidth;
import static com.storms.blast.main.Constant.PLAYER_HEIGHT;
import static com.storms.blast.main.Constant.PLAYER_WIDTH;

public class Game extends View {

    private Storm storm;
    private Wall wall;
    protected SurfaceHolder holder;
    private ButtonLeft buttonLeft;
    private ButtonRight buttonRight;
    private ButtonUp buttonUp;
    private ButtonDown buttonDown;
    private ButtonFire buttonFire;
    private Gun gun;
    private Rocket rocket;
    private ArrayList<Rocket> bullets = new ArrayList<Rocket>();
    private Bitmap rocketBitmap;
    private Bitmap destroyBitmap;
    private Water waterL;
    private Water waterR;

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
        Bitmap buttonFireBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.button_fire);
        rocketBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        destroyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bang);
        Bitmap waterBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wave1);

        destroyBitmap = Bitmap.createScaledBitmap(destroyBitmap, BULLET_SIDE_X, BULLET_SIDE_Y, true);
        rocketBitmap = Bitmap.createScaledBitmap(rocketBitmap, BULLET_SIDE_X, BULLET_SIDE_Y, true);
        gunBitmap = Bitmap.createScaledBitmap(gunBitmap, GUN_SIDE_X, GUN_SIDE_Y, true);
        waterBitmap = Bitmap.createScaledBitmap(waterBitmap, WATER_WIDTH/2, WATER_HEIGHT, true);

        storm = new Storm(stormBitmap, PLAYER_X, PLAYER_Y, PLAYER_HEIGHT, PLAYER_WIDTH, true);
        wall = new Wall(wallBitmap, WALL_X, WALL_Y, WALL_HEIGHT, WALL_WIDTH);
        buttonLeft = new ButtonLeft(buttonLeftBitmap, BUTTON_LEFT_X, BUTTON_LEFT_Y);
        buttonRight = new ButtonRight(buttonRightBitmap, BUTTON_RIGHT_X, BUTTON_RIGHT_Y);
        buttonUp = new ButtonUp(buttonDownBitmap, BUTTON_UP_X, BUTTON_UP_Y);
        buttonDown = new ButtonDown(buttonUpBitmap, BUTTON_DOWN_X, BUTTON_DOWN_Y);
        buttonFire = new ButtonFire(buttonFireBitmap, BUTTON_FIRE_X, BUTTON_FIRE_Y);
        gun = new Gun(gunBitmap);
        waterL = new Water(waterBitmap, screenWidth, (int) (screenHeight*1.75));
        waterR = new Water(waterBitmap, screenWidth + screenWidth/2, (int) (screenHeight*1.75));


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
                    gun.setRotate(5);
                    gun.setAngle(gun.getAngle() + 5);
                } else if (buttonDown.isTouched((int) (event.getX()), (int) (event.getY()))) {
                    gun.setRotate(-5);
                } else if (buttonFire.isTouched((int)(event.getX()), (int)(event.getY()))) {
                    buttonFire.isFiring = true;
                }
                return true;
            case MotionEvent.ACTION_UP: // палец поднят
                if (buttonFire.isFiring){
                    bullets.add(new Rocket(rocketBitmap, buttonFire.timer, gun.getAngle(), (int)(gun.getX() + (int)(GUN_SIDE_Y /2)), (int)(gun.getY()+ (int)(GUN_SIDE_Y /2))));
                    buttonFire.isFiring = false;
                }
                storm.stop();
                gun.setRotate(0);
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bgBitmap, null, destination, paint);
        if (bullets.size() != 0) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).draw(canvas);
                }
            }
        storm.draw(canvas);
        gun.draw(canvas);
        waterL.draw(canvas);
        waterR.draw(canvas);
        buttonFire.draw(canvas);
        buttonLeft.draw(canvas);
        buttonRight.draw(canvas);
        buttonUp.draw(canvas);
        buttonDown.draw(canvas);

        wall.draw(canvas);
    }

    public void update() {
        buttonFire.update();
        storm.update(timerInterval, (int)(waterL.getY() - WATER_HEIGHT/5));
        gun.update((int)storm.getX(), (int)storm.getY());
        if (bullets.size() != 0) {
            for (int i = 0; i < bullets.size(); i++) {

                if (bullets.get(i).getBoundingBoxRect().intersect(wall.getBoundingBoxRect())){
                    bullets.get(i).setSpeedX(-bullets.get(i).getSpeedX());
                }
                //отражение
                bullets.get(i).update();
            }
        }
        waterL.update();
        waterR.update();

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
