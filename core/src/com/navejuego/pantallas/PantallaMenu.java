package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.navejuego.GestorAssets;


/**
 * Created by albertmoreno on 9/4/16.
 */
public class PantallaMenu extends Pantalla {


    private SpriteBatch batch;
    private Stage menuStage;


    private Skin skin;
    private Texture background;

    private TextButton jugar, Ajustes, Ranking, Garaje;



    public PantallaMenu() {



        menuStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(menuStage);
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        batch = new SpriteBatch();
        background = GestorAssets.getInstance().getTexture("background_8.png");

        jugar= new TextButton("Jugar", skin);
        Ajustes= new TextButton("Ajustes", skin);
        Ranking = new TextButton("Ranking", skin);
        Garaje = new TextButton("Garaje", skin);

        jugar.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 8);

        jugar.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, 2* Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 16);
        jugar.getSkin().getFont("default-font").getData().setScale(2,2); //cambia el tamaño de la fuente del boton
        //Ajustes.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
        //Ajustes.setPosition(Gdx.graphics.getWidth() / 4 + 100 , Gdx.graphics.getHeight() / 3);

        Garaje.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 8);
        Garaje.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 16);
        Garaje.getSkin().getFont("default-font").getData().setScale(2, 2); //cambia el tamaño de la fuente

        Ajustes.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 8);
        Ajustes.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth() / 8, 0,5*Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 16);
        Ajustes.getSkin().getFont("default-font").getData().setScale(2, 2); //cambia el tamaño de la fuente del boton

        Ranking.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 8);
        Ranking.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 16);
        Ranking.getSkin().getFont("default-font").getData().setScale(2, 2); //cambia el tamaño de la fuente



        // jugar.addListener()

        jugar.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());
                jugar.setText("Starting new game");
                ScreenManager.getInstance().showScreen(ScreenEnum.GAME);

            }
        });


        menuStage.addActor(Ajustes);
        menuStage.addActor(Garaje);
        menuStage.addActor(Ranking);
        menuStage.addActor(jugar);



    }

    private void createBasicSkin() {
/*
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("font", font);

        Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
       pixmap.setColor(Color.BLUE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.YELLOW);
        textButtonStyle.font = skin.getFont("font");
        skin.add("default",textButtonStyle);

        */





    }
/*
    @Override
    public void show() {

        //crea una tabla donde añadir elementos del menu
        //Table table= new Table();
        //table.setPosition(1024 / 3, 450);
        //table.setFillParent(true);
        //table.setHeight(500);
        //menuStage.addActor(table);

        //etiqueta del texto
        //Label label = new Label("Welcome to SpaceShipInvaders", skin );
        //table.addActor(label);

        //Boton
        TextButton buttonPlay = new TextButton("Jugar", skin);
        buttonPlay.setSize(Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/8);
        buttonPlay.setPosition(Gdx.graphics.getWidth() / 2 - 400, Gdx.graphics.getHeight() / 2);
        //buttonPlay.setWidth(Gdx.graphics.getWidth()/4);
        //buttonPlay.setHeight(Gdx.graphics.getHeight()/8);
        //buttonPlay.addListener()

        menuStage.addActor(buttonPlay);


    }
    */

    @Override
    public void render(float delta) {
        menuStage.setDebugAll(true);
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();

        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.end();

        //pinta el menu
        menuStage.act();
        menuStage.draw();


    }

    @Override
    public void dispose() {
        menuStage.dispose();


    }
}
