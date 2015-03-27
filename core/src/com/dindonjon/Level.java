package com.dindonjon;

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
                        startY = y;
                    }
                }
            }
        }
		
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		
		Cell cell = tileLayer.getCell(0, 0);
		//System.out.println(cell.getTile().getProperties().get("collidable"));
	}

	public OrthogonalTiledMapRenderer getTiledMapRenderer() {
		return mapRenderer;
	}
	
	public Cell getCell(int x, int y){
		Cell cell = tileLayer.getCell(x, y);
		//System.out.println(cell.getTile().getProperties().get("collidable"));
		
		return cell;
	}
	
	public int getMapWidth(){
		return map.getProperties().get("width", Integer.class);
	}
	
	public boolean isCollidable(int x, int y){
		Cell cell = tileLayer.getCell(x, y);
		Object property = cell.getTile().getProperties().get("collidable");
		
		System.out.println(property);
		
		return property != null;
	}
	
}