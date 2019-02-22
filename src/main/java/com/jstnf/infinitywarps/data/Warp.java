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
		this.name = name.toLowerCase();
		this.alias = name;

		this.loc = loc;
		this.ownerUUID = warpOwnerUUID;

		this.isPrivate = false;
		addedPlayers = new ArrayList<String>();

		/* No item icon provided, set to default item icon */
		String mat = plugin.configs.main.getString("defaultItemIcon", "ENDER_PEARL");
		iconMaterial = Material.getMaterial(mat);
		iconLore = new ArrayList<String>();

		cost = 0;
	}

	/**
	 * Constructor used when constructing a warp from warps.yml.
	 */
	public Warp(String name, Location loc, boolean isPrivate, String warpOwnerUUID, ArrayList<String> players,
			Material iconMat, ArrayList<String> lore, double cost)
	{
		this.name = name.toLowerCase();
		this.alias = name;

		this.loc = loc;
		this.ownerUUID = warpOwnerUUID;

		this.isPrivate = isPrivate;
		addedPlayers = players;

		this.iconMaterial = iconMat;
		this.iconLore = lore;

		this.cost = cost;
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

		metaRef.setDisplayName(name);

		ArrayList<String> tempLore = new ArrayList<String>();
		if (iconLore != null && iconLore.size() > 0)
		{
			for (int i = 0; i < iconLore.size(); i++)
			{
				tempLore.add(ChatColor.translateAlternateColorCodes('&', iconLore.get(i)));
			}
		}
		/* Replace with config option for useEconomy */
		if (true)
		{
			tempLore.add("Cost: " + cost);
		}
		metaRef.setLore(tempLore);

		result.setItemMeta(metaRef);
		return result;
	}

	/**
	 * Set item icon based on given ItemStack.
	 */
	public boolean setItemIcon(ItemStack newIcon)
	{
		if (newIcon == null)
		{
			return false;
		}

		try
		{
			iconMaterial = newIcon.getType();
			iconLore = (ArrayList) newIcon.getItemMeta().getLore();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public String getWarpName()
	{
		return name;
	}

	public String getWarpAlias()
	{
		return alias;
	}
}