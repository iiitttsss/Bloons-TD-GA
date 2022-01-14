package com;

import com.bloonsTd.Map;
import com.bloonsTd.MapRenderer;
import com.geneticAlgorithem.Population;
import com.util.Timer;
import processing.core.PApplet;

public class GeneticAlgorithmRunner extends PApplet
{
    public static int setFrameRate = 15;
    private Population population;
    private Map map;
    private MapRenderer mapRenderer;
    // used for deltaTime calculations
    private int lastTime = 0;

    public static void main(String[] args)
    {
        PApplet.main(new String[]{GeneticAlgorithmRunner.class.getName()});
    }

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
        int[] screenSize = {this.width, this.height};
        this.setMap(new Map(screenSize));
        this.setMapRenderer(new MapRenderer(screenSize, this.getMap()));

    }


    @Override
    public void draw()
    {
        // deltaTime calculation
        int currentTime = this.millis();
        Global.setDeltaTime(currentTime - lastTime);
        lastTime = currentTime;

        Timer.start();
        this.getPopulation().advanceGeneration(this.getMap());
        Timer.end();

//        float preScore = this.getPopulation().getCreatures()[this.getPopulation().POPULATION_SIZE - 1].getScore();
//        System.out.println(preScore);
//        System.out.println(this.getPopulation().getCreatures()[this.getPopulation().POPULATION_SIZE - 1]);
//        System.out.println(this.getMap());

        this.getPopulation().getCreatures()[this.getPopulation().POPULATION_SIZE - 1].evaluate(this.getMap());
        System.out.println("age: " +  this.getPopulation().getCreatures()[this.getPopulation().POPULATION_SIZE - 1].getAge());
        System.out.println("genome: " +  this.getPopulation().getCreatures()[this.getPopulation().POPULATION_SIZE - 1].getGenome());


//        float afterScore = this.getPopulation().getCreatures()[this.getPopulation().POPULATION_SIZE - 1].getScore();
//        System.out.println(afterScore);
//        System.out.println(this.getPopulation().getCreatures()[this.getPopulation().POPULATION_SIZE - 1]);
//        System.out.println(this.getMap());
//
//        if (preScore != afterScore)
//        {
//            exit();
//        }

//
//        for(Creature c :  this.getPopulation().getCreatures())
//        {
//            System.out.println(c.getScore());
//        }
        this.render();
        //System.out.println(this.getPopulation());
    }


    private void render()
    {
        this.getMapRenderer().renderMapToBuffer();
        this.image(this.getMapRenderer().getMapBuffer(), 0, 0);

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
