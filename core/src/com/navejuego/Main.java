package com.navejuego;

import com.badlogic.gdx.Game;
import com.navejuego.pantallas.PantallaJuego;
import com.navejuego.scene2dObsoleto.SingletonAssetManager;

/**
 * Clase Main
 * Clase principal del juego.
 */
public class Main extends Game {
	/**
	 * Método crear
	 */
	@Override
	public void create() {
		SingletonAssetManager.getInstance(); //Inicializo el AssetManager

		setScreen(new PantallaJuego(this));
	}
}