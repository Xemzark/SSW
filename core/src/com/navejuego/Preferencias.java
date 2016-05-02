package com.navejuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by beno_ on 02/05/2016.
 */

public class Preferencias {

    Preferences prefs;
    private static Preferencias instance;

    private Preferencias(){
        this.prefs  = Gdx.app.getPreferences("My Preferences");
    }
    public static Preferencias getInstance() {
        if (instance == null) {
            instance = new Preferencias();
        }
        return instance;
    }

    public void setMusic(boolean music){
        this.prefs.putBoolean("music",music);
    }

    public void setSound(boolean sound){
        this.prefs.putBoolean("sound",sound);
    }

    public void setVibration(boolean vibration){
        this.prefs.putBoolean("vibration",vibration);
    }
    public boolean soundOn(){
        //el true es el default
        return this.prefs.getBoolean("sound",true);
    }

    public boolean musicOn(){
        return this.prefs.getBoolean("music",true);
    }

    public boolean vibrationOn(){
        return this.prefs.getBoolean("vibration",true);
    }

    //Esto es necesario para persistir las perferencias.
    public void savePreferences(){
        this.prefs.flush();
    }
}
