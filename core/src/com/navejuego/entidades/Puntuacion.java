package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by beno_ on 13/04/2016.
 */

//fuentes propias https://github.com/libgdx/libgdx/wiki/Gdx-freetype

public class Puntuacion extends Actor {
    private BitmapFont font;
    private String puntuacion = "xxxxxxxx";
    private float buffer = 10;

    public Puntuacion(String puntuacion){

        this.font=new BitmapFont();
        this.font.setColor(Color.WHITE);
        this.font.getData().setScale((float)1.7, (float)1.7);
        this.puntuacion = puntuacion;
    }

    public void update(){


    }

    public void setPutuacion(String puntuacion){
        this.puntuacion = puntuacion;

    }
    public String getPuntuacion(){
        return this.puntuacion;
    }
    public void draw (Batch batch)
    {
        font.draw(batch, "HIGH SCORE", (Gdx.graphics.getWidth()/2)-this.buffer*7 ,Gdx.graphics.getHeight()- this.buffer);
        font.draw(batch, this.puntuacion, (Gdx.graphics.getWidth()/2)- this.buffer*7, Gdx.graphics.getHeight()-buffer*4);

    }
}
