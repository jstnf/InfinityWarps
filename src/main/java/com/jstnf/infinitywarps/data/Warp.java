package com.jstnf.infinitywarps.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Warp
{
	private Location loc;
	private ItemStack itemIcon;
	private boolean isPrivate;
	private OfflinePlayer warpOwner;
	private ArrayList<OfflinePlayer> addedPlayers;

	public Warp(Location loc, boolean isPrivate, Player warpOwner, ArrayList<OfflinePlayer> players)
	{
		this.loc = loc;
		this.isPrivate = isPrivate;
		this.warpOwner = Bukkit.getOfflinePlayer(warpOwner.getUniqueId());
		addedPlayers = new ArrayList<OfflinePlayer>();
		for (OfflinePlayer offlinePlayer : players)
		{
			addedPlayers.add(offlinePlayer);
		}

		/* If no item icon provided, set to default item icon */
		itemIcon = new ItemStack(Material.ENDER_PEARL);
	}

	public Warp(Location loc, boolean isPrivate, Player warpOwner, ArrayList<OfflinePlayer> players, ItemStack itemIcon)
	{
		this.loc = loc;
		this.isPrivate = isPrivate;
		this.warpOwner = Bukkit.getOfflinePlayer(warpOwner.getUniqueId());
		addedPlayers = new ArrayList<OfflinePlayer>();
		for (OfflinePlayer offlinePlayer : players)
		{
			addedPlayers.add(offlinePlayer);
		}
		this.itemIcon = itemIcon;
	}

	public ItemStack getItemIcon()
	{
		return itemIcon;
	}
}