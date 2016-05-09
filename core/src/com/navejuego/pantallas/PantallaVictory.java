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
import com.navejuego.entidades.LevelManager;

import java.awt.Image;

/**
 * Created by albert on 16/4/16.
 */
public class PantallaVictory extends Pantalla{

    private Stage victoryStage;
    private SpriteBatch batchVictory;
    private BitmapFont font;
    private Texture backgroundVictory;
    private TextButton Menu, volvermenu;
    private TextureAtlas buttonsAtlas;
    private Skin buttonSkin;
    private int bestScore;
    private int score;

    public PantallaVictory() {

        int nivel = Constantes.getLevelInt();

        this.score = Constantes.lastScore;
        this.bestScore = PartidaGuardada.getInstance().getPuntuacion(nivel,0);
        PartidaGuardada.getInstance().setPuntucion(nivel,this.score);
        PartidaGuardada.getInstance().saveGameData();
        PartidaGuardada.getInstance().printPuntuaciones();

        buttonsAtlas = new TextureAtlas("otherskin/button.pack"); //**button atlas image **//
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//

        victoryStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(victoryStage);


        font = new BitmapFont(Gdx.files.internal("otherfont/font.fnt"));
        font.getData().setScale(0.5f*Gdx.graphics.getWidth()/360.f,0.5f*Gdx.graphics.getHeight()/640.0f);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(); //** Button properties **//
        style.up = buttonSkin.getDrawable("buttonOff");
        style.down = buttonSkin.getDrawable("buttonOn");
        style.font=font;




        batchVictory = new SpriteBatch();
        backgroundVictory = GestorAssets.getInstance().getTexture("background_victory.png");



        volvermenu = new TextButton("Menu", style);

        volvermenu.setSize(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 8);
        volvermenu.setPosition(Gdx.graphics.getWidth() / 2 - volvermenu.getWidth()/2, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 4);
        volvermenu.getLabel().setFontScale(0.5f * (Gdx.graphics.getWidth() / 640.0f));

        volvermenu.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());

                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);

            }
        });
        victoryStage.addActor(volvermenu);

    }

    public void render(float delta) {
        //gameoverStage.setDebugAll(true);
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batchVictory.begin();

        batchVictory.draw(backgroundVictory, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(batchVictory, String.valueOf(this.score), (Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 14), Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 11);
        font.draw(batchVictory, String.valueOf(this.bestScore), (Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()/8), Gdx.graphics.getHeight()/2 -Gdx.graphics.getHeight() / 20);


        batchVictory.end();

        //pinta el menu
        victoryStage.act();
        victoryStage.draw();
    }

    public void dispose() {
        victoryStage.dispose();


    }



}

