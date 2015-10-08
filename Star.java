import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Star
{
	int x;
	int y;
	static ArrayList<Star> stars = new ArrayList<Star>();
	
	Star(int px, int py)
	{
		x = px;
		y = py;
	}
	
	void draw(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.drawRect((int)(x+(Main.windowWidth/2)-(Player.player.x)),
				(int)(y+(Main.windowHeight/2)-(Player.player.y)), 1, 1);
	}
	
}
