package com.bloonsTd.entities.bullets;

import com.bloonsTd.entities.EntitiesTypesDictionary;

public class BulletsTypesDictionary extends EntitiesTypesDictionary
{
    public static final int DEFAULT = 0;

    public BulletsTypesDictionary()
    {
        super("Bloons-TD-GA/src/com/data/entities-types/bullet-types.csv");
    }

    @Override
    public BulletType createNewEntity(String[] values)
    {
        return new BulletType(values);
    }
}
