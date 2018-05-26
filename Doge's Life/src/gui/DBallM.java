package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Random;

import rafgfxlib.Util;

public class DBallM {

	public static final int BALL_WIDTH=20;
	public static final int BRZINA_KUGLICA=5;
	public static final int GLOW=15;
	public static final int GLOW_DISTANCE=40;
	
	private String path="img/balls.png";
	private Color color= Color.yellow;
	public int x,y;
	public int dir=1;
	private double resize=1;
	private Image img;
	private int kuglaRed,kuglaKolona;
	private int imDH,imDW;
	private boolean isLaunched=false;
	int[] rgb;
	int pp= BALL_WIDTH/2+GLOW;
	private WritableRaster grad;
	
	public DBallM(int x,int y){
		this.x = x;
		this.y = y;
		
		img = Util.loadImage(path).getScaledInstance(600, 300, Image.SCALE_SMOOTH);
		Random r = new Random();
		kuglaRed = r.nextInt(2);
		kuglaKolona = r.nextInt(3);
		imDH=img.getHeight(null)/2;
		imDW=img.getWidth(null)/4;
		rgb = new int[4];
		Color c = boja();
		color=c;
		
		rgb[0]=color.getRed();
		rgb[1]=color.getGreen();
		rgb[2]=color.getBlue();
		rgb[3]=255;
		grad = Util.createRaster(pp+GLOW_DISTANCE, pp+GLOW_DISTANCE, true);

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	public void draw(Graphics g,int centerY,int wy){
		g.setColor(color);
		
		/*if(y <= centerY+wy && y >= centerY){
			resize -= 0.5;
		}else if(y>= centerY-wy && y<=centerY){
			resize += 0.5;
		}*/
		
		
		//outerGlow
		
		int cx = grad.getWidth();
		int cy = grad.getWidth();
		
		for(int x=0;x<cx;x++){
			for(int y=0;y<cy;y++){
				int dx = cx/2-x;
				int dy = cy/2-y;
				int daljina = (int)Math.sqrt(dx * dx + dy * dy);
				if(daljina<=pp){
					double fD = daljina / (double)pp;
					rgb[3]=(int)((1.0 - fD) * 255);
					grad.setPixel(x, y, rgb);
				}
			}
		}
		g.drawImage(Util.rasterToImage(grad), x-(pp+GLOW_DISTANCE)/2, y-(pp+GLOW_DISTANCE)/2, null);
		
		g.drawImage(img, x-BALL_WIDTH/2, y-BALL_WIDTH/2, x-BALL_WIDTH/2+BALL_WIDTH, y-BALL_WIDTH/2+BALL_WIDTH,
				imDW*kuglaKolona, imDH*kuglaRed,imDW*kuglaKolona+imDW , imDH*kuglaRed+imDH, null);
	}
	
	
	public void update(int centerX,int centerY,double dx,double dy,int pomx){
		
		if(isLaunched == false){
		x+=dir*BRZINA_KUGLICA;
		if(dir == -1){
			//System.out.println("Iznad ");
			y= centerY+(int)(-(dy/dx)* Math.sqrt(dx*dx-(x-centerX)*(x-centerX)));
			
			//System.out.println("Iznad 2 "+k.y);
		}else{
			//System.out.println("ISPOD");
			y=centerY+(int)((dy/dx)* Math.sqrt((dx)*dx-(x-centerX)*(x-centerX)));
			
		}
		
		if(x >= centerX + pomx || x <= centerX - pomx){
			dir*=-1;
		}
		}else{
			
		}
	}
	
	public Color boja(){
		if(kuglaRed == 0 && kuglaKolona == 0){
			return new Color(255, 14, 14);
		}else if(kuglaRed == 0 && kuglaKolona == 1){
			return new Color(232, 27, 250);
		}else if(kuglaRed == 0 && kuglaKolona == 2){
			return new Color(174, 23, 255);
		}else if(kuglaRed == 0 && kuglaKolona == 3){
			return new Color(30, 22, 203);
		}else if(kuglaRed == 1 && kuglaKolona == 0){
			return new Color(253,232,111);
		}else if(kuglaRed == 1 && kuglaKolona == 1){
			return new Color(113, 71, 155);
		}else if(kuglaRed == 1 && kuglaKolona == 2){
			return new Color(0, 140, 100);
		}else if(kuglaRed == 1 && kuglaKolona == 3){
			return new Color(75, 7, 145);
		}else{
			return Color.white;
		}
	}
}
