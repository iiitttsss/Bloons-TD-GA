package com.bloonsTd.balloons;

import com.bloonsTd.bullets.Bullet;

public class Balloon
{
	private boolean isActive; // true if the balloon need to be updated and rendered
	private int id; // each ballons have a unique number (used by the bullets so they will not hit
					// the balloon more than once)

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

	public void init(float initXPos, float initYPos, int type)
	{
		this.setActive(true);

		this.setxPos(initXPos);
		this.setyPos(initYPos);
		this.setType(type);
	}

	public float getRadius()
	{
		return 10;
	}

	/**
	 * called when the balloon is hit by the bullet
	 * 
	 * @param bullet - the bullet the balloon was hit by
	 */
	public void hit(Bullet bullet)
	{
		// TODO Auto-generated method stub
		this.setActive(false);
	}

	public float calulateStrength()
	{
		// TODO
		return 1;
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
