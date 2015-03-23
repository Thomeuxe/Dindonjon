package com.dindonjon;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Level {

	TiledMap map;
	OrthogonalTiledMapRenderer mapRenderer;
	TiledMapTileLayer tileLayer;
	
	public Level(String file){
		TiledMap map = new TmxMapLoader().load(file);
		tileLayer = (TiledMapTileLayer)map.getLayers().get("collision");
		
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		
		Cell cell = tileLayer.getCell(1, 1);
		System.out.println(cell.getTile().getProperties().get("collidable"));
	}

	public OrthogonalTiledMapRenderer getTiledMapRenderer() {
		return mapRenderer;
	}
	
	public Cell getCell(int x, int y){
		Cell cell = tileLayer.getCell(x, y);
		System.out.println(cell.getTile().getProperties().get("collidable"));
		
		return cell;
	}
	
}