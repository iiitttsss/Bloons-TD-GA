// handeling all the bullets - creating, disabling, enabling, updating
package com.bloonsTd.entities.bullets;

import com.bloonsTd.entities.EntitiesManager;
import com.bloonsTd.entities.Entity;
import com.bloonsTd.entities.balloons.BalloonsManager;
import com.bloonsTd.entities.balloons.BalloonsTypesDictionary;

public class BulletsManager extends EntitiesManager
{
	public static BulletsTypesDictionary getBulletsTypesDictionaryReference()
	{
		return bulletsTypesDictionaryReference;
	}

	public static void setBulletsTypesDictionaryReference(BulletsTypesDictionary bulletsTypesDictionaryReference)
	{
		BulletsManager.bulletsTypesDictionaryReference = bulletsTypesDictionaryReference;
	}

	private static BulletsTypesDictionary bulletsTypesDictionaryReference;

	public BulletsManager()
	{
		super();
		this.setTypeDict(new BulletsTypesDictionary());
		BulletsManager.setBulletsTypesDictionaryReference((BulletsTypesDictionary) this.getTypeDict());
	}

	@Override
	public Bullet createNewEntity()
	{
		return new Bullet();
	}

	public void update(float deltaTime, BalloonsManager allBalloons)
	{
		for (Entity entity : this.getActiveEntities())
		{
			Bullet bullet = (Bullet) entity;
			bullet.update(deltaTime, allBalloons);
		}
	}

	/**
	 * 
	 * @param type
	 * @param initXPos
	 * @param initYPos
	 * @param xVel
	 * @param yVel
	 * @param lifeTime
	 * @return - the bullet created
	 */
	public Bullet addBullet(int type, float initXPos, float initYPos, float xVel, float yVel, float lifeTime)
	{
		Bullet bullet = (Bullet) super.addEntity();
		bullet.init(type, initXPos, initYPos, xVel, yVel, lifeTime);
		return bullet;
	}
}
