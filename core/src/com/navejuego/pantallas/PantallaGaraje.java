package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.navejuego.Constantes;
import com.navejuego.Dialog;
import com.navejuego.GestorAssets;
import com.navejuego.PartidaGuardada;
import com.navejuego.entidades.JugadorType;
import com.navejuego.entidades.ui.Barra;


/**
 * Created by albertmoreno on 14/4/16.
 */
public class PantallaGaraje extends Pantalla {


    private Stage garajeStage;
    private Barra barra;

    private Skin skinGaraje;
    private SpriteBatch batchGaraje;

    private Texture plataforma;
    private Image barraAtaque, barraEscudo, barraCadencia, barraVida, nave1;
    private TextButton aceptar;


    private Skin skin;
    private Texture backgroundGaraje;
    private JugadorType jugador;

    private TextureAtlas buttonsAtlas;
    private Skin buttonSkin;
    private BitmapFont font;
    private int opcion;


    public PantallaGaraje() {






        garajeStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(garajeStage);
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        buttonsAtlas = new TextureAtlas("otherskin/button.pack"); //**button atlas image **//
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//


        font = new BitmapFont(Gdx.files.internal("otherfont/font.fnt"));

       opcion= Constantes.naveUltimaSeleccion;
        System.out.println("Nave numero:" + opcion);


        batchGaraje = new SpriteBatch();
        backgroundGaraje = GestorAssets.getInstance().getTexture("background_garaje.png");



        switch (opcion) {

            case 0:
                jugador = new JugadorType(opcion);
                barraAtaque = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraAtaque.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 50);
                barraAtaque.setSize(Gdx.graphics.getWidth() / 2 * jugador.damage/17, Gdx.graphics.getHeight() / 16);

                barraEscudo = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraEscudo.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 18);
                barraEscudo.setSize(Gdx.graphics.getWidth() / 2* jugador.escudo/140, Gdx.graphics.getHeight() / 16);

                barraVida = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraVida.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 11);
                barraVida.setSize(Gdx.graphics.getWidth() / 3 * jugador.vida/140, Gdx.graphics.getHeight() / 16);

                barraCadencia = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraCadencia.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 8);
                barraCadencia.setSize(Gdx.graphics.getWidth() / 2 * jugador.cadenciaDisparo, Gdx.graphics.getHeight() / 16);

                nave1 = new Image(GestorAssets.getInstance().getTexture("nave1.png"));
                nave1.setPosition(Gdx.graphics.getWidth() / 2 - 2 * 80 * Constantes.resizeWidth / 2, Gdx.graphics.getHeight() / 3 + Gdx.graphics.getHeight() / 8);
                nave1.setSize(2 * 80 * Constantes.resizeWidth, 2 * 80 * Constantes.resizeHeight);


                break;
            case 1:
                jugador = new JugadorType(opcion);
                barraAtaque = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraAtaque.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 50);
                barraAtaque.setSize(Gdx.graphics.getWidth() / 2 * jugador.damage/17, Gdx.graphics.getHeight() / 16);
                barraEscudo = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraEscudo.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 18);
                barraEscudo.setSize(Gdx.graphics.getWidth() / 2 * jugador.escudo/140, Gdx.graphics.getHeight() / 16);

                barraVida = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraVida.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 11);
                barraVida.setSize(Gdx.graphics.getWidth() / 2 * jugador.vida/140, Gdx.graphics.getHeight() / 16);

                barraCadencia = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraCadencia.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 8);
                barraCadencia.setSize(Gdx.graphics.getWidth() / 2 * jugador.cadenciaDisparo, Gdx.graphics.getHeight() / 16);

                nave1 = new Image(GestorAssets.getInstance().getTexture("nave2.png"));
                nave1.setPosition(Gdx.graphics.getWidth() / 2 - 2 * 80 * Constantes.resizeWidth / 2, Gdx.graphics.getHeight() / 3 + Gdx.graphics.getHeight() / 8);
                nave1.setSize(2 * 80 * Constantes.resizeWidth, 2 * 80 * Constantes.resizeHeight);
                break;
            case 2:
                jugador = new JugadorType(opcion);

                barraAtaque = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraAtaque.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 50);
                barraAtaque.setSize(Gdx.graphics.getWidth() / 2 * jugador.damage/17, Gdx.graphics.getHeight() / 16);

                barraEscudo = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraEscudo.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 18);
                barraEscudo.setSize(Gdx.graphics.getWidth() / 2 * jugador.escudo/140, Gdx.graphics.getHeight() / 16);

                barraVida = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraVida.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 11);
                barraVida.setSize(Gdx.graphics.getWidth() / 2 * jugador.vida/140, Gdx.graphics.getHeight() / 16);

                barraCadencia = new Image(GestorAssets.getInstance().getTexture("barraloca.png"));
                barraCadencia.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 8);
                barraCadencia.setSize(Gdx.graphics.getWidth() / 2 * jugador.cadenciaDisparo, Gdx.graphics.getHeight() / 16);

                nave1 = new Image(GestorAssets.getInstance().getTexture("nave3.png"));
                nave1.setPosition(Gdx.graphics.getWidth() / 2 - 2 * 80 * Constantes.resizeWidth / 2, Gdx.graphics.getHeight() / 3 + Gdx.graphics.getHeight() / 8);
                nave1.setSize(2 * 80 * Constantes.resizeWidth, 2 * 80 * Constantes.resizeHeight);
                break;

        }

        if(opcion > PartidaGuardada.getInstance().getNaveDesbloqueada()){
            nave1.setColor(0,0,0,1);



        }
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(); //** Button properties **//
        style.up = buttonSkin.getDrawable("buttonOff");
        style.down = buttonSkin.getDrawable("buttonOn");

        style.font=font;

        aceptar = new TextButton("Aceptar", style);

        aceptar.setSize(1.5f * Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 12);
        aceptar.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 5.5f, Gdx.graphics.getHeight() / 4 - Gdx.graphics.getHeight() / 5.5f);
        aceptar.getLabel().setFontScale(0.5f * (Gdx.graphics.getWidth() / 640.0f));

        aceptar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(opcion> PartidaGuardada.getInstance().getNaveDesbloqueada()){
                    Dialog d = new Dialog("Nave Bloqueada",skin);

                    d.setPosition(Gdx.graphics.getWidth()/2- 100, Gdx.graphics.getHeight()/2);
                    d.button("Aceptar");
                    d.setScale(5, 5);
                    d.show(garajeStage);
                }else {

                    PartidaGuardada.getInstance().setNaveSeleccionada(opcion);
                    PartidaGuardada.getInstance().saveGameData();
                    ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
                }
            }
        });



        ImageButton leftarrow=new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("otherskin/left-pointing-arrow-white.png"))));
        leftarrow=resizebutton(leftarrow,50*Gdx.graphics.getWidth()/340.0f,50*Gdx.graphics.getHeight()/640.f);
        leftarrow.setPosition(0, 0.58f * Gdx.graphics.getHeight());

        ImageButton rightarrow = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("otherskin/right-pointing-arrow-white.png"))));
        rightarrow=resizebutton(rightarrow, 50*Gdx.graphics.getWidth()/340.0f,50*Gdx.graphics.getHeight()/640.f);
        rightarrow.setPosition(Gdx.graphics.getWidth() - rightarrow.getWidth(), 0.58f * Gdx.graphics.getHeight());

        leftarrow.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (opcion <= 0) {
                    opcion= 2;

                } else {
                    opcion--;


                }
                Constantes.naveUltimaSeleccion = opcion;
                ScreenManager.getInstance().showScreen(ScreenEnum.GARAJE);
            }
        });

        rightarrow.addListener(new ChangeListener() {


            @Override
            public void changed(ChangeEvent event, Actor actor) {


                if (opcion>=2)
                {
                    opcion=0;

                }else {
                    // System.out.println(opcion);
                    opcion++;


                }
                Constantes.naveUltimaSeleccion= opcion;

                ScreenManager.getInstance().showScreen(ScreenEnum.GARAJE);


            }
        });





        garajeStage.addActor(rightarrow);
        garajeStage.addActor(leftarrow);
        garajeStage.addActor(aceptar);
        garajeStage.addActor(barraVida);
        garajeStage.addActor(barraAtaque);
        garajeStage.addActor(barraCadencia);
        garajeStage.addActor(barraEscudo);
        garajeStage.addActor(nave1);

    }





    private ImageButton resizebutton(ImageButton b, float x, float y){

        b.getImage().setSize(x, y);
        b.setSize(x, y);

        return b;
    }



    @Override
    public void render(float delta) {
        garajeStage.setDebugAll(true);
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batchGaraje.begin();


        batchGaraje.draw(backgroundGaraje, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        batchGaraje.end();

        //pinta el menu
        garajeStage.act();
        garajeStage.draw();





    }

    @Override
    public void dispose() {
        garajeStage.clear();
        garajeStage.dispose();



    }



}
