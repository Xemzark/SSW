package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;

import com.navejuego.GestorAssets;
import com.navejuego.pantallas.Pantalla;

import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANIEL on 27/04/2016.
 */
public class MainNivelesScreen extends Pantalla {


    private Stage nivelesStage;

    private Skin skinNiveles;
    private SpriteBatch batchNiveles;


    private Table menuNiveles;
    private Skin skin;
    private Texture backgroundNiveles;
    private TextButton button1, button2,button3,button4;





    public MainNivelesScreen() {


        nivelesStage = new Stage();
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        menuNiveles = new Table(skin);
        menuNiveles.setFillParent(true);
        menuNiveles.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        menuNiveles.setSize(Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2);




        nivelesStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(nivelesStage);
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        batchNiveles = new SpriteBatch();
        backgroundNiveles = GestorAssets.getInstance().getTexture("background_niveles.png");


       


        button1 = new TextButton("1", skin);
        button2 = new TextButton("2", skin);
        button3 = new TextButton("3", skin);
        button4 = new TextButton("4", skin);

        button1.setSize(200,100);
        button2.setSize(200,100);
        button3.setSize(200,100);
        button4.setSize(200,100);

        button1.setPosition(100, 220);
        button2.setPosition(350, 220);
        button3.setPosition(100, 50);
        button4.setPosition(350, 50);

        nivelesStage.addActor(button1);
        nivelesStage.addActor(button2);
        nivelesStage.addActor(button3);
        nivelesStage.addActor(button4);
        nivelesStage.addActor(menuNiveles);




    }

    @Override
    public void render(float delta) {
        nivelesStage.setDebugAll(true);
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batchNiveles.begin();

        batchNiveles.draw(backgroundNiveles, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batchNiveles.end();

        //pinta el menu
        nivelesStage.act();
        nivelesStage.draw();





    }

    @Override
    public void dispose() {
        nivelesStage.dispose();


    }



}





