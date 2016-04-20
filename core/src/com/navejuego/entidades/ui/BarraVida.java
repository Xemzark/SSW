package com.navejuego.entidades.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.navejuego.GestorAssets;
import com.navejuego.pantallas.PantallaJuego;

//TODO: Crear clase "Barra"

/**
 * Created by beno_ on 11/04/2016.
 */
//tuto -->  https://www.youtube.com/watch?v=zrsUFTplNa4
public class BarraVida extends Actor {

    private Sprite vidaBG;
    private Sprite vidaFG;
    private Texture corazon;
    private float margenDeIcono = 22;

    public BarraVida() {

        this.vidaBG = new Sprite(GestorAssets.getInstance().getTexture("vidabgv2.png"));
        this.vidaFG = new Sprite(GestorAssets.getInstance().getTexture("vidafgv2.png"));
        this.corazon = GestorAssets.getInstance().getTexture("corazon.png");

        //50% del tamaño de la pantalla
        int startingYPosition = Gdx.graphics.getHeight() / 2;

        this.vidaBG.setY(startingYPosition);

        this.vidaFG.setY(startingYPosition);

        //vidaBG.getHeight()/2
        this.vidaFG.setOrigin(getX(), getY());
    }

    public void update(){
        //TODO: Refactor (responsabilidad al jugador
        this.vidaFG.setScale(1f, (PantallaJuego.jugador.getVida() / PantallaJuego.jugador.getMaxVida()));
    }

    public void render(Batch batch){
        this.vidaBG.draw(batch);
        this.vidaFG.draw(batch);
        batch.draw(this.corazon, margenDeIcono, Gdx.graphics.getHeight() - this.vidaBG.getHeight());
    }
}



