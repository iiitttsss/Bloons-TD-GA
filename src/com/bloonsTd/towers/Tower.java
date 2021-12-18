package com.bloonsTd.towers;

public class Tower
{
	// the size of the tower is determined by the tower type
	private float xPos;
	private float yPos;

	private int type;

	public Tower(float xPos, float yPos, int type)
	{
		this.setxPos(xPos);
		this.setyPos(yPos);
		this.setType(type);
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
