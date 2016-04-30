package com.navejuego;

import com.badlogic.gdx.Game;
import com.navejuego.pantallas.PantallaJuego;
import com.navejuego.GestorAssets;
import com.navejuego.pantallas.PantallaMenu;

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
		Constantes.CalculateScreenConstants();
		GestorAssets.getInstance(); //Inicializo el AssetManager
		setScreen(new PantallaJuego(this));
	}
}