/**
 * 13-12-2021
 * handle the game map / the simulation object (running the game)
 */
package com.bloonsTd;

import java.util.ArrayList;

import com.Global;
import com.Main;
import com.bloonsTd.balloons.Balloon;
import com.bloonsTd.balloons.BalloonsManager;
import com.bloonsTd.balloons.BalloonsTypesDictionary;
import com.bloonsTd.rounds.BalloonsSpawner;
import com.bloonsTd.bullets.Bullet;
import com.bloonsTd.bullets.BulletsManager;
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

	private BalloonsManager balloons;

	private ArrayList<Tower> towers;

	private BulletsManager bullets;


	private float deltaTime = 1000f / Main.setFrameRate;

	private BalloonsSpawner balloonsSpawner;


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
		this.setBalloons(new BalloonsManager());

//		this.getBalloons().addBalloon(BalloonsTypesDictionary.RED_BALLOON, 100, 100, 0, 0);
//		this.getBalloons().addBalloon(BalloonsTypesDictionary.BLUE_BALLOON, 100, 100, 0, 0);
//
//		this.getBalloons().addBalloon(BalloonsTypesDictionary.GREEN_BALLOON, 100, 100, 0, 0);
//		this.getBalloons().addBalloon(BalloonsTypesDictionary.YELLOW_BALLOON, 100, 100, 0, 0);
//		this.getBalloons().addBalloon(BalloonsTypesDictionary.PINK_BALLOON, 100, 100, 0, 0);
//		this.getBalloons().addBalloon(BalloonsTypesDictionary.BLACK_BALLOON, 100, 100, 0, 0);
//		this.getBalloons().addBalloon(BalloonsTypesDictionary.WHITE_BALLOON, 100, 100, 0, 0);


		TowersTypesDictionary.initTypeDict();
		this.setTowers(new ArrayList<Tower>());
//		for (int x = 0; x < 1200; x += 100)
//		{
//
//			this.placeTower(x, 500, TowersTypesDictionary.DART_MONKEY);
//			this.placeTower(x, 1000, TowersTypesDictionary.DART_MONKEY);
//		}
		this.placeTower(500, 500, TowersTypesDictionary.DART_MONKEY, this.getPathPoints());
		this.placeTower(500, 250, TowersTypesDictionary.DART_MONKEY, this.getPathPoints());

		// this.placeTower(250, 200, TowersTypesDictionary.DART_MONKEY,
		// this.getPathPoints());

		this.setBullets(new BulletsManager());
//		for (int i = 0; i < 100; i++)
//		{
//			this.getBullets().addBullet(500, 500, (float) Math.random() * 2 - 1, (float) Math.random() * 2 - 1);
//		}

		this.setBalloonsSpawner(new BalloonsSpawner());
		// render
		this.setMapBuffer(Global.getPro().createGraphics(graphicsSize[0], graphicsSize[1]));
	}

	/**
	 * 
	 * @param width
	 * @param height
	 * @return - returning an array of the distance from the closest path for each
	 *         pixel
	 */
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

	/**
	 * 
	 * @param vertices - the points that defign the path
	 * @return - return an array of important data that will used a lot and only
	 *         need to be calculated once
	 */
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

	/**
	 * the main rendering method - call all the smaller rendering methods
	 */
	public void renderMapToBuffer()
	{
		PGraphics md = this.getMapBuffer(); // md - map display
		md.beginDraw();

		// md.background(150);
		this.renderBackground(md);
		this.renderPaths(md);
		this.renderBloons(md);
		this.renderTowers(md);
		this.renderBullets(md);

		md.text(Global.getPro().frameRate, 30, 15);
		md.text(Global.getPro().mouseX, 30, 30);
		md.text(Global.getPro().mouseY, 30, 45);

		md.endDraw();
	}

	private void renderBullets(PGraphics md)
	{
		md.pushStyle();
		for (Bullet bullet : this.getBullets().getBullets())
		{
			if (bullet.isActive())
			{
				md.circle(bullet.getxPos(), bullet.getyPos(), bullet.getRadius() * 2);
			}
		}
		md.popStyle();

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

			if (tower.getLastTarget() != null && tower.getLastTarget().isActive())
			{
				md.circle(tower.getLastTarget().getxPos(), tower.getLastTarget().getyPos(), 50);
			}
		}
		md.popStyle();
	}

	private void renderBackground(PGraphics md)
	{
		md.background(150);

		// visulazing the distance from the closest path
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


	private void renderBloons(PGraphics md)
	{
		md.push();
		for (Balloon balloon : this.getBalloons().getBalloons())
		{
			if (balloon.isActive())
			{
				md.fill(BalloonsTypesDictionary.typeDict.get(balloon.getType()).getColor());
				md.circle(balloon.getxPos(), balloon.getyPos(), balloon.getRadius() * 2);
			}
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
		// TODO - check if intersecting other towers
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
			tower.update(this.getDeltaTime(), this.getBullets(), this.getBalloons().getBalloons());
		}
	}

	private void updateBullets()
	{
		this.getBullets().update(this.getDeltaTime(), this.getBalloons());
	}

	/**
	 * the main update method
	 */
	public void update()
	{
		this.getBalloonsSpawner().spawnBalloons(deltaTime, this.getBalloons());
		// build towers
		// add money
		// move bloons
		this.updateBalloons();
		// hit bloons
		this.updateTowers();
		this.updateBullets();
	}

	private void updateBalloons()
	{
		this.getBalloons().update(this.getDeltaTime(), this.getSegmentData());
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

	public BalloonsManager getBalloons()
	{
		return balloons;
	}

	public void setBalloons(BalloonsManager balloons)
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

	public BulletsManager getBullets()
	{
		return bullets;
	}

	public void setBullets(BulletsManager bullets)
	{
		this.bullets = bullets;
	}

	public float getDeltaTime()
	{
		return deltaTime;
	}

	public void setDeltaTime(float deltaTime)
	{
		this.deltaTime = deltaTime;
	}

	public BalloonsSpawner getBalloonsSpawner()
	{
		return balloonsSpawner;
	}

	public void setBalloonsSpawner(BalloonsSpawner balloonsSpawner)
	{
		this.balloonsSpawner = balloonsSpawner;
	}
}
