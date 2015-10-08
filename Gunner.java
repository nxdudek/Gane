
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


public class Gunner extends Enemy
{
	Gunner(float px, float py)
	{
		x = px;
		y = py;
		width = 30;
		height = 30;
		c = Color.magenta;
		health = Global.gunnerHealth;
		gfxPoints = GfxPoint.loadSquare(15, c);
		calcDists();
		
		Brain b = new Brain(this, 0, 0, false);
		b.add(new Turret(0, 0, b));
		brains.add(b);
		
		Entity.entities.add(this);
	}
	
	void move()
	{
		float d = Global.distance(x, y, Player.player.x, Player.player.y);
		if(d > 500)
			accelerate(1);
		else if(d < 300)
			accelerate(-1);
		super.move();
	}
	
	/*
	Gunner(float px, float py)
	{
		x = px;
		y = py;
		width = 30;
		height = 30;
		c = Color.magenta;
		firingCounter = 400;
		health = Global.gunnerHealth;
		gfxPoints = GfxPoint.loadSquare(15, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	void move()
	{
		float d = Global.distance(x, y, Player.player.x, Player.player.y);
		if(d > 500)
			accelerate(1);
		else if(d < 300)
			accelerate(-1);
		super.move();
		firingCounter--;
		if(firingCounter <= 0)
		{
			new EnemyBullet(x, y);
			firingCounter = new Random().nextInt(Global.gunnerFiringCooldown);
		}
	}
	*/
	void accelerate(float d)
	{
		float vdist = Player.player.y - y;
		float hdist = Player.player.x - x;
		float tdist = (float) Global.distance(x, y, Player.player.x, Player.player.y);
		xVel += ((hdist / tdist) * Global.gunnerAccel) * d;
		yVel += ((vdist / tdist) * Global.gunnerAccel) * d;
		if(xVel > Global.gunnerTopSpeed)
			xVel = Global.gunnerTopSpeed;
		if(xVel < -Global.gunnerTopSpeed)
			xVel = -Global.gunnerTopSpeed;
		if(yVel > Global.gunnerTopSpeed)
			yVel = Global.gunnerTopSpeed;
		if(yVel < -Global.gunnerTopSpeed)
			yVel = -Global.gunnerTopSpeed;
	}
}	
