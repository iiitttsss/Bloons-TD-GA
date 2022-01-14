package com.bloonsTd.rounds;

import com.bloonsTd.entities.balloons.BalloonsManager;

public class BalloonsSpawner
{

	private RoundsData roundsData;
	private int roundNumber;
	private float timeSinceLastSpawn;
	private int subRoundNumber;
	private int numberOfBalloonsSpawnInThisSubRound;

	public BalloonsSpawner()
	{
		this.setRoundsData(new RoundsData());
	}

	public void init()
	{
		this.setRoundNumber(0);
		this.setTimeSinceLastSpawn(0);
		this.setSubRoundNumber(0);
		this.setNumberOfBalloonsSpawnInThisSubRound(0);
	}

	/**
	 * spawning new balloons when needed
	 */
	public void spawnBalloons(float deltaTime, BalloonsManager balloons, int[] startPosition)
	{
		this.timeSinceLastSpawn -= deltaTime;
		// if need to spawn balloon
		if (this.getTimeSinceLastSpawn() <= 0)
		{
			this.setTimeSinceLastSpawn(1000);
			// if there are still rounds
			if (this.getRoundNumber() < this.getRoundsData().getRoundsData().size())
			{
				// if there are still subrounds
				if (this.getSubRoundNumber() < this.getRoundsData().getRoundsData().get(this.getRoundNumber())
						.getSubRounds().size())
				{
					// if there are still balloons in that sub round
					if (this.getNumberOfBalloonsSpawnInThisSubRound() < this.getRoundsData().getRoundsData()
							.get(this.getRoundNumber()).getSubRounds().get(this.getSubRoundNumber()).getAmount())
					{
						balloons.addBalloon(this.getRoundsData().getRoundsData().get(this.getRoundNumber())
								.getSubRounds().get(this.getSubRoundNumber()).getType(), startPosition[0], startPosition[1], 0, 0);
//						System.out.print(this.getRoundNumber() + " | ");
//						System.out.print(this.getSubRoundNumber() + " | ");
//						System.out.print(this.getNumberOfBalloonsSpawnInThisSubRound() + " | ");
//						System.out.println();
						this.numberOfBalloonsSpawnInThisSubRound++;

					}
					else
					{
						// when subround ends
						this.subRoundNumber++;
						this.setNumberOfBalloonsSpawnInThisSubRound(0);
					}
				}
				else
				{
					// when round ends
					this.roundNumber++;
					this.setSubRoundNumber(0);
					this.setNumberOfBalloonsSpawnInThisSubRound(0);
					this.setTimeSinceLastSpawn(10000);

				}
			}
			else
			{
				// when all rounds end
			}
		}
	}

	public int getRoundNumber()
	{
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber)
	{
		this.roundNumber = roundNumber;
	}

	public float getTimeSinceLastSpawn()
	{
		return timeSinceLastSpawn;
	}

	public void setTimeSinceLastSpawn(float timeSinceLastSpawn)
	{
		this.timeSinceLastSpawn = timeSinceLastSpawn;
	}

	public int getSubRoundNumber()
	{
		return subRoundNumber;
	}

	public void setSubRoundNumber(int subRoundNumber)
	{
		this.subRoundNumber = subRoundNumber;
	}

	public int getNumberOfBalloonsSpawnInThisSubRound()
	{
		return numberOfBalloonsSpawnInThisSubRound;
	}

	public void setNumberOfBalloonsSpawnInThisSubRound(int numberOfBalloonsSpawnInThisSubRound)
	{
		this.numberOfBalloonsSpawnInThisSubRound = numberOfBalloonsSpawnInThisSubRound;
	}

	public RoundsData getRoundsData()
	{
		return roundsData;
	}

	public void setRoundsData(RoundsData roundsData)
	{
		this.roundsData = roundsData;
	}

}
