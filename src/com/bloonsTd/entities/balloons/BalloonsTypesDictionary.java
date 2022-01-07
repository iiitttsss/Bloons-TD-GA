package com.bloonsTd.entities.balloons;

import com.bloonsTd.entities.EntitiesTypesDictionary;

public class BalloonsTypesDictionary extends EntitiesTypesDictionary
{
    public static final int ERROR = -1;
    public static final int RED_BALLOON = 0;
    public static final int BLUE_BALLOON = 1;
    public static final int GREEN_BALLOON = 2;
    public static final int YELLOW_BALLOON = 3;
    public static final int PINK_BALLOON = 4;
    public static final int BLACK_BALLOON = 5;
    public static final int WHITE_BALLOON = 6;
    public static final int LEAD_BALLOON = 7;
    public static final int ZEBRA_BALLOON = 8;
    public static final int RAINBOW_BALLOON = 9;
    public static final int CERAMIC_BALLOON = 10;
    public static final int MOAB_BALLOON = 11;
    public static final int BFB_BALLOON = 12;
    public static final int ZOMG_BALLOON = 13;

    public BalloonsTypesDictionary()
    {
        super("Bloons-TD-GA/src/com/data/entities-types/balloon-types.csv");
    }

    @Override
    public BalloonType createNewEntity(String[] values)
    {
        return new BalloonType(values);
    }
}
