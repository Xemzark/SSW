package com.navejuego;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.navejuego.pantallas.ScreenEnum;
import com.navejuego.pantallas.ScreenManager;

import java.util.ArrayList;

/**
 * Clase que ejecuta la animación de explosion derivada de la clase Explosion, con la particularidad que permite especificar
 * si despues de la explosión el nivel se termina, redireccionando a la pantalla de victoria
 * o derrota segun como se especifique.
 */
public class ExplosionChain extends Explosion {

    /**
     * Número de veces que ha de repetirse la animación
     */
    int times;

    /**
     * Númeor de veces que se ha ejeutado la animación
     */
    int indiceTotal = 0;

    /**
     * Boleano para especificar si al terminar la animación se ha de redireccionar a otra pantalla
     */
    boolean triggerSceneCHangeOnEnd = false;

    /**
     * Boleano para especificar si el final de la explosión supone una victoria
     */
    boolean triggerIsVictory = false;

    /**
     * Constructor
     * @param texture
     * @param posicion
     * @param duracion
     * @param times
     * @param size
     */
    public ExplosionChain(ArrayList<Texture> texture, Vector2 posicion, float duracion, int times, float size) {
        super(texture, posicion, duracion, size);
        this.times = times;
    }

    /**
     * Define si la explosion supone una victoria de nivel
     * @param victoryOnEnd
     */
    public void setOnEndVictory(boolean victoryOnEnd)
    {
        triggerSceneCHangeOnEnd = victoryOnEnd;
        triggerIsVictory = true;
    }

    /**
     * Define si la explosión supone una derrota de nivel
     * @param defeatOnEnd
     */
    public void setOnEndDefeat(boolean defeatOnEnd)
    {
        triggerSceneCHangeOnEnd = defeatOnEnd;
        triggerIsVictory = false;
    }

    /**
     * Selecciona la siguiente textura de la animación que será dibujada. I si se ha
     * terminado la animacion y se ha especificado una derrota o victoria canvia de pantalla,
     * victory o defeat segun se halla indicado
     * @param delta
     */
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
                    GestorAssets.getInstance().getSound("explosion2.mp3").stop();
                    GestorAssets.getInstance().getSound("explosion2.mp3").play();
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



