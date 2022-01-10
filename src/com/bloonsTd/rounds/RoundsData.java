// keeping all the data about what balloons need to be spawn each round
// 30-12-2021
package com.bloonsTd.rounds;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class RoundsData
{
	private ArrayList<Round> roundsData;

	public RoundsData()
	{
		try
		{
			// Bloons-TD-GA/src/com/data/roundsAdv.txt
			roundsData = loadRoundsData("Bloons-TD-GA/src/com/data/rounds/rounds.txt");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return an array of all the balloons that spawn in each round
	 * @throws Exception
	 */
	private ArrayList<Round> loadRoundsData(String roundsFileName) throws Exception
	{
		// 85 rounds
		// 14 balloon types
		ArrayList<Round> rounds = new ArrayList<Round>();
		File file = new File(roundsFileName);
	 
	        // Note:  Double backquote is to avoid compiler
	        // interpret words
	        // like \test as \t (ie. as a escape sequence)
	 
	        // Creating an object of BufferedReader class
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line;

			while ((line = br.readLine()) != null)
			{
				Round round = new Round(line);
				rounds.add(round);
			}
		br.close();

		return rounds;
	}



	public ArrayList<Round> getRoundsData()
	{
		return roundsData;
	}

	public void setRoundsData(ArrayList<Round> roundsData)
	{
		this.roundsData = roundsData;
	}
}
