package com.bloonsTd.entities.balloons;

import com.Global;
import com.bloonsTd.entities.EntityType;

public class BalloonType extends EntityType
{
	// speed
	private float speed;
	// color
	private int color;

	private float radius;

	private float strength;

	public BalloonType(String[] values)
	{
		this.speed = Float.parseFloat(values[1]);
		this.color = Global.getPro().color(Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]));
		this.setRadius(Float.parseFloat(values[5]));
		this.setStrength(Float.parseFloat(values[6]));
	}

	public float getSpeed()
	{
		return speed;
	}

	public void setSpeed(float speed)
	{
		this.speed = speed;
	}

	public int getColor()
	{
		return color;
	}

	public void setColor(int color)
	{
		this.color = color;
	}

	public float getRadius()
	{
		return radius;
	}

	public void setRadius(float radius)
	{
		this.radius = radius;
	}

	public float getStrength()
	{
		return strength;
	}

	public void setStrength(float strength)
	{
		this.strength = strength;
	}
}
