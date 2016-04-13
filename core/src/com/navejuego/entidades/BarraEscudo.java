package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.navejuego.GestorAssets;

import javax.naming.Context;

/**
 * Created by beno_ on 11/04/2016.
 */
//tuto -->  https://www.youtube.com/watch?v=zrsUFTplNa4
public class BarraEscudo {

    private Sprite vidaBG;
    private Sprite vidaFG;
    private JugadorEntity owner;
    private Texture corazon;
    float buffer = 22;

    public BarraEscudo(JugadorEntity owner){

        this.vidaBG = new Sprite(GestorAssets.getInstance().getTexture("escudobg.png"));
        this.vidaFG = new Sprite(GestorAssets.getInstance().getTexture("escudofg.png"));
        this.owner = owner;
        this.corazon = GestorAssets.getInstance().getTexture("shieldbar.png");
        float spriteh = 0;

        spriteh = this.vidaBG.getHeight();

        int sw = 0;
        sw = Gdx.graphics.getWidth();
        int sh = 0;
        sh = Gdx.graphics.getHeight();

        this.vidaBG.setX(10);
        this.vidaBG.setY(20);

        this.vidaFG.setX(10);
        this.vidaFG.setY(20);
        //vidaBG.getHeight()/2
        this.vidaFG.setOrigin(0, 80);

    }

    public void update(){
        //this.vidaFG.setScale(owner.getVida() / 100, 1f);
        this.vidaFG.setScale(1f,(this.owner.getVida()/(float)this.owner.getMaxVida()));
        /*this.vidaBG.setX(20);
        this.vidaBG.setY(40);

        this.vidaFG.setX(20);
        this.vidaFG.setY(40);*/
       /* this.vidaBG.setX(20);
        this.vidaBG.setY(40 + buffer);

        this.vidaFG.setX(20);
        this.vidaFG.setY(40 + buffer);*/
    }

    public void render(Batch batch){
        this.vidaBG.draw(batch);
        this.vidaFG.draw(batch);
        batch.draw(this.corazon,buffer, this.buffer);
    }
}
