package com.jstnf.infinitywarps.rewrite.database.objects;

import com.jstnf.infinitywarps.rewrite.settings.IWSettings;
import com.jstnf.infinitywarps.rewrite.InfinityWarps;
import com.jstnf.infinitywarps.rewrite.database.IWDatabaseObject;
import com.jstnf.infinitywarps.rewrite.gui.IWItemIcon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for an InfinityWarps warp
 *
 * @author jstnf
 * 18 April 2019
 */
public class Warp extends IWDatabaseObject
{
	private InfinityWarps plugin;

	/* Location */
	private String worldName;
	private double x, y, z, pitch, yaw;

	/* Item */
	private IWItemIcon itemIcon;

	/**
	 * Constructor used when setting a new warp or importing a warp from Essentials.
	 */
	public Warp(InfinityWarps plugin, String alias, String worldName, double x, double y, double z, double pitch,
			double yaw)
	{
		super(alias);

		this.plugin = plugin;

		/* Location */
		this.worldName = worldName;
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;

		/* Item */
		itemIcon = new IWItemIcon(plugin.configManager.main.getString(IWSettings.DEFAULT_WARP_ICON.getPath(),
				(String) IWSettings.DEFAULT_WARP_ICON.getDefaultValue()), alias, new ArrayList<String>(), false);
	}

	/**
	 * Constructor used when importing a warp from InfinityWarps config files.
	 */
	public Warp(InfinityWarps plugin, String alias, String worldName, double x, double y, double z, double pitch,
			double yaw, String itemMaterial, String itemName, ArrayList<String> lore, boolean isEnchanted)
	{
		super(alias);

		this.plugin = plugin;

		/* Location */
		this.worldName = worldName;
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;

		/* Item */
		itemIcon = new IWItemIcon(itemMaterial, itemName, lore, isEnchanted);
	}

	/* GET METHODS */

	/**
	 * Get the location of the warp.
	 *
	 * @return the location, null if the world is invalid.
	 */
	public Location getLocation()
	{
		if (Bukkit.getWorld(worldName) == null)
		{
			return null;
		}
		return new Location(Bukkit.getWorld(worldName), x, y, z, (float) yaw, (float) pitch);
	}

	/**
	 * Get the ItemStack to be used in InfinityWarps GUIs.
	 *
	 * @return ItemStack used in InfinityWarps GUIs.
	 */
	public ItemStack getItemStack()
	{
		ItemStack result = itemIcon.getItemStack();

		boolean showCoords = plugin.configManager.main
				.getBoolean(IWSettings.SHOW_COORDS.getPath(), (boolean) IWSettings.SHOW_COORDS.getDefaultValue());
		if (showCoords)
		{
			ItemMeta meta = result.getItemMeta();
			List<String> tempLore = meta.getLore();
			tempLore.add(0, ChatColor.YELLOW + worldName + " @ " + x + "," + y + "," + z);
			meta.setLore(tempLore);
			result.setItemMeta(meta);
		}

		return result;
	}

	/**
	 * Get the world name.
	 *
	 * @return the world name.
	 */
	public String getWorldName()
	{
		return worldName;
	}

	/**
	 * Get the x position.
	 *
	 * @return the x position.
	 */
	public double getX()
	{
		return x;
	}

	/**
	 * Get the y position.
	 *
	 * @return the y position.
	 */
	public double getY()
	{
		return y;
	}

	/**
	 * Get the z position.
	 *
	 * @return the z position.
	 */
	public double getZ()
	{
		return z;
	}

	/**
	 * Get the pitch.
	 *
	 * @return the pitch.
	 */
	public double getPitch()
	{
		return pitch;
	}

	/**
	 * Get the yaw.
	 *
	 * @return the yaw.
	 */
	public double getYaw()
	{
		return yaw;
	}

	/**
	 * Get the item icon.
	 *
	 * @return the item icon.
	 */
	public IWItemIcon getItemIcon()
	{
		return itemIcon;
	}
}