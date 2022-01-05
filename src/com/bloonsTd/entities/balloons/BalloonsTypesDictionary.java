package com.bloonsTd.entities.balloons;

import java.util.HashMap;

public class BalloonsTypesDictionary
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
    private HashMap<Integer, BalloonType> typeDict = new HashMap<Integer, BalloonType>();
    public BalloonsTypesDictionary()
    {
        //																		spd    R    G    B    rad   str
        this.getTypeDict().put(RED_BALLOON, new BalloonType(1.00f, 240, 030, 040, 025, 1));
        this.getTypeDict().put(BLUE_BALLOON, new BalloonType(1.40f, 040, 150, 220, 030, 2));
        this.getTypeDict().put(GREEN_BALLOON, new BalloonType(1.80f, 110, 180, 000, 033, 3));
        this.getTypeDict().put(YELLOW_BALLOON, new BalloonType(3.20f, 255, 220, 005, 036, 4));
        this.getTypeDict().put(PINK_BALLOON, new BalloonType(3.50f, 255, 070, 100, 040, 5));
        this.getTypeDict().put(BLACK_BALLOON, new BalloonType(1.80f, 022, 022, 022, 021, 11));
        this.getTypeDict().put(WHITE_BALLOON, new BalloonType(2.00f, 190, 240, 255, 021, 11));
        this.getTypeDict().put(LEAD_BALLOON, new BalloonType(1.00f, 040, 150, 220, 033, 23));
        this.getTypeDict().put(ZEBRA_BALLOON, new BalloonType(1.80f, 255, 255, 255, 033, 23));
        this.getTypeDict().put(RAINBOW_BALLOON, new BalloonType(1.80f, 255, 255, 255, 040, 47));
        this.getTypeDict().put(CERAMIC_BALLOON, new BalloonType(2.50f, 255, 255, 255, 040, 104));
        this.getTypeDict().put(MOAB_BALLOON, new BalloonType(1.00f, 255, 255, 255, 115, 616));
        this.getTypeDict().put(BFB_BALLOON, new BalloonType(0.25f, 255, 255, 255, 180, 3164));
        this.getTypeDict().put(ZOMG_BALLOON, new BalloonType(0.18f, 255, 255, 255, 215, 16656));

    }

    public HashMap<Integer, BalloonType> getTypeDict()
    {
        return typeDict;
    }

    public void setTypeDict(HashMap<Integer, BalloonType> typeDict)
    {
        this.typeDict = typeDict;
    }

}
