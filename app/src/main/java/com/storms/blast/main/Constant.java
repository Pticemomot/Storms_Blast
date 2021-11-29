package com.storms.blast.main;

import static com.storms.blast.main.MainActivity.screenHeight;
import static com.storms.blast.main.MainActivity.screenWidth;

public class Constant {
    public static final int PLAYER_HEIGHT = (int) adaptiveVarY(384);
    public static final int PLAYER_WIDTH = (int) adaptiveVarX(384);
    public static final int BUTTON_SIDE_Y = (int) adaptiveVarY(220);
    public static final int BUTTON_SIDE_X = (int) adaptiveVarX(220);
    public static final int PLAYER_Y = (int) adaptiveVarY(800);
    public static final int PLAYER_X = (int) adaptiveVarX(256);
    public static final int GUN_SIDE_Y = (int) adaptiveVarY(192);
    public static final int GUN_SIDE_X = (int) adaptiveVarX(192);
    public static final int WALL_X = (int)(screenWidth/2 - (int) adaptiveVarX(128));
    public static final int WALL_Y = (int)(adaptiveVarY(512));
    public static final int WALL_HEIGHT = screenHeight;
    public static final int WALL_WIDTH = (int) adaptiveVarX(128);
    public static final int BUTTON_LEFT_X = (int) adaptiveVarX(1900);
    public static final int BUTTON_LEFT_Y = (int)(adaptiveVarY(1164));
    public static final int BUTTON_RIGHT_X = (int) adaptiveVarX(2452);
    public static final int BUTTON_RIGHT_Y = (int)(adaptiveVarY(1164));
    public static final int BUTTON_UP_X = (int) adaptiveVarX(2176);
    public static final int BUTTON_UP_Y = (int)(adaptiveVarY(1164));
    public static final int BUTTON_DOWN_X = (int) adaptiveVarX(2176);
    public static final int BUTTON_DOWN_Y = (int)(adaptiveVarY(888));
    public static final int BUTTON_FIRE_X = (int)(adaptiveVarX(258));
    public static final int BUTTON_FIRE_Y = (int)(adaptiveVarY(1164));
    public static final int BULLET_SIDE = (int) adaptiveVarY(64);
    //все эти числа - пиксели для экрана с разрешением 1440 на 2701, но преобразованные для др. экранов

    public static double adaptiveVarY(double var) {
        return screenHeight/(1440/var);
    }//функция, которая преобразует все числа в отношение базового экрана и текущего
    public static double adaptiveVarX(double var) {
        return screenWidth/(2701/var);
    }
}
