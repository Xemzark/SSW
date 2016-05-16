package com.navejuego.entidades.bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.navejuego.Constantes;
import com.navejuego.Explosion;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.*;
import com.navejuego.entidades.patrones.LinealMovement;
import com.navejuego.pantallas.PantallaJuego;

import java.util.ArrayList;

/**
 * Created by beno_ on 20/04/2016.
 */
public class BulletEspecial extends BulletEntity {

    public BulletEspecial(Texture texture, Vector2 posicion, int damage){
        super(texture, posicion, damage);
        movementPattern = new LinealMovement(900.0f, true);
        setSize(50 * Constantes.resizeWidth,70 * Constantes.resizeHeight);
    }
    @Override
    protected void comprobarColision() {
        for(Actor b : PantallaJuego.stage.getActors()){
            //Gdx.app.log("comprovando colision", b.getName());
            if (b instanceof EnemigoEntity){
                EnemigoEntity enemigo = (EnemigoEntity) b;
                if (enemigo.getHitbox().overlaps(this.getHitbox())){
                    enemigo.recibirDmg(damage, ignoraEscudo);
                    //Gdx.app.log("Hit a nave enemiga", "");
                    this.destruirse();
                }
            }
        }
    }

    @Override
    public void destruirse() {

        ArrayList<Texture> explosionTextura = new ArrayList<Texture>();
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo1.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo2.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo3.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo4.png"));
        explosionTextura.add(GestorAssets.getInstance().getTexture("explo5.png"));
        Explosion explo = new Explosion(explosionTextura, new Vector2(getX(),getY()),1.0f,50);
        PantallaJuego.stage.addActor(explo);

        this.remove();
        //Gdx.app.log("Bullet killed!", "");
    }
}
