package com.geneticAlgorithem;

import com.bloonsTd.Map;

public class Creature
{
    private Genome genome;
    private boolean isEvaluated;
    private float score;
    private int age;

    public Creature()
    {
        this.setGenome(new Genome());
    }

    /**
     * @param other - copying all the data of the other creature to this one
     */
    public void copyFrom(Creature other)
    {
        this.setScore(other.getScore());
        this.genome.copyFrom(other.getGenome());
        this.setAge(this.getAge() + 1);
    }

    @Override
    public String toString()
    {
        String s = "";

        s += "score: " + this.getScore() + "\n";

        s += this.genome.toString();

        return s;
    }

    public void twoParentsReproduce(Creature parent1, Creature parent2)
    {
        this.genome.twoParentsReproduce(parent1.getGenome(), parent2.getGenome());
        this.setAge(0);
    }

    public void initRandomCreature()
    {
        this.genome.initRandomGenome();
    }

    public void evaluate(Map map)
    {
        // TODO
        // this.setScore((float) Math.random());
        this.setScore(this.genome.evaluate(map) - this.getAge() / 1000f);
    }

    public void mutate()
    {
        this.genome.mutate();
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public Genome getGenome()
    {
        return genome;
    }

    public void setGenome(Genome genome)
    {
        this.genome = genome;
    }

    public boolean isEvaluated()
    {
        return isEvaluated;
    }

    public void setEvaluated(boolean isEvaluated)
    {
        this.isEvaluated = isEvaluated;
    }

    public float getScore()
    {
        return score;
    }

    public void setScore(float score)
    {
        this.score = score;
    }
}
