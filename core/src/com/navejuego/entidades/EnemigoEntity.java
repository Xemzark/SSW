package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.GestorAssets;

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

    protected float cadenciaDisparo;

    protected float tiempoSiguienteDisparo;
    private boolean vivo;
    // Variable para generar su posición aleatoria
    Random pos = new Random();

    // Variables para sus posiciones
    private float posX;
    private float posY;

    private Rectangle enemyHitbox;
    private PowerUpEntity powerUp;
    private int probabilidadPowerUp; //Entre 0% y 100%

    /**
     * Constructor
     * Esta clase recibe una textura a asociarle y un vector de posición.
     * texture sprite a asociarle, gestionado por el assetManager
     * posicion vector de coordenadas x, y para inicializar la posición
     */
    public EnemigoEntity(Stage stage){
        // Debe conocer su stage, su textura y su sprite

        this.stage = stage;
        this.texture = GestorAssets.getInstance().getTexture("addShield.png");
        this.sprite = new Sprite(this.texture);
        this.enemyHitbox = new Rectangle();
        this.vivo = true;

        //Valores iniciales del Actor
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        //setSize(Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/8);
        setSize(PIXELS_METRE, PIXELS_METRE);

        this.enemyHitbox.setSize(this.sprite.getWidth(), this.sprite.getHeight());

        // Valores aleatorios
        this.posX = pos.nextInt(Gdx.graphics.getWidth()) - getWidth(); // Posición X aleatoria
        this.posY = Gdx.graphics.getHeight() + getHeight(); // Posición Y por encima de la pantalla
        setPosition(posX, posY);
        this.enemyHitbox.setPosition(posX, posY);
        //Fin valores iniciales del Actor
    }

    /**
     * Método act
     * Actualiza su posición en tiempo delta
     * @param delta
     */
    @Override
    public void act(float delta) {
        setPosition(getX(), getY() - (100 * delta));
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
    protected void generarDisparo(float delta) {

    }

    /**
     * TODO: Recibir daño.
     */
    protected void recibirDmg(int dmg, boolean ignoraEscudo) {

    }

    public String getName(){
        return "Nave enemiga";
    }
    /**
     * TODO: Desaparecer/eliminar enemigo.
     */
    public void destruirse() {
        generarPowerUp();
        this.remove();
        Gdx.app.log("Enemy killed!", "");
    }

    /**
     * TODO: Comprueba una colisión con el jugador. Si se da, se auto-destruye y le causa daño.
     */
    public void comprobarColisionJugador() {

    }

    /**
     * TODO: Tirar los dados para ver si genera o no genera el power up.
     */
    private void generarPowerUp() {

    }

    @Override
    public Rectangle getHitbox(){
        return this.enemyHitbox;
    }
}

