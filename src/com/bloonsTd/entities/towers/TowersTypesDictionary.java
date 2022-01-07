package com.bloonsTd.entities.towers;

import com.bloonsTd.entities.EntitiesTypesDictionary;
import com.bloonsTd.entities.EntityType;

public class TowersTypesDictionary extends EntitiesTypesDictionary
{
	public static final int DART_MONKEY = 0;

	public TowersTypesDictionary()
	{
		super("Bloons-TD-GA/src/com/data/entities-types/tower-types.csv");
	}

	@Override
	public EntityType createNewEntity(String[] values)
	{
		return new TowerType(values);
	}
}
