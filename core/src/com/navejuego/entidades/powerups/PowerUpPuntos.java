package com.navejuego.entidades.powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by Nex on 13/04/2016.
 */
public class PowerUpPuntos extends PowerUpEntity {

    private int puntos;

    /**
     * Constructor del powerup, donde ademas le pasamos los puntos
     * cuyo valor dependerá del enemigo que los generará
     * @param texture
     * @param posicion
     * @param puntos
     */
    public PowerUpPuntos(Texture texture, Vector2 posicion, int puntos){
        super(texture, posicion);
        this.puntos = puntos;
    }

    @Override
    public void aplicarEfectosSobreJugador() {
        PantallaJuego.jugador.addPuntos(this.puntos);
    }
}
