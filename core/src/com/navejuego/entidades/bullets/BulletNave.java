package com.navejuego.entidades.bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.navejuego.entidades.*;
import com.navejuego.entidades.patrones.LinealMovement;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by Elias on 09/04/2016.
 */
public class BulletNave extends BulletEntity {

    public BulletNave(Texture texture, Vector2 posicion, int damage) {
        super(texture, posicion, damage);
        movementPattern = new LinealMovement(900.0f, true);
    }

    @Override
    protected void comprobarColision() {
        for(Actor b : PantallaJuego.stage.getActors()){
            //Gdx.app.log("comprovando colision", b.getName());
            if (b instanceof EnemigoEntity){
                EnemigoEntity enemigo = (EnemigoEntity) b;
                if (enemigo.getHitbox().overlaps(this.getHitbox())){
                    enemigo.recibirDmg(damage, ignoraEscudo);
                    //Gdx.app.log("Hit a nave enemiga", "");
                    this.destruirse();
                }
            }
        }
    }

}
