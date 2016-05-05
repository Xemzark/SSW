package com.navejuego;

import com.badlogic.gdx.Gdx;
import com.navejuego.entidades.LevelManager;

/**
 * Created by Andrés on 03/04/2016.
 */
public class Constantes {
    //Constantes para libGDX

    private static final float logicalHeight = 640.0f;
    private static final float logicalWidth = 360.0f;

    public static final float lateralBarWidth = 20.0f;
    public static final float PIXELS_METRE = 110.0f;

    public static float resizeWidth;
    public static float resizeHeight;

    public static LevelManager.Nivel selectedLevel;
    public static int lastScore = 0;

    public static void CalculateScreenConstants() {
        resizeWidth = Gdx.graphics.getWidth() / logicalWidth;
        resizeHeight = Gdx.graphics.getHeight() / logicalHeight;
        System.out.print("W: " + resizeWidth + ", H: " + resizeHeight);
    }

}
