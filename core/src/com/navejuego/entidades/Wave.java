package com.navejuego.entidades;

import com.navejuego.entidades.powerups.PowerUpASPD;
import com.navejuego.pantallas.PantallaJuego;

/**
 * Created by Elias on 21/04/2016.
 */
public class Wave {

    /**
     * Tiempo entre la aparicion de un Enemigo y otro dentro de la oleada.
     * Medido en milesimas de segundo.
     */
    public long spawnDelay;

    /**
     * Cantidad de enemigos a crear.
     */
    public int spawnTargetAmount;

    /**
     * Enemigo a instanciar.
     */
    public EnemigoEntity enemy;

    /**
     * Cantidad de enemigos creados actualmente.
     */
    private int spawnCount;

    /**
     * Usado para determinar si hacer un spawn o no.
     */
    private long nextSpawn;

    //TODO: Reemplazar "enemigo" por un factory class o lo que sea
    public Wave(EnemigoEntity enemigo, int cantidadDeEnemgios, long tiempoEntreEnemigos) {
        enemy = enemigo;
        spawnTargetAmount = cantidadDeEnemgios;
        spawnDelay = tiempoEntreEnemigos;
        spawnCount = 0;
        nextSpawn = System.currentTimeMillis() + tiempoEntreEnemigos;
    }

    public void GetReady() {
        nextSpawn = System.currentTimeMillis() + spawnDelay;
    }

    public void Spawn () {

        if (spawnCount < spawnTargetAmount && nextSpawn <= System.currentTimeMillis()) {

            PantallaJuego.stage.addActor(new EnemigoEntity(PantallaJuego.stage, 1 ));
            nextSpawn = System.currentTimeMillis() + spawnDelay;
            spawnCount += 1;
        }
    }

    public boolean isDone() {
        //TODO: Que esto devuelva "True" solo cuando todas las naves de la oleada sean destruidas.
        return spawnTargetAmount <= spawnCount;
    }

    public void Reset() {
        spawnCount = 0;
        nextSpawn = System.currentTimeMillis() + spawnDelay;
    }

}
