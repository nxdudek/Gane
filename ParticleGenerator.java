import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class ParticleGenerator
{
	static Particle[] pool = new Particle[Global.maxParticles];
	static ArrayList<ParticleGenerator> generators = new ArrayList<ParticleGenerator>();
	ArrayList<Particle> particles = new ArrayList<Particle>();
	
	static void initParticles()
	{
		for(int i = 0; i < pool.length; ++i)
		{
			pool[i] = new Particle();
		}
	}
	
	static void gen(float x, float y, float xVel, float yVel, int n, Color c, Color e)
	{
		ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, n, c, e));
	}
	
	static void genNarrow(float x, float y, float xVel, float yVel, int n, Color c, Color e)
	{
		ParticleGenerator.generators.add(new ParticleGenerator(x, y, xVel, yVel, n, c, e, 1));
	}
	
	ParticleGenerator(float x, float y, int n)
	{	

		for(int i = 0; i < pool.length; ++i)
		{
			if(!pool[i].used)
			{
				particles.add(pool[i]);
				pool[i].used = true;
				n--;
			}
			if(n == 0)
				break;
		}
		for(Particle p : particles)
		{
			p.seed(x, y);
		}
	}
	
	ParticleGenerator(float x, float y, int n, Color cl)
	{	

		for(int i = 0; i < pool.length; ++i)
		{
			if(!pool[i].used)
			{
				particles.add(pool[i]);
				pool[i].used = true;
				n--;
			}
			if(n == 0)
				break;
		}
		for(Particle p : particles)
		{
			p.seed(x, y, cl);
		}
	}
	
	ParticleGenerator(float x, float y, float dx, float dy, int n, Color cl, Color cl2)
	{	

		for(int i = 0; i < pool.length; ++i)
		{
			if(!pool[i].used)
			{
				particles.add(pool[i]);
				pool[i].used = true;
				n--;
			}
			if(n == 0)
				break;
		}
		for(Particle p : particles)
		{
				p.seed(x, y, dx, dy, cl, cl2);
		}
	}
	
	ParticleGenerator(float x, float y, float dx, float dy, int n, Color cl, Color cl2, int t)
	{	

		for(int i = 0; i < pool.length; ++i)
		{
			if(!pool[i].used)
			{
				particles.add(pool[i]);
				pool[i].used = true;
				n--;
			}
			if(n == 0)
				break;
		}
		for(Particle p : particles)
		{
			if(t == 0)
				p.seed(x, y, dx, dy, cl, cl2);
			else if (t == 1)
				p.seedNarrow(x, y, dx, dy, cl, cl2);
		}
	}
	
	static void drawGenerators(Graphics g)
	{
		for(int i = 0; i < generators.size(); ++i)
		{
			generators.get(i).draw(g);
		}
	}
	
	static void moveGenerators()
	{
		for(int i = 0; i < generators.size(); ++i)
		{
			generators.get(i).move();
		}
	}
	
	void move()
	{
		for(int i = 0; i < particles.size(); ++i)
		{
			particles.get(i).move();
			if(particles.get(i).lifespan <= 0)
			{
				particles.get(i).die();
				particles.remove(particles.get(i));
			}
		}
	}
	
	void draw(Graphics g)
	{
		for(int i = 0; i < particles.size(); ++i)
		{
			particles.get(i).draw(g);
		}
	}
	
}
