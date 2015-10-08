import java.util.Random;


public class Game
{
	static Random gen = new Random();

	static void gameTick()
	{
		Entity.moveEntities();
		Game.detectCollisions();
		Game.destroyEntities();
	}
	
	static void gameTickNonEntity()
	{
		ParticleGenerator.moveGenerators();
		Explosion.explosionTick();
		Explosion.cleanExplosions();
	}
	
	static void detectCollisions()
	{
		Entity a, b;
		int esize = Entity.entities.size();
		for(int i = 0; i < esize; ++i)
		{
			for(int j = 0; j < esize; ++j)
			{
				a = Entity.entities.get(i);
				b = Entity.entities.get(j);
				if(a.equals(b))
					break;
				else if(a.destroy || b.destroy)
					continue;
				else
				{
				/*	if(Global.distance(a.x, a.y, b.x, b.y) < (a.maxPointDist + b.maxPointDist))
					{
						if(Entity.polygonCollision(a, b))
							a.collide(b);
					}*/
					if(Math.abs((a.x - b.x)) <= (a.width + b.width)/2 && Math.abs((a.y - b.y)) <= (a.height + b.height)/2 )
						a.collide(b);
				}
			}
		}
	}
	
	synchronized static void destroyEntities()
	{
		for(int i = 0; i < Entity.entities.size(); ++i)
		{
			if(Entity.entities.get(i).destroy)
			{
				Entity.entities.remove(i);
				--i;
			}
		}
	}
	
	static void lose()
	{
		Main.pause = true;
	}
	
	static void newGame()
	{
		Entity.entities.clear();
		Level.level = 0;
		Level.loadLevel();
		Player.player = new Player(0, 0);
		Main.pause = false;
	}
}
