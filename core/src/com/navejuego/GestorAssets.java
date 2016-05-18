package com.navejuego;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Clase encargada de gestionar y cargar los assets del proyecto.
 */
public class GestorAssets {

    /**
     * Instancia GestorAssets para Singleton
     */
    private static GestorAssets instance;

    /**
     * Objeto AssetManager de Libgdx, donde se realizará la carga de los Assets
     */
    private AssetManager manager;

    /**
     * Constructor
     */
    private GestorAssets(){
        manager=new AssetManager();
        create();
    }

    /**
     * Singleton para GestorAssets
     * @return instancia GestorAssets
     */
    public static GestorAssets getInstance(){
        if (instance == null){
            instance = new GestorAssets();
        }
        return instance;
    }

    /**
     * Metodo encargado de especificar y cargar todos los assets: Texturas, sonidos y música.
     */
    public void create() {

        //naves
        manager = new AssetManager();
        manager.load("nave1.png", Texture.class);
        manager.load("nave2.png", Texture.class);
        manager.load("nave3.png", Texture.class);
        manager.load("noNave.png", Texture.class);

       //bosses
        manager.load("boss1.png", Texture.class);
        manager.load("boss2.png", Texture.class);
        manager.load("boss3.png", Texture.class);
        manager.load("boss4.png", Texture.class);

        //balas
        manager.load("bullet.png", Texture.class);
        manager.load("proyectilEnemigo.png", Texture.class);
        manager.load("escudoEnemigo.png", Texture.class);

        //enemigos
        manager.load("heavyfreighter.png", Texture.class);
        manager.load("alien1.png", Texture.class);
        manager.load("alien2.png", Texture.class);
        manager.load("alien3.png", Texture.class);
        manager.load("alien4.png", Texture.class);
        manager.load("orangeship.png", Texture.class);
        manager.load("orangeship2.png", Texture.class);
        manager.load("orangeship3.png", Texture.class);
        manager.load("smallorange.png", Texture.class);
        manager.load("greenship1.png", Texture.class);
        manager.load("greenship2.png", Texture.class);
        manager.load("greenship3.png", Texture.class);
        manager.load("greenship4.png", Texture.class);
        manager.load("blueship1.png", Texture.class);
        manager.load("blueship2.png", Texture.class);
        manager.load("blueship3.png", Texture.class);
        manager.load("blueship4.png", Texture.class);

        //Background
        manager.load("background_1.png", Texture.class);
        manager.load("background_3.png", Texture.class);
        manager.load("background_4.png", Texture.class);
        manager.load("background_5.png", Texture.class);
        manager.load("background_6.png", Texture.class);
        manager.load("background_7.png", Texture.class);
        manager.load("background_garaje.png", Texture.class);
        manager.load("otherskin/ajustess.png", Texture.class);
        manager.load("background_ajustes.png", Texture.class);
        manager.load("background_menuprincipal.png", Texture.class);
        manager.load("background_gameover.png", Texture.class);
        manager.load("background_niveles.png", Texture.class);
        manager.load("background_victory.png", Texture.class);
        manager.load("background_ranking.png", Texture.class);

        //UI
        manager.load("vidabgv2.png", Texture.class);
        manager.load("vidafgv2.png", Texture.class);
        manager.load("escudobg.png", Texture.class);
        manager.load("escudofg.png", Texture.class);
        manager.load("escudoNave.png", Texture.class);
        manager.load("shieldbar.png", Texture.class);
        manager.load("corazon.png", Texture.class);
        manager.load("2xShield.png", Texture.class);
        manager.load("vidabossbg.png", Texture.class);
        manager.load("vidabossfg.png", Texture.class);
        manager.load("boss_corazon.png", Texture.class);
        manager.load("barraloca.png", Texture.class);

        //Power Ups
        manager.load("powerup_vida.png", Texture.class);
        manager.load("powerup_shield.png", Texture.class);
        manager.load("powerup_cadencia.png", Texture.class);
        manager.load("powerup_score3.png", Texture.class);
        manager.load("powerup_invulnerable.png", Texture.class);

        //ataques especiales
        manager.load("botonespecial.png", Texture.class);
        manager.load("botonespecial_no.png", Texture.class);
        manager.load("missil_especial.png", Texture.class);

        manager.load("especial_curacion_off.png", Texture.class);
        manager.load("especial_curacion_on.png", Texture.class);

        manager.load("especialexplosion_off.png", Texture.class);
        manager.load("especialexplosion_on.png", Texture.class);

        //animaciones
        manager.load("explo1.png", Texture.class);
        manager.load("explo2.png", Texture.class);
        manager.load("explo3.png", Texture.class);
        manager.load("explo4.png", Texture.class);
        manager.load("explo5.png", Texture.class);

        manager.load("electric1.png", Texture.class);
        manager.load("electric2.png", Texture.class);
        manager.load("electric3.png", Texture.class);
        manager.load("electric4.png", Texture.class);
        manager.load("electric5.png", Texture.class);

        manager.load("blue4.png", Texture.class);
        manager.load("blue2.png", Texture.class);

        //Músicas
        //http://www.dl-sounds.com/royalty-free/category/space/
        manager.load("SpaceLoungeLoop.mp3", Music.class);
        manager.load("SpaceCube.mp3", Music.class);
        manager.load("SpaceTrip.mp3", Music.class);
        manager.load("SpaceGate.mp3", Music.class);
        manager.load("Starlight.mp3", Music.class);
        manager.load("defeat.mp3", Music.class);
        manager.load("victory.mp3", Music.class);

        //Sonidos
        //http://soundbible.com/tags-explosion.html
        manager.load("explosion.mp3", Sound.class);
        manager.load("powerup.mp3", Sound.class);
        manager.load("explosion2.mp3", Sound.class);
        manager.load("laser.mp3", Sound.class);
        manager.load("especialdestruir.mp3", Sound.class);
        manager.load("misiles.mp3", Sound.class);

        manager.finishLoading();
    }

    /**
     * Devuelve el progreso de carga de los Assets
     * @return progreso
     */
    public float retornarProgress(){
        return manager.getProgress();
    }

    /**
     * Devuelve un update del manager
     * @return update, manager
     */
    public boolean retornarUpdate(){
        return manager.update();
    }

    /**
     * Devuelve la textura especificada
     * @param texture
     * @return textura
     */
    public Texture getTexture(String texture){
        return manager.get(texture);
    }

    /**
     * Devuelve la musica especificada
     * @param music
     * @return musica
     */
    public Music getMusic(String music){
        return manager.get(music);
    }

    /**
     * Devuelve el sonido espefificado
     * @param sound
     * @return sonido
     */
    public Sound getSound(String sound){
        return manager.get(sound);
    }



}