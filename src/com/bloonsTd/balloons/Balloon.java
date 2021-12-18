package com.bloonsTd.balloons;


public class Balloon
{
	private float xPos;
	private float yPos;

	// which balloon is it (will determine its stats)
	private int type;

	// to make the tracking the position of the balloon easier,
	// the balloon will know on what segement it is on
	// and where on that segment
	private int segmentNumber;
	private float percentOfSegment;

	public Balloon(float initXPos, float initYPos, int type)
	{
		this.setxPos(initXPos);
		this.setyPos(initYPos);
		this.setType(type);
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
}
