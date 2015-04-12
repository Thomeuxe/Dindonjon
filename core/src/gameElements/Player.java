package gameElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Creatures {
	
	public Player(int pv, int pa, Texture img, String name) {
		super(pv, pa, img);
	}

	public void move(int x, int y) {
		
	}
	
	public void update(){
		if(this.totalPv == this.pv){
			this.lifeBar.setAlpha(0);
			//System.out.println("full");
		}else if(this.pv < this.totalPv && this.pv > 0){
			this.lifeBar.setAlpha(1);
			this.lifeBar.setSize(this.pv/this.totalPv*32, 5);
			this.lifeBar.setPosition(this.sprite.getX(), this.sprite.getY()+36);
			//System.out.println(this.pv/this.totalPv);
		}else{
			Gdx.app.exit();
		}
	}
	
}
