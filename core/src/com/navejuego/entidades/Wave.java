package com.navejuego.entidades;

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
    public int enemy;

    /**
     * Cantidad de enemigos creados actualmente.
     */
    private int spawnCount;

    /**
     * Usado para determinar si hacer un spawn o no.
     */
    private long nextSpawn;

    public Wave(int enemyType, int cantidadDeEnemgios, long tiempoEntreEnemigos) {
        enemy = enemyType;
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

            PantallaJuego.stage.addActor(new EnemigoEntity(enemy));
            nextSpawn = System.currentTimeMillis() + spawnDelay;
            spawnCount += 1;
        }
    }

    public boolean isDone() {
        return spawnTargetAmount <= spawnCount;
    }

    public void Reset() {
        spawnCount = 0;
        nextSpawn = System.currentTimeMillis() + spawnDelay;
    }

}
