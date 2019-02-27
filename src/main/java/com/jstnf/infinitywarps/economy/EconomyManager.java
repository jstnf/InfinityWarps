package com.jstnf.infinitywarps.economy;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

public class EconomyManager
{
	private Economy economy;

	public EconomyManager(Economy economy)
	{
		this.economy = economy;
	}

	/**
	 * Returns whether or not a player has sufficient funds.
	 *
	 * @param p      - the Player
	 * @param amount - amount of money
	 * @return true if the player has enough money in their account
	 */
	public boolean hasMoney(Player p, double amount)
	{
		return economy.has(p, amount);
	}

	/**
	 * Takes money from a player's account.
	 *
	 * @param p      - the Player
	 * @param amount - amount of money to be taken
	 * @return true if money was successfully taken from the account
	 */
	public boolean takeMoney(Player p, double amount)
	{
		if (hasMoney(p, amount))
		{
			economy.withdrawPlayer(p, amount);
			return true;
		}
		return false;
	}
}