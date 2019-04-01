package com.jstnf.infinitywarps.data;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.IWUtils;
import com.jstnf.infinitywarps.config.log_out.SimpleConfig;
import com.jstnf.infinitywarps.exception.NoSuchWarpException;
import com.jstnf.infinitywarps.exception.NoSuchWarpGroupException;
import com.jstnf.infinitywarps.exception.SimilarNameException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * InfinityWarps by jstnf
 * WarpGroupManager
 * Stores loaded warp groups from configuration files for use in inventory constructing.
 *
 * @author jstnf / pokeball92870
 */
public class WarpGroupManager
{
	private HashMap<String, WarpGroup> localWarpGroups;
	private IWMain plugin;

	public WarpGroupManager(IWMain plugin)
	{
		this.plugin = plugin;
		localWarpGroups = new HashMap<String, WarpGroup>();
	}

	/**
	 * Parse through InfinityWarps/groups and import warp group data.
	 *
	 * @return if import was successful.
	 */
	public boolean importGroups()
	{
		try
		{
			File groupsFolder = new File(plugin.getDataFolder() + File.separator + "groups");
			if (!groupsFolder.exists())
			{
				groupsFolder.mkdir();
			}
			String[] keys = groupsFolder.list();

			for (String key : keys)
			{
				SimpleConfig config = plugin.getConfigManager().manager.getNewConfig("groups" + File.separator + key);
				String alias = config.getString("alias");
				ArrayList<String> warps = (ArrayList<String>) config.getList("warps");
				String itemMat = config.getString("itemIcon");
				ArrayList<String> lore = (ArrayList<String>) config.getList("itemLore");

				WarpGroup wg = new WarpGroup(alias, warps, itemMat, lore, plugin);
				localWarpGroups.put(IWUtils.iwFormatString(alias), wg);
			}

			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public void addGroup(String groupName) throws SimilarNameException
	{
		File warpGroupFolder = new File(plugin.getDataFolder() + File.separator + "groups");
		if (!warpGroupFolder.exists())
		{
			warpGroupFolder.mkdir();
		}

		String fileName = IWUtils.iwFormatString(groupName) + ".yml";
		String[] warpGroups = warpGroupFolder.list();
		if (IWUtils.hasStringConflictArray(warpGroups, fileName))
		{
			throw new SimilarNameException();
		}

		SimpleConfig warpGroupConfig = plugin.getConfigManager().manager
				.getNewConfig("groups" + File.separator + fileName);
		warpGroupConfig.set("alias", groupName);
		warpGroupConfig.set("warps", new ArrayList<String>());
		warpGroupConfig.set("itemIcon", "BOOK");
		warpGroupConfig.set("itemDataValue", 0);
		warpGroupConfig.set("itemLore", new ArrayList<String>());
		warpGroupConfig.saveConfig();

		WarpGroup wg = new WarpGroup(groupName, new ArrayList<String>(), "BOOK", new ArrayList<String>(), plugin);
		localWarpGroups.put(IWUtils.iwFormatString(groupName), wg);
		plugin.getInventoryManager().updateInventoryDefinitions(plugin.getWarpManager().getWarps(), localWarpGroups);
	}

	public boolean removeGroup(String groupName)
	{
		/* TO IMPLEMENT! */
		return false;
	}

	public void addWarpToGroup(String groupName, String warpName)
			throws NoSuchWarpGroupException, SimilarNameException, NoSuchWarpException
	{
		String gn = IWUtils.iwFormatString(groupName);
		String wn = IWUtils.iwFormatString(warpName);

		WarpGroup wg = null;

		for (WarpGroup g : localWarpGroups.values())
		{
			if (g.getGroupName().equals(gn))
			{
				wg = g;
				break;
			}
		}

		if (wg != null)
		{
			/* Check if warp is already in the warp group */
			if (wg.containsWarp(wn))
			{
				try
				{
					wg.addWarp(wn);

					/* Attempt to write changes to warp group file */
					try
					{
						SimpleConfig config = plugin.getConfigManager().manager
								.getNewConfig("groups" + File.separator + gn + ".yml");
						config.set("warps", wg.getWarps());
						config.saveConfig();
					}
					/* If cannot find the file, write a new one */
					catch (Exception e)
					{
						File warpGroupFolder = new File(plugin.getDataFolder() + File.separator + "groups");
						if (!warpGroupFolder.exists())
						{
							warpGroupFolder.mkdir();
						}

						String fileName = gn + ".yml";

						SimpleConfig warpGroupConfig = plugin.getConfigManager().manager
								.getNewConfig("groups" + File.separator + fileName);
						warpGroupConfig.set("alias", wg.getGroupAlias());
						warpGroupConfig.set("warps", wg.getWarps());
						warpGroupConfig.set("itemIcon", wg.getIconMaterial());
						warpGroupConfig.set("itemDataValue", wg.getIconDataValue());
						warpGroupConfig.set("itemLore", wg.getIconLore());
						warpGroupConfig.saveConfig();
					}

					plugin.getInventoryManager()
							.updateInventoryDefinitions(plugin.getWarpManager().getWarps(), localWarpGroups);
				}
				/* The warp doesn't exist */
				catch (NoSuchWarpException e)
				{
					throw e;
				}
			}
			else
			{
				throw new SimilarNameException();
			}
		}
		else
		{
			throw new NoSuchWarpGroupException();
		}
	}

	public boolean renameWarpInGroups(String warpName, String newWarpName)
	{
		/* TO IMPLEMENT! */
		return false;
	}

	public boolean removeWarpFromGroup(String groupName, String warpName)
	{
		/* TO IMPLEMENT! */
		return false;
	}

	public HashMap<String, WarpGroup> getWarpGroups()
	{
		return localWarpGroups;
	}
}