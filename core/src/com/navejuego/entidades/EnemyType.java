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
                danoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
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
                maxVida = 1;
                maxEscudo = 0;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = (20); //Daño que le hace la nave al jugador si colisionan
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
                cadenciaDisparo = 4f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                damage = 10;
                maxVida = 40;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 25;
                patternList.add(new SinusMovement(100,1,false));;
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
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 12;
                maxVida = 10;
                maxEscudo = 30;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
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
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 10;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 20;
                //movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                patternList.add(new ZigZagMovement(100,100,false,false));
                patternList.add(new LinealMovement(100,false));
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
                puntuacion = 50;
                cadenciaDisparo = 100000000000f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                damage = 10;
                maxVida = 1;
                maxEscudo = 0;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = (20); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 15;
                //movementPattern =  new LinealMovement(400, false);
                patternList.add(new LinealMovement(400,false));
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
                puntuacion = 350;
                cadenciaDisparo = 4f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                damage = 10;
                maxVida = 40;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 25;
                //movementPattern =  new LinealMovement(75, false);
                patternList.add(new ZigZagMovement(100,100,false,false));
                patternList.add(new LinealMovement(100,false));
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
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 10;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 20;
                //movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                patternList.add(new ZigZagMovement(100,100,false,false));
                patternList.add(new LinealMovement(100,false));
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
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 10;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 20;
                //movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                patternList.add(new ZigZagMovement(100,100,false,false));
                patternList.add(new LinealMovement(100,false));
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
                puntuacion = 50;
                cadenciaDisparo = 100000000000f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                damage = 10;
                maxVida = 1;
                maxEscudo = 0;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = (20); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 15;
                //movementPattern =  new LinealMovement(400, false);
                patternList.add(new LinealMovement(400,false));
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
                puntuacion = 350;
                cadenciaDisparo = 4f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                damage = 10;
                maxVida = 40;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 20;
                //movementPattern =  new LinealMovement(75, false);
                patternList.add(new ZigZagMovement(100,100,false,false));
                patternList.add(new LinealMovement(100,false));
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
                puntuacion = 200;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 10;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 20;
                //movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                patternList.add(new ZigZagMovement(100,100,false,false));
                patternList.add(new LinealMovement(100,false));
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
                puntuacion = 400;
                cadenciaDisparo = 1f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 10;
                maxVida = 30;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 20;
                //movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                patternList.add(new ZigZagMovement(100,100,false,false));
                patternList.add(new LinealMovement(100,false));
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
                puntuacion = 150;
                cadenciaDisparo = 100000000000f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                damage = 10;
                maxVida = 1;
                maxEscudo = 0;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = (40); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 15;
                //movementPattern =  new LinealMovement(400, false);
                patternList.add(new LinealMovement(400,false));
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
                puntuacion = 800;
                cadenciaDisparo = 4f;
                tiempoSiguienteDisparo = 0;
                vivo = true;
                damage = 10;
                maxVida = 60;
                maxEscudo = 30;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 25;
                //movementPattern =  new LinealMovement(75, false);
                patternList.add(new ZigZagMovement(100,100,false,false));
                patternList.add(new LinealMovement(100,false));
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
                puntuacion = 800;
                cadenciaDisparo = 0.5f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 10;
                maxVida = 10;
                maxEscudo = 20;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = ((int)vida/2); //Daño que le hace la nave al jugador si colisionan
                probabilidadPowerUp = 20;
               // movementPattern =  new TargetMovement(200, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                patternList.add(new ZigZagMovement(100,100,false,false));
                patternList.add(new LinealMovement(100,false));
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
                puntuacion = 20000;
                cadenciaDisparo = 0.5f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 10;
                maxVida = 200;
                maxEscudo = 0;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 1000; //Daño que le hace la nave al jugador si colisionan
                patternList.add(new NullMovement());
                patternList.add(new HoritzontalMovement(150));
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
                puntuacion = 20000;
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
                puntuacion = 20000;
                cadenciaDisparo = 0.5f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 10;
                maxVida = 200;
                maxEscudo = 0;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 1000; //Daño que le hace la nave al jugador si colisionan
                patternList.add(new NullMovement());
                patternList.add(new HoritzontalMovement(150));
                sizeX = 150.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150,false);
                break;

            case 24: //BOSS PANTALLA 4 Verde
                texture = GestorAssets.getInstance().getTexture("boss4.png");
                sprite = new Sprite(texture);
                textureEscudo = GestorAssets.getInstance().getTexture("escudoEnemigo.png");
                spriteEscudo = new Sprite(textureEscudo);
                hitbox = new Circle();
                puntuacion = 20000;
                cadenciaDisparo = 0.5f;
                tiempoSiguienteDisparo = 0f;
                vivo = true;
                damage = 10;
                maxVida = 500;
                maxEscudo = 0;
                vida = maxVida;
                escudo = maxEscudo;
                danoColision = 1000; //Daño que le hace la nave al jugador si colisionan
                patternList.add(new NullMovement());
                patternList.add(new HoritzontalMovement(150));
                patternList.add(new NullMovement());
                patternList.add(new HoritzontalMovement(150));
                sizeX = 150.0f;
                sizeY = 130.0f;
                bulletMovement = new LinealMovement(150,false);
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
                return 7;
            case 6:
                return 8;
            case 10:
                return 9;
            case 14:
                return 10;
            case 3: //Naves tank
                return 4;
            case 7:
                return 5;
            case 11:
                return 6;
            case 15:
                return 7;
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
                return 900;
            case 9:
            case 12:
                return 800;
            case 13:
            case 16:
                return 700;
            case 2: //Naves kamikaze
                return 500;
            case 6:
                return 400;
            case 10:
                return 300;
            case 14:
                return 200;
            case 3: //Naves tank
                return 1500;
            case 7:
                return 1400;
            case 11:
                return 1300;
            case 15:
                return 1200;
        }
        return -1;
    }
}
