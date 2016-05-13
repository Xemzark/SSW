package com.navejuego;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.navejuego.pantallas.ScreenEnum;
import com.navejuego.pantallas.ScreenManager;

import java.util.ArrayList;

/**
 * Created by Kevin on 29/04/2016.
 */
public class ExplosionChain extends Explosion {
    int times; //Veces que se repite la animacion
    int indiceTotal = 0;

    boolean triggerSceneCHangeOnEnd = false;
    boolean triggerIsVictory = false;

    public ExplosionChain(ArrayList<Texture> texture, Vector2 posicion, float duracion, int times, float size) {
        super(texture, posicion, duracion, size);
        this.times = times;
    }

    public void setOnEndVictory(boolean victoryOnEnd)
    {
        triggerSceneCHangeOnEnd = victoryOnEnd;
        triggerIsVictory = true;
    }

    public void setOnEndDefeat(boolean defeatOnEnd)
    {
        triggerSceneCHangeOnEnd = defeatOnEnd;
        triggerIsVictory = false;
    }

    @Override
    public void act(float delta) {

        if (indiceTotal < times) {
            ttrans += delta;
            if (ttrans >= duracion) {
                indiceTotal += 1 ;
                indiceactual = 0;
                ttrans = 0.0f;

            } else if (duracionframe * (indiceactual + 1) <= ttrans) {
                if(Preferencias.getInstance().soundOn()) {
                    GestorAssets.getInstance().getSound("explosion2.wav").stop();
                    GestorAssets.getInstance().getSound("explosion2.wav").play();
                }
                indiceactual += 1;
                texture = listatextura.get(indiceactual);
            }

        }
        else{
            //Desbloquear siguiente nivel
            if (triggerSceneCHangeOnEnd) {
                if (triggerIsVictory)
                    ScreenManager.getInstance().showScreen(ScreenEnum.VICTORY);
                else
                    ScreenManager.getInstance().showScreen(ScreenEnum.GAME_OVER);
            }
            destruirse();
        }
    }
}



