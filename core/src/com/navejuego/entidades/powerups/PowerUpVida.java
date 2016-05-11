package com.navejuego.entidades.powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by Nex on 13/04/2016.
 */
public class PowerUpVida extends PowerUpEntity {

    private int vida = 20;
    public PowerUpVida (Texture textura, Vector2 posicion){
        super(textura, posicion);
    }

    @Override
    public void aplicarEfectosSobreJugador() {
        PantallaJuego.jugador.curarse(vida);
    }
}
