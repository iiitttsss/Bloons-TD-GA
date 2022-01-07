package com.bloonsTd.entities.towers;

import com.Global;
import com.bloonsTd.entities.EntityType;

public class TowerType extends EntityType
{
	private int color;

	private float radius;

	public TowerType(String[] values)
	{
		this.color = Global.getPro().color(Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]));
		this.setRadius(Float.parseFloat(values[4]));
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
