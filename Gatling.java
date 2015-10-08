import java.awt.Color;


public class Gatling extends Enemy
{
	Gatling(float px, float py)
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
		
		Brain b = new Brain(this, 0, 0, true);
		b.add(new HorizontalBlock(0, 20, b));
		b.add(new HorizontalBlock(0, 20, b));
		b.add(new HorizontalBlock(0, -20, b));
		b.add(new HorizontalBlock(0, -20, b));
		b.add(new VerticalBlock(20, 0, b));
		b.add(new VerticalBlock(20, 0, b));
		b.add(new VerticalBlock(-20, 0, b));
		b.add(new VerticalBlock(-20, 0, b));
		int q, w;
		if(Game.gen.nextInt(2) == 1)
			q = 1;
		else
			q = -1;
		if(Game.gen.nextInt(2) == 1)
			w = 1;
		else
			w = -1;

		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		b.add(new Turret(q, w, b));
		
		brains.add(b);
		
		Entity.entities.add(this);
	}
}
