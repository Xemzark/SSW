package com.navejuego;


import com.badlogic.gdx.Game;
import com.navejuego.pantallas.PantallaJuego;
import com.navejuego.GestorAssets;
import com.navejuego.pantallas.ScreenManager;
import com.navejuego.pantallas.*;

/**
 * Clase Main
 * Clase principal del juego.
 */
public class Main extends Game {
    /**
     * Método crear
     */
    @Override
    public void create() {



        ScreenManager.getInstance().initialize(this); //Inicializo el AssetManager
        GestorAssets.getInstance().create();

        ScreenManager.getInstance().showScreen(ScreenEnum.GAME_OVER);





    }

}