package com.navejuego;

import com.badlogic.gdx.Game;
import com.navejuego.pantallas.*;

/**
 * Clase Main
 * Clase principal del juego. Si es la primer vez que el usuario abre el juego
 * Se generará una partida nueva. Rellenando las base de datos con los valores por defecto.
 */
public class Main extends Game {
	/**
	 * Método crear donde se inicia el juego y la partida. Tambien se cargan los managers
	 * tanto de Screens como de Assets
	 */
	@Override
	public void create() {
		Constantes.CalculateScreenConstants();
		ScreenManager.getInstance().initialize(this); //Inicializo el AssetManager


		if(PartidaGuardada.getInstance().firstTime() || Constantes.FORCE_FIRST_TIME_GAME_DATA){

			if(Constantes.FILL_TEST_RANKING){
				PartidaGuardada.getInstance().fillPutuacionesPrueba();
			}else{
				PartidaGuardada.getInstance().fillPutuaciones();
			}
			PartidaGuardada.getInstance().setFirstime(false);
			PartidaGuardada.getInstance().setNivelDesbloqueado(0);
			PartidaGuardada.getInstance().setNaveDesbloqueada(0);
			PartidaGuardada.getInstance().setNaveSeleccionada(0);
			Preferencias.getInstance().setMusic(true);
			Preferencias.getInstance().setSound(true);
			Preferencias.getInstance().setVibration(true);
			PartidaGuardada.getInstance().saveGameData();
			Preferencias.getInstance().savePreferences();
			Constantes.FORCE_FIRST_TIME_GAME_DATA = false;
			Constantes.naveUltimaSeleccion=0;
			PartidaGuardada.getInstance().notfirstTime();

		}

		//temporal
		if(Constantes.UNLOCK_ALL_LEVELS){
			PartidaGuardada.getInstance().setNivelDesbloqueado(3);
		}
		if(Constantes.UN_LOCK_ALL_SHIPS){
			PartidaGuardada.getInstance().setNaveDesbloqueada(2);
		}

		//PartidaGuardada.getInstance().printPuntuaciones();

		ScreenManager.getInstance().showScreen(ScreenEnum.LOADING);
	}
}