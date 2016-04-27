package com.navejuego.entidades;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.navejuego.GestorAssets;

import java.util.ArrayList;

/**
 * Created by beno_ on 23/04/2016.
 */
public class LevelManager {

    private Level currentLevel;

    public enum Nivel{
        NIVEL_1, NIVEL_2, NIVEL_3, NIVEL_4;
    }

    public int bossList[] = {2,2,2,2};
    public int bossType;

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

                waveArray.add(new Wave(null, 5, 1000));
                waveArray.add(new Wave(null, 2, 3000));
                bossType = bossList[0];
                background = GestorAssets.getInstance().getTexture("background_1.png");
                music = GestorAssets.getInstance().getMusic("SpaceLoungeLoop.wav");

                break;

            case NIVEL_2:

                waveArray.add(new Wave(null, 5, 1000));
                waveArray.add(new Wave(null, 2, 3000));
                bossType = bossList[1];
                background = GestorAssets.getInstance().getTexture("background_3.png");
                music = GestorAssets.getInstance().getMusic("SpaceCube.wav");

                break;

            case NIVEL_3:

                waveArray.add(new Wave(null, 5, 1000));
                waveArray.add(new Wave(null, 2, 3000));
                bossType = bossList[2];
                background = GestorAssets.getInstance().getTexture("background_7.png");
                music = GestorAssets.getInstance().getMusic("SpaceTrip.mp3");

                break;

            case NIVEL_4:

                waveArray.add(new Wave(null, 5, 1000));
                waveArray.add(new Wave(null, 2, 3000));
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
