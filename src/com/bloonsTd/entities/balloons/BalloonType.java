package com.bloonsTd.entities.balloons;

import com.Global;

public class BalloonType
{
	// speed
	private float speed;
	// color
	private int color;

	private float radius;

	private float strength;

	public BalloonType(float speed, int colorR, int colorG, int colorB, float radius, float strength)
	{
		this.speed = speed;
		this.color = Global.getPro().color(colorR, colorG, colorB);
		this.setRadius(radius);
		this.setStrength(strength);
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
