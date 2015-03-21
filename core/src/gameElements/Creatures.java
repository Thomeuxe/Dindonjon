package gameElements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Creatures {
	
	private Sprite sprite;
	
	private int pv;
	
	private int pa;
	
	private int orientation;
	
	private int width;
	
	private int height;
	
	public Creatures(int pv, int pa, Texture img){
		this.pv = pv;
		this.pa = pa;
		this.orientation = (int) Math.round(Math.random()*4);
		this.sprite = new Sprite(img);
	}
	
	public int attack(){
		double rnd_snd = (Math.random()*2-1)+(Math.random()*2-1)+(Math.random()*2-1);
		return (int) Math.round(rnd_snd*(this.pa/4)+this.pa);
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getPa() {
		return pa;
	}

	public void setPa(int pa) {
		this.pa = pa;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	
}
