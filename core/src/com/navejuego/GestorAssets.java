package com.navejuego;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
/**
 * Created by root on 4/04/16.
 */
public class GestorAssets {

    private static GestorAssets instance;
    private AssetManager manager;

    private GestorAssets(){
        manager=new AssetManager();
        create();
    }

    public static GestorAssets getInstance(){
        if (instance == null){
            instance = new GestorAssets();
        }
        return instance;
    }

    public void create() {
        //manager de Assets
        //cargarle todos los assets posibles a usar en la pantalla!
        manager = new AssetManager();
        manager.load("nave.png", Texture.class);
        manager.load("enemigo.png", Texture.class);
        manager.load("alien.png", Texture.class);
        manager.load("bullet.png", Texture.class);
        manager.load("background_1.png", Texture.class);
        manager.load("background_2.png", Texture.class);
        manager.load("background_3.png", Texture.class);
        manager.load("background_4.png", Texture.class);
        manager.load("background_5.png", Texture.class);
        manager.load("background_6.png", Texture.class);
        manager.load("background_7.png", Texture.class);
        manager.load("vidabg.png", Texture.class);
        manager.load("vidafg.png", Texture.class);
        manager.load("vidabgv2.png", Texture.class);
        manager.load("vidafgv2.png", Texture.class);
        manager.load("escudobg.png", Texture.class);
        manager.load("escudofg.png", Texture.class);
        manager.load("shieldbar.png", Texture.class);
        manager.load("corazon.png", Texture.class);
        manager.load("2xShield.png", Texture.class);
        manager.load("addShield.png", Texture.class);
        manager.load("powerup_vida.png", Texture.class);
        manager.load("bulletespecial.png", Texture.class);
        manager.load("botonespecial.png", Texture.class);
        manager.finishLoading();
        //asÃ­ se cargan todos los assets. puede tardar, asÃ­ que mejor hacer pantalla de carga
    }

    public Texture getTexture(String s){ return manager.get(s);}

}