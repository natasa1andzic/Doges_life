package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import rafgfxlib.Util;

public class SpriteSheet {

	private BufferedImage sheet;
	private BufferedImage originalsheet;
	private int frameW, frameH;
	private int sheetW, sheetH;
	private int offsetX = 0, offsetY = 0;
	private BufferedImage specialEffect;
	
	public SpriteSheet(String imageName, int columns, int rows) {
		sheet = Util.loadImage(imageName);
		originalsheet = sheet;
		if(imageName == null) {
			sheet = null;
			System.out.println("Error loading sprite sheet!");
			return;
		}
		
		sheetW = columns;
		sheetH = rows;
		frameW = sheet.getWidth() / sheetW;
		frameH = sheet.getHeight() / sheetH;
	}

	public int getColumnCount() {
		return sheetW;
	}
	public int getRowCount() {
		return sheetH;
	}
	public int getFrameWidth() {
		return frameW;
	}
	public int getFrameHeight() {
		return frameH;
	}
	
	public void drawTo(Graphics g, int posX, int posY, int frameX, int frameY) {
		if(sheet == null) return;
		if(frameX < 0 || frameY < 0 || frameX >= sheetW || frameY >= sheetH) return;
		
		g.drawImage(sheet,
				posX - offsetX, posY - offsetY, 
				posX - offsetX + frameW, posY - offsetY + frameH, 
				frameX * frameW, frameY * frameH, 
				frameX * frameW + frameW, frameY * frameH + frameH, 
				null);
	}
	
	public void removeEffect(){
		sheet=originalsheet;
	}
	
	public void setOffsets(int x, int y)
	{
		offsetX = x;
		offsetY = y;
	}
	
	public void setOffsetX(int x) { offsetX = x; }
	public void setOffsetY(int y) { offsetY = y; }
	public int getOffsetX() { return offsetX; }
	public int getOffsetY() { return offsetY; }

	public BufferedImage getSheet() {
		return sheet;
	}

	public void setSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}

	public int getFrameW() {
		return frameW;
	}

	public void setFrameW(int frameW) {
		this.frameW = frameW;
	}

	public int getFrameH() {
		return frameH;
	}

	public void setFrameH(int frameH) {
		this.frameH = frameH;
	}

	public int getSheetW() {
		return sheetW;
	}

	public void setSheetW(int sheetW) {
		this.sheetW = sheetW;
	}

	public int getSheetH() {
		return sheetH;
	}

	public void setSheetH(int sheetH) {
		this.sheetH = sheetH;
	}
	
	
public void applyBinary() {
		WritableRaster source = sheet.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), true);
		int rgb[] = new int[4];
		
		for (int y=0;y<source.getHeight();y++) {
			for (int x=0;x<source.getWidth();x++) {
				source.getPixel(x, y, rgb);
				int i = (int)(rgb[0] * 0.30 + rgb[1] * 0.59 + rgb[2] * 0.11);
				
				if(i > 160)
					i = 255;
				else
					i = 0;
				rgb[0] = i;
				rgb[1] = i;
				rgb[2] = i;
				target.setPixel(x, y, rgb);
			}
		}
		sheet = Util.rasterToImage(target);
	}
	
	// metoda 2//
	
	public void applyGrayscale() {
		WritableRaster source = sheet.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), true);
		int rgb[] = new int[4];
		
		for (int y=0;y<source.getHeight();y++) {
			for (int x=0;x<source.getWidth();x++) {
				source.getPixel(x, y, rgb);
				if(rgb[3]!= 255)continue;
				int i=(rgb[0]+rgb[1]+rgb[2])/3;
				rgb[0]=i;
				rgb[1]=i;
				rgb[2]=i;
				target.setPixel(x, y, rgb);
			}
		}
		sheet = Util.rasterToImage(target);
	}
	
	// metoda 3//
	
	public void applyNegative() {
		WritableRaster source = sheet.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), true);
		int rgb[] = new int[4];
		
		for (int y=0;y<source.getHeight();y++) {
			for (int x=0;x<source.getWidth();x++) {
				source.getPixel(x, y, rgb);
				rgb[0] = 255 - rgb[0];
				rgb[1] = 255 - rgb[1];
				rgb[2] = 255 - rgb[2];
				target.setPixel(x, y, rgb);
			}
		}
		
		sheet = Util.rasterToImage(target);
		
	}
	
	// metoda 4//
	
	public void setBlue() {
		WritableRaster source = sheet.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), false);
		
		int rgb[] = new int[4];
		
		for(int y = 0; y < source.getHeight(); y++) {
			for(int x = 0; x < source.getWidth(); x++) {
				source.getPixel(x, y, rgb);
				rgb[0] = 0;
				rgb[1] = 0;
				target.setPixel(x, y, rgb);
			}
		}
		sheet = Util.rasterToImage(target);

	}
	
	// metoda 5//
	
	public void setRed() {
		WritableRaster source = sheet.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), false);
		int rgb[] = new int[4];
		
		for(int y = 0; y < source.getHeight(); y++) {
			for(int x = 0; x < source.getWidth(); x++) {
				source.getPixel(x, y, rgb);
				rgb[1] = 0;
				rgb[2] = 0;
				target.setPixel(x, y, rgb);
			}
		}
		sheet = Util.rasterToImage(target);
}
	
	// metoda 6//
	
	public void setGreen() {
		WritableRaster source = sheet.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), true);
	
		int rgb[] = new int[4];
	
		for(int y = 0; y < source.getHeight(); y++) {
			for(int x = 0; x < source.getWidth(); x++) {
				source.getPixel(x, y, rgb);
				rgb[0] = 0;
				rgb[2] = 0;
				target.setPixel(x, y, rgb);
			}
		}
		sheet = Util.rasterToImage(target);
	}
}
