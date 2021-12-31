package com.bloonsTd.towers;

import java.util.ArrayList;

import com.bloonsTd.path.Path;

public class TowerPlacer
{
	public static void placeTower(Path path, ArrayList<Tower> towers, int x, int y, int towerType)
	{
		// TODO - check if intersecting other towers
		// check if the place is good
		// (check if intersecting the path / other towers)
		// this.getTowers().add(new Tower(x, y, towerType));

		if (path.getDistanceFromClosestPath()[y][x] >= TowersTypesDictionary.typeDict.get(towerType).getRadius()
				+ path.getPathWidth())
		{
			towers.add(new Tower(x, y, towerType, path.getPathPoints()));
		}
	}
}
