import java.awt.Color;


public class ColorList
{
	static Color[] cyan = new Color[11];
	
	static void initColors()
	{
		initCyan();
	}
	
	static void initCyan()
	{
		cyan[5] = Color.cyan;
		cyan[4] = cyan[5].darker();
		cyan[3] = cyan[4].darker();
		cyan[2] = cyan[3].darker();
		cyan[1] = cyan[2].darker();
		cyan[0] = cyan[1].darker();
		cyan[6] = cyan[5].brighter();
		cyan[7] = cyan[6].brighter();
		cyan[8] = cyan[7].brighter();
		cyan[9] = cyan[8].brighter();
		cyan[10] = cyan[9].brighter();
	}
}
