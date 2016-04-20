package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.GestorAssets;
import java.util.Timer;



/**
 * Created by beno_ on 20/04/2016.
 */
public class AtaqueEspecial extends GameObjectEntity{

    private boolean disponible;
    private int tiempoRestante;
    private Texture texture;
    private Sprite sprite;
    private Timer timer;

    public AtaqueEspecial(Stage stage){

        this.texture = GestorAssets.getInstance().getTexture("botonespecial.png");
        this.stage = stage;
        this.sprite = new Sprite(this.texture);
        this.sprite.setScale((float)0.2,(float)0.2);
        this.tiempoRestante = 0;
    }

    public void generarDisparo(float w, float h){
        //tiempoSiguienteDisparo += delta;
        //if (tiempoSiguienteDisparo > cadenciaDisparo) {
            if(tiempoRestante > 0){
                tiempoRestante --;
                return;
            }

            Texture bulletTextura = GestorAssets.getInstance().getTexture("bulletespecial.png");
            BulletEspecial bullet = new BulletEspecial(this.stage, bulletTextura, new Vector2(w, h));
            BulletEspecial bullet1 = new BulletEspecial(this.stage, bulletTextura, new Vector2(w+50, h-13));
            BulletEspecial bullet2 = new BulletEspecial(this.stage, bulletTextura, new Vector2(w-50, h -13));
            BulletEspecial bullet3 = new BulletEspecial(this.stage, bulletTextura, new Vector2(w+100, h -25));
            BulletEspecial bullet4 = new BulletEspecial(this.stage, bulletTextura, new Vector2(w-100, h -25));
            BulletEspecial bullet5 = new BulletEspecial(this.stage, bulletTextura, new Vector2(w+150, h -50));
            BulletEspecial bullet6 = new BulletEspecial(this.stage, bulletTextura, new Vector2(w-150, h-50));

            //this.stage.addActor(bullet);
            this.stage.addActor(bullet);
            this.stage.addActor(bullet1);
            this.stage.addActor(bullet2);
            this.stage.addActor(bullet3);
            this.stage.addActor(bullet4);
            this.stage.addActor(bullet5);
            this.stage.addActor(bullet6);

            //tiempoSiguienteDisparo = 0;
        //}
            disponible = false;
            tiempoRestante = 30;
    }

    public void recargarAtaqueEspecial(){
        this.tiempoRestante = 0;
    }

    public void destruirse(){

    }
    @Override
    public void draw(Batch batch, float parentAlpha) {

        double w = Gdx.graphics.getWidth()-Gdx.graphics.getWidth()*0.25;
        batch.draw(this.sprite,(float) w , 0);
    }

}


