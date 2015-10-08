import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Gui extends JPanel implements KeyListener
{
	
	Gui(JFrame parent)
	{
		setVisible(true);
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);
		setBackground(Color.BLACK);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		for(int i = 0; i < Entity.entities.size(); ++i)
		{
			Entity.entities.get(i).draw(g);
		}
		for(int i = 0; i < Star.stars.size(); ++i)
		{
			Star.stars.get(i).draw(g);
		}
		for(int i = 0; i < ParticleGenerator.generators.size(); ++i)
		{
			ParticleGenerator.generators.get(i).draw(g);
		}
		for(int i = 0; i < Explosion.explosions.size(); ++i)
		{
			Explosion.explosions.get(i).draw(g);
		}
		Player.player.draw(g);
		g.setColor(Color.WHITE);
		g.drawString("Level: " + Level.level, 10, 20);
		g.drawString("Enemies left: " + Level.enemiesLeft, 10, 40);
		g.drawString("ENERGY: " + (int)Player.energy, Main.windowWidth/2-30, 40);
		g.drawString("Health: " + Player.healthString, Main.windowWidth/2-30, 60);

		if(Main.pause && Player.health == 0)
			g.drawString("GAME OVER", Main.windowWidth/2-50, Main.windowHeight/2-20);
		else if(Main.pause)
			g.drawString("paused", Main.windowWidth/2-50, Main.windowHeight/2-20);

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		switch(e.getKeyCode())
		{
		case Global.up:
				Player.player.up = true;
				break;
		case Global.down:
			Player.player.down = true;
			break;
		case Global.left:
			Player.player.left = true;
			break;
		case Global.right:
			Player.player.right = true;
			break;
		case Global.shootUp:
			Player.player.shoot(0, -1);
			break;
		case Global.shootDown:
			Player.player.shoot(0, 1);
			break;
		case Global.shootLeft:
			Player.player.shoot(-1, 0);
			break;
		case Global.shootRight:
			Player.player.shoot(1, 0);
			break;
		case Global.modifierOne:
			Player.player.modifierOne = true;
			break;
		case Global.modifierTwo:
			Player.player.modifierTwo = true;
			Player.specialAttackThree();
			break;
		case Global.specialAttackOne:
			Player.player.specialAttackOne();
			break;
		case Global.specialAttackTwo:
			Player.player.specialAttackTwo();
			break;
		case Global.restart:
			Game.newGame();
			break;
		case Global.heal:
			if(Player.health < 5 && Player.energy >= 100)
			{
				Player.health++;
				Player.energy -= 100;
				Player.calcHealth();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		switch(e.getKeyCode())
		{
			case Global.quit:
				System.exit(0);
				break;
			case Global.up:
				Player.player.up = false;
				break;
			case Global.down:
				Player.player.down = false;
				break;
			case Global.left:
				Player.player.left = false;
				break;
			case Global.right:
				Player.player.right = false;
				break;
			case Global.modifierOne:
				Player.player.modifierOne = false;
				break;
			case Global.modifierTwo:
				Player.player.modifierTwo = false;
				break;
			case Global.pause:
				if(Player.health > 0)
					Main.pause = Main.pause ? false : true;
				break;
			default:
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}
}
