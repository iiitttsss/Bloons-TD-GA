package com.bloonsTd.towers;

import java.util.ArrayList;

import com.bloonsTd.balloons.Balloon;
import com.bloonsTd.balloons.BalloonsManager;
import com.bloonsTd.bullets.BulletsManager;
import com.bloonsTd.towers.smartRange.SegmentPercent;
import com.util.MathUtils;

public class Tower
{
	// the size of the tower is determined by the tower type
	private float xPos;
	private float yPos;

	private float coolDownRemained;

	private int type;

	// smart range - what part of each segment is within the range

	private ArrayList<SegmentPercent> segmentsPercents;

	public static final int EVALUATE_CLOSE = 0;
	public static final int EVALUATE_STRONG = 1;
	public static final int EVALUATE_FIRST = 2;
	public static final int EVALUATE_LAST = 3;

	private Balloon lastTarget;

	public Tower(float xPos, float yPos, int type, int[][] pathPoints)
	{
		this.setxPos(xPos);
		this.setyPos(yPos);
		this.setType(type);
		this.createSegmentsPercents(pathPoints);

		// this.setSegmentsPercents(this.createSegmentsPercents());
	}

	/**
	 * 
	 * @return - returning the tower radius based on its type
	 */
	public float getRadius()
	{
		return TowersTypesDictionary.typeDict.get(this.getType()).getRadius();
	}

	/**
	 * 
	 * @return - return true if the tower can shoot (based of cool down timer);
	 */
	private boolean canShoot()
	{
		return this.getCoolDownRemained() <= 0;
	}

	/**
	 * handeling shooting
	 * 
	 * @param targetBalloon
	 */
	private void shoot(BulletsManager bulletsManager, Balloon targetBalloon)
	{
		float dx = (targetBalloon.getxPos() - this.getxPos());
		float dy = (targetBalloon.getyPos() - this.getyPos());
		float magnitude = MathUtils.distance(0, 0, dx, dy);
		float speed = this.getBulletSpeed();

		bulletsManager.addBullet(this.getxPos(), this.getyPos(), dx * speed / magnitude, dy * speed / magnitude,
				this.getBulletLifeTime());

		this.setCoolDownRemained(this.getTowerCoolDown());
	}

	private float getTowerCoolDown()
	{
		// should return based on tower type (from a dictionary)
		return 1000;
	}

	private float getBulletLifeTime()
	{
		// should return based on tower type (from a dictionary)
		return 1000;
	}

	private float getBulletSpeed()
	{
		// should return based on tower type (from a dictionary)
		return 20;
	}

	/**
	 * called once every frame - incharge of all the tower actions
	 * 
	 * @param balloons - the list of all the balloons
	 */
	public void update(float deltaTime, BulletsManager bulletsManager, BalloonsManager balloons)
	{
		// may nedd to change if in while
		this.coolDownRemained -= deltaTime;
		if (this.canShoot())
		{
			Balloon targetBalloon = this.chooseTarget(balloons);
			if (targetBalloon != null)
			{
				this.shoot(bulletsManager, targetBalloon);
			}
		}
	}

	/**
	 * @return the range of this tower
	 */
	public float getRange()
	{
		// TODO Auto-generated method stub
		return 200;
	}

	/**
	 * 
	 * @param balloon
	 * @param evaluationMethod - strong/first/last/close
	 * @return - how much this balloons is good considering what the tower is
	 *         sorting for. higher score is better.
	 */
	private float evaluateBalloonPriority(Balloon balloon, int evaluationMethod)
	{
		// the higer the better
		switch (evaluationMethod)
		{
		case Tower.EVALUATE_CLOSE:
			return 1.0f
					/ MathUtils.distanceSquare(balloon.getxPos(), balloon.getyPos(), this.getxPos(), this.getyPos());
		case Tower.EVALUATE_FIRST:
			return balloon.getSegmentNumber() + balloon.getPercentOfSegment();
		case Tower.EVALUATE_LAST:
			return 1f / (balloon.getSegmentNumber() + balloon.getPercentOfSegment());
		case Tower.EVALUATE_STRONG:
			return balloon.getStrength();
		default:
			return Float.MIN_VALUE;
		}
	}

	/**
	 * @param x - x pos
	 * @param y - y pos
	 * @return - if the point (x, y) is within range
	 */
	private boolean isInRange(Balloon balloon)
	{
		float range = this.getRange() + balloon.getRadius();
		return MathUtils.distanceSquare(balloon.getxPos(), balloon.getyPos(), this.getxPos(), this.getyPos()) < range
				* range;
	}

	/**
	 * 
	 * @param balloons - the list of all the balloons
	 * @return - the best balloon to target
	 */
	private Balloon chooseTarget(BalloonsManager balloons)
	{
		Balloon bestBalloon = null;
		float bestScore = Float.MIN_VALUE;

		for (Balloon balloon : balloons.getActiveBalloons())
		{

			if (this.isInRange(balloon))
			{
				float balloonScore = this.evaluateBalloonPriority(balloon, Tower.EVALUATE_FIRST);
				if (balloonScore > bestScore)
				{
					bestScore = balloonScore;
					bestBalloon = balloon;
				}
			}

		}
		this.setLastTarget(bestBalloon);
		return bestBalloon;
	}

	/**
	 * 
	 * @param pathPoints - the vertecies of the path
	 * @return - determining what start and end percent of all segments is within
	 *         range and return and array containing all that saegment with segment
	 *         number, start percent and end percent
	 */
	private ArrayList<SegmentPercent> createSegmentsPercents(int[][] pathPoints)
	{
		ArrayList<SegmentPercent> sp = new ArrayList<SegmentPercent>();

		for (int vertexIndex = 0; vertexIndex < pathPoints.length - 1; vertexIndex++)
		{
			int[] point1 = pathPoints[vertexIndex];
			int[] point2 = pathPoints[vertexIndex + 1];

			ArrayList<Float> intercepts = MathUtils.calculateLineCircleXIntercepts(this.getxPos(), this.getyPos(),
					this.getRange(), point1[0], point1[1], point2[0], point2[1]);

			// if there are 2 intercepts
			// 4 because it contains: x1, y1, x2, y2

			// and at leas 1 intersection is between the boinding box of the two points
			if (intercepts.size() == 4)
			{
				boolean isBothSegmentPointsInsideTheCircle = (MathUtils.isPointInsideCircle(this.getxPos(),
						this.getyPos(), this.getRange(), point1[0], point1[1])
						&& MathUtils.isPointInsideCircle(this.getxPos(), this.getyPos(), this.getRange(), point2[0],
								point2[1]));
//				boolean isIntercept1InsideTheSegmentBox = ;
//				boolean isIntercept2InsideTheSegmentBox = ;

				if (isBothSegmentPointsInsideTheCircle || (MathUtils.isPointInsideBox(point1[0], point1[1], point2[0],
						point2[1], intercepts.get(0), intercepts.get(1))
						|| MathUtils.isPointInsideBox(point1[0], point1[1], point2[0], point2[1], intercepts.get(2),
								intercepts.get(3))))
				{
					float[][] startAndEndPoints = this.determineStartAndEndPointForSegmentsInsideRange(intercepts,
							point1, point2);
					// System.out.println("start point: " + startAndEndPoints[0][0] + ", " +
					// startAndEndPoints[0][1]);
					// System.out.println("end point: " + startAndEndPoints[1][0] + ", " +
					// startAndEndPoints[1][1]);

					float distBetweenPoint1And2 = MathUtils.distance(point1[0], point1[1], point2[0], point2[1]);
					float startPercent = MathUtils.distance(point1[0], point1[1], startAndEndPoints[0][0],
							startAndEndPoints[0][1]) / distBetweenPoint1And2;
					float endPercent = MathUtils.distance(point1[0], point1[1], startAndEndPoints[1][0],
							startAndEndPoints[1][1]) / distBetweenPoint1And2;

					sp.add(new SegmentPercent(vertexIndex, startPercent, endPercent));

				}
			}
		}
//		for (SegmentPercent segment : sp)
//		{
//			System.out.println("segment number: " + segment.getSegmentNumber());
//			System.out.println("start: " + segment.getStartPercentOfSegment());
//			System.out.println("end: " + segment.getEndPercentOfSegment());
//		}

		return sp;
	}

	private float[][] determineStartAndEndPointForSegmentsInsideRange(ArrayList<Float> intercepts, int[] point1,
			int[] point2)
	{
		// determine which point is assosate with what intersection
		// distance point to intersection
		float dist1to1 = MathUtils.distance(point1[0], point1[1], intercepts.get(0), intercepts.get(1));
		float dist1to2 = MathUtils.distance(point1[0], point1[1], intercepts.get(2), intercepts.get(3));
		float dist2to1 = MathUtils.distance(point2[0], point2[1], intercepts.get(0), intercepts.get(1));
		float dist2to2 = MathUtils.distance(point2[0], point2[1], intercepts.get(2), intercepts.get(3));

		// to detrmine which point is assoiated with which intersection,
		// the smaller sum will be the correct one
		float sum1 = dist1to1 + dist2to2;
		float sum2 = dist1to2 + dist2to1;

		float[] intersection1 = new float[2];
		float[] intersection2 = new float[2];
		if (sum1 < sum2)
		{
			intersection1[0] = intercepts.get(0);
			intersection1[1] = intercepts.get(1);
			intersection2[0] = intercepts.get(2);
			intersection2[1] = intercepts.get(3);
		}
		else
		{
			intersection1[0] = intercepts.get(2);
			intersection1[1] = intercepts.get(3);
			intersection2[0] = intercepts.get(0);
			intersection2[1] = intercepts.get(1);
		}

		// determine if should start/end on the edge of a circle or inside the circle
		float[] start;
		float[] end;
		if (MathUtils.isPointInsideCircle(this.getxPos(), this.getyPos(), this.getRange(), point1[0], point1[1]))
		{
			start = new float[2];
			start[0] = point1[0];
			start[1] = point1[1];
		}
		else
		{
			start = intersection1;

		}
		if (MathUtils.isPointInsideCircle(this.getxPos(), this.getyPos(), this.getRange(), point2[0], point2[1]))
		{
			end = new float[2];
			end[0] = point2[0];
			end[1] = point2[1];
		}
		else
		{
			end = intersection2;
		}

		// returning start and end points
		float[][] returnArrays = new float[2][2];
		returnArrays[0] = start;
		returnArrays[1] = end;
		return returnArrays;

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

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public ArrayList<SegmentPercent> getSegmentsPercents()
	{
		return segmentsPercents;
	}

	public void setSegmentsPercents(ArrayList<SegmentPercent> segmentsPercents)
	{
		this.segmentsPercents = segmentsPercents;
	}

	public Balloon getLastTarget()
	{
		return lastTarget;
	}

	public void setLastTarget(Balloon lastTarget)
	{
		this.lastTarget = lastTarget;
	}

	public float getCoolDownRemained()
	{
		return coolDownRemained;
	}

	public void setCoolDownRemained(float coolDownRemained)
	{
		this.coolDownRemained = coolDownRemained;
	}
}
