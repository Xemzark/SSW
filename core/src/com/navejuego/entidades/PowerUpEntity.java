package com.navejuego.entidades;

/**
 * Created by Elias on 09/04/2016.
 */
public class PowerUpEntity extends GameObjectEntity {
    private int vida;
    private int escudo;
    private float segundosInvulnerabilidad;
    private float segundosDobleCadenciaDisparo;
    private int puntos;

    private JugadorEntity jugador;

    public PowerUpEntity(JugadorEntity jugador, int vida, int escudo, float segundosInvulnerabilidad, float segundosDobleCadenciaDisparo, int puntos) {
        this.vida = vida;
        this.escudo = escudo;
        this.segundosInvulnerabilidad = segundosInvulnerabilidad;
        this.segundosDobleCadenciaDisparo = segundosDobleCadenciaDisparo;
        this.puntos = puntos;
        this.jugador = jugador;
    }

    /**
     * TODO: Comprobar si ha colisionado con el jugador. Si ha colisionado, se auto-destruye y le aplica el efecto.
     */
    public void comprobarColisionJugador() {
        if (false) { //TODO: Comprobar colisión aquí
            destruirse();
            aplicarEfectosSobreJugador();
        }
    }

    /**
     * TODO: Destruir power-up
     */
    @Override
    public void destruirse() {

    }

    /**
     * TODO: Aplica sus efectos sobre el jugador.
     */
    private void aplicarEfectosSobreJugador() {
        jugador.modificarStats(this);
    }
}
