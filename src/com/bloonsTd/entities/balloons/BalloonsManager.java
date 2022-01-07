package com.bloonsTd.entities.balloons;

import com.bloonsTd.entities.EntitiesManager;
import com.bloonsTd.entities.Entity;

public class BalloonsManager extends EntitiesManager
{

    private static BalloonsTypesDictionary balloonsTypesDictionaryReference;

    public BalloonsManager()
    {
        super();
        this.setTypeDict(new BalloonsTypesDictionary());
        BalloonsManager.setBalloonsTypesDictionaryReference((BalloonsTypesDictionary) this.getTypeDict());

    }

    @Override
    public Balloon createNewEntity()
    {
        return new Balloon();
    }

    /**
     * adding new balloon to the balloonsManager
     *
     * @param type             - the type of the new balloon
     * @param initXPos         - the x position of the new balloon
     * @param initYPos         - the y position of the new balloon
     * @param segmentNumber    - the number of the segment the new balloons is on
     * @param percentOfSegment - the percent of the segment the balloon is on
     * @return - the new balloon object
     */
    public Balloon addBalloon(int type, float initXPos, float initYPos, int segmentNumber, float percentOfSegment)
    {
        Balloon balloon = (Balloon) super.addEntity();
        balloon.init(type, initXPos, initYPos, segmentNumber, percentOfSegment);

        return balloon;
    }

    /**
     * checking if a balloon passed all the path and returning how much lives are
     * lost
     *
     * @param allSegmentData - pre calculated data on all the segments
     * @return - the number of lives lost
     */
    public float checkForPasses(float[][] allSegmentData)
    {
        float passes = 0;
        for (Entity entity : this.getActiveEntities()) {
            Balloon balloon = (Balloon) entity;

            if (!this.isBalloonOnPath(balloon, allSegmentData)) {
                passes += balloon.getStrength();
                balloon.setActive(false);
            }
        }
        return passes;
    }

    /**
     * @param balloon        - the balloon
     * @param allSegmentData - pre calculated data on all the segments
     * @return - return true if the balloons did not finish the course
     */
    private boolean isBalloonOnPath(Balloon balloon, float[][] allSegmentData)
    {
        return balloon.getSegmentNumber() < allSegmentData.length;
    }

    /**
     * handling the movement of all the balloons
     *
     * @param allSegmentData - pre calculated data on all the segments
     */
    public void moveBalloons(float deltaTime, float[][] allSegmentData)
    {
        for (Entity entity : this.getActiveEntities()) {
            Balloon balloon = (Balloon) entity;

            if (!this.isBalloonOnPath(balloon, allSegmentData)) {
                continue;
            }
            int segmentNumber = balloon.getSegmentNumber();

            float[] segmentData = allSegmentData[segmentNumber];
            float percentIncrease = deltaTime * balloon.getSpeed() / segmentData[0];

            balloon.setPercentOfSegment(balloon.getPercentOfSegment() + percentIncrease);
            balloon.setxPos(balloon.getxPos() + percentIncrease * segmentData[1]);
            balloon.setyPos(balloon.getyPos() + percentIncrease * segmentData[2]);

            // move to the next segment
            if (balloon.getPercentOfSegment() >= 1f) {
                balloon.setSegmentNumber(balloon.getSegmentNumber() + 1);

                // if the segment exist
                if (this.isBalloonOnPath(balloon, allSegmentData)) {
                    float[] newSegmentData = allSegmentData[balloon.getSegmentNumber()];

                    float startPercent = (balloon.getPercentOfSegment() - 1f) * segmentData[0] / newSegmentData[0];
                    balloon.setPercentOfSegment(startPercent);
                    balloon.setxPos(balloon.getxPos() + startPercent * newSegmentData[1]);
                    balloon.setyPos(balloon.getyPos() + startPercent * newSegmentData[2]);
                }

            }
        }
    }

    public static BalloonsTypesDictionary getBalloonsTypesDictionaryReference()
    {
        return balloonsTypesDictionaryReference;
    }

    public static void setBalloonsTypesDictionaryReference(BalloonsTypesDictionary balloonsTypesDictionaryReference)
    {
        //System.out.println("try new reference");
        // in order to create the reference only once:
        if (BalloonsManager.getBalloonsTypesDictionaryReference() == null) {
            BalloonsManager.balloonsTypesDictionaryReference = balloonsTypesDictionaryReference;
            //System.out.println("new reference");
        }
    }

}
