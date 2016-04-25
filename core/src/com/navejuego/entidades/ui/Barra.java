package com.navejuego.entidades.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
    private Texture icon;
    private float margenDeIcono = 22;
    private boolean horizontal;

    public Barra(Texture background, Texture foreground, Texture icon, float margenIcono, Vector2 posicion, boolean horizontal) {

        this.background = new Sprite(background);
        this.foreground = new Sprite(foreground);
        this.icon = icon;
        this.margenDeIcono = margenIcono;
        this.horizontal = horizontal;

        this.background.setPosition(posicion.x, posicion.y);
        this.foreground.setPosition(posicion.x, posicion.y);
        this.setPosition(posicion.x, posicion.y);

       /* if (horizontal) {
            this.background.setOrigin(getX(), getY()+getHeight()/2);
            this.foreground.setOrigin(getX(), getY()+getHeight()/2);
        } else {
            this.background.setOrigin(getX()+background.getWidth()/2, getY());
            this.foreground.setOrigin(getX()+foreground.getWidth()/2, getY());
        }*/
    }

    public void Update(float percent) {
        //TODO: Llamar desde el jugador
        if (horizontal) {
            this.foreground.setScale(percent, 1f);
        } else {
            this.foreground.setScale(1f, percent);
        }
    }

    public void render(Batch batch){
        this.background.draw(batch);
        this.foreground.draw(batch);
        batch.draw(this.icon, margenDeIcono/2, this.background.getY() - this.margenDeIcono / 4);
    }
}



