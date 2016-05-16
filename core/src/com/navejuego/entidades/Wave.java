package com.navejuego.entidades;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.navejuego.pantallas.PantallaJuego;

import java.util.ArrayList;

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

    private ArrayList<EnemigoEntity> spawnedEnemies;

    public Wave(int enemyType) {
        enemy = enemyType;
        spawnTargetAmount = EnemyType.getSpawnAmount(enemyType);
        spawnDelay = EnemyType.getSpawnDelay(enemyType);
        spawnCount = 0;
        nextSpawn = System.currentTimeMillis() + spawnDelay;
        spawnedEnemies = new ArrayList<EnemigoEntity>();
    }

    public void GetReady() {
        nextSpawn = System.currentTimeMillis() + spawnDelay;
    }

    public void Spawn () {

        if (spawnCount < spawnTargetAmount && nextSpawn <= System.currentTimeMillis()) {
            EnemigoEntity enemyRef = new EnemigoEntity(enemy);
            PantallaJuego.stage.addActor(enemyRef);
            spawnedEnemies.add(enemyRef);
            nextSpawn = System.currentTimeMillis() + spawnDelay;
            spawnCount += 1;
        }
    }

    public boolean isDone() {
        if (spawnTargetAmount > spawnCount) {
            return false;
        }

        for (EnemigoEntity enemyRef : spawnedEnemies) {
            if (PantallaJuego.stage.getActors().contains(enemyRef, false)) {
                return false;
            }
        }

        return true;
    }

    public void Reset() {
        spawnCount = 0;
        nextSpawn = System.currentTimeMillis() + spawnDelay;
        spawnedEnemies = new ArrayList<EnemigoEntity>();
    }

}
