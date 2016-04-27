package com.navejuego.entidades.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.JugadorEntity;
import com.navejuego.pantallas.Pantalla;
import com.navejuego.pantallas.PantallaJuego;

import javax.naming.Context;

/**
 * Created by beno_ on 11/04/2016.
 */
//tuto -->  https://www.youtube.com/watch?v=zrsUFTplNa4
public class BarraEscudo extends Actor {

    private Sprite escudoBG;
    private Sprite escudoFG;
    private Texture escudo;
    float buffer = 22;

    public BarraEscudo(){

        this.escudoBG = new Sprite(GestorAssets.getInstance().getTexture("escudobg.png"));
        this.escudoFG = new Sprite(GestorAssets.getInstance().getTexture("escudofg.png"));
        this.escudo = GestorAssets.getInstance().getTexture("shieldbar.png");
        float spriteh = 0;

        spriteh = this.escudoBG.getHeight();

        int sw = 0;
        sw = Gdx.graphics.getWidth();
        int sh = 0;
        sh = Gdx.graphics.getHeight();

        this.escudoBG.setX(10);
        this.escudoBG.setY(20);

        this.escudoFG.setX(10);
        this.escudoFG.setY(20);
        //escudoBG.getHeight()/2
        this.escudoFG.setOrigin(0, 80);

    }

    public void update(){
        //this.escudoFG.setScale(owner.getVida() / 100, 1f);
        this.escudoFG.setScale(1f, (PantallaJuego.jugador.getEscudo() / PantallaJuego.jugador.getMaxEscudo()));
        /*this.escudoBG.setX(20);
        this.escudoBG.setY(40);

        this.escudoFG.setX(20);
        this.escudoFG.setY(40);*/
       /* this.escudoBG.setX(20);
        this.escudoBG.setY(40 + margenDeIcono);

        this.escudoFG.setX(20);
        this.escudoFG.setY(40 + margenDeIcono);*/
    }

    public void render(Batch batch){
        this.escudoBG.draw(batch);
        this.escudoFG.draw(batch);
        batch.draw(this.escudo,buffer, this.buffer);
    }
}
