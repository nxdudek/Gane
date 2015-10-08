import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.*;

public class Main
{
	static int windowWidth;
	static int windowHeight;
	static boolean pause = false;
	static int delay = 200;
	
	public static void main(String[] args)
	{
		ColorList.initColors();
		Level.initializeLevels();
		ParticleGenerator.initParticles();

		Game.newGame();
		
		JFrame w = new JFrame();
		w.add(new Gui(w));
		w.setUndecorated(true);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		gd.setFullScreenWindow(w);
		w.setVisible(true);
		windowWidth = w.getWidth();
		windowHeight = w.getHeight();
		

		//Timer t = new Timer(Global.gameTickMilliseconds, Game.gameTickNonEntity());

		
		while(true)
		{
			Game.gameTickNonEntity();
			if(delay > 0)
				delay--;
			else
			{
				if(!pause)
					Game.gameTick();
			}
			w.repaint();
			try
			{
				Thread.sleep(Global.gameTickMilliseconds);
			}
			catch(Exception e) {	}
		}
	}

}
