package com.jstnf.infinitywarps.data;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.IWUtils;
import com.jstnf.infinitywarps.config.SimpleConfig;
import com.jstnf.infinitywarps.exception.SimilarNameException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

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
				ArrayList<String> lore = (ArrayList<String>) config.getList("lore");

				WarpGroup wg = new WarpGroup(alias, warps, itemMat, lore);
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
		/* TO IMPLEMENT! */

		File warpGroupFolder = new File(plugin.getDataFolder() + File.separator + "groups");
		if (!warpGroupFolder.exists())
		{
			warpGroupFolder.mkdir();
		}

		String fileName = IWUtils.iwFormatString(groupName) + ".yml";
		String[] warpGroups = warpGroupFolder.list();
		if (IWUtils.hasStringConflict(warpGroups, fileName))
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

		WarpGroup wg = new WarpGroup(groupName, new ArrayList<String>(), "BOOK", new ArrayList<String>());
		localWarpGroups.put(IWUtils.iwFormatString(groupName), wg);
		plugin.getInventoryManager().updateInventoryDefinitions(plugin.getWarpManager().getWarps(), localWarpGroups);
	}

	public boolean removeGroup(String groupName)
	{
		/* TO IMPLEMENT! */
		return false;
	}

	public boolean addWarpToGroup(String groupName, String warpName)
	{
		/* TO IMPLEMENT! */
		return false;
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