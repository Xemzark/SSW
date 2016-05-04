package com.navejuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;
import java.util.Hashtable;

/**
 * Created by Elias on 20/04/2016.
 */

public class PartidaGuardada {

    //Base de datos
    Preferences gameData;
    //singleton
    private static PartidaGuardada instance;
    //constante del numero maximo de puntuaciones que se guardan por nivel
    private static final int maxPuntuaciones = 8;
    //constante del numero maximo de nveles del juego
    private static final int maxNiveles = 4;
    //int[] puntuaciones = new int[this.maxPuntuaciones];

    //Singleton
    private PartidaGuardada(){
        this.gameData  = Gdx.app.getPreferences("GameData");
    }

    public static PartidaGuardada getInstance() {
        if (instance == null) {
            instance = new PartidaGuardada();
        }
        return instance;
    }

    /**
     * Colorca una puntuacion en el nivel seleccionado. La funcion ya se encarga de reordenar el array de puntuaciones
     * I de eliminar puntuaciones para hacer espacio(la menor puntuacion).
     * Recordar Nivel 1 = 0 en la BD;
     * @param nivel
     * @param puntuacion
     */

    public void setPuntucion(Integer nivel, Integer puntuacion){

        Json json = new Json();
        String serializedInts = Gdx.app.getPreferences("GameData").getString(String.valueOf(nivel));
        int[] deserializedInts = json.fromJson(int[].class, serializedInts);

        //Si la puntuacion que intentamos colocar es menor que la menor puntuacion de un nivel no hace nada.
        if(puntuacion > deserializedInts[this.maxPuntuaciones - 1]){

            Hashtable<String, String> hashTable = new Hashtable<String, String>();

            deserializedInts[this.maxPuntuaciones - 1] = puntuacion;
            deserializedInts = ordenarArray(deserializedInts);

            hashTable.put(String.valueOf(nivel) , json.toJson(deserializedInts));
            this.gameData.put(hashTable);

        }
    }

    /**
     * Ordena un array de integros de mayor a menor
     * @param array
     * @return
     */
    public int[] ordenarArray(int[] array){
        int aux;

        for (int i = 0; i < array.length - 1; i++) {
            for (int x = i + 1; x < array.length; x++) {
                if (array[x] > array[i]) {
                    aux = array[i];
                    array[i] = array[x];
                    array[x] = aux;
                }
            }
        }
        return array;
    }

    /**
     * Devuelve la punacion indicada por nivel y fina. Recordar Nivel 1 = 0 en la BD;
     * @param nivel
     * @param fila
     * @return
     */
    public int getPuntuacion(int nivel, int fila){

        Json json = new Json();
        String serializedInts = Gdx.app.getPreferences("GameData").getString(String.valueOf(nivel));
        int[] deserializedInts = json.fromJson(int[].class, serializedInts);
        return deserializedInts[fila];
    }

    /**
     * Muestra todas las puntuaciones por consola
     */
    public void printPuntuaciones(){

        Json json = new Json();

        for(int i = 0; i < this.maxNiveles; i++){

            int[] ints = new int[this.maxPuntuaciones];
            String serializedInts = Gdx.app.getPreferences("GameData").getString(String.valueOf(i));
            ints = json.fromJson(int[].class, serializedInts);

            for(int x = 0; x < this.maxPuntuaciones; x++){

                Gdx.app.log("Nivel: " + String.valueOf(i)+" Fila: "+ String.valueOf(x) +" --> "+  String.valueOf(ints[x]),"");

            }
        }
    }

    /**
     * Precarga todas las puntuaciones
     */
    public void fillPutuaciones(){

        Hashtable<String, String> hashTable = new Hashtable<String, String>();

        for(int i = 0; i < this.maxNiveles; i++){

            int[] ints = new int[this.maxPuntuaciones];

            for(int x = 0; x < this.maxPuntuaciones; x++){

                //ints[x] = 500 + i + x*2;
                ints[x] = 0;
            }

            Json json = new Json();
            ints = this.ordenarArray(ints);
            hashTable.put(String.valueOf(i) , json.toJson(ints));
        }
        this.gameData.put(hashTable);
    }

    /**
     * Introduce el último nivel que ha desbloqueado el jugador
     * @param nivelDesbloqueado
     */
    public void setNivelDesbloqueado(Integer nivelDesbloqueado){
        this.gameData.putInteger("level", nivelDesbloqueado);
    }

    /**
     * Devuelve el ultimo nivel que ha desbloqueado el jugador
     * @return
     */
    public Integer getNivelDesbloqueado(){
        return this.gameData.getInteger("level", 1);
    }

    /**
     * Para definir la ultima nave que ha seleccionado el jugador
     * @param nave
     */
    public void setNaveSeleccionada(Integer nave){
        this.gameData.putInteger("nave", nave);
    }

    /**
     * Devuelve la ultima nave seleccionada por el jugador
     * @return
     */
    public Integer getNaveSeleccionada(){
        return this.gameData.getInteger("nave", 1);
    }

    /**
     * Introduce la ultima nave desbloqueada por el usuario
     * @param desbloqueada
     */
    public void setNaveDesbloqueada(int desbloqueada){
        this.gameData.putInteger("navedes",desbloqueada);
    }

    /**
     * Devuelve la ultima nave desbloqueada por el usuario
     * @return
     */
    public Integer getNaveDesbloqueada(){
        return this.gameData.getInteger("navedes",1);
    }

    /**
     * Para indicar si es la primera primera vez que el jugador ha abierto el juego
     * @param first
     */
    public void setFirstime(boolean first){
        this.gameData.putBoolean("first",  first);
    }

    /**
     * Devuelve si es la primera vez que el jugador ha abierto el juego
     * @return
     */
    public boolean firstTime() {
        return this.gameData.getBoolean("first",true);
    }

    /**
     * Necesario para persistir los cambios. O no se guardaran
     */
    public void saveGameData(){
        this.gameData.flush();
    }
}
