import java.awt.Color;


public class Chaosphere extends Enemy
{
	
	void foo(int dx, int dy) {
		brains.add(new Brain(this, dx, dy, true));
		brains.get(brains.size()-1).add(new HorizontalBlock(0, (int)((Game.gen.nextFloat() - .5)*20), brains.get(brains.size()-1)));
		brains.get(brains.size()-1).add(new HorizontalBlock(0, (int)((Game.gen.nextFloat() - .5)*20), brains.get(brains.size()-1)));
		brains.get(brains.size()-1).add(new VerticalBlock( (int)((Game.gen.nextFloat() - .5)*20), 0, brains.get(brains.size()-1)));
		brains.get(brains.size()-1).add(new VerticalBlock( (int)((Game.gen.nextFloat() - .5)*20), 0, brains.get(brains.size()-1)));
		brains.get(brains.size()-1).add(new Turret((int)((Game.gen.nextFloat() - .5)*3), (int)((Game.gen.nextFloat() - .5)*3), brains.get(brains.size()-1)));
	}
	
	Chaosphere(float px, float py)
	{
		x = px;
		y = py;
		xVel = (float) (Game.gen.nextFloat() - .5);
		yVel = (float) (Game.gen.nextFloat() - .5);
		width = 30;
		height = width;
		c = Color.magenta;
		health = 1;
		gfxPoints = GfxPoint.loadSquare(width/2, c);
		gfxPoints.addAll(GfxPoint.loadSquare(width/4, c));
		calcDists();
		Entity.entities.add(this);
		for (int i = 0; i < (Game.gen.nextInt(Global.chaosphereMaxSize) + Global.chaosphereBaseSize) ; i++) {
			foo( (int)((Game.gen.nextFloat() - .5)*100),(int)((Game.gen.nextFloat() - .5)*100));
		}
	}
}
