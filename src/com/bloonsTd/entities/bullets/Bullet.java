package com.bloonsTd.entities.bullets;

import com.bloonsTd.entities.Entity;
import com.bloonsTd.entities.balloons.Balloon;
import com.bloonsTd.entities.balloons.BalloonsManager;
import com.util.MathUtils;

import java.util.ArrayList;

public class Bullet extends Entity
{
    private float xVel;
    private float yVel;

    private int pierceRemained;
    private float lifeTimeRemained;

    private ArrayList<Integer> balloonsClearList;// list of balloons IDs that the bullet cannot hit

    public Bullet()
    {
        super();
        // 		this.setBalloonsClearList(new ArrayList<Integer>());
        this.setBalloonsClearList(new ArrayList<>());
    }

    /**
     * initializing the bullet
     *
     * @param xPos - start x position
     * @param yPos - start y position
     * @param xVel - start x velocity
     * @param yVel - start y velocity
     */
    public void init(int type, float xPos, float yPos, float xVel, float yVel, float lifeTime)
    {
        super.init(type, xPos, yPos);

        this.setxVel(xVel);
        this.setyVel(yVel);

        this.setPierceRemained(this.getPierce());
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

        if (this.getLifeTimeRemained() <= 0) {
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
        if (this.getPierceRemained() <= 0) {
            this.setActive(false);
        }
    }

    /**
     * checking if thr bullet is touching a balloon
     *
     * @param balloonsManager - an array-list of all the balloons
     */
    private void collisionWithBalloons(BalloonsManager balloonsManager)
    {
        for (Entity entity : balloonsManager.getActiveEntities()) {
            Balloon balloon = (Balloon) entity;

            if (!this.getBalloonsClearList().contains(balloon.getId())) {
                float disSq = MathUtils.distanceSquare(this.getxPos(), this.getyPos(), balloon.getxPos(),
                        balloon.getyPos());
                float maxDisSq = (this.getRadius() + balloon.getRadius()) * (this.getRadius() + balloon.getRadius());

                if (disSq < maxDisSq) {
                    this.hit(balloon);
                    balloon.hit(this, balloonsManager, this.getBalloonsClearList());
                }
            }

            // the bullet can become inactive when hit a balloon
            if (!this.isActive()) {
                break;
            }
        }
    }

    /**
     * handling movement
     */
    private void move()
    {
    	float speed = this.getSpeed();
        this.setxPos(this.getxPos() + this.getxVel() * speed);
        this.setyPos(this.getyPos() + this.getyVel() * speed);
    }

    public void decrementPierceRemained()
    {
        this.pierceRemained--;
    }

    /**
     * @return - the radius of the bullet based on it type
     */
    public float getRadius()
    {
        return ((BulletType) BulletsManager.getBulletsTypesDictionaryReference().getTypeDict().get(this.getType())).getRadius();

    }

    public float getSpeed()
    {
        return ((BulletType) BulletsManager.getBulletsTypesDictionaryReference().getTypeDict().get(this.getType())).getSpeed();
    }

    public int getColor()
	{
        return ((BulletType) BulletsManager.getBulletsTypesDictionaryReference().getTypeDict().get(this.getType())).getColor();
	}

    /**
     *
     * @return - the pierce value the bullet will start with
     */
	public int getPierce()
    {
        return ((BulletType) BulletsManager.getBulletsTypesDictionaryReference().getTypeDict().get(this.getType())).getPierce();

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

    public float getLifeTimeRemained()
    {
        return lifeTimeRemained;
    }

    public void setLifeTimeRemained(float lifeTimeRemained)
    {
        this.lifeTimeRemained = lifeTimeRemained;
    }
}
