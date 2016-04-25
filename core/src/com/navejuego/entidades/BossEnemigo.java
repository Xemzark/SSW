package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.patrones.MovementPattern;
import com.navejuego.entidades.powerups.PowerUpEntity;
import com.navejuego.pantallas.PantallaJuego;

import java.util.ArrayList;


import static com.navejuego.Constantes.PIXELS_METRE;

/**
 * Created by Elias on 09/04/2016.
 */

public class BossEnemigo extends EnemigoEntity {

    private ArrayList<MovementPattern> patternList = new ArrayList<MovementPattern>();


    public BossEnemigo (Stage stage, int startingX, int startingY,
                         PowerUpEntity powerUp, int enemyType){
        // Debe conocer su stage, su textura y su sprite
        super(stage, enemyType);
        enemyProperties = new EnemyType(enemyType);
        this.stage = stage;
        sprite = enemyProperties.sprite;
        hitbox = enemyProperties.hitbox;

        puntuacion = enemyProperties.puntuacion;
        cadenciaDisparo = enemyProperties.cadenciaDisparo;
        tiempoSiguienteDisparo = enemyProperties.tiempoSiguienteDisparo;
        vivo = enemyProperties.vivo;

        vida = enemyProperties.vida;
        escudo = enemyProperties.escudo;

        dañoColision = enemyProperties.dañoColision ; //Daño que le hace la nave al jugador si colisionan
        //Valores iniciales del Actor
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        //setSize(Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/8);
        setSize(PIXELS_METRE, PIXELS_METRE);
        this.hitbox.setSize(getWidth(), getHeight());



        // Valores aleatorios

        //REVISAR LUEGO!!!!

        /*this.posX = pos.nextInt(Gdx.graphics.getWidth() - 2*((int) getWidth())) + getWidth(); // Posición X aleatoria
        this.posY = Gdx.graphics.getHeight() + getHeight(); // Posición Y por encima de la pantalla
        setPosition(posX, posY);
        */

        this.hitbox.setPosition(startingX, startingY);
        //Fin valores iniciales del Actor

        //Set downwards movement at a speed of 150 per second
        patternList= enemyProperties.patternList;
    }
    /**
     * Constructor
     * Esta clase recibe una textura a asociarle y un vector de posición.
     * texture sprite a asociarle, gestionado por el assetManager
     * posicion vector de coordenadas x, y para inicializar la posición
     */


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
        //MoveTo(getX(), getY() - (100 * delta));
        generarDisparo(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Esto marca los límites de los bordes verdes del renderdebug
        // Debe corresponderse al tamaño
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
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
        animacionExplo();
        PantallaJuego.jugador.addPuntos(50);
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
/*
    public void cambiarPatron(ArrayList<MovementPattern> patternList){
        int index = patternList.size();
        if(vida)

    }

    @Override
    public Rectangle getHitbox(){
        return this.hitbox;
    }
    */
}