package com.navejuego.entidades;


import java.util.List;

/**
 * Created by Elias on 21/04/2016.
 */
public class WaveManager {

    /**
     * Oleada actual.
     */
    private int currentWave;

    /**
     * Lista que contiene las oleadas presentes en un nivel.
     * Cada entrada es el enemigo a spawnear.
     */
    private List<Wave> waveList;

    private boolean loop;

    /**
     * Enemigo que aparece cuando todas las oleadas han acabado.
     */
    private BossEnemigo boss;

    public WaveManager(List<Wave> waveList, BossEnemigo boss, boolean loop) {
        this.waveList = waveList;
        this.boss = boss;
        currentWave = 0;
        this.loop = loop;
    }

    /**
     * @return True if it can spawn something, false if there's nothing to spawn.
     */
    public boolean Spawn () {
        if (currentWave >= waveList.size()) {
            //No hay mas oleadas.
            //TODO: Spawn boss
            if (loop) {
                Reset ();
                return Spawn ();
            } else {
                //TODO: Devolver si el boss ha sido derrotado o no
                return false;
            }
        }

        if (!waveList.get(currentWave).isDone()) {
            //Quedan enemigos por spawnear en la oleada.
            waveList.get(currentWave).Spawn();
            return true;
        } else {
            System.out.print("Wave " + currentWave + " ended.");
            currentWave += 1;
            return Spawn ();
        }
    }

    public void Reset () {
        System.out.print("Resetting waves.");
        for (Wave wave : waveList) {
            wave.Reset();
        }
        currentWave = 0;
    }

}
