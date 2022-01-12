package com.util;

import java.util.ArrayList;

public class MathUtils
{
	// box defigned by 2 points of oposite vertexes
	/**
	 * 
	 * @param x1 - x of the first vertex of the box
	 * @param y1 - y of the first vertex of the box
	 * @param x2 - x of the second vertex of the box
	 * @param y2 - y of the second vertex of the box
	 * @param a  - x of the point
	 * @param b  - y of the point
	 * @return - is the point inside or on the box
	 */
	public static boolean isPointInsideBox(float x1, float y1, float x2, float y2, float a, float b)
	{
		// if the vertecies are on the same vertical / horizontal line

		return (((a >= x1) && (a <= x2)) || ((a <= x1) && (a >= x2)))
				&& (((b >= y1) && (b <= y2)) || ((b <= y1) && (b >= y2)));
	}

	/**
	 * @param cx - x center of circle
	 * @param cy - y center of circle
	 * @param cr - radius of circle
	 * @param x1 - x of point
	 * @param y1 - y of point
	 * @return - is the point indside the circle
	 */
	public static boolean isPointInsideCircle(float cx, float cy, float cr, float x1, float y1)
	{
		float insideSqrt = (x1 - cx) * (x1 - cx) + (y1 - cy) * (y1 - cy);
		return insideSqrt < cr * cr;
	}

	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return - distance between 2 points
	 */
	public static float distance(float x1, float y1, float x2, float y2)
	{
		return (float) Math.sqrt(MathUtils.distanceSquare(x1, y1, x2, y2));
	}

	public static float distanceSquare(float x1, float y1, float x2, float y2)
	{
		return (((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
	}
	/**
	 * 
	 * @param cx - x of the center of the circle
	 * @param cy - y of the center of the circle
	 * @param cr - radius of the circle
	 * @param x1 - x of point 1
	 * @param y1 - y of point 1
	 * @param x2 - x of point 2
	 * @param y2 - y of point 2
	 * @return the intercepts between the circle and the line
	 * can be either 0, 1, or 2 intercepts
	 * containes x and then y for evry intercept
	 * 
	 * circle:
	 * (x-cx)^2 + (y-cy)^2 = cr^2
	 * line:
	 * m=(y1-y2)/(x1-x2)
	 * y-y1 = m(x-x1)
	 */
	public static ArrayList<Float> calculateLineCircleXIntercepts(float cx, float cy, float cr, float x1, float y1,
			float x2, float y2)
	{
		ArrayList<Float> solutions = new ArrayList<Float>();
		if (x1 != x2)
		{
			float lineSlope = (y1 - y2) / (x1 - x2);
			float lineOffset = y1 - lineSlope * x1;

			float a = lineSlope * lineSlope + 1;
			float b = 2 * lineSlope * (lineOffset - cy) - 2 * cx;
			float c = cx * cx + (lineOffset - cy) * (lineOffset - cy) - cr * cr;

			float discriminant = b * b - 4 * a * c;

			if (discriminant >= 0)
			{
				float sqrt = (float) Math.sqrt(discriminant);

				float solution1 = (-b + sqrt) / (2 * a);
				solutions.add(solution1);
				solutions.add(solution1 * lineSlope + lineOffset);

				if (discriminant != 0)
				{
					float solution2 = (-b - sqrt) / (2 * a);
					solutions.add(solution2);
					solutions.add(solution2 * lineSlope + lineOffset);
				}
			}
		}
		else // vertical line
		{
			// if the line passes through the circle
			if (x1 >= cx - cr && x1 <= cx + cr)
			{
				float s = (float) Math.sqrt(cr * cr - (x1 - cx) * (x1 - cx));
				float solution1 = s + cy;
				solutions.add(x1);
				solutions.add(solution1);
				if (s != 0)
				{
					float solution2 = -s + cy;
					solutions.add(x1);
					solutions.add(solution2);
				}
			}
		}
		return solutions;
	}
}
