import java.awt.Color;


public class Octopus extends Enemy
{
	OctopusTentacle n, s, e, w;
	int growCount;
	
	public Octopus(int px, int py)
	{
		growCount = 0;
		x = px;
		y = py;
		xVel = 0;
		yVel = 0;
		width = Global.octopusSize;
		height = width;
		health = Global.octopusHeadHealth;
		c = Color.gray;
		gfxPoints = GfxPoint.loadSquare(width/2, c);
		calcDists();
		
		n = new OctopusTentacle(-width/2, -width/2, width/2, this);
		s = new OctopusTentacle(-width/2, width/2, width/2, this);
		w = new OctopusTentacle(width/2, -width/2, width/2, this);
		e = new OctopusTentacle(width/2, width/2, width/2, this);
		
		Entity.entities.add(this);
	}
	
	void death()
	{
		if(n != null)
			n.death();
		if(s != null)
			s.death();
		if(w != null)
			w.death();
		if(e != null)
			e.death();
		super.death();
	}
	
	void move()
	{
		super.move();
		if(n != null)
			n.move();
		else
		{
			growCount++;
			if(growCount > Global.octopusGrowTime)
			{
				n = new OctopusTentacle(-width/2, -width/2, width/2, this);
				growCount = 0;
			}
		}
		if(s != null)
			s.move();
		else
		{
			growCount++;
			if(growCount > Global.octopusGrowTime)
			{
				s = new OctopusTentacle(-width/2, width/2, width/2, this);
				growCount = 0;
			}
		}
		if(w != null)
			w.move();
		else
		{
			growCount++;
			if(growCount > Global.octopusGrowTime)
			{
				w = new OctopusTentacle(width/2, -width/2, width/2, this);
				growCount = 0;
			}
		}
		if(e != null)
			e.move();
		else
		{
			growCount++;
			if(growCount > Global.octopusGrowTime)
			{
				e = new OctopusTentacle(width/2, width/2, width/2, this);
				growCount = 0;
			}
		}		
	}
	
	void accelerate()
	{
		float vdist = Player.player.y - y;
		float hdist = Player.player.x - x;
		float tdist = (float) Math.sqrt(vdist * vdist + hdist * hdist);
		xVel += (hdist / tdist * Global.octopusAccel);
		yVel += (vdist / tdist * Global.octopusAccel);
		if(xVel > Global.octopusTopSpeed)
			xVel = Global.octopusTopSpeed;
		if(xVel < -Global.octopusTopSpeed)
			xVel = -Global.octopusTopSpeed;
		if(yVel > Global.octopusTopSpeed)
			yVel = Global.octopusTopSpeed;
		if(yVel < -Global.octopusTopSpeed)
			yVel = -Global.octopusTopSpeed;
	}
}

class OctopusTentacle extends Enemy
{
	Enemy parent;
	OctopusTentacle child;
	float xo;
	float yo;
	float lastx;
	float lasty;
	int growTimer;
	int growCap;
	int mt;
	
	OctopusTentacle(float px, float py, int n, Enemy p)
	{
		mt = 0;
		growTimer = 0;
		growCap = Game.gen.nextInt(500) + 500;
		parent = p;
		xo = (float) (px * 0.8);
		yo = (float) (py * 0.8);
		x = parent.x + xo;
		y = parent.y + yo;
		xVel = 0;
		yVel = 0;
		width = n;
		height = width;
		health = Global.octopusTentacleHealth;
		c = Color.gray;
		gfxPoints = GfxPoint.loadSquare(width/2, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	void death()
	{
		if(child != null)
			child.death();
		if(parent instanceof OctopusTentacle)
			((OctopusTentacle) parent).child = null;
		if(parent instanceof Octopus)
		{
			if(((Octopus)parent).n != null)
			{
				if((((Octopus)parent).n).equals(this))
					((Octopus)parent).n = null;
			}
			if(((Octopus)parent).s != null)
			{
				if((((Octopus)parent).s).equals(this))
					((Octopus)parent).s = null;
			}
			if(((Octopus)parent).w != null)
			{
				if((((Octopus)parent).w).equals(this))
					((Octopus)parent).w = null;
			}
			if(((Octopus)parent).e != null)
			{
				if((((Octopus)parent).e).equals(this))
					((Octopus)parent).e = null;
			}
		}
		flagDestroy();
		ParticleGenerator.gen(x, y, 0, 0, 5, c, c);
	}
	
	void move()
	{
		if(parent == null || parent.health <= 0)
			death();
		if(growTimer < growCap)
			growTimer++;
		else if(child == null)
		{
			if(width >= 20)
				child = new OctopusTentacle(xo, yo, width-10, this);
		}
		
		float dx = xo + parent.x - x;
		float dy = yo + parent.y - y;
		xVel = dx * 10 * Global.octopusAccel;
		yVel = dy * 10 * Global.octopusAccel;
		x += xVel;
		y += yVel;
		
		if(child != null)
			child.move();
	}
}
