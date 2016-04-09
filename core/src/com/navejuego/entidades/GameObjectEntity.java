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

    protected int vida;
    protected int escudo;

    protected Rectangle hitbox;

    public abstract void destruirse();

    public int getVida() {
        return vida;
    }

    public int getEscudo() {
        return escudo;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

}
