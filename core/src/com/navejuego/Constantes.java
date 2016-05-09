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

    public static final boolean unlockAllLevels = false;
    public static final boolean forceFirstTimeGameData = true;
    public static final boolean fillTestRanking = true;

    public static void CalculateScreenConstants() {
        resizeWidth = Gdx.graphics.getWidth() / logicalWidth;
        resizeHeight = Gdx.graphics.getHeight() / logicalHeight;
        System.out.print("W: " + resizeWidth + ", H: " + resizeHeight);
    }

    public static int getLevelInt(){
        int nivel = 0;
        switch(Constantes.selectedLevel){
            case NIVEL_1 :
                //Statements
                nivel = 0;
                break; //optional
            case NIVEL_2 :
                //Statements
                nivel = 1;
                break; //optional
            case NIVEL_3 :
                //Statements
                nivel = 2;
                break; //optional
            case NIVEL_4 :
                //Statements
                nivel = 3;
                break; //optional
            //You can have any number of case statements.
            default : //Optional
                //Statements
        }

        return nivel;
    }

}
