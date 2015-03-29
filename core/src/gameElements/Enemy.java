package gameElements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Enemy extends Creatures {

	public Enemy(int pv, int pa, Texture img) {
		super(pv, pa, img);
		// TODO Auto-generated constructor stub
	}

	public void move() {
		new MathUtils();
		int rand = MathUtils.random(3);
		
		if(rand == 0)
			this.setPosY(this.getPosY()-1);
		if(rand == 1)
			this.setPosX(this.getPosX()+1);
		if(rand == 2)
			this.setPosY(this.getPosY()+1);
		if(rand == 3)
			this.setPosX(this.getPosX()-1);
	}

}
