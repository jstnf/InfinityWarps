package com.jstnf.infinitywarps.utils.economy;

import com.jstnf.infinitywarps.IWMain;
import org.bukkit.entity.Player;

public class EconomyUtils
{
    /**
     * Checks prerequisites for using the economy feature.
     */
    public static boolean hasPrerequisites(IWMain plugin)
    {
        boolean useEconomy = plugin.configs.main.getBoolean("useEconomy", false);
        boolean vault = true; // to implement
        boolean ecoPlugin = true; // to implement

        return useEconomy && vault && ecoPlugin;
    }

    /**
     * Returns whether or not a player has sufficient funds.
     *
     * @param p - the Player
     * @param amount - amount of money
     * @return true if the player has enough money in their account
     */
    public static boolean hasMoney(Player p, double amount)
    {
        /* TO IMPLEMENT */
        return true;
    }

    /**
     * Takes money from a player's account.
     *
     * @param p - the Player
     * @param amount - amount of money to be taken
     * @return true if money was successfully taken from the account
     */
    public static boolean takeMoney(Player p, double amount)
    {
        if (hasMoney(p, amount))
        {
            /* TO IMPLEMENT */
            return true;
        }
        return false;
    }
}