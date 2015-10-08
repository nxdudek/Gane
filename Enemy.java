import java.awt.Color;
import java.util.ArrayList;


public class Enemy extends Entity
{
	int health;
	ArrayList<Brain> brains = new ArrayList<Brain>();
	
	void death()
	{
		for(int i = 0; i < brains.size(); ++i)
		{
			brains.get(i).death();
			--i;
		}
		brains.clear();
		ParticleGenerator.gen(x, y, 0, 0, 10, c, c);
		flagDestroy();
		if(!disabled)
			Level.enemiesLeft--;
		if(Level.enemiesLeft == 0)
			Level.loadLevel();
	}
	
	void takeDamage(int d)
	{
		if(health > 0)
		{
			health -= d;
			darken();
			if(health <= 0)
			{
				death();
			}
		}
	}
	
	void collide(Entity e)
	{
		if(e instanceof Player)
		{
			takeDamage(10);
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, 15, Color.cyan, c.brighter()));
			Player.player.takeDamage(1);
		}
		else if(e instanceof Enemy)
		{

		}
		else if(e instanceof EnemyBullet)
		{
			
		}
		else if(e instanceof Bullet && !(e instanceof EnemyBullet))
		{
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, 15, c.brighter(), e.c.brighter()));
			e.flagDestroy();
			this.takeDamage( ( (Bullet)e).damage );
		}
		else if(e instanceof Debris)
		{
			takeDamage(1);
			e.takeDamage(1);
		}
		else if(e instanceof Wall)
		{
			bounce(e, this);
		}
	}
}
