//keep track of the data for what part of the sesgment is within range
package com.bloonsTd.towers.smartRange;

public class SegmentPercent
{
	private int segmentNumber;
	private float startPercentOfSegment;
	private float endPercentOfSegment;

	public SegmentPercent(int sn, float spos, float epos)
	{
		this.setSegmentNumber(sn);
		this.setStartPercentOfSegment(spos);
		this.setEndPercentOfSegment(epos);
	}

	public int getSegmentNumber()
	{
		return segmentNumber;
	}
	public void setSegmentNumber(int segmentNumber)
	{
		this.segmentNumber = segmentNumber;
	}
	public float getStartPercentOfSegment()
	{
		return startPercentOfSegment;
	}
	public void setStartPercentOfSegment(float startPercentOfSegment)
	{
		this.startPercentOfSegment = startPercentOfSegment;
	}
	public float getEndPercentOfSegment()
	{
		return endPercentOfSegment;
	}
	public void setEndPercentOfSegment(float endPercentOfSegment)
	{
		this.endPercentOfSegment = endPercentOfSegment;
	}


}
