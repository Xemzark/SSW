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
public class PantallaRanking extends Pantalla {


    private SpriteBatch batchRanking;
    private Stage rankingStage;


    private Skin skinRanking;
    private Texture backgroundRanking;


    public PantallaRanking() {


        rankingStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(rankingStage);
        skinRanking = new Skin(Gdx.files.internal("skin/uiskin.json"));

        batchRanking = new SpriteBatch();
        backgroundRanking = GestorAssets.getInstance().getTexture("background_8.png");


    }

    @Override
    public void render(float delta) {
        rankingStage.setDebugAll(true);
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batchRanking.begin();

        batchRanking.draw(backgroundRanking, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batchRanking.end();

        //pinta el menu
        rankingStage.act();
        rankingStage.draw();


    }

    @Override
    public void dispose() {
        rankingStage.dispose();


    }




}
