package com.navejuego.entidades.patrones;

import com.badlogic.gdx.math.Vector2;
import com.navejuego.entidades.GameObjectEntity;

/**
 * Created by Elias on 19/04/2016.
 */
public class GrowthMovement implements MovementPattern {

    private boolean grow;
    private Vector2 targetScale;
    float scalePerSecond;

    public GrowthMovement(Vector2 targetScale, boolean grow, float scalePerSecond) {
        this.scalePerSecond = scalePerSecond;
        this.grow = grow;
        this.targetScale = targetScale;
    }

    @Override
    public float getSpeed() {
        return scalePerSecond;
    }

    @Override
    public void setSpeed(float newSpeed) {
        scalePerSecond = newSpeed;
    }

    @Override
    public void Move(GameObjectEntity entity, float delta) {
        entity.MoveTo(entity.getX(), entity.getY());

        if (grow) { //Grow
            if (targetScale.x > entity.getScaleX()) { //Grow in X
                if (targetScale.x <= entity.getScaleX() + scalePerSecond) { //Target scale reached?
                    entity.setScaleX(targetScale.x);
                } else { //Increase X scale
                    entity.setScaleX(entity.getScaleX() + scalePerSecond * delta);
                }
            }
            if (targetScale.y > entity.getScaleY()) { //Grow in Y
                if (targetScale.y <= entity.getScaleY() + scalePerSecond) { //Target scale reached?
                    entity.setScaleY(targetScale.y);
                } else { //Increase Y scale
                    entity.setScaleY(entity.getScaleY() + scalePerSecond * delta);
                }
            }
        } else { //Shrink, same as above
            if (targetScale.x < entity.getScaleX()) { //Grow in X
                if (targetScale.x >= entity.getScaleX() - scalePerSecond) { //Target scale reached?
                    entity.setScaleX(targetScale.x);
                } else { //Increase X scale
                    entity.setScaleX(entity.getScaleX() - scalePerSecond * delta);
                }
            }
            if (targetScale.y < entity.getScaleY()) { //Grow in Y
                if (targetScale.y >= entity.getScaleY() - scalePerSecond) { //Target scale reached?
                    entity.setScaleY(targetScale.y);
                } else { //Increase Y scale
                    entity.setScaleY(entity.getScaleY() - scalePerSecond * delta);
                }
            }
        }

        entity.RecalculateHitboxSize();
    }

    @Override
    public void Reset() {

    }
}
