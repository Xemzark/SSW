package com.navejuego.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.especiales.AtaqueEspecial;
import com.navejuego.entidades.especiales.EspecialMisiles;

/**
 * Created by beno_ on 05/05/2016.
 */
public class JugadorType{

    protected float cadenciaDisparo;
    protected float maxVida;
    protected float maxEscudo;
    protected float vida;
    protected float escudo;
    protected AtaqueEspecial especial;
    protected int damage;
    protected Texture textura;
    protected Texture texturaEscudo;

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
                this.especial = new EspecialMisiles(5, 20, 7, 40.0f, 50.0f);
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
                this.especial = new EspecialMisiles(5, 20, 7, 40.0f, 50.0f);
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
                this.especial = new EspecialMisiles(5, 20, 7, 35.0f, 30.0f);
                break;
            default:
                this.vida = 200;
                this.escudo = 200;
                this.maxEscudo = 200;
                this.maxVida = 200;
                this.texturaEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                this.textura = GestorAssets.getInstance().getTexture("medfrighter.png");
                this.cadenciaDisparo = 0.6f;
                this.especial = new EspecialMisiles(5, 20, 7, 40.0f, 50.0f);
                break;
        }
    }
}
