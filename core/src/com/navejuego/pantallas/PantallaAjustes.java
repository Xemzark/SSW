package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.navejuego.GestorAssets;

/**
 * Created by albertmoreno on 14/4/16.
 */
public class PantallaAjustes extends Pantalla {


    private Stage ajustesStage;

    private Skin gameOverskin;
    private SpriteBatch batchAjustes;




    private Texture backgroundAjustes;




    public PantallaAjustes() {


        ajustesStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(ajustesStage);
        gameOverskin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        batchAjustes = new SpriteBatch();
        backgroundAjustes = GestorAssets.getInstance().getTexture("background_ajustes.png");












    }


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


    public void dispose() {
        ajustesStage.dispose();


    }



}












