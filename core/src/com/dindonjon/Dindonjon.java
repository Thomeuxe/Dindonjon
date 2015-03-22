package com.dindonjon;

import gameElements.Player;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;
import com.dindonjon.screens.GameScreen;

public class Dindonjon extends Game{
	SpriteBatch batch;
	Texture img;
	Player dindon;
	OrthographicCamera camera;
	Level level;
	
	@Override
	public void create () {
		setScreen(new GameScreen());
		
		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
		batch = new SpriteBatch();
		img = new Texture("dindon.png");
		//dindon = new Sprite(img);
		
		camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        //Gdx.input.setInputProcessor(this);
        level = new Level("maps/mapTest.tmx");
		
		dindon = new Player(10 , 5, img , "John");
		dindon.getSprite().setPosition(w/2 -dindon.getSprite().getWidth()/2, h/2 - dindon.getSprite().getHeight()/2);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            dindon.move(0, 1);
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            dindon.move(1, 0);
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            dindon.move(0, -1);
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            dindon.move(-1, 0);
        }
        
        camera.update();
        
        level.getTiledMapRenderer().setView(camera);
        level.getTiledMapRenderer().render();
        
        batch.begin();
        dindon.getSprite().draw(batch);
        batch.end();
	}
}
