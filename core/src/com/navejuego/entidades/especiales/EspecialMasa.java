package com.navejuego.entidades.especiales;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.navejuego.AnimacionChain;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.EnemigoEntity;
import com.navejuego.pantallas.PantallaJuego;

import java.util.ArrayList;

/**
 * Created by beno_ on 10/05/2016.
 */
public class EspecialMasa extends AtaqueEspecial {

    private ArrayList<Texture> animacion;

    public EspecialMasa(int delay) {
        super(delay,GestorAssets.getInstance().getTexture("especialexplosion_on.png"), GestorAssets.getInstance().getTexture("especialexplosion_off.png"));
        this.animacion = new ArrayList<Texture>();
        this.animacion.add(GestorAssets.getInstance().getTexture("blue4.png"));
        this.animacion.add(GestorAssets.getInstance().getTexture("blue2.png"));
        this.animacion.add(GestorAssets.getInstance().getTexture("blue4.png"));
        this.animacion.add(GestorAssets.getInstance().getTexture("blue2.png"));
    }

    @Override
    public void activar() {

        System.out.println("Mostrando actores: \n");
        int len = PantallaJuego.stage.getActors().size;

        for(int i = len-1; i >= 0; i--){

            Actor a =PantallaJuego.stage.getActors().get(i);
            System.out.println(a.getName());

            if(a.getName()!= null && a.getName().equals("Nave enemiga")){

                EnemigoEntity enemy = (EnemigoEntity)a;

                float x1 = enemy.getX();
                float y1 = enemy.getY();

                AnimacionChain animacion = new AnimacionChain(this.animacion, new Vector2(x1,y1),0.7f,2,180,GestorAssets.getInstance().getSound("especialdestruir.wav"));
                PantallaJuego.stage.addActor(animacion);
                enemy.destruirse();
            }
        }
    }
}
