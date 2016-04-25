package com.navejuego.entidades.powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.GestorAssets;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by Nex on 13/04/2016.
 */
public class PowerUpEscudo extends PowerUpEntity {

    private int escudo = 10;
    public PowerUpEscudo (Texture textura, Vector2 posicion){
        super(textura, posicion);
    }

    public PowerUpEscudo (Stage stage, Vector2 posicion){
        super(GestorAssets.getInstance().getTexture("addShield.png"), posicion);
    }

    @Override
    public void aplicarEfectosSobreJugador() {
        PantallaJuego.jugador.subirEscudo(escudo);
    }
}
