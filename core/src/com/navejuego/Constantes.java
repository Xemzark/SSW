package com.navejuego;

import com.badlogic.gdx.Gdx;
import com.navejuego.entidades.LevelManager;

/**
 * Created by Andrés on 03/04/2016.
 */
public class Constantes {
    //Constantes para libGDX

    public static final float LOGICAL_HEIGHT = 640.0f;
    public static final float LOGICAL_WIDTH = 360.0f;
    public static final float LATERAL_BAR_WIDTH = 20.0f;

    public static float resizeWidth;
    public static float resizeHeight;

    public static LevelManager.Nivel selectedLevel;
    public static int lastScore = 0;
    public static int naveUltimaSeleccion = PartidaGuardada.getInstance().getNaveSeleccionada();

    //public static int opcion;

    public static final boolean UNLOCK_ALL_LEVELS = false;
    public static final boolean UN_LOCK_ALL_SHIPS = false;
    public static final boolean FORCE_FIRST_TIME_GAME_DATA = true;
    public static final boolean FILL_TEST_RANKING = false;

    public static void CalculateScreenConstants() {
        resizeWidth = Gdx.graphics.getWidth() / LOGICAL_WIDTH;
        resizeHeight = Gdx.graphics.getHeight() / LOGICAL_HEIGHT;
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
