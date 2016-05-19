package com.navejuego.entidades.especiales;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.navejuego.Constantes;
import com.navejuego.GestorAssets;
import com.navejuego.Preferencias;
import com.navejuego.pantallas.PantallaJuego;
import com.navejuego.entidades.bullets.BulletEspecial;

/**
 * Clase del AtaqueEspecial Misiles
 */
public class EspecialMisiles extends AtaqueEspecial {

    /**
     * Daño realizado por cada proyectil del ataque
     */
    private int damage;

    /**
     * Número de misiles lanzados por ataque
     */
    private int missileAmount;

    /**
     * Distancia entre missiles en el eje de las X
     */
    private float distanceBetweenMissilesX;

    /**
     * Distancia entre missiles en el eje de las Y
     */
    private float distanceBetweenMissilesY;

    /**
     * Constructor
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

    /**
     * Ejecuta el ataque especial generando tantos proyectiles como se han especificado en
     * missileAmount y calculando la posición de cada uno de ellos.
     */
    public void activar() {

        if(Preferencias.getInstance().soundOn()){
            GestorAssets.getInstance().getSound("misiles.mp3").play();
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


