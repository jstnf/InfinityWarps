package com.jstnf.infinitywarps.utils;

import com.jstnf.infinitywarps.data.Warp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ListUtils
{
	/**
	 * Given a HashMap of warps (local warps), return an alphabetized ArrayList of Warps
	 *
	 * @param warps - local warps
	 * @return Alphabetized ArrayList of warps
	 */
	public static ArrayList<Warp> alphaSort(HashMap<String, Warp> warps)
	{
		if (warps == null || warps.size() == 0)
		{
			return new ArrayList<Warp>();
		}

		ArrayList<Warp> sorted = new ArrayList<Warp>();
		Iterator<Warp> it = warps.values().iterator();

		while (it.hasNext())
		{
			Warp w = it.next();
			if (sorted.size() == 0)
			{
				sorted.add(w);
			}
			else
			{
				int index = 0;
				while (index < sorted.size() && sorted.get(index).getWarpAlias().compareTo(w.getWarpAlias()) < 0)
				{
					index++;
				}
				sorted.add(index, w);
			}
		}

		return sorted;
	}

	public static boolean hasStringIgnoreCase(ArrayList<String> list, String s)
	{
		if (list == null || s == null)
		{
			return false;
		}
		for (String str : list)
		{
			if (str.equalsIgnoreCase(s))
			{
				return true;
			}
		}
		return false;
	}
}