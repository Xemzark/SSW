package com.navejuego.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.especiales.AtaqueEspecial;
import com.navejuego.entidades.especiales.EspecialMasa;
import com.navejuego.entidades.especiales.EspecialMisiles;
import com.navejuego.entidades.especiales.EspecialRegeneracion;

/**
 * En esta clase se representan los distintos tipos de jugador, en este caso naves que el
 * jugador puede seleccionar
 */
public class JugadorType{

    /**
     * Emumerable de las diferentes pasivas de las naves
     */
    public enum PasivasNave {
        NONE,
        SHIELD_ON_KILL,
        BLINK_TIME_ON_HIT,
        SPEEDUP_ON_KILL,
        DUAL_SHOTS
    }

    /**
     * cadencia de disparo
     */
    public float cadenciaDisparo;

    /**
     * Máximo valor de vida
     */
    public float maxVida;

    /**
     * Máximo valor de escudo
     */
    public float maxEscudo;

    /**
     *  Valor de vida con la que empieza la nave del jugador
     */
    public float vida;

    /**
     * Valor de escudo con el que empieza la nave del juagador
     */
    public float escudo;

    /**
     * Integro que representa el ataque especial asociado a una nave
     */
    public int especial;

    /**
     * Daño realizado con cada disparo
     */
    public int damage;
    /**
     * Definicion para el ataque especial que se muestra en el garaje
     */
    public String especialDefinicion;
    /**
     * Definicion para la pasiva que se muestra en el garaje
     */
    public String pasivaDefinicion;

    /**
     * Textura de la nave
     */
    public Texture textura;

    /**
     * Textura del escudo de la nave
     */
    public Texture texturaEscudo;

    /**
     * Pasivas de la nave
     */
    public PasivasNave pasiva;

    /**
     * Método encargado de devolver una nueva instancia de ataque especial
     * @param especial integro que representa el ataque especial
     * @return Nueva instancia AtaqueEspecial
     */
    public AtaqueEspecial getEspecial(int especial){

        AtaqueEspecial aEspecial;

        switch (especial){
            case 0:
                aEspecial = new EspecialMisiles(10, 20, 7, 40.0f, 50.0f);
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
        switch (type){
            case 0: //Balanced
                this.vida = 100;
                this.escudo = 100;
                this.maxEscudo = 100;
                this.maxVida = 100;
                this.damage = 11;
                this.texturaEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                this.textura = GestorAssets.getInstance().getTexture("nave1.png");
                this.cadenciaDisparo = 0.4f; //DPS: 27.5
                this.especial = 0; //oleada de misiles, poco CD
                this.pasiva = PasivasNave.BLINK_TIME_ON_HIT;
                this.especialDefinicion= "Lanzamiento de cohetes";
                this.pasivaDefinicion= "Invulnerabilidad de 1 segundo \ntras ser golpeado";
                break;
            case 1: //Tank
                this.vida = 150;
                this.escudo = 120;
                this.maxEscudo = 120;
                this.maxVida = 150;
                this.damage = 18;
                this.texturaEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                this.textura = GestorAssets.getInstance().getTexture("nave2.png");
                this.cadenciaDisparo = 1f; //DPS: 36 (18 x 2 gracias a dual shots)
                this.especial = 1; //matar naves
                this.pasiva = PasivasNave.DUAL_SHOTS;
                this.especialDefinicion= "Destruye los enemigos";
                this.pasivaDefinicion= "Dispara 2 proyectiles \npor los laterales";
                break;
            case 2: //Risky
                this.vida = 40;
                this.escudo = 30;
                this.maxEscudo = 60;
                this.maxVida = 40;
                this.damage = 4;
                this.texturaEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                this.textura = GestorAssets.getInstance().getTexture("nave3.png");
                this.cadenciaDisparo = 0.1f; //DPS: 40
                this.especial = 2;
                this.pasiva = PasivasNave.SHIELD_ON_KILL;
                this.especialDefinicion= "Recupera el escudo";
                this.pasivaDefinicion= "Matar un enemigo te \nda 1 punto de escudo";
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
