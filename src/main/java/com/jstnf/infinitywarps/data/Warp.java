package com.jstnf.infinitywarps.data;

import com.jstnf.infinitywarps.IWMain;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Warp
{
	private IWMain plugin;

	/* Warp name */
	private String name;
	private String alias;

	/* Warp location */
	private Location loc;

	/* Item icon vars */
	private Material iconMaterial;
	private ArrayList<String> iconLore;

	/* Private warp vars */
	private boolean isPrivate;
	private String ownerUUID;
	private ArrayList<String> addedPlayers;

	/* Economy - applicable only with useEconomy set to true */
	private double cost;

	/**
	 * Constructor used with '/setwarp <warp>' command.
	 */
	public Warp(String name, Location loc, String warpOwnerUUID, IWMain plugin)
	{
		this.plugin = plugin;

		this.name = name.toLowerCase();
		this.alias = name;

		this.loc = loc;
		this.ownerUUID = warpOwnerUUID;

		this.isPrivate = false;
		this.addedPlayers = new ArrayList<String>();

		/* No item icon provided, set to default item icon */
		String mat = plugin.getConfigManager().main.getString("defaultItemIcon", "ENDER_PEARL");
		this.iconMaterial = Material.getMaterial(mat);
		this.iconLore = new ArrayList<String>();

		this.cost = 0;
	}

	/**
	 * Constructor used when constructing a warp from <warp>.yml.
	 */
	public Warp(String name, Location loc, boolean isPrivate, String warpOwnerUUID, ArrayList<String> players,
			Material iconMat, ArrayList<String> lore, double cost, IWMain plugin)
	{
		this.name = name.toLowerCase();
		this.alias = name;

		this.loc = loc;
		this.ownerUUID = warpOwnerUUID;

		this.isPrivate = isPrivate;
		this.addedPlayers = players;

		this.iconMaterial = iconMat;
		this.iconLore = lore;

		this.cost = cost;

		this.plugin = plugin;
	}

	/**
	 * Returns the item icon from the current Material and lore.
	 */
	public ItemStack getItemIcon()
	{
		ItemStack result = new ItemStack(Material.ENDER_PEARL);
		if (iconMaterial != null)
		{
			result = new ItemStack(iconMaterial);
		}
		ItemMeta metaRef = result.getItemMeta();

		String displayName = ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', alias);
		metaRef.setDisplayName(displayName);

		ArrayList<String> finalLore = new ArrayList<String>();
		if (isPrivate)
		{
			finalLore.add("" + ChatColor.RED + ChatColor.ITALIC + "Private Warp");
		}
		if (iconLore != null && iconLore.size() > 0)
		{
			for (int i = 0; i < iconLore.size(); i++)
			{
				finalLore.add(ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', iconLore.get(i)));
			}
		}
		/* Replace with config option for useEconomy */
		if (plugin.useEconomy)
		{
			finalLore.add("");
			finalLore.add(ChatColor.WHITE + "Cost: ");
			finalLore.add("" + ChatColor.GREEN + cost);
		}
		metaRef.setLore(finalLore);

		result.setItemMeta(metaRef);
		return result;
	}

	/**
	 * Set item icon based on given ItemStack.
	 */
	public boolean setItemIcon(ItemStack newIcon, boolean replaceItemLore)
	{
		if (newIcon == null)
		{
			return false;
		}

		try
		{
			iconMaterial = newIcon.getType();
			if (replaceItemLore)
			{
				iconLore = (ArrayList<String>) newIcon.getItemMeta().getLore();
			}
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Set warp cost.
	 */
	public void setCost(double newCost)
	{
		cost = newCost;
	}

	public String getWarpName()
	{
		return name;
	}

	public String getWarpAlias()
	{
		return alias;
	}

	public boolean isPrivate()
	{
		return isPrivate;
	}
}