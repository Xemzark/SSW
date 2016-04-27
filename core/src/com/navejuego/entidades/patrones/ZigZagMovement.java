package com.navejuego.entidades.patrones;

import com.badlogic.gdx.Gdx;
import com.navejuego.entidades.GameObjectEntity;

/**
 * Created by Xavi on 19/04/2016.
 */
public class ZigZagMovement implements MovementPattern {

    private float speed;
    private float ZigSpeed;
    private boolean goRight;
    /**
     * If this is set to true, the ship moves upwards.
     * Otherwise, it moves downwards.
     */
    private boolean goUp = false;

    public ZigZagMovement(float speed, float ZigSpeed, boolean goUp) {
        this.speed = speed;
        this.goUp = goUp;
        this.ZigSpeed = ZigSpeed;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(float newSpeed) {
        speed = newSpeed;
    }

    public boolean isGoingUp() {
        return goUp;
    }

    public void setDirectionUp (boolean goUp) {
        this.goUp = goUp;
    }

    /**
     * Moves the entity up or down.
     */
    @Override
    public void Move(GameObjectEntity entity, float delta) {
        float newY =  entity.getY();
        if (goUp) {
            newY += speed * delta;
        } else {
            newY -= speed * delta;
        }
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
            newX += ZigSpeed * delta;
        } else {
            newX -= ZigSpeed * delta;

        }
        entity.MoveTo(newX, newY);
    }

    @Override
    public void Reset() {

    }
}