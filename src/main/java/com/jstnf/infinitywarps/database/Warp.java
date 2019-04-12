package com.jstnf.infinitywarps.database;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.IWUtils;
import com.jstnf.infinitywarps.config.ConfigPaths;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * InfinityWarps by jstnf
 * Warp
 * Stores loaded information about location, GUI item icons, and private warp information imported from YAML configs.
 *
 * @author jstnf / pokeball92870
 */
public class Warp
{
	private IWMain plugin;

	/* Warp name */
	private String name;
	private String alias;

	/* Warp location */
	private String world;
	private double x;
	private double y;
	private double z;
	private float pitch;
	private float yaw;

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
	 * Constructor used with the '/setwarp <warp>' command or importing from Essentials config.
	 */
	public Warp(String name, String world, double x, double y, double z, float pitch, float yaw, String warpOwnerUUID,
			IWMain plugin)
	{
		this.plugin = plugin;

		this.name = IWUtils.iwFormatString(name);
		this.alias = name;

		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;

		this.ownerUUID = warpOwnerUUID;

		this.isPrivate = false;
		this.addedPlayers = new ArrayList<String>();

		/* No item icon provided, set to default item icon */
		String mat = plugin.getConfigManager().main.getString(ConfigPaths.DEFAULT_WARP_ITEM.getPath(),
				(String) ConfigPaths.DEFAULT_WARP_ITEM.getDefaultValue());
		this.iconMaterial = Material.getMaterial(mat);
		this.iconLore = new ArrayList<String>();

		this.cost = 0;
	}

	/**
	 * Constructor used when constructing a warp from <warp>.yml.
	 */
	public Warp(String name, String world, double x, double y, double z, float pitch, float yaw, boolean isPrivate,
			String warpOwnerUUID, ArrayList<String> players, Material iconMat, ArrayList<String> lore, double cost,
			IWMain plugin)
	{
		this.name = IWUtils.iwFormatString(name);
		this.alias = name;

		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;
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
		boolean spaceAdded = false;
		if (plugin.useEconomy)
		{
			finalLore.add("");
			spaceAdded = true;
			finalLore.add(ChatColor.WHITE + "Cost: ");
			finalLore.add("" + ChatColor.GREEN + cost);
		}
		if (plugin.getConfigManager().main
				.getBoolean(ConfigPaths.SHOW_COORDS.getPath(), (boolean) ConfigPaths.SHOW_COORDS.getDefaultValue()))
		{
			if (spaceAdded)
			{
				finalLore.add("");
			}
			finalLore.add(ChatColor.AQUA + world + " @ " + (int) x + "," + (int) y + "," + (int) z);
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

	public ArrayList<String> getAddedPlayers()
	{
		return addedPlayers;
	}

	public String getWorld()
	{
		return world;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getZ()
	{
		return z;
	}

	public float getPitch()
	{
		return pitch;
	}

	public float getYaw()
	{
		return yaw;
	}
}