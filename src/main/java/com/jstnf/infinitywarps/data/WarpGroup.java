package com.jstnf.infinitywarps.data;

import com.jstnf.infinitywarps.IWUtils;
import org.bukkit.Material;

import java.util.ArrayList;

/**
 * InfinityWarps by jstnf
 * WarpGroup
 * Locally stores information about warp groups, or collections of warps for use in the GUI imported from YAML configs.
 *
 * @author jstnf / pokeball92870
 */
public class WarpGroup
{
	private String name;
	private String alias;

	private ArrayList<String> warps;

	/* Item icon vars */
	private Material iconMaterial;
	private ArrayList<String> iconLore;

	public WarpGroup(String alias)
	{
		this.name = IWUtils.iwFormatString(alias);
		this.alias = alias;

		this.warps = new ArrayList<String>();

		this.iconMaterial = Material.BOOK;
		this.iconLore = new ArrayList<String>();
	}

	public WarpGroup(String alias, ArrayList<String> warps, String material, ArrayList<String> lore)
	{
		this.name = IWUtils.iwFormatString(alias);
		this.alias = alias;

		this.warps = warps;

		try
		{
			this.iconMaterial = Material.getMaterial(material);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.iconMaterial = Material.BOOK;
		}
		this.iconLore = lore;
	}

	public boolean addWarp(String warpName)
	{
		/* TO IMPLEMENT */
		/* need to check if the warp added is valid */
		return false;
	}

	public boolean removeWarp(String warpName)
	{
		boolean contains = IWUtils.listContainsStringIgnoreCase(warps, warpName);
		if (contains)
		{
			for (int i = 0; i < warps.size(); i++)
			{
				if (warps.get(i).equalsIgnoreCase(warpName))
				{
					warps.remove(i);
					return true;
				}
			}
		}
		return false;
	}

	public String getGroupName()
	{
		return name;
	}

	public String getGroupAlias()
	{
		return alias;
	}

	public ArrayList<String> getWarps()
	{
		return warps;
	}
}