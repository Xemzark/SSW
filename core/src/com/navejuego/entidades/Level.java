package com.navejuego.entidades;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by beno_ on 23/04/2016.
 */
public class Level {

    private Texture background;
    private Music music;
    private WaveManager wavemanager;

    public Level(WaveManager wavemanager, Music music, Texture background){
        this.wavemanager = wavemanager;
        this.music = music;
        this.background = background;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public Texture getBackground() {
        return background;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }

    public WaveManager getWavemanager() {
        return wavemanager;
    }

    public void setWavemanager(WaveManager wavemanager) {
        this.wavemanager = wavemanager;
    }





}
