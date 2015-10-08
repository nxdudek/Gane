import java.awt.Color;


public class Aegis extends Enemy
{
	Sword warrior;
	
	Aegis(float px, float py)
	{
		x = px;
		y = py;
		xVel = 0;
		yVel = 0;
		width = 30;
		height = width;
		c = Color.gray;
		health = 1;
		gfxPoints = GfxPoint.loadSquare(width/2, c);
		gfxPoints.addAll(GfxPoint.loadSquare(width/2-1, Color.gray));
		gfxPoints.addAll(GfxPoint.loadSquare(width/2+1, Color.gray));
		gfxPoints.addAll(GfxPoint.loadSquare(width/2-2, Color.cyan));
		gfxPoints.addAll(GfxPoint.loadSquare(width/2-4, Color.red));
		gfxPoints.addAll(GfxPoint.loadSquare(width/2-6, Color.green));
		gfxPoints.addAll(GfxPoint.loadSquare(width/2-8, Color.yellow));
		calcDists();
		warrior = new Sword(x, y, this);
		Entity.entities.add(this);
	}
	
	void death()
	{
		warrior.c = Color.gray;
		warrior.defender = null;
		super.death();
	}
	
	void move()
	{
		super.move();
		float d = Global.distance(x, y, warrior.x, warrior.y);
		ParticleGenerator.genNarrow(x, y, (warrior.x - x)/d, (warrior.y - y)/d, 1, warrior.c, warrior.c);
	}
	
	void accelerate()
	{
		float vdist = Player.player.y - y;
		float hdist = Player.player.x - x;
		float tdist = (float) Math.sqrt(vdist * vdist + hdist * hdist);
		xVel -= (hdist / tdist * Global.floaterAccel);
		yVel -= (vdist / tdist * Global.floaterAccel);
		if(xVel > Global.floaterTopSpeed)
			xVel = Global.floaterTopSpeed;
		if(xVel < -Global.floaterTopSpeed)
			xVel = -Global.floaterTopSpeed;
		if(yVel > Global.floaterTopSpeed)
			yVel = Global.floaterTopSpeed;
		if(yVel < -Global.floaterTopSpeed)
			yVel = -Global.floaterTopSpeed;
	}
}

class Sword extends Enemy
{
	Aegis defender;
	boolean defended;
	int flashCount;
	
	Sword(float px, float py, Aegis p)
	{
		flashCount = 50;
		defender = p;
		x = px;
		y = py;
		xVel = 0;
		yVel = 0;
		width = 30;
		height = 60;
		c = Color.gray;
		health = 1;
		gfxPoints = GfxPoint.loadRectangle(width/2, height/2, c);
		calcDists();
		
		Brain b = new Brain(this, 0, 0, false);
		b.add(new Block(0, -15, b));
		b.add(new Block(0, 15, b));
		b.body.get(0).resize(10, 10);
		b.body.get(1).resize(10, 10);
		brains.add(b);
		
		Entity.entities.add(this);
	}
	
	void move()
	{
		if(defender != null)
		{
			flashCount--;
			if(flashCount <= 0)
			{
				flashCount = 5;
				c = Color.getHSBColor(Game.gen.nextFloat(), Game.gen.nextFloat(), Game.gen.nextFloat());
				recolor(Color.getHSBColor(Game.gen.nextFloat(), Game.gen.nextFloat(), Game.gen.nextFloat()));
			}
			float d = Global.distance(x, y, defender.x, defender.y);
			ParticleGenerator.genNarrow(x, y, (defender.x - x)/d, (defender.y - y)/d, 1, c, c);
		}

		accelerate();
		super.move();
	}
	
	void accelerate()
	{
		float vdist = Player.player.y - y;
		float hdist = Player.player.x - x;
		float tdist = (float) Math.sqrt(vdist * vdist + hdist * hdist);
		xVel += (hdist / tdist * Global.swordAccel);
		yVel += (vdist / tdist * Global.swordAccel);
		if(xVel > Global.swordMaxSpeed)
			xVel = Global.swordMaxSpeed;
		if(xVel < -Global.swordMaxSpeed)
			xVel = -Global.swordMaxSpeed;
		if(yVel > Global.swordMaxSpeed)
			yVel = Global.swordMaxSpeed;
		if(yVel < -Global.swordMaxSpeed)
			yVel = -Global.swordMaxSpeed;
	}
	
	void takeDamage(int d)
	{
		if(health > 0 && defender == null)
		{
			health -= d;
			if(health <= 0)
				death();
		}
	}
}
