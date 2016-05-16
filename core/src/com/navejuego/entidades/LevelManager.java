package com.navejuego.entidades;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.navejuego.GestorAssets;
import java.util.Random;


import java.util.ArrayList;

/**
 * Created by beno_ on 23/04/2016.
 */
public class LevelManager {

    private Level currentLevel;

    public enum Nivel{
        NIVEL_1, NIVEL_2, NIVEL_3, NIVEL_4;
    }
    public int enemySet[]; //tipos de enemigos que podran aparecer en un nivel
    public int bossList[] = {21,22,23,24}; //Por orden, se corresponden al boss de cada nivel
    public int bossType;
    public int waveAmount; //cantidad de oleadas que hay en un nivel
    public Random rnd = new Random(); //se utiliza para cargar aleatoriamente los enemigos que se generan

    public LevelManager(Nivel nivel){
        this.setCurrentLevel(nivel);
    }

    public Level getCurrentLevel(){
        return this.currentLevel;
    }

    public void setCurrentLevel(Nivel lvl){

        ArrayList<Wave> waveArray = new ArrayList<Wave>();
        Texture background = null;
        Music music = null;

        switch (lvl) {
            case NIVEL_1:

                enemySet = new int[] {1,2,3,4};
                waveAmount = 6;

                for(int i =0; i < waveAmount; i++ ) {
                    waveArray.add(new Wave(enemySet[rnd.nextInt(enemySet.length)]));
                }

                bossType = bossList[0];
                background = GestorAssets.getInstance().getTexture("background_1.png");
                music = GestorAssets.getInstance().getMusic("SpaceLoungeLoop.wav");

                break;

            case NIVEL_2:

                enemySet = new int[] {5,6,7,8};
                waveAmount = 9;

                for(int i =0; i < waveAmount; i++ ) {
                    waveArray.add(new Wave(enemySet[rnd.nextInt(enemySet.length)]));
                }
                bossType = bossList[1];
                background = GestorAssets.getInstance().getTexture("background_3.png");
                music = GestorAssets.getInstance().getMusic("SpaceCube.wav");

                break;

            case NIVEL_3:

                enemySet = new int[] {9,10,11,12};
                waveAmount = 13;

                for(int i =0; i < waveAmount; i++ ) {
                    waveArray.add(new Wave(enemySet[rnd.nextInt(enemySet.length)]));
                }

                bossType = bossList[2];
                background = GestorAssets.getInstance().getTexture("background_7.png");
                music = GestorAssets.getInstance().getMusic("SpaceTrip.mp3");

                break;

            case NIVEL_4:

                enemySet = new int[] {13,14,15,16};
                waveAmount = 15;

                for(int i =0; i < waveAmount; i++ ) {
                    waveArray.add(new Wave(enemySet[rnd.nextInt(enemySet.length)]));
                }
                bossType = bossList[3];
                background = GestorAssets.getInstance().getTexture("background_6.png");
                music = GestorAssets.getInstance().getMusic("SpaceGate.mp3");

                break;

            default:

                break;

        }

        WaveManager waves = new WaveManager(waveArray, bossType, false);
        this.currentLevel = new Level(waves,music,background);

    }

    public void restartLevel(){
        this.currentLevel.getWavemanager().Reset();
    }

    public void spawn(){
        this.currentLevel.getWavemanager().Spawn();
    }

    public Texture getBackGround(){
        return this.currentLevel.getBackground();
    }

    public Music getMusic(){
        return this.currentLevel.getMusic();
    }



}
