package com.dindonjon;

import java.util.ArrayList;

import gameElements.Enemy;
import gameElements.Player;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Input;
import com.dindonjon.screens.GameScreen;

public class Dindonjon extends Game{
	SpriteBatch batch;
	Texture img;
	Player player;
	OrthographicCamera camera;
	Level level;
    boolean animation;
	
	@Override
	public void create () {
		setScreen(new GameScreen());
		
		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
		batch = new SpriteBatch();
		img = new Texture("dindon.png");

        level = new Level("maps/mapTest.tmx");
        
        //enemies = new ArrayList<Enemy>();
		
        //int height = level.map.getProperties().get("height", Integer.class)*32;
        //int width = level.map.getProperties().get("width", Integer.class)*32;
		
		camera = new OrthographicCamera(w, h);
        camera.setToOrtho(false,w,h);
        camera.update();
		
		player = new Player(10 , 5, img , "John");
		player.getSprite().setPosition(w/2-(player.getSprite().getWidth()/2), h/2-(player.getSprite().getHeight()/2));
        
        camera.translate(-w/2+level.startX*32+(player.getSprite().getWidth()/2), -h/2+(level.tileLayer.getHeight()*32-level.startY*32)+(player.getSprite().getHeight()/2));
        player.setPos(level.startX, level.startY);
        
        System.out.println(player.getPosX()+"-->"+player.getPosY());
        
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(25/255f, 25/255f, 25/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
			if(!level.isCollidable(player.getPosX(), player.getPosY()-1)){
	            camera.translate(0, 32);
	            player.setPosY(player.getPosY()-1);
	            for (Enemy enemy : level.getEnemy()) {
	            	enemy.move();
	            }
			}
			player.getSprite().setRotation(90);
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
			if(!level.isCollidable(player.getPosX()+1, player.getPosY())){
	            camera.translate(32, 0);
	            player.setPosX(player.getPosX()+1);
	            for (Enemy enemy : level.getEnemy()) {
	            	enemy.move();
	            }
			}
			player.getSprite().setRotation(0);
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
			if(!level.isCollidable(player.getPosX(), player.getPosY()+1)){
	            camera.translate(0, -32);
	            player.setPosY(player.getPosY()+1);
	            for (Enemy enemy : level.getEnemy()) {
	            	enemy.move();
	            }
			}
			player.getSprite().setRotation(-90);
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
			if(!level.isCollidable(player.getPosX()-1, player.getPosY())){
	            camera.translate(-32, 0);
	            player.setPosX(player.getPosX()-1);
	            for (Enemy enemy : level.getEnemy()) {
	            	enemy.move();
	            }
			}
			player.getSprite().setRotation(180);
        }
        
        camera.update();
		
        
        level.getTiledMapRenderer().setView(camera);
        level.getTiledMapRenderer().render();
        
        batch.begin();
        for (Enemy enemy : level.getEnemy()) {
        	enemy.getSprite().setPosition(
        			enemy.getPosX()*32-camera.position.x+Gdx.graphics.getWidth()/2,
        			enemy.getPosY()*32-camera.position.y+Gdx.graphics.getHeight()/2
        		);
        	enemy.getSprite().draw(batch);
		}
        
        player.getSprite().draw(batch);
        player.getSprite().setOriginCenter();
        
        batch.end();
	}
}
