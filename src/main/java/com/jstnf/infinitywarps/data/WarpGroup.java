package com.jstnf.infinitywarps.data;

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

	public WarpGroup(String name)
	{
		this.name = name.toLowerCase();
		this.alias = name;

		this.warps = new ArrayList<String>();
	}

	public WarpGroup(String name, ArrayList<String> warps)
	{
		this.name = name.toLowerCase();
		this.alias = name;

		this.warps = warps;
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