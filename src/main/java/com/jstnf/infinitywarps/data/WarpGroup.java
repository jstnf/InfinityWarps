package com.jstnf.infinitywarps.data;

import java.util.ArrayList;

public class WarpGroup
{
	private ArrayList<String> warps;
	private String groupName;

	public WarpGroup(String name)
	{
		this.groupName = name;
		this.warps = new ArrayList<String>();
	}

	public WarpGroup(String name, ArrayList<String> warps)
	{
		this.groupName = name;
		this.warps = warps;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public ArrayList<String> getWarps()
	{
		return warps;
	}
}
