import java.awt.Color;
import java.util.ArrayList;


public class Citadel extends Enemy
{
	Citadel(float px, float py)
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
		
		brains.add(new Brain(this, -50, -50, true));
		brains.add(new Brain(this, 50, -50, true));
		brains.add(new Brain(this, -50, 50, true));
		brains.add(new Brain(this, 50, 50, true));
		brains.add(new Brain(this, 0, 0, false));

		brains.get(0).add(new HorizontalBlock(0, 21, brains.get(0)));
		brains.get(0).add(new HorizontalBlock(0, -21, brains.get(0)));
		brains.get(0).add(new VerticalBlock(21, 0, brains.get(0)));
		brains.get(0).add(new VerticalBlock(-21, 0, brains.get(0)));
		brains.get(0).add(new Turret(-1, -1, brains.get(0)));
		
		brains.get(1).add(new HorizontalBlock(0, 21, brains.get(1)));
		brains.get(1).add(new HorizontalBlock(0, -21, brains.get(1)));
		brains.get(1).add(new VerticalBlock(21, 0, brains.get(1)));
		brains.get(1).add(new VerticalBlock(-21, 0, brains.get(1)));
		brains.get(1).add(new Turret(1, -1, brains.get(1)));

		brains.get(2).add(new HorizontalBlock(0, 21, brains.get(2)));
		brains.get(2).add(new HorizontalBlock(0, -21, brains.get(2)));
		brains.get(2).add(new VerticalBlock(21, 0, brains.get(2)));
		brains.get(2).add(new VerticalBlock(-21, 0, brains.get(2)));
		brains.get(2).add(new Turret(-1, 1, brains.get(2)));

		brains.get(3).add(new HorizontalBlock(0, 21, brains.get(3)));
		brains.get(3).add(new HorizontalBlock(0, -21, brains.get(3)));
		brains.get(3).add(new VerticalBlock(21, 0, brains.get(3)));
		brains.get(3).add(new VerticalBlock(-21, 0, brains.get(3)));
		brains.get(3).add(new Turret(1, 1, brains.get(3)));

		brains.get(4).add(new VerticalBlock(20, 0, brains.get(4)));
		brains.get(4).add(new VerticalBlock(-20, 0, brains.get(4)));
		brains.get(4).add(new HorizontalBlock(0, 20, brains.get(4)));
		brains.get(4).add(new HorizontalBlock(0, -20, brains.get(4)));
		brains.get(4).add(new VerticalBlock(22, 0, brains.get(4)));
		brains.get(4).add(new VerticalBlock(-22, 0, brains.get(4)));
		brains.get(4).add(new HorizontalBlock(0, 22, brains.get(4)));
		brains.get(4).add(new HorizontalBlock(0, -22, brains.get(4)));
		brains.get(4).add(new VerticalBlock(60, 0, brains.get(4)));
		brains.get(4).add(new VerticalBlock(-60, 0, brains.get(4)));
		brains.get(4).add(new HorizontalBlock(0, 60, brains.get(4)));
		brains.get(4).add(new HorizontalBlock(0, -60, brains.get(4)));
		
		Entity.entities.add(this);
	}
}










