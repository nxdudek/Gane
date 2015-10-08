import java.awt.Color;
import java.util.ArrayList;

public class Bullet extends Entity
{
	int damage;
	
	Bullet()
	{
		
	}
	
	Bullet(float dx, float dy)
	{
		xVel = dx * Global.bulletSpeed;
		yVel = dy * Global.bulletSpeed;
		x = Player.player.x + xVel;
		y = Player.player.y + yVel;
		height = 4;
		width = 4;
		damage = 1;
		c = Global.bulletColor;
		gfxPoints = GfxPoint.loadSquare(2, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	Bullet(float px, float py, float dx, float dy)
	{
		x = px;
		y = py;
		xVel = dx * Global.bulletSpeed;
		yVel = dy * Global.bulletSpeed;
		height = 4;
		width = 4;
		damage = 1;
		c = Global.bulletColor;
		gfxPoints = GfxPoint.loadSquare(2, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	void move()
	{
		super.move();
		if(Global.distance(x, y, Player.player.x, Player.player.y) > 3000)
			flagDestroy();
	}
	
	void collide(Entity e)
	{
		if(e instanceof Enemy)
		{
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, 15, c.brighter(), e.c.brighter()));
			this.flagDestroy();
			((Enemy)e).takeDamage(damage);
		}
		else if(e instanceof Bullet)
		{
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, 15, c.brighter(), c.brighter()));
			flagDestroy();
			e.flagDestroy();
		}
		else if(e instanceof Wall)
		{
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, 15, c.brighter(), c.brighter()));
			this.flagDestroy();
		}
		else if(e instanceof Debris)
		{
			e.takeDamage(1);
			flagDestroy();
		}
	}
}
