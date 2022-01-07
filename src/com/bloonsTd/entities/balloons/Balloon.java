package com.bloonsTd.entities.balloons;

import com.bloonsTd.entities.Entity;
import com.bloonsTd.entities.bullets.Bullet;

import java.util.ArrayList;

public class Balloon extends Entity
{

    public static final float BALLOONS_SPEED_MULTIPLIER = 0.05f;
    public static final float BALLOONS_RADIUS_MULTIPLIER = 0.7f;


    // to make the tracking the position of the balloon easier,
    // the balloon will know on what segment it is on
    // and where on that segment
    private int segmentNumber;
    private float percentOfSegment;

    public Balloon()
    {
    }

    /**
     * because balloons do not get deleted but disabled, there is no need to
     * construct them each time, so the method initializing them instead
     *
     * @param type             - the type of the new balloon
     * @param initXPos         - the start x position of the new balloon
     * @param initYPos         - the start y position of the new balloon
     * @param segmentNumber    - the start segment number of the new balloon
     * @param percentOfSegment - the start percent of segment of the new balloon
     */
    public void init(int type, float initXPos, float initYPos, int segmentNumber, float percentOfSegment)
    {
        super.init(type, initXPos, initYPos);

        this.setSegmentNumber(segmentNumber);
        this.setPercentOfSegment(percentOfSegment);
    }

    /**
     * called when the balloon is hit by the bullet
     *
     * @param bullet - the bullet the balloon was hit by
     */
    public void hit(Bullet bullet, BalloonsManager balloonsManager, ArrayList<Integer> bulletClearList)
    {
        switch (this.getType()) {
            case BalloonsTypesDictionary.RED_BALLOON:
                this.setActive(false);
                break;
            case BalloonsTypesDictionary.BLUE_BALLOON:
            case BalloonsTypesDictionary.GREEN_BALLOON:
            case BalloonsTypesDictionary.YELLOW_BALLOON:
            case BalloonsTypesDictionary.PINK_BALLOON:
                this.setType(this.getType() - 1);
                break;
            case BalloonsTypesDictionary.BLACK_BALLOON:
            case BalloonsTypesDictionary.WHITE_BALLOON:
                this.setType(BalloonsTypesDictionary.PINK_BALLOON);
                bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.PINK_BALLOON, this.getxPos(),
                        this.getyPos(), segmentNumber, percentOfSegment).getId());
                break;
            case BalloonsTypesDictionary.LEAD_BALLOON:
                this.setType(BalloonsTypesDictionary.BLACK_BALLOON);
                bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.BLACK_BALLOON, this.getxPos(),
                        this.getyPos(), segmentNumber, percentOfSegment).getId());
                break;
            case BalloonsTypesDictionary.ZEBRA_BALLOON:
                this.setType(BalloonsTypesDictionary.BLACK_BALLOON);
                bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.WHITE_BALLOON, this.getxPos(),
                        this.getyPos(), segmentNumber, percentOfSegment).getId());
                break;
            case BalloonsTypesDictionary.RAINBOW_BALLOON:
                this.setType(BalloonsTypesDictionary.ZEBRA_BALLOON);
                bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.ZEBRA_BALLOON, this.getxPos(),
                        this.getyPos(), segmentNumber, percentOfSegment).getId());
                break;
            case BalloonsTypesDictionary.CERAMIC_BALLOON:
                this.setType(BalloonsTypesDictionary.RAINBOW_BALLOON);
                bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.RAINBOW_BALLOON, this.getxPos(),
                        this.getyPos(), segmentNumber, percentOfSegment).getId());
                break;
            case BalloonsTypesDictionary.MOAB_BALLOON:
                this.setType(BalloonsTypesDictionary.CERAMIC_BALLOON);
                for (int i = 0; i < 3; i++) {
                    bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.CERAMIC_BALLOON, this.getxPos(),
                            this.getyPos(), segmentNumber, percentOfSegment).getId());
                }
                break;
            case BalloonsTypesDictionary.BFB_BALLOON:
                this.setType(BalloonsTypesDictionary.MOAB_BALLOON);
                for (int i = 0; i < 3; i++) {
                    bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.MOAB_BALLOON, this.getxPos(),
                            this.getyPos(), segmentNumber, percentOfSegment).getId());
                }
                break;
            case BalloonsTypesDictionary.ZOMG_BALLOON:
                this.setType(BalloonsTypesDictionary.BFB_BALLOON);
                for (int i = 0; i < 3; i++) {
                    bulletClearList.add(balloonsManager.addBalloon(BalloonsTypesDictionary.BFB_BALLOON, this.getxPos(),
                            this.getyPos(), segmentNumber, percentOfSegment).getId());
                }
                break;
        }
    }



    /**
     * @return - how strong this balloon is
     */
    public float getStrength()
    {
        return ((BalloonType) BalloonsManager.getBalloonsTypesDictionaryReference().getTypeDict().get(this.getType())).getStrength();
    }

    public float getSpeed()
    {
        return BALLOONS_SPEED_MULTIPLIER * ((BalloonType) BalloonsManager.getBalloonsTypesDictionaryReference().getTypeDict().get(this.getType())).getSpeed();
    }

    /**
     * @return - return the radius based on the balloon type
     */
    public float getRadius()
    {
        return BALLOONS_RADIUS_MULTIPLIER * ((BalloonType) BalloonsManager.getBalloonsTypesDictionaryReference().getTypeDict().get(this.getType())).getRadius();
    }

    public int getColor()
    {
        return ((BalloonType) BalloonsManager.getBalloonsTypesDictionaryReference().getTypeDict().get(this.getType())).getColor();

    }

    public int getSegmentNumber()
    {
        return segmentNumber;
    }

    public void setSegmentNumber(int segmentNumber)
    {
        this.segmentNumber = segmentNumber;
    }

    public float getPercentOfSegment()
    {
        return percentOfSegment;
    }

    public void setPercentOfSegment(float percentOfSegment)
    {
        this.percentOfSegment = percentOfSegment;
    }

}
