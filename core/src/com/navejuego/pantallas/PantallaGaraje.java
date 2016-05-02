package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.navejuego.GestorAssets;

/**
 * Created by albertmoreno on 14/4/16.
 */
public class PantallaGaraje extends Pantalla {


    private Stage garajeStage;

    private Skin skinGaraje;
    private SpriteBatch batchGaraje;



    private Skin skin;
    private Texture backgroundGaraje;


    public PantallaGaraje() {


        garajeStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(garajeStage);
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        batchGaraje = new SpriteBatch();
        backgroundGaraje = GestorAssets.getInstance().getTexture("backgroundgaraje.png");




    }

    @Override
    public void render(float delta) {
        garajeStage.setDebugAll(true);
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
        garajeStage.dispose();


    }



}
