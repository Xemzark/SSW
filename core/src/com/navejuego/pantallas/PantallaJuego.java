package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.navejuego.Main;
import com.navejuego.entidades.EnemigoEntity;
import com.navejuego.entidades.JugadorEntity;
import com.navejuego.scene2dObsoleto.SingletonAssetManager;

import java.util.Random;

/**
 * Created by Andrés on 03/04/2016.
 */

/**
 * Clase PantallaJuego
 * Pantalla principal de juego.
 * Contiene un stage (asociado a actores) y gestiona la actuación del stage y todos sus actores
 * en tiempo delta. El Stage es una jerarquía de objetos que sirve para distribuir eventos entre
 * los actores que contenga.
 */
public class PantallaJuego extends Pantalla {

    private Stage stage; // Variable Stage, Scene2D

    // Variables de Actores
    private JugadorEntity jugador;

    float b = 0; //TEST NRO ACTORES A LA VEZ

    private float acumulableTiempo; // Variable para generar los enemigos
    boolean enemigoSi; // Variable para generar los enemigos
    float entreEnemigoAcumulable; // Variable para generar los enemigos

    /**
     * Constructor
     *
     * @param game
     */
    public PantallaJuego(Main game){
        super(game);

        // Se inicializa el stage con un ViewPort para adaptar la pantalla a los márgenes del dispositivo
        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())); //fitviewport adapta la pantalla. tamaño y lo otro lo pone a negro

        this.acumulableTiempo = 0.0f;
        this.enemigoSi = true;
    }

    @Override
    public void show() {
        //Aquí habría que crear también el background, y los enemigos no aparecerían desde un principio aqui
        //sino que lo que debe aparecer es la interfaz!
        //pero el background deberá actualizarse en el render para ir bajando!

        /**
         * Se cargan las texturas e inicializan los actores correspondientes.
         * Los Actores reciben la correspondiente textura y el vector de inicialización
         */
        Texture naveTextura = SingletonAssetManager.getManager().get("nave.png");
        jugador = new JugadorEntity(stage, naveTextura, new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2));

        /*
         * Se de la la propiedad al stage de procesar inputs. El stage estará escuchando eventos de
         * input que sucedan sobre la pantalla actual
         */
        Gdx.input.setInputProcessor(stage);
        stage.setDebugAll(true);

        /*
         * Añadir los actores al stage para que el stage pueda procesar eventos de input hacia
         * los actores pertinentes. En principio, de momento sólo debe haber un actor, el jugador
         */
        stage.addActor(jugador);
    }

    @Override
    public void hide() {
        dispose();
    }

    /**
     * Método render
     * Inicializa la pantalla dándole color.
     * stage.act() hace que todos los actores del stage se actualicen ejecutando su propio método
     * act() en tiempo delta.
     * stage.draw() hace que todos los actores del stage se dibujen ejecutando su propio método draw
     * @param delta
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Generar enemigos antes de actualizar
        generarEnemigos();

        stage.act(delta);

        //Cuantos actores hay
        int i = 0;
        for(Actor b : stage.getActors()){
            i++;
        }

        float a = 0.5f;
        b += delta;
        if (b > a){
            System.out.print(i + "\n");
            b = 0;
        }

        //System.out.print(Gdx.graphics.getDeltaTime() + "\n"); deltatime

        // Siempre dibujar después de actualizar
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    /**
     * Función que genera los enemigos.
     * TODO: Rehacer
     */
    private void generarEnemigos(){
        //Coger el tiempo
        acumulableTiempo += Gdx.graphics.getDeltaTime();

        //Variable para el tiempo entre enemigos
        Random entreEnemigo = new Random();

        /**
         * Ola entre 6 y 3 segundos
         * Si se ha generado el enemigo, se empieza a contar el tiempo y no se puede generar otro.
         */
        if(enemigoSi){
            entreEnemigoAcumulable = entreEnemigo.nextInt(6-3 + 1) + 3;
            enemigoSi = false; // Ya no se puede recalcular el tiempo hasta el siguiente enemigo
        }

        // Si se ha superado el tiempo marcado para el enemigo y ya se había calculado dicho tiempo...
        if ((acumulableTiempo > entreEnemigoAcumulable) && (!enemigoSi)){

            //Se genera un nuevo enemigo, sea cual sea
            stage.addActor(new EnemigoEntity(stage));

            acumulableTiempo=0.0f; // Se reinicia el tiempo
            enemigoSi = true; // Se puede recalcular el tiempo para el siguiente enemigo
        }
    }

    /**
     * TODO: Muestra en pantalla la barra de vida y escudo según la vida de la Nave del jugador.
     */
    private void mostrarVidaEscudo() {

    }

    /**
     * TODO: Muestra en pantalla la UI de la puntuación.
     */
    private void mostrarPuntiacion() {

    }

    /**
     * TODO: Si la partida estaba pausada, la despausa.
     * TODO: Si la partida estaba despausada, la pausa.
     */
    public void pausarPartida() {

    }

    /**
     * TODO: Muestra los botones asociados al menu de pausa.
     */
    private void mostrarMenuPausa() {

    }

    /**
     * TODO: Función que se llama cuando el jugador presiona el botón de ataque especial.
     */
    private void activarAtaqueEspecial() {

    }

    /**
     * TODO: Hacer aparecer el boss.
     */
    private void generaBoss() {

    }

    /**
     * TODO: Comprobar si se ha ganado el nivel.
     */
    private void comprobarVictoria() {

    }

    /**
     * TODO: Comprobar si se ha perdido el nivel.
     */
    private void comprobarDerrota() {

    }
}



