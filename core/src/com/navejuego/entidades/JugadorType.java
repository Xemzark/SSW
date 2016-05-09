package com.navejuego.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.navejuego.GestorAssets;

/**
 * Created by beno_ on 05/05/2016.
 */
public class JugadorType{

    public float cadenciaDisparo;
    public float maxVida;
    public float maxEscudo;
    public float vida;
    public float escudo;
    public int damage;
    public Texture textura;
    public Texture texturaEscudo;

    public JugadorType(int type){

        //manager.load("medfrighter.png", Texture.class);
        //manager.load("ship3.png", Texture.class);
        //manager.load("destroyer.png", Texture.class);
        switch (type){
            case 0:
                this.vida = 100;
                this.escudo = 100;
                this.maxEscudo = 100;
                this.maxVida = 100;
                this.damage = 10;
                this.texturaEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                this.textura = GestorAssets.getInstance().getTexture("nave.png");
                this.cadenciaDisparo = 0.4f;
                break;
            case 1:
                this.vida = 70;
                this.escudo = 70;
                this.maxEscudo = 70;
                this.maxVida = 70;
                this.damage = 12;
                this.texturaEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                this.textura = GestorAssets.getInstance().getTexture("destroyer.png");
                this.cadenciaDisparo = 0.2f;
                break;
            case 2:
                this.vida = 40;
                this.escudo = 40;
                this.maxEscudo = 40;
                this.maxVida = 40;
                this.damage = 3;
                this.texturaEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                this.textura = GestorAssets.getInstance().getTexture("ship3.png");
                this.cadenciaDisparo = 0.1f;
                break;
            default:
                this.vida = 200;
                this.escudo = 200;
                this.maxEscudo = 200;
                this.maxVida = 200;
                this.texturaEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                this.textura = GestorAssets.getInstance().getTexture("medfrighter.png");
                this.cadenciaDisparo = 0.6f;

                break;
        }
    }
}
