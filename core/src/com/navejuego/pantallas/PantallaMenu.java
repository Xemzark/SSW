package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.navejuego.GestorAssets;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;


/**
 * Created by albertmoreno on 9/4/16.
 */
public class PantallaMenu extends Pantalla {


    private SpriteBatch batch;
    private Stage menuStage;


    private Skin skin;
    private Texture background, sol;

    private BitmapFont font;
    private TextButton jugar, Ajustes, Ranking, Garaje;
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin; //** images are used as skins of the button **//

    private Image ajustess;



    public PantallaMenu() {







        buttonsAtlas = new TextureAtlas("otherskin/button.pack"); //**button atlas image **//
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//
        //font = new BitmapFont(Gdx.files.internal("otherskin/new.fnt"),Gdx.files.internal("otherskin/new.png"),true); //** font **//
        font = new BitmapFont(Gdx.files.internal("otherfont/font.fnt"));

        menuStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(menuStage);
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        TextButtonStyle style = new TextButtonStyle(); //** Button properties **//
        style.up = buttonSkin.getDrawable("buttonOff");
        style.down = buttonSkin.getDrawable("buttonOn");
        style.font=font;



        batch = new SpriteBatch();
        background = GestorAssets.getInstance().getTexture("background_menuprincipal.png");
        //sol = GestorAssets.getInstance().getTexture("sol.png");

        ajustess = new com.badlogic.gdx.scenes.scene2d.ui.Image(GestorAssets.getInstance().getTexture("otherskin/ajustess.png"));
        ajustess.setSize(Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight() / 12);
        ajustess.setPosition(Gdx.graphics.getWidth() / 4 + Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 5);






        jugar= new TextButton("Jugar", style);
        Ajustes= new TextButton("Ajustes", style);
        Ranking = new TextButton("Ranking", style);
        Garaje = new TextButton("Garaje", style);

        jugar.setSize(1.5f* Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 12);
        jugar.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 5.5f,   Gdx.graphics.getHeight() / 4 + Gdx.graphics.getHeight() / 4);
        jugar.getLabel().setFontScale(0.5f*(Gdx.graphics.getWidth()/640.0f));

        //jugar.getSkin().getFont("default-font").getData().setScale(2,2); //cambia el tamaño de la fuente del boton
        //Ajustes.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
        //Ajustes.setPosition(Gdx.graphics.getWidth() / 4 + 100 , Gdx.graphics.getHeight() / 3);

        Garaje.setSize(1.5f*Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 12);
        Garaje.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() /5.5f , Gdx.graphics.getHeight() / 4 + Gdx.graphics.getHeight() / 8);
        Garaje.getLabel().setFontScale(0.5f*(Gdx.graphics.getWidth()/640.0f));

        //Garaje.getSkin().getFont("default-font").getData().setScale(2, 2); //cambia el tamaño de la fuente

        Ajustes.setSize(1.5f*Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 12);
        Ajustes.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() /5.5f, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 14);
        Ajustes.getLabel().setFontScale(0.5f*(Gdx.graphics.getWidth()/640.0f));

        //Ajustes.getSkin().getFont("default-font").getData().setScale(2, 2); //cambia el tamaño de la fuente del boton

        Ranking.setSize(1.5f* Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 12);
        Ranking.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() /5.5f, Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 5.5f);
        Ranking.getLabel().setFontScale(0.5f*(Gdx.graphics.getWidth()/640.0f));

        //Ranking.getSkin().getFont("default-font").getData().setScale(2, 2); //cambia el tamaño de la fuente




        ajustess.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());
                jugar.setText("Starting new game");
                ScreenManager.getInstance().showScreen(ScreenEnum.AJUSTES);

            }
        });



        jugar.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());
                jugar.setText("Starting new game");
                ScreenManager.getInstance().showScreen(ScreenEnum.GAME);

            }
        });




        Ranking.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());

                ScreenManager.getInstance().showScreen(ScreenEnum.GARAJE);

            }
        });

        Ajustes.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());

                ScreenManager.getInstance().showScreen(ScreenEnum.AJUSTES);

            }
        });


        Garaje.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());

                ScreenManager.getInstance().showScreen(ScreenEnum.GARAJE);

            }
        });



        menuStage.addActor(Ajustes);
        menuStage.addActor(Garaje);
        menuStage.addActor(Ranking);
        menuStage.addActor(jugar);
        menuStage.addActor(ajustess);




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
        //batch.draw(sol, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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
