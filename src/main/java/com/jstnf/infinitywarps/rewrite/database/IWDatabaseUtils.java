package com.jstnf.infinitywarps.rewrite.database;

import org.bukkit.ChatColor;

/**
 * Utility classes for interacting with IWDatabaseObjects
 *
 * @author jstnf
 * 18 April 2019
 */
public class IWDatabaseUtils
{
	/**
	 * Given an alias, return its converted database name
	 * @param alias - The Warp or WarpGroup alias
	 * @return The converted database name of the alias
	 */
	public static String convertToDatabaseName(String alias)
	{
		String stripped = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', alias));
		return stripped.replaceAll("[^a-zA-Z0-9]", "_").toLowerCase();
	}
}