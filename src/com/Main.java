package com;

import com.bloonsTd.Map;
import com.bloonsTd.MapRenderer;
import com.bloonsTd.entities.towers.TowerPlacer;
import com.bloonsTd.entities.towers.TowersTypesDictionary;
import com.geneticAlgorithem.Population;

import processing.core.PApplet;

public class Main extends PApplet
{

	private Population population;
	private Map map;
	private MapRenderer mapRenderer;

	public static int setFrameRate = 30;
	// used for deltaTime calculations
	private int lastTime = 0;

	@Override
	public void settings()
	{
		this.size(1200, 1200);
	}

	@Override
	public void setup()
	{
		// this is a comment
		System.out.println("start program");
		Global.setPro(this);
		this.frameRate(setFrameRate);

		// init population
		this.setPopulation(new Population());
		// init map
		int[] screenSize = { this.width, this.height };
		this.setMap(new Map(screenSize));
		this.getMap().init();
		this.setMapRenderer(new MapRenderer(screenSize, this.getMap()));
		TowerPlacer.placeTower(map.getPath(), map.getTowers(), 220, 200, TowersTypesDictionary.DART_MONKEY);
		TowerPlacer.placeTower(map.getPath(), map.getTowers(), 320, 200, TowersTypesDictionary.DART_MONKEY);
		TowerPlacer.placeTower(map.getPath(), map.getTowers(), 320, 220, TowersTypesDictionary.DART_MONKEY);//this tower could not be placed


	}


	@Override
	public void draw()
	{
		// deltaTime calculation
		int currentTime = this.millis();
		Global.setDeltaTime(currentTime - lastTime);
		lastTime = currentTime;

		this.update();
		this.render();
	}

	private void update()
	{
		for (int i = 0; i < 1; i++)
		{
			this.getMap().preUpdate();
			this.getMap().update();
		}
	}

	private void render()
	{
		this.getMapRenderer().renderMapToBuffer();
		this.image(this.getMapRenderer().getMapBuffer(), 0, 0);

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

	public int getLastTime()
	{
		return lastTime;
	}

	public void setLastTime(int lastTime)
	{
		this.lastTime = lastTime;
	}

	public MapRenderer getMapRenderer()
	{
		return mapRenderer;
	}

	public void setMapRenderer(MapRenderer mapRenderer)
	{
		this.mapRenderer = mapRenderer;
	}

}
