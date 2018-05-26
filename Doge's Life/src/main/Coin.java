package main;

import java.awt.Graphics;

public class Coin {

	private int posX, posY;
	private SpriteSheet mySheet;
	private int animationID = 0;
	private int animFrame = 0;
	private boolean animPlaying = false;
	private int frameInterval = 2;
	private int frameCountdown = 0;
	private int fallSpeed = 10;
	private int poluprecnik=20;
	
	public Coin(SpriteSheet sheet, int X, int Y) {
		posX = X;
		posY = Y;
		mySheet = sheet;
	}
	
	public void update() {
		if(animPlaying) {
			frameCountdown--;
			if(frameCountdown < 0) {
				animationID = (animationID + 1) % mySheet.getRowCount();
				frameCountdown = frameInterval;
			}
		}
	}
	
	public void draw(Graphics g) {
		mySheet.drawTo(g, posX, posY, 0, animationID);
	}
	
	public int getAnimation() {
		return animationID; }
	
	public void setAnimation(int anim) {
		if(anim >= 0 && anim < mySheet.getRowCount())
			animationID = anim;
	}
	
	public int getFrame() {
		return animFrame; }
	
	public void setFrame(int frame) {
		if(frame >= 0 && frame < mySheet.getColumnCount())
			animFrame = frame;
	}
	
	public void play() {
		animPlaying = true; }
	public void pause() {
		animPlaying = false; }
	public void stop() {
		animPlaying = false;
		animFrame = 0;
		frameCountdown = frameInterval;
	}
	
	public boolean isPlaying() {
		return animPlaying; }
	
	public int getAnimationInterval() {
		return frameInterval;
	}

	public void setAnimationInterval(int i) {
		if(i >= 0)
			frameInterval = i;
	}
	
	public void setPosition(int x, int y) {
		posX = x;
		posY = y;
	}
	
	public int getPositionX() {
		return posX;
	}

	public int getPositionY() {
		return posY;
	}
	
	public void move() {
		posY += fallSpeed;
	}

	public int getPoluprecnik() {
		return poluprecnik;
	}

	public void setPoluprecnik(int poluprecnik) {
		this.poluprecnik = poluprecnik;
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
}
