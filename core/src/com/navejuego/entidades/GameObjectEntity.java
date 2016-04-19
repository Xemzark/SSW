package com.navejuego.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.entidades.patrones.MovementPattern;

/**
 * Created by Elias on 09/04/2016.
 */
public abstract class GameObjectEntity extends Actor {
    protected Stage stage;
    protected Texture texture;

    protected Sprite sprite;

    protected float vida;
    protected float escudo;

    protected Rectangle hitbox;

    protected MovementPattern movementPattern;

    public abstract void destruirse();

    public float getVida() {
        return vida;
    }

    public float getEscudo() {
        return escudo;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Moves the game object to a new place.
     * @param x The new X
     * @param y the new Y
     */
    public void MoveTo(float x, float y) {
        setPosition(x, y);
        hitbox.setPosition(x, y);
    }

}
