package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.patrones.LinealMovement;
import com.navejuego.entidades.powerups.PowerUpEntity;
import com.navejuego.pantallas.PantallaJuego;

import java.util.ArrayList;
import java.util.Random;

import static com.navejuego.Constantes.PIXELS_METRE;

/**
 * Created by Andrés on 03/04/2016.
 */

/**
 * Clase EnemigoEntity
 * Esta clase sirve para generar los enemigos y asociarles las acciones que deben realizar
 * en el tiempo delta.
 */
public class EnemigoEntity extends GameObjectEntity {

    protected int puntuacion;
    protected int dañoColision;

    protected float cadenciaDisparo;
    protected float tiempoSiguienteDisparo;
    protected boolean vivo;
    protected EnemyType enemyProperties;

    // Variable para generar su posición aleatoria
    Random pos = new Random();

    // Variables para sus posiciones
    protected float posX;
    protected float posY;
    private Sprite spriteEscudo;
    private PowerUpEntity powerUp;
    private int probabilidadPowerUp; //Entre 0% y 100%

    /**
     * Constructor
     * Esta clase recibe una textura a asociarle y un vector de posición.
     * texture sprite a asociarle, gestionado por el assetManager
     * posicion vector de coordenadas x, y para inicializar la posición
     */

    public EnemigoEntity(Stage stage, int enemyType){
        // Debe conocer su stage, su textura y su sprite

        enemyProperties = new EnemyType(enemyType);
        this.stage = stage;
        texture = enemyProperties.texture;
        sprite = enemyProperties.sprite;
        spriteEscudo = enemyProperties.spriteEscudo;
        hitbox = enemyProperties.hitbox;
        spriteEscudo.setAlpha(0.7f);

        puntuacion = enemyProperties.puntuacion;
        cadenciaDisparo = enemyProperties.cadenciaDisparo;
        tiempoSiguienteDisparo = enemyProperties.tiempoSiguienteDisparo;
        vivo = enemyProperties.vivo;

        maxVida = enemyProperties.maxVida;
        vida = enemyProperties.vida;
        maxEscudo = enemyProperties.maxEscudo;
        escudo = enemyProperties.escudo;

        dañoColision = enemyProperties.dañoColision ; //Daño que le hace la nave al jugador si colisionan
        this.powerUp = powerUp;
        probabilidadPowerUp = enemyProperties.probabilidadPowerUp;

        //Valores iniciales del Actor
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        //setSize(Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/8);
        setSize(PIXELS_METRE, PIXELS_METRE);
        this.hitbox.setSize(getWidth(), getHeight());


        // Valores aleatorios


        posX = pos.nextInt(Gdx.graphics.getWidth() - 2*((int) getWidth())) + getWidth(); // Posición X aleatoria
        posY = Gdx.graphics.getHeight() + getHeight(); // Posición Y por encima de la pantalla
        setPosition(posX, posY);

        hitbox.setPosition(posX, posY);
        //Fin valores iniciales del Actor

        //Set downwards movement at a speed of 150 per second
        movementPattern = enemyProperties.movementPattern;
    }

    /**
     * Método act
     * Actualiza su posición en tiempo delta
     * @param delta
     */
    @Override
    public void act(float delta) {
        comprobarColisionJugador();
        eliminarseOutOfBounds();

        movementPattern.Move(this, delta);
        spriteEscudo.setPosition(getX(),getY());
        //MoveTo(getX(), getY() - (100 * delta));
        generarDisparo(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Esto marca los límites de los bordes verdes del renderdebug
        // Debe corresponderse al tamaño
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        spriteEscudo.draw(batch);
    }

    /**
     * TODO: Crear disparos (similar a la función en JugadorEntity)
     */
    /**
     * generarDisparo
     * Este método genera un disparo de la nave cada delta tiempo
     */
    protected void generarDisparo(float delta) {
        tiempoSiguienteDisparo += delta;
        if (tiempoSiguienteDisparo > cadenciaDisparo) {
            Texture bulletTextura = GestorAssets.getInstance().getTexture("bullet.png");
            com.navejuego.entidades.bullets.BulletEnemigo bullet = new com.navejuego.entidades.bullets.BulletEnemigo(this.stage, bulletTextura, new Vector2(getX() + (getWidth() / 2), getY()));
            bullet.setName("Bala Enemigo");
            this.stage.addActor(bullet);
            tiempoSiguienteDisparo = 0;
        }
    }

    /**
     * Esta función procesa el daño que recibe el enemigo en función de su escudo y de
     * su vida. Si se derriba, debe pasarle su puntuación a la nave del jugador y destruirse.
     * @param dmg
     * @param ignoraEscudo
     */
    public void recibirDmg(int dmg, boolean ignoraEscudo) {
        if (ignoraEscudo || escudo <= 0) {
            vida -= dmg;
        } else {
            int temp = dmg;
            dmg -= escudo;
            escudo -= temp;
            if (escudo < 0)
                escudo = 0;
            if (dmg > 0)
                vida -= dmg;
            spriteEscudo.setAlpha(Math.min(this.escudo/this.maxEscudo, 0.7f));
        }

        if (vida <= 0) {
            darPuntuacion();
            destruirse();
        }
    }

    public String getName(){
        return "Nave enemiga";
    }
    /**
     * TODO: Desaparecer/eliminar enemigo.
     */
    public void destruirse() {
        generarPowerUp();
        animacionExplo();
        PantallaJuego.jugador.addPuntos(puntuacion);
        GestorAssets.getInstance().getSound("explosion2.wav").play();
        this.remove();
        //Gdx.app.log("Enemy killed!", "");
    }

    public void animacionExplo()
    {
        ArrayList<Texture> explosionTextura = new ArrayList<Texture>();
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo1.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo2.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo3.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo4.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo5.png"));
        com.navejuego.Explosion explo = new com.navejuego.Explosion(this.stage,explosionTextura, new Vector2(getX(),getY()),1.0f);
        this.stage.addActor(explo);
    }

    /**
     * Si el enemigo se sale de la pantalla sin ser eliminado, se autodestruye
     */
    public void eliminarseOutOfBounds(){
        if ((this.getY()+getHeight()) < 0){
            this.remove();
        }
    }

    /**
     * Le pasa su puntuación al jugador para poder sumársela a su puntuación
     */
    public void darPuntuacion(){
        PantallaJuego.jugador.getPuntuacion().incrementarPuntuacion(this.puntuacion);
    }

    /**
     * Este método comprueba si ha colisionado con el jugador. De ser así, se autodestruye,
     * causándole daño.
     */
    public void comprobarColisionJugador() {
        if(this.getHitbox().overlaps(PantallaJuego.jugador.getHitbox())){
            PantallaJuego.jugador.recibirDmg(this.dañoColision, false);
            Gdx.app.log("HitColision! a jugador!", "");
            this.destruirse();
        }

    }

    /**
     * TODO: Tirar los dados para ver si genera o no genera el power up.
     */
    private void generarPowerUp() {
        Vector2 posicion = new Vector2(getX(), getY());
        Texture powerup;
        //Power up vida
       /* powerup = GestorAssets.getInstance().getTexture("powerup_vida.png");
        PowerUpVida vida = new PowerUpVida(stage, powerup, posicion );
        this.stage.addActor(vida);*/

        //Power up escudo
        powerup = GestorAssets.getInstance().getTexture("addShield.png");
        com.navejuego.entidades.powerups.PowerUpASPD pUp = new com.navejuego.entidades.powerups.PowerUpASPD(stage, powerup, posicion );
        this.stage.addActor(pUp);

    }

    @Override
    public Rectangle getHitbox(){
        return this.hitbox;
    }
}

