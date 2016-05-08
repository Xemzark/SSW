package com.navejuego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.navejuego.GestorAssets;
import com.navejuego.PartidaGuardada;

import java.awt.Image;

import javax.xml.soap.Text;

/**
 * Created by albertmoreno on 14/4/16.
 */
public class PantallaRanking extends Pantalla {


    private SpriteBatch batchRanking;
    private Stage rankingStage;


    private Label title;
    private Skin skinRanking;
    private Texture backgroundRanking;
    private BitmapFont font;

    private static final String reallyLongString = "Holins \n Holins \n Holins \n Holins \n Holins \n Holins \n Holins \n Holins \n Holins \n Holins \n";

    private String name="Pedro";
    private String points="200";

    int[] Posiciones = new int[PartidaGuardada.getInstance().getMaxPuntuaciones()];
    int[] Puntuaciones = new int[PartidaGuardada.getInstance().getMaxPuntuaciones()];
    int nivelACargar=0;

    Table scrollTable = new Table();

    float textsizeX = 0.01f*Gdx.graphics.getWidth();
    float textsizeY = 0.01f*Gdx.graphics.getWidth();
    int scrollerSizeX = Gdx.graphics.getWidth();
    int scrollerSizeY = Gdx.graphics.getHeight()/3;
    Label.LabelStyle labelstyle = new Label.LabelStyle();

    /**
     * Para cargar putuaciones de un nivel u otro. Nivel 1 = 0;
     * @param nivel
     */
    public void setNivelACargar(int nivel){

        this.nivelACargar = nivel;
        System.out.println("Cargando nivel "+nivel);
        this.Puntuaciones=new int[PartidaGuardada.getInstance().getMaxPuntuaciones()];
        this.Puntuaciones = PartidaGuardada.getInstance().getPuntuaciones(this.nivelACargar);

        for(int i = 1; i != PartidaGuardada.getInstance().getMaxPuntuaciones() + 1; i++){
            System.out.println(i);
            this.Posiciones[i - 1] = i;
        }
    }

    /**
     * rellena el scroll con las puntuaciones indicadas en SetNivelACargar(nivel);
     */
    public void fillScrollTable(){
        scrollTable.clear();
        this.labelstyle.font= font;

        for(int i=0; i<Posiciones.length;i++) {

            Label text = new Label(String.valueOf(this.Posiciones[i]), labelstyle);
            text.setAlignment(Align.center);
            text.setFontScale(0.1f*textsizeX, 0.1f*textsizeY);
            text.setWrap(true);

            Label text2 = new Label(String.valueOf(this.Puntuaciones[i]), labelstyle);
            text2.setFontScale(0.1f*textsizeX,0.1f*textsizeY);
            text2.setAlignment(Align.center);
            text2.setWrap(true);

            scrollTable.add(text).minWidth(0.5f * scrollerSizeX).minHeight(0.25f*scrollerSizeY);
            scrollTable.add(text2).minWidth(0.5f * scrollerSizeX).minHeight(0.25f * scrollerSizeY);
            scrollTable.row();

        }

    }
    public PantallaRanking() {

        rankingStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(rankingStage);
        skinRanking = new Skin(Gdx.files.internal("skin/uiskin.json"));


        font = new BitmapFont(Gdx.files.internal("otherfont/font.fnt"));
        Label.LabelStyle ls = new Label.LabelStyle(font, null);

        scrollTable.row();
        final ScrollPane scroller = new ScrollPane(scrollTable);
        scroller.setSize(scrollerSizeX, scrollerSizeY);
        scroller.setPosition(0, 0.25f * Gdx.graphics.getHeight());
        rankingStage.addActor(scroller);

        title = new Label("Nivel "+(nivelACargar+1),ls);
        title.setPosition(0.27f * Gdx.graphics.getWidth(), 0.58f * Gdx.graphics.getHeight());
        title.setFontScale(0.5f * Gdx.graphics.getWidth()/360.0f);
        title.setSize(title.getWidth() * title.getFontScaleX(), title.getHeight() * title.getFontScaleY());

        ImageButton leftarrow=new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("otherskin/left-pointing-arrow-white.png"))));
        leftarrow=resizebutton(leftarrow,50*Gdx.graphics.getWidth()/340.0f,50*Gdx.graphics.getHeight()/640.f);
        leftarrow.setPosition(0, 0.58f * Gdx.graphics.getHeight());

        ImageButton rightarrow = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("otherskin/right-pointing-arrow-white.png"))));
        rightarrow=resizebutton(rightarrow, 50*Gdx.graphics.getWidth()/340.0f,50*Gdx.graphics.getHeight()/640.f);
        rightarrow.setPosition(Gdx.graphics.getWidth() - rightarrow.getWidth(), 0.58f * Gdx.graphics.getHeight());

        leftarrow.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (nivelACargar > 0) {
                    nivelACargar -= 1;
                    setNivelACargar(nivelACargar);
                    fillScrollTable();
                    title.setText("Nivel " + (nivelACargar + 1));

                }
            }
        });

        rightarrow.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(nivelACargar < PartidaGuardada.getInstance().getMaxNiveles()-1) {
                    nivelACargar += 1;
                    setNivelACargar(nivelACargar);
                    fillScrollTable();
                    title.setText("Nivel "+(nivelACargar+1));
                }
            }
        });


        rankingStage.addActor(title);
        rankingStage.addActor(rightarrow);
        rankingStage.addActor(leftarrow);


        //Definir nivel del cual cargar puntuaciones
        this.setNivelACargar(nivelACargar);
        //Rellenar el scroll con las puntuaciones
        this.fillScrollTable();

        TextureAtlas buttonsAtlas = new TextureAtlas("otherskin/button.pack"); //**button atlas image **//
        Skin buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//

        buttonsAtlas = new TextureAtlas("otherskin/checkbox.pack"); //**button atlas image **//

        batchRanking = new SpriteBatch();
        backgroundRanking = GestorAssets.getInstance().getTexture("background_8.png");

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

    private ImageButton resizebutton(ImageButton b, float x, float y){

        b.getImage().setSize(x, y);
        b.setSize(x,y);

        return b;
    }

}
