package com.bloonsTd.entities;

public abstract class Entity
{
	private float xPos;
	private float yPos;

	private boolean isActive;

	// which balloon is it (will determine its stats)
	private int type;

	private int id; // each balloons have a unique number (used by the bullets so they will not hit
	// the balloon more than once)

	public Entity()
	{

	}

	public void init(int type, float initXPos, float initYPos)
	{
		this.setType(type);
		this.setxPos(initXPos);
		this.setyPos(initYPos);

		this.setActive(true);
	}

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
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

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

}
