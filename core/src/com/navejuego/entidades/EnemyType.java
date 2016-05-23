package com.navejuego.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.patrones.HoritzontalMovement;
import com.navejuego.entidades.patrones.LinealMovement;
import com.navejuego.entidades.patrones.MovementPattern;
import com.navejuego.entidades.patrones.NullMovement;
import com.navejuego.entidades.patrones.SinusMovement;
import com.navejuego.entidades.patrones.TargetPlayerMovement;
import com.navejuego.entidades.patrones.ZigZagMovement;

import java.util.ArrayList;

/**
 * Created by Kevin on 24/04/2016.
 */

/**
 * EnemyType guarda todos los parametros basicos de los diferentes enemigos y bosses del juego.
 * Cuando se quiera acceder a datos predeterminados, se obtendrán de aqui.
 */
public class EnemyType {

    protected int puntuacion;
    protected int danoColision;

    protected float cadenciaDisparo;
    protected float tiempoSiguienteDisparo;
    protected boolean vivo;
    protected float vida;
    protected float escudo;
    protected int damage;

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
    protected float bulletSpeed;

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

            case 1: //enemigo morado 1 y sujeto de pruebas
                texture = GestorAssets.getInstance().getTexture("alien1.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 10;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 10; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 20;
                patternList.add(new ZigZagMovement(100,100,false,false));
                patternList.add(new LinealMovement(100,false));
                sizeX = 80.0f;
                sizeY = 90.0f;
                bulletSpeed = 200;
                bulletMovement = new TargetPlayerMovement(bulletSpeed);

                break;

            case 2: //enemigo morado 2, Kamikaze
                texture = GestorAssets.getInstance().getTexture("alien2.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 50;
                cadenciaDisparo = 100000000000f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                damage = 10;
                maxVida = 2;
                maxEscudo = 0;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 20; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 15;
                patternList.add(new LinealMovement(400,false));
                sizeX = 90.0f;
                sizeY = 100.0f;
                bulletMovement = new LinealMovement(250,false);

                break;

            case 3: //enemigo morado 3, Tanque
                texture = GestorAssets.getInstance().getTexture("alien3.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 350;
                cadenciaDisparo = 3f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                damage = 8;
                maxVida = 10;
                maxEscudo = 40;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 25; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 25;
                patternList.add(new SinusMovement(80,1,false));;
                sizeX = 100.0f;
                sizeY = 120.0f;
                bulletMovement = new LinealMovement(250,false);

                break;
            case 4: //enemigo morado 4
                texture = GestorAssets.getInstance().getTexture("alien4.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 210;
                cadenciaDisparo = 1.25f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 15;
                maxVida = 10;
                maxEscudo = 30;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 15; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 20;
                patternList.add(new LinealMovement(100,false));
                sizeX = 80.0f;
                sizeY = 90.0f;
                bulletMovement = new LinealMovement(250,false);

                break;

            /**
             *
             * FACCION AZUL
             *
             */
            case 5: //enemigo azul 1
                texture = GestorAssets.getInstance().getTexture("blueship1.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 210;
                cadenciaDisparo = 0.9f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 12;
                maxVida = 10;
                maxEscudo = 30;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 12; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 21;
                //movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                patternList.add(new ZigZagMovement(110,110,false,false));
                patternList.add(new LinealMovement(110,false));
                sizeX = 80.0f;
                sizeY = 90.0f;
                bulletSpeed = 200;
                bulletMovement = new TargetPlayerMovement(bulletSpeed);

                break;
            case 6: //enemigo azul 2 kamikaze
                texture = GestorAssets.getInstance().getTexture("blueship2.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 55;
                cadenciaDisparo = 100000000000f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                damage = 10;
                maxVida = 5;
                maxEscudo = 0;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 25; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 16;
                //movementPattern =  new LinealMovement(400, false);
                patternList.add(new LinealMovement(420,false));
                sizeX = 90.0f;
                sizeY = 100.0f;
                bulletMovement = new LinealMovement(250,false);

                break;
            case 7: //enemigo azul 3 tanque
                texture = GestorAssets.getInstance().getTexture("blueship3.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 360;
                cadenciaDisparo = 2.5f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                damage = 10;
                maxVida = 30;
                maxEscudo = 35;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 28; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 26;
                //movementPattern =  new LinealMovement(75, false);
                patternList.add(new ZigZagMovement(80,100,false,false));
                patternList.add(new LinealMovement(80,false));
                sizeX = 100.0f;
                sizeY = 120.0f;
                bulletMovement = new LinealMovement(250,false);

                break;
            case 8: //enemigo azul 4
                texture = GestorAssets.getInstance().getTexture("blueship4.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 210;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 17;
                maxVida = 10;
                maxEscudo = 30;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 17; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 21;
                //movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                patternList.add(new ZigZagMovement(110,100,false,false));
                patternList.add(new LinealMovement(110,false));
                sizeX = 80.0f;
                sizeY = 90.0f;
                bulletMovement = new LinealMovement(250,false);

                break;

            /**
             *
             *  FACCION NARANJA
             *
             */

            case 9: //enemigo naranja 1
                texture = GestorAssets.getInstance().getTexture("orangeship.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 230;
                cadenciaDisparo = 0.8f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 17;
                maxVida = 10;
                maxEscudo = 35;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 17; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 22;
                //movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                patternList.add(new ZigZagMovement(120,100,false,false));
                sizeX = 80.0f;
                sizeY = 90.0f;
                bulletSpeed = 200;
                bulletMovement = new TargetPlayerMovement(bulletSpeed);

                break;

            case 10: //enemigo naranja 2 kamikaze
                texture = GestorAssets.getInstance().getTexture("orangeship2.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 60;
                cadenciaDisparo = 100000000000f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                damage = 10;
                maxVida = 2;
                maxEscudo = 10;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 27; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 17;
                //movementPattern =  new LinealMovement(400, false);
                patternList.add(new LinealMovement(460,false));
                sizeX = 90.0f;
                sizeY = 100.0f;
                bulletMovement = new LinealMovement(250,false);

                break;

            case 11: //enemigo naranja 3 tanque
                texture = GestorAssets.getInstance().getTexture("orangeship3.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 380;
                cadenciaDisparo = 2f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                damage = 20;
                maxVida = 10;
                maxEscudo = 45;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 30; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 22;
                //movementPattern =  new LinealMovement(75, false);
                patternList.add(new ZigZagMovement(90,110,false,false));
                patternList.add(new LinealMovement(90,false));
                sizeX = 100.0f;
                sizeY = 120.0f;
                bulletMovement = new LinealMovement(250,false);

                break;

            case 12: //enemigo naranja 4
                texture = GestorAssets.getInstance().getTexture("smallorange.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 245;
                cadenciaDisparo = 0.8f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 20;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 15; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 22;
                //movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                patternList.add(new ZigZagMovement(120,120,false,false));
                patternList.add(new LinealMovement(120,false));
                sizeX = 80.0f;
                sizeY = 90.0f;
                bulletMovement = new LinealMovement(250,false);

                break;

            /**
             *
             *  FACCION VERDE
             *
             */

            case 13: //enemigo verde 1
                texture = GestorAssets.getInstance().getTexture("greenship1.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 250;
                cadenciaDisparo = 0.75f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 15;
                maxVida = 20;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 15; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 23;
                //movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                patternList.add(new ZigZagMovement(100,200,false,false));
                patternList.add(new LinealMovement(130,false));
                sizeX = 80.0f;
                sizeY = 90.0f;
                bulletSpeed = 200;
                bulletMovement = new TargetPlayerMovement(bulletSpeed);

                break;

            case 14: //enemigo verde 2 kamikaze
                texture = GestorAssets.getInstance().getTexture("greenship2.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 90;
                cadenciaDisparo = 100000000000f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                damage = 10;
                maxVida = 5;
                maxEscudo = 10;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 40; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 19;
                //movementPattern =  new LinealMovement(400, false);
                patternList.add(new LinealMovement(480,false));
                sizeX = 90.0f;
                sizeY = 100.0f;
                bulletMovement = new LinealMovement(250,false);

                break;

            case 15: //enemigo verde 3 tanque
                texture = GestorAssets.getInstance().getTexture("greenship3.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 500;
                cadenciaDisparo = 1.5f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                damage = 30;
                maxVida = 10;
                maxEscudo = 45;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 45; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 28;
                //movementPattern =  new LinealMovement(75, false);
                patternList.add(new ZigZagMovement(130, 80,false,false));
                sizeX = 100.0f;
                sizeY = 120.0f;
                bulletMovement = new LinealMovement(250,false);

                break;

            case 16: //enemigo verde 4
                texture = GestorAssets.getInstance().getTexture("greenship4.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 280;
                cadenciaDisparo = 0.75f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 10;
                maxVida = 10;
                maxEscudo = 25;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 15; //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 20;
               // movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                patternList.add(new ZigZagMovement(130,130,false,false));
                sizeX = 80.0f;
                sizeY = 90.0f;
                bulletMovement = new LinealMovement(250,false);

                break;

            case 21: //BOSS DEMO Morado
                texture = GestorAssets.getInstance().getTexture("boss1.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 1000;
                cadenciaDisparo = 0.5f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 10;
                maxVida = 300;
                maxEscudo = 0;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 1000; //Daño que le hace la nave al jugador si colisionan
                patternList.add(new NullMovement());
                patternList.add(new HoritzontalMovement(150));
                patternList.add(new HoritzontalMovement(200));
                sizeX = 150.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150,false);
                break;
            case 22: //BOSS PANTALLA 2 Azul
                texture = GestorAssets.getInstance().getTexture("boss2.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 2000;
                cadenciaDisparo = 100000000000f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 5000;
                maxVida = 500;
                maxEscudo = 0;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 50; //Daño que le hace la nave al jugador si colisionan
                patternList.add(new ZigZagMovement(100,100,false, true));
                patternList.add(new ZigZagMovement(150,150,true, true));
                patternList.add(new ZigZagMovement(250,250,false, true));
                sizeX = 150.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150,false);
                break;

            case 23: //BOSS PANTALLA 3 Naranja
                texture = GestorAssets.getInstance().getTexture("boss3.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 3000;
                cadenciaDisparo = 0.35f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 12;
                maxVida = 1000;
                maxEscudo = 0;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 1000; //Daño que le hace la nave al jugador si colisionan
                patternList.add(new HoritzontalMovement(50));
                patternList.add(new HoritzontalMovement(100));
                patternList.add(new HoritzontalMovement(150));
                patternList.add(new HoritzontalMovement(200));
                sizeX = 150.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(175,false);
                break;

            case 24: //BOSS PANTALLA 4 Verde
                texture = GestorAssets.getInstance().getTexture("boss4.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 4000;
                cadenciaDisparo = 0.6f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 10;
                maxVida = 1500;
                maxEscudo = 0;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 25; //Daño que le hace la nave al jugador si colisionan
                patternList.add(new ZigZagMovement(100,100,false, true));
                patternList.add(new ZigZagMovement(150,150,true, true));
                patternList.add(new ZigZagMovement(200,200,false, true));
                sizeX = 150.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(125, false);
                break;
        }
    }

    public static int getSpawnAmount(int type) {
        switch(type) {
            case 1: //Naves normales
            case 4:
                return 5;
            case 5:
            case 8:
                return 6;
            case 9:
            case 12:
                return 7;
            case 13:
            case 16:
                return 8;
            case 2: //Naves kamikaze
                return 6;
            case 6:
                return 7;
            case 10:
                return 8;
            case 14:
                return 9;
            case 3: //Naves tank
                return 3;
            case 7:
                return 4;
            case 11:
                return 5;
            case 15:
                return 6;
        }
        return -1;
    }

    public static long getSpawnDelay(int type) {
        switch(type) {
            case 1: //Naves normales
            case 4:
                return 1000;
            case 5:
            case 8:
                return 950;
            case 9:
            case 12:
                return 900;
            case 13:
            case 16:
                return 850;
            case 2: //Naves kamikaze
                return 500;
            case 6:
                return 450;
            case 10:
                return 400;
            case 14:
                return 350;
            case 3: //Naves tank
                return 1500;
            case 7:
                return 1450;
            case 11:
                return 1400;
            case 15:
                return 1350;
        }
        return -1;
    }
}
