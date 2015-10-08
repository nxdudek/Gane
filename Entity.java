import java.util.ArrayList;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Entity
{
	static ArrayList<Entity> entities = new ArrayList<Entity>();
	float x;
	float y;
	float xVel;
	float yVel;
	float weight;
	int height;
	int width;
	int health;
	float maxPointDist;
	float minPointDist;
	boolean part = false;
	boolean destroy = false;
	boolean canExplode = true;
	boolean disabled = false;
	ArrayList<GfxPoint> gfxPoints = new ArrayList<GfxPoint>();
	Color c;
	
	Entity()
	{

	}
	
	void draw(Graphics g)
	{
		// their position, adjusted for the screen, moved relative to player, centered
		int gfxpsize = gfxPoints.size();
		GfxPoint p1;
		GfxPoint p2;
		int xdisplacement = (int) (x+(Main.windowWidth/2)-(Player.player.x));
		int ydisplacement = (int) (y+(Main.windowHeight/2)-(Player.player.y));
		for(int i = 0; i < gfxpsize; i+=2)
		{
			p1 = gfxPoints.get(i);
			p2 = gfxPoints.get(i+1);
			g.setColor(p1.c);
			g.drawLine((int)p1.x + xdisplacement, (int)p1.y + ydisplacement, (int)p2.x + xdisplacement, (int)p2.y + ydisplacement);
		}
		
	//	g.drawRect((int)(x+(Main.windowWidth/2)-(Player.player.x)-(width/2)),
		//		(int)(y+(Main.windowHeight/2)-(Player.player.y)-(height/2)), width, height);
	}
	
	static void moveEntities()
	{
		for(int i = 0; i < entities.size(); ++i)
		{
			entities.get(i).move();
		}
	}
	
	void move()
	{
		if(!disabled)
			accelerate();
		x += xVel;
		y += yVel;
	}
	
	void accelerate()
	{
		
	}
	
	void moveDist(float xd, float yd)
	{
		x += xd;
		y += yd;
	}
	
	void flagDestroy()
	{
		destroy = true;
	}

	void death()
	{
		flagDestroy();
	}
	
	void takeDamage(int d)
	{
		health -= d;
		if(health <= 0)
			death();
	}
	
	void collide(Entity e)
	{
		
	}
	
	static boolean lineLine(float p, float e, float dir)
	{
		float t = (p - e)/-dir;
		if(t >= 0 && t <= 1)
			return true;
		return false;
	}
	
	static void bounce(Entity w, Entity e)
	{
		float oldX = e.xVel * -3;
		float oldY = e.yVel * -3;
		if(oldX > 0 && oldX < 1)
			oldX = 1;
		if(oldX < 0 && oldX > -1)
			oldX = -1;
		if(oldY > 0 && oldY < 1)
			oldY = 1;
		if(oldY < 0 && oldY > -1)
			oldY = -1;
		
		if(e.xVel > 0)
		{
			if(lineLine(w.x - w.width/2, e.x + e.width/2, e.xVel))
			{
				e.xVel *= -1;
			}
		}
		else
		{
			if(lineLine(w.x+w.width/2, e.x-e.width/2, e.xVel))
			{
				e.xVel = e.xVel * -1;
			}
		}
		if(e.yVel > 0)
		{
			if(lineLine(w.y-w.height/2, e.y+e.height/2, e.yVel))
			{
				e.yVel = e.yVel * -1;
			}
		}
		else
		{
			if(lineLine(w.y+w.height/2, e.y-e.height/2, e.yVel))
			{
				e.yVel = e.yVel * -1;
			}
		}
		e.moveDist(oldX, oldY);
	}
	
	static float cross(float a, float b, float c, float d)
	{
		return a * d - b * c;
	}
	
	static boolean polygonCollision(Entity w, Entity e)
	{
		int wsize = w.gfxPoints.size();
		int esize = e.gfxPoints.size();
		GfxPoint a, b, c;
		float f1, f2, f3, f4, f5, f6, f7, f8;
		for(int i = 0; i < wsize; ++i)
		{
			for(int j = 0; j < esize; j+=2)
			{
				a = w.gfxPoints.get(i);
				b = e.gfxPoints.get(j);
				c = e.gfxPoints.get(j+1);
				f1 = w.x + a.x - w.xVel;
				f2 = w.y + a.y - w.yVel;
				f3 = w.x + a.x;
				f4 = w.y + a.y;
				f5 = e.x + b.x;
				f6 = e.y + b.y;
				f7 = e.x + c.x;
				f8 = e.y + c.y;
				if(lineCollision(f1, f2, f3, f4, f5, f6, f7, f8))
				{
					return true;
				}
			}
		}
		for(int i = 0; i < wsize; i+=2)
		{
			for(int j = 0; j < esize; ++j)
			{
				a = w.gfxPoints.get(i);
				b = w.gfxPoints.get(i+1);
				c = e.gfxPoints.get(j);
				f1 = w.x + a.x;
				f2 = w.y + a.y;
				f3 = w.x + b.x;
				f4 = w.y + b.y;
				f5 = e.x + c.x - e.xVel;
				f6 = e.y + c.y - e.yVel;
				f7 = e.x + c.x;
				f8 = e.y + c.y;
				if(lineCollision(f1, f2, f3, f4, f5, f6, f7, f8))
				{
					return true;
				}			
			}
		}
		return false;
	}
	
	static boolean lineCollision(float ax, float ay, float bx, float by, float cx, float cy, float dx, float dy)
	{
		float px = ax;
		float py = ay;
		float rx = bx - ax;
		float ry = by - ay;
		float qx = cx;
		float qy = cy;
		float sx = dx - cx;
		float sy = dy - cy;
		float den = cross(rx, ry, sx, sy);
		
		if(den == 0)
			return false;
		float t = cross(qx-px, qy-py, sx, sy) / den;
		float u = cross(qx-px, qy-py, rx, ry) / den;
		
		if(t >= 0 && t <= 1 && u >= 0 && u <= 1)
		{
			return true;
		}
		return false;
	}
	
	void calcDists()
	{
		calcDistToFarthestPoint();
		calcDistToClosestPoint();
	}
	
	void calcDistToFarthestPoint()
	{
		float f;
		float m = 0;
		GfxPoint p;
		for(int i = 0; i < gfxPoints.size(); ++i)
		{
			p = gfxPoints.get(i);
			f = Global.distance(0, 0, p.x, p.y);
			if(f > m)
				m = f;
		}
		maxPointDist = m;
	}

	void calcDistToClosestPoint()
	{
		float f;
		float m = 1000;
		GfxPoint p;
		for(int i = 0; i < gfxPoints.size(); ++i)
		{
			p = gfxPoints.get(i);
			f = Global.distance(0, 0, p.x, p.y);
			if(f < m)
				m = f;		
		}
		minPointDist = m;
	}
	
	void recolor(Color cl)
	{
		for(int i = 0; i < gfxPoints.size(); ++i)
		{
			gfxPoints.get(i).c = cl;
		}
	}
	
	void darken()
	{
		for(int i = 0; i < gfxPoints.size(); ++i)
		{
			gfxPoints.get(i).c = gfxPoints.get(i).c.darker();
		}
	}
	
}

