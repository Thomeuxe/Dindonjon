package gameElements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class Creatures {
	
	private Sprite sprite;
	
	private float pv;
	
	private float totalPv;
	
	private int pa;
	
	private int orientation;
	
	private int width;
	
	private int height;
	
	private int posX;
	
	private int posY;
	
	private Sprite lifeBar;
	
	public Creatures(int pv, int pa, Texture img){
		this.pv = pv;
		this.totalPv = pv;
		this.pa = pa;
		this.width = 32;
		this.height = 32;
		this.orientation = (int) Math.round(Math.random()*4);
		this.sprite = new Sprite(img);
		this.sprite.setSize(width, height);
		
		this.lifeBar = new Sprite();
		this.lifeBar.setTexture(new Texture("lifeBar.png"));
		this.lifeBar.setSize(32, 5);
	}
	
	public void update(){
		if(this.totalPv == this.pv){
			this.lifeBar.setAlpha(0);
			//System.out.println("full");
		}else{
			this.lifeBar.setAlpha(1);
			this.lifeBar.setSize(this.pv/this.totalPv*32, 5);
			this.lifeBar.setPosition(this.sprite.getX(), this.sprite.getY()+36);
			//System.out.println(this.pv/this.totalPv);
		}
	}
	
	public void attack(Creatures player){
		double rnd_snd = (Math.random()*2-1)+(Math.random()*2-1)+(Math.random()*2-1);
		int attack = (int) Math.round(rnd_snd*(this.pa/4)+this.pa);
		player.setPv(player.getPv() - attack);
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public Sprite getLifeBar(){
		return lifeBar;
	}

	public float getPv() {
		return pv;
	}

	public void setPv(float pv) {
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
