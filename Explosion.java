import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Explosion
{
	static ArrayList<Explosion> explosions = new ArrayList<Explosion>();
	float x;
	float y;
	int lifespan;
	int age;
	boolean destroy;
	int damage;
	Color c;
	ArrayList<Entity> affected = new ArrayList<Entity>();
	
	Explosion(float px, float py, int n, int d)
	{
		x = px;
		y = py;
		lifespan = n;
		age = 0;
		destroy = false;
		damage = d;
		c = Color.red;
		explosions.add(this);
	}
	
	void draw(Graphics g)
	{
		int i = Game.gen.nextInt(3);
		if(i == 0)
			c = Color.red;
		else if(i == 1)
			c = Color.yellow;
		else if(i == 2)
			c = Color.orange;
		g.setColor(c);
		g.drawOval((int)(x+(Main.windowWidth/2)-(Player.player.x)-(age/2)),
				(int)(y+(Main.windowHeight/2)-(Player.player.y)-(age/2)), age, age);
	}
	
	void flagDestroy()
	{
		destroy = true;
		affected.clear();
	}
	
	void move()
	{
		Entity e;
		for(int i = 0; i < Entity.entities.size(); ++i)
		{
			e = Entity.entities.get(i);
			if(!affected.contains(e) && e.canExplode)
			{
				float d = Global.distance(x, y, e.x, e.y);
				if(d - e.minPointDist < age/2 && ((e instanceof Enemy) || (e instanceof Player) || (e instanceof Debris)) )
				{
					ParticleGenerator.generators.add(new ParticleGenerator(e.x, e.y, e.xVel, e.yVel, 25, e.c, c));
					e.takeDamage(damage);
					affected.add(e);
				}
			}
		}
		age++;
		if(age == lifespan)
			flagDestroy();
	}
	
	static void explosionTick()
	{
		for(int i = 0; i < explosions.size(); ++i)
		{
			explosions.get(i).move();
		}
	}
	
	static void cleanExplosions()
	{
		Explosion e;
		for(int i = 0; i < explosions.size(); ++i)
		{
			e = explosions.get(i);
			if(e.destroy)
				explosions.remove(e);
		}
	}

}
