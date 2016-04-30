package com.navejuego.entidades.patrones;

import com.badlogic.gdx.Gdx;
import com.navejuego.Constantes;
import com.navejuego.entidades.GameObjectEntity;

/**
 * Created by Kevin on 20/04/2016.
 */
public class HoritzontalMovement implements MovementPattern {


    /**
     *  Mueve horizontalmente el objeto hasta los limites de la pantalla, sin salirse de ella
     */
    private float speed;
    private boolean goRight;

    public HoritzontalMovement(float speed) {
        this.speed = speed * Constantes.resizeWidth;
        this.goRight = true;
    }

    public boolean isGoRight() {
        return goRight;
    }

    public void setGoRight(boolean goRight) {
        this.goRight = goRight;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(float newSpeed) {
        speed = newSpeed;
    }

    /**
     * Moves the entity up or down.
     */
    @Override
    public void Move(GameObjectEntity entity, float delta) {
        float newX =  entity.getX();

        /**
         * Controla que este dentro de los limites de la pantalla, si llega al final, cambia de
         * direccion
         */
        if (goRight && (speed*delta)+newX > Gdx.graphics.getWidth()-entity.getWidth()){
            goRight = false;
        } else if (!goRight && newX - (speed*delta) < 0) {
            goRight = true;
        }

        /**
         * Desplaza el objeto en la direccion asignada
         */
        if (goRight) {
            newX += speed * delta;
        } else {
            newX -= speed * delta;

        }
        entity.MoveTo(newX, entity.getY());
    }


    @Override
    public void Reset() {

    }
}
