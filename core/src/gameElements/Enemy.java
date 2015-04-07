package gameElements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.dindonjon.Level;

public class Enemy extends Creatures {

	public Enemy(int pv, int pa, Texture img) {
		super(pv, pa, img);
		// TODO Auto-generated constructor stub
	}

	public void move(Level level, double heuristic) {
		if(heuristic <= 2){ //TODO maxSearchDistance
			
		}else{
			new MathUtils();
			int rand = MathUtils.random(4);
			//System.out.println(!level.isCollidable(this.getPosX(), this.getPosY()-1));
			
			if(rand == 0){
				if(!level.isCollidable(this.getPosX(), level.getMapHeight()-(this.getPosY()-1))){
					this.setPosY(this.getPosY()-1);
					this.getSprite().setRotation(-90);
				}else{
					this.move(level, 0);
				}
			}
			
			if(rand == 1){
				if(!level.isCollidable(this.getPosX()+1, level.getMapHeight()-this.getPosY())){
					this.setPosX(this.getPosX()+1);
					this.getSprite().setRotation(0);
				}else{
					this.move(level, 0);
				}
			}
			
			if(rand == 2){
				if(!level.isCollidable(this.getPosX(), level.getMapHeight()-(this.getPosY()+1))){
					this.setPosY(this.getPosY()+1);
					this.getSprite().setRotation(90);
				}else{
					this.move(level, 0);
				}
			}
			if(rand == 3){
				if(!level.isCollidable(this.getPosX()-1, level.getMapHeight()-this.getPosY())){
					this.setPosX(this.getPosX()-1);
					this.getSprite().setRotation(180);
				}else{
					this.move(level, 0);
				}
			}
		}
	}
	
	public double getHeuristic(Player player){
		int playerX = player.getPosX();
		int playerY = player.getPosY();
		
		int x = this.getPosX();
		int y = this.getPosY();
		
		int dx = playerX - x;
		int dy = y - playerY;
		
		return Math.sqrt((dx*dx)+(dy*dy));
	}

}
