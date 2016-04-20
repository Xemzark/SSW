package com.navejuego.entidades.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.entidades.*;
import com.navejuego.entidades.patrones.LinealMovement;

/**
 * Created by Elias on 09/04/2016.
 */
public class BulletNave extends BulletEntity {

    public BulletNave(Stage stage, Texture texture, Vector2 posicion) {
        super(stage, texture, posicion);
        movementPattern = new LinealMovement(900.0f, true);
    }

    /**
     * TODO: Comprobar colision con otros gameobjects del stage.
     */
    @Override
    protected void comprobarColision() {
        for(Actor b : stage.getActors()){
            //TODO: Comprobar colisión aquí y aplicar efectos de choque si corresponde.
            //Gdx.app.log("comprovando colision", b.getName());
            if (b instanceof EnemigoEntity){
                EnemigoEntity enemigo = (EnemigoEntity) b;
                if (enemigo.getHitbox().overlaps(this.getHitbox())){
                    enemigo.recibirDmg(damage, ignoraEscudo);
                    Gdx.app.log("Hit a nave enemiga", "");
                    this.destruirse();
                }
            }
        }
    }

}
