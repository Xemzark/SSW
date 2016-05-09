package com.navejuego.entidades.especiales;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.navejuego.AnimacionChain;
import com.navejuego.Constantes;
import com.navejuego.ExplosionChain;
import com.navejuego.GestorAssets;
import com.navejuego.entidades.EnemigoEntity;
import com.navejuego.pantallas.PantallaJuego;

import java.util.ArrayList;

/**
 * Created by beno_ on 09/05/2016.
 */
public class EspecialRegeneracion extends AtaqueEspecial {


    public EspecialRegeneracion(int delay) {
        super(delay, GestorAssets.getInstance().getTexture("especial_curacion_on.png"), GestorAssets.getInstance().getTexture("especial_curacion_off.png"));
    }

    @Override
    public void activar() {

        ArrayList<Texture> arrayTextura = new ArrayList<Texture>();
        ArrayList<Texture> arrayTextura2 = new ArrayList<Texture>();
        arrayTextura.add(GestorAssets.getInstance().getTexture("electric1.png"));
        arrayTextura.add(GestorAssets.getInstance().getTexture("electric2.png"));
        arrayTextura.add(GestorAssets.getInstance().getTexture("electric3.png"));
        arrayTextura.add(GestorAssets.getInstance().getTexture("electric4.png"));
        arrayTextura.add(GestorAssets.getInstance().getTexture("electric5.png"));

        arrayTextura2.add(GestorAssets.getInstance().getTexture("electric1.png"));
        arrayTextura2.add(GestorAssets.getInstance().getTexture("electric2.png"));
        arrayTextura2.add(GestorAssets.getInstance().getTexture("electric3.png"));
        arrayTextura2.add(GestorAssets.getInstance().getTexture("electric4.png"));
        arrayTextura2.add(GestorAssets.getInstance().getTexture("electric5.png"));

        float x1 = PantallaJuego.jugador.getX() + 80 * Constantes.resizeWidth/2;
        float y1 = PantallaJuego.jugador.getY() - (80 * Constantes.resizeHeight/2);

        float x2 = PantallaJuego.jugador.getX() - (80 * Constantes.resizeWidth/2)*3.7f;
        float y2 = PantallaJuego.jugador.getY() - (80 * Constantes.resizeHeight/2);

        AnimacionChain animacion = new AnimacionChain(arrayTextura, new Vector2(x1,y1),0.3f,2,180,GestorAssets.getInstance().getSound("laser.wav"));
        AnimacionChain animacion2 = new AnimacionChain(arrayTextura2, new Vector2(x2,y2),0.3f,2,180,GestorAssets.getInstance().getSound("laser.wav"));
        //explo.setOnEndVictory(true);
        PantallaJuego.stage.addActor(animacion);
        PantallaJuego.stage.addActor(animacion2);


        PantallaJuego.jugador.subirEscudo(100);
        PantallaJuego.jugador.curarse(100);
    }
}
