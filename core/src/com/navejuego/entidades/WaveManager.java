package com.navejuego.entidades;


import com.navejuego.pantallas.PantallaJuego;

import java.util.List;

/**
 * Clase encargada de la gestión de Waves. Esta compuesta por una lista de Waves, y permite
 * lanzar el metodo Spawn del wave actual
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

    /**
     * Boleano para ejecutar las oleadas en bucle
     */
    private boolean loop;

    /**
     * Variable para establecer si el boss ha sido lanzado
     */
    private boolean bossMode;

     /**
     * Enemigo que aparece cuando todas las oleadas han acabado.
     */
    private int bossType;

    /**
     * Constructor
     * @param waveList Lista de Waves
     * @param bossType Tipo de Boss
     * @param loop True si queremos waves en bucle, False si no
     */
    public WaveManager(List<Wave> waveList, int bossType, boolean loop) {
        this.waveList = waveList;
        this.bossType = bossType;
        currentWave = 0;
        this.loop = loop;
        bossMode = false;
    }

    /**
     * Metodo encargado de lanzar el Spawn de cada oleada
     * @return Devuleve true si puede spawnear, false si no puede
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

    /**
     * Resetea todos los waves
     */
    public void Reset () {

        for (Wave wave : waveList) {
            wave.Reset();
        }
        currentWave = 0;
    }

    /**
     * Lanza en la pantalla al boss seleccionado
     * @param bossType boss a spawnear
     */
    public void SpawnBoss (int bossType) {
        PantallaJuego.stage.addActor(new BossEnemigo(bossType));
    }

}
