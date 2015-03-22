package com.dindonjon;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Level {

	TiledMap map;
	OrthogonalTiledMapRenderer mapRenderer;
	
	public Level(String file){
		TiledMap map = new TmxMapLoader().load(file);

		TiledMapTileLayer colisionLayer = (TiledMapTileLayer)map.getLayers().get("collision");
		
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		
		String wall = colisionLayer.getCell(0, 0).getClass();
		System.out.println(wall);
	}

	public OrthogonalTiledMapRenderer getTiledMapRenderer() {
		return mapRenderer;
	}
	
}