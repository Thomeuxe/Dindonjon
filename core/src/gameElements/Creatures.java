package gameElements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Creatures {
	
	private Sprite sprite;
	
	private int pv;
	
	private int totalPv;
	
	private int pa;
	
	private int orientation;
	
	private int width;
	
	private int height;
	
	private int posX;
	
	private int posY;
	
	public Creatures(int pv, int pa, Texture img){
		this.pv = pv;
		this.totalPv = pv;
		this.pa = pa;
		this.width = 32;
		this.height = 32;
		this.orientation = (int) Math.round(Math.random()*4);
		this.sprite = new Sprite(img);
		this.sprite.setSize(width, height);
	}
	
	public void update(){
		
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
	
	public void setPos(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public boolean isDead(){
		if(this.pv <= 0){
			return true;
		}else{
			return false;
		}
	}
	
	public void die(){
		this.getSprite().setAlpha(0);
		this.posX = -1000;
		this.posY = -1000;
	}
	
}
