package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.navejuego.GestorAssets;

import java.awt.Image;

/**
 * Created by albert on 16/4/16.
 */
public class PantallaGameOver extends Pantalla{


    private Stage gameoverStage;

    private Skin gameOverskin;
    private SpriteBatch batchGameOver;




    private Texture backgroundGameOver;
    private com.badlogic.gdx.scenes.scene2d.ui.Image gameOver;
    private TextButton retry, volvermenu;


    public PantallaGameOver() {


        gameoverStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(gameoverStage);
        gameOverskin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        batchGameOver = new SpriteBatch();
        backgroundGameOver = GestorAssets.getInstance().getTexture("background_1.png");

        gameOver = new com.badlogic.gdx.scenes.scene2d.ui.Image(GestorAssets.getInstance().getTexture("game_over.png"));
        gameOver.setSize(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3);
        gameOver.setPosition(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);


        retry = new TextButton("Volver a jugar", gameOverskin);
        volvermenu = new TextButton("Volver menu", gameOverskin);

        retry.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 8);
        retry.setPosition(Gdx.graphics.getWidth() / 3- Gdx.graphics.getWidth() / 9, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 16);
        //retry.getSkin().getFont("default-font").getData().setScale(2, 2); //cambia el tamaño de la fuente


        volvermenu.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 8);
        volvermenu.setPosition(Gdx.graphics.getWidth() / 4 + Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 16);
        //volvermenu.getSkin().getFont("default-font").getData().setScale(2, 2); //cambia el tamaño de la fuente

        volvermenu.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());

                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);

            }
        });

        retry.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());

                ScreenManager.getInstance().showScreen(ScreenEnum.GAME);

            }
        });


        gameoverStage.addActor(gameOver);
        gameoverStage.addActor(volvermenu);
        gameoverStage.addActor(retry);





    }


    public void render(float delta) {
        gameoverStage.setDebugAll(true);
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batchGameOver.begin();

        batchGameOver.draw(backgroundGameOver, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batchGameOver.end();

        //pinta el menu
        gameoverStage.act();
        gameoverStage.draw();





    }


    public void dispose() {
        gameoverStage.dispose();


    }



}

