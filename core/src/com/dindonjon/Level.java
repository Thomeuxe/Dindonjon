package com.dindonjon;

import gameElements.Enemy;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Level {

	public TiledMap map;
	OrthogonalTiledMapRenderer mapRenderer;
	TiledMapTileLayer tileLayer;
	TiledMapTileLayer metaLayer;
	
	private ArrayList<Cell> enemyCell;
	ArrayList<Enemy> enemies;
	
	int startX;
	int startY;
	
	public Level(String file){
		map = new TmxMapLoader().load(file);
		tileLayer = (TiledMapTileLayer) map.getLayers().get("collision");
		metaLayer = (TiledMapTileLayer) map.getLayers().get("meta");
		
		for(int x = 0; x < metaLayer.getWidth();x++){
            for(int y = 0; y < metaLayer.getHeight();y++){
                Cell cell = metaLayer.getCell(x,y);
                if(cell != null){
                	Object property = cell.getTile().getProperties().get("start");

                	if(property != null){
                        startX = x;
                        startY = tileLayer.getHeight()-y;
                    }
                }
            }
        }
		
		enemyCell = new ArrayList<TiledMapTileLayer.Cell>();
		enemies = new ArrayList<Enemy>();
		
		for(int x = 0; x < metaLayer.getWidth();x++){
            for(int y = 0; y < metaLayer.getHeight();y++){
                Cell cell = metaLayer.getCell(x,y);
                if(cell != null){
                	Object property = cell.getTile().getProperties().get("spawn_enemy");

                	if(property != null){
                		Enemy enemy = new Enemy(10, 1, new Texture("enemy.png"));
                		enemy.setPos(x, y);
                		enemies.add(enemy);
                    }
                }
            }
        }
		
		mapRenderer = new OrthogonalTiledMapRenderer(map);
	}

	public OrthogonalTiledMapRenderer getTiledMapRenderer() {
		return mapRenderer;
	}
	
	public Cell getCell(int x, int y){
		Cell cell = tileLayer.getCell(x, y);
		
		return cell;
	}
	
	public int getMapWidth(){
		return map.getProperties().get("width", Integer.class);
	}
	
	public boolean isCollidable(int x, int y){
		Cell cell = tileLayer.getCell(x, tileLayer.getHeight()-y);
		Object property = cell.getTile().getProperties().get("collidable");
		
		System.out.println(property);
		
		return property != null;
	}
	
	public ArrayList<Cell> getEnemyCell(){
		return enemyCell;
	}
	
	public ArrayList<Enemy> getEnemy(){
		return enemies;
	}
	
}