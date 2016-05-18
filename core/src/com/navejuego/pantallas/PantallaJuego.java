package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.navejuego.Constantes;
import com.navejuego.PartidaGuardada;
import com.navejuego.entidades.JugadorEntity;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.LevelManager;
import com.navejuego.Preferencias;

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

    public static Stage stage; // Variable Stage, Scene2D
    private SpriteBatch batch;
    //private Texture background;
    //private Music music;
    private LevelManager levelManager;
    // Variables de Actores
    public static JugadorEntity jugador = null;

    private float acumulableTiempo; // Variable para generar los enemigos
    boolean enemigoSi; // Variable para generar los enemigos
    float entreEnemigoAcumulable; // Variable para generar los enemigos

    //private WaveManager waves;

    /**
     * Constructor
     *
     */
    public PantallaJuego(){

        if(Preferencias.getInstance().musicOn()){
            GestorAssets.getInstance().getMusic("Starlight.mp3").stop();
        }
        // Se inicializa el stage con un ViewPort para adaptar la pantalla a los márgenes del dispositivo
        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())); //fitviewport adapta la pantalla. tamaño y lo otro lo pone a negro
        this.acumulableTiempo = 0.0f;
        this.enemigoSi = true;
        this.levelManager = new LevelManager(Constantes.selectedLevel);


        //comprobamos si la musica esta habilitada
        if(Preferencias.getInstance().musicOn()){
            this.levelManager.getMusic().setLooping(true);
            this.levelManager.getMusic().play();
        }
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

        //para el background
        batch = new SpriteBatch();

        //background = GestorAssets.getInstance().getTexture("background_1.png");


        //Texture naveTextura = GestorAssets.getInstance().getTexture("nave.png");

        jugador = new JugadorEntity(new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2), PartidaGuardada.getInstance().getNaveSeleccionada());

        /*
         * Se de la la propiedad al stage de procesar inputs. El stage estará escuchando eventos de
         * input que sucedan sobre la pantalla actual
         */
        Gdx.input.setInputProcessor(stage);
        //stage.setDebugAll(true);

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

        batch.begin();

        //batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(this.levelManager.getBackGround(),0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        batch.end();

        // Generar enemigos antes de actualizar
        levelManager.spawn();

        stage.act(delta);

        //System.out.print(Gdx.graphics.getDeltaTime() + "\n"); deltatime

        // Siempre dibujar después de actualizar
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.clear();
        this.levelManager.getMusic().dispose();
        //stage.dispose();
    }
}



