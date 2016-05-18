package com.navejuego;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.navejuego.entidades.GameObjectEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xavi on 22/04/2016.
 */
public class Explosion extends GameObjectEntity {
    float duracion; //segundos
    float ttrans; // segundos
    protected int indiceactual;
    List<Texture> listatextura;
    float duracionframe; //segundos

    public Explosion(ArrayList<Texture> texture, Vector2 posicion, float duracion, float size) {

        this.texture = texture.get(0);
        this.sprite = new Sprite(this.texture);
        this.sprite.setSize(size * Constantes.resizeWidth, size * Constantes.resizeHeight);
        setSize(sprite.getWidth(), sprite.getHeight());
        ttrans = 0.0f;
        this.duracion = duracion;
        float x = posicion.x;
        float y = posicion.y;
        indiceactual = 0;
        listatextura = texture;

        //Valores iniciales del Actor
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setPosition(x, y);
        duracionframe = duracion / texture.size();
    }

    /**
     * Dibuja la textura seleccionada de la animación
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void destruirse() {
        this.remove();
    }

    /**
     * Selecciona la siguiente textura de la animación que sera dibujada
     * @param delta
     */
    @Override
    public void act(float delta) {
        ttrans += delta;
        if (ttrans >= duracion){
            destruirse();
        }else if(duracionframe * (indiceactual+1)<= ttrans){
            indiceactual+=1;
            texture = listatextura.get(indiceactual);
        }
    }
}
