package com.jstnf.infinitywarps.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

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
	 * Returns the default ItemStack for the border identifier of the warp selection menu.
	 */
	public static ItemStack borderIdentifier(String identifier, int index)
	{
		ItemStack borderIdentifier = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
		ItemMeta metaRef = borderIdentifier.getItemMeta();
		metaRef.setDisplayName(" ");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("" + ChatColor.DARK_GRAY + identifier);
		lore.add("" + ChatColor.DARK_GRAY + index);
		metaRef.setLore(lore);
		borderIdentifier.setItemMeta(metaRef);
		return borderIdentifier;
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

	/**
	 * Returns the default ItemStack for the 'All Warps' indicator of the warp selection menu.
	 */
	public static ItemStack allWarps()
	{
		ItemStack allWarps = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
		ItemMeta metaRef = allWarps.getItemMeta();
		metaRef.setDisplayName(ChatColor.WHITE + "All Warps");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("" + ChatColor.GRAY + ChatColor.ITALIC + "Click to change category.");
		metaRef.setLore(lore);
		allWarps.setItemMeta(metaRef);
		return allWarps;
	}

	/**
	 * Returns the default ItemStack for the 'Public Warps' indicator of the warp selection menu.
	 */
	public static ItemStack publicWarps()
	{
		ItemStack publicWarps = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		ItemMeta metaRef = publicWarps.getItemMeta();
		metaRef.setDisplayName(ChatColor.GREEN + "Public Warps");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("" + ChatColor.GRAY + ChatColor.ITALIC + "Click to change category.");
		metaRef.setLore(lore);
		publicWarps.setItemMeta(metaRef);
		return publicWarps;
	}

	/**
	 * Returns the default ItemStack for the 'Private Warps' indicator of the warp selection menu.
	 */
	public static ItemStack privateWarps()
	{
		ItemStack privateWarps = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		ItemMeta metaRef = privateWarps.getItemMeta();
		metaRef.setDisplayName(ChatColor.RED + "All Warps");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("" + ChatColor.GRAY + ChatColor.ITALIC + "Click to change category.");
		metaRef.setLore(lore);
		privateWarps.setItemMeta(metaRef);
		return privateWarps;
	}
}