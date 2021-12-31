// keeping track on a specific round data
// 30-12-2021
package com.bloonsTd.rounds;

import java.util.ArrayList;

import com.bloonsTd.balloons.BalloonsTypesDictionary;

public class Round
{
	private ArrayList<SubRound> subRounds;

	/**
	 * taking a line from the rounds file and interperting it
	 * 
	 * @param roundString - a line from the rounds file
	 */
	public Round(String roundString)
	{
		this.setSubRounds(new ArrayList<SubRound>());
		// System.out.println(line);
		String[] balloonTypesArray = roundString.split(", ");
		// for every balloon type
		for (String balloonType : balloonTypesArray)
		{
			String[] spawnData = balloonType.split(" ");
			int balloonTypeIndex = nameToIndex(spawnData[1]);
			SubRound subRound = new SubRound(Integer.parseInt(spawnData[0]), balloonTypeIndex,
					balloonType.contains("Regrowth"), balloonType.contains("Camo"));
			this.getSubRounds().add(subRound);
		}
	}

	/**
	 * converting from the round file naming to the number used in the code
	 * 
	 * @param name - the name of the balloon type as appering in the rounds file
	 * @return - returning the balloon type as used by the code
	 */
	private int nameToIndex(String name)
	{
		switch (name)
		{
		case "Red":
			return BalloonsTypesDictionary.RED_BALLOON;
		case "Blue":
			return BalloonsTypesDictionary.BLUE_BALLOON;
		case "Green":
			return BalloonsTypesDictionary.GREEN_BALLOON;
		case "Yellow":
			return BalloonsTypesDictionary.YELLOW_BALLOON;
		case "Pink":
			return BalloonsTypesDictionary.PINK_BALLOON;
		case "Black":
			return BalloonsTypesDictionary.BLACK_BALLOON;
		case "White":
			return BalloonsTypesDictionary.WHITE_BALLOON;
		case "Lead":
			return BalloonsTypesDictionary.LEAD_BALLOON;
		case "Zebra":
			return BalloonsTypesDictionary.ZEBRA_BALLOON;
		case "Rainbow":
			return BalloonsTypesDictionary.RAINBOW_BALLOON;
		case "Ceramic":
			return BalloonsTypesDictionary.CERAMIC_BALLOON;
		case "M.O.A.B":
			return BalloonsTypesDictionary.MOAB_BALLOON;
		case "B.F.B":
			return BalloonsTypesDictionary.BFB_BALLOON;
		case "Z.O.M.G":
			return BalloonsTypesDictionary.ZOMG_BALLOON;
		default:
			return BalloonsTypesDictionary.ERROR;

		}
	}

	public ArrayList<SubRound> getSubRounds()
	{
		return subRounds;
	}

	public void setSubRounds(ArrayList<SubRound> subRounds)
	{
		this.subRounds = subRounds;
	}

}
