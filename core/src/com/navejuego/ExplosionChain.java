package com.navejuego;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Kevin on 29/04/2016.
 */
public class ExplosionChain extends Explosion {
    int times; //Veces que se repite la animacion
    int indiceTotal = 0;


    public ExplosionChain(ArrayList<Texture> texture, Vector2 posicion, float duracion, int times) {
        super(texture, posicion, duracion);
        this.times = times;

    }

    @Override
    public void act(float delta) {

        if (indiceTotal < times) {
            ttrans += delta;
            if (ttrans >= duracion) {
                GestorAssets.getInstance().getSound("explosion2.wav").stop();
                indiceTotal += 1 ;
                indiceactual = 0;
                ttrans = 0.0f;
                GestorAssets.getInstance().getSound("explosion2.wav").play();
            } else if (duracionframe * (indiceactual + 1) <= ttrans) {
                indiceactual += 1;
                texture = listatextura.get(indiceactual);
            }

        }
        else{
            destruirse();
        }
    }
}



