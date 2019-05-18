package com.jstnf.infinitywarps;

import com.jstnf.infinitywarps.commands.iw.CommandEntry;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

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

	public static CommandEntry[] generateEntries(CommandSender sender)
	{
		CommandEntry[] entries = CommandEntry.values();
		ArrayList<CommandEntry> list = new ArrayList<CommandEntry>();
		for (CommandEntry entry : entries)
		{
			if (entry.permission().equals("") || sender.hasPermission(entry.permission()))
			{
				list.add(entry);
			}
		}
		return (CommandEntry[]) list.toArray();
	}
}