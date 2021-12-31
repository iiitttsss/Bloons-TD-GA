/**
 * 13-12-2021
 * handle the game map / the simulation object (running the game)
 */
package com.bloonsTd;

import java.util.ArrayList;

import com.Main;
import com.bloonsTd.balloons.BalloonsManager;
import com.bloonsTd.balloons.BalloonsTypesDictionary;
import com.bloonsTd.rounds.BalloonsSpawner;
import com.bloonsTd.bullets.BulletsManager;
import com.bloonsTd.path.Path;
import com.bloonsTd.towers.Tower;
import com.bloonsTd.towers.TowerPlacer;
import com.bloonsTd.towers.TowersTypesDictionary;

public class Map
{
	private Path path;

	private BalloonsManager balloons;
	private BalloonsSpawner balloonsSpawner;

	private ArrayList<Tower> towers;

	private BulletsManager bullets;

	private float deltaTime = 1000f / Main.setFrameRate;

	public Map(int[] graphicsSize)
	{
		// path
		int[][] pathPoints = {
				{ 100, 100 }, { 50, 400 }, { 800, 300 }, { 500, 800 }, { 700, 800 }, { 700, 1000 }, { 900, 1000 },
				{ 900, 700 }, { 100, 550 } };
		this.setPath(new Path(graphicsSize, pathPoints));

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
		TowerPlacer.placeTower(this.getPath(), this.getTowers(), 500, 500, TowersTypesDictionary.DART_MONKEY);

		this.setBullets(new BulletsManager());

//		for (int i = 0; i < 100; i++)
//		{
//			this.getBullets().addBullet(500, 500, (float) Math.random() * 2 - 1, (float) Math.random() * 2 - 1);
//		}

		this.setBalloonsSpawner(new BalloonsSpawner());
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
		this.getBalloons().update(this.getDeltaTime(), this.getPath().getSegmentData());
	}

	public BalloonsManager getBalloons()
	{
		return balloons;
	}

	public void setBalloons(BalloonsManager balloons)
	{
		this.balloons = balloons;
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

	public Path getPath()
	{
		return path;
	}

	public void setPath(Path path)
	{
		this.path = path;
	}
}
