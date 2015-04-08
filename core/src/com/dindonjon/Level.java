package com.dindonjon;

import gameElements.Enemy;
import gameElements.Player;

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
	public String file;
	OrthogonalTiledMapRenderer mapRenderer;
	public TiledMapTileLayer tileLayer;
	TiledMapTileLayer metaLayer;
	
	private ArrayList<Cell> enemyCell;
	ArrayList<Enemy> enemies;
	Player player;
	
	int startX;
	int startY;
	
	public Level(String file){
		map = new TmxMapLoader().load(file);
		tileLayer = (TiledMapTileLayer) map.getLayers().get("collision");
		metaLayer = (TiledMapTileLayer) map.getLayers().get("meta");
		this.file = file;
		
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
	
	public void setPlayer(Player player){
		this.player = player;
	}

	public OrthogonalTiledMapRenderer getTiledMapRenderer() {
		return mapRenderer;
	}
	
	public Cell getCell(int x, int y){
		Cell cell = tileLayer.getCell(x, y);
		
		return cell;
	}
	
	public int getMapHeight(){
		return map.getProperties().get("height", Integer.class);
	}
	
	public int getMapWidth(){
		return map.getProperties().get("width", Integer.class);
	}
	
	public boolean isCollidable(int x, int y){
		Cell cell = tileLayer.getCell(x, tileLayer.getHeight()-y);
		//System.out.println(this.getSingleEnemy(x, y));
		
		if(this.isCellFree(x, y) == true){
			if(cell != null){
				Object property = cell.getTile().getProperties().get("collidable");
				return property != null;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
	
	public ArrayList<Cell> getEnemyCell(){
		return enemyCell;
	}
	
	public ArrayList<Enemy> getEnemy(){
		return enemies;
	}
	
	public boolean isCellFree(int posX, int posY){
		for (Enemy enemy : enemies) {
			if(enemy.getPosX() == posX && enemy.getPosY() == tileLayer.getHeight()-posY){
				return false;
			}
		}
		if(this.player.getPosX() == posX && this.player.getPosY() == posY){
			return false;
		}
		return true;
	}
	
	public boolean isEnemy(int posX, int posY){
		for (Enemy enemy : enemies) {
			if(enemy.getPosX() == posX && enemy.getPosY() == tileLayer.getHeight()-posY){
				return true;
			}
		}
		return false;
	}
	
	public Enemy getEnemy(int posX, int posY){
		for (Enemy enemy : enemies) {
			if(enemy.getPosX() == posX && enemy.getPosY() == tileLayer.getHeight()-posY){
				return enemy;
			}
		}
		return null;
	}
}