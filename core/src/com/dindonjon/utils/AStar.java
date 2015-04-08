package com.dindonjon.utils;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMap;

import javafx.collections.transformation.SortedList;

public class AStar {
	
	/** The set of nodes that have been searched through */
	private ArrayList closed = new ArrayList();
	/** The set of nodes that we do not yet consider fully searched */
	//private SortedList open = new SortedList();
	
	
	
	/** The map being searched */
	private TiledMap map;
	/** The maximum depth of search we're willing to accept before giving up */
	private int maxSearchDistance;
	
	/** The complete set of nodes across the map */
	//private Node[][] nodes;
	/** True if we allow diaganol movement */
	private boolean allowDiagMovement;
	/** The heuristic we're applying to determine which nodes to search first */
	private double heuristic;
}
