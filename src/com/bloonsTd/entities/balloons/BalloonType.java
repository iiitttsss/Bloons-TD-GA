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

	private static int NAME_INDEX = 1;
	private static int SPEED_INDEX = 1;
	private static int COLOR_R_INDEX = 2;
	private static int COLOR_G_INDEX = 3;
	private static int COLOR_B_INDEX = 4;
	private static int RADIUS_INDEX = 5;
	private static int STRENGTH_INDEX = 6;


	public BalloonType(String[] values)
	{
		this.speed = Float.parseFloat(values[SPEED_INDEX]);
		this.color = Global.getPro().color(Integer.parseInt(values[COLOR_R_INDEX]), Integer.parseInt(values[COLOR_G_INDEX]), Integer.parseInt(values[COLOR_B_INDEX]));
		this.setRadius(Integer.parseInt(values[RADIUS_INDEX]));
		this.setStrength(Integer.parseInt(values[STRENGTH_INDEX]));
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
