/*
  13-12-2021
  handle the game map / the simulation object (running the game)
 */
package com.bloonsTd;

import com.Main;
import com.bloonsTd.entities.balloons.BalloonsManager;
import com.bloonsTd.entities.balloons.BalloonsTypesDictionary;
import com.bloonsTd.entities.bullets.BulletsManager;
import com.bloonsTd.entities.towers.TowerPlacer;
import com.bloonsTd.entities.towers.TowersManager;
import com.bloonsTd.entities.towers.TowersTypesDictionary;
import com.bloonsTd.path.Path;
import com.bloonsTd.rounds.BalloonsSpawner;

public class Map
{
	private float lives;

	private Path path;

	private BalloonsManager balloons;
	private BalloonsSpawner balloonsSpawner;

	private TowersManager towers;

	private BulletsManager bullets;

	private float deltaTime = 1000f / Main.setFrameRate;

	public Map(int[] graphicsSize)
	{
		// path
		int[][] pathPoints = { { 100, 100 }, { 50, 400 }, { 800, 300 }, { 500, 800 }, { 700, 800 }, { 700, 1000 },
				{ 900, 1000 }, { 900, 700 }, { 100, 550 } };
		this.setPath(new Path(graphicsSize, pathPoints));

		// balloons
		this.setBalloons(new BalloonsManager());

		this.setTowers(new TowersManager());

		this.setBullets(new BulletsManager());

		this.setBalloonsSpawner(new BalloonsSpawner());
	}

	public void init()
	{
		this.setLives(200);
		this.getBalloons().init();
//		TowerPlacer.placeTower(this.getPath(), this.getTowers(), 500, 500, TowersTypesDictionary.DART_MONKEY);
//		TowerPlacer.placeTower(this.getPath(), this.getTowers(), 800, 900, TowersTypesDictionary.DART_MONKEY);
//		TowerPlacer.placeTower(this.getPath(), this.getTowers(), 750, 575, TowersTypesDictionary.DART_MONKEY);
//		TowerPlacer.placeTower(this.getPath(), this.getTowers(), 500, 250, TowersTypesDictionary.DART_MONKEY);

		this.getBullets().init();
		this.getBalloonsSpawner().init();

		this.getTowers().init();
	}

	/**
	 * 
	 * @return - returning the score of this round
	 */
	public float calculateScore()
	{
		return this.getBalloonsSpawner().getRoundNumber() * 10 + this.getBalloonsSpawner().getSubRoundNumber();
	}

	/**
	 * @return - returning true if game is over
	 */
	public boolean isGameOver()
	{
		return this.getLives() <= 0;
	}

	/**
	 * happan before update
	 */
	public void preUpdate()
	{
		this.getBalloons().updateActiveEntities();
		this.getBullets().updateActiveEntities();
		this.getTowers().updateActiveEntities();
	}

	/**
	 * the main update method
	 */
	public void update()
	{
		this.getBalloonsSpawner().spawnBalloons(deltaTime, this.getBalloons());
		// build towers
		// add money
		this.getBalloons().moveBalloons(this.getDeltaTime(), this.getPath().getSegmentData());
		this.getTowers().update(this.getDeltaTime(), this.getBullets(), this.getBalloons());
		this.getBullets().update(this.getDeltaTime(), this.getBalloons());
		this.lives -= this.getBalloons().checkForPasses(this.getPath().getSegmentData());
	}

	public BalloonsManager getBalloons()
	{
		return balloons;
	}

	public void setBalloons(BalloonsManager balloons)
	{
		this.balloons = balloons;
	}

	public TowersManager getTowers()
	{
		return towers;
	}

	public void setTowers(TowersManager towers)
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

	public float getLives()
	{
		return lives;
	}

	public void setLives(float lives)
	{
		this.lives = lives;
	}
}
