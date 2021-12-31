package com.bloonsTd.balloons;

import java.util.ArrayList;

public class BalloonsManager
{
	private ArrayList<Balloon> balloons;
	
	public BalloonsManager()
	{
		this.setBalloons(new ArrayList<Balloon>());
	}

	public void update(float deltaTime, float[][] allSegmentData)
	{
		this.moveBalloons(deltaTime, allSegmentData);
	}

	/**
	 * 
	 * @param balloon - the balloon
	 * @return - return true if the balloons did not finish the course
	 */
	private boolean isBalloonOnPath(Balloon balloon, float[][] allSegmentData)
	{
		return balloon.getSegmentNumber() < allSegmentData.length;
	}

	/**
	 * handeling the movement of all the balloons
	 * 
	 * @param allSegmentData
	 */
	private void moveBalloons(float deltaTime, float[][] allSegmentData)
	{
		for (Balloon balloon : this.getBalloons())
		{
			if (balloon.isActive())
			{
				if (!this.isBalloonOnPath(balloon, allSegmentData))
				{
					continue;
				}
				int segmentNumber = balloon.getSegmentNumber();

				float[] segmentData = allSegmentData[segmentNumber];
				float percentIncrease = deltaTime * Balloon.BALLOONS_SPEED_MULTIPLIER * balloon.getSpeed()
						/ segmentData[0];

				balloon.setPercentOfSegment(balloon.getPercentOfSegment() + percentIncrease);
				balloon.setxPos(balloon.getxPos() + percentIncrease * segmentData[1]);
				balloon.setyPos(balloon.getyPos() + percentIncrease * segmentData[2]);

				// move to the next segment
				if (balloon.getPercentOfSegment() >= 1f)
				{
					balloon.setSegmentNumber(balloon.getSegmentNumber() + 1);

					// if the segment exist
					if (this.isBalloonOnPath(balloon, allSegmentData))
					{
						float[] newSegmentData = allSegmentData[balloon.getSegmentNumber()];

						float startPercent = (balloon.getPercentOfSegment() - 1f) * segmentData[0] / newSegmentData[0];
						balloon.setPercentOfSegment(startPercent);
						balloon.setxPos(balloon.getxPos() + startPercent * newSegmentData[1]);
						balloon.setyPos(balloon.getyPos() + startPercent * newSegmentData[2]);
					}
				}
			}
		}
	}

	/**
	 * asigning an inactive balloon if there is one availble, if there are none
	 * avliable, creating a new one
	 * 
	 * @param initXPos
	 * @param initYPos
	 * @param type
	 */
	public void addBalloon(int type, float initXPos, float initYPos, int segmentNumber, float percentOfSegment)
	{
		Balloon currentBalloon = null;
		boolean foundBalloon = false;

		// finding unused bullet
		for (Balloon b : this.getBalloons())
		{
			if (!b.isActive())
			{
				currentBalloon = b;
				foundBalloon = true;
				break;
			}
		}

		// if could not find unused bullet, create a new one
		if (!foundBalloon)
		{
			currentBalloon = new Balloon();
			currentBalloon.setId(this.getBalloons().size());
			this.getBalloons().add(currentBalloon);
		}

		currentBalloon.init(type, initXPos, initYPos, segmentNumber, percentOfSegment);
	}

	public ArrayList<Balloon> getBalloons()
	{
		return balloons;
	}

	public void setBalloons(ArrayList<Balloon> balloons)
	{
		this.balloons = balloons;
	}


}