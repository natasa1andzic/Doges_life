package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import main.DogeFrame;
import rafgfxlib.Util;

public class Bag {

	private Image image;
	private int posX;
	private int posY;
	private int x=1,y=0;
	private int inc = 0;
	private String string = "Coins collected: ";
	private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
	
	public Bag(){
		BufferedImage temp = Util.loadImage("img/elven bag.png");
		image = temp.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		posX = DogeFrame.FRAME_WIDTH/2 + DogeFrame.FRAME_WIDTH/4+40;
		posY=35;
	}

	public void render(Graphics2D g){
		int dx1,dy1,dx2,dy2,sx1,sy1,sx2,sy2;
		dx1 = DogeFrame.FRAME_WIDTH/2 + DogeFrame.FRAME_WIDTH/4;
		dy1 = 15;
		dx2 = dx1+image.getWidth(null);
		dy2 = image.getHeight(null);
		
		sx1=0;
		sy1=0;
		sx2=image.getWidth(null);
		sy2=image.getHeight(null);
		
		g.setColor(Color.YELLOW);
		g.fillOval(dx1-15, dy1-15, sx2+27+inc, sy2+27+inc);
		g.setColor(Color.BLUE);
		g.fillOval(dx1-7, dy1-7, sx2+12+inc, sy2+12+inc);
		
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString(string, DogeFrame.FRAME_WIDTH/2 + DogeFrame.FRAME_WIDTH/4, 160);
		g.drawString(Integer.toString(DogeFrame.brojac), DogeFrame.FRAME_WIDTH/2 + DogeFrame.FRAME_WIDTH/4+150, 160);
		g.drawImage(image, dx1+inc/2, dy1+inc/2, dx2+inc/2, dy2+inc/2, sx1, sy1, sx2, sy2, null);
		
		if(inc > 0){
			inc--;
		}
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

	public int getInc() {
		return inc;
	}

	public void setInc(int inc) {
		this.inc = inc;
	}

}
