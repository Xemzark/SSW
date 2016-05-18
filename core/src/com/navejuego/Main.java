package com.navejuego;

import com.badlogic.gdx.Game;
import com.navejuego.pantallas.PantallaJuego;
import com.navejuego.GestorAssets;
import com.navejuego.pantallas.PantallaMenu;
import com.navejuego.pantallas.ScreenManager;
import com.navejuego.pantallas.*;

/**
 * Clase Main
 * Clase principal del juego. Si es la primer vez que el usuario abre el juego
 * Se generará una partida nueva. Rellenando las base de datos con los valores por defecto.
 */
public class Main extends Game {
	/**
	 * Método crear
	 */
	@Override
	public void create() {
		Constantes.CalculateScreenConstants();
		ScreenManager.getInstance().initialize(this); //Inicializo el AssetManager
		GestorAssets.getInstance().create();

		if(PartidaGuardada.getInstance().firstTime() || Constantes.forceFirstTimeGameData){
			System.out.println("Iniciando juego por primera vez");
			if(Constantes.fillTestRanking){
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
		}

		//temporal
		if(Constantes.unlockAllLevels){
			PartidaGuardada.getInstance().setNivelDesbloqueado(3);
		}
		if(Constantes.unLockAllShips){
			PartidaGuardada.getInstance().setNaveDesbloqueada(2);
		}

		//Preferencias.getInstance().setMusic(true);
       // Preferencias.getInstance().setSound(true);
        //Preferencias.getInstance().setVibration(true);
		//Preferencias.getInstance().savePreferences();

		PartidaGuardada.getInstance().printPuntuaciones();

		ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
	}
}