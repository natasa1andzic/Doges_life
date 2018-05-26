package main;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gui.DBallM;

public class DogeSprite {

	public static final int DOGE_SCALE=3;
	
	private int[] PARTICLES;
	private int posX, posY;
	private SpriteSheet mySheet;
	private int animationID = 0;
	private int animFrame = 0;
	private boolean animPlaying = false;
	private int frameInterval = 2;
	private int frameCountdown = 0;
	private double dx; 
	private double dy;
	private ArrayList<DBallM> kuglice = new ArrayList<>();
	private int centerX,centerY;
	private int wx,wy;
	//private BufferedImage pozadina = Util.loadImage("slike/pozadina.jpg");
	
	public DogeSprite(SpriteSheet sheet, int X, int Y) {
		posX = X;
		posY = Y;
		
		mySheet = sheet;
		BufferedImage novo=mySheet.getSheet();
		int w = novo.getWidth()*DOGE_SCALE;
		int h = novo.getHeight()*DOGE_SCALE;
		BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(DOGE_SCALE,DOGE_SCALE);
		AffineTransformOp scaleOp = 
		   new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(novo, after);
		
		mySheet.setSheet(after);
		//zbog skaliranja
		mySheet.setFrameH(after.getHeight()/mySheet.getSheetH());
		mySheet.setFrameW(after.getWidth()/mySheet.getSheetW());
		centerX = posX+mySheet.getFrameW()/2;
		centerY = posY+mySheet.getFrameHeight()/2+15;
		
		dx=mySheet.getFrameW()/2;
		dy=mySheet.getFrameH()/4;
		insertKuglica();
		wx = mySheet.getFrameW()/2;
		wy = mySheet.getFrameH()/2;
	}
	
	public void update() {
		//KUGLICE (na levi klik se spawnuje)
		
		int pomx,pomy;
		pomx=mySheet.getFrameWidth()/2;
		pomy=mySheet.getFrameH()/2;

		for(DBallM k:kuglice){
			
			k.update(centerX, centerY, dx, dy, pomx);
			
			/*k.x+=k.dir*BRZINA_KUGLICA;
			if(k.dir == -1){
				//System.out.println("Iznad ");
				k.y= centerY+(int)(-(dy/dx)* Math.sqrt(dx*dx-(k.x-centerX)*(k.x-centerX)));
				
				//System.out.println("Iznad 2 "+k.y);
			}else{
				//System.out.println("ISPOD");
				k.y=centerY+(int)((dy/dx)* Math.sqrt((dx)*dx-(k.x-centerX)*(k.x-centerX)));
				
			}
			
			if(k.x >= centerX + pomx || k.x <= centerX - pomx){
				k.dir*=-1;
			}*/
			
		}
		//KUGLICE

		if(animPlaying) {
			frameCountdown--;
			if(frameCountdown < 0) {
				animFrame = (animFrame + 1) % mySheet.getColumnCount();
				frameCountdown = frameInterval;
			}
		}
	}
	
	public void draw(Graphics g) {
		//kugle koje su iza y= (dy*sqrt(dx*dx-x*x))/dx

		
		for(DBallM db : kuglice){
			if(db.dir == -1){
				db.draw(g,centerY,wy);
			}
		}
		//mySheet.applyNegative();
		mySheet.drawTo(g, posX, posY, animFrame, animationID);
		
		//kugle koje su ispred y= -(dy/dx)* sqrt(dx*dx-x*x)
		for(DBallM db : kuglice){
			if(db.dir == 1){
				db.draw(g,centerY,wy);
			
			}
		}
	}
	
	public int getAnimation() {
		return animationID; }
	
	public void setAnimation(int anim) {
		if(anim >= 0 && anim < mySheet.getRowCount())
			animationID = anim;
	}
	
	public int getFrame() {
		return animFrame;
	}
	
	public void setFrame(int frame) {
		if(frame >= 0 && frame < mySheet.getColumnCount())
			animFrame = frame;
	}
	
	public void play() {
		animPlaying = true;
	}
	public void pause() {
		animPlaying = false;
	}
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
	
	public void move(int movX, int movY) {
		for(DBallM d: kuglice){
			d.x+=movX;
			d.y+=movY;
		}
		posX += movX;
		posY += movY;
		centerX+=movX;
		centerY+=movY;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public SpriteSheet getMySheet() {
		return mySheet;
	}

	public void setMySheet(SpriteSheet mySheet) {
		this.mySheet = mySheet;
	}
	
	public void insertKuglica(){
		kuglice.add(new DBallM((int)(centerX-dx),(int)(centerY+1)));
	}
}
