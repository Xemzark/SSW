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

    public enum PasivasNave {
        NONE,
        SHIELD_ON_KILL,
        BLINK_TIME_ON_HIT,
        SPEEDUP_ON_KILL,
        DUAL_SHOTS
    }

    public float cadenciaDisparo;
    public float maxVida;
    public float maxEscudo;
    public float vida;
    public float escudo;
    public int especial;
    public int damage;
    public Texture textura;
    public Texture texturaEscudo;
    public PasivasNave pasiva;

    public AtaqueEspecial getEspecial(int especial){

        AtaqueEspecial aEspecial;

        switch (especial){
            case 0:
                aEspecial = new EspecialMisiles(10, 25, 7, 40.0f, 50.0f);
                break;
            case 1:
                aEspecial = new EspecialMasa(25);
                break;
            case 2:
                aEspecial = new EspecialRegeneracion(30);
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
            case 0: //Balanced
                this.vida = 100;
                this.escudo = 100;
                this.maxEscudo = 100;
                this.maxVida = 100;
                this.damage = 9;
                this.texturaEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                this.textura = GestorAssets.getInstance().getTexture("nave1.png");
                this.cadenciaDisparo = 0.3f; //DPS: 30
                this.especial = 0;
                this.pasiva = PasivasNave.BLINK_TIME_ON_HIT;
                break;
            case 1: //Tank
                this.vida = 150;
                this.escudo = 120;
                this.maxEscudo = 120;
                this.maxVida = 150;
                this.damage = 20;
                this.texturaEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                this.textura = GestorAssets.getInstance().getTexture("nave2.png");
                this.cadenciaDisparo = 1f; //DPS: 20
                this.especial = 1;
                this.pasiva = PasivasNave.DUAL_SHOTS; //DPS: 40 (si los dos disparos dan al mismo enemigo)
                break;
            case 2: //Risky
                this.vida = 50;
                this.escudo = 25;
                this.maxEscudo = 60;
                this.maxVida = 40;
                this.damage = 4;
                this.texturaEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                this.textura = GestorAssets.getInstance().getTexture("nave3.png");
                this.cadenciaDisparo = 0.1f; //DPS: 40
                this.especial = 2;
                this.pasiva = PasivasNave.SHIELD_ON_KILL;
                break;
            default:
                this.vida = 1;
                this.escudo = 1;
                this.maxEscudo = 1;
                this.maxVida = 1;
                this.damage = 1;
                this.texturaEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                this.textura = GestorAssets.getInstance().getTexture("nave1.png");
                this.cadenciaDisparo = 1f;
                this.especial = 0;
                this.pasiva = PasivasNave.NONE;
                break;
        }
    }
}
