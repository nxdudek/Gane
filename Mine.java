import java.awt.Color;

public class Mine extends Bullet
{	
	Mine()
	{
		xVel = 0;
		yVel = 0;
		x = Player.player.x;
		y = Player.player.y;
		height = 4;
		width = 4;
		damage = 1;
		c = Color.yellow;
		gfxPoints = GfxPoint.loadSquare(2, c);
		Entity.entities.add(this);
	}
	
	void death()
	{
		Explosion.explosions.add(new Explosion(x, y, 200, 1));
		this.flagDestroy();
		Player.eb = null;
		ParticleGenerator.generators.add(new ParticleGenerator(x, y, 25, Color.yellow));
	}
	
	void collide(Entity e)
	{
		if(e instanceof Enemy)
		{
			death();
			((Enemy)e).takeDamage(damage);
		}
		else if(e instanceof Bullet)
		{
			death();
			e.flagDestroy();
		}
		else if(e instanceof Wall)
		{
			death();
		}
	}
}
