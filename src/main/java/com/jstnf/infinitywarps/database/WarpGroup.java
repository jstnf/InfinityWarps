package com.jstnf.infinitywarps.database;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.IWUtils;
import com.jstnf.infinitywarps.exception.NoSuchWarpException;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * InfinityWarps by jstnf
 * WarpGroup
 * Stores loaded information about warp groups, or collections of warps for use in the GUI imported from YAML configs.
 *
 * @author jstnf / pokeball92870
 */
public class WarpGroup
{
	private IWMain plugin;

	private String name;
	private String alias;

	private ArrayList<String> warps;

	/* Item icon vars */
	private String iconMaterial;
	private int iconDataValue;
	private ArrayList<String> iconLore;

	public WarpGroup(String alias, IWMain plugin)
	{
		this.plugin = plugin;

		this.name = IWUtils.iwFormatString(alias);
		this.alias = alias;

		this.warps = new ArrayList<String>();

		this.iconMaterial = "BOOK";
		this.iconLore = new ArrayList<String>();
	}

	/**
	 * Constructor used when constructing a warp group from a <warpgroup>.yml.
	 */
	public WarpGroup(String alias, ArrayList<String> warps, String material, ArrayList<String> lore, IWMain plugin)
	{
		this.plugin = plugin;

		this.name = IWUtils.iwFormatString(alias);
		this.alias = alias;

		this.warps = warps;

		this.iconMaterial = material;
		this.iconLore = lore;
	}

	public void addWarp(String warpName) throws NoSuchWarpException
	{
		if (plugin.getWarpManager().isValidWarp(warpName))
		{
			warps.add(warpName);
		}
		else
		{
			throw new NoSuchWarpException(warpName);
		}
	}

	public void removeWarp(String warpName) throws NoSuchWarpException
	{
		String wn = IWUtils.iwFormatString(warpName);

		if (containsWarp(wn))
		{
			for (int i = 0; i < warps.size(); i++)
			{
				if (warps.get(i).equalsIgnoreCase(wn))
				{
					warps.remove(i);
					return;
				}
			}
		}
		else
		{
			throw new NoSuchWarpException(warpName);
		}
	}

	/**
	 * Returns true if a warp with a similar name is found.
	 *
	 * @param warpName - the name of the warp, should be formatted already with IWUtils
	 * @return if the warp group contains the specified warp.
	 */
	public boolean containsWarp(String warpName)
	{
		return IWUtils.hasStringConflictArrayList(warps, warpName);
	}

	public ItemStack getItemIcon()
	{
		ItemStack result = new ItemStack(Material.BOOK);

		try
		{
			Material m = Material.getMaterial(iconMaterial);
			result = new ItemStack(m);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		ItemMeta metaRef = result.getItemMeta();

		String displayName = ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', alias);
		metaRef.setDisplayName(displayName);

		ArrayList<String> finalLore = new ArrayList<String>();
		finalLore.add("" + ChatColor.GRAY + ChatColor.ITALIC + "Warp Group");

		if (iconLore != null && iconLore.size() > 0)
		{
			for (int i = 0; i < iconLore.size(); i++)
			{
				finalLore.add(ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', iconLore.get(i)));
			}
		}

		metaRef.setLore(finalLore);
		result.setItemMeta(metaRef);

		return result;
	}

	public String getGroupName()
	{
		return name;
	}

	public String getGroupAlias()
	{
		return alias;
	}

	public String getIconMaterial()
	{
		return this.iconMaterial;
	}

	/**
	 * Exclusively for use in Minecraft versions 1.12 or before.
	 *
	 * @return the data value for the item Material to be used in inventories.
	 */
	public int getIconDataValue()
	{
		return this.iconDataValue;
	}

	public ArrayList<String> getIconLore()
	{
		return this.iconLore;
	}

	public ArrayList<String> getWarps()
	{
		return warps;
	}
}