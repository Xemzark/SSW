package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;


import static com.navejuego.Constantes.PIXELS_METRE;

/**
 * Created by Andrés on 04/04/2016.
 */

/*
PARA HACER EL BULLET
Are you using scene2d? Then just create an Actor bullet, set its position and add it to the Stage.
Are you using box2d? Then create the bullet body, fixture, set its position and add it to the box2d world.
 */

/**
 * Clase BulletEntity
 * Esta clase sirve para generar los disparos del juego, tanto los de la nave del jugador como los
 * de los enemigos.
 */
public abstract class BulletEntity extends GameObjectEntity {

    protected float velocidad = 900.0f; //En pixeles/segundo
    public Rectangle bulletHitbox;
    protected int damage; //Daño que aplica al golpear

    /**
     * Constructor
     * Esta clase recibe una textura a asociarle y un vector de posición.
     * @param texture sprite a asociarle, gestionado por el assetManager
     * @param posicion vector de coordenadas x, y para inicializar la posición
     */
    public BulletEntity(Stage stage, Texture texture, Vector2 posicion){
        this.stage = stage;
        this.texture = texture;
        this.bulletHitbox = new Rectangle();
        this.sprite = new Sprite(this.texture);

        float x = posicion.x - (5);
        float y = posicion.y - 10;

        //Valores iniciales del Actor
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setPosition(x, y);
        setSize(10, 20);
        this.bulletHitbox.setPosition(x, y);
        this.bulletHitbox.setSize(this.sprite.getWidth(), this.sprite.getHeight());
        //Fin de valores iniciales del Actor
    }

    /**
     * Este método se encarga de actualizar la posición del disparo cada delta tiempo.
     * @param delta tiempo desde el último frame (*delta leer como *segundo)
     */
    @Override
    public void act(float delta) {
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

    @Override
    public Rectangle getHitbox(){
        return this.bulletHitbox;
    }
}
