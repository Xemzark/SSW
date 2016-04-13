package com.navejuego.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Elias on 09/04/2016.
 */
public class BulletEnemigo extends BulletEntity {
    private JugadorEntity jugador;

    public BulletEnemigo(Stage stage, Texture texture, Vector2 posicion, JugadorEntity jugador) {
        super(stage, texture, posicion);
        this.jugador = jugador;
    }

    /**
     * TODO: Comprobar colision con objeto jugador.
     */
    @Override
    protected void comprobarColision() {

    }

    /**
     * Actualizar su posición y la de su hitbox
     * @param delta
     */
    @Override
    protected void movimiento(float delta) {
        // leer * delta como *segundo!!!!!!!!!!!
        setPosition(getX(), getY() - (velocidad * delta));
    }
}
