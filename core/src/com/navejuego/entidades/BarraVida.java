package com.navejuego.entidades;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.navejuego.GestorAssets;

/**
 * Created by beno_ on 11/04/2016.
 */
//tuto -->  https://www.youtube.com/watch?v=zrsUFTplNa4
public class BarraVida {

    private Sprite vidaBG;
    private Sprite vidaFG;
    private JugadorEntity owner;
    private final short buffer = 20;

    public BarraVida(JugadorEntity owner){

        this.vidaBG = new Sprite(GestorAssets.getInstance().getTexture("vidabgv2.png"));
        this.vidaFG = new Sprite(GestorAssets.getInstance().getTexture("vidafgv2.png"));
        this.owner = owner;

        this.vidaBG.setX(10);
        this.vidaBG.setY(200);

        this.vidaFG.setX(10);
        this.vidaFG.setY(200);
        //vidaBG.getHeight()/2
        this.vidaFG.setOrigin(0,80);

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
    }
}
