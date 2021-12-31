package com.bloonsTd.path;

public class Path
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

	// the width and height of the map
	private int[] mapSize;

	public Path(int[] graphicsSize, int[][] newPathPoints)
	{
		this.setPathWidth(40);

		this.setPathPoints(newPathPoints);
		this.setSegmentData(this.createSegmentData(newPathPoints));

		this.setDistanceFromClosestPath(this.calculateDistanceFromClosestPathArray(graphicsSize[0], graphicsSize[1]));

		this.setMapSize(graphicsSize);
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
					int[] currentPoint = this.getPathPoints()[pvi]; // currentPoint
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

	public int[] getMapSize()
	{
		return mapSize;
	}

	public void setMapSize(int[] mapSize)
	{
		this.mapSize = mapSize;
	}
}
