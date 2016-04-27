package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.patrones.HoritzontalMovement;
import com.navejuego.entidades.patrones.LinealMovement;
import com.navejuego.entidades.patrones.MovementPattern;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.entidades.patrones.NullMovement;
import java.util.ArrayList;





import java.util.Random;

/**
 * Created by Kevin on 24/04/2016.
 */

/**
 * EnemyType guarda todos los parametros basicos de los diferentes enemigos y bosses del juego.
 * Cuando se quiera acceder a datos predeterminados, se obtendrán de aqui.
 */
public class EnemyType {

    protected int puntuacion;
    protected int dañoColision;

    protected float cadenciaDisparo;
    protected float tiempoSiguienteDisparo;
    protected boolean vivo;
    protected float vida;
    protected float escudo;

    protected float maxVida;
    protected float maxEscudo;


    protected int probabilidadPowerUp; //Entre 0% y 100%
    protected Texture texture;
    protected Texture textureEscudo;
    protected Sprite sprite;
    protected Sprite spriteEscudo;

    protected Rectangle hitbox;

    protected MovementPattern movementPattern;
    protected ArrayList<MovementPattern> patternList = new ArrayList<MovementPattern>();

    /**
     * Mediante un swithc/case, se selecciona el tipo de enemigo deseado y se obtienen sus datos
     * @param enemyType
     */
    public EnemyType(int enemyType) {

        switch(enemyType){
            case 1: //ENEMIGO DEMO
                texture = GestorAssets.getInstance().getTexture("alien.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Rectangle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 100;
                movementPattern =  new LinealMovement(150, false);
                break;

            case 2: //BOSS DEMO
                texture = GestorAssets.getInstance().getTexture("goku.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Rectangle();
                puntuacion = 20000;
                cadenciaDisparo = 0.5f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 100;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = 1000; //Daño que le hace la nave al jugador si colisionan
                patternList.add(new NullMovement());
                patternList.add(new HoritzontalMovement(150));
                break;
        }
    }
}
