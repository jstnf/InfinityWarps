package com.jstnf.infinitywarps.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConstantItemStacks
{
	/**
	 * Returns the default ItemStack for the borders of the warp selection menu.
	 */
	public static ItemStack border()
	{
		ItemStack border = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
		ItemMeta metaRef = border.getItemMeta();
		metaRef.setDisplayName(" ");
		border.setItemMeta(metaRef);
		return border;
	}

	/**
	 * Returns the default ItemStack for the 'previous page' button of the warp selection menu.
	 */
	public static ItemStack previousPage()
	{
		ItemStack prevPage = new ItemStack(Material.FEATHER);
		ItemMeta metaRef = prevPage.getItemMeta();
		metaRef.setDisplayName(ChatColor.WHITE + "Previous Page");
		prevPage.setItemMeta(metaRef);
		return prevPage;
	}

	/**
	 * Returns the default ItemStack for the 'next page' button of the warp selection menu.
	 */
	public static ItemStack nextPage()
	{
		ItemStack nextPage = new ItemStack(Material.FEATHER);
		ItemMeta metaRef = nextPage.getItemMeta();
		metaRef.setDisplayName(ChatColor.WHITE + "Next Page");
		nextPage.setItemMeta(metaRef);
		return nextPage;
	}
}