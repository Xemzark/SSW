package com.navejuego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.navejuego.GestorAssets;

import static com.navejuego.Constantes.*;

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
    private boolean invulnerabilidad;
    private int contador = 0;
    private BarraVida barravida;
    private BarraEscudo barraescudo;
    private float maxVida = 100;
    private float maxEscudo = 100;
    private float valorPuntuacion;
    private Puntuacion puntuacion;
    /**
     * Constructor
     * Esta clase recibe una textura a asociarle y un vector de posición.
     * @param texture sprite a asociarle, gestionado por el assetManager
     * @param posicion vector de coordenadas x, y para inicializar la posición
     */
    public JugadorEntity(Stage stage, Texture texture, Vector2 posicion){
        this.stage = stage; //La nave jugador conoce el stage al que pertenece para añadirle bullets
        this.texture = texture;
        this.sprite = new Sprite(this.texture);
        this.hitbox = new Rectangle();

        this.vida = 100;

        this.escudo = 100;

        this.barravida = new BarraVida();
        this.stage.addActor(barravida);
        this.barraescudo = new BarraEscudo();
        stage.addActor(barraescudo);
        this.valorPuntuacion = 0;
        this.puntuacion = new Puntuacion("xxxxxxxxxx");


        this.tiempoSiguienteDisparo = 0;
        this.cadenciaDisparo = 0.05f;

        //Valores iniciales del Actor
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setPosition(posicion.x - getWidth() / 2, posicion.y - getHeight() / 2);
        hitbox.setPosition(getX(), getY());
        setSize(PIXELS_METRE, PIXELS_METRE);
        hitbox.setSize(getWidth(), getHeight());
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
                if (newX < 0) {
                    newX = 0;
                } else if ((newX + getWidth()) > Gdx.graphics.getWidth()) {
                    newX = Gdx.graphics.getWidth() - getWidth();
                }
                if (newY < 0) {
                    newY = 0;
                } else if ((newX + getHeight()) > Gdx.graphics.getHeight()) {
                    //TODO: Ajustar por barra de acción
                    newY = Gdx.graphics.getHeight() - getHeight();
                }

                setPosition(newX, newY);
                hitbox.setPosition(getX(), getY());


            }
        });
        //Fin de reacción al drag
    }

    @Override
    public void act(float delta) {
        generarDisparo(delta);
        //
            //BLABLABLA
        //
        eliminarDisparo();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        barravida.render(batch);
        barraescudo.render(batch);
        this.puntuacion.draw(batch);
    }


    /**
     * generarDisparo
     * Este método genera un disparo de la nave cada delta tiempo
     */
    protected void generarDisparo(float delta) {
        tiempoSiguienteDisparo += delta;
        if (tiempoSiguienteDisparo > cadenciaDisparo) {
            Texture bulletTextura = GestorAssets.getInstance().getTexture("bullet.png");
            BulletNave bullet = new BulletNave(this.stage, bulletTextura, new Vector2(getX() + (getWidth() / 2), getY() + getHeight()));
            bullet.setName("Bala " + contador);
            this.stage.addActor(bullet);
            tiempoSiguienteDisparo = 0;
        }
    }

    /**
     * eliminarDisparo
     * Este método elimina los disparos que se salen por arriba de la pantalla
     */
    private void eliminarDisparo(){
        // No me gusta el INSTANCEOF!
        for(Actor a : this.stage.getActors()){
            if(a instanceof BulletEntity && (a.getY()>Gdx.graphics.getHeight())){
                a.remove();
            }
        }
    }

    /*
     * Primero daña escudos. Si están vacíos, daña la nave.
     * No recibe daño si es invulnerable.
     * @param dmg Daño que aplica.
     * @param ignoraEscudo Si es cierto, ignora escudo.
     */
    public void recibirDmg(int dmg, boolean ignoraEscudo) {
        if (invulnerabilidad)
            return;

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

        if (vida <= 0)
            destruirse();

        updateUI();
    }

    public void updateUI(){
        barravida.update();
        barraescudo.update();
    }

    /**
     * TODO: Incrementa la vida.
     * @param cura
     */
    public void curarse(int cura) {

    }

    /**
     * TODO: Incrementa el escudo.
     * @param escudo
     */
    public void subirEscudo(int escudo) {

    }

    /**
     * TODO: Activa secuencia de destrucción de la nave.
     */
    public void destruirse() {
        setPosition(-100, -100);
        hitbox.setPosition(-100,-100);
        remove();
    }

    /**
     * TODO: Dado un PowerUp, modifica las stats.
     */
    public void modificarStats(PowerUpEntity powerUp) {

    }

    /**
     * TODO: Si el ataque especial puede activarse, lo activa. Devuelve cierto si lo ha activado.
     */
    public boolean activarAtaqueEspecial() {
        return false;
    }

    public float getMaxVida(){
        return this.maxVida;
    }

    public float getMaxEscudo() { return this.maxEscudo; }

}
