package com.bloonsTd;

import processing.core.PGraphics;

public class MapRender
{
	// rendering
	private PGraphics mapBuffer;

	public PGraphics getMapBuffer()
	{
		return mapBuffer;
	}

	public void setMapBuffer(PGraphics mapBuffer)
	{
		this.mapBuffer = mapBuffer;
	}
}
