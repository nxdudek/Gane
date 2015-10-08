import java.awt.Color;
import java.awt.event.KeyEvent;


public class Global
{
	static int gameTickMilliseconds = 10;
	static int starCount = 100;
	static int maxParticles = 300;
	
	static float energyCap = 100;
	static float energyRegenRate = 0.1f;
	static float playerAcceleration = .1f;
	static float playerTopSpeed = 4;
	static float bulletSpeed = 8;
	
	static int gravityBombStrength = 100;
	static int gravityBombCost = 80;
	static int ringShotCost = 40;
	static int bulletCost = 5;
	static int piercingCost = 15;
	static int explodingCost = 40;
	static int movementCost = 0;
	static int disableThreshold = 20;

	final static int pause = KeyEvent.VK_SPACE;
	final static int restart = KeyEvent.VK_Y;
	final static int quit = KeyEvent.VK_ESCAPE;
	final static int up = KeyEvent.VK_UP;
	final static int down = KeyEvent.VK_DOWN;
	final static int left = KeyEvent.VK_LEFT;
	final static int right = KeyEvent.VK_RIGHT;
	final static int shootUp = KeyEvent.VK_W;
	final static int shootDown = KeyEvent.VK_S;
	final static int shootLeft = KeyEvent.VK_A;
	final static int shootRight = KeyEvent.VK_D;
	final static int modifierOne = KeyEvent.VK_SHIFT;
	final static int modifierTwo = KeyEvent.VK_CONTROL;
	final static int specialAttackOne = KeyEvent.VK_Q;
	final static int specialAttackTwo = KeyEvent.VK_E;
	final static int heal = KeyEvent.VK_Z;

	static int blockHealth = 2;
	static int turretHealth = 1;
	static int turretFiringCooldown = 200;
	static int turretFiringBase = 200;
	
	static int floaterHealth = 2;
	static int floaterHalfSize = 20;
	static float floaterAccel = .01f;
	static float floaterTopSpeed = 1;
	static float floaterTopSpeedDefault = 1;
	final static int floaterSpawnVariance = 500;
	final static int floaterSpawnBase = 1000;
	
	static int gunnerHealth = 1;
	static int gunnerFiringCooldown = 300;
	static int gunnerFiringCooldownDefault = 300;
	final static float gunnerAccel = .005f;
	final static float gunnerTopSpeed = 1;
	final static int gunnerSpawnVariance = 600;
	final static int gunnerSpawnBase = 300;
	
	static int flowerHealth = 1;
	static int petalHealth = 3;
	final static float flowerAccel = .005f;
	final static float flowerTopSpeed = .3f;
	final static int flowerSpawnVariance = 600;
	final static int flowerSpawnBase = 300;
	
	static int sapperExplosionRadius = 300;
	static int sapperHealth = 1;
	static float sapperAccel = .5f;
	static float sapperTopSpeed = 6;
	static int sapperSpawnVariance = 500;
	static int sapperSpawnBase = 1000;
	
	static int brightburstBurstFrequency = 500;
	static int brightburstHealth = 2;
	static int brightcoreHealth = 1;
	static float brightburstTopSpeed = 1;
	static int brightburstSpawnVariance = 500;
	static int brightburstSpawnBase = 1000;

	static int octopusGrowTime = 1200;
	static int octopusSize = 60;
	static int octopusHeadHealth = 5;
	static int octopusTentacleHealth = 2;
	static int octopusTopSpeed = 2;
	static float octopusAccel = .001f;
	static int octopusSpawnVariance = 500;
	static int octopusSpawnBase = 1000;
	
	static int chaosphereMaxSize = 5;
	static int chaosphereBaseSize = 5;
	
	static float swordMaxSpeed = 5;
	static float swordAccel = .001f;
		
	final static Color playerColor = Color.cyan;
	final static Color bulletColor = Color.red;
	final static Color enemyColor = Color.green;
	
	static float distance(float a, float b, float c, float d)
	{
		return (float)Math.sqrt((a-c)*(a-c) + (b-d)*(b-d));
	}
	
	static float distance(GfxPoint a, GfxPoint b)
	{
		return (float)Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
	}
	
	static void resetToDefaults()
	{
		floaterTopSpeed = floaterTopSpeedDefault;
		gunnerFiringCooldown = gunnerFiringCooldownDefault;
	}
}