package com.bloonsTd.towers;

import com.Global;

public class TowerType
{
	private int color;

	private float radius;

	public TowerType(int colorR, int colorG, int colorB, float radius)
	{
		this.color = Global.getPro().color(colorR, colorG, colorB);
		this.setRadius(radius);
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
}
