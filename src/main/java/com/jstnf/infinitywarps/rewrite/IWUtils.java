package com.jstnf.infinitywarps.rewrite;

/**
 * A collection of util methods for InfinityWarps
 *
 * @author jstnf
 * 18 May 2019
 */
public class IWUtils
{
	/**
	 * Determine if the given String can be parsed into an Integer
	 *
	 * @param s - A string
	 * @return true, if s can be parsed into an Integer
	 */
	public static boolean isInt(String s)
	{
		try
		{
			Integer.parseInt(s);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}