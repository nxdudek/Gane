import java.awt.Color;


public class PiercingBullet extends Bullet
{

	PiercingBullet(int dx, int dy)
	{
		super(dx, dy);
		c = Color.green;
		damage = 2;
		gfxPoints = GfxPoint.loadSquare(2, c);
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
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, 15, c.brighter(), e.c.brighter()));
			flagDestroy();
			e.flagDestroy();
		}
		else if(e instanceof Wall)
		{
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, 2, c.brighter(), c.brighter()));
		}
	}
}
