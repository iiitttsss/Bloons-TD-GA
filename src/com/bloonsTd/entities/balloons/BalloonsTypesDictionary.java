package com.bloonsTd.entities.balloons;

import java.util.HashMap;

public class BalloonsTypesDictionary
{

	public static HashMap<Integer, BalloonType> typeDict = new HashMap<Integer, BalloonType>();

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

	public static void initTypeDict()
	{
		//																		spd    R    G    B    rad   str
		BalloonsTypesDictionary.typeDict.put(RED_BALLOON, 		new BalloonType(1.00f, 240, 030, 040, 025, 1));
		BalloonsTypesDictionary.typeDict.put(BLUE_BALLOON, 		new BalloonType(1.40f, 040, 150, 220, 030, 2));
		BalloonsTypesDictionary.typeDict.put(GREEN_BALLOON, 	new BalloonType(1.80f, 110, 180, 000, 033, 3));
		BalloonsTypesDictionary.typeDict.put(YELLOW_BALLOON, 	new BalloonType(3.20f, 255, 220, 005, 036, 4));
		BalloonsTypesDictionary.typeDict.put(PINK_BALLOON, 		new BalloonType(3.50f, 255, 070, 100, 040, 5));
		BalloonsTypesDictionary.typeDict.put(BLACK_BALLOON, 	new BalloonType(1.80f, 022, 022, 022, 021, 11));
		BalloonsTypesDictionary.typeDict.put(WHITE_BALLOON, 	new BalloonType(2.00f, 190, 240, 255, 021, 11));
		BalloonsTypesDictionary.typeDict.put(LEAD_BALLOON, 		new BalloonType(1.00f, 040, 150, 220, 033, 23));
		BalloonsTypesDictionary.typeDict.put(ZEBRA_BALLOON, 	new BalloonType(1.80f, 255, 255, 255, 033, 23));
		BalloonsTypesDictionary.typeDict.put(RAINBOW_BALLOON, 	new BalloonType(1.80f, 255, 255, 255, 040, 47));
		BalloonsTypesDictionary.typeDict.put(CERAMIC_BALLOON, 	new BalloonType(2.50f, 255, 255, 255, 040, 104));
		BalloonsTypesDictionary.typeDict.put(MOAB_BALLOON, 		new BalloonType(1.00f, 255, 255, 255, 115, 616));
		BalloonsTypesDictionary.typeDict.put(BFB_BALLOON, 		new BalloonType(0.25f, 255, 255, 255, 180, 3164));
		BalloonsTypesDictionary.typeDict.put(ZOMG_BALLOON, 		new BalloonType(0.18f, 255, 255, 255, 215, 16656));

	}

}
