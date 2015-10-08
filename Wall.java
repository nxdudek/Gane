import java.awt.Color;


public class Wall extends Entity
{
	Wall(int px, int py, int w, int h)
	{
		x = px;
		y = py;
		width = w;
		height = h;
		c = Color.white;
		gfxPoints = GfxPoint.loadRectangle(width/2, height/2, c);
		calcDists();
		Entity.entities.add(this);
	}
	
	void move()
	{
		
	}
	
	void collide(Entity e)
	{
		if(e instanceof Player)
		{
			bounce(this, e);
		}
		else if(e instanceof Block)
		{
			float oldX = ((Block)e).parent.parent.xVel;
			float oldY = ((Block)e).parent.parent.yVel;
			bounce(this, e);
			((Block)e).parent.parent.xVel = e.xVel;
			((Block)e).parent.parent.yVel = e.yVel;
			((Block)e).parent.parent.moveDist(oldX, oldY);
			((Block)e).parent.moveDist(oldX, oldY);
			((Block)e).moveDist(oldX, oldY);
		}
		else if(e instanceof Enemy)
		{
			bounce(this, e);
		}
	}
}
