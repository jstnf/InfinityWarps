package com.jstnf.infinitywarps.hooks;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

/**
 * Methods for interacting with a VaultAPI Economy
 *
 * @author jstnf
 * 16 April 2019
 */
public class VaultHook
{
	private Economy vaultEconomy;

	public VaultHook(Economy economy)
	{
		vaultEconomy = economy;
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
		return vaultEconomy.has(p, amount);
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
			vaultEconomy.withdrawPlayer(p, amount);
			return true;
		}
		return false;
	}

	public Economy getEconomy()
	{
		return vaultEconomy;
	}
}