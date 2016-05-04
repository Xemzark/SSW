package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.patrones.HoritzontalMovement;
import com.navejuego.entidades.patrones.LinealMovement;
import com.navejuego.entidades.patrones.MovementPattern;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.entidades.patrones.NullMovement;
import com.navejuego.entidades.patrones.TargetMovement;

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

    protected float sizeX;
    protected float sizeY;

    protected int probabilidadPowerUp; //Entre 0% y 100%
    protected Texture texture;
    protected Texture textureEscudo;
    protected Sprite sprite;
    protected Sprite spriteEscudo;

    protected Circle hitbox;

    protected MovementPattern movementPattern;
    protected ArrayList<MovementPattern> patternList = new ArrayList<MovementPattern>();
    protected MovementPattern bulletMovement;

    /**
     * Mediante un swithc/case, se selecciona el tipo de enemigo deseado y se obtienen sus datos
     * @param enemyType
     */
    public EnemyType(int enemyType) {

        switch(enemyType){

            /**
             *
             * FACCION MORADA
             *
             */

            case 1: //ENEMIGO DEMO
                texture = GestorAssets.getInstance().getTexture("alien1.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150, false);
                break;

            case 2: //Kamikaze
                texture = GestorAssets.getInstance().getTexture("alien4.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 50;
                cadenciaDisparo = 100000000000f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                maxVida = 1;
                maxEscudo = 0;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = (20); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new LinealMovement(400, false);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = null;
                break;

            case 3: //Tanque
                texture = GestorAssets.getInstance().getTexture("alien3.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 350;
                cadenciaDisparo = 4f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                maxVida = 40;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new LinealMovement(75, false);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = null;
                break;
            case 4: //enemigo morado 4
                texture = GestorAssets.getInstance().getTexture("alien4.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150, false);
                break;

            /**
             *
             * FACCION AZUL
             *
             */
            case 5: //enemigo azul 1
                texture = GestorAssets.getInstance().getTexture("blueship1.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150, false);
                break;
            case 6: //enemigo azul 2
                texture = GestorAssets.getInstance().getTexture("blueship2.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150, false);
                break;
            case 7: //enemigo azul 3
                texture = GestorAssets.getInstance().getTexture("blueship3.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150, false);
                break;
            case 8: //enemigo azul 4
                texture = GestorAssets.getInstance().getTexture("blueship4.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150, false);
                break;

            /**
             *
             *  FACCION NARANJA
             *
             */

            case 9: //enemigo naranja 1
                texture = GestorAssets.getInstance().getTexture("orangeship.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150, false);
                break;

            case 10: //enemigo naranja 2
                texture = GestorAssets.getInstance().getTexture("orangeship2.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150, false);
                break;

            case 11: //enemigo naranja 3
                texture = GestorAssets.getInstance().getTexture("orangeship3.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150, false);
                break;

            case 12: //enemigo naranja 4
                texture = GestorAssets.getInstance().getTexture("smallorange.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150, false);
                break;

            /**
             *
             *  FACCION VERDE
             *
             */

            case 13: //enemigo verde 1
                texture = GestorAssets.getInstance().getTexture("greenship1.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150, false);
                break;

            case 14: //enemigo verde 2
                texture = GestorAssets.getInstance().getTexture("greenship2.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150, false);
                break;

            case 15: //enemigo verde 3
                texture = GestorAssets.getInstance().getTexture("greenship3.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150, false);
                break;

            case 16: //enemigo verde 1
                texture = GestorAssets.getInstance().getTexture("greenship.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoNave.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                dañoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 10;
                movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                sizeX = 110.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150, false);
                break;

            case 20: //BOSS DEMO
                texture = GestorAssets.getInstance().getTexture("goku.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
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
                sizeX = 150.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150,false);
                break;
        }
    }
}
