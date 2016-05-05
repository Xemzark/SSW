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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.navejuego.Constantes;
import com.navejuego.GestorAssets;
import com.navejuego.PartidaGuardada;

import java.awt.Image;

/**
 * Created by albert on 16/4/16.
 */
public class PantallaGameOver extends Pantalla{


    private Stage gameoverStage;


    private SpriteBatch batchGameOver;


private BitmapFont font;

    private Texture backgroundGameOver;
    private com.badlogic.gdx.scenes.scene2d.ui.Image gameOver;
    private TextButton retry, volvermenu;
    private TextureAtlas buttonsAtlas;
    private Skin buttonSkin;
    private int score = 0;
    private int bestScore;

    public PantallaGameOver() {

        //buscando datos de puntuacion y nivel
        int nivel = Constantes.getLevelInt();
        this.score = Constantes.lastScore;
        this.bestScore = PartidaGuardada.getInstance().getPuntuacion(nivel,0);

        buttonsAtlas = new TextureAtlas("otherskin/button.pack"); //**button atlas image **//
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//

        gameoverStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(gameoverStage);


        font = new BitmapFont(Gdx.files.internal("otherfont/font.fnt"));
        font.getData().setScale(0.5f,0.5f);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(); //** Button properties **//
        style.up = buttonSkin.getDrawable("buttonOff");
        style.down = buttonSkin.getDrawable("buttonOn");
        style.font=font;



        batchGameOver = new SpriteBatch();
        backgroundGameOver = GestorAssets.getInstance().getTexture("background_gameover.png");


        retry = new TextButton("Jugar", style);
        volvermenu = new TextButton("Menu", style);

        retry.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 8);
        retry.setPosition(Gdx.graphics.getWidth() / 3 - Gdx.graphics.getWidth() / 9, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 4);
        retry.getLabel().setFontScale(0.5f * (Gdx.graphics.getWidth() / 640.0f));


        volvermenu.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 8);
        volvermenu.setPosition(Gdx.graphics.getWidth() / 4 + Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 4);
        volvermenu.getLabel().setFontScale(0.5f * (Gdx.graphics.getWidth() / 640.0f));

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



        gameoverStage.addActor(volvermenu);
        gameoverStage.addActor(retry);





    }


    public void render(float delta) {
        //gameoverStage.setDebugAll(true);
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batchGameOver.begin();

        batchGameOver.draw(backgroundGameOver, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(batchGameOver, String.valueOf(this.score), (Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 14), Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 29);
        font.draw(batchGameOver, String.valueOf(this.bestScore), (Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()/8), Gdx.graphics.getHeight()/2 -Gdx.graphics.getHeight() / 9);



        batchGameOver.end();

        //pinta el menu
        gameoverStage.act();
        gameoverStage.draw();





    }


    public void dispose() {
        gameoverStage.dispose();


    }



}

