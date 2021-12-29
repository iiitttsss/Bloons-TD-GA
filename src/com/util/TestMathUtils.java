package com.util;

public class TestMathUtils
{

	public static void main(String[] args)
	{
		testCalculateLineCircleXIntercepts();
	}

	private static void testCalculateLineCircleXIntercepts()
	{
		// (-0.823, -0.823), (1.823, 1.823)
		System.out.println("test two intersections");

		for (float s : MathUtils.calculateLineCircleXIntercepts(1, 0, 2, 0, 0, 1, 1))
		{
			System.out.println(s);
		}

		// (), ()
		System.out.println("test 0 intersections");

		for (float s : MathUtils.calculateLineCircleXIntercepts(1, 0, 3, -1, 5, 5, 5))
		{
			System.out.println(s);
		}

		System.out.println("test 1 intersection");
		// (1, 5), ()
		// can returns nothing or 2 solutions because due to rounding error
		for (float s : MathUtils.calculateLineCircleXIntercepts(1, 0, 5f, -1, 5, 5, 5))
		{
			System.out.println(s);
		}

		System.out.println("test horizontal line");
		// (-6.66, 5), (10.66, 5) but returns nothing probably because rounding error
		for (float s : MathUtils.calculateLineCircleXIntercepts(2, 0, 10, -1, 5, 5, 5))
		{
			System.out.println(s);
		}

		System.out.println("test verticle line");
		// (5, 9.539), (5, -9.539) but returns nothing probably because rounding error
		for (float s : MathUtils.calculateLineCircleXIntercepts(2, 0, 10, 5, -5, 5, 5))
		{
			System.out.println(s);
		}
	}

}
