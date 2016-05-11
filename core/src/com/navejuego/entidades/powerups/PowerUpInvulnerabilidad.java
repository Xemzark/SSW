package com.navejuego.entidades.powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.navejuego.GestorAssets;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by Nex on 13/04/2016.
 */
public class PowerUpInvulnerabilidad extends PowerUpEntity {

    private int segundosInvulnerabilidad = 20;
    public PowerUpInvulnerabilidad (Texture texture, Vector2 posicion){
        super(texture, posicion);
    }

    public PowerUpInvulnerabilidad (Vector2 posicion){
        super(GestorAssets.getInstance().getTexture("addShield.png"), posicion);
    }

    @Override
    public void aplicarEfectosSobreJugador() {
        PantallaJuego.jugador.setInvulnerabilidad( segundosInvulnerabilidad);
    }
}
