import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.JComponent;

public class Vehicle{
	
	public int xPos = 150;
	public int yPos = 300;
	public int width = 0;
	public int height = 0;
	public int life = 20;
    public int speed = 3;
	public boolean idle = true;
	public boolean alive = true;
	public boolean contact = false;

	public BufferedImage image;
	public URL resource = getClass().getResource("slime/idle0.png");

	public Vehicle(Draw comp){
		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public Vehicle(int xPass, int yPass, Draw comp){
		xPos = xPass;
		yPos = yPass;

		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}

		height = image.getHeight();
		width = image.getWidth();
	}

	public void moveTo(int toX, int toY){
		if(xPos<toX){
			xPos = xPos + this.speed;
		}
		else if(xPos>toX){
			xPos = xPos - this.speed;
		}

		if(yPos<toY){
			yPos = yPos + this.speed;
		}
		else if(yPos>toY){
			yPos = yPos - this.speed;
		}
	}

	public void die(Draw compPass){
		idle = false;
		if(alive){
			Thread monThread = new Thread(new Runnable(){
				public void run(){
					for(int ctr = 0; ctr < 4; ctr++){
						try {					
							resource = getClass().getResource("slime/die"+ctr+".png");
							
							try{
								image = ImageIO.read(resource);
							}
							catch(IOException e){
								e.printStackTrace();
							}
					        compPass.repaint();
					        Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					alive = false;
					compPass.checkDeath();
				}
			});
			monThread.start();
		}
	}
}