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

    private boolean bossMode;

    /**
     * Enemigo que aparece cuando todas las oleadas han acabado.
     */
    private BossEnemigo boss;

    public WaveManager(List<Wave> waveList, BossEnemigo boss, boolean loop) {
        this.waveList = waveList;
        this.boss = boss;
        currentWave = 0;
        this.loop = loop;
        bossMode = false;
    }

    /**
     * @return True if it can spawn something, false if there's nothing to spawn.
     */
    public boolean Spawn () {
        if (bossMode) {
            //TODO: Devolver si el boss ha sido derrotado o no
            return false;
        }

        if (!waveList.get(currentWave).isDone()) {
            //Quedan enemigos por spawnear en la oleada.
            waveList.get(currentWave).Spawn();
            return true;
        } else {
            System.out.print("Wave " + currentWave + " ended.\n");
            currentWave += 1;
            if (currentWave < waveList.size()) {
                waveList.get(currentWave).GetReady();
                waveList.get(currentWave).Spawn();
                return true;
            } else {
                if (boss != null) {
                    //TODO: Spanw boss
                    return true;
                } else if (loop) {
                    //Looping waves
                    Reset ();
                    return true;
                } else {
                    //Waves over
                    return false;
                }
            }
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
