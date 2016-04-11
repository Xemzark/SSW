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

        this.tiempoSiguienteDisparo = 0;
        this.cadenciaDisparo = 0.05f;

        //Valores iniciales del Actor
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setPosition(posicion.x - getWidth() / 2, posicion.y - getHeight() / 2);
        setSize(PIXELS_METRE, PIXELS_METRE);
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
                setPosition(getX() + dx, getY() + dy);
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

    /**
     * TODO: Aplica daño al jugador si este no es invulnerable.
     * Primero daña escudos. Si están vacíos, daña la nave.
     * @param dmg
     * @param ignoraEscudo
     */
    protected void recibirDmg(int dmg, boolean ignoraEscudo) {

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

}
