import java.awt.Color;


public class Debris extends Entity
{
	Debris(float px, float py, float dx, float dy, int w)
	{
		x = px;
		y = py;
		if(xVel == 0)
			xVel = (Game.gen.nextFloat() - 1);
		else
			xVel = dx * (Game.gen.nextFloat() - 1) * 3;
		
		if(yVel == 0)
			yVel = dy * (Game.gen.nextFloat() - 1);
		else
			yVel = dx * (Game.gen.nextFloat() - 1) * 3;
		width = w;
		height = w;
		c = Color.darkGray;
		health = 1;
		gfxPoints = GfxPoint.loadSquare(width/2, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	Debris(Entity e)
	{
		x = e.x;
		y = e.y;
		xVel = e.xVel + (Game.gen.nextFloat() - 1) * 3;
		yVel = e.yVel + (Game.gen.nextFloat() - 1) * 3;
		width = e.width;
		height = e.height;
		c = Color.darkGray;
		health = 1;
		gfxPoints = GfxPoint.loadSquare(width/2, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	void death()
	{
		ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, 15, c, c));
		flagDestroy();
	}
	
	void collide(Entity e)
	{
		if(e instanceof Player)
		{
			takeDamage(1);
			Player.player.takeDamage(1);
		}
		else if(e instanceof Enemy)
		{
			takeDamage(1);
			e.takeDamage(1);
		}
		else if(e instanceof Bullet)
		{
			takeDamage(1);
			e.flagDestroy();
		}
		else if(e instanceof Wall)
		{
			takeDamage(1);
		}
	}
}
