package com.navejuego.entidades;


import com.navejuego.pantallas.PantallaJuego;

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
    public void Spawn () {
        if (bossMode)
            return;

        //TODO: Convertir boss es una wave

        if (!waveList.get(currentWave).isDone()) {
            //Quedan enemigos por spawnear en la oleada.
            waveList.get(currentWave).Spawn();
        } else {
            System.out.print("Wave " + currentWave + " ended.\n");
            if (currentWave < waveList.size()-1) {
                currentWave += 1;
                waveList.get(currentWave).GetReady();
                waveList.get(currentWave).Spawn();
            } else {
                System.out.print("All waves ended.\n");
                if (bossType != 0) {
                    SpawnBoss(bossType);
                    bossMode = true;
                } else if (loop) {
                    //Looping waves
                    Reset ();
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

    public void SpawnBoss (int bossType) {
        PantallaJuego.stage.addActor(new BossEnemigo(bossType));
    }

}
