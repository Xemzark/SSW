package com.navejuego.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by Elias on 09/04/2016.
 */
public abstract class PowerUpEntity extends GameObjectEntity {
    private float velocidad = 200.0f;

    public PowerUpEntity(Stage stage, Texture texture, Vector2 posicion) {
        /*this.vida = vida;
        this.escudo = escudo;
        this.segundosInvulnerabilidad = segundosInvulnerabilidad;
        this.segundosDobleCadenciaDisparo = segundosDobleCadenciaDisparo;
        this.puntos = puntos;*/

        this.stage = stage;
        this.texture = texture;
        this.sprite = new Sprite(this.texture);
        this.hitbox = new Rectangle();

        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setPosition(posicion.x, posicion.y);
        setSize(50, 50);
        hitbox.setSize(sprite.getWidth(), sprite.getHeight());
        hitbox.setPosition(getX(), getY());

    }

    @Override
    public void act(float delta){
        movimiento(delta);
        comprobarColisionJugador();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void movimiento(float delta){
        setPosition(getX(), getY() - (velocidad * delta));
        hitbox.setPosition(getX(), getY());
    }
    /**
     * TODO: Comprobar si ha colisionado con el jugador. Si ha colisionado, se auto-destruye y le aplica el efecto.
     */
    public void comprobarColisionJugador() {
        if (PantallaJuego.jugador.getHitbox().overlaps(this.getHitbox())){
            this.aplicarEfectosSobreJugador();
            this.destruirse();
        }
    }

    /**
     * TODO: Destruir power-up
     */
    @Override
    public void destruirse() {
        this.remove();
    }

    /**
     * TODO: Aplica sus efectos sobre el jugador.
     */
    public abstract void aplicarEfectosSobreJugador();
}
