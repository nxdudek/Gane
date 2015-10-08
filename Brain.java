import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


public class Brain extends Enemy
{
	Enemy parent;
	ArrayList<Block> body = new ArrayList<Block>();
	float xo;
	float yo;
	
	Brain(Enemy p, float dx, float dy, boolean b)
	{
		parent = p;
		xo = dx;
		yo = dy;
		x = parent.x + xo;
		y = parent.y + yo;
		xVel = 0;
		yVel = 0;
		health = 1;
		c = Color.cyan;		
		width = 20;
		height = width;
		if(b)
		{
			gfxPoints = GfxPoint.loadSquare(width/2, c);
			calcDists();
		}
		else
		{
			minPointDist = 0;
			maxPointDist = 0;
		}
		Entity.entities.add(this);
	}
	
	void death()
	{
		Entity e;
		for(int i = 0; i < body.size(); ++i)
		{
			e = body.get(i);
			/*
			if(Game.gen.nextInt(10) < 7)
				new Debris(e.x, e.y, Math.signum(x - e.x), Math.signum(y - e.y), 20);
			*/
			e.death();
			--i;
		}
		parent.brains.remove(this);
		ParticleGenerator.gen(x, y, 0, 0, 10, c, c);
		flagDestroy();
	}
	
	void add(Block e)
	{
		e.x = e.x + x;
		e.y = e.y + y;
		body.add(e);
	}
	
	void move()
	{
		x = parent.x + xo;
		y = parent.y + yo;
	}
}

class Block extends Enemy
{
	Brain parent;
	float xo;
	float yo;
	
	Block()
	{
		
	}
	
	Block(float px, float py, Brain p)
	{
		parent = p;
		xo = px;
		yo = py;
		x = px;
		y = py;
		xVel = parent.parent.xVel;
		yVel = parent.parent.yVel;
		c = Color.orange;
		width = 40;
		height = width;
		health = Global.blockHealth;
		gfxPoints = GfxPoint.loadSquare(width/2, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	Block(float px, float py, Brain p, Color cl)
	{
		parent = p;
		xo = px;
		yo = py;
		x = px;
		y = py;
		xVel = parent.parent.xVel;
		yVel = parent.parent.yVel;
		c = cl;
		width = 40;
		height = width;
		health = Global.blockHealth;
		gfxPoints = GfxPoint.loadSquare(width/2, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	void resize(int w, int h)
	{
		width = w;
		height = h;
		gfxPoints = GfxPoint.loadRectangle(w/2, h/2, c);
		calcDists();
	}
	
	void death()
	{
		ParticleGenerator.gen(x, y, 0, 0, 10, c, c);
		if(parent != null)
		{
			parent.body.remove(this);
			parent = null;
		}
		flagDestroy();
	}
	
	void move()
	{
		if(parent != null)
		{
			accelerate();
			x = parent.x + xo;
			y = parent.y + yo;
		}
	}
	
	void accelerate()
	{
		xVel = parent.parent.xVel;
		yVel = parent.parent.yVel;
	}
	
	void collide(Entity e)
	{
		if(e instanceof Player)
		{
			takeDamage(10);
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, 15, Color.cyan, c.brighter()));
			Player.player.takeDamage(1);
		}
		else if(e instanceof Enemy)
		{

		}
		else if(e instanceof EnemyBullet)
		{
			
		}
		else if(e instanceof Bullet && !(e instanceof EnemyBullet))
		{
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, 15, c.brighter(), e.c.brighter()));
			e.flagDestroy();
			this.takeDamage( ( (Bullet)e).damage );
		}
		else if(e instanceof Wall)
		{
			float oldX = e.xVel;
			float oldY = e.yVel;
			bounce(e, this);
			parent.parent.xVel = e.xVel;
			parent.parent.yVel = e.yVel;
			parent.parent.moveDist(oldX, oldY);
			parent.moveDist(oldX, oldY);
			moveDist(oldX, oldY);
		}
	}
}

class HorizontalBlock extends Block
{
	HorizontalBlock(float px, float py, Brain p)
	{
		parent = p;
		xo = px;
		yo = py;
		x = px;
		y = py;
		xVel = parent.parent.xVel;
		yVel = parent.parent.yVel;
		c = Color.orange;
		width = 60;
		height = 20;
		health = Global.blockHealth;
		gfxPoints = GfxPoint.loadRectangle(width/2, height/2, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	HorizontalBlock(float px, float py, Brain p, Color cl)
	{
		parent = p;
		xo = px;
		yo = py;
		x = px;
		y = py;
		xVel = parent.parent.xVel;
		yVel = parent.parent.yVel;
		c = cl;
		width = 60;
		height = 20;
		health = Global.blockHealth;
		gfxPoints = GfxPoint.loadRectangle(width/2, height/2, c);
		calcDists();
		Entity.entities.add(this);
	}
}

class VerticalBlock extends Block
{
	VerticalBlock(float px, float py, Brain p)
	{
		parent = p;
		xo = px;
		yo = py;
		x = px;
		y = py;
		xVel = parent.parent.xVel;
		yVel = parent.parent.yVel;
		c = Color.orange;
		width = 20;
		height = 60;
		health = Global.blockHealth;
		gfxPoints = GfxPoint.loadRectangle(width/2, height/2, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	VerticalBlock(float px, float py, Brain p, Color cl)
	{
		parent = p;
		xo = px;
		yo = py;
		x = px;
		y = py;
		xVel = parent.parent.xVel;
		yVel = parent.parent.yVel;
		c = cl;
		width = 20;
		height = 60;
		health = Global.blockHealth;
		gfxPoints = GfxPoint.loadRectangle(width/2, height/2, c);
		calcDists();
		Entity.entities.add(this);
	}
}

class Turret extends Block
{
	int firingCounter;
	
	Turret(float px, float py, Brain p)
	{
		firingCounter = Game.gen.nextInt(Global.turretFiringCooldown) + Global.turretFiringBase;
		parent = p;
		xo = px;
		yo = py;
		x = px;
		y = py;
		xVel = parent.parent.xVel;
		yVel = parent.parent.yVel;
		c = Color.yellow;
		width = 20;
		height = width;
		health = Global.turretHealth;
		gfxPoints = GfxPoint.loadSquare(width/2, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	Turret(float px, float py, Brain p, Color cl)
	{
		firingCounter = Game.gen.nextInt(Global.turretFiringCooldown) + Global.turretFiringBase;
		parent = p;
		xo = px;
		yo = py;
		x = px;
		y = py;
		xVel = parent.parent.xVel;
		yVel = parent.parent.yVel;
		c = cl;
		width = 20;
		height = width;
		health = Global.turretHealth;
		gfxPoints = GfxPoint.loadSquare(width/2, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	void move()
	{
		super.move();
		firingCounter--;
		if(firingCounter <= 0)
		{
			new EnemyBullet(x, y);
			firingCounter = Game.gen.nextInt(Global.turretFiringCooldown) + Global.turretFiringBase;
		}
	}
}