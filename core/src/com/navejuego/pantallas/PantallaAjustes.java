package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.navejuego.GestorAssets;

/**
 * Created by albertmoreno on 14/4/16.
 */
public class PantallaAjustes extends Pantalla {


    private Stage ajustesStage;

    private SpriteBatch batchAjustes;



    private BitmapFont font;
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin; //** images are used as skins of the button **//
    private TextButton button;


    private Skin skinAjustes;
    private Texture backgroundAjustes;


    public void show() {


        buttonsAtlas = new TextureAtlas("otherskin/button.pack"); //**button atlas image **//
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//
        //font = new BitmapFont(Gdx.files.internal("otherskin/new.fnt"),Gdx.files.internal("otherskin/new.png"),true); //** font **//
        font = new BitmapFont(Gdx.files.internal("skin/default.fnt"));

        ajustesStage = new Stage();        //** window is stage **//
        ajustesStage.clear();
        Gdx.input.setInputProcessor(ajustesStage); //** stage is responsive **//

        TextButtonStyle style = new TextButtonStyle(); //** Button properties **//
        style.up = buttonSkin.getDrawable("buttonOff");
        style.down = buttonSkin.getDrawable("buttonOn");

        style.font = font;

        button = new TextButton("KKKK", style);
        //** Button text and style **//
        button.setHeight(200); //** Button Height **//
        button.setWidth(200); //** Button Width **//



        button.setPosition(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2, Gdx.graphics.getHeight() / 2);


        ajustesStage.addActor(button);


        skinAjustes = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Slider slider = new Slider(0,50,10,false,skinAjustes);
        slider.setPosition(200,200);
        slider.setSize(50,50);

        ajustesStage.addActor(slider);

    }

    public PantallaAjustes() {

        /*ajustesStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(ajustesStage);
        skinAjustes = new Skin(Gdx.files.internal("skin/uiskin.json"));

        batchAjustes = new SpriteBatch();
        backgroundAjustes = GestorAssets.getInstance().getTexture("background_8.png");

        Slider slider = new Slider(0,50,10,false,skinAjustes);
        slider.setPosition(200,200);
        slider.setSize(50,50);

        ajustesStage.addActor(slider);*/

    }

    @Override
    public void render(float delta) {
        ajustesStage.setDebugAll(true);
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        /*batchAjustes.begin();

        batchAjustes.draw(backgroundAjustes, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batchAjustes.end();*/

        //pinta el menu
        ajustesStage.act();
        ajustesStage.draw();


    }

    @Override
    public void dispose() {
        ajustesStage.dispose();




}









}
