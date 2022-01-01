package com.bloonsTd.entities.towers;

import java.util.HashMap;

public class TowersTypesDictionary
{

	public static HashMap<Integer, TowerType> typeDict = new HashMap<Integer, TowerType>();

	public static final int DART_MONKEY = 1;

	public static void initTypeDict()
	{
		TowersTypesDictionary.typeDict.put(DART_MONKEY, new TowerType(240, 30, 40, 40));

	}
}
