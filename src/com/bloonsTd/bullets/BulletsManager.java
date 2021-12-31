// handeling all the bullets - creating, disabling, enabling, updating
package com.bloonsTd.bullets;

import java.util.ArrayList;

import com.bloonsTd.balloons.BalloonsManager;

public class BulletsManager
{
	private ArrayList<Bullet> bullets;

	public BulletsManager()
	{
		this.setBullets(new ArrayList<Bullet>());
	}

	public void update(float deltaTime, BalloonsManager allBalloons)
	{
		for (Bullet b : this.getBullets())
		{
			if (b.isActive())
			{
				b.update(deltaTime, allBalloons);
			}
		}
	}

	/**
	 * adding a bullet to the list if needed, asigning an inactive bullet if there
	 * is one availible
	 * 
	 * @param xPos
	 * @param yPos
	 * @param xVel
	 * @param yVel
	 */
	public void addBullet(float xPos, float yPos, float xVel, float yVel, float lifeTime)
	{
		Bullet currentBullet = null;
		boolean foundBullet = false;

		// finding unused bullet
		for (Bullet b : this.getBullets())
		{
			if (!b.isActive())
			{
				currentBullet = b;
				foundBullet = true;
				break;
			}
		}

		// if could not find unused bullet, create a new one
		if (!foundBullet)
		{
			currentBullet = new Bullet();
			this.getBullets().add(currentBullet);
		}

		currentBullet.init(xPos, yPos, xVel, yVel, lifeTime);
	}

	public ArrayList<Bullet> getBullets()
	{
		return bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets)
	{
		this.bullets = bullets;
	}
}