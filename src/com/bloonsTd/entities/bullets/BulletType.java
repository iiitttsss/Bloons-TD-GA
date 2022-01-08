package com.bloonsTd.entities.bullets;

import com.Global;
import com.bloonsTd.entities.EntityType;

public class BulletType extends EntityType
{
    private float speed;
    private int color;
    private float radius;

    public int getPierce()
    {
        return pierce;
    }

    public void setPierce(int pierce)
    {
        this.pierce = pierce;
    }

    private int pierce;

    public BulletType(String[] values)
    {
        this.speed = Float.parseFloat(values[1]);
        this.color = Global.getPro().color(Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]));
        this.setRadius(Float.parseFloat(values[5]));
        this.setPierce(Integer.parseInt(values[6]));

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
}
