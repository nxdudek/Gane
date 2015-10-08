import java.awt.Color;


public class Brightburst extends Enemy
{
	int burstTimer;
	int burstIncrement;
	Color darkc;
	Brightcore nw, sw, ne, se;
	
	public Brightburst(int px, int py)
	{
		burstTimer = 0;
		burstIncrement = 5;
		
		x = px;
		y = py;
		xVel = 0;
		yVel = 0;
		width = 60;
		height = width;
		health = Global.brightburstHealth;
		c = Color.orange;
		gfxPoints = GfxPoint.loadSquare(width/2, c);
		gfxPoints.add(new GfxPoint(0, -5, c));
		gfxPoints.add(new GfxPoint(-5, 0, c));

		gfxPoints.add(new GfxPoint(-5, 0, c));
		gfxPoints.add(new GfxPoint(5, 0, c));
		
		gfxPoints.add(new GfxPoint(5, 0, c));
		gfxPoints.add(new GfxPoint(0, -5, c));

		calcDists();
		darkc = c.darker().darker().darker().darker().darker();
		c = darkc;
		
		nw = new Brightcore(-width/2, -width/2, this);
		sw = new Brightcore(-width/2, width/2, this);
		ne = new Brightcore(width/2, -width/2, this);
		se = new Brightcore(width/2, width/2, this);
		
		Entity.entities.add(this);
	}
	
	void death()
	{
		if(nw != null)
			nw.death();
		if(sw != null)
			sw.death();
		if(ne != null)
			ne.death();
		if(se != null)
			se.death();
		super.death();
	}

	void move()
	{
		//if(!(nw == null && sw == null && ne == null && se == null))
		//{
		
		burstTimer += burstIncrement;
		if(burstTimer % (Global.brightburstBurstFrequency / 10) == 0)
		{
			c = c.brighter();
		}
		if(burstTimer > Global.brightburstBurstFrequency)
		{
			fireBurst();
			c = darkc;
			burstTimer = 0;
		}
		super.move();
	}
	
	void fireBurst()
	{
		new EnemyBullet(x, y, 0, -1);
		new EnemyBullet(x, y, (float)(0.71), (float)(-0.71));
		new EnemyBullet(x, y, 1, 0);
		new EnemyBullet(x, y, (float)(0.71), (float)(0.71));
		new EnemyBullet(x, y, 0, 1);
		new EnemyBullet(x, y, (float)(-0.71), (float)(0.71));
		new EnemyBullet(x, y, -1, 0);
		new EnemyBullet(x, y, (float)(-0.71), (float)(-0.71));
	}
	
	void accelerate()
	{
		xVel += Game.gen.nextFloat()/20 - 0.025;
		yVel += Game.gen.nextFloat()/20 - 0.025;
		if(xVel > Global.brightburstTopSpeed)
			xVel = Global.brightburstTopSpeed;
		if(xVel < -Global.brightburstTopSpeed)
			xVel = -Global.brightburstTopSpeed;
		if(yVel > Global.brightburstTopSpeed)
			yVel = Global.brightburstTopSpeed;
		if(yVel < -Global.brightburstTopSpeed)
			yVel = Global.brightburstTopSpeed;
	}

}

class Brightcore extends Enemy
{
	Brightburst parent;
	float xo;
	float yo;
	
	Brightcore(float px, float py, Brightburst p)
	{
		parent = p;
		xo = px;
		yo = py;
		x = xo + p.x;
		y = yo + p.y;
		xVel = 0;
		yVel = 0;
		width = 40;
		height = width;
		health = Global.brightcoreHealth;
		c = Color.cyan;
		gfxPoints = GfxPoint.loadSquare(width/2, c);
		gfxPoints.addAll(GfxPoint.loadSquare(width/2-5, Color.red));
		calcDists();
		Entity.entities.add(this);
	}
	
	void move()
	{
		x = parent.x + xo;
		y = parent.y + yo;
	}
	
	void death()
	{
		new EnemyBullet(x, y, 0, -1);
		new EnemyBullet(x, y, (float)(0.71), (float)(-0.71));
		new EnemyBullet(x, y, 1, 0);
		new EnemyBullet(x, y, (float)(0.71), (float)(0.71));
		new EnemyBullet(x, y, 0, 1);
		new EnemyBullet(x, y, (float)(-0.71), (float)(0.71));
		new EnemyBullet(x, y, -1, 0);
		new EnemyBullet(x, y, (float)(-0.71), (float)(-0.71));
		parent.burstIncrement--;
		if(parent.nw != null)
		{
			if(parent.nw.equals(this))
				parent.nw = null;
		}
		if(parent.sw != null)
		{
			if(parent.sw.equals(this))
				parent.sw = null;
		}
		if(parent.ne != null)
		{
			if(parent.ne.equals(this))
				parent.ne = null;
		}
		if(parent.se != null)
		{
			if(parent.se.equals(this))
				parent.se = null;
		}
		flagDestroy();
	}
}





