package com.jstnf.infinitywarps.utils;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.commands.*;
import com.jstnf.infinitywarps.commands.iwtest.IWTestCommand;
import com.jstnf.infinitywarps.commands.iwtest.MylocationSubCommand;
import com.jstnf.infinitywarps.commands.iwtest.TestSetwarpSubCommand;
import com.jstnf.infinitywarps.commands.manwarp.ManwarpCommand;
import com.jstnf.infinitywarps.data.Warp;
import com.jstnf.infinitywarps.data.WarpGroup;
import com.jstnf.infinitywarps.gui.DefinitionType;
import com.jstnf.infinitywarps.gui.InventoryDefinition;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandUtils
{
	/**
	 * Register all command and subcommand listeners and executors.
	 */
	public static void setupListeners(IWMain plugin)
	{
		plugin.getLogger().info("Hooking commands...");

		/* Declare commands and command listeners */
		InfinitywarpsCommand iwc = new InfinitywarpsCommand(plugin);
		WarpCommand warpc = new WarpCommand(plugin);
		SetwarpCommand setwarpc = new SetwarpCommand(plugin);
		DelwarpCommand delwarpc = new DelwarpCommand(plugin);
		WarpsCommand warpsc = new WarpsCommand(plugin);
		ManwarpCommand manwarpc = new ManwarpCommand(plugin);
		SetpwarpCommand setpwarpc = new SetpwarpCommand(plugin);

		/* Register SubCommands */


		/* Set executors */
		plugin.getCommand("infinitywarps").setExecutor(iwc);
		plugin.getCommand("warp").setExecutor(warpc);
		plugin.getCommand("setwarp").setExecutor(setwarpc);
		plugin.getCommand("delwarp").setExecutor(delwarpc);
		plugin.getCommand("warps").setExecutor(warpsc);
		plugin.getCommand("manwarp").setExecutor(manwarpc);
		plugin.getCommand("setpwarp").setExecutor(setpwarpc);
	}

	/**
	 * Register /iwtest command listener for testing and debug purposes.
	 */
	public static void setupTestListener(IWMain plugin)
	{
		/* Declare commands and command listeners */
		IWTestCommand iwtestc = new IWTestCommand(plugin);

		/* Register SubCommands */
		iwtestc.registerSubCommand("mylocation", new MylocationSubCommand());
		iwtestc.registerSubCommand("setwarp", new TestSetwarpSubCommand());

		/* Set executors */
		plugin.getCommand("iwtest").setExecutor(iwtestc);
	}

	/**
	 * Convert any non-alphanumeric characters to _ and return.
	 *
	 * @param s - A string
	 * @return String with any alphanumeric characters replaced with _
	 */
	public static String convertNonAlphanumeric(String s)
	{
		String stripped = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', s));
		return stripped.replaceAll("[^a-zA-Z0-9]", "_");
	}

	/**
	 * Determine if a String is already present in an array of Strings.
	 */
	public static boolean hasStringConflict(String[] strings, String s)
	{
		boolean conflict = false;
		int index = 0;
		while (index < strings.length && !conflict)
		{
			conflict = strings[index].equalsIgnoreCase(s);
			index++;
		}
		return conflict;
	}

	/**
	 * Determine if the given String is a double.
	 */
	public static boolean isDouble(String s)
	{
		try
		{
			Double.parseDouble(s);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public static ArrayList<InventoryDefinition> defineInventoryDefinitions(HashMap<String, Warp> warps,
			ArrayList<WarpGroup> groups)
	{
		ArrayList<InventoryDefinition> definitions = new ArrayList<InventoryDefinition>();
		ArrayList<Warp> allWarps = ListUtils.alphaSort(warps);
		InventoryDefinition all = new InventoryDefinition("all_warps", DefinitionType.WARPS, allWarps);
		InventoryDefinition publicWarps = new InventoryDefinition("public_warps", DefinitionType.WARPS);
		InventoryDefinition privateWarps = new InventoryDefinition("private_warps", DefinitionType.WARPS);
		InventoryDefinition groupList = new InventoryDefinition("groups", DefinitionType.GROUPS);

		for (Warp w : allWarps)
		{
			if (w.isPrivate())
			{
				privateWarps.warps.add(w);
			}
			else
			{
				publicWarps.warps.add(w);
			}
		}

		/* IMPLEMENT GROUP THINGS! */

		definitions.add(all);
		definitions.add(publicWarps);
		definitions.add(privateWarps);
		definitions.add(groupList);
		return definitions;
	}

	public static InventoryDefinition getDefinition(ArrayList<InventoryDefinition> definitions, String id)
	{
		for (int i = 0; i < definitions.size(); i++)
		{
			if (id.equalsIgnoreCase(definitions.get(i).identifier))
			{
				return definitions.get(i);
			}
		}
		return null;
	}
}