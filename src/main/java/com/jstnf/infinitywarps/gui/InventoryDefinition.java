package com.jstnf.infinitywarps.gui;

import com.jstnf.infinitywarps.data.Warp;
import com.jstnf.infinitywarps.data.WarpGroup;

import java.util.ArrayList;

public class InventoryDefinition
{
	public String identifier;
	public DefinitionType definitionType;
	public ArrayList<Warp> warps;
	public ArrayList<WarpGroup> groups;

	/* Vague warp list definition */
	public InventoryDefinition(String id, DefinitionType invType)
	{
		this.identifier = id;
		this.definitionType = invType;
		this.warps = new ArrayList<Warp>();
		this.groups = null;
	}

	/* Specific warp list definition */
	public InventoryDefinition(String id, DefinitionType invType, ArrayList<Warp> warps)
	{
		this.identifier = id;
		this.definitionType = invType;
		this.warps = warps;
		this.groups = null;
	}

	/* Warp group definition */
	public InventoryDefinition(String id, ArrayList<WarpGroup> groups)
	{
		this.identifier = id;
		this.definitionType = DefinitionType.GROUPS;
		this.warps = null;
		this.groups = groups;
	}

	/**
	 * For use in inventory listener / custom item lore
	 */
	public String getIdentifier()
	{
		return identifier;
	}

	/**
	 * For use in inventory listener / custom item lore
	 */
	public int getBounds()
	{
		int pages = 1;
		if (warps != null)
		{
			pages = warps.size() / 36;
			if (warps.size() % 36 != 0)
			{
				pages += 1;
			}
			if (warps.size() == 0)
			{
				pages = 1;
			}
		}
		return pages;
	}

	/**
	 * For use to display group in inventory
	 */
	public String getWarpGroupName()
	{
		if (definitionType != DefinitionType.WARPGROUP)
		{
			return "";
		}
		return identifier.substring(6);
	}

	public Warp getWarpAt(int index)
	{
		if (index >= warps.size())
		{
			return null;
		}
		else
		{
			return warps.get(index);
		}
	}

}
