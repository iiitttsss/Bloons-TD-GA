package com.geneticAlgorithem;

import com.bloonsTd.Map;
import com.bloonsTd.entities.towers.TowerPlacer;
import com.bloonsTd.entities.towers.TowersTypesDictionary;

public class Genome
{
    public static final int TOWER_BUILD_LIST_SIZE = 16;
    public static final int ACTIONS_LIST_SIZE = 1;
    public static final int EXECUTE_ORDER_LIST_SIZE = TOWER_BUILD_LIST_SIZE + ACTIONS_LIST_SIZE;
    // TODO - move these constants to the bloons TD package
    public final int numberOfPossibleTowers = 1;
    public final int numberOfPossibleActions = 7;
    public final int maxPosition = 1200;
    private int[][] towerBuildList;
    private int[][] actionsList;
    private int[] executeOrderList;


    public Genome()
    {
        this.setTowerBuildList(new int[TOWER_BUILD_LIST_SIZE][3]);
        this.setActionsList(new int[ACTIONS_LIST_SIZE][2]);
        this.setExecuteOrderList(new int[EXECUTE_ORDER_LIST_SIZE]);
    }

    /**
     * @return - returning how much update did the creature survive
     */
    public float evaluate(Map map)
    {
        int score = 0;
        map.init();
        for (int[] towerPlace : this.getTowerBuildList())
        {
            TowerPlacer.placeTower(map.getPath(), map.getTowers(), towerPlace[0], towerPlace[1], TowersTypesDictionary.DART_MONKEY);
        }
        while (!map.isGameOver())
        {
            map.preUpdate();
            map.update();
            score++;
        }
        return (float) score * score / 1000000;
    }

    public void copyFrom(Genome other)
    {
        for (int i = 0; i < TOWER_BUILD_LIST_SIZE; i++)
        {
            this.getTowerBuildList()[i][0] = other.getTowerBuildList()[i][0];
            this.getTowerBuildList()[i][1] = other.getTowerBuildList()[i][1];
            this.getTowerBuildList()[i][2] = other.getTowerBuildList()[i][2];

        }

        for (int i = 0; i < ACTIONS_LIST_SIZE; i++)
        {
            this.getActionsList()[i][0] = other.getActionsList()[i][0];
            this.getActionsList()[i][1] = other.getActionsList()[i][1];
        }

        for (int i = 0; i < EXECUTE_ORDER_LIST_SIZE; i++)
        {
            this.getExecuteOrderList()[i] = other.getExecuteOrderList()[i];
        }
    }

    @Override
    public String toString()
    {
        String s = "";

        s += "towerBuildList\n";
        for (int[] towerBuild : towerBuildList)
        {
            s += "(" + towerBuild[0] + ", " + towerBuild[1] + ", " + towerBuild[2] + "), ";
        }
//		s += "\n";
//		s += "actionsList\n";
//		for (int[] actions : actionsList)
//		{
//			s += "(" + actions[0] + ", " + actions[1] + "), ";
//		}
//		s += "\n";
//		s += "executeOrder\n";
//		for (int executeOrder : executeOrderList)
//		{
//			s += executeOrder;
//		}
//		s += " :" + executeOrderList.length;
//		s += "\n";

        return s;
    }

    public void twoParentsReproduce(Genome parent1, Genome parent2)
    {
        final int numberOfParts = 5;
        for (int part = 0; part < numberOfParts; part++)
        {
            int copyFromParentNumber = (int) (Math.random() * 2); // 0 or 1
            Genome parent = (copyFromParentNumber == 0 ? parent1 : parent2);

            for (int i = Genome.TOWER_BUILD_LIST_SIZE * part / numberOfParts; //
                 i < Genome.TOWER_BUILD_LIST_SIZE * (part + 1) / numberOfParts; i++)
            {
                this.towerBuildList[i][0] = parent.getTowerBuildList()[i][0];
                this.towerBuildList[i][1] = parent.getTowerBuildList()[i][1];
                this.towerBuildList[i][2] = parent.getTowerBuildList()[i][2];
            }

            for (int i = Genome.ACTIONS_LIST_SIZE * part / numberOfParts; //
                 i < Genome.ACTIONS_LIST_SIZE * (part + 1) / numberOfParts; i++)
            {
                this.actionsList[i][0] = parent.getActionsList()[i][0];
                this.actionsList[i][1] = parent.getActionsList()[i][1];
            }

            for (int i = Genome.EXECUTE_ORDER_LIST_SIZE * part / numberOfParts; //
                 i < Genome.EXECUTE_ORDER_LIST_SIZE * (part + 1) / numberOfParts; i++)
            {
                this.executeOrderList[i] = parent.getExecuteOrderList()[i];
            }
        }
    }

    public void initRandomGenome()
    {
        for (int i = 0; i < towerBuildList.length; i++)
        {
            towerBuildList[i][0] = (int) (Math.random() * (this.maxPosition)); // 0-100
            towerBuildList[i][1] = (int) (Math.random() * (this.maxPosition)); // 0-100
            towerBuildList[i][2] = (int) (Math.random() * numberOfPossibleTowers);
        }

        for (int i = 0; i < actionsList.length; i++)
        {
            actionsList[i][0] = (int) (Math.random() * numberOfPossibleActions);

            // towerBuildListSize * 3 because it is a big enough number
            // (towerBuildListSize will be deterimined in a way that will be enough for the
            // game duration)
            // if there are not enought towers build, will use the % of this number
            actionsList[i][1] = (int) (Math.random() * TOWER_BUILD_LIST_SIZE * 3);
        }

        for (int i = 0; i < executeOrderList.length; i++)
        {
            executeOrderList[i] = (int) (Math.random() * 2);
        }
    }

    public void mutate()
    {
        // TODO
        final float mutationRate = 0.01f;
        for (int i = 0; i < Genome.TOWER_BUILD_LIST_SIZE; i++)
        {
            int[] towerBuildOrder = this.towerBuildList[i];
            for (int j = 0; j < towerBuildOrder.length; j++)
            {
                float r = (float) Math.random();
                if (r < mutationRate)
                {
                    int numberOfOptions = 0;
                    switch (j)
                    {
                        case 0:
                        case 1:
                            numberOfOptions = (this.maxPosition);
                            break;
                        case 2:
                            numberOfOptions = numberOfPossibleTowers;
                            break;
                    }
                    towerBuildList[i][j] = (int) (Math.random() * numberOfOptions);
                    //System.out.println("-------------------------------------------------mutation");
                }
            }
        }
        for (int i = 0; i < Genome.ACTIONS_LIST_SIZE; i++)
        {
            int[] actionsOrder = this.actionsList[i];
            for (int j = 0; j < actionsOrder.length; j++)
            {
                float r = (float) Math.random();
                if (r < mutationRate)
                {
                    int numberOfOptions = 0;
                    switch (j)
                    {
                        case 0:
                            numberOfOptions = numberOfPossibleActions;
                            break;
                        case 1:
                            numberOfOptions = TOWER_BUILD_LIST_SIZE * 3;
                            break;
                    }
                    actionsList[i][j] = (int) (Math.random() * numberOfOptions);
                }
            }
        }
        for (int i = 0; i < Genome.EXECUTE_ORDER_LIST_SIZE; i++)
        {
            float r = (float) Math.random();
            if (r < mutationRate)
            {
                this.executeOrderList[i] += 1;
                this.executeOrderList[i] %= 2;
            }
        }
    }

    public int[][] getTowerBuildList()
    {
        return towerBuildList;
    }

    public void setTowerBuildList(int[][] towerBuildList)
    {
        this.towerBuildList = towerBuildList;
    }

    public int[][] getActionsList()
    {
        return actionsList;
    }

    public void setActionsList(int[][] actionsList)
    {
        this.actionsList = actionsList;
    }

    public int[] getExecuteOrderList()
    {
        return executeOrderList;
    }

    public void setExecuteOrderList(int[] executeOrderList)
    {
        this.executeOrderList = executeOrderList;
    }
}
