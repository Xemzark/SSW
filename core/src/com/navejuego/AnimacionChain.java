package com.navejuego;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 * Clase que ejecuta cualquier animación derivada de la clase Animación
 */
public class AnimacionChain extends Animacion {
    private int times; //Veces que se repite la animacion
    private int indiceTotal = 0;
    private Sound sound;

    /**
     * Constructor
     * @param texture Array de texturas que componen la animación
     * @param posicion Posicion donde debe ejecutarse la animación
     * @param duracion Duranción de la animación
     * @param size Tamaño de los sprites de la animación
     */
    public AnimacionChain(ArrayList<Texture> texture, Vector2 posicion, float duracion, int times, float size) {
        super(texture, posicion, duracion, size);
        this.times = times;
    }

    /**
     * Selecciona la siguiente textura de la animación que será dibujada. I si se ha
     * terminado la animacion esta se destruye
     * @param delta
     */
    @Override
    public void act(float delta) {

        if (indiceTotal < times) {
            ttrans += delta;
            if (ttrans >= duracion) {
                indiceTotal += 1 ;
                indiceactual = 0;
                ttrans = 0.0f;
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

