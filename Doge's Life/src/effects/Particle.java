package effects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import particleUtil.Vector2d;
import rafgfxlib.Util;

public class Particle {
		
	private Vector2d location;
    private Vector2d vel;
    private Vector2d acc;
    private Vector2d size;
    private Vector2d maxSize;
    private Vector2d growth;
    private Vector2d life;
    private Color color;
    private Image image;
    
    private boolean ultSize = false;
    private boolean defaultSize = false;
 
    public Particle(double x, double y, double dx, double dy, double size, double life, Color c){
        this.location = new Vector2d(x,y);
        this.vel = new Vector2d(dx,dy);
        this.acc = new Vector2d(0,0);
        this.life = new Vector2d(life,life);
        this.size = new Vector2d(size,size);
        this.growth = new Vector2d(0,0);
        this.maxSize = new Vector2d(0,0);
        this.color = c;
        image = Util.loadImage("img/coins.png").getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    }
 
    public boolean update(){
        vel.add(acc);
        location.add(vel);
        size.add(growth);
        life.x--;
 
        if(life.x <= 0)
            return true;
 
        if(defaultSize){
            if(size.x >= maxSize.x){
                if(size.y >= maxSize.y)
                    return true;
                else
                    size.x = maxSize.x;
            }
            if(size.y >= maxSize.y) 
                size.y = maxSize.y;
            if(size.x <= 0)
                if(size.y <= 0)
                    return true;
                else
                    size.x = 1;
            if(size.y <= 0)
                size.y = 1;
            return false; 
        }
 
        if(ultSize){
            if(size.x > maxSize.x){
                size.x = maxSize.x;
                growth.x *= -1;
            }
            if(size.y > maxSize.y){
                size.y = maxSize.y;
                growth.y *= -1;
            }
            if(size.x <= 0){
                size.x = 1;
                growth.x *= -1;
            }
            if(size.y <= 0){
                size.y = 1;
                growth.y *= -1;
            }
        }
        else{ 
            if(size.x > maxSize.x)
                size.x = maxSize.x;
            if(size.y > maxSize.y)
                size.y = maxSize.y;
            if(size.x <= 0)
                size.x = 1;
            if(size.y <= 0)
                size.y = 1;
        }
        return false;
    }
 
    public void render(Graphics2D g){
        g.setColor(color);
        //g.fillOval((int)(location.x-(size.x/2)), (int)(location.y-(size.y/2)), (int)size.x, (int)size.y);
        g.drawImage(image, (int)(location.x-(size.x/2)), (int)(location.y-(size.y/2)), (int)(location.x-(size.x/2))+20, (int)(location.y-(size.y/2))+20,
        		0, 0, 20, 20/4, null);
    }
 
    public void setLocation(double x,  double y){
        location.x = x;
        location.y = y;
    }
 
    public void setVel(double x,  double y){
        vel.x = x;
        vel.y = y;
    }
 
    public void setAcc(double x,  double y){
        acc.x = x;
        acc.y = y;
    }
 
    public void setSize(double x,  double y){
        size.x = x;
        size.y = y;
    }
 
    public void setMaxSize(double x,  double y){
        maxSize.x = x;
        maxSize.y = y;
    }
 
    public void setGrowth(double x,  double y){
        growth.x = x;
        growth.y = y;
    }
 
    public void setLife(double num){
        life.x = num;
        life.y = num;
    }
 
    public void setSizeDeault(boolean c){
        defaultSize = c;
    }
 
    public void setUltSize(boolean c){
        defaultSize = false;
        ultSize = c;
    }
 
    public Vector2d getLocation(){
        return location;
    }
 
    public Vector2d getVel(){
        return vel;
    }
}
