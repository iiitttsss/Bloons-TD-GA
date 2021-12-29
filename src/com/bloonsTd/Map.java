/**
 * 13-12-2021
 * handle the game map
 */
package com.bloonsTd;

import java.util.ArrayList;

import com.Global;
import com.bloonsTd.balloons.Balloon;
import com.bloonsTd.balloons.BalloonsTypesDictionary;
import com.bloonsTd.towers.Tower;
import com.bloonsTd.towers.TowersTypesDictionary;

import processing.core.PGraphics;

public class Map
{
	// bloons will move from point to point
	// y - each point
	// x - cordinates
	// bloons spawn at the first
	// bloons finish at the last
	private int[][] pathPoints;

	// will track handy data for each segment
	// - segment length
	// - dx
	// - dy
	// - slope
	private float[][] segmentData;

	// for both sides of the line between the point
	// there is some distance towers cannot be placed in
	private float pathWidth;

	// for every pixel in the map,
	// this array store the distance to the closest path
	private float[][] distanceFromClosestPath;

	private ArrayList<Balloon> balloons;


	private ArrayList<Tower> towers;

	// rendering
	private PGraphics mapBuffer;

	public Map(int[] graphicsSize)
	{
		// TODO
		// path
		this.setPathWidth(40);
		int[][] path = {
				{ 100, 100 }, { 50, 400 }, { 800, 300 }, { 500, 800 }, { 700, 800 }, { 700, 1000 }, { 900, 1000 },
				{ 900, 700 }, { 100, 550 } };
		this.setPathPoints(path);
		this.setSegmentData(this.createSegmentData(path));
		this.setDistanceFromClosestPath(this.calculateDistanceFromClosestPathArray(graphicsSize[0], graphicsSize[1]));
		// balloons
		BalloonsTypesDictionary.initTypeDict();
		this.setBalloons(new ArrayList<Balloon>());

		this.getBalloons().add(new Balloon(100, 100, BalloonsTypesDictionary.RED_BALLOON));
		this.getBalloons().add(new Balloon(100, 100, BalloonsTypesDictionary.BLUE_BALLOON));

		this.getBalloons().add(new Balloon(100, 100, BalloonsTypesDictionary.GREEN_BALLOON));
		this.getBalloons().add(new Balloon(100, 100, BalloonsTypesDictionary.YELLOW_BALLOON));
		this.getBalloons().add(new Balloon(100, 100, BalloonsTypesDictionary.PINK_BALLOON));
		this.getBalloons().add(new Balloon(100, 100, BalloonsTypesDictionary.BLACK_BALLOON));
		this.getBalloons().add(new Balloon(100, 100, BalloonsTypesDictionary.WHITE_BALLOON));

		TowersTypesDictionary.initTypeDict();
		this.setTowers(new ArrayList<Tower>());
//		for (int x = 0; x < 1200; x += 100)
//		{
//
//			this.placeTower(x, 500, TowersTypesDictionary.DART_MONKEY);
//			this.placeTower(x, 1000, TowersTypesDictionary.DART_MONKEY);
//		}
		this.placeTower(500, 500, TowersTypesDictionary.DART_MONKEY, this.getPathPoints());


		// render
		this.setMapBuffer(Global.getPro().createGraphics(graphicsSize[0], graphicsSize[1]));
	}

	private float[][] calculateDistanceFromClosestPathArray(int width, int height)
	{
		float[][] distanceFromClosestPathArray = new float[height][width];

		for (int yp = 0; yp < height; yp++) // y pixel
		{
			for (int xp = 0; xp < width; xp++) // x pixel
			{
				float minDist = width + height;
				for (int pvi = 0; pvi < this.getPathPoints().length - 1; pvi++) // pathVertexIndex = pvi
				{
					int[] currentPoint = this.getPathPoints()[pvi]; //currentPoint
					int[] nextPoint = this.getPathPoints()[pvi + 1]; // nextPoint
					
					float m = this.getSegmentData()[pvi][3];

					float y1 = -1 / m * (xp - currentPoint[0]) + currentPoint[1];
					float y2 = -1 / m * (xp - nextPoint[0]) + nextPoint[1];

					// if ((xp <= currentPoint[0] && xp >= nextPoint[0]) || (xp >= currentPoint[0]
					// && xp <= nextPoint[0]))
					if ((yp <= y1 && yp >= y2) || (yp >= y1 && yp <= y2))
					{
						if (currentPoint[0] == nextPoint[0])
						{
							float dist = (float) Math.abs(xp - currentPoint[0]);

							if (dist < minDist)
							{
								minDist = dist;
							}
						}
						else
						{
						
							// y - y1 = m(x - x1)
							// y = mx + (y1 - mx1)
							// 0 = mx - y + (y1 -mx1)
							// a=m, b=-1, c=(y1-mx1)

							// m*xp - yp + currentPoint[1] - m*currentPoint[0]
							float dist = (float) (Math.abs(m * (xp - currentPoint[0]) - yp + currentPoint[1])
									/ Math.sqrt(m * m + 1));

							if (dist < minDist)
							{
								minDist = dist;
							}
						}
					}

				}
				for (int[] pathPoint : this.getPathPoints())
				{
					float dist = (float) Math.sqrt(
							(xp - pathPoint[0]) * (xp - pathPoint[0]) + (yp - pathPoint[1]) * (yp - pathPoint[1]));
					if (dist < minDist)
					{
						minDist = dist;
					}
				}

				distanceFromClosestPathArray[yp][xp] = minDist;
			}
		}

		return distanceFromClosestPathArray;
	}

	private float[][] createSegmentData(int[][] vertices)
	{
		float[][] segments = new float[this.getPathPoints().length - 1][4];
		for (int i = 0; i < this.getPathPoints().length - 1; i++)
		{
			int[] vertex = this.getPathPoints()[i];
			int[] nextVertex = this.getPathPoints()[i + 1];
			
			float dist = (float) Math.sqrt((vertex[0] - nextVertex[0]) * (vertex[0] - nextVertex[0])
					+ (vertex[1] - nextVertex[1]) * (vertex[1] - nextVertex[1]));
			segments[i][0] = dist;

			float dx = (nextVertex[0] - vertex[0]);
			segments[i][1] = dx;
			float dy = (nextVertex[1] - vertex[1]);
			segments[i][2] = dy;

			float slope = dy / dx;
			segments[i][3] = slope;

		}
		return segments;
	}

	public void renderMapToBuffer()
	{
		PGraphics md = this.getMapBuffer(); // md - map display
		md.beginDraw();

		// md.background(150);
		this.renderBackground(md);
		this.renderPaths(md);
		this.renderBloons(md);
		this.renderTowers(md);

		md.text(Global.getPro().frameRate, 30, 15);
		md.text(Global.getPro().mouseX, 30, 30);
		md.text(Global.getPro().mouseY, 30, 45);

		md.endDraw();
	}

	private void renderTowers(PGraphics md)
	{
		md.pushStyle();
		for (Tower tower : this.getTowers())
		{
			md.fill(TowersTypesDictionary.typeDict.get(tower.getType()).getColor());
			float radius = TowersTypesDictionary.typeDict.get(tower.getType()).getRadius();
			md.circle(tower.getxPos(), tower.getyPos(), 2 * radius);
			md.noFill();
			md.circle(tower.getxPos(), tower.getyPos(), 2 * tower.getRange());

			if (tower.getLastTarget() != null)
			{
				md.circle(tower.getLastTarget().getxPos(), tower.getLastTarget().getyPos(), 50);
			}
		}
		md.popStyle();
	}

	private void renderBackground(PGraphics md)
	{
		md.background(150);
//		for (int y = 0; y < md.height; y++)
//		{
//			for (int x = 0; x < md.width; x++)
//			{
//				float value = this.getDistanceFromClosestPath()[y][x];
//				int c;
//				if (value <= 40)
//				{
//					c = Global.getPro().color(255);
//				}
//				else
//				{
//					c = Global.getPro().color(0);
//				}
//				md.set(x, y, c);
//			}
//		}
//		md.updatePixels();

	}

	/**
	 * 
	 * @param balloon - the balloon
	 * @return - return true if the balloons did not finish the course
	 */
	private boolean isBalloonOnPath(Balloon balloon)
	{
		return balloon.getSegmentNumber() < this.getSegmentData().length;
	}

	private void moveBalloons()
	{
		for (Balloon balloon : this.getBalloons())
		{
			if (!this.isBalloonOnPath(balloon))
			{
				continue;
			}
			int segmentNumber = balloon.getSegmentNumber();
			
			float[] segmentData = this.getSegmentData()[segmentNumber];
			float percentIncrease = balloon.getSpeed() / segmentData[0];

			balloon.setPercentOfSegment(balloon.getPercentOfSegment() + percentIncrease);
			balloon.setxPos(balloon.getxPos() + percentIncrease * segmentData[1]);
			balloon.setyPos(balloon.getyPos() + percentIncrease * segmentData[2]);

			// move to the next segment
			if (balloon.getPercentOfSegment() >= 1f)
			{
				balloon.setSegmentNumber(balloon.getSegmentNumber() + 1);

				// if the segment exist
				if (this.isBalloonOnPath(balloon))
				{
					float[] newSegmentData = this.getSegmentData()[balloon.getSegmentNumber()];

					float startPercent = (balloon.getPercentOfSegment() - 1f) * segmentData[0] / newSegmentData[0];
					balloon.setPercentOfSegment(startPercent);
					balloon.setxPos(balloon.getxPos() + startPercent * newSegmentData[1]);
					balloon.setyPos(balloon.getyPos() + startPercent * newSegmentData[2]);
				}
			}
		}
	}

	private void renderBloons(PGraphics md)
	{
		md.push();
		for (Balloon balloon : this.getBalloons())
		{
			md.fill(BalloonsTypesDictionary.typeDict.get(balloon.getType()).getColor());
			md.circle(balloon.getxPos(), balloon.getyPos(), 20);
		}
		md.pop();
	}

	private void renderPaths(PGraphics md)
	{
		for (int i = 0; i < this.getPathPoints().length; i++)
		{
			int[] vertex = this.getPathPoints()[i];

			md.pushStyle();
			md.strokeWeight(this.getPathWidth() * 2);
			md.stroke(200);
			if (i != this.getPathPoints().length - 1)
			{
				int[] nextVertex = this.getPathPoints()[i + 1];
				md.line(vertex[0], vertex[1], nextVertex[0], nextVertex[1]);
			}
			md.strokeWeight(3);

			md.circle(vertex[0], vertex[1], 10);

			md.popStyle();
		}
	}

	private void placeTower(int x, int y, int towerType, int[][] pathPoints)
	{
		// TODO
		// check if the place is good
		// (check if intersecting the path / other towers)
		// this.getTowers().add(new Tower(x, y, towerType));

		if (this.getDistanceFromClosestPath()[y][x] >= TowersTypesDictionary.typeDict.get(towerType).getRadius()
				+ this.getPathWidth())
		{
			this.getTowers().add(new Tower(x, y, towerType, pathPoints));
		}
	}

	private void updateTowers()
	{
		for (Tower tower : this.getTowers())
		{
			tower.update(1000 / 30, balloons);
		}
	}

	public void update()
	{
		// build towers
		// add money
		// move bloons
		this.moveBalloons();
		// hit bloons
		this.updateTowers();
	}

	public PGraphics getMapBuffer()
	{
		return mapBuffer;
	}

	public void setMapBuffer(PGraphics mapBuffer)
	{
		this.mapBuffer = mapBuffer;
	}

	public int[][] getPathPoints()
	{
		return pathPoints;
	}

	public void setPathPoints(int[][] pathPoints)
	{
		this.pathPoints = pathPoints;
	}

	public float getPathWidth()
	{
		return pathWidth;
	}

	public void setPathWidth(float pathWidth)
	{
		this.pathWidth = pathWidth;
	}

	public ArrayList<Balloon> getBalloons()
	{
		return balloons;
	}

	public void setBalloons(ArrayList<Balloon> balloons)
	{
		this.balloons = balloons;
	}

	public float[][] getSegmentData()
	{
		return segmentData;
	}

	public void setSegmentData(float[][] segmentData)
	{
		this.segmentData = segmentData;
	}

	public float[][] getDistanceFromClosestPath()
	{
		return distanceFromClosestPath;
	}

	public void setDistanceFromClosestPath(float[][] distanceFromClosestPath)
	{
		this.distanceFromClosestPath = distanceFromClosestPath;
	}

	public ArrayList<Tower> getTowers()
	{
		return towers;
	}

	public void setTowers(ArrayList<Tower> towers)
	{
		this.towers = towers;
	}

}
