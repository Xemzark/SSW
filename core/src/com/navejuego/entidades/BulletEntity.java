package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Andrés on 04/04/2016.
 */

/**
 * Clase BulletEntity
 * Esta clase sirve para generar los disparos del juego, tanto los de la nave del jugador como los
 * de los enemigos.
 */
public abstract class BulletEntity extends GameObjectEntity {

    protected float velocidad = 200.0f; //En pixeles/segundo
    protected int damage = 10; //Daño que aplica al golpear
    protected boolean ignoraEscudo = false;

    /**
     * Constructor
     * Esta clase recibe una textura a asociarle y un vector de posición.
     * @param texture sprite a asociarle, gestionado por el assetManager
     * @param posicion vector de coordenadas x, y para inicializar la posición
     */
    public BulletEntity(Stage stage, Texture texture, Vector2 posicion){
        this.stage = stage;
        this.texture = texture;
        this.hitbox = new Rectangle();
        this.sprite = new Sprite(this.texture);

        float x = posicion.x - (5);
        float y = posicion.y - 10;

        //Valores iniciales del Actor
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setPosition(x, y);
        setSize(10, 20);
        this.hitbox.setPosition(getX(), getY());
        this.hitbox.setSize(getWidth(), getHeight());
        //Fin de valores iniciales del Actor
    }

    /**
     * Este método se encarga de actualizar la posición del disparo cada delta tiempo,
     * eliminarlo si se ha salido de pantalla y comprobar si ha colisionado con algún actor
     * @param delta tiempo desde el último frame (*delta leer como *segundo)
     */
    @Override
    public void act(float delta) {
        eliminarDisparo();

        movimiento(delta);
        comprobarColision();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    /**
     * TODO: Metodo de auto-destruccion
     */
    @Override
    public void destruirse() {
        this.remove();
        Gdx.app.log("Bullet killed!", "");
    }

    /**
     * Ejecuta la logica de colision y aplica sus efectos.
     */
    protected abstract void comprobarColision();

    /**
     * Mueve el proyectil en la direccion que corresponde.
     */
    protected abstract void movimiento(float delta);

    /**
     * eliminarDisparo
     * Este método elimina los disparos que se salen por arriba de la pantalla, tanto
     * por arriba como por abajo, aplicable tanto a disparos de la nave como a los
     * disparos enemigos.
     */
    protected void eliminarDisparo(){
        if(this.getY() > Gdx.graphics.getHeight() || (this.getY()+this.getHeight()) < 0){
            remove();
        }
    }

    @Override
    public Rectangle getHitbox(){
        return this.hitbox;
    }

    public void setIgnoraEscudo(boolean ignorar) {
        ignoraEscudo = ignorar;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
