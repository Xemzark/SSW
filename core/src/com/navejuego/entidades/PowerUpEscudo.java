package com.navejuego.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by Nex on 13/04/2016.
 */
public class PowerUpEscudo extends PowerUpEntity {

    private float escudo = 10.0f;
    public PowerUpEscudo (Stage stage, Texture textura, Vector2 posicion){
        super(stage, textura, posicion);
    }

    @Override
    public void aplicarEfectosSobreJugador() {
        if (PantallaJuego.jugador.getEscudo() + escudo > 100){
            PantallaJuego.jugador.escudo = 100;
        } else {
            PantallaJuego.jugador.escudo += escudo;
        }

        PantallaJuego.jugador.updateUI();
    }
}
