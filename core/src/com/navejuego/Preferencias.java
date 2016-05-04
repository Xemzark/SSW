package com.navejuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by beno_ on 02/05/2016.
 */

public class Preferencias {

    Preferences prefs;
    private static Preferencias instance;

    /**
     * Constructor
     */
    private Preferencias(){
        this.prefs  = Gdx.app.getPreferences("My Preferences");
    }

    /**
     * Singleton
     * @return
     */
    public static Preferencias getInstance() {
        if (instance == null) {
            instance = new Preferencias();
        }
        return instance;
    }

    /**
     * Introduce si la musica esta habilitada
     * @param music
     */
    public void setMusic(boolean music){
        this.prefs.putBoolean("music",music);
    }

    /**
     * Introduce si los sonidos estan habilitados
     * @param sound
     */
    public void setSound(boolean sound){
        this.prefs.putBoolean("sound",sound);
    }

    /**
     * Introduce si la vibracion esta activa o no
     * @param vibration
     */
    public void setVibration(boolean vibration){
        this.prefs.putBoolean("vibration",vibration);
    }

    /**
     * Devuelve si los sonidos estan activados
     * @return
     */
    public boolean soundOn(){
        //el true es el default
        return this.prefs.getBoolean("sound",true);
    }

    /**
     * Devuelve si la musica esta activada
     * @return
     */
    public boolean musicOn(){
        return this.prefs.getBoolean("music",true);
    }

    /**
     * Devuelve si la vibracion esta activada
     * @return
     */
    public boolean vibrationOn(){
        return this.prefs.getBoolean("vibration",true);
    }

    /**
     * Esto es necesario para persistir las perferencias. O no se guardaran
     */
    public void savePreferences(){
        this.prefs.flush();
    }
}
