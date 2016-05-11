package com.navejuego.entidades.bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.navejuego.entidades.patrones.LinealMovement;
import com.navejuego.entidades.patrones.MovementPattern;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by Elias on 09/04/2016.
 */
public class BulletEnemigo extends BulletEntity {

    public BulletEnemigo(Texture texture, Vector2 posicion, int damage,MovementPattern pattern) {
        super(texture, posicion, damage);
        movementPattern = pattern;
    }

    /**
     * TODO: Comprobar colision con objeto jugador.
     */
    @Override
    protected void comprobarColision() {
            //TODO: Comprobar colisión aquí y aplicar efectos de choque si corresponde.
        if (PantallaJuego.jugador.getHitbox().overlaps(this.getHitbox())) {
            PantallaJuego.jugador.recibirDmg(damage, ignoraEscudo);
            //Gdx.app.log("Hit a jugador!", "");
            this.destruirse();
        }
    }
}
