package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.navejuego.Constantes;
import com.navejuego.ExplosionChain;
import com.navejuego.GestorAssets;
import com.navejuego.Preferencias;
import com.navejuego.entidades.bullets.BulletNave;
import com.navejuego.entidades.ui.Barra;
import com.navejuego.entidades.ui.Puntuacion;
import com.navejuego.pantallas.PantallaJuego;
import com.navejuego.entidades.especiales.AtaqueEspecial;

import java.util.ArrayList;

/**
 * Created by Andrés on 03/04/2016.
 */

/**
 * Clase JugadorEntity
 * Esta clase sirve para generar la nave del jugador y asociarle reacciones a eventos que
 * esté escuchando del stage al que esté asociada.
 */
public class JugadorEntity extends GameObjectEntity {

    protected float cadenciaDisparo;
    protected float tiempoSiguienteDisparo;

    private AtaqueEspecial ataqueEspecial;

    private boolean invulnerabilidad; //tiene periodo de invulnerabilidad?
    private long inicioInvulnerabilidad = 0;
    private float duracionInvulnerabilidad = 0;
    private boolean parpadeo; //controla el parpadeo de la nave mientras es invulnerable
    private int damage;

    private boolean dobleASPD = false; //tiene powerup doble velocidad disparo?
    private long inicioDobleASPD = 0;
    private int duracionDobleASPD = 0;

    private Barra barravida;
    private Barra barraescudo;
    private Puntuacion puntuacion;

    private Texture noNave; //textura "en blanco" para hacer desaparecer la nave (invencibilidad)
    private Texture currentTexture; //textura en uso actualmente (para invencibilidad)
    private Sprite spriteEscudo;
    private JugadorType jugadorProperties;

    private float parpadeoInvulnerabilidad;


    /**
     * Constructor
     * Esta clase recibe una textura a asociarle y un vector de posición.
     * @param posicion vector de coordenadas x, y para inicializar la posición
     */
    public JugadorEntity(Vector2 posicion, int jtype){

        this.jugadorProperties = new JugadorType(jtype);

        this.texture = this.jugadorProperties.textura;
        this.currentTexture = this.texture;
        this.damage = this.jugadorProperties.damage;

        this.sprite = new Sprite(this.texture);
        this.sprite.setSize(80 * Constantes.resizeWidth, 80 * Constantes.resizeHeight);
        this.hitbox = new Circle();
        this.noNave = GestorAssets.getInstance().getTexture("noNave.png");

        this.invulnerabilidad = false;
        this.puntuacion = new com.navejuego.entidades.ui.Puntuacion();
        this.maxVida = jugadorProperties.maxVida;
        this.maxEscudo = jugadorProperties.maxEscudo;
        this.vida = maxVida;
        this.escudo = maxEscudo;
        this.vida = jugadorProperties.vida;
        this.escudo = jugadorProperties.escudo;
        this.ataqueEspecial = jugadorProperties.getEspecial(jugadorProperties.especial);
        PantallaJuego.stage.addActor(this.ataqueEspecial);
        // Sprite animación escudo
        this.spriteEscudo = new Sprite(jugadorProperties.texturaEscudo);
        spriteEscudo.setAlpha(0.7f);
        //

        this.barravida = new Barra (GestorAssets.getInstance().getTexture("vidabgv2.png"),
                GestorAssets.getInstance().getTexture("vidafgv2.png"),
                GestorAssets.getInstance().getTexture("corazon.png"),
                Constantes.lateralBarWidth * 1.5f,
                new Vector2(5, 150),
                false, 200.0f);
        PantallaJuego.stage.addActor(barravida);
        this.barraescudo = new Barra (GestorAssets.getInstance().getTexture("escudobg.png"),
                GestorAssets.getInstance().getTexture("escudofg.png"),
                GestorAssets.getInstance().getTexture("shieldbar.png"),
                Constantes.lateralBarWidth * 1.5f,
                new Vector2(5, 395),
                false, 200.0f);
        PantallaJuego.stage.addActor(barraescudo);
        updateUI();

        this.tiempoSiguienteDisparo = 0;
        this.cadenciaDisparo = jugadorProperties.cadenciaDisparo;

        //Valores iniciales del Actor
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setPosition(posicion.x - getWidth() / 2, posicion.y - getHeight() / 2);
        setSize(sprite.getWidth(), sprite.getHeight());
        hitbox.setPosition(getX() + getWidth() / 2, getY() + getHeight() / 2); //x, y, radio (20% del ancho de la nave)
        RecalculateHitboxSize();
        spriteEscudo.setPosition(getX(), getY());
        spriteEscudo.setSize(getWidth(), getHeight());

        //Fin de valores iniciales del Actor

        //Inicio de reacción al drag
        super.setTouchable(Touchable.enabled); //Objeto se marca como touchable
        addListener(new DragListener() { //Le añado un dragListener; reaccionar a eventos de drag
            /**
             * Método touchDragged
             * Si se hace drag del actor, qué debe suceder.
             * @param event Evento de acción sobre el actor captado por el stage al que pertenece
             * @param x X actual
             * @param y Y actual
             * @param pointer Dedo que presiona la pantalla simultáneamente; donde 0 es el primero, 1 el segundo, ...
             */
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                //System.out.print("Me muevo" + getX() + " " + getY() + "\n");
                //recalcular para que la posición sea relativa al objeto y no a la pantalla
                //porque x e y devuelven el pixel del centro del sprite, no el 0,0 relativo al sprite
                float dx = x - getWidth() * 0.5f;
                float dy = y - getHeight() * 0.5f;
                //recalcular para que la posición sea relativa al objeto y no a la pantalla
                float newX = getX() + dx;
                float newY = getY() + dy;
                //si se sale da pantalla, ajustar
                if (newX < Constantes.lateralBarWidth + 10.0f) {
                    newX = Constantes.lateralBarWidth + 10.0f;
                } else if ((newX + getWidth()) > Gdx.graphics.getWidth()) {
                    newX = Gdx.graphics.getWidth() - getWidth();
                }
                if (newY < 0) {
                    newY = 0;
                } else if ((newY + getHeight()) > Gdx.graphics.getHeight()) {
                    //TODO: Ajustar por barra de acción
                    newY = Gdx.graphics.getHeight() - getHeight();
                }

                MoveTo(newX, newY);
                spriteEscudo.setPosition(newX, newY);
            }
        });
        //Fin de reacción al drag
    }

    @Override
    public void act(float delta) {

        generarDisparo(delta);

        if (invulnerabilidad) {
            parpadeoInvulnerabilidad += delta;
            if (!parpadeo && parpadeoInvulnerabilidad >= 0.2f){ //cambia el sprite por uno en blanco, para indicar que se es invulnerabl
                currentTexture = noNave;
                parpadeo = true;
                parpadeoInvulnerabilidad = 0.0f;
            }
            else if (parpadeo && parpadeoInvulnerabilidad >= 0.2f){
                currentTexture = texture;
                parpadeo = false;
                parpadeoInvulnerabilidad = 0.0f;
            }
            contadorInvulnerabilidad();
        }
        if (dobleASPD) {
            contadorDobleASPD();
        }
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(currentTexture, getX(), getY(), getWidth(), getHeight());
        spriteEscudo.draw(batch);
        barravida.render(batch);
        barraescudo.render(batch);
        this.puntuacion.draw(batch);
        this.ataqueEspecial.draw(batch,parentAlpha);
    }

    /**
     * generarDisparo
     * Este método genera un disparo de la nave cada delta tiempo
     */

    protected void generarDisparo(float delta) {
        tiempoSiguienteDisparo += delta;
        if (tiempoSiguienteDisparo > cadenciaDisparo) {
            Texture bulletTextura = GestorAssets.getInstance().getTexture("bullet.png");
            if (jugadorProperties.pasiva == JugadorType.PasivasNave.DUAL_SHOTS) {
                //Bullet 1
                BulletNave bullet = new BulletNave(bulletTextura,
                        new Vector2(getX() + getWidth() * 0.2f,
                                getY() + getHeight()),
                        this.damage);
                PantallaJuego.stage.addActor(bullet);
                //Bullet 2
                bullet = new BulletNave(bulletTextura,
                        new Vector2(getX() + getWidth() * 0.8f,
                                getY() + getHeight()),
                        this.damage);
                PantallaJuego.stage.addActor(bullet);
            } else {
                BulletNave bullet = new BulletNave(bulletTextura,
                        new Vector2(getX() + (getWidth() / 2),
                                getY() + getHeight()),
                        this.damage);
                PantallaJuego.stage.addActor(bullet);
            }
            tiempoSiguienteDisparo = 0;
        }
    }

    /*
     * Primero daña escudos. Si están vacíos, daña la nave.
     * No recibe daño si es invulnerable.
     * Actualiza el estado del escudo (sprite)
     * @param dmg Daño que aplica.
     * @param ignoraEscudo Si es cierto, ignora escudo.
     */
    public boolean recibirDmg(int dmg, boolean ignoraEscudo) {
        if (invulnerabilidad)
            return false;

        if (ignoraEscudo || escudo <= 0) {
            vida -= dmg;
        } else {
            int temp = dmg;
            dmg -= escudo;
            escudo -= temp;

            if (escudo <= 0) //Escudo destruido
                escudo = 0;
            if (dmg > 0)
                vida -= dmg;

            spriteEscudo.setAlpha(Math.min(this.escudo / this.maxEscudo, 0.7f));
        }

        if (vida <= 0) {
            destruirse();
        }
        if(Preferencias.getInstance().vibrationOn()){
            Gdx.input.vibrate(300);
        }

        if (jugadorProperties.pasiva == JugadorType.PasivasNave.BLINK_TIME_ON_HIT) {
            setInvulnerabilidad(1.5f);
        }

        updateUI();
        return true;
    }

    public void updateUI(){
        barravida.Update(vida/maxVida);
        barraescudo.Update(escudo / maxEscudo);
    }

    public Puntuacion getPuntuacion() {
        return puntuacion;
    }

    public int getPuntuacionInt() {
        return Integer.parseInt(puntuacion.getPuntuacion());
    }

    /**
     * Incrementa la vida si el parametro de cura es positivo.
     * Nunca la incrementa por encima del máximo.
     * @param cura Putnos de vida a incrementar. Valores negativos no hacen efecto.
     */
    public void curarse(int cura) {
        if (cura > 0 && vida > 0) {
            vida = Math.min(vida + cura, maxVida);
            updateUI();
        }

    }

    /**
     * Incrementa el escudo si el parametro de escudo es positivo.
     * Nunca lo incrementa por encima del máximo.
     * Actualiza el estado del escudo (sprite)
     * @param escudo  Putnos de escudo a incrementar. Valores negativos no hacen efecto.
     */
    public void subirEscudo(int escudo) {
        if (escudo > 0) {
            this.escudo = Math.min(this.escudo + escudo, maxEscudo);
            updateUI();
            spriteEscudo.setAlpha(Math.min(this.escudo / this.maxEscudo, 0.7f));
        }
    }

    /**
     * Destruye la nave.
     */
    public void destruirse() {
        animacionExploChain();
        setPosition(-100, -100);
        hitbox.setPosition(-100, -100);
        Constantes.lastScore = Integer.parseInt(this.getPuntuacion().getPuntuacion());
        remove();
    }

    protected void animacionExplo()
    {
        ArrayList<Texture> explosionTextura = new ArrayList<Texture>();
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo1.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo2.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo3.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo4.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo5.png"));
        com.navejuego.Explosion explo = new com.navejuego.Explosion(explosionTextura, new Vector2(getX(),getY()),1.0f, 100);
        PantallaJuego.stage.addActor(explo);
    }

    public float getMaxVida(){
        return this.maxVida;
    }

    public float getMaxEscudo() { return this.maxEscudo; }

    public boolean getInvulnerabilidad (){
        return invulnerabilidad;
    }

    public void setInvulnerabilidad (float duracion) {
        if (!invulnerabilidad) {
            this.invulnerabilidad = true;
            this.inicioInvulnerabilidad = TimeUtils.millis();
            this.duracionInvulnerabilidad = duracion;
            this.parpadeo = false;
            this.parpadeoInvulnerabilidad = 0.0f;
        } else {
            this.duracionInvulnerabilidad += duracion;
        }
    }

    protected void contadorInvulnerabilidad(){
        if (TimeUtils.timeSinceMillis(inicioInvulnerabilidad) > duracionInvulnerabilidad * 1000){
            invulnerabilidad = false;
            parpadeo = false;
            currentTexture = texture;
            inicioInvulnerabilidad = 0;
            duracionInvulnerabilidad = 0;
        }
    }

    public void setDobleASPD(int duracion){

        if (!this.dobleASPD) {
            this.dobleASPD = true;
            this.inicioDobleASPD = TimeUtils.millis();
            this.duracionDobleASPD = duracion;
            this.cadenciaDisparo /= 2;
        } else {
            duracionDobleASPD += duracion;
        }
    }

    protected void contadorDobleASPD() {

        if (TimeUtils.timeSinceMillis(inicioDobleASPD) > duracionDobleASPD * 1000){
            dobleASPD = false;
            inicioDobleASPD = 0;
            duracionDobleASPD = 0;
            this.cadenciaDisparo *= 2;

        }
    }

    protected void animacionExploChain(){

        ArrayList<Texture> explosionTextura = new ArrayList<Texture>();
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo1.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo2.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo3.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo4.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo5.png"));
        ExplosionChain explo = new ExplosionChain(explosionTextura, new Vector2(getX(),getY()),1.0f,3, 100);
        explo.setOnEndDefeat(true);
        PantallaJuego.stage.addActor(explo);
    }

    public JugadorType getJugadorType(){
        return this.jugadorProperties;
    }

    public void addPuntos(int puntos){
        this.puntuacion.incrementarPuntuacion(puntos);
    }

    public void RecalculateHitboxSize() {
        hitbox.setRadius(getWidth() / 5);
    }
}
