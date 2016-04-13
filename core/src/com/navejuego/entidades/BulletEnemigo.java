package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by Elias on 09/04/2016.
 */
public class BulletEnemigo extends BulletEntity {

    public BulletEnemigo(Stage stage, Texture texture, Vector2 posicion) {
        super(stage, texture, posicion);
    }

    /**
     * TODO: Comprobar colision con objeto jugador.
     */
    @Override
    protected void comprobarColision() {
            //TODO: Comprobar colisión aquí y aplicar efectos de choque si corresponde.
        if (PantallaJuego.jugador.getHitbox().overlaps(this.getHitbox())) {
            PantallaJuego.jugador.recibirDmg(damage, ignoraEscudo);
            Gdx.app.log("Hit a jugador!", "");
            this.destruirse();
        }
    }

    /**
     * Actualizar su posición y la de su hitbox
     * @param delta
     */
    @Override
    protected void movimiento(float delta) {
        // leer * delta como *segundo!!!!!!!!!!!
        setPosition(getX(), getY() - (velocidad * delta));
        hitbox.setPosition(getX(), getY());
    }
}
