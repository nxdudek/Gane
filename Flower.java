import java.awt.Color;


public class Flower extends Enemy
{
	int healTick;
	
	Flower(float px, float py)
	{
		Level.flowersLeft++;
		healTick = 999;
		x = px;
		y = py;
		xVel = 0;
		yVel = 0;
		health = Global.flowerHealth;
		c = Color.LIGHT_GRAY;
		width = 40;
		height = 40;
		
		Brain b = new Brain(this, 0, 0, false);
		b.add(new FlowerHorizontalBlock(0, -40, b));
		b.add(new FlowerHorizontalBlock(0, 40, b));
		b.add(new FlowerVerticalBlock(40, 0, b));
		b.add(new FlowerVerticalBlock(-40, 0, b));
		brains.add(b);
		canExplode = false;
		
		gfxPoints = GfxPoint.loadSquare(20, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	void move()
	{
		if(healTick < 1000)
		{
			healTick++;
		}
		else
		{
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, 45, c.brighter()));
			healTick = 0;
			Entity e;
			for(int i = 0; i < Entity.entities.size(); ++i)
			{
				e = Entity.entities.get(i);
				if(e instanceof Floater)
				{
					if(((Floater)e).health < Level.flowersLeft + 2)
					{
						((Floater)e).health++;
						((Floater)e).c = ((Floater)e).c.brighter().brighter();
					}
				}
			}
		}
		accelerate();
		super.move();
	}
	
	void accelerate()
	{
		float vdist = Player.player.y - y;
		float hdist = Player.player.x - x;
		float tdist = (float) Math.sqrt(vdist * vdist + hdist * hdist);
		xVel += (hdist / tdist * Global.flowerAccel);
		yVel += (vdist / tdist * Global.flowerAccel);
		if(xVel > Global.flowerTopSpeed)
			xVel = Global.flowerTopSpeed;
		if(xVel < -Global.flowerTopSpeed)
			xVel = -Global.flowerTopSpeed;
		if(yVel > Global.flowerTopSpeed)
			yVel = Global.flowerTopSpeed;
		if(yVel < -Global.flowerTopSpeed)
			yVel = -Global.flowerTopSpeed;
	}
	
	void death()
	{
		Level.flowersLeft--;
		Entity e;
		for(int i = 0; i < Entity.entities.size(); ++i)
		{
			e = Entity.entities.get(i);
			if(e instanceof Floater)
			{
				((Floater)e).takeDamage(1);
			}
		}
		super.death();
	}
}
	
class FlowerHorizontalBlock extends Block
{
	FlowerHorizontalBlock(float px, float py, Brain p)
	{
		parent = p;
		xo = px;
		yo = py;
		x = px;
		y = py;
		xVel = parent.parent.xVel;
		yVel = parent.parent.yVel;
		c = Color.blue;
		width = 50;
		height = 20;
		health = 3;
		gfxPoints = GfxPoint.loadRectangle(width/2, height/2, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	void death()
	{
		parent.parent.canExplode = true;
		Block b;
		if(parent.body.size() > 0)
		{
			for(int i = 0; i < parent.parent.brains.get(0).body.size(); ++i)
			{
				b = parent.body.get(i);
				b.recolor(Color.cyan);
			}
		}
		super.death();
	}
}

class FlowerVerticalBlock extends Block
{
	FlowerVerticalBlock(float px, float py, Brain p)
	{
		parent = p;
		xo = px;
		yo = py;
		x = px;
		y = py;
		xVel = parent.parent.xVel;
		yVel = parent.parent.yVel;
		c = Color.blue;
		width = 20;
		height = 50;
		health = 3;
		gfxPoints = GfxPoint.loadRectangle(width/2, height/2, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	void death()
	{
		parent.parent.canExplode = true;
		Block b;
		if(parent.body.size() > 0)
		{
			for(int i = 0; i < parent.parent.brains.get(0).body.size(); ++i)
			{
				b = parent.body.get(i);
				b.recolor(Color.cyan);
			}
		}
		super.death();
	}
}