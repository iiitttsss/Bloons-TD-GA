package com.bloonsTd.balloons;

import java.util.ArrayList;

import com.bloonsTd.bullets.Bullet;

public class Balloon
{
	private boolean isActive; // true if the balloon need to be updated and rendered
	private int id; // each ballons have a unique number (used by the bullets so they will not hit
					// the balloon more than once)
	public static final float BALLOONS_SPEED_MULTIPLIER = 0.05f;
	public static final float BALLOONS_RADIUS_MULTIPLIER = 0.7f;

	private float xPos;
	private float yPos;

	// which balloon is it (will determine its stats)
	private int type;

	// to make the tracking the position of the balloon easier,
	// the balloon will know on what segement it is on
	// and where on that segment
	private int segmentNumber;
	private float percentOfSegment;

	public Balloon()
	{
	}

	/**
	 * because balloons do not get deleted but didabled, there is no need to
	 * conctract them each time, so the method initlizing them instead
	 * 
	 * @param type
	 * @param initXPos
	 * @param initYPos
	 * @param segmentNumber
	 * @param percentOfSegment
	 */
	public void init(int type, float initXPos, float initYPos, int segmentNumber, float percentOfSegment)
	{
		this.setActive(true);
		this.setType(type);

		this.setxPos(initXPos);
		this.setyPos(initYPos);
		this.setSegmentNumber(segmentNumber);
		this.setPercentOfSegment(percentOfSegment);
	}

	/**
	 * called when the balloon is hit by the bullet
	 * 
	 * @param bullet - the bullet the balloon was hit by
	 */
	public void hit(Bullet bullet, BalloonsManager balloonsManager, ArrayList<Integer> bulletClearList)
	{
		switch (this.getType())
		{
		case BalloonsTypesDictionary.RED_BALLOON:
			this.setActive(false);
			break;
		case BalloonsTypesDictionary.BLUE_BALLOON:
		case BalloonsTypesDictionary.GREEN_BALLOON:
		case BalloonsTypesDictionary.YELLOW_BALLOON:
		case BalloonsTypesDictionary.PINK_BALLOON:
			this.type--;
			break;
		case BalloonsTypesDictionary.BLACK_BALLOON:
		case BalloonsTypesDictionary.WHITE_BALLOON:
			this.setType(BalloonsTypesDictionary.PINK_BALLOON);
			bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.PINK_BALLOON, xPos, yPos,
					segmentNumber, percentOfSegment));
			break;
		case BalloonsTypesDictionary.LEAD_BALLOON:
			this.setType(BalloonsTypesDictionary.BLACK_BALLOON);
			bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.BLACK_BALLOON, xPos, yPos,
					segmentNumber, percentOfSegment));
			break;
		case BalloonsTypesDictionary.ZEBRA_BALLOON:
			this.setType(BalloonsTypesDictionary.BLACK_BALLOON);
			bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.WHITE_BALLOON, xPos, yPos,
					segmentNumber, percentOfSegment));
			break;
		case BalloonsTypesDictionary.RAINBOW_BALLOON:
			this.setType(BalloonsTypesDictionary.ZEBRA_BALLOON);
			bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.ZEBRA_BALLOON, xPos, yPos,
					segmentNumber, percentOfSegment));
			break;
		case BalloonsTypesDictionary.CERAMIC_BALLOON:
			this.setType(BalloonsTypesDictionary.RAINBOW_BALLOON);
			bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.RAINBOW_BALLOON, xPos, yPos,
					segmentNumber, percentOfSegment));
			break;
		case BalloonsTypesDictionary.MOAB_BALLOON:
			this.setType(BalloonsTypesDictionary.CERAMIC_BALLOON);
			for (int i = 0; i < 3; i++)
			{
				bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.CERAMIC_BALLOON, xPos, yPos,
						segmentNumber, percentOfSegment));
			}
			break;
		case BalloonsTypesDictionary.BFB_BALLOON:
			this.setType(BalloonsTypesDictionary.MOAB_BALLOON);
			for (int i = 0; i < 3; i++)
			{
				bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.MOAB_BALLOON, xPos, yPos,
						segmentNumber, percentOfSegment));
			}
			break;
		case BalloonsTypesDictionary.ZOMG_BALLOON:
			this.setType(BalloonsTypesDictionary.BFB_BALLOON);
			for (int i = 0; i < 3; i++)
			{
				bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.BFB_BALLOON, xPos, yPos,
						segmentNumber, percentOfSegment));
			}
			break;

		}
	}

	/**
	 * 
	 * @return - how strong this balloon is
	 */
	public float getStrength()
	{
		return BalloonsTypesDictionary.typeDict.get(this.getType()).getStrength();
	}

	public float getSpeed()
	{
		return BALLOONS_SPEED_MULTIPLIER * BalloonsTypesDictionary.typeDict.get(this.getType()).getSpeed();
	}

	/**
	 * 
	 * @return - return the radius based on the balloon type
	 */
	public float getRadius()
	{
		return BALLOONS_RADIUS_MULTIPLIER * BalloonsTypesDictionary.typeDict.get(this.getType()).getRadius();
	}

	public int getSegmentNumber()
	{
		return segmentNumber;
	}

	public void setSegmentNumber(int segmentNumber)
	{
		this.segmentNumber = segmentNumber;
	}

	public float getPercentOfSegment()
	{
		return percentOfSegment;
	}

	public void setPercentOfSegment(float percentOfSegment)
	{
		this.percentOfSegment = percentOfSegment;
	}

	public float getxPos()
	{
		return xPos;
	}

	public void setxPos(float xPos)
	{
		this.xPos = xPos;
	}

	public float getyPos()
	{
		return yPos;
	}

	public void setyPos(float yPos)
	{
		this.yPos = yPos;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
