package com.bloonsTd;

import com.Global;
import com.bloonsTd.balloons.Balloon;
import com.bloonsTd.balloons.BalloonsTypesDictionary;
import com.bloonsTd.bullets.Bullet;
import com.bloonsTd.towers.Tower;
import com.bloonsTd.towers.TowersTypesDictionary;

import processing.core.PGraphics;

public class MapRenderer
{
	// rendering
	private PGraphics mapBuffer;

	private Map mapReference;

	public MapRenderer(int[] graphicsSize, Map mr)
	{
		this.setMapBuffer(Global.getPro().createGraphics(graphicsSize[0], graphicsSize[1]));
		this.setMapReference(mr);
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
		for (Bullet bullet : this.getMapReference().getBullets().getBullets())
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
		for (Tower tower : this.getMapReference().getTowers())
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
		for (Balloon balloon : this.getMapReference().getBalloons().getBalloons())
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
		for (int i = 0; i < this.getMapReference().getPath().getPathPoints().length; i++)
		{
			int[] vertex = this.getMapReference().getPath().getPathPoints()[i];

			md.pushStyle();
			md.strokeWeight(this.getMapReference().getPath().getPathWidth() * 2);
			md.stroke(200);
			if (i != this.getMapReference().getPath().getPathPoints().length - 1)
			{
				int[] nextVertex = this.getMapReference().getPath().getPathPoints()[i + 1];
				md.line(vertex[0], vertex[1], nextVertex[0], nextVertex[1]);
			}
			md.strokeWeight(3);

			md.circle(vertex[0], vertex[1], 10);

			md.popStyle();
		}
	}

	public PGraphics getMapBuffer()
	{
		return mapBuffer;
	}

	public void setMapBuffer(PGraphics mapBuffer)
	{
		this.mapBuffer = mapBuffer;
	}

	public Map getMapReference()
	{
		return mapReference;
	}

	public void setMapReference(Map mapReference)
	{
		this.mapReference = mapReference;
	}
}
