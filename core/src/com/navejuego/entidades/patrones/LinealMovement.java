package com.navejuego.entidades.patrones;

import com.navejuego.entidades.GameObjectEntity;

/**
 * Created by Elias on 19/04/2016.
 */
public class LinealMovement implements MovementPattern {

    private float speed;

    /**
     * If this is set to true, the ship moves upwards.
     * Otherwise, it moves downwards.
     */
    private boolean goUp = false;

    public LinealMovement(float speed, boolean goUp) {
        this.speed = speed;
        this.goUp = goUp;
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
        entity.MoveTo(entity.getX(), newY);
    }

    @Override
    public void Reset() {

    }
}
