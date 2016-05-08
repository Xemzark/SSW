package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Timer;
import com.navejuego.Constantes;
import com.navejuego.GestorAssets;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by beno_ on 20/04/2016.
 */
public class AtaqueEspecial extends GameObjectEntity{

    private boolean disponible = true;
    private int tiempoRestante = 5;
    private static final int delay = 5;
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

    public AtaqueEspecial(){

        this.texture = GestorAssets.getInstance().getTexture("botonespecial.png");

        this.boton = new botonEspecial(this.texture,GestorAssets.getInstance().getTexture("explo1.png"),GestorAssets.getInstance().getTexture("corazon.png"));
        this.boton.sizeBy(2, 2);
        this.boton.setPosition((float) (Gdx.graphics.getWidth() * 0.75), 0.0f);

        this.boton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if (disponible) {
                    generarDisparo(PantallaJuego.jugador.getX(), PantallaJuego.jugador.getY());
                    disponible = false;
                    tiempoRestante = delay;
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
    }

    public void generarDisparo(float w, float h){

            Texture bulletTextura = GestorAssets.getInstance().getTexture("bulletespecial.png");
            com.navejuego.entidades.bullets.BulletEspecial bullet = new com.navejuego.entidades.bullets.BulletEspecial(bulletTextura, new Vector2(w, h));
            bullet.setSize(bullet.getWidth() * Constantes.resizeWidth, bullet.getHeight() * Constantes.resizeHeight);

        com.navejuego.entidades.bullets.BulletEspecial bullet1 = new com.navejuego.entidades.bullets.BulletEspecial(bulletTextura, new Vector2(w+50, h-13));
        bullet1.setSize(bullet1.getWidth() * Constantes.resizeWidth, bullet1.getHeight() * Constantes.resizeHeight);

        com.navejuego.entidades.bullets.BulletEspecial bullet2 = new com.navejuego.entidades.bullets.BulletEspecial(bulletTextura, new Vector2(w-50, h -13));
        bullet2.setSize(bullet2.getWidth() * Constantes.resizeWidth, bullet2.getHeight() * Constantes.resizeHeight);

        com.navejuego.entidades.bullets.BulletEspecial bullet3 = new com.navejuego.entidades.bullets.BulletEspecial(bulletTextura, new Vector2(w+100, h -25));
        bullet3.setSize(bullet3.getWidth() * Constantes.resizeWidth, bullet3.getHeight() * Constantes.resizeHeight);

        com.navejuego.entidades.bullets.BulletEspecial bullet4 = new com.navejuego.entidades.bullets.BulletEspecial(bulletTextura, new Vector2(w-100, h -25));
        bullet4.setSize(bullet4.getWidth() * Constantes.resizeWidth, bullet4.getHeight() * Constantes.resizeHeight);

        com.navejuego.entidades.bullets.BulletEspecial bullet5 = new com.navejuego.entidades.bullets.BulletEspecial(bulletTextura, new Vector2(w+150, h -50));
        bullet5.setSize(bullet5.getWidth() * Constantes.resizeWidth, bullet5.getHeight() * Constantes.resizeHeight);

        com.navejuego.entidades.bullets.BulletEspecial bullet6 = new com.navejuego.entidades.bullets.BulletEspecial(bulletTextura, new Vector2(w-150, h-50));
        bullet6.setSize(bullet6.getWidth() * Constantes.resizeWidth, bullet6.getHeight() * Constantes.resizeHeight);

            PantallaJuego.stage.addActor(bullet);
            PantallaJuego.stage.addActor(bullet1);
            PantallaJuego.stage.addActor(bullet2);
            PantallaJuego.stage.addActor(bullet3);
            PantallaJuego.stage.addActor(bullet4);
            PantallaJuego.stage.addActor(bullet5);
            PantallaJuego.stage.addActor(bullet6);
    }

    public void recargarAtaqueEspecial(){

        this.tiempoRestante = 0;
        this.disponible = true;
        this.boton.setDisponible();
    }

    public void destruirse(){

    }
    @Override
    public void draw(Batch batch, float parentAlpha) {

        double w = Gdx.graphics.getWidth()-Gdx.graphics.getWidth()*0.25;
        //batch.draw(this.sprite,(float) w , 0);
        PantallaJuego.stage.addActor(this.boton);

    }

}


