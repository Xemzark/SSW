package com.navejuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Clase Preferencias, gestiona las preferencias utilizando el objeto preferences de Ligbgdx.
 * Permite modificar cualquier campo de preferencias asi como consultar su estado.
 */
public class Preferencias {

    /**
     * Objeto Preferences de Libgdx, utilizado como base de datos para almacenar los atributos
     * de preferencias
     */
    Preferences prefs;
    /**
     * Instancia de la clase para el Singleton
     */
    private static Preferencias instance;

    /**
     * Constructor
     */
    private Preferencias(){
        this.prefs  = Gdx.app.getPreferences("My Preferences");
    }

    /**
     * Singleton Preferencias
     * @return Devuelve instancia Preferencias
     */
    public static Preferencias getInstance() {
        if (instance == null) {
            instance = new Preferencias();
        }
        return instance;
    }

    /**
     * Introduce si la musica esta habilitada
     * @param music, true o false
     */
    public void setMusic(boolean music){
        this.prefs.putBoolean("music",music);
    }

    /**
     * Introduce si los sonidos estan habilitados
     * @param sound, true o false
     */
    public void setSound(boolean sound){
        this.prefs.putBoolean("sound",sound);
    }

    /**
     * Introduce si la vibracion esta activa o no
     * @param vibration, true o false
     */
    public void setVibration(boolean vibration){
        this.prefs.putBoolean("vibration",vibration);
    }

    /**
     * Devuelve si los sonidos estan activados
     * @return true si los sonidos estan activos, false si no lo estan
     */
    public boolean soundOn(){
        //el true es el default
        return this.prefs.getBoolean("sound",true);
    }

    /**
     * Devuelve si la musica esta activada
     * @return true si la música está activa, false si no lo esta
     */
    public boolean musicOn(){
        return this.prefs.getBoolean("music",true);
    }

    /**
     * Devuelve si la vibracion esta activada
     * @return true si la vibración está activa, false si no lo está
     */
    public boolean vibrationOn(){
        return this.prefs.getBoolean("vibration",true);
    }

    /**
     * Persiste los cambios en el fichero
     * Esto es necesario para persistir las perferencias. O no se guardarán
     */
    public void savePreferences(){
        this.prefs.flush();
    }
}
