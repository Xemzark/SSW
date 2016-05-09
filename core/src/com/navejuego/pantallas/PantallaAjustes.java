package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.navejuego.GestorAssets;
import com.navejuego.Preferencias;

/**
 * Created by albertmoreno on 14/4/16.
 */
public class PantallaAjustes extends Pantalla {


    private Stage ajustesStage;

    private SpriteBatch batchAjustes;
    private TextureAtlas buttonsAtlas;
    private Skin buttonSkin,checkboxSkin;
    private BitmapFont font;
    private TextButton aceptar;
    private CheckBox checkbox1,checkbox2,checkbox3;

    private Texture backgroundAjustes;




    public PantallaAjustes() {


        buttonsAtlas = new TextureAtlas("otherskin/button.pack"); //**button atlas image **//
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//

        buttonsAtlas = new TextureAtlas("otherskin/checkbox.pack"); //**button atlas image **//
        checkboxSkin = new Skin();
        checkboxSkin.addRegions(buttonsAtlas); //** skins for on and off **//


        font = new BitmapFont(Gdx.files.internal("otherfont/font.fnt"));

        ajustesStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(ajustesStage);

        batchAjustes = new SpriteBatch();
        backgroundAjustes = GestorAssets.getInstance().getTexture("background_ajustes.png");

        CheckBox.CheckBoxStyle checkstyle = new CheckBox.CheckBoxStyle();
        checkstyle.font=font;
        checkstyle.checkboxOn=checkboxSkin.getDrawable("checkboxon");
        checkstyle.checkboxOff=checkboxSkin.getDrawable("checkboxoff");

        checkbox1 =new CheckBox(null,checkstyle);
        checkbox1.setPosition(0.65f*Gdx.graphics.getWidth(), 0.55f*Gdx.graphics.getHeight());
        checkbox1=resizeCheckbox(checkbox1, 50 * (Gdx.graphics.getWidth() / 360.f), 50 * (Gdx.graphics.getHeight() / 640.0f));
        checkbox1.setChecked(Preferencias.getInstance().musicOn());

        checkbox2 =new CheckBox(null,checkstyle);
        checkbox2.setPosition(0.65f * Gdx.graphics.getWidth(), 0.43f * Gdx.graphics.getHeight());
        checkbox2=resizeCheckbox(checkbox2,50*(Gdx.graphics.getWidth()/360.f),50*(Gdx.graphics.getHeight()/640.0f));
        checkbox2.setChecked(Preferencias.getInstance().soundOn());

        checkbox3 =new CheckBox(null,checkstyle);
        checkbox3.setPosition(0.65f*Gdx.graphics.getWidth(), 0.32f*Gdx.graphics.getHeight());
        checkbox3=resizeCheckbox(checkbox3,50*(Gdx.graphics.getWidth()/360.f),50*(Gdx.graphics.getHeight()/640.0f));
        checkbox3.setChecked(Preferencias.getInstance().vibrationOn());


        checkbox1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (checkbox1.isChecked()) {
                    Preferencias.getInstance().setMusic(true);
                    System.out.println("Music: " + Preferencias.getInstance().musicOn());
                    Preferencias.getInstance().savePreferences();

                } else {
                    Preferencias.getInstance().setMusic(false);
                    System.out.println("Music: " + Preferencias.getInstance().musicOn());
                    GestorAssets.getInstance().getMusic("Starlight.wav").stop();
                    Preferencias.getInstance().savePreferences();
                }
            }
        });
        checkbox2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(checkbox2.isChecked())
                {
                    Preferencias.getInstance().setSound(true);
                    System.out.println("Sound: " + Preferencias.getInstance().soundOn());
                    Preferencias.getInstance().savePreferences();

                }else{
                    Preferencias.getInstance().setSound(false);
                    System.out.println("Sound: " + Preferencias.getInstance().soundOn());
                    Preferencias.getInstance().savePreferences();
                }
            }
        });
        checkbox3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(checkbox3.isChecked())
                {
                    Preferencias.getInstance().setVibration(true);
                    System.out.println("Vibration: " + Preferencias.getInstance().vibrationOn());
                    Preferencias.getInstance().savePreferences();

                }else{
                    Preferencias.getInstance().setVibration(false);
                    System.out.println("Vibration: " + Preferencias.getInstance().vibrationOn());
                    Preferencias.getInstance().savePreferences();
                }
            }
        });

        ajustesStage.addActor(checkbox3);

        ajustesStage.addActor(checkbox2);

        ajustesStage.addActor(checkbox1);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(); //** Button properties **//
        style.up = buttonSkin.getDrawable("buttonOff");
        style.down = buttonSkin.getDrawable("buttonOn");

        style.font=font;

        aceptar = new TextButton("Aceptar", style);

        aceptar.setSize(1.5f* Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 12);
        aceptar.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 5.5f, Gdx.graphics.getHeight() / 4 - Gdx.graphics.getHeight() / 5.5f);
        aceptar.getLabel().setFontScale(0.5f * (Gdx.graphics.getWidth() / 640.0f));

        aceptar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        });

        ajustesStage.addActor(aceptar);




    }

    private CheckBox resizeCheckbox(CheckBox c, float width, float height){


        c.getCells().get(0).height(height);
        c.getCells().get(0).width(width);
        c.setSize(width, height);
        return c;


    }

    private Actor scaleActor(Actor a, float f){
        a.setWidth(a.getWidth() * f);
        a.setHeight(a.getHeight() * f);

        return a;
    }
    public void render(float delta) {
        ajustesStage.setDebugAll(false);
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












