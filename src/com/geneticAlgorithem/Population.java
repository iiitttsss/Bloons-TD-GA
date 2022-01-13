package com.geneticAlgorithem;

import com.bloonsTd.Map;

import java.util.Arrays;
import java.util.Comparator;

public class Population
{
    public static final int POPULATION_SIZE = 100;// the size of the total population
    public static final int KEEP_N_TOP_CREATURES = 5; // the n creatures with the highest score will stay
    private Creature[][] creaturesN;
    private int usingCreatureNumber;
    private int generation;
    // in the population

    public Population()
    {
        this.setCreaturesN(new Creature[2][Population.POPULATION_SIZE]);
        this.initPopulation();
    }

    /**
     * @return a random parent (creature) - the selection weight is the score of the
     * creature
     */
    private Creature poolSelection(float scoreSum)
    {
        float r = (float) (Math.random() * scoreSum);

        int i = 0;
        while (r > 0)
        {
            r -= this.getCreatures()[i].getScore();
            i++;
        }
        return this.getCreatures()[i - 1];
    }

    @Override
    public String toString()
    {
        String s = "";
        for (Creature creature : this.getCreatures())
        {
            s += creature.toString();
            s += "\n";
        }
        s += "-------------------------------\n";
        return s;
    }

    private void evaluateAllCreatures(Map map)
    {
        for (int i = 0; i < this.getCreatures().length; i++)
        {
            Creature creature = this.creaturesN[this.usingCreatureNumber][i];
            if (!creature.isEvaluated())
            {
                creature.evaluate(map);
            }
        }
    }

    private void reproduce()
    {
        Arrays.sort(this.getCreatures(), Comparator.comparing(Creature::getScore));
//		for(Creature c : this.getCreatures())
//		{
//			System.out.println(c.getScore());
//		}
        //this.getChildren()[0].twoParentsReproduce(this.getCreatures()[1], this.getCreatures()[2]);

        // copy best parents (mark them to not evaluate) - copy at the end of the array
        for (int i = 0; i < Population.KEEP_N_TOP_CREATURES; i++)
        {
            int copyIndex = Population.POPULATION_SIZE - 1 - i;
            this.getChildren()[copyIndex].copyFrom(this.getCreatures()[copyIndex]);
            this.getChildren()[copyIndex].setEvaluated(true);
        }

        // calculating the sum of all the scores for the pool selection range
        float scoreSum = 0;
        for (Creature c : this.getCreatures())
        {
            scoreSum += c.getScore();
        }

        // reproduce from 2 parents
        for (int i = 0; i < Population.POPULATION_SIZE - Population.KEEP_N_TOP_CREATURES; i++)
        {
            // for every new child:
            Creature child = this.getChildren()[i];
            child.setEvaluated(false);
            // 1. choose two random parents
            Creature parent1 = this.poolSelection(scoreSum);
            Creature parent2 = this.poolSelection(scoreSum);
            // 2. reproduce from them
            child.twoParentsReproduce(parent1, parent2);
        }
    }

    private void mutate()
    {
        for (int i = 0; i < this.getChildren().length; i++)
        {
            Creature creature = this.getChildren()[i];
            if (!creature.isEvaluated())
            {
                creature.mutate();
            }
        }
    }

    /**
     * @return - if using 0 return 1, if using 1 return 0
     */
    private int otherCreaturesArrayIndex()
    {
        return (this.usingCreatureNumber + 1) % this.creaturesN.length;
    }

    public void advanceGeneration(Map map)
    {
        this.evaluateAllCreatures(map);
        this.reproduce();
        this.mutate();
        this.generation++;
        this.usingCreatureNumber = this.otherCreaturesArrayIndex();

        System.out.println("lowest score in the population is: " + this.getCreatures()[0].getScore());
		System.out.println("1/4 score in the population is: " + this.getCreatures()[POPULATION_SIZE / 4].getScore());
		System.out.println("median score in the population is: " + this.getCreatures()[POPULATION_SIZE / 2].getScore());
		System.out.println("3/4 score in the population is: " + this.getCreatures()[POPULATION_SIZE * 3 / 4].getScore());
        System.out.println("highest score in the population is: " + this.getCreatures()[POPULATION_SIZE - 1].getScore());
//		for(Creature c : this.getCreatures())
//		{
//			System.out.println(c.getScore() + " | " + c.isEvaluated());
//		}
//		System.out.println(this);
        // System.out.println("\n");
    }

    /**
     * filling the creatures array with new random creaures
     */
    private void initPopulation()
    {
        this.setGeneration(0);

        // creating all the creatures objects
        for (int n = 0; n < this.creaturesN.length; n++)
        {
            for (int i = 0; i < this.creaturesN[n].length; i++)
            {
                Creature creature = new Creature();
                creature.initRandomCreature();
                this.creaturesN[n][i] = creature;
            }
        }

        // initilizing the first population
        for (int i = 0; i < this.getCreatures().length; i++)
        {
            Creature creature = this.getCreatures()[i];
            creature.initRandomCreature();
        }
    }

    public Creature[] getCreatures()
    {
        return this.creaturesN[this.usingCreatureNumber];
    }

    public Creature[] getChildren()
    {
        return this.creaturesN[this.otherCreaturesArrayIndex()];
    }

    public int getGeneration()
    {
        return generation;
    }

    public void setGeneration(int generation)
    {
        this.generation = generation;
    }

    public int getUsingCreatureNumber()
    {
        return usingCreatureNumber;
    }

    public void setUsingCreatureNumber(int usingCreatureNumber)
    {
        this.usingCreatureNumber = usingCreatureNumber;
    }

    public Creature[][] getCreaturesN()
    {
        return creaturesN;
    }

    public void setCreaturesN(Creature[][] creaturesN)
    {
        this.creaturesN = creaturesN;
    }

}
