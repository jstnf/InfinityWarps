package com.jstnf.infinitywarps.data;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.utils.config.SimpleConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;

public class WarpManager
{
	public HashMap<String, Warp> localWarps;
	private IWMain plugin;

	public WarpManager(IWMain plugin)
	{
		this.plugin = plugin;
		localWarps = new HashMap<String, Warp>();
	}

	public boolean importWarps()
	{
		localWarps = new HashMap<String, Warp>();
		try
		{
			SimpleConfig warps = plugin.configs.warps;
			if (warps.getConfigurationSection("warps") != null
					&& warps.getConfigurationSection("warps").getKeys(false) != null)
			{
				plugin.getLogger()
						.info("Found " + warps.getConfigurationSection("warps").getKeys(false).size() + " warps.");

				for (String key : warps.getConfigurationSection("warps").getKeys(false))
				{
					String pre = "warps." + key + ".";

					String alias = warps.getString(pre + "alias");

					/* Import location */
					String world = warps.getString(pre + "location.world");
					double x = warps.getDouble(pre + "location.x");
					double y = warps.getDouble(pre + "location.y");
					double z = warps.getDouble(pre + "location.z");
					float pitch = (float) warps.getDouble(pre + "location.pitch");
					float yaw = (float) warps.getDouble(pre + "location.yaw");

					Location loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
					boolean isPrivate = warps.getBoolean(pre + "isPrivate");
					Material iconMaterial = Material.getMaterial(warps.getString(pre + "material"));
					String ownerUUID = warps.getString(pre + "warpOwner");
					ArrayList<String> addedPlayers = (ArrayList<String>) warps.getList(pre + "addedPlayers");
					ArrayList<String> lore = (ArrayList<String>) warps.getList(pre + "lore");
					double cost = warps.getDouble(pre + "cost");

					Warp newWarp = new Warp(alias, loc, isPrivate, ownerUUID, addedPlayers, iconMaterial, lore, cost);
					localWarps.put(key, newWarp);
				}
			}
			else
			{
				plugin.getLogger().info("Found 0 warps.");
			}
			return true;
		}
		catch (Exception e)
		{
			/* Unable to import warps from warps.yml */
			e.printStackTrace();
			plugin.getLogger().severe("Unable to import warps from config file.");
			plugin.getLogger().severe("You may need to delete and generate a new warps.yml file.");
			plugin.getLogger().severe("InfinityWarps is disabling...");
			plugin.getServer().getPluginManager().disablePlugin(plugin);
			return false;
		}
	}

	public boolean warpExists(String warpName)
	{
		if (localWarps == null)
		{
			return false;
		}
		return localWarps.containsKey(warpName.toLowerCase());
	}

	public boolean removeWarp(String warpName)
	{
		if (warpExists(warpName))
		{
			try
			{
				plugin.configs.warps.set("warps." + warpName.toLowerCase(), null);
				plugin.configs.warps.saveConfig();
				return true;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public void addWarp(Warp warp)
	{
		/* To implement! */
	}
}