package com.bloonsTd;

import com.Global;
import com.bloonsTd.entities.Entity;
import com.bloonsTd.entities.balloons.Balloon;
import com.bloonsTd.entities.balloons.BalloonsTypesDictionary;
import com.bloonsTd.entities.bullets.Bullet;
import com.bloonsTd.entities.towers.Tower;
import com.bloonsTd.entities.towers.TowersTypesDictionary;

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
		this.renderBalloons(md);
		this.renderTowers(md);
		this.renderBullets(md);

		md.text(Global.getPro().frameRate, 30, 15);
		md.text(Global.getPro().mouseX, 30, 30);
		md.text(Global.getPro().mouseY, 30, 45);
		md.text("lives: " + this.getMapReference().getLives(), 30, 60);

		md.text("# of balloons: " + this.getMapReference().getBalloons().getEntities().size(), 1100, 15);
		md.text("# of bullets: " + this.getMapReference().getBullets().getEntities().size(), 1100, 30);
		md.text("# of towers: " + this.getMapReference().getTowers().getEntities().size(), 1100, 45);

		md.endDraw();
	}

	private void renderBullets(PGraphics md)
	{
		md.pushStyle();
		for (Entity entity : this.getMapReference().getBullets().getActiveEntities())
		{
			Bullet bullet = (Bullet) entity;

			md.circle(bullet.getxPos(), bullet.getyPos(), bullet.getRadius() * 2);

		}
		md.popStyle();
	}

	private void renderTowers(PGraphics md)
	{
		md.pushStyle();
		for (Entity entity : this.getMapReference().getTowers().getActiveEntities())
		{
			Tower tower = (Tower) entity;

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

	private void renderBalloons(PGraphics md)
	{
		md.push();
		for (Entity entity : this.getMapReference().getBalloons().getActiveEntities())
		{
			Balloon balloon = (Balloon) entity;
			if (balloon.isActive())
			{
				md.fill(Balloon.getBalloonsTypesDictionaryReference().getTypeDict().get(balloon.getType()).getColor());
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
