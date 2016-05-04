package com.navejuego.entidades.patrones;

import com.navejuego.Constantes;
import com.navejuego.entidades.GameObjectEntity;

/**
 * Created by Kevin on 30/04/2016.
 */
public class TargetMovement implements MovementPattern{
    /**
     *
     *
     * Si funciona !!!!! (creo)
     * Se crea la recta que cruza los puntos de un objetivo recibido en el constructor y el del objeto
     * a mover, y a partir de ahi, se desplaza a traves de dicha recta.
     *
     */
    private float speed;
    private float targetX;
    private float targetY;
    private float m; //pendiente de la ecuacion
    private float b; //punto de corte en el eje
    private boolean first = true;  //si se tiene que calcular la ecuacion
    private boolean goUp;

    public TargetMovement(float speed, float targetX, float targetY) {
        this.speed = speed * Constantes.resizeHeight;
        this.targetX = targetX;
        this.targetY = targetY;

    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(float newSpeed) {
        speed = newSpeed;
    }

    @Override
    public void Move(GameObjectEntity entity, float delta) {
        float newX = entity.getX();
        float newY = entity.getY();
        if (first) {        //si es la primera vez que se carga, se genera la ecucion (forma punto pendiente creo)
            first = false;  //y se determina si el objetivo esta arriba o abajo respecto al objeto a mover
            m = (entity.getY() - targetY)/(entity.getX() - targetX);
            b = -targetX*m + targetY;

            if(entity.getY() <= targetY){
                goUp = true;
            }
            else{
                goUp = false;
            }
        }

        if(goUp){
            newY += speed * delta;
        }
        else{
            newY -= speed * delta;

        }
        newX = (newY - b)/m;
        entity.MoveTo(newX, newY);

    }

    @Override
    public void Reset() {

    }

}
