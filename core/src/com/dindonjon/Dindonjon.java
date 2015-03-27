package com.dindonjon;

import gameElements.Player;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
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
		

        level = new Level("maps/mapTest.tmx");
		
		//TODO
		//System.out.println(level.getMapWidth());
		
        int height = level.map.getProperties().get("height", Integer.class)*32;
        int width = level.map.getProperties().get("width", Integer.class)*32;
		
		camera = new OrthographicCamera(w, h);
        camera.setToOrtho(true,w,h);
        camera.update();
        //Gdx.input.setInputProcessor(this);
        
        //System.out.println(width);
		
		dindon = new Player(10 , 5, img , "John");
		dindon.getSprite().setPosition(w/2-(dindon.getSprite().getWidth()/2), h/2-(dindon.getSprite().getHeight()/2));
        System.out.println(dindon.getSprite().getX()+";"+dindon.getSprite().getY());
        System.out.println(Gdx.graphics.getWidth());
        
        camera.translate(-w/2+level.startX*32+(dindon.getSprite().getWidth()/2), -h/2+level.startY*32+(dindon.getSprite().getHeight()/2));
        dindon.setPos(level.startX, level.startY);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            //dindon.move(0, 1);
			if(!level.isCollidable(dindon.getPosX(), dindon.getPosY()-1)){
	            camera.translate(0, -32);
	            dindon.setPosY(dindon.getPosY()-1);
	            System.out.println(dindon.getPosY());
			}
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            //dindon.move(1, 0);
			if(!level.isCollidable(dindon.getPosX()+1, dindon.getPosY())){
	            camera.translate(32, 0);
	            dindon.setPosX(dindon.getPosX()+1);
			}
            System.out.println(dindon.getPosX());
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            //dindon.move(0, -1);
			if(!level.isCollidable(dindon.getPosX(), dindon.getPosY()+1)){
	            camera.translate(0, 32);
	            dindon.setPosY(dindon.getPosY()+1);
			}
            System.out.println(dindon.getPosY());
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            //dindon.move(-1, 0);

			if(!level.isCollidable(dindon.getPosX()-1, dindon.getPosY())){
	            camera.translate(-32, 0);
	            dindon.setPosX(dindon.getPosX()-1);
			}
        }
        
        camera.update();
        
        level.getTiledMapRenderer().setView(camera);
        level.getTiledMapRenderer().render();
        
        batch.begin();
        dindon.getSprite().draw(batch);
        batch.end();
	}
}
