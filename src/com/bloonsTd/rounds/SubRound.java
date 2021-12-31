// keeping track on the amount of a specific balloon that need to be spawn iin a specific round
// 30-12-2021
package com.bloonsTd.rounds;

public class SubRound
{
	private int amount;
	private int type;
	private boolean regrowth;
	private boolean camo;

	/**
	 * 
	 * @param amount   - the number of balloons from that type that need to be
	 *                 spawned
	 * @param type     - the type of the balloon
	 * @param regrowth - is a regrowth balloon
	 * @param camo     - is a camo balloon
	 */
	public SubRound(int amount, int type, boolean regrowth, boolean camo)
	{
		this.setAmount(amount);
		this.setType(type);
		this.setRegrowth(regrowth);
		this.setCamo(camo);
	}

	public int getAmount()
	{
		return amount;
	}
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public boolean isRegrowth()
	{
		return regrowth;
	}
	public void setRegrowth(boolean regrowth)
	{
		this.regrowth = regrowth;
	}
	public boolean isCamo()
	{
		return camo;
	}
	public void setCamo(boolean camo)
	{
		this.camo = camo;
	}


}
