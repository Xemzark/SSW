package com.navejuego.pantallas;


import com.badlogic.gdx.Screen;


/**
 * Created by root on 4/04/16.
 */
public enum ScreenEnum {

    MAIN_MENU {
        public Pantalla getScreen(Object... params) {
            return new PantallaMenu();
        }
    },

    GAME {
        public Pantalla getScreen(Object... params) {
            return new PantallaJuego();
        }
    };

    public abstract Pantalla getScreen(Object... params);
}

