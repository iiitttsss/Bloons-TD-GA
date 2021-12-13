package com;

import com.geneticAlgorithem.Population;

import processing.core.PApplet;

public class Main extends PApplet
{

	private Population population;

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

		this.setPopulation(new Population());

		for (int i = 0; i < 200; i++)
		{
			this.population.advanceGeneration();
		}
	}

	@Override
	public void draw()
	{
		exit();
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

}
