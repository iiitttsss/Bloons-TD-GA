package com;

import com.bloonsTd.Map;
import com.geneticAlgorithem.Population;

import processing.core.*;

public class Main extends PApplet
{

	private Population population;
	private Map map;

	@Override
	public void settings()
	{
		size(1200, 1200);
	}

	@Override
	public void setup()
	{
		System.out.println("start program");
		Global.setPro(this);

		// init population
		this.setPopulation(new Population());
		// init map
		int[] screenSize = { this.width, this.height };
		this.setMap(new Map(screenSize));

		
		for (int i = 0; i < 200; i++)
		{
			this.population.advanceGeneration();
		}
		this.getMap().renderMapToBuffer();
	}

	@Override
	public void draw()
	{
		// exit();
		this.update();
		this.render();
	}

	private void update()
	{
		this.getMap().update();
	}

	private void render()
	{
		this.getMap().renderMapToBuffer();
		this.image(this.getMap().getMapBuffer(), 0, 0);

	}

	public static void main(String[] args)
	{
		PApplet.main(new String[] { Main.class.getName() });
	}

	public Population getPopulation()
	{
		return population;
	}

	public void setPopulation(Population population)
	{
		this.population = population;
	}

	public Map getMap()
	{
		return map;
	}

	public void setMap(Map map)
	{
		this.map = map;
	}

}
