import java.awt.Color;
import java.awt.Graphics;

public class GravityBomb extends Entity
{
	int maxFreq;
	int freq;
	int damage;
	
	GravityBomb(float px, float py)
	{
		x = px;
		y = py;
		width = 15;
		height = 15;
		c = Color.blue;
		maxFreq = 300;
		freq = 1;
		damage = 5;
		Entity.entities.add(this);
	}
	
	void draw(Graphics g)
	{
		// their position, adjusted for the screen, moved relative to player, centered
		g.setColor(c);
		g.drawOval((int)(x+(Main.windowWidth/2)-(Player.player.x)-(width/2)),
				(int)(y+(Main.windowHeight/2)-(Player.player.y)-(height/2)), width/(maxFreq/freq), height/(maxFreq/freq));
		g.drawOval((int)(x+(Main.windowWidth/2)-(Player.player.x)-(200)),
				(int)(y+(Main.windowHeight/2)-(Player.player.y)-(200)), 400, 400);

	}
	
	void move()
	{
		Entity e;
		for(int i = 0; i < Entity.entities.size(); ++i)
		{
			e = Entity.entities.get(i);
			float d = Global.distance(x, y, e.x, e.y);
			if(d < 200 && ((e instanceof Bullet) || (e instanceof Enemy)) && !e.part)
			{
				e.xVel -= Global.gravityBombStrength*Math.signum(e.x-x)/(d+1);
				e.yVel -= Global.gravityBombStrength*Math.signum(e.y-y)/(d+1);
			}
		}
		freq++;
		if(freq == maxFreq)
		{
			for(int i = 0; i < Entity.entities.size(); ++i)
			{
				e = Entity.entities.get(i);
				float d = Global.distance(x, y, e.x, e.y);
				if(d < 200 && ((e instanceof Player) || (e instanceof Enemy)) )
				{
					e.takeDamage((int)(damage/(1/d)));
				}
				else if(d < 200 && (e instanceof Bullet))
				{
					e.flagDestroy();
				}
			}
			this.flagDestroy();
			ParticleGenerator.generators.add(new ParticleGenerator(x, y, 25, Color.blue));
		}
	}
}
