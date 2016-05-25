package com.navejuego.pantallas;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.navejuego.GestorAssets;

/**
 * Created by albertmoreno on 14/4/16.
 */
public class LoadingScreen extends Pantalla {

    private Stage stage;

    /** This is the skin file (see GameOverScreen for more information on this). */
    private Skin skin;

    /** This is the label that we use to display some text on the screen. */
    private Label loading;
    private BitmapFont font;
    private SpriteBatch batch;



    public LoadingScreen() {


        stage = new Stage(new FitViewport(640, 360));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        // Create some loading text using this skin file and position it on screen.
        font = new BitmapFont(Gdx.files.internal("otherfont/font.fnt"));
        font.getData().setScale(0.35f*Gdx.graphics.getWidth()/360.0f,0.5f*Gdx.graphics.getHeight()/640.0f);


        batch = new SpriteBatch();

        GestorAssets.getInstance().create();



    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();


        // This is important. To load an asset from the asset manager you call update() method.
        // this method will return true if it has finished loading. Else it will return false.
        // You usually want to do the code that changes to the main menu screen if it has finished
        // loading, else you update the screen to not make the user angry and keep loading.
        if (GestorAssets.getInstance().retornarUpdate()) {

            ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);

        } else {

            int progress = (int) (GestorAssets.getInstance().retornarProgress() * 100);
            font.draw(batch, String.valueOf("Loading... " + progress + "%"), (Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()/3), Gdx.graphics.getHeight() / 2);

        }

        batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
