package com.navejuego.entidades.powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.navejuego.Constantes;
import com.navejuego.GestorAssets;
import com.navejuego.Preferencias;
import com.navejuego.entidades.GameObjectEntity;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by Elias on 09/04/2016.
 */
public abstract class PowerUpEntity extends GameObjectEntity {
    private float velocidad = 200.0f;

    public PowerUpEntity(Texture texture, Vector2 posicion) {
        /*this.vida = vida;
        this.escudo = escudo;
        this.segundosInvulnerabilidad = segundosInvulnerabilidad;
        this.segundosDobleCadenciaDisparo = segundosDobleCadenciaDisparo;
        this.puntos = puntos;*/

        this.texture = texture;
        this.sprite = new Sprite(this.texture);
        this.hitbox = new Circle();

        setSize(50 * Constantes.resizeWidth, 50 * Constantes.resizeHeight);
        setPosition(posicion.x, posicion.y);
        setBounds(getX(), getY(), getWidth(), getHeight());


        hitbox.set(getX()+getWidth()/2,getY()+getHeight()/2,getWidth()/2);

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
        hitbox.set(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);
    }

    public void comprobarColisionJugador() {
        if (PantallaJuego.jugador.getHitbox().overlaps(this.getHitbox())){
            this.aplicarEfectosSobreJugador();
            if(Preferencias.getInstance().soundOn()){
                GestorAssets.getInstance().getSound("powerup.mp3").play();
            }

            this.destruirse();
        }
    }

    @Override
    public void destruirse() {
        this.remove();
    }

    public abstract void aplicarEfectosSobreJugador();
}
