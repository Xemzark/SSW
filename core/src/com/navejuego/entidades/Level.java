package com.navejuego.entidades;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

/**
 * Clase que representa la estructura y contenido de un nivel.
 */
public class Level {

    /**
     * Fondo de pantalla
     */
    private Texture background;

    /**
     * Musica de fondo del nivel
     */
    private Music music;
    private WaveManager wavemanager;

    /**
     * Constructor
     * @param wavemanager WaveManager del nivel
     * @param music Musica del nivel
     * @param background Fondo asociado al nivel para la pantalla Juego
     */
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
