package com.navejuego;

import com.badlogic.gdx.Gdx;
import com.navejuego.entidades.LevelManager;

/**
 * En esta clase se especfican las constantes y variables globales de la aplicación
 */
public class Constantes {
    //Constantes para libGDX

    /**
     * Altura logica de la pantalla
     */
    public static final float LOGICAL_HEIGHT = 640.0f;

    /**
     * Ancho logico de la pantalla
     */
    public static final float LOGICAL_WIDTH = 360.0f;

    /**
     * Tamaño de las barras laaterales de la UI de juego
     */
    public static final float LATERAL_BAR_WIDTH = 20.0f;

    /**
     * Desbloquear todos los niveles
     */
    public static final boolean UNLOCK_ALL_LEVELS = true;

    /**
     * Desbloquear todas las naves
     */
    public static final boolean UN_LOCK_ALL_SHIPS = true;

    /**
     * Si esta a true fuerza a crear una nueva partida, aunque haya una creada, sobreescribiendola
     */
    public static final boolean FORCE_FIRST_TIME_GAME_DATA = false;

    /**
     * Si esta a true se genera un ranking de prueba para todos los niveles
     */
    public static final boolean FILL_TEST_RANKING = false;

    /**
     * Variable que almacena el resize de LOGICAL_WIDTH
     */
    public static float resizeWidth;

    /**
     * Variable que almacena el resize de LOGICAL_HEIGHT
     */
    public static float resizeHeight;

    /**
     * Variable que almacena el nivel seleccionado
     */
    public static LevelManager.Nivel selectedLevel;

    /**
     * Último score conseguido por el jugador
     */
    public static int lastScore = 0;

    /**
     * Última nave seleccionada por el jugador en el garaje
     */
    public static int naveUltimaSeleccion = PartidaGuardada.getInstance().getNaveSeleccionada();

    /**
     * Calcula el resize de las constantes logicas de anchura y altura en base al tamaño de la
     * pantalla del jugador
     */
    public static void CalculateScreenConstants() {
        resizeWidth = Gdx.graphics.getWidth() / LOGICAL_WIDTH;
        resizeHeight = Gdx.graphics.getHeight() / LOGICAL_HEIGHT;
        System.out.print("W: " + resizeWidth + ", H: " + resizeHeight);
    }

    /**
     * Devuelve un Integro del nivel seleccionado
     * @return nivel
     */
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
