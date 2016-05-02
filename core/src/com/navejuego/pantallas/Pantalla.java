package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.navejuego.Main;

/**
 * Created by Andrés on 30/03/2016.
 */

// Screens son pantallas. Cada screen es una pantalla
    //no existe conexión entre pantallas y juegos. deben conectarse

//SE TRATA DE UNA CLASE ABSTRACTA PARA QUE TODAS LAS PANTALLAS HEREDEN DE ESTA/
//DE ESTE MODO, TODAS HEREDARAN EL JUEGO PRINCIPAL SIN NECESIDAD DE REESCRIBIR
//TODOS LOS MÉTODOS NI PASARLE EL JUEGO PRINCIPAL

/**
 * Clase Abstracta Pantalla
 * Clase generada para que todas las clases que extiendan a esta puedan ya tener instanciada la
 * variable game, que contiene la instancia de la clase principal del juego
 */
public abstract class Pantalla implements Screen{


    //protected Main game; // Declaración de variable para instanciar la clase principal del juego


    


    public Pantalla(){

    }

    //Método que se invoca para mostrar una pantalla
    @Override
    public void show() {

    }

    // Parámetro delta. MUY IMPORTANTE, sirve para llamar a render cada delta time (1/30f = 30 fps)
    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    //Método para dejar de usar una pantalla
    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
