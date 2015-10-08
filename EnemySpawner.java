import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class EnemySpawner extends Enemy
{
	int spawnCounter = 500;
	Color c2 = Color.green;
	Floater spawning;
	int spawnInterval = 50;
	
	// 0 = floater, 1 = gunner, 2 = flower
	int spawnType;
	
	EnemySpawner(float px, float py)
	{
		x = px;
		y = py;
		height = 100;
		width = 100;
		c = Color.pink;
		health = 1;
		spawnType = 0;
		gfxPoints = GfxPoint.loadSquare(50, c);
		calcDists();
		brains.add(new Brain(this, 0, 0, false));
		brains.get(0).add(new HorizontalBlock(0, 50, brains.get(0)));
		brains.get(0).add(new HorizontalBlock(0, -50, brains.get(0)));
		brains.get(0).add(new VerticalBlock(50, 0, brains.get(0)));
		brains.get(0).add(new VerticalBlock(-50, 0, brains.get(0)));
		brains.get(0).body.get(0).resize(120, 20);
		brains.get(0).body.get(1).resize(120, 20);
		brains.get(0).body.get(2).resize(20, 120);
		brains.get(0).body.get(3).resize(20, 120);
		Entity.entities.add(this);
	}
	
	EnemySpawner(float px, float py, int t)
	{
		x = px;
		y = py;
		height = 100;
		width = 100;
		c = Color.ORANGE;
		health = 5;
		spawnType = t;
		Entity.entities.add(this);
	}
	
	void death()
	{
		super.death();
		if(spawning != null)
			spawning.death();
	}
	
	void spawn(int n)
	{
		if(spawning == null)
		{
			spawning = new Floater(x, y, new ArrayList<GfxPoint>());
			spawning.disabled = true;
			spawning.canExplode = false;
		}
		if(spawning.gfxPoints.size() == 0)
		{
			spawning.gfxPoints.add(new GfxPoint(-n/2, -n/2, c2));
			spawning.gfxPoints.add(new GfxPoint(-n/2, -n/2, c2));
		}
		else if(spawning.gfxPoints.size() == 2)
		{
			if(Global.distance(spawning.gfxPoints.get(0), spawning.gfxPoints.get(1)) < n)
			{
				if(spawnCounter > spawnInterval)
				{
					ParticleGenerator.gen(spawning.gfxPoints.get(1).x+x, spawning.gfxPoints.get(1).y+y, 0, 0, 2, c2, c2);
					spawning.gfxPoints.get(1).x++;
					spawning.calcDists();
					spawnCounter = 0;
				}
				else
					return;
			}
			else
			{
				spawning.gfxPoints.add(new GfxPoint(n/2, -n/2, c2));
				spawning.gfxPoints.add(new GfxPoint(n/2, -n/2, c2));
			}
		}
		else if(spawning.gfxPoints.size() == 4)
		{
			if(Global.distance(spawning.gfxPoints.get(2), spawning.gfxPoints.get(3)) < n)
			{
				if(spawnCounter > spawnInterval)
				{
					ParticleGenerator.gen(spawning.gfxPoints.get(3).x+x, spawning.gfxPoints.get(3).y+y, 0, 0, 2, c2, c2);
					spawning.gfxPoints.get(3).y++;
					spawning.calcDists();
					spawnCounter = 0;
				}
				else
					return;			}
			else
			{
				spawning.gfxPoints.add(new GfxPoint(n/2, n/2, c2));
				spawning.gfxPoints.add(new GfxPoint(n/2, n/2, c2));
			}
		}
		else if(spawning.gfxPoints.size() == 6)
		{
			if(Global.distance(spawning.gfxPoints.get(4), spawning.gfxPoints.get(5)) < n)
			{
				if(spawnCounter > spawnInterval)
				{
					ParticleGenerator.gen(spawning.gfxPoints.get(5).x+x, spawning.gfxPoints.get(5).y+y, 0, 0, 5, c2, c2);
					spawning.gfxPoints.get(5).x--;
					spawning.calcDists();
					spawnCounter = 0;
				}
				else
					return;			}
			else
			{
				spawning.gfxPoints.add(new GfxPoint(-n/2, n/2, c2));
				spawning.gfxPoints.add(new GfxPoint(-n/2, n/2, c2));
			}
		}
		else if(spawning.gfxPoints.size() == 8)
		{
			if(Global.distance(spawning.gfxPoints.get(6), spawning.gfxPoints.get(7)) < n)
			{
				if(spawnCounter > spawnInterval)
				{
					ParticleGenerator.gen(spawning.gfxPoints.get(7).x+x, spawning.gfxPoints.get(7).y+y, 0, 0, 5, c2, c2);
					spawning.gfxPoints.get(7).y--;
					spawning.calcDists();
					spawnCounter = 0;
				}
				else
					return;			}
			else
			{
				ParticleGenerator.gen(x, y, 0, 0, 2, c2, c2);
				Level.enemiesLeft++;
				spawning.disabled = false;
				spawning.canExplode = true;
				spawning.calcDists();
				spawning = null;
				spawnCounter = 0;
			}
		}
	}
	
	void move()
	{
		super.move();
		spawnCounter += health * 10;
		spawn(50);
	}
}
