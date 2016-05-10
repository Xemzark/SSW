package com.navejuego.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.especiales.AtaqueEspecial;
import com.navejuego.entidades.especiales.EspecialMasa;
import com.navejuego.entidades.especiales.EspecialMisiles;
import com.navejuego.entidades.especiales.EspecialRegeneracion;

/**
 * Created by beno_ on 05/05/2016.
 */
public class JugadorType{

    public float cadenciaDisparo;
    public float maxVida;
    public float maxEscudo;
    public float vida;
    public float escudo;
    public int especial;
    public int damage;
    public Texture textura;
    public Texture texturaEscudo;


    public AtaqueEspecial getEspecial(int especial){

        AtaqueEspecial aEspecial;

        switch (especial){
            case 0:
                aEspecial = new EspecialMisiles(5, 20, 7, 40.0f, 50.0f);
                break;
            case 1:
                aEspecial = new EspecialMasa(5);
                break;
            case 2:
                aEspecial = new EspecialRegeneracion(3);
                break;
            default:
                aEspecial = new EspecialMisiles(5, 20, 7, 40.0f, 50.0f);
                break;
        }
        return aEspecial;
    }

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
                this.cadenciaDisparo = 0.3f;
                this.especial = 0;
                break;
            case 1:
                this.vida = 120;
                this.escudo = 120;
                this.maxEscudo = 120;
                this.maxVida = 120;
                this.damage = 12;
                this.texturaEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                this.textura = GestorAssets.getInstance().getTexture("destroyer.png");
                this.cadenciaDisparo = 0.4f;
                this.especial = 1;
                break;
            case 2:
                this.vida = 150;
                this.escudo = 150;
                this.maxEscudo = 150;
                this.maxVida = 150;
                this.damage = 15;
                this.texturaEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                this.textura = GestorAssets.getInstance().getTexture("ship3.png");
                this.cadenciaDisparo = 0.5f;
                this.especial = 2;
                break;
            default:
                this.vida = 200;
                this.escudo = 200;
                this.maxEscudo = 200;
                this.maxVida = 200;
                this.texturaEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                this.textura = GestorAssets.getInstance().getTexture("medfrighter.png");
                this.cadenciaDisparo = 0.6f;
                this.especial = 0;
                break;
        }
    }
}
