package com.navejuego;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.navejuego.pantallas.ScreenEnum;
import com.navejuego.pantallas.ScreenManager;

import java.util.ArrayList;

/**
 * Created by Kevin on 29/04/2016.
 */
public class AnimacionChain extends Animacion {
    private int times; //Veces que se repite la animacion
    private int indiceTotal = 0;
    private Sound sound;

    public AnimacionChain(ArrayList<Texture> texture, Vector2 posicion, float duracion, int times, float size, Sound sound) {
        super(texture, posicion, duracion, size);
        this.times = times;
        this.sound = sound;
    }

    @Override
    public void act(float delta) {

        if (indiceTotal < times) {
            ttrans += delta;
            if (ttrans >= duracion) {
                indiceTotal += 1 ;
                indiceactual = 0;
                ttrans = 0.0f;
                if(Preferencias.getInstance().soundOn()) {
                    this.sound.stop();
                    this.sound.play();
                }
            } else if (duracionframe * (indiceactual + 1) <= ttrans) {
                indiceactual += 1;
                texture = listatextura.get(indiceactual);
            }

        }
        else{
            //Desbloquear siguiente nivel
            destruirse();
        }
    }
}

