package com.jstnf.infinitywarps.inventory;

import com.jstnf.infinitywarps.database.Warp;
import com.jstnf.infinitywarps.database.WarpGroup;

import java.util.ArrayList;

/**
 * InfinityWarps by jstnf
 * InventoryDefinition
 * Definitions for use in constructing and listening to inventories.
 *
 * @author jstnf / pokeball92870
 */
public class InventoryDefinition
{
	public DefinitionType definitionType;
	private String identifier;
	private ArrayList<Warp> warps;
	private ArrayList<WarpGroup> groups;

	/* Vague warp list definition */
	public InventoryDefinition(String id, DefinitionType invType)
	{
		this.identifier = id;
		this.definitionType = invType;
		this.warps = new ArrayList<Warp>();
		this.groups = new ArrayList<WarpGroup>();
	}

	/* Specific constructor for either all warps or all groups */
	public InventoryDefinition(String id, ArrayList elements)
	{
		this.identifier = id;
		if (id.equalsIgnoreCase("all_warps"))
		{
			this.definitionType = DefinitionType.WARPS;
			this.warps = elements;
			this.groups = new ArrayList<WarpGroup>();
		}
		else
		{
			this.definitionType = DefinitionType.GROUPS;
			this.warps = new ArrayList<Warp>();
			this.groups = elements;
		}
	}

	public InventoryDefinition(String id, ArrayList<Warp> warps, ArrayList<WarpGroup> groups)
	{
		this.identifier = id;
		this.definitionType = DefinitionType.ALL;
		this.warps = warps;
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
		int size = 0;
		if (warps != null)
		{
			size += warps.size();
		}
		if (groups != null)
		{
			size += groups.size();
		}
		pages = size / 36;
		if (size % 36 != 0)
		{
			pages += 1;
		}
		if (size == 0)
		{
			pages = 1;
		}
		return pages;
	}

	/**
	 * For use to display group in inventory
	 */
	public String getWarpGroupName()
	{
		if (definitionType != DefinitionType.WARP_GROUP)
		{
			return "";
		}
		return identifier.substring(6);
	}

	public ArrayList<Object> getCompleteElementList()
	{
		ArrayList<Object> elements = new ArrayList<Object>();

		for (WarpGroup wg : groups)
		{
			elements.add(wg);
		}
		for (Warp w : warps)
		{
			elements.add(w);
		}

		return elements;
	}

	public void addWarp(Warp w)
	{
		warps.add(w);
	}

	public void addGroup(WarpGroup wg)
	{
		groups.add(wg);
	}
}