package com.bloonsTd.entities.towers;

import com.bloonsTd.entities.EntitiesManager;
import com.bloonsTd.entities.Entity;
import com.bloonsTd.entities.balloons.BalloonsManager;
import com.bloonsTd.entities.bullets.BulletsManager;

public class TowersManager extends EntitiesManager
{
    private static TowersTypesDictionary towersTypesDictionaryReference;
    public static TowersTypesDictionary getTowersTypesDictionaryReference()
    {
        return towersTypesDictionaryReference;
    }

    public static void setTowersTypesDictionaryReference(TowersTypesDictionary towersTypesDictionaryReference)
    {
        //towersTypesDictionaryReference
        if (TowersManager.getTowersTypesDictionaryReference() == null) {
            TowersManager.towersTypesDictionaryReference = towersTypesDictionaryReference;
        }
    }
	public TowersManager()
	{
		super();
		this.setTypeDict(new TowersTypesDictionary());
        TowersManager.setTowersTypesDictionaryReference((TowersTypesDictionary) this.getTypeDict());
    }

    @Override
    public Tower createNewEntity()
    {
        return new Tower();
    }

    public void update(float deltaTime, BulletsManager bullets, BalloonsManager balloons)
    {
        for (Entity entity : this.getActiveEntities()) {
            Tower tower = (Tower) entity;
            tower.update(deltaTime, bullets, balloons);
        }
    }

    public Tower addTower(int type, float xPos, float yPos, int[][] pathPoints)
    {
        Tower tower = (Tower) super.addEntity();
        tower.init(type, xPos, yPos, pathPoints);

        return tower;
    }

}
