package com.bloonsTd.entities.towers;

import com.bloonsTd.entities.Entity;
import com.bloonsTd.path.Path;
import com.util.MathUtils;

public class TowerPlacer
{
	/**
	 * place a tower only if it is 1. not on a path 2. inside the game borders 3.
	 * not intersectiing other towers
	 * 
	 * @param path      - the paths the balloons follow
	 * @param towers    - all the other towers
	 * @param x         - the x position of the new tower
	 * @param y         - the y position of the new tower
	 * @param towerType - the type of the new tower
	 * @return - return true if succeeded in building a tower, false otherwise
	 */
	public static boolean placeTower(Path path, TowersManager towers, int x, int y, int towerType)
	{
		float towerRadius = Tower.calculateRadiusBasedOnType(towerType);
		if (!TowerPlacer.doesTowerIntersectThePath(x, y, towerRadius, path))
		{
			if (!TowerPlacer.doesTowerIntersectOtherTowers(towers, x, y, towerRadius))
			{
				if (TowerPlacer.isTheTowerInsideTheMap(x, y, towerRadius, path))
				{
					towers.addTower(towerType, x, y, path.getPathPoints());
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isTheTowerInsideTheMap(float towerX, float towerY, float towerRadius, Path path)
	{
		float tx = towerX;// tower x
		float ty = towerY;// tower y
		float tr = towerRadius;// tower radius

		float mw = path.getMapSize()[0];// map width
		float mh = path.getMapSize()[1];// map height

		return (tx - tr >= 0) && (tx + tr < mw) && (ty - tr >= 0) && (ty + tr < mh);
	}

	private static boolean doesTowerIntersectThePath(float towerX, float towerY, float towerRadius, Path path)
	{
		return path.getDistanceFromClosestPath()[(int) towerY][(int) towerX] <= towerRadius + path.getPathWidth();
	}

	private static boolean doesTowerIntersectOtherTowers(TowersManager towers, float towerX, float towerY,
			float towerRadius)
	{
		for (Entity entity : towers.getActiveEntities())
		{
			Tower tower = (Tower) entity;

			float distSq = MathUtils.distanceSquare(tower.getxPos(), tower.getyPos(), towerX, towerY);
			float limitDistSq = (tower.getRadius() + towerRadius) * (tower.getRadius() + towerRadius);
			if (distSq <= limitDistSq)
			{
				return true;
			}
		}
		return false;
	}
}
