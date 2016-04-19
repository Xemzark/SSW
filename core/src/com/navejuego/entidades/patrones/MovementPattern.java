package com.navejuego.entidades.patrones;

import com.navejuego.entidades.GameObjectEntity;

/**
 * Created by Elias on 19/04/2016.
 */
public interface MovementPattern {

    /**
     * Get the speed of the movement pattern.
     * @return
     */
    float getSpeed();

    /**
     * Alter the speed for the object.
     * @param newSpeed : The new speed for the object.
     */
    void setSpeed(float newSpeed);

    /**
     * Based on the current position of the entity, an elapsed time and usually some
     * inner values of the Movement Pattern, calculates the next position and moves the object.
     * Uses entity.MoveTo() to set the new position.
     * @param entity The entity to move.
     * @param delta Time since last update.
     */
    void Move (GameObjectEntity entity, float delta);

    /**
     * If the Movement Pattern has some kind of inner values or behaviours,
     * reset them. Otherwise, does nothing.
     */
    void Reset ();


}
