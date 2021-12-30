package com.bloonsTd.bullets;

import java.util.ArrayList;

import com.bloonsTd.balloons.Balloon;
import com.bloonsTd.balloons.BalloonsManager;
import com.util.MathUtils;

public class Bullet
{
	private boolean isActive;

	private float xPos;
	private float yPos;
	private float xVel;
	private float yVel;

	private int pierceRemained;
	private float lifeTimeRemained;

	private ArrayList<Integer> balloonsClearList;// list of ballons IDs that the bullet cannot hit

	public Bullet()
	{
		this.setBalloonsClearList(new ArrayList<Integer>());
	}

	/**
	 * initilizing the bullet
	 * 
	 * @param xPos
	 * @param yPos
	 * @param xVel
	 * @param yVel
	 */
	public void init(float xPos, float yPos, float xVel, float yVel, float lifeTime)
	{
		this.setActive(true);

		this.setxPos(xPos);
		this.setyPos(yPos);
		this.setxVel(xVel);
		this.setyVel(yVel);

		this.setPierceRemained(1);
		this.setLifeTimeRemained(lifeTime);

		this.getBalloonsClearList().clear();
	}


	public void update(float deltaTime, BalloonsManager allBalloons)
	{
		this.move();
		this.collisionWithBalloons(allBalloons);
		this.despawn(deltaTime);
	}

	private void despawn(float deltaTime)
	{
		this.lifeTimeRemained -= deltaTime;

		if (this.getLifeTimeRemained() <= 0)
		{
			this.setActive(false);
		}
	}

	/**
	 * called when hitting a balloon
	 * 
	 * @param balloon - the balloon that got hit
	 */
	private void hit(Balloon balloon)
	{
		this.getBalloonsClearList().add(balloon.getId());

		this.decrementPierceRemained();
		if (this.getPierceRemained() <= 0)
		{
			this.setActive(false);
		}
	}

	/**
	 * checking if thr bullet is touching a balloon
	 * 
	 * @param allBalloons - an array-list of all the balloons
	 */
	private void collisionWithBalloons(BalloonsManager balloonsManager)
	{
		for (Balloon balloon : balloonsManager.getBalloons())
		{
			if (balloon.isActive())
			{
				if (!this.getBalloonsClearList().contains(balloon.getId()))
				{
					float disSq = MathUtils.distanceSquare(this.getxPos(), this.getyPos(), balloon.getxPos(),
							balloon.getyPos());
					float maxDisSq = (this.getRadius() + balloon.getRadius())
							* (this.getRadius() + balloon.getRadius());

					if (disSq < maxDisSq)
					{
						balloon.hit(this, balloonsManager);
						this.hit(balloon);
						// System.out.println("hit");
					}
				}
			}

			// the bullet can become inactive when hit a balloon
			if (!this.isActive())
			{
				break;
			}
		}
	}

	/**
	 * 
	 * @return - the radius of the bullet based on it type
	 */
	public float getRadius()
	{
		// return 5 until implemnted properly
		// should return the radius from a dictionary based on the bullet type
		return 5;
	}

	/**
	 * handling movment
	 */
	private void move()
	{
		this.xPos += this.xVel;
		this.yPos += this.yVel;
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

	public float getxVel()
	{
		return xVel;
	}

	public void setxVel(float xVel)
	{
		this.xVel = xVel;
	}

	public float getyVel()
	{
		return yVel;
	}

	public void setyVel(float yVel)
	{
		this.yVel = yVel;
	}

	public ArrayList<Integer> getBalloonsClearList()
	{
		return balloonsClearList;
	}

	public void setBalloonsClearList(ArrayList<Integer> balloonsClearList)
	{
		this.balloonsClearList = balloonsClearList;
	}

	public int getPierceRemained()
	{
		return pierceRemained;
	}

	public void setPierceRemained(int pierceRemained)
	{
		this.pierceRemained = pierceRemained;
	}

	public void decrementPierceRemained()
	{
		this.pierceRemained--;
	}

	public float getLifeTimeRemained()
	{
		return lifeTimeRemained;
	}

	public void setLifeTimeRemained(float lifeTimeRemained)
	{
		this.lifeTimeRemained = lifeTimeRemained;
	}
}
