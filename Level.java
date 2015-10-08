import java.util.ArrayList;
import java.util.Random;

public class Level
{
	static ArrayList<Level> levels = new ArrayList<Level>();
	static Level currentLevel;
	static int enemiesLeft;
	static boolean testmode = false;
	static int level = 0;
	static int floatersLeft = 0;
	static int gunnersLeft = 0;
	static int flowersLeft = 0;
	static int spawnersLeft = 0;
	
	int spawnFloaters;
	int spawnGunners;
	int spawnSappers;
	int spawnFlowers;
	int spawnSpawners;
	int spawnBrightbursts;
	int spawnOctopi;
	int spawnCitadels;
	int spawnChaospheres;
	int spawnGatlings;
	int totalEnemies;
	
	
	Level(int ft, int gn, int sp, int fl, int es, int bb, int oc, int ct, int cs, int gt)
	{
		spawnFloaters = ft;
		spawnGunners = gn;
		spawnSappers = sp;
		spawnFlowers = fl;
		spawnSpawners = es;
		spawnBrightbursts = bb;
		spawnOctopi = oc;
		spawnCitadels = ct;
		spawnChaospheres = cs;
		spawnGatlings = gt;
		totalEnemies = ft + gn + sp + fl + es + bb + oc + ct + cs + gt;
	}
	
	static void initializeLevels()
	{
		// floaters gunners sappers flowers floaterspawners brightbursts octopi citadels chaosphere gatling
		//					 fl gn sp fl fs bb o ct cs gt
		levels.add(new Level(2, 1, 0, 0, 0, 0 ,0, 0, 0, 0));
		levels.add(new Level(5, 2, 1, 0, 0, 0, 0, 0, 0, 0));
		levels.add(new Level(4, 4, 4, 1, 0, 0, 0, 0, 0, 0)); // 3
		levels.add(new Level(0, 5, 2, 0, 3, 0, 0, 0, 0, 0));
		levels.add(new Level(6, 2, 2, 4, 2, 0, 0, 0, 0, 0));
		levels.add(new Level(4, 2, 4, 2, 1, 2, 0, 0, 0, 0)); // 6
		levels.add(new Level(0, 2, 4, 0, 1, 4, 1, 0, 0, 0));
		levels.add(new Level(6, 2, 4, 2, 1, 1, 2, 1, 0, 0));
		levels.add(new Level(4, 2, 6, 2, 1, 1, 3, 1, 1, 0)); // 9
		levels.add(new Level(0, 2, 2, 0, 0, 0, 2, 1, 1, 1)); // 10
		levels.add(new Level(0, 0, 2, 0, 0, 0, 4, 2, 2, 1)); // 11
		levels.add(new Level(0, 0, 4, 0, 0, 2, 2, 3, 2, 2)); // 12

		int x, y;
		for(int i = 0; i < Global.starCount; ++i)
		{
			x = Game.gen.nextInt(5000)-2500;
			y = Game.gen.nextInt(5000)-2500;
			Star.stars.add(new Star(x, y));
		}
	}
	
	static void loadLevel()
	{
		//testmode = true;

		Main.delay = 0;		
		if((level >= levels.size()))
		{
			System.out.println("no more levels");
			System.exit(0);
		}
		currentLevel = levels.get(level);
		level++;
		enemiesLeft = currentLevel.totalEnemies;
		
		resetLevel();
		Global.resetToDefaults();
		
		new Wall(0, -1000, 2000, 30);
		new Wall(0, 1000, 2000, 30);
		new Wall(-1000, 0, 30, 2000);
		new Wall(1000, 0, 30, 2000);

		if(!testmode)
		{
			spawnFloater();
			spawnGunner();
			spawnSapper();
			spawnFlower();
			spawnEnemySpawner();
			spawnBrightburst();
			spawnOctopus();
			spawnCitadel();
			spawnChaosphere();
			spawnGatling();
		}
		else
		{
			enemiesLeft = 10000;
			
			Player.health = 1000;
			Player.energy = 10000;
			
			new Aegis(-300, -300);
			//new Gatling(-300, -300);
			//new Chaosphere(-300, -300);
			//new Citadel(-300, -300);
			//new Octopus(-300, -300);
			//new Brightburst(-300, -300);
			//new EnemySpawner(-300, -300);
			//new Flower(-300,-300);
			//new Sapper(-300,-300);
			//new Gunner(-300,-300);
			//new Floater(-300,-300);
		}

		
		new Wall(-800, 400, 40, 300);
		new Wall(-800, -400, 40, 300);
		new Wall(-300, 400, 300, 80);
		new Wall(-200, 800, 20, 200);
		new Wall(350, 325, 100, 100);
		new Wall(550, 150, 100, 100);
		new Wall(150, 50, 100, 100);
		new Wall(150, -150, 100, 100);
		new Wall(150, -300, 100, 100);
	}
	
	static void resetLevel()
	{
		floatersLeft = 0;
		gunnersLeft = 0;
		flowersLeft = 0;
		spawnersLeft = 0;
		Player.energy = 100;
		if(Player.player != null)
		{	
			Player.health = 5;
			Player.calcHealth();
			Player.player.xVel = 0;
			Player.player.yVel = 0;
		}
	}
	
	static void spawnFloater()
	{
		int x, y;
		for(int i = 0; i < currentLevel.spawnFloaters; ++i)
		{
			x = Game.gen.nextInt(1800)-900;
			y = Game.gen.nextInt(1800)-900;
			if(Player.player != null)
			{
				while(Global.distance(x, y, Player.player.x, Player.player.y) < 100)
				{
					x = Game.gen.nextInt(1800)-900;
					y = Game.gen.nextInt(1800)-900;					
				}
			}
			new Floater(x,y);
		}
	}
	
	static void spawnGunner()
	{
		int x, y;
		for(int i = 0; i < currentLevel.spawnGunners; ++i)
		{
			x = Game.gen.nextInt(1800)-900;
			y = Game.gen.nextInt(1800)-900;
			if(Player.player != null)
			{
				while(Global.distance(x, y, Player.player.x, Player.player.y) < 100)
				{
					x = Game.gen.nextInt(1800)-900;
					y = Game.gen.nextInt(1800)-900;					
				}
			}
			new Gunner(x,y);
		}
	}
	
	static void spawnSapper()
	{
		int x, y;
		for(int i = 0; i < currentLevel.spawnSappers; ++i)
		{
			x = Game.gen.nextInt(1800)-900;
			y = Game.gen.nextInt(1800)-900;
			if(Player.player != null)
			{
				while(Global.distance(x, y, Player.player.x, Player.player.y) < 100)
				{
					x = Game.gen.nextInt(1800)-900;
					y = Game.gen.nextInt(1800)-900;					
				}
			}
			new Sapper(x,y);
		}
	}
	
	static void spawnFlower()
	{
		int x, y;
		for(int i = 0; i < currentLevel.spawnFlowers; ++i)
		{
			x = Game.gen.nextInt(1800)-900;
			y = Game.gen.nextInt(1800)-900;
			if(Player.player != null)
			{
				while(Global.distance(x, y, Player.player.x, Player.player.y) < 100)
				{
					x = Game.gen.nextInt(1800)-900;
					y = Game.gen.nextInt(1800)-900;					
				}
			}
			new Flower(x,y);
		}
	}
	
	static void spawnEnemySpawner()
	{
		int x, y;
		for(int i = 0; i < currentLevel.spawnSpawners; ++i)
		{
			x = Game.gen.nextInt(1800)-900;
			y = Game.gen.nextInt(1800)-900;
			if(Player.player != null)
			{
				while(Global.distance(x, y, Player.player.x, Player.player.y) < 100)
				{
					x = Game.gen.nextInt(1800)-900;
					y = Game.gen.nextInt(1800)-900;					
				}
			}
			new EnemySpawner(x,y);
		}
	}
	
	static void spawnBrightburst()
	{
		int x, y;
		for(int i = 0; i < currentLevel.spawnBrightbursts; ++i)
		{
			x = Game.gen.nextInt(1800)-900;
			y = Game.gen.nextInt(1800)-900;
			if(Player.player != null)
			{
				while(Global.distance(x, y, Player.player.x, Player.player.y) < 100)
				{
					x = Game.gen.nextInt(1800)-900;
					y = Game.gen.nextInt(1800)-900;					
				}
			}
			new Brightburst(x,y);
		}
	}
	
	static void spawnOctopus()
	{
		int x, y;
		for(int i = 0; i < currentLevel.spawnOctopi; ++i)
		{
			x = Game.gen.nextInt(1800)-900;
			y = Game.gen.nextInt(1800)-900;
			if(Player.player != null)
			{
				while(Global.distance(x, y, Player.player.x, Player.player.y) < 100)
				{
					x = Game.gen.nextInt(1800)-900;
					y = Game.gen.nextInt(1800)-900;					
				}
			}
			new Octopus(x,y);
		}
	}
	
	static void spawnCitadel()
	{
		int x, y;
		for(int i = 0; i < currentLevel.spawnCitadels; ++i)
		{
			x = Game.gen.nextInt(1800)-900;
			y = Game.gen.nextInt(1800)-900;
			if(Player.player != null)
			{
				while(Global.distance(x, y, Player.player.x, Player.player.y) < 100)
				{
					x = Game.gen.nextInt(1800)-900;
					y = Game.gen.nextInt(1800)-900;					
				}
			}
			new Citadel(x,y);
		}
	}
	
	
	static void spawnChaosphere()
	{
		int x, y;
		for(int i = 0; i < currentLevel.spawnChaospheres; ++i)
		{
			x = Game.gen.nextInt(1800)-900;
			y = Game.gen.nextInt(1800)-900;
			if(Player.player != null)
			{
				while(Global.distance(x, y, Player.player.x, Player.player.y) < 100)
				{
					x = Game.gen.nextInt(1800)-900;
					y = Game.gen.nextInt(1800)-900;					
				}
			}
			new Chaosphere(x,y);
		}
	}
	
	static void spawnGatling()
	{
		int x, y;
		for(int i = 0; i < currentLevel.spawnGatlings; ++i)
		{
			x = Game.gen.nextInt(1800)-900;
			y = Game.gen.nextInt(1800)-900;
			if(Player.player != null)
			{
				while(Global.distance(x, y, Player.player.x, Player.player.y) < 100)
				{
					x = Game.gen.nextInt(1800)-900;
					y = Game.gen.nextInt(1800)-900;					
				}
			}
			new Gatling(x,y);
		}
	}
	
}
