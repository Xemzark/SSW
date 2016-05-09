package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.navejuego.Constantes;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.JugadorType;
import com.navejuego.entidades.ui.Barra;

/**
 * Created by albertmoreno on 14/4/16.
 */
public class PantallaGaraje extends Pantalla {


    private Stage garajeStage;
    private Barra barra;

    private Skin skinGaraje;
    private SpriteBatch batchGaraje;

    private Texture plataforma;
    private Image barraAtaque, barraEscudo, barraCadencia, barraVida, nave1;

    private int opcion;

    private Skin skin;
    private Texture backgroundGaraje;
    private JugadorType jugador;


    public PantallaGaraje() {


        garajeStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(garajeStage);
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));



        opcion = 0;


        batchGaraje = new SpriteBatch();
        backgroundGaraje = GestorAssets.getInstance().getTexture("background_garaje.png");



        switch (opcion) {

            case 0:
                jugador = new JugadorType(opcion);
                barraAtaque = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraAtaque.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 50);
                barraAtaque.setSize(Gdx.graphics.getWidth() / 2 * jugador.damage/10, Gdx.graphics.getHeight() / 16);

                barraEscudo = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraEscudo.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 18);
                barraEscudo.setSize(Gdx.graphics.getWidth() / 2* jugador.escudo/10, Gdx.graphics.getHeight() / 16);

                barraVida = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraVida.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 11);
                barraVida.setSize(Gdx.graphics.getWidth() / 3 * jugador.vida/10, Gdx.graphics.getHeight() / 16);

                barraCadencia = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraCadencia.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 8);
                barraCadencia.setSize(Gdx.graphics.getWidth() / 2 * jugador.cadenciaDisparo/10, Gdx.graphics.getHeight() / 16);

                nave1 = new Image(GestorAssets.getInstance().getTexture("nave.png"));
                nave1.setPosition(Gdx.graphics.getWidth() / 2 - 2 * 80 * Constantes.resizeWidth / 2, Gdx.graphics.getHeight() / 3 + Gdx.graphics.getHeight() / 8);
                nave1.setSize(2 * 80 * Constantes.resizeWidth, 2 * 80 * Constantes.resizeHeight);
                break;
            case 1:
                jugador = new JugadorType(opcion);
                barraAtaque = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraAtaque.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 50);
                barraAtaque.setSize(Gdx.graphics.getWidth() / 2 * jugador.damage, Gdx.graphics.getHeight() / 16);

                barraEscudo = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraEscudo.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 18);
                barraEscudo.setSize(Gdx.graphics.getWidth() / 2 * jugador.escudo, Gdx.graphics.getHeight() / 16);

                barraVida = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraVida.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 11);
                barraVida.setSize(Gdx.graphics.getWidth() / 2 * jugador.vida, Gdx.graphics.getHeight() / 16);

                barraCadencia = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraCadencia.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 8);
                barraCadencia.setSize(Gdx.graphics.getWidth() / 2 * jugador.cadenciaDisparo, Gdx.graphics.getHeight() / 16);

                nave1 = new Image(GestorAssets.getInstance().getTexture("destroyer.png"));
                nave1.setPosition(Gdx.graphics.getWidth() / 2 - 2 * 80 * Constantes.resizeWidth / 2, Gdx.graphics.getHeight() / 3 + Gdx.graphics.getHeight() / 8);
                nave1.setSize(2 * 80 * Constantes.resizeWidth, 2 * 80 * Constantes.resizeHeight);
                break;
            case 2:
                jugador = new JugadorType(opcion);

                barraAtaque = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraAtaque.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 50);
                barraAtaque.setSize(Gdx.graphics.getWidth() / 2 * jugador.damage, Gdx.graphics.getHeight() / 16);

                barraEscudo = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraEscudo.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 18);
                barraEscudo.setSize(Gdx.graphics.getWidth() / 2 * jugador.escudo, Gdx.graphics.getHeight() / 16);

                barraVida = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraVida.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 11);
                barraVida.setSize(Gdx.graphics.getWidth() / 2 * jugador.vida, Gdx.graphics.getHeight() / 16);

                barraCadencia = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraCadencia.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 8);
                barraCadencia.setSize(Gdx.graphics.getWidth() / 2 * jugador.cadenciaDisparo, Gdx.graphics.getHeight() / 16);

                nave1 = new Image(GestorAssets.getInstance().getTexture("ship3.png"));
                nave1.setPosition(Gdx.graphics.getWidth() / 2 - 2 * 80 * Constantes.resizeWidth / 2, Gdx.graphics.getHeight() / 3 + Gdx.graphics.getHeight() / 8);
                nave1.setSize(2 * 80 * Constantes.resizeWidth, 2 * 80 * Constantes.resizeHeight);
                break;

        }






       // plataforma = GestorAssets.getInstance().getTexture("plataforma.png");




        garajeStage.addActor(barraVida);
        garajeStage.addActor(barraAtaque);
        garajeStage.addActor(barraCadencia);
        garajeStage.addActor(barraEscudo);
        garajeStage.addActor(nave1);

    }









    @Override
    public void render(float delta) {
        //garajeStage.setDebugAll(true);
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batchGaraje.begin();


        batchGaraje.draw(backgroundGaraje, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        batchGaraje.end();

        //pinta el menu
        garajeStage.act();
        garajeStage.draw();





    }

    @Override
    public void dispose() {
        garajeStage.clear();
        garajeStage.dispose();



    }



}
