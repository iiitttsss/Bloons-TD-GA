package com.bloonsTd.balloons;

import com.Global;

public class BalloonType
{
	// speed
	private float speed;
	// color
	private int color;

	public BalloonType(float speed, int colorR, int colorG, int colorB)
	{
		this.speed = speed;
		this.color = Global.getPro().color(colorR, colorG, colorB);
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
}
