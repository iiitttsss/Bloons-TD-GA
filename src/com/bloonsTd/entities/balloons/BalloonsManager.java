package com.bloonsTd.entities.balloons;

import com.bloonsTd.entities.EntitiesManager;
import com.bloonsTd.entities.Entity;

public class BalloonsManager extends EntitiesManager {
    private BalloonsTypesDictionary typeDict;

    public BalloonsManager() {
        super();
        this.setTypeDict(new BalloonsTypesDictionary());
    }

    @Override
    public Balloon createNewEntity() {
        return new Balloon(this.getTypeDict());
    }

    /**
     * adding new balloon to the balloonsManager
     *
     * @param type
     * @param initXPos
     * @param initYPos
     * @param segmentNumber
     * @param segmentPercent
     * @return - the new balloon object
     */
    public Balloon addBalloon(int type, float initXPos, float initYPos, int segmentNumber, float percentOfSegment) {
        Balloon balloon = (Balloon) super.addEntity();
        balloon.init(type, initXPos, initYPos, segmentNumber, percentOfSegment);

        return balloon;
    }

    /**
     * checking if a balloon passed all the path and returning how much lives are
     * lost
     *
     * @param allSegmentData
     * @return - the number of lives lost
     */
    public float checkForPasses(float[][] allSegmentData) {
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
     * @param balloon - the balloon
     * @return - return true if the balloons did not finish the course
     */
    private boolean isBalloonOnPath(Balloon balloon, float[][] allSegmentData) {
        return balloon.getSegmentNumber() < allSegmentData.length;
    }

    /**
     * handeling the movement of all the balloons
     *
     * @param allSegmentData
     */
    public void moveBalloons(float deltaTime, float[][] allSegmentData) {
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

    public BalloonsTypesDictionary getTypeDict() {
        return typeDict;
    }

    public void setTypeDict(BalloonsTypesDictionary typeDict) {
        this.typeDict = typeDict;
    }

}
