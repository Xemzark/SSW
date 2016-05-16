package com.navejuego.entidades.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.Constantes;
import com.navejuego.entidades.GameObjectEntity;

/**
 * Created by Andrés on 04/04/2016.
 */

/**
 * Clase BulletEntity
 * Esta clase sirve para generar los disparos del juego, tanto los de la nave del jugador como los
 * de los enemigos.
 */
public abstract class BulletEntity extends GameObjectEntity {

    protected int damage = 0; //Daño que aplica al golpear
    protected boolean ignoraEscudo = false;

    /**
     * Constructor
     * Esta clase recibe una textura a asociarle y un vector de posición.
     * @param texture sprite a asociarle, gestionado por el assetManager
     * @param posicion vector de coordenadas x, y para inicializar la posición
     */
    public BulletEntity(Texture texture, Vector2 posicion, int damage){
        this.texture = texture;
        this.hitbox = new Circle();
        this.sprite = new Sprite(this.texture);
        this.damage = damage;
        float x = posicion.x;
        float y = posicion.y;


        //Valores iniciales del Actor
        setSize(10 * Constantes.resizeWidth, 20 * Constantes.resizeHeight);
        setPosition(x - getWidth() / 2, y);
        setBounds(getX(), getY(), getWidth(), getHeight());
        hitbox.setPosition(getX() + getWidth() / 2, getY() + getHeight() / 2);
        RecalculateHitboxSize();

        movementPattern = null;
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

        movementPattern.Move(this, delta);

        comprobarColision();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void destruirse() {
        this.remove();
        //Gdx.app.log("Bullet killed!", "");
    }

    /**
     * Ejecuta la logica de colision y aplica sus efectos.
     */
    protected abstract void comprobarColision();

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
    public Circle getHitbox(){
        return this.hitbox;
    }

    public void setIgnoraEscudo(boolean ignorar) {
        ignoraEscudo = ignorar;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
