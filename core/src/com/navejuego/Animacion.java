package com.navejuego;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animacion extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    TextureRegion[] animationFrames;
    Animation animation;
    float elapsedTime;

    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("enemigo.png");

        TextureRegion[][] tmpFrames = TextureRegion.split(img,10,10);

        animationFrames = new TextureRegion[4];
        int index = 0;

        for (int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++) {
                animationFrames[index++] = tmpFrames[j][i];
            }
        }

        animation = new Animation(1f/4f,animationFrames);
    }

    @Override
    public void render () {
        elapsedTime += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(animation.getKeyFrame(elapsedTime,true),0,0);
        batch.end();
    }
}