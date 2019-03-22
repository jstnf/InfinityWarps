package com.jstnf.infinitywarps;

import com.jstnf.infinitywarps.command.commands.*;
import com.jstnf.infinitywarps.command.commands.infinitywarps.ImportesswarpsSubcommand;
import com.jstnf.infinitywarps.command.commands.infinitywarps.InfinitywarpsCommand;
import com.jstnf.infinitywarps.command.commands.manwarp.ManwarpCommand;
import com.jstnf.infinitywarps.command.commands.warpgroup.*;
import com.jstnf.infinitywarps.data.Warp;
import com.jstnf.infinitywarps.data.WarpGroup;
import com.jstnf.infinitywarps.inventory.DefinitionType;
import com.jstnf.infinitywarps.inventory.InventoryDefinition;
import javafx.util.Pair;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * InfinityWarps by jstnf
 * IWUtils
 * Util methods for all parts of InfinityWarps.
 *
 * @author jstnf / pokeball92870
 */
public class IWUtils
{
	public static final String[] TEMP_INVENTORY_DEFINITION_IDENTIFIERS = { "all_warps", "groups", "ungrouped", "group_",
			"public_warps", "private_warps", "essentials" };

	/**
	 * Register all command and subcommand listeners and executors.
	 */
	public static void setupListeners(IWMain plugin)
	{
		/* Declare command and command listeners */
		InfinitywarpsCommand iwc = new InfinitywarpsCommand(plugin);
		DelwarpCommand delwarpc = new DelwarpCommand(plugin);
		ManwarpCommand manwarpc = new ManwarpCommand(plugin);
		SetwarpCommand setwarpc = new SetwarpCommand(plugin);
		SetpwarpCommand setpwarpc = new SetpwarpCommand(plugin);
		WarpCommand warpc = new WarpCommand(plugin);
		WarpgroupCommand warpgroupc = new WarpgroupCommand(plugin);
		WarplistCommand warplistc = new WarplistCommand(plugin);
		WarpsCommand warpsc = new WarpsCommand(plugin);

		/* Register SubCommands */
		// iw
		ImportesswarpsSubcommand importesswarpssbc = new ImportesswarpsSubcommand();
		iwc.registerSubCommand("importesswarps", importesswarpssbc);

		// warpgroup
		AddWarpgroupSubcommand addwarpgroupsbc = new AddWarpgroupSubcommand();
		InfoWarpgroupSubcommand infowarpgroupsbc = new InfoWarpgroupSubcommand();
		RemoveWarpgroupSubcommand removewarpgroupsbc = new RemoveWarpgroupSubcommand();
		RenameWarpgroupSubcommand renamewarpgroupsbc = new RenameWarpgroupSubcommand();
		warpgroupc.registerSubCommand("add", addwarpgroupsbc);
		warpgroupc.registerSubCommand("info", infowarpgroupsbc);
		warpgroupc.registerSubCommand("remove", removewarpgroupsbc);
		warpgroupc.registerSubCommand("rename", renamewarpgroupsbc);

		/* Set executors */
		plugin.getCommand("infinitywarps").setExecutor(iwc);
		plugin.getCommand("delwarp").setExecutor(delwarpc);
		plugin.getCommand("manwarp").setExecutor(manwarpc);
		plugin.getCommand("setwarp").setExecutor(setwarpc);
		plugin.getCommand("setpwarp").setExecutor(setpwarpc);
		plugin.getCommand("warp").setExecutor(warpc);
		plugin.getCommand("warpgroup").setExecutor(warpgroupc);
		plugin.getCommand("warplist").setExecutor(warplistc);
		plugin.getCommand("warps").setExecutor(warpsc);
	}

	/**
	 * Convert any String into a format used by InfinityWarps.
	 *
	 * @param s - A string
	 * @return String with any alphanumeric characters replaced with _
	 */
	public static String iwFormatString(String s)
	{
		String stripped = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', s));
		return stripped.replaceAll("[^a-zA-Z0-9]", "_").toLowerCase();
	}

	/**
	 * Determine if a String is already present within an array of Strings.
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

	/**
	 * Determine if the given String is an int.
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

	/**
	 * Build the InventoryDefinitions in which the InventoryManager constructs inventories.
	 *
	 * @param warps  - The current loaded, local warps
	 * @param groups - The current loaded, warp groups
	 * @return All InventoryDefinitions from the given warps and warp groups
	 */
	public static ArrayList<InventoryDefinition> defineInventoryDefinitions(HashMap<String, Warp> warps,
			HashMap<String, WarpGroup> groups)
	{
		ArrayList<InventoryDefinition> definitions = new ArrayList<InventoryDefinition>();
		ArrayList<Warp> allWarps = alphaSort(warps);
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

		/* IMPLEMENT WARPGROUP THINGS! */

		definitions.add(all);
		definitions.add(publicWarps);
		definitions.add(privateWarps);
		definitions.add(groupList);
		return definitions;
	}

	/**
	 * Given an identifier, return its corresponding InventoryDefinition.
	 *
	 * @param definitions - The current loaded InventoryDefinitions
	 * @param id          - The identifier for an InventoryDefinition
	 * @return The InventoryDefinition that matches the identifier, null if not found
	 */
	public static InventoryDefinition getDefinition(ArrayList<InventoryDefinition> definitions, String id)
	{
		if (id == null)
		{
			return null;
		}

		for (int i = 0; i < definitions.size(); i++)
		{
			if (id.equalsIgnoreCase(definitions.get(i).identifier))
			{
				return definitions.get(i);
			}
		}
		return null;
	}

	/**
	 * Given an identifier, return its index within the currently loaded InventoryDefinitions.
	 *
	 * @param definitions - The current loaded InventoryDefinitions
	 * @param id          - The identifier for an InventoryDefinition
	 * @return The index of the identifier's corresponding InventoryDefinition, -1 if not found
	 */
	public static int getDefinitionSlot(ArrayList<InventoryDefinition> definitions, String id)
	{
		for (int i = 0; i < definitions.size(); i++)
		{
			if (id.equalsIgnoreCase(definitions.get(i).identifier))
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * Given a HashMap of warps (local warps), return an alphabetized ArrayList of Warps.
	 *
	 * @param warps - The current loaded, local warps
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
				while (index < sorted.size() && sorted.get(index).getWarpName().compareTo(w.getWarpName()) < 0)
				{
					index++;
				}
				sorted.add(index, w);
			}
		}

		return sorted;
	}

	/**
	 * Verify, using item lore, if an inventory is a valid InfinityWarps GUI.
	 *
	 * @param lore - The item lore
	 * @return If the lore is a valid identifier for an InfinityWarps GUI
	 */
	public static Pair<String, Integer> validateInventoryId(ArrayList<String> lore)
	{
		/* Invalid parameters */
		if (lore == null || lore.size() != 2)
		{
			return null;
		}

		String lore0 = ChatColor.stripColor(lore.get(0));
		String lore1 = ChatColor.stripColor(lore.get(1));

		boolean matches = false;
		for (String s : TEMP_INVENTORY_DEFINITION_IDENTIFIERS)
		{
			if (lore.get(0).contains(s))
			{
				matches = true;
			}
		}

		if (matches)
		{
			if (isInt(lore1))
			{
				return new Pair<String, Integer>(lore0, Integer.parseInt(lore1));
			}
		}

		/* A check failed, not a valid inventory ID */
		return null;
	}

	public static boolean listContainsStringIgnoreCase(ArrayList<String> list, String s)
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