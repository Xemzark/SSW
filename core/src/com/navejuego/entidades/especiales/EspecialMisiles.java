package com.navejuego.entidades.especiales;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.navejuego.Constantes;
import com.navejuego.GestorAssets;
import com.navejuego.Preferencias;
import com.navejuego.pantallas.PantallaJuego;
import com.navejuego.entidades.bullets.BulletEspecial;

/**
 * Created by beno_ on 20/04/2016.
 */
public class EspecialMisiles extends AtaqueEspecial {

    private int damage;
    private int missileAmount;
    private float distanceBetweenMissilesX;
    private float distanceBetweenMissilesY;

    /**
     *
     * @param delay Segundos entre usos
     * @param damage Daño de cada misil
     * @param missileAmount Cantidad de misiles (procura usar impar)
     * @param distanceBetweenMissilesX Distancia en X con el misil mas lejano, desde el central.
     * @param distanceBetweenMissilesY Distancia en Y con el misil mas lejano, desde el central.
     */
    public EspecialMisiles (int delay, int damage, int missileAmount, float distanceBetweenMissilesX, float distanceBetweenMissilesY){
        super(delay, GestorAssets.getInstance().getTexture("botonespecial.png"),GestorAssets.getInstance().getTexture("botonespecial_no.png"));
        this.damage = damage;
        this.missileAmount = missileAmount;
        this.distanceBetweenMissilesX = distanceBetweenMissilesX * Constantes.resizeWidth;
        this.distanceBetweenMissilesY = distanceBetweenMissilesY * Constantes.resizeHeight;
    }

    public void activar() {

        if(Preferencias.getInstance().soundOn()){
            GestorAssets.getInstance().getSound("misiles.wav").play();
        }

        Texture bulletTextura = GestorAssets.getInstance().getTexture("missil_especial.png");
        float playerX = PantallaJuego.jugador.getX();
        float playerY = PantallaJuego.jugador.getY();

        int centralMissile = missileAmount/2;

        float bulletX = playerX - distanceBetweenMissilesX*centralMissile;
        float bulletY = playerY - distanceBetweenMissilesY*centralMissile;;

        System.out.println("Realizando disparo! \n");
        for (int disparo = 0; disparo < missileAmount; disparo++) {
            BulletEspecial bullet = new BulletEspecial(bulletTextura, new Vector2(bulletX, bulletY), damage);
            bullet.setSize(45 * Constantes.resizeWidth, 60 * Constantes.resizeHeight);
            PantallaJuego.stage.addActor(bullet);
            System.out.println("bala!");
            bulletX += distanceBetweenMissilesX;

            if (disparo < centralMissile) {
                bulletY += distanceBetweenMissilesY;
            } else {
                bulletY -= distanceBetweenMissilesY;
            }
        }
    }

}


