package com.navejuego.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by Nex on 13/04/2016.
 */
public class PowerUpASPD extends PowerUpEntity {

    private int segundosDobleASPD = 20;
    public PowerUpASPD (Stage stage, Texture texture, Vector2 posicion){
        super(stage, texture, posicion);
    }

    @Override
    public void aplicarEfectosSobreJugador() {

        PantallaJuego.jugador.setDobleASPD(segundosDobleASPD);
    }

}
