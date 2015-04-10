package gameElements;

import java.util.Iterator;
import java.util.List;

import org.xguzm.pathfinding.gdxbridge.NavTmxMapLoader;
import org.xguzm.pathfinding.gdxbridge.NavigationTiledMapLayer;
import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import org.xguzm.pathfinding.grid.finders.AStarGridFinder;
import org.xguzm.pathfinding.grid.finders.GridFinderOptions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;
import com.dindonjon.Level;

public class Enemy extends Creatures {
	
	GridCell[][] cells;

	public Enemy(int pv, int pa, Texture img) {
		super(pv, pa, img);
		// TODO Auto-generated constructor stub
	}

	public void move(Level level, double heuristic, Creatures player) {
		if(heuristic <= 10){ //TODO maxSearchDistance
			
			GridCell[][] cells = new GridCell[level.getMapWidth()][level.getMapHeight()];
			
			for (int y = level.getMapHeight() - 1; y >= 0; y--) {
				for (int x = 0; x < level.getMapWidth(); x++){
					int invY = level.getMapHeight() - 1 - y;
					//GridCell cell = new GridCell(x, invY, !level.isCollidable(x, invY) );
					//cells[x][invY] = cell;
					//System.out.println(cell + "___" + !level.isCollidable(x, level.getMapHeight()-(y)) );
				}
			}
			
			for (int x = 0; x < level.getMapWidth(); x++){
				for (int y = 0; y < level.getMapHeight(); y++) {
					int invY = level.getMapHeight() - 1 - y;
					GridCell cell = new GridCell(x, y, !level.isCollidable(x, y+1) );
					cells[x][y] = cell;
					System.out.println(cell + "___" + !level.isCollidable(x, y+1) );
				}
			}
			
			NavigationGrid<GridCell> navGrid = new NavigationGrid(cells);
			
			GridFinderOptions opt = new GridFinderOptions();
			opt.allowDiagonal = false;
			opt.isYDown = true;
			
			AStarGridFinder<GridCell> finder = new AStarGridFinder<GridCell>(GridCell.class, opt);
			List<GridCell> pathToEnd = finder.findPath(this.getPosX(), level.getMapHeight()-this.getPosY()-1, player.getPosX(), player.getPosY()-1, navGrid);
			System.out.println("Enemy : "+this.getPosX()+"_"+(this.getPosY()));
			System.out.println("Dindon : "+player.getPosX()+"_"+player.getPosY());
			System.out.println(pathToEnd);
			
			if(pathToEnd != null && pathToEnd.size() != 1){
				int newPosX = pathToEnd.get(0).x;
				int newPosY = level.getMapHeight()-pathToEnd.get(0).y-1;
				this.setPosX(newPosX);
				this.setPosY(newPosY);
				System.out.println("newEnemy : "+newPosX+"_"+newPosY);
			}
			
			if(pathToEnd != null && pathToEnd.size() == 1){
				this.attack(player);
			}
			
			System.out.println("Enemy : "+this.getPosX()+"_"+(this.getPosY()));
			
			//TiledMap map = new NavTmxMapLoader().load("maps/mapTest.tmx");

			//AStarGridFinder<GridCell> finder = new AStarGridFinder<GridCell>(GridCell.class);
			//MapLayer layer = map.getLayers().get("navigation");
			//NavigationTiledMapLayer nav = (NavigationTiledMapLayer) layer;
			//List<GridCell> path = finder.findPath(this.getPosX(), this.getPosY(), player.getPosX(), player.getPosY(), nav);
			
			//for(GridCell c : path){
				//Gdx.app.log("Map Loading Test - path: ", c.toString());
			//}
			
		}else{
			new MathUtils();
			int rand = MathUtils.random(4);
			//System.out.println(!level.isCollidable(this.getPosX(), this.getPosY()-1));
			
			if(rand == 0){
				if(!level.isCollidable(this.getPosX(), level.getMapHeight()-(this.getPosY()-1))){
					this.setPosY(this.getPosY()-1);
					this.getSprite().setRotation(-90);
				}else{
					this.move(level, heuristic, player);
				}
			}
			
			if(rand == 1){
				if(!level.isCollidable(this.getPosX()+1, level.getMapHeight()-this.getPosY())){
					this.setPosX(this.getPosX()+1);
					this.getSprite().setRotation(0);
				}else{
					this.move(level, heuristic, player);
				}
			}
			
			if(rand == 2){
				if(!level.isCollidable(this.getPosX(), level.getMapHeight()-(this.getPosY()+1))){
					this.setPosY(this.getPosY()+1);
					this.getSprite().setRotation(90);
				}else{
					this.move(level, heuristic, player);
				}
			}
			if(rand == 3){
				if(!level.isCollidable(this.getPosX()-1, level.getMapHeight()-this.getPosY())){
					this.setPosX(this.getPosX()-1);
					this.getSprite().setRotation(180);
				}else{
					this.move(level, heuristic, player);
				}
			}
		}
	}
	
	public double getHeuristic(Player player, Level level){
		int playerX = player.getPosX();
		int playerY = player.getPosY();
		
		int x = this.getPosX();
		int y = this.getPosY();
		
		int dx = playerX - x;
		int dy = level.getMapHeight() - playerY - y;
		
		return Math.sqrt((dx*dx)+(dy*dy));
	}

}
