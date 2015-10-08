import java.awt.Color;


public class Sapper extends Enemy
{
	int flashCounter;
	int accelTick = 0;
	boolean flash;
	
	Sapper(float px, float py)
	{
		x = px;
		y = py;
		health = Global.sapperHealth;
		flash = true;
		flashCounter = 0;
		c = Color.red;
		width = 40;
		height = 40;
		gfxPoints = GfxPoint.loadSquare(20, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	void death()
	{
		Explosion.explosions.add(new Explosion(x+(Game.gen.nextInt(100)-50), y+(Game.gen.nextInt(100)-50), Game.gen.nextInt(Global.sapperExplosionRadius)+100, 1));
		Explosion.explosions.add(new Explosion(x+(Game.gen.nextInt(100)-50), y+(Game.gen.nextInt(100)-50), Game.gen.nextInt(Global.sapperExplosionRadius)+100, 1));
		Explosion.explosions.add(new Explosion(x+(Game.gen.nextInt(100)-50), y+(Game.gen.nextInt(100)-50), Game.gen.nextInt(Global.sapperExplosionRadius)+100, 1));
		super.death();
	}
	
	void move()
	{
		flashCounter++;
		if(flashCounter >= 50)
		{
			if(flash)
			{
				flash = false;
				c = Color.red;
			}
			else
			{
				c = Color.white;
				flash = true;
			}
			flashCounter = 0;
		}
		super.move();
	}
	
	void accelerate()
	{
		accelTick++;
		if(accelTick>360)
			accelTick = 0;
		
		float vdist = Player.player.y - y;
		float hdist = Player.player.x - x;
		float tdist = (float) Math.sqrt(vdist * vdist + hdist * hdist);
		xVel += (hdist / tdist * Math.cos(accelTick)*Global.sapperAccel);
		yVel += (vdist / tdist * Math.cos(accelTick)*Global.sapperAccel);
		if(xVel > Global.sapperTopSpeed)
			xVel = Global.sapperTopSpeed;
		if(xVel < -Global.sapperTopSpeed)
			xVel = -Global.sapperTopSpeed;
		if(yVel > Global.sapperTopSpeed)
			yVel = Global.sapperTopSpeed;
		if(yVel < -Global.sapperTopSpeed)
			yVel = -Global.sapperTopSpeed;
	}
}
