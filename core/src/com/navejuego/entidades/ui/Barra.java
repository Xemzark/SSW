package com.navejuego.entidades.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.navejuego.Constantes;
import com.navejuego.GestorAssets;
import com.navejuego.pantallas.PantallaJuego;

//TODO: Crear clase "Barra"

/**
 * Created by beno_ on 11/04/2016.
 */
//tuto -->  https://www.youtube.com/watch?v=zrsUFTplNa4
public class Barra extends Actor {

    private Sprite background;
    private Sprite foreground;
    private Sprite icon;
    private float margenDeIcono = 22;
    private boolean horizontal;
    private float length;

    public Barra(Texture background, Texture foreground, Texture icon, float margenIcono, Vector2 posicion, boolean horizontal, float length) {

        this.background = new Sprite(background);
        this.foreground = new Sprite(foreground);

        //TODO: Handle rotation of the sprite

        if (icon != null) {
            this.icon = new Sprite(icon);
        }

        if (horizontal) {
            this.margenDeIcono = margenIcono * Constantes.resizeWidth;
        } else {
            this.margenDeIcono = margenIcono * Constantes.resizeHeight;
        }

        this.horizontal = horizontal;
        this.length = length;

        Vector2 adjustedPosition = new Vector2(posicion.x * Constantes.resizeWidth, posicion.y * Constantes.resizeHeight);

        this.setPosition(adjustedPosition.x, adjustedPosition.y);

        if (horizontal) {
            this.background.setOrigin(this.background.getX(), this.background.getY() + this.background.getHeight()/2);
            this.foreground.setOrigin(this.foreground.getX(), this.foreground.getY() + this.foreground.getHeight() / 2);
        } else {
            this.background.setOrigin(this.background.getX() + this.background.getWidth() / 2, this.background.getY());
            this.foreground.setOrigin(this.foreground.getX() + this.foreground.getWidth() / 2, this.foreground.getY());
        }

        this.background.setPosition(adjustedPosition.x, adjustedPosition.y);
        if (horizontal) {
            this.background.setSize(length * Constantes.resizeWidth,
                    background.getHeight() * Constantes.resizeHeight);
        } else {
            this.background.setSize(Constantes.lateralBarWidth * Constantes.resizeWidth,
                    length * Constantes.resizeHeight);
        }

        this.foreground.setPosition(adjustedPosition.x, adjustedPosition.y);
        if (horizontal) {
            this.foreground.setSize(length * Constantes.resizeWidth,
                    foreground.getHeight() * Constantes.resizeHeight);
        } else {
            this.foreground.setSize(Constantes.lateralBarWidth * Constantes.resizeWidth,
                    length * Constantes.resizeHeight);
        }

        if (icon != null) {
            if (horizontal) {
                this.icon.setPosition(this.background.getX() - this.margenDeIcono, this.background.getY());
            } else {
                this.icon.setPosition(this.background.getX(), this.background.getY() - this.margenDeIcono);
            }
            this.icon.setSize(Constantes.lateralBarWidth * Constantes.resizeWidth, Constantes.lateralBarWidth * Constantes.resizeHeight);
        }
    }

    public void Update(float percent) {
        if (horizontal) {
            this.foreground.setScale(percent, 1f);
        } else {
            this.foreground.setScale(1f, percent);
        }
    }

    public void render(Batch batch){
        this.background.draw(batch);
        this.foreground.draw(batch);
        if (icon != null)
            this.icon.draw(batch);
    }
}



