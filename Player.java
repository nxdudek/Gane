import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Player extends Entity
{
	static Player player;
	static String healthString;
	static int health = 5;
	boolean up, down, left, right, modifierOne, modifierTwo;
	static Mine eb;
	
	static float energy;
	
	Player(int xp, int yp)
	{
		x = xp;
		y = yp;
		height = 16;
		width = 16;
		c = Global.playerColor;
		health = 5;
		energy = Global.energyCap;
		calcHealth();
		gfxPoints = GfxPoint.loadSquare(8, c);
		gfxPoints.add(new GfxPoint(-4, -4, c));
		gfxPoints.add(new GfxPoint(4, 4, c));
		gfxPoints.add(new GfxPoint(4, -4, c));
		gfxPoints.add(new GfxPoint(-4, 4, c));
		calcDists();
		Entity.entities.add(this);
	}
	
	static void calcHealth()
	{
		healthString = "";
		for(int i = 0; i < health; ++i)
		{
			healthString = healthString + "o";
		}
	}
	
	void draw(Graphics g)
	{	
		g.setColor(c);
		int gfxpsize = gfxPoints.size();
		GfxPoint p1;
		GfxPoint p2;
		int xdisplacement = (int) ((Main.windowWidth/2));
		int ydisplacement = (int) ((Main.windowHeight/2));
		for(int i = 0; i < gfxpsize; i+=2)
		{
			p1 = gfxPoints.get(i);
			p2 = gfxPoints.get(i+1);
			g.drawLine((int)p1.x + xdisplacement, (int)p1.y + ydisplacement, (int)p2.x + xdisplacement, (int)p2.y + ydisplacement);
		}
	}
	
	void move()
	{
		accelerate();
		super.move();
		if(energy < Global.energyCap)
			energy += Global.energyRegenRate;
		c = ColorList.cyan[(int) (energy/10)];
	}
	
	void accelerate()
	{
		if(up && yVel > -Global.playerTopSpeed && energy >= Global.disableThreshold)
		{
			yVel -= Global.playerAcceleration;
			energy -= Global.movementCost;
		}
		if(down && yVel < Global.playerTopSpeed && energy >= Global.disableThreshold)
		{
			yVel += Global.playerAcceleration;
			energy -= Global.movementCost;
		}
		if(left && xVel > -Global.playerTopSpeed && energy >= Global.disableThreshold)
		{
			xVel -= Global.playerAcceleration;
			energy -= Global.movementCost;
		}
		if(right && xVel < Global.playerTopSpeed && energy >= Global.disableThreshold)
		{
			xVel += Global.playerAcceleration;
			energy -= Global.movementCost;
		}
	}
	
	void takeDamage(int d)
	{
		health -= d;
		calcHealth();
		if(health <= 0)
			Game.lose();
	}
	
	void specialAttackOne()
	{
		if(!Main.pause && Main.delay <= 0 && energy >= Global.ringShotCost)
		{
			energy -= Global.ringShotCost;
			new Bullet(0, -1);
			new Bullet((float)(0.71), (float)(-0.71));
			new Bullet(1, 0);
			new Bullet((float)(0.71), (float)(0.71));
			new Bullet(0, 1);
			new Bullet((float)(-0.71), (float)(0.71));
			new Bullet(-1, 0);
			new Bullet((float)(-0.71), (float)(-0.71));
		}
	}
	
	void specialAttackTwo()
	{

		if(!Main.pause && Main.delay <= 0)
		{
			if(energy >= Global.gravityBombCost)
			{
				energy -= Global.gravityBombCost;
				new GravityBomb(x, y);
			}
		}
	}
	
	static void specialAttackThree()
	{
		if((eb == null) && energy >= Global.explodingCost)
		{
			energy -= Global.explodingCost;
			eb = new Mine();
		}
		else if(eb != null)
		{
			eb.death();
			eb = null;
		}
	}
	
	synchronized void shoot(int dx, int dy)
	{
		if(!Main.pause && Main.delay <= 0)
		{
			if(modifierOne && energy >= Global.disableThreshold)
			{
				new PiercingBullet(dx, dy);
				energy -= Global.piercingCost;
			}
			else if(energy >= Global.disableThreshold)
			{
				new Bullet(dx, dy);
				energy -= Global.bulletCost;
			}
		}
	}
	
	void collide(Entity e)
	{
		if(e instanceof Wall)
		{
			bounce(e, this);
		}
		else if(e instanceof Bullet)
		{
			e.flagDestroy();
			takeDamage(((Bullet)e).damage);
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, 15, e.c.brighter(), c.brighter()));
		}
		else if(e instanceof Enemy)
		{
			takeDamage(1);
			e.takeDamage(10);
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, 15, e.c.brighter(), c.brighter()));
		}
	}
	
	static void playerBounce(Entity w, Entity e)
	{
		float oldX = e.xVel * -1;
		float oldY = e.yVel * -1;
		if(e.xVel > 0)
		{
			if(lineLine(w.x-w.width/2, e.x+e.width/2, e.xVel))
			{
				if(Player.player.right)
					e.xVel = Math.signum(e.xVel) * -1;
				else
					e.xVel = e.xVel * -1;
			}
		}
		else
		{
			if(lineLine(w.x+w.width/2, e.x-e.width/2, e.xVel))
			{
				if(Player.player.left)
					e.xVel = Math.signum(e.xVel) * -1;
				else
					e.xVel = e.xVel * -1;
			}
		}
		if(e.yVel > 0)
		{
			if(lineLine(w.y-w.height/2, e.y+e.height/2, e.yVel))
			{
				if(Player.player.down)
					e.yVel = Math.signum(e.yVel) * -1;
				else
					e.yVel = e.yVel * -1;
			}
		}
		else
		{
			if(lineLine(w.y+w.height/2, e.y-e.height/2, e.yVel))
			{
				if(Player.player.up)
					e.yVel = Math.signum(e.yVel) * -1;
				else
					e.yVel = e.yVel * -1;
			}
		}
		e.moveDist(oldX, oldY);
		e.moveDist(oldX, oldY);
	}
}
