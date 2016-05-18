package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.navejuego.Constantes;
import com.navejuego.Explosion;
import com.navejuego.GestorAssets;
import com.navejuego.Preferencias;
import com.navejuego.entidades.patrones.MovementPattern;
import com.navejuego.entidades.patrones.TargetPlayerMovement;
import com.navejuego.entidades.powerups.PowerUpASPD;
import com.navejuego.entidades.powerups.PowerUpEntity;
import com.navejuego.entidades.powerups.PowerUpEscudo;
import com.navejuego.entidades.powerups.PowerUpInvulnerabilidad;
import com.navejuego.entidades.powerups.PowerUpPuntos;
import com.navejuego.entidades.powerups.PowerUpVida;
import com.navejuego.pantallas.PantallaJuego;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Andrés on 03/04/2016.
 */

/**
 * Clase EnemigoEntity
 * Esta clase sirve para generar los enemigos y asociarles las acciones que deben realizar
 * en el tiempo delta.
 */
public class EnemigoEntity extends GameObjectEntity {

    protected int puntuacion;
    protected int danoColision;
    protected int damage = 0;
    protected float cadenciaDisparo;
    protected float tiempoSiguienteDisparo;
    protected boolean vivo;
    protected EnemyType enemyProperties;
    protected MovementPattern bulletMovement;
    protected float bulletSpeed;

    protected float duracion; //segundos
    protected float ttrans; // segundos
    protected int indexmove;
    private ArrayList<MovementPattern> patternList;
    // Variable para generar su posición aleatoria
    Random pos = new Random();

    // Variables para sus posiciones
    protected float posX;
    protected float posY;
    private Sprite spriteEscudo;
    private PowerUpEntity powerUp;
    private int probabilidadPowerUp; //Entre 0% y 100%

    /**
     * Constructor
     * Esta clase recibe una textura a asociarle y un vector de posición.
     * texture sprite a asociarle, gestionado por el assetManager
     * posicion vector de coordenadas x, y para inicializar la posición
     */


    public EnemigoEntity(int enemyType) {
        // Debe conocer su stage, su textura y su sprite

        enemyProperties = new EnemyType(enemyType);
        texture = enemyProperties.texture;
        sprite = enemyProperties.sprite;
        spriteEscudo = enemyProperties.spriteEscudo;
        hitbox = enemyProperties.hitbox;

        puntuacion = enemyProperties.puntuacion;
        cadenciaDisparo = enemyProperties.cadenciaDisparo;
        tiempoSiguienteDisparo = enemyProperties.tiempoSiguienteDisparo;
        vivo = enemyProperties.vivo;
        damage = enemyProperties.damage;
        maxVida = enemyProperties.maxVida;
        vida = enemyProperties.vida;
        maxEscudo = enemyProperties.maxEscudo;
        escudo = enemyProperties.escudo;
        spriteEscudo.setAlpha(Math.min(this.escudo/this.maxEscudo, 0.7f));


        patternList = new ArrayList<MovementPattern>();
        patternList = enemyProperties.patternList;
        movementPattern = patternList.get(0);
        //segundos que tarda en recorrer la pantalla /  cantidad de patrones
        //No toma en cuenta que las naves salen por encima de la pantalla! Eso puede ser un problema.
        //De momento se queda así porque no queda mal a la práctica
        duracion = (Constantes.LOGICAL_HEIGHT / movementPattern.getSpeed()) / patternList.size();
        ttrans = 0.0f;
        indexmove=0;


        danoColision = enemyProperties.danoColision; //Daño que le hace la nave al jugador si colisionan
        this.powerUp = powerUp;
        probabilidadPowerUp = enemyProperties.probabilidadPowerUp;
        bulletMovement = enemyProperties.bulletMovement;
        bulletSpeed = enemyProperties.bulletSpeed;

        //Valores iniciales del Actor
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        //setSize(Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/8);
        setSize(enemyProperties.sizeX * Constantes.resizeWidth, enemyProperties.sizeY * Constantes.resizeHeight);
        spriteEscudo.setSize(this.getWidth(), this.getHeight());

        // Valores aleatorios

        //System.out.print(Gdx.graphics.getWidth() - 2 * ((int) getWidth()) + "\n");
        posX = pos.nextInt(Gdx.graphics.getWidth() - 1 * ((int) getWidth())) + Constantes.LATERAL_BAR_WIDTH; // Posición X aleatoria
        //posX = Gdx.graphics.getWidth()/2;
        posY = Gdx.graphics.getHeight() + getHeight(); // Posición Y por encima de la pantalla
        setPosition(posX, posY);
        hitbox.setPosition(getX() + getWidth() / 2, getY() + getHeight() / 2);
        RecalculateHitboxSize();
        //hitbox.setPosition(posX, posY);
        //Fin valores iniciales del Actor

    }

    /**
     * Método act
     * Actualiza su posición en tiempo delta
     * @param delta
     */
    @Override
    public void act(float delta) {
        comprobarColisionJugador();
        eliminarseOutOfBounds();
        ttrans += delta;
        if ((indexmove+1<patternList.size()) && (ttrans >= duracion)) {
            indexmove += 1;
            movementPattern = patternList.get(indexmove);
            duracion = (Constantes.LOGICAL_HEIGHT / movementPattern.getSpeed()) / patternList.size();
            ttrans = 0.0f;
        }
        movementPattern.Move(this, delta);
        spriteEscudo.setPosition(getX(), getY());
        //MoveTo(getX(), getY() - (100 * delta));
        generarDisparo(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Esto marca los límites de los bordes verdes del renderdebug
        // Debe corresponderse al tamaño
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        spriteEscudo.draw(batch);
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
            Texture bulletTextura = GestorAssets.getInstance().getTexture("proyectilEnemigo.png");
            if (bulletMovement instanceof TargetPlayerMovement){ //si el patron es el dirigido hacia el jugador, queremos que cada bala vaya a su posicion actual
                bulletMovement = new TargetPlayerMovement(bulletSpeed);
            }
            com.navejuego.entidades.bullets.BulletEnemigo bullet = new com.navejuego.entidades.bullets.BulletEnemigo(bulletTextura, new Vector2(getX() + (getWidth() / 2), getY()),damage, bulletMovement);
            bullet.setSize(10.0f * Constantes.resizeWidth, 10.0f * Constantes.resizeHeight);
            bullet.setName("Bala Enemigo");
            PantallaJuego.stage.addActor(bullet);
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
            spriteEscudo.setAlpha(Math.min(this.escudo/this.maxEscudo, 0.7f));
        }

        if (vida <= 0) {
            darPuntuacion();
            destruirse();
        }
    }

    public String getName(){
        return "Nave enemiga";
    }
    /**
     * TODO: Desaparecer/eliminar enemigo.
     */
    public void destruirse() {
        int num_aleatorio = MathUtils.random(1, 100);
        if (num_aleatorio <= probabilidadPowerUp) {
            generarPowerUp();
        }
        animacionExplo();

        JugadorEntity jugador = PantallaJuego.jugador;
        jugador.addPuntos(50);

        if (jugador.getJugadorType().pasiva == JugadorType.PasivasNave.SHIELD_ON_KILL) {
            jugador.subirEscudo(2);
        } else if (jugador.getJugadorType().pasiva == JugadorType.PasivasNave.SPEEDUP_ON_KILL) {
            jugador.setDobleASPD(1);
        }

        if(Preferencias.getInstance().soundOn()){
            GestorAssets.getInstance().getSound("explosion2.mp3").play(0.3f);
        }


        this.remove();
        //Gdx.app.log("Enemy killed!", "");
    }

    public void animacionExplo()
    {
        ArrayList<Texture> explosionTextura = new ArrayList<Texture>();
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo1.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo2.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo3.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo4.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo5.png"));
        Explosion explo = new Explosion(explosionTextura, new Vector2(getX(),getY()),1.0f,100);
        PantallaJuego.stage.addActor(explo);
    }

    /**
     * Si el enemigo se sale de la pantalla sin ser eliminado, se autodestruye
     */
    public void eliminarseOutOfBounds(){
        if ((this.getY()+getHeight()) < 0){
            this.remove();
        }
    }

    /**
     * Le pasa su puntuación al jugador para poder sumársela a su puntuación
     */
    public void darPuntuacion(){
        PantallaJuego.jugador.getPuntuacion().incrementarPuntuacion(this.puntuacion);
    }

    /**
     * Este método comprueba si ha colisionado con el jugador. De ser así, se autodestruye,
     * causándole daño.
     */
    public void comprobarColisionJugador() {

        if(this.getHitbox().overlaps(PantallaJuego.jugador.getHitbox())){
            if (PantallaJuego.jugador.recibirDmg(this.danoColision, false)) {
                this.destruirse();
                //Gdx.app.log("HitColision! a jugador!", "");
            }
        }



    }

    /**
     * TODO: Tirar los dados para ver si genera o no genera el power up.
     */
    private void generarPowerUp() {

        int s_powerup = (int) (Math.random() * 5);
        //System.out.println("Power up" + s_powerup);

        int dados = MathUtils.random(1, 100);

        Vector2 posicion = new Vector2(getX(), getY());
        Texture powerUpTexture;
        PowerUpEntity powerUp;

        if (dados <= 5) { //5%
            powerUpTexture = GestorAssets.getInstance().getTexture("powerup_vida.png");
            powerUp = new PowerUpVida(powerUpTexture, posicion);
        } else if (dados <= 20) { //15%
            powerUpTexture = GestorAssets.getInstance().getTexture("powerup_cadencia.png");
            powerUp = new PowerUpASPD(powerUpTexture, posicion);
        } else if (dados <= 30) { //10%
            powerUpTexture = GestorAssets.getInstance().getTexture("powerup_invulnerable.png");
            powerUp = new PowerUpInvulnerabilidad(powerUpTexture, posicion);
        } else if (dados <= 70) { //40%
            powerUpTexture = GestorAssets.getInstance().getTexture("powerup_score3.png");
            powerUp = new PowerUpPuntos(powerUpTexture, posicion, 1337);
        } else { //30%
            powerUpTexture = GestorAssets.getInstance().getTexture("powerup_shield.png");
            powerUp = new PowerUpEscudo(powerUpTexture, posicion);
        }

        PantallaJuego.stage.addActor(powerUp);

    }

    @Override
    public Circle getHitbox(){
        return this.hitbox;
    }
}

