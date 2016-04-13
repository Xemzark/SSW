package com.navejuego.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by Nex on 13/04/2016.
 */
public class PowerUpVida extends PowerUpEntity {

    private float vida = 20.0f;
    public PowerUpVida (Stage stage, Texture textura, Vector2 posicion){
        super(stage, textura, posicion);
    }

    @Override
    public void aplicarEfectosSobreJugador() {
        if (PantallaJuego.jugador.vida + vida > 100){
            PantallaJuego.jugador.vida = 100;
        } else {
            PantallaJuego.jugador.vida += 20;
        }

        PantallaJuego.jugador.updateUI();
    }
}
