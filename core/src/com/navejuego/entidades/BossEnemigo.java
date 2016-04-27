package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.navejuego.Constantes;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.patrones.MovementPattern;
import com.navejuego.entidades.powerups.PowerUpEntity;
import com.navejuego.pantallas.PantallaJuego;

import java.util.ArrayList;


import static com.navejuego.Constantes.PIXELS_METRE;

/**
 * Created by Elias on 09/04/2016.
 */

public class BossEnemigo extends EnemigoEntity {

    private ArrayList<MovementPattern> patternList;

    private int patternIndex;
    private float segmentoVida;
    private float nextSegmentoVida;


    public BossEnemigo (int enemyType) {
        // Debe conocer su stage, su textura y su sprite
        super(enemyType);

        //System.out.print("Boss Size: " + getHeight() + ", " + getWidth());

        posX = Gdx.graphics.getWidth() / 2 - getWidth() / 2; // Centra al boss en el eje de las X
        posY = Gdx.graphics.getHeight() - getHeight() * 2; // Situa al boss en la parte superior de la pantalla
        setPosition(posX, posY);
        this.hitbox.setPosition(posX, posY);

        //Carga la succesion de patrones de movimiento del boss
        patternList = new ArrayList<MovementPattern>();
        patternList = enemyProperties.patternList;
        movementPattern = patternList.get(0);
        patternIndex = 1;
        segmentoVida = maxVida/patternList.size();
        nextSegmentoVida = maxVida - segmentoVida;
    }


    /**
     * generarDisparo
     * Este método genera un disparo de la nave cada delta tiempo
    */

    /**
     * Método act
     * Actualiza su posición en tiempo delta
     * @param delta
     */
    @Override
    public void act(float delta) {
        comprobarColisionJugador();
        eliminarseOutOfBounds();

        movementPattern.Move(this, delta);
        //MoveTo(getX(), getY() - (100 * delta));
        generarDisparo(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Esto marca los límites de los bordes verdes del renderdebug
        // Debe corresponderse al tamaño
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    /**
     * TODO: Crear disparos (similar a la función en JugadorEntity)
     */
    /**
     * generarDisparo
     * Este método genera un disparo de la nave cada delta tiempo
     */
    protected void generarDisparo(float delta) {
        tiempoSiguienteDisparo += delta;
        if (tiempoSiguienteDisparo > cadenciaDisparo) {
            Texture bulletTextura = GestorAssets.getInstance().getTexture("bullet.png");
            com.navejuego.entidades.bullets.BulletEnemigo bullet1 = new com.navejuego.entidades.bullets.BulletEnemigo(bulletTextura, new Vector2(getX() + getWidth(), getY()));
            com.navejuego.entidades.bullets.BulletEnemigo bullet2 = new com.navejuego.entidades.bullets.BulletEnemigo(bulletTextura, new Vector2(getX(), getY()));
            bullet1.setName("Bala Enemigo 1");
            PantallaJuego.stage.addActor(bullet1);
            bullet1.setName("Bala Enemigo 2");
            PantallaJuego.stage.addActor(bullet2);
            tiempoSiguienteDisparo = 0;
        }
    }

    /**
     * Esta función procesa el daño que recibe el enemigo en función de su escudo y de
     * su vida. Si se derriba, debe pasarle su puntuación a la nave del jugador y destruirse.
     * @param dmg
     * @param ignoraEscudo
     */

    public void recibirDmg(int dmg, boolean ignoraEscudo) {
        if (ignoraEscudo || escudo <= 0) {
            vida -= dmg;
        } else {
            int temp = dmg;
            dmg -= escudo;
            escudo -= temp;
            if (escudo < 0)
                escudo = 0;
            if (dmg > 0)
                vida -= dmg;
        }
        cambiarPatron();

        if (vida <= 0) {
            darPuntuacion();
            destruirse();
        }
    }

    @Override
    public String getName(){

        return "Boss enemigo";
    }

    @Override
    public void destruirse() {

        animacionExplo();
        GestorAssets.getInstance().getSound("explosion2.wav").play();
        /*animacionExplo();
        GestorAssets.getInstance().getSound("explosion2.wav").play();
        animacionExplo();
        GestorAssets.getInstance().getSound("explosion2.wav").play();
        */
        PantallaJuego.jugador.addPuntos(puntuacion);

        this.remove();
        Gdx.app.log("Boss defeated!", "");
    }


    /**
     * Este método comprueba si ha colisionado con el jugador. De ser así, se autodestruye,
     * causándole daño.
     */
    @Override
    public void comprobarColisionJugador() {

        if(this.getHitbox().overlaps(PantallaJuego.jugador.getHitbox())){
            PantallaJuego.jugador.recibirDmg(this.dañoColision, false);
            Gdx.app.log("HitColisionBoss! a jugador!", "");
        }

    }

    public void cambiarPatron(){

        if(vida < nextSegmentoVida && patternIndex < patternList.size()){
            animacionExplo();
            GestorAssets.getInstance().getSound("explosion2.wav").play();
            movementPattern = patternList.get(patternIndex);
            nextSegmentoVida -= segmentoVida;
            patternIndex += 1;

        }

    }
}