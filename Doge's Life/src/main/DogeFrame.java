package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import effects.Particle;
import gui.Bag;
import nebo.Zvezda;
import nebo.Zvezde;
import particleUtil.ParticleCount;
import rafgfxlib.GameFrame;
import rafgfxlib.Util;

public class DogeFrame extends GameFrame{

	private static final long serialVersionUID = 1L;

	public static final int FRAME_WIDTH=1000;
	public static final int FRAME_HEIGHT=700;
	public static final int COIN_SPAWN=30;
	public static final int DOGE_SPEED=10;
	public static final int NUMBER_OF_STARS=40;
	public static int ticker=0;
	
	public static int brojac = 0;  
	private BufferedImage pozadina = Util.loadImage("slike/pozadina.jpg");

	//lista koinova da se renderuje
	private CoinCounter coins;
	//private Backround pozadina;
	private int coinTickCount=0;
	private Random rnd = new Random();
	private Color c = Color.yellow;

	//private StarField nebo;
	private Bag bag = new Bag();
	private SpriteSheet coinSprite;
	private SpriteSheet dogeSprite;
	private DogeSprite doge;
	private ParticleCount particles;
	private Zvezde zvezde ;
	
	
	public DogeFrame(){
		super("Doge to the moon",FRAME_WIDTH,FRAME_HEIGHT);
		zvezde = new Zvezde();
		initStars();
		particles = new ParticleCount();
		coinSprite = new SpriteSheet("img/coins.png", 1, 4);
		coins = new CoinCounter();
		//pozadina = new Backround("slike/forest.png");
		dogeSprite=new SpriteSheet("img/doge_sprite.png", 4, 9);
		doge = new DogeSprite(dogeSprite,FRAME_WIDTH/2,FRAME_HEIGHT-100);
		
		setHighQuality(true);
		startThread();
	}

	public void drawBG(Graphics g) {
		g.drawImage(pozadina, 0,0, null);
	}
	
	@Override
	public void handleWindowInit() {
		// TODO Auto-generated method stub
	}

	@Override
	public void handleWindowDestroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		// TODO Auto-generated method stub
		//g.drawImage(pozadina.getBackround(), null, 0, 0);
		
		g.drawImage(pozadina,0,0,null);
		
		for(Coin c : coins.getCoins()){
			c.draw(g);
		}

		for(Particle cp : particles.getCoinParticles()){
			cp.render(g);
		}

		for(Zvezda z : zvezde.getZvezde()){
			z.render(g);
		}

		zvezde.refactor();
		bag.render(g);
		doge.draw(g);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		ticker++;
		if(isMouseButtonDown(GFMouseButton.Right))
			addParticle(getMouseX(),getMouseY());
		
		coinTickCount++;
		if(coinTickCount >= COIN_SPAWN){
			coinTickCount=coinTickCount%(COIN_SPAWN);
			Random r = new Random();
			
			int spawn = r.nextInt(FRAME_WIDTH-coinSprite.getFrameWidth());
			
			while(spawn <= coinSprite.getFrameWidth()){
				spawn = r.nextInt(FRAME_WIDTH-coinSprite.getFrameWidth());
			}
			coins.add(new Coin(coinSprite, spawn, 0));
		}

		if(isKeyDown(KeyEvent.VK_RIGHT)){
			doge.move(DOGE_SPEED, 0);
		}else if(isKeyDown(KeyEvent.VK_LEFT)){
			doge.move(-DOGE_SPEED, 0);
		}
		
		int x1,y1,r1,r2;
		
		x1=doge.getCenterX();
		y1=doge.getCenterY();
		r1 = Math.min(dogeSprite.getFrameWidth()/2, dogeSprite.getFrameHeight()/2);
		r2 = Math.min(coinSprite.getFrameWidth()/2, coinSprite.getFrameHeight()/2);

		for(Coin c : coins.getCoins()){
			int x2,y2;
			//1 je doge 2 je coin
			
			x2=c.getPositionX();
			y2=c.getPositionY();
			
			if((x2-x1)*(x2-x1) + (y1-y2)*(y1-y2) <= (r1+r2)*(r1+r2)){
				//System.out.println("Doge colision");
				brojac++;
				
				doge.insertKuglica();
				
				int x = bag.getPosX();
				int y = bag.getPosY();
				for(int i=0;i<10;i++){
					addParticle(x, y);
				}
				bag.setInc(10);
				coins.remove(c);
			}
			c.play();
			c.move();
			c.update();
		}
		int index=0;
		for(Particle cp : particles.getCoinParticles()){
			if(cp.update()){
				particles.remove(cp);
			}
			index++;
		}
		
		particles.refactor();
		coins.refactor();
		doge.update();
		
		for(Zvezda z : zvezde.getZvezde()){
			z.update(ticker);
		}
		ticker%=60;
	}

	@Override
	public void handleMouseDown(int x, int y, GFMouseButton button) {

	}

	@Override
	public void handleMouseUp(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMouseMove(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyDown(int keyCode) {
		// TODO Auto-generated method stub
		 if(keyCode == KeyEvent.VK_LEFT)
		{
			doge.setAnimation(3);
			doge.play();
		}
		else if(keyCode == KeyEvent.VK_RIGHT)
		{
			doge.setAnimation(1);
			doge.play();
		}
	}

	@Override
	public void handleKeyUp(int keyCode) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_UP ||
				keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
			doge.stop();
			doge.setFrame(5);
		}
	}

	public void addParticle(int x,int y){
        Particle p = new Particle(x+random(32),y+random(32),0,0,0,0,c);
        p.setVel(random(1),random(1));
        p.setAcc(0,randomPlus(3));
        p.setLife(randomPlus(150)+550);
        p.setSize(16, 16);
        p.setMaxSize(25,25);
        p.setGrowth(-.5, -.5);
        p.setSizeDeault(true);
        //p.setUltSize(true);
        particles.add(p);
    }
	
	public double random( double num ){
        return (num * 2)  * rnd.nextDouble() - num;
    }
 
    public double randomPlus( double num ){
        double temp = ((num * 2)  * rnd.nextDouble()) - num;
        if( temp < 0 )
            return temp * -1;
        else
            return temp;
    }
	
    public void initStars(){
    	int x,y;
    	for(int i=0;i<DogeFrame.NUMBER_OF_STARS;i++){
			x = rnd.nextInt(FRAME_WIDTH);
			y = rnd.nextInt(DogeFrame.FRAME_HEIGHT/2);
			int scala = (rnd.nextInt(35)%35+28);
			double rotate = rnd.nextDouble()*Math.PI+0.1;
			Zvezda z = new Zvezda(x, y,  scala, rotate);
			zvezde.add(z);
		}
    	zvezde.refactor();
    }
	
	public static void main(String[] args) {
		DogeFrame df = new DogeFrame();
		df.initGameWindow();
	}
}
