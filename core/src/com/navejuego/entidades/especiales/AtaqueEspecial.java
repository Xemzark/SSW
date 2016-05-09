package com.navejuego.entidades.especiales;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Timer;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.GameObjectEntity;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by beno_ on 20/04/2016.
 */
public abstract class AtaqueEspecial extends GameObjectEntity {

    private boolean disponible;
    private int tiempoRestante;
    private int delay;
    private botonEspecial boton;

    public class botonEspecial extends ImageButton
    {
        public botonEspecial(Texture texture_up, Texture texture_down, Texture background) {
            super(new SpriteDrawable(new Sprite(texture_up)),
                    new SpriteDrawable(new Sprite(texture_down)));
            this.setBackground(new SpriteDrawable(new Sprite(background)));
        }

        public void setDisponible(){
            Texture t = GestorAssets.getInstance().getTexture("botonespecial.png");
            ImageButtonStyle style = new ImageButtonStyle();
            style.imageUp = new SpriteDrawable(new Sprite(t));
            this.setStyle(style);
        }

        public void setNoDisponible(){
            Texture t = GestorAssets.getInstance().getTexture("botonespecial_no.png");
            ImageButtonStyle style = new ImageButtonStyle();
            style.imageUp = new SpriteDrawable(new Sprite(t));
            this.setStyle(style);
        }
    }

    public AtaqueEspecial (int delay) {

        this.delay = delay;
        this.disponible = false;
        startTimer();

        this.texture = GestorAssets.getInstance().getTexture("botonespecial.png");

        this.boton = new botonEspecial(this.texture,GestorAssets.getInstance().getTexture("explo1.png"),GestorAssets.getInstance().getTexture("corazon.png"));
        this.boton.setSize(Gdx.graphics.getWidth() * 0.25f, Gdx.graphics.getHeight() * 0.25f);
        this.boton.setPosition(Gdx.graphics.getWidth() * -0.1f, Gdx.graphics.getWidth() * -0.05f);

        this.boton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if (disponible) {
                    startTimer();
                    activar();
                    disponible = false;
                    boton.setNoDisponible();
                }
            }

            ;
        });

        Timer timer = new Timer();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                tiempoRestante--;
                if (tiempoRestante <= 0) {
                    disponible = true;
                    boton.setDisponible();
                }
            }
        }
                , 0        //    (delay)
                , 1     //    (seconds)
        );
        timer.start();

        PantallaJuego.stage.addActor(this.boton);
        boton.setNoDisponible();
    }

    private void startTimer() {
        tiempoRestante = delay;
    }

    public abstract void activar ();

    public void destruirse() {

    }

    public void recargarAtaqueEspecial(){
        this.tiempoRestante = 0;
        this.disponible = true;
        this.boton.setDisponible();
    }
}


