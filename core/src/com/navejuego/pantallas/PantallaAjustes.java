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
public class PantallaAjustes extends Pantalla {


    private Stage ajustesStage;

    private SpriteBatch batchAjustes;



    private Skin skinAjustes;
    private Texture backgroundAjustes;


    public PantallaAjustes() {

        ajustesStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(ajustesStage);
        skinAjustes = new Skin(Gdx.files.internal("skin/uiskin.json"));

        batchAjustes = new SpriteBatch();
        backgroundAjustes = GestorAssets.getInstance().getTexture("background_8.png");


    }

    @Override
    public void render(float delta) {
        ajustesStage.setDebugAll(true);
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batchAjustes.begin();

        batchAjustes.draw(backgroundAjustes, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batchAjustes.end();

        //pinta el menu
        ajustesStage.act();
        ajustesStage.draw();


    }

    @Override
    public void dispose() {
        ajustesStage.dispose();




}









}
