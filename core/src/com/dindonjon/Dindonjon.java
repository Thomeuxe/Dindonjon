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
	SpriteBatch lifeBarBatch;
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
		lifeBarBatch = new SpriteBatch();
		
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
		player.getLifeBar().setPosition(player.getSprite().getX(), player.getSprite().getY()+36);
        
        camera.translate(-w/2+level.startX*32+(player.getSprite().getWidth()/2), -h/2+(level.tileLayer.getHeight()*32-level.startY*32)+(player.getSprite().getHeight()/2));
        player.setPos(level.startX, level.startY);
        level.setPlayer(player);
	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(25/255f, 25/255f, 25/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            if(level.isEnemy(player.getPosX(), player.getPosY()-1)){
            	Enemy enemy = level.getEnemy(player.getPosX(), player.getPosY()-1);
            	enemy.setPv(enemy.getPv()-player.getPa());
            }
			
			if(!level.isCollidable(player.getPosX(), player.getPosY()-1)){
	            camera.translate(0, 32);
	            player.setPosY(player.getPosY()-1);
			}
			
			for (Enemy enemy : level.getEnemy()) {
            	enemy.move(level, enemy.getHeuristic(player, level), player);
            }
            
			player.getSprite().setRotation(90);
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
        	if(level.isEnemy(player.getPosX()+1, player.getPosY())){
            	Enemy enemy = level.getEnemy(player.getPosX()+1, player.getPosY());
            	enemy.setPv(enemy.getPv()-player.getPa());
            	System.out.println(player.getPosX() + "__" + player.getPosY());
            	System.out.println(enemy.getPosX() + "__" + enemy.getPosY());
            }
        	
			if(!level.isCollidable(player.getPosX()+1, player.getPosY())){
	            camera.translate(32, 0);
	            player.setPosX(player.getPosX()+1);
			}
			
			for (Enemy enemy : level.getEnemy()) {
            	enemy.move(level, enemy.getHeuristic(player, level), player);
            }
			player.getSprite().setRotation(0);
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
        	if(level.isEnemy(player.getPosX(), player.getPosY()+1)){
            	Enemy enemy = level.getEnemy(player.getPosX(), player.getPosY()+1);
            	enemy.setPv(enemy.getPv()-player.getPa());
            }
        	
			if(!level.isCollidable(player.getPosX(), player.getPosY()+1)){
	            camera.translate(0, -32);
	            player.setPosY(player.getPosY()+1);
			}
			
			for (Enemy enemy : level.getEnemy()) {
            	enemy.move(level, enemy.getHeuristic(player, level), player);
            }
			player.getSprite().setRotation(-90);
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
        	if(level.isEnemy(player.getPosX()-1, player.getPosY())){
            	Enemy enemy = level.getEnemy(player.getPosX()-1, player.getPosY());
            	enemy.setPv(enemy.getPv()-player.getPa());
            }
        	
			if(!level.isCollidable(player.getPosX()-1, player.getPosY())){
	            camera.translate(-32, 0);
	            player.setPosX(player.getPosX()-1);
			}
			
			for (Enemy enemy : level.getEnemy()) {
            	enemy.move(level, enemy.getHeuristic(player, level), player);
            }
			player.getSprite().setRotation(180);
        }
        
        camera.update();
        player.update();
		
        
        level.getTiledMapRenderer().setView(camera);
        level.getTiledMapRenderer().render();
        
        batch.begin();
        player.getSprite().draw(batch);
        player.getSprite().setOriginCenter();
        for (Enemy enemy : level.getEnemy()) {
        	enemy.getSprite().setPosition(
        			enemy.getPosX()*32-camera.position.x+Gdx.graphics.getWidth()/2,
        			enemy.getPosY()*32-camera.position.y+Gdx.graphics.getHeight()/2
        		);
        	enemy.update();
        	enemy.getSprite().draw(batch);
        	if(enemy.isDead()){
        		enemy.die();
        	}
		}
        for (Enemy enemy : level.getEnemy()) {
        	enemy.getLifeBar().draw(batch);
        }
        
        player.getLifeBar().draw(batch);
        batch.end();
	}
}
