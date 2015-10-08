import java.awt.Color;


public class EnemyBullet extends Bullet
{
	EnemyBullet(float px, float py)
	{
		x = px;
		y = py;
		height = 4;
		width = 4;
		float dx = (Player.player.x - x)/Math.abs(Global.distance(x, y, Player.player.x, Player.player .y));
		float dy = (Player.player.y - y)/Math.abs(Global.distance(x, y, Player.player.x, Player.player .y));
		xVel = dx * Global.bulletSpeed;
		yVel = dy * Global.bulletSpeed;
		c = Color.magenta.brighter();
		damage = 1;
		gfxPoints = GfxPoint.loadSquare(2, c);
		Entity.entities.add(this);
	}

	EnemyBullet(float px, float py, float dx, float dy)
	{
		x = px;
		y = py;
		height = 4;
		width = 4;
		xVel = dx * Global.bulletSpeed;
		yVel = dy * Global.bulletSpeed;
		c = Color.magenta.brighter();
		damage = 1;
		gfxPoints = GfxPoint.loadSquare(2, c);
		Entity.entities.add(this);
	}
	
	void move()
	{
		super.move();
	}
	
	void collide(Entity e)
	{
		if(e instanceof Enemy)
		{

		}
		else if(e instanceof Player)
		{
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, 15, Color.cyan, Color.magenta));
			flagDestroy();
			e.takeDamage(damage);
		}
		else if(e instanceof Bullet)
		{
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, 5, Color.magenta, Color.magenta));
			flagDestroy();
			e.flagDestroy();
		}
		else if(e instanceof Wall)
		{
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, 5, Color.magenta, Color.magenta));
			this.flagDestroy();
		}
	}
}
