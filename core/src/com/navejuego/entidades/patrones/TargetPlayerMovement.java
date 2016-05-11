package com.navejuego.entidades.patrones;

import com.navejuego.Constantes;
import com.navejuego.entidades.GameObjectEntity;
import com.navejuego.pantallas.Pantalla;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by Kevin on 30/04/2016.
 */
public class TargetPlayerMovement implements MovementPattern{
    /**
     *
     *
     *
     * Se crea la recta que cruza los puntos de la posicion del jugador y el del objeto
     * a mover, y a partir de ahi, se desplaza a traves de dicha recta.
     *
     * TODO!!!! Parece que si el jugador esta cerca del objeto a mover cuando se activa el patron, se acelera mucho!
     *
     *
     */
    private float speed;
    private float targetX = PantallaJuego.jugador.getX() + PantallaJuego.jugador.sprite.getWidth()/2;
    private float targetY = PantallaJuego.jugador.getY() + PantallaJuego.jugador.sprite.getHeight()/2;
    private float m; //pendiente de la ecuacion
    private float b; //punto de corte en el eje
    private boolean first = true;  //si se tiene que calcular la ecuacion
    private boolean goUp;

    public TargetPlayerMovement(float speed) {
        this.speed = speed * Constantes.resizeHeight;

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
            m = (newY - targetY)/(newX - targetX);
            b = -targetX*m + targetY;

            if(newY <= targetY){
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
