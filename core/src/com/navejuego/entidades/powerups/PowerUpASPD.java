package com.navejuego.entidades.powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by Nex on 13/04/2016.
 */
public class PowerUpASPD extends PowerUpEntity {

    private int segundosDobleASPD = 5;
    public PowerUpASPD (Texture texture, Vector2 posicion){
        super(texture, posicion);
    }

    @Override
    public void aplicarEfectosSobreJugador() {

        PantallaJuego.jugador.setDobleASPD(segundosDobleASPD);
    }

}
