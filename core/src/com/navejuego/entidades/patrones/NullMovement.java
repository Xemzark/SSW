package com.navejuego.entidades.patrones;

import com.navejuego.entidades.GameObjectEntity;

/**
 * Created by Elias on 19/04/2016.
 */
public class NullMovement implements MovementPattern {


    public NullMovement(){
        setSpeed(0);
    }
    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public void setSpeed(float newSpeed) {

    }

    @Override
    public void Move(GameObjectEntity entity, float delta) {
        entity.MoveTo(entity.getX(), entity.getY());
    }

    @Override
    public void Reset() {

    }
}
