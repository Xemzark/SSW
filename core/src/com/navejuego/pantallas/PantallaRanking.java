package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.navejuego.GestorAssets;

/**
 * Created by albertmoreno on 14/4/16.
 */
public class PantallaRanking extends Pantalla {


    private SpriteBatch batchRanking;
    private Stage rankingStage;


    private Skin skinRanking;
    private Texture backgroundRanking;
    private BitmapFont font;


    private static final String reallyLongString = "Holins \n Holins \n Holins \n Holins \n Holins \n Holins \n Holins \n Holins \n Holins \n Holins \n";

    private String name="Pedro";
    private String points="200";

    String[] Puntos = {"100","200","500","9000","100","200","500","9000","100","200","500","9000"};
    String[] Personas = {"Juan","Jose","Elias","Broken","Juan","Jose","Elias","Broken","Juan","Jose","Elias","Broken"};


    public PantallaRanking() {


        rankingStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(rankingStage);
        skinRanking = new Skin(Gdx.files.internal("skin/uiskin.json"));
        font = new BitmapFont(Gdx.files.internal("otherfont/font.fnt"));


        TextureAtlas buttonsAtlas = new TextureAtlas("otherskin/button.pack"); //**button atlas image **//
        Skin buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//

        buttonsAtlas = new TextureAtlas("otherskin/checkbox.pack"); //**button atlas image **//

        batchRanking = new SpriteBatch();
        backgroundRanking = GestorAssets.getInstance().getTexture("background_8.png");


        int scrollerSizeX = Gdx.graphics.getWidth();
        int scrollerSizeY = Gdx.graphics.getHeight()/3;
        float textsizeX = 0.01f*Gdx.graphics.getWidth();
        float textsizeY = 0.01f*Gdx.graphics.getWidth();

        Label.LabelStyle labelstyle = new Label.LabelStyle();
        labelstyle.font=font;



        final Table scrollTable = new Table();

        for(int i=0; i<Personas.length;i++) {

            Label text = new Label(Personas[i], labelstyle);
            text.setAlignment(Align.center);
            text.setFontScale(0.1f*textsizeX, 0.1f*textsizeY);
            text.setWrap(true);
            Label text2 = new Label(Puntos[i], labelstyle);
            text2.setFontScale(0.1f*textsizeX,0.1f*textsizeY);
            text2.setAlignment(Align.center);
            text2.setWrap(true);

            scrollTable.add(text).minWidth(0.5f * scrollerSizeX).minHeight(0.25f*scrollerSizeY);
            scrollTable.add(text2).minWidth(0.5f * scrollerSizeX).minHeight(0.25f * scrollerSizeY);
            scrollTable.row();

        }


        scrollTable.row();

        final ScrollPane scroller = new ScrollPane(scrollTable);

        scroller.setSize(scrollerSizeX,scrollerSizeY);
        scroller.setPosition(0,0.25f*Gdx.graphics.getHeight());

        rankingStage.addActor(scroller);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(); //** Button properties **//
        style.up = buttonSkin.getDrawable("buttonOff");
        style.down = buttonSkin.getDrawable("buttonOn");

        style.font=font;

        TextButton aceptar = new TextButton("Aceptar", style);

        aceptar.setSize(1.5f* Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 12);
        aceptar.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 5.5f, Gdx.graphics.getHeight() / 4 - Gdx.graphics.getHeight() / 5.5f);
        aceptar.getLabel().setFontScale(0.5f * (Gdx.graphics.getWidth() / 640.0f));

        aceptar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }
        });

        rankingStage.addActor(aceptar);



    }

    @Override
    public void render(float delta) {
        rankingStage.setDebugAll(true);
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batchRanking.begin();

        batchRanking.draw(backgroundRanking, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batchRanking.end();

        //pinta el menu
        rankingStage.act();
        rankingStage.draw();


    }

    @Override
    public void dispose() {
        rankingStage.dispose();


    }




}
