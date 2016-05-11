package com.navejuego.entidades.patrones;

import com.navejuego.Constantes;
import com.navejuego.entidades.GameObjectEntity;

/**
 * Created by Xavi on 19/04/2016.
 */
public class SinusMovement implements MovementPattern {

    private float speed;
    private double deltaAcum;
    private float SinSpeed;
    /**
     * If this is set to true, the ship moves upwards.
     * Otherwise, it moves downwards.
     */
    private boolean goUp = false;

    public SinusMovement(float speed, float SinSpeed, boolean goUp) {
        this.speed = speed * Constantes.resizeHeight;
        this.goUp = goUp;
        deltaAcum = 0;
        this.SinSpeed = SinSpeed * Constantes.resizeWidth;
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
        float newY = entity.getY();
        float newX = entity.getX();
        if (goUp) {
            newY += speed * delta;
        } else {
            newY -= speed * delta;
        }
        deltaAcum += (double) delta * SinSpeed;
        newX = (float) (newX + Math.sin(deltaAcum));

        entity.MoveTo(newX, newY);
    }

    @Override
    public void Reset() {

    }
}