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
    private int bossType;

    public WaveManager(List<Wave> waveList, int bossType, boolean loop) {
        this.waveList = waveList;
        this.bossType = bossType;
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
            if (currentWave < waveList.size() -1) {
                waveList.get(currentWave).GetReady();
                waveList.get(currentWave).Spawn();
                currentWave += 1;
                return true;
            } else {
                if (bossType != 0) {
                    System.out.print(currentWave);

                    waveList.get(currentWave).SpawnBoss(bossType);
                    bossMode= true;
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
