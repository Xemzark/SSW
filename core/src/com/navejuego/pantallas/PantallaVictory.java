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
import com.navejuego.Dialog;
import com.navejuego.GestorAssets;
import com.navejuego.PartidaGuardada;
import com.navejuego.Preferencias;

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

        if(Preferencias.getInstance().musicOn()){
            GestorAssets.getInstance().getMusic("victory.wav").setLooping(true);
            GestorAssets.getInstance().getMusic("victory.wav").play();
        }

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

                if(Preferencias.getInstance().musicOn()){
                    GestorAssets.getInstance().getMusic("victory.wav").stop();
                }
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);

            }
        });
        victoryStage.addActor(volvermenu);

        if(!Constantes.unlockAllLevels){
            int nivels = Constantes.getLevelInt();

            if(nivels < 3 && nivels == PartidaGuardada.getInstance().getNivelDesbloqueado() ){

                System.out.println("Desbloqueando nivel......!!!!");
                System.out.println("nivel actual: " + nivels);
                System.out.println("Nivel maximo: " + PartidaGuardada.getInstance().getNivelDesbloqueado());

                nivels = nivels + 1;
                PartidaGuardada.getInstance().setNivelDesbloqueado(nivels);
                PartidaGuardada.getInstance().saveGameData();
                PartidaGuardada.getInstance().saveGameData();

                String texto;

                //En estos niveles desbloqueas una nave, en los demas no!
                if(nivels == 1 || nivels == 3){
                    texto = "\n Felicidades!! \n \n Has desbloqueado el nivel "+ String.valueOf(nivels + 1) + "! \n" +
                            " Has desbloqueado una nueva nave! \n";

                    int navedesbloqueada = 0;
                    if(nivels == 1){
                        navedesbloqueada = 1;
                    }else if(nivels == 3){
                        navedesbloqueada = 2;
                    }

                    if (navedesbloqueada > PartidaGuardada.getInstance().getNaveDesbloqueada()) {
                        PartidaGuardada.getInstance().setNaveDesbloqueada(navedesbloqueada);
                        System.out.println("Nave debloqueada: " + navedesbloqueada);
                    }


                }else{
                    texto = "\n Felicidades!! \n \n Has desbloqueado el nivel "+ String.valueOf(nivels + 1) + "! \n";
                }

                Skin uiSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
                Dialog d = new Dialog("Recompensa!",uiSkin);
                d.text(texto);
                d.button("Aceptar");

                d.show(victoryStage);

            }

        }

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

