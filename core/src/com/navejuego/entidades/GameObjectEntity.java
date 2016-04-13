package com.navejuego.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

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

}
