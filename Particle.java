import java.awt.Color;
import java.awt.Graphics;
import java.sql.Time;
import java.util.Random;


public class Particle 
{
	float x;
	float y;
	float xVel;
	float yVel;
	int lifespan;
	boolean used;
	Color c;
	
	Particle()
	{
		x = 10000;
		y = 10000;
		xVel = 0;
		yVel = 0;
		used = false;
		lifespan = 0;
		c = Color.white;
	}
	
	void seed(float px, float py)
	{
		x = px;
		y = py;
		Random gen = new Random();
		xVel = (float) Math.sin(gen.nextFloat()*2*(float)((gen.nextInt(2)-1)*2+1));
		yVel = (float) Math.cos(gen.nextFloat()*2*(float)((gen.nextInt(2)-1)*2+1));
		lifespan = gen.nextInt(50) + gen.nextInt(50);
		used = true;
		c = Color.white;
	}
	
	void seed(float px, float py, Color cl)
	{
		x = px;
		y = py;
		Random gen = new Random();
		xVel = (float) Math.sin(gen.nextFloat()*2*(float)((gen.nextInt(2)-1)*2+1));
		yVel = (float) Math.cos(gen.nextFloat()*2*(float)((gen.nextInt(2)-1)*2+1));
		lifespan = gen.nextInt(100);
		used = true;
		c = cl;
	}
	
	void seed(float px, float py, float dx, float dy, Color cl, Color cl2)
	{
		x = px;
		y = py;
		Random gen = new Random();
		xVel = gen.nextFloat()*2*(float)((gen.nextInt(2)-1)*2+1) + dx/(gen.nextInt(8)+1);
		yVel = gen.nextFloat()*2*(float)((gen.nextInt(2)-1)*2+1) + dy/(gen.nextInt(8)+1);
		lifespan = gen.nextInt(100);
		used = true;
		if(gen.nextInt(2) == 1)
			c = cl;
		else
			c = cl2;
	}
	
	void seedNarrow(float px, float py, float dx, float dy, Color cl, Color cl2)
	{
		x = px;
		y = py;
		int spd = Game.gen.nextInt(8+1);
		xVel = (dx + ((Game.gen.nextFloat()-1))/4)*spd;
		yVel = (dy + ((Game.gen.nextFloat()-1))/4)*spd;
		lifespan = Game.gen.nextInt(30);
		used = true;
		if(Game.gen.nextInt(2) == 1)
			c = cl;
		else
			c = cl2;
	}
	
	void die()
	{
		x = 10000;
		y = 10000;
		xVel = 0;
		yVel = 0;
		used = false;
	}
	
	void draw(Graphics g)
	{
		g.setColor(c);
		g.drawRect((int)(x+(Main.windowWidth/2)-(Player.player.x)),
				(int)(y+(Main.windowHeight/2)-(Player.player.y)), 1, 1);	
	}
	
	
	void move()
	{
		if(used)
		{
			x += xVel;
			y += yVel;
			lifespan--;
		}
	}
	
}
