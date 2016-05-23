package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import com.navejuego.Constantes;
import com.navejuego.GestorAssets;
import com.navejuego.PartidaGuardada;
import com.navejuego.entidades.LevelManager;

/**
 * Created by DANIEL on 27/04/2016.
 */
public class MainNivelesScreen extends Pantalla {


    private Stage nivelesStage;

    private Skin skinNiveles;
    private SpriteBatch batchNiveles;


    // private Table menuNiveles;
    private Skin skin;
    private Texture backgroundNiveles;
    private TextButton button1, button2,button3,button4,buttonatras;
    private BitmapFont font;

    private TextureAtlas buttonsAtlas;
    private Skin buttonSkin;





    public MainNivelesScreen() {

        System.out.println("Nivel desbloqueado actualmente: "+ PartidaGuardada.getInstance().getNivelDesbloqueado());
        buttonsAtlas = new TextureAtlas("otherskin/button.pack"); //**button atlas image **//
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//
        font = new BitmapFont(Gdx.files.internal("otherfont/font.fnt"));
        //nivelesStage = new Stage();
        //skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

      /*  menuNiveles = new Table(skin);
        menuNiveles.setFillParent(true);
        menuNiveles.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        menuNiveles.setSize(Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2);
*/
        nivelesStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(nivelesStage);
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        TextButtonStyle style = new TextButtonStyle(); //** Button properties **//
        style.up = buttonSkin.getDrawable("buttonOff");
        style.down = buttonSkin.getDrawable("buttonOn");
        style.font=font;

        batchNiveles = new SpriteBatch();
        backgroundNiveles = GestorAssets.getInstance().getTexture("background_niveles.png");


        button1 = new TextButton("1", style);
        button1.setSize(1.5f * Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 12);
        button1.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 5.5f, Gdx.graphics.getHeight() / 4 + Gdx.graphics.getHeight() / 4);
        button1.getLabel().setFontScale(0.5f * (Gdx.graphics.getWidth() / 640.0f));


        button2 = new TextButton("2", style);
        button2.setSize(1.5f * Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 12);
        button2.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 5.5f, Gdx.graphics.getHeight() / 4 + Gdx.graphics.getHeight() / 8);
        button2.getLabel().setFontScale(0.5f * (Gdx.graphics.getWidth() / 640.0f));



        button3 = new TextButton("3", style);
        button3.setSize(1.5f * Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 12);
        button3.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 5.5f, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 14);
        button3.getLabel().setFontScale(0.5f * (Gdx.graphics.getWidth() / 640.0f));




        button4 = new TextButton("4", style);
        button4.setSize(1.5f * Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 12);
        button4.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 5.5f, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 5.5f);
        button4.getLabel().setFontScale(0.5f * (Gdx.graphics.getWidth() / 640.0f));

        buttonatras = new TextButton("Atras", style);
        buttonatras.setSize(1.5f * Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 12);
        buttonatras.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 5.5f, Gdx.graphics.getHeight() / 4 - Gdx.graphics.getHeight() / 4);
        buttonatras.getLabel().setFontScale(0.5f * (Gdx.graphics.getWidth() / 640.0f));


        nivelesStage.addActor(button1);
        nivelesStage.addActor(button2);
        nivelesStage.addActor(button3);
        nivelesStage.addActor(button4);
        nivelesStage.addActor(buttonatras);
        //     nivelesStage.addActor(menuNiveles);



        button1.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());

                    Constantes.selectedLevel = LevelManager.Nivel.NIVEL_1;
                    ScreenManager.getInstance().showScreen(ScreenEnum.GAME);
            }
        });

        button2.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());
                if(PartidaGuardada.getInstance().getNivelDesbloqueado() >0){
                    Constantes.selectedLevel = LevelManager.Nivel.NIVEL_2;
                    ScreenManager.getInstance().showScreen(ScreenEnum.GAME);
                }else{
                    System.out.println("Nivel 2 no desbloqueado");
                    Skin uiSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
                    Dialog d = new Dialog("Nivel 2 no desbloqueado",uiSkin);
                    d.text("\n Acaba el nivel 1 para desbloquear este nivel! \n");
                    d.button("Aceptar");
                    d.show(nivelesStage);
                }


            }
        });

        button3.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());
                if(PartidaGuardada.getInstance().getNivelDesbloqueado() >1){
                    Constantes.selectedLevel = LevelManager.Nivel.NIVEL_3;
                    ScreenManager.getInstance().showScreen(ScreenEnum.GAME);
                }else{
                    System.out.println("Nivel 3 no desbloqueado");
                    Skin uiSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
                    Dialog d = new Dialog("Nivel 3 no desbloqueado",uiSkin);
                    d.text("\n Acaba el nivel 2 para desbloquear este nivel! \n");
                    d.button("Aceptar");
                    d.show(nivelesStage);
                }


            }
        });

        button4.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());
                if(PartidaGuardada.getInstance().getNivelDesbloqueado() >2){
                    Constantes.selectedLevel = LevelManager.Nivel.NIVEL_4;
                    ScreenManager.getInstance().showScreen(ScreenEnum.GAME);
                }else{
                    System.out.println("Nivel 4 no desbloqueado");
                    Skin uiSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
                    Dialog d = new Dialog("Nivel 4 no desbloqueado",uiSkin);
                    d.text("\n Acaba el nivel 3 para desbloquear este nivel! \n");
                    d.button("Aceptar");
                    d.show(nivelesStage);
                }


            }
        });

        buttonatras.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());


                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        });



    }

    @Override
    public void render(float delta) {
       // nivelesStage.setDebugAll(true);
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