import java.awt.Color;
import java.util.ArrayList;


public class GfxPoint
{
	float x;
	float y;
	Color c;
	
	GfxPoint()
	{
		
	}
	
	GfxPoint(float px, float py)
	{
		x = px;
		y = py;
		c = Color.white;
	}
	
	GfxPoint(float px, float py, Color cl)
	{
		x = px;
		y = py;
		c = cl;
	}
	
	static GfxPoint minus(GfxPoint p, float mx, float my)
	{
		p.x -= mx;
		p.y -= my;
		return p;
	}
		
	static ArrayList<GfxPoint> loadSquare(int s, Color c)
	{
		ArrayList<GfxPoint> gfxPoints = new ArrayList<GfxPoint>();
		gfxPoints.add(new GfxPoint(-s, -s, c));
		gfxPoints.add(new GfxPoint(s, -s, c));
		gfxPoints.add(new GfxPoint(-s, -s, c));
		gfxPoints.add(new GfxPoint(-s, s, c));
		gfxPoints.add(new GfxPoint(s, s, c));
		gfxPoints.add(new GfxPoint(-s, s, c));
		gfxPoints.add(new GfxPoint(s, s, c));
		gfxPoints.add(new GfxPoint(s, -s, c));
		return gfxPoints;
	}
	
	static ArrayList<GfxPoint> loadRectangle(int w, int h, Color c)
	{
		ArrayList<GfxPoint> gfxPoints = new ArrayList<GfxPoint>();
		gfxPoints.add(new GfxPoint(-w, -h, c));
		gfxPoints.add(new GfxPoint(w, -h, c));
		gfxPoints.add(new GfxPoint(-w, -h, c));
		gfxPoints.add(new GfxPoint(-w, h, c));
		gfxPoints.add(new GfxPoint(w, h, c));
		gfxPoints.add(new GfxPoint(-w, h, c));
		gfxPoints.add(new GfxPoint(w, h, c));
		gfxPoints.add(new GfxPoint(w, -h, c));
		return gfxPoints;
	}
	
	static ArrayList<GfxPoint> loadFloater()
	{
		Color c = Color.green;
		ArrayList<GfxPoint> gfxPoints = new ArrayList<GfxPoint>();
		gfxPoints.add(new GfxPoint(-Global.floaterHalfSize, -Global.floaterHalfSize, c));
		gfxPoints.add(new GfxPoint(Global.floaterHalfSize, -Global.floaterHalfSize, c));
		
		gfxPoints.add(new GfxPoint(-Global.floaterHalfSize, -Global.floaterHalfSize, c));
		gfxPoints.add(new GfxPoint(-Global.floaterHalfSize/2, Global.floaterHalfSize, c));
		
		gfxPoints.add(new GfxPoint(Global.floaterHalfSize/2, Global.floaterHalfSize, c));
		gfxPoints.add(new GfxPoint(-Global.floaterHalfSize/2, Global.floaterHalfSize, c));
		
		gfxPoints.add(new GfxPoint(Global.floaterHalfSize/2, Global.floaterHalfSize, c));
		gfxPoints.add(new GfxPoint(Global.floaterHalfSize, -Global.floaterHalfSize, c));
		return gfxPoints;
	}
}
