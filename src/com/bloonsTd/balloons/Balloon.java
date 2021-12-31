package com.bloonsTd.balloons;

import com.bloonsTd.bullets.Bullet;

public class Balloon
{
	private boolean isActive; // true if the balloon need to be updated and rendered
	private int id; // each ballons have a unique number (used by the bullets so they will not hit
					// the balloon more than once)
	public static final float BALLOONS_SPEED_MULTIPLIER = 0.025f;

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
	public void init(int type, float initXPos, float initYPos,  int segmentNumber, float percentOfSegment)
	{
		this.setActive(true);
		this.setType(type);

		this.setxPos(initXPos);
		this.setyPos(initYPos);
		this.setSegmentNumber(segmentNumber);
		this.setPercentOfSegment(percentOfSegment);
	}

	/**
	 * 
	 * @return - return the radius based on the balloon type
	 */
	public float getRadius()
	{
		// TODO - implament this method (17 is only temprary number)
		return 17;
	}

	/**
	 * called when the balloon is hit by the bullet
	 * 
	 * @param bullet - the bullet the balloon was hit by
	 */
	public void hit(Bullet bullet, BalloonsManager balloonsManager)
	{
		// TODO Auto-generated method stub
		switch(this.getType())
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
				balloonsManager.addBalloon(type, xPos, yPos, segmentNumber, percentOfSegment);
				break;
		}
	}

	/**
	 * 
	 * @return - how strong this balloon is
	 */
	public float calulateStrength()
	{
		return this.getType();
	}

	public float getSpeed()
	{
		float speed = BalloonsTypesDictionary.typeDict.get(this.getType()).getSpeed();
		return speed;
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
