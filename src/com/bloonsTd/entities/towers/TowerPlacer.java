package com.bloonsTd.entities.towers;

import java.util.ArrayList;

import com.bloonsTd.path.Path;
import com.util.MathUtils;

public class TowerPlacer
{
	public static void placeTower(Path path, ArrayList<Tower> towers, int x, int y, int towerType)
	{
		// if tower do not intersect the path
		Tower newTower = new Tower(x, y, towerType, path.getPathPoints());
		if (!TowerPlacer.doesTowerIntersectThePath(towers, newTower, path))
		{
			if (!TowerPlacer.doesTowerIntersectOtherTowers(towers, newTower))
			{
				if (TowerPlacer.isTheTowerInsideTheMap(newTower, path))
				{
					towers.add(newTower);
				}
			}
		}
	}

	private static boolean isTheTowerInsideTheMap(Tower newTower, Path path)
	{
		float tx = newTower.getxPos();// tower x
		float ty = newTower.getyPos();// tower y
		float tr = newTower.getRadius();// tower radius

		float mw = path.getMapSize()[0];// map width
		float mh = path.getMapSize()[1];// map height

		return (tx - tr >= 0) && (tx + tr < mw) && (ty - tr >= 0) && (ty + tr < mh);
	}

	private static boolean doesTowerIntersectThePath(ArrayList<Tower> towers, Tower newTower, Path path)
	{
		return path.getDistanceFromClosestPath()[(int) newTower.getyPos()][(int) newTower.getxPos()] <= newTower
				.getRadius() + path.getPathWidth();
	}

	private static boolean doesTowerIntersectOtherTowers(ArrayList<Tower> towers, Tower newTower)
	{
		for (Tower tower : towers)
		{
			float distSq = MathUtils.distanceSquare(tower.getxPos(), tower.getyPos(), newTower.getxPos(),
					newTower.getyPos());
			float limitDistSq = (tower.getRadius() + newTower.getRadius()) * (tower.getRadius() + newTower.getRadius());
			if (distSq <= limitDistSq)
			{
				return true;
			}
		}
		return false;
	}
}
