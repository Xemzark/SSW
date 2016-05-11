package com.navejuego.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.navejuego.entidades.patrones.MovementPattern;

/**
 * Created by Elias on 09/04/2016.
 */
public abstract class GameObjectEntity extends Actor {
    protected Texture texture;

    protected Sprite sprite;

    protected float vida;
    protected float escudo;

    protected float maxVida;
    protected float maxEscudo;

    protected Circle hitbox;

    protected MovementPattern movementPattern;

    public abstract void destruirse();

    public float getVida() {
        return vida;
    }

    public float getEscudo() {
        return escudo;
    }

    public Circle getHitbox() {
        return hitbox;
    }

    /**
     * Moves the game object to a new place.
     * @param x The new X
     * @param y the new Y
     */
    public void MoveTo(float x, float y) {
        setPosition(x, y);
        hitbox.setPosition(x+getWidth()/2, y+getHeight()/2);
    }

}
