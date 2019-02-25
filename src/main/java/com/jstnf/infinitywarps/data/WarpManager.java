package com.jstnf.infinitywarps.data;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.exception.InvalidCostException;
import com.jstnf.infinitywarps.exception.SimilarNameException;
import com.jstnf.infinitywarps.utils.CommandUtils;
import com.jstnf.infinitywarps.utils.config.SimpleConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.io.File;
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
		try
		{
			File warpFolder = new File(plugin.getDataFolder() + File.separator + "warps");
			if (!warpFolder.exists())
			{
				warpFolder.mkdir();
			}
			String[] keys = warpFolder.list();

			for (String key : keys)
			{
				SimpleConfig config = plugin.configs.manager.getNewConfig("warps" + File.separator + key);
				String alias = config.getString("alias");
				String worldName = config.getString("location.world");
				double x = config.getDouble("location.x");
				double y = config.getDouble("location.y");
				double z = config.getDouble("location.z");
				float pitch = (float) config.getDouble("location.pitch");
				float yaw = (float) config.getDouble("location.yaw");
				Location l = new Location(Bukkit.getServer().getWorld(worldName), x, y, z, pitch, yaw);
				boolean isPrivate = config.getBoolean("isPrivate");
				String ownerUUID = config.getString("warpOwner");
				ArrayList<String> addedPlayers = (ArrayList<String>) config.getList("added");
				String itemMat = config.getString("itemIcon");
				ArrayList<String> lore = (ArrayList<String>) config.getList("lore");
				double cost = config.getDouble("cost");

				Warp w = new Warp(alias, l, isPrivate, ownerUUID, addedPlayers, Material.getMaterial(itemMat), lore,
						cost, plugin);
				localWarps.put(CommandUtils.convertNonAlphanumeric(alias), w);
			}

			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public void addWarp(String name, String cost, Player p, boolean isPrivate, ArrayList<String> players)
			throws SimilarNameException, InvalidCostException
	{
		File warpFolder = new File(plugin.getDataFolder() + File.separator + "warps");
		if (!warpFolder.exists())
		{
			warpFolder.mkdir();
		}

		String fileName = CommandUtils.convertNonAlphanumeric(name) + ".yml";
		String[] warps = warpFolder.list();
		if (CommandUtils.hasStringConflict(warps, fileName))
		{
			throw new SimilarNameException();
		}
		if (!CommandUtils.isDouble(cost))
		{
			throw new InvalidCostException();
		}
		double c = Double.parseDouble(cost);
		SimpleConfig warpConfig = plugin.configs.manager.getNewConfig("warps" + File.separator + fileName);
		warpConfig.set("alias", name);
		warpConfig.createSection("location");
		warpConfig.set("location.world", p.getWorld().getName());
		Location l = p.getLocation();
		warpConfig.set("location.x", l.getX());
		warpConfig.set("location.y", l.getY());
		warpConfig.set("location.z", l.getZ());
		warpConfig.set("location.pitch", (double) l.getPitch());
		warpConfig.set("location.yaw", (double) l.getYaw());
		warpConfig.set("isPrivate", isPrivate);
		warpConfig.set("warpOwner", p.getUniqueId().toString());
		warpConfig.set("added", players);
		warpConfig.set("itemIcon", plugin.configs.main.getString("defaultItemIcon", "ENDER_PEARL"));
		warpConfig.set("lore", new ArrayList<String>());
		warpConfig.set("cost", c);
		warpConfig.saveConfig();

		Warp w = new Warp(name, l, p.getUniqueId().toString(), plugin);
		localWarps.put(CommandUtils.convertNonAlphanumeric(name), w);
		plugin.gui2.updateInventoryDefinitions(localWarps, null);
	}

	public boolean removeWarp(String name)
	{
		/* TO IMPLEMENT! */
		return false;
	}
}