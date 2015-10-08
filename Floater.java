import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


public class Floater extends Enemy
{	
	Floater(float px, float py)
	{
		x = px;
		y = py;
		width = 50;
		height = 50;
		health = Global.floaterHealth;
		c = Global.enemyColor;
		gfxPoints = GfxPoint.loadSquare(25, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	Floater(float px, float py, ArrayList<GfxPoint> gp)
	{
		x = px;
		y = py;
		width = 50;
		height = 50;
		health = Global.floaterHealth;
		c = Global.enemyColor;
		gfxPoints = gp;
		calcDists();
		Entity.entities.add(this);
	}
	
	void accelerate()
	{
		float vdist = Player.player.y - y;
		float hdist = Player.player.x - x;
		float tdist = (float) Math.sqrt(vdist * vdist + hdist * hdist);
		xVel += (hdist / tdist * Global.floaterAccel);
		yVel += (vdist / tdist * Global.floaterAccel);
		if(xVel > Global.floaterTopSpeed)
			xVel = Global.floaterTopSpeed;
		if(xVel < -Global.floaterTopSpeed)
			xVel = -Global.floaterTopSpeed;
		if(yVel > Global.floaterTopSpeed)
			yVel = Global.floaterTopSpeed;
		if(yVel < -Global.floaterTopSpeed)
			yVel = -Global.floaterTopSpeed;
	}
}

