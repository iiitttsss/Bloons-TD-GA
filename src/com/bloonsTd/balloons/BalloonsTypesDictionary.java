package com.bloonsTd.balloons;

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
		BalloonsTypesDictionary.typeDict.put(RED_BALLOON, new BalloonType(1, 240, 30, 40));
		BalloonsTypesDictionary.typeDict.put(BLUE_BALLOON, new BalloonType(1.4f, 40, 150, 220));
		BalloonsTypesDictionary.typeDict.put(GREEN_BALLOON, new BalloonType(1.8f, 110, 180, 0));

		BalloonsTypesDictionary.typeDict.put(YELLOW_BALLOON, new BalloonType(3.2f, 255, 220, 5));
		BalloonsTypesDictionary.typeDict.put(PINK_BALLOON, new BalloonType(3.5f, 255, 70, 100));
		BalloonsTypesDictionary.typeDict.put(BLACK_BALLOON, new BalloonType(1.8f, 22, 22, 22));
		BalloonsTypesDictionary.typeDict.put(WHITE_BALLOON, new BalloonType(2f, 190, 240, 255));

//		BalloonsTypesDictionary.typeDict.put(LEAD_BALLOON, new BalloonType(1f, 40, 150, 220));
//		BalloonsTypesDictionary.typeDict.put(ZEBRA_BALLOON, new BalloonType(1.8f, 40, 150, 220));
//		BalloonsTypesDictionary.typeDict.put(RAINBOW_BALLOON, new BalloonType(1.8f, 40, 150, 220));
//		BalloonsTypesDictionary.typeDict.put(CERAMIC_BALLOON, new BalloonType(2.5f, 40, 150, 220));

	}

}
