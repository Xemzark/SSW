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

    NIVELES {
        public Pantalla getScreen(Object... params) {
            return new MainNivelesScreen();
        }
    },

    GAME_OVER {
        public Pantalla getScreen(Object... params) {
            return new PantallaGameOver();
        }
    },

    VICTORY {
        public Pantalla getScreen(Object... params) {
            return new PantallaVictory();
        }
    },

    AJUSTES {
        public Pantalla getScreen(Object... params) {
            return new PantallaAjustes();
        }
    },

    GARAJE {
        public Pantalla getScreen(Object... params) {
            return new PantallaGaraje();
        }
    },

    LOADING {
        public Pantalla getScreen(Object... params) {
            return new PantallaGaraje();
        }
    },





    GAME {
        public Pantalla getScreen(Object... params) {
            return new PantallaJuego();
        }
    };

    public abstract Pantalla getScreen(Object... params);
}

