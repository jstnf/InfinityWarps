package com.jstnf.infinitywarps.data;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.config.SimpleConfig;
import com.jstnf.infinitywarps.IWUtils;
import com.jstnf.infinitywarps.exception.InvalidCostException;
import com.jstnf.infinitywarps.exception.NoSuchWarpException;
import com.jstnf.infinitywarps.exception.SimilarNameException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WarpManager
{
	private HashMap<String, Warp> localWarps;
	private IWMain plugin;

	public WarpManager(IWMain plugin)
	{
		this.plugin = plugin;
		localWarps = new HashMap<String, Warp>();
	}

	/**
	 * Parse through InfinityWarps/warps and import warp data.
	 *
	 * @return if import was successful.
	 */
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
				SimpleConfig config = plugin.getConfigManager().manager.getNewConfig("warps" + File.separator + key);
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
				localWarps.put(IWUtils.convertNonAlphanumeric(alias), w);
			}

			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Add a warp, executed via /setwarp or /setpwarp.
	 *
	 * @param name      - Warp alias
	 * @param cost      - Cost to use the warp
	 * @param p         - Player who executed the command
	 * @param isPrivate - true, if the warp is a private warp
	 * @param players   - List of UUIDs of added users
	 * @throws SimilarNameException - A warp with a similar name exists
	 * @throws InvalidCostException - The cost provided through the plugin does is not a valid decimal
	 */
	public void addWarp(String name, String cost, Player p, boolean isPrivate, ArrayList<String> players)
			throws SimilarNameException, InvalidCostException
	{
		File warpFolder = new File(plugin.getDataFolder() + File.separator + "warps");
		if (!warpFolder.exists())
		{
			warpFolder.mkdir();
		}

		String fileName = IWUtils.convertNonAlphanumeric(name) + ".yml";
		String[] warps = warpFolder.list();
		if (IWUtils.hasStringConflict(warps, fileName))
		{
			throw new SimilarNameException();
		}
		if (!IWUtils.isDouble(cost))
		{
			throw new InvalidCostException();
		}
		double c = Double.parseDouble(cost);
		SimpleConfig warpConfig = plugin.getConfigManager().manager.getNewConfig("warps" + File.separator + fileName);
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
		warpConfig.set("itemIcon", plugin.getConfigManager().main.getString("defaultItemIcon", "ENDER_PEARL"));
		warpConfig.set("lore", new ArrayList<String>());
		warpConfig.set("cost", c);
		warpConfig.saveConfig();

		Warp w = new Warp(name, l, p.getUniqueId().toString(), plugin);
		localWarps.put(IWUtils.convertNonAlphanumeric(name), w);
		plugin.getInventoryManager().updateInventoryDefinitions(localWarps, null);
	}

	/**
	 * Remove a warp, executed via /delwarp.
	 *
	 * @param name - Warp alias
	 * @return if a warp was successfully removed
	 */
	public boolean removeWarp(String name) throws NoSuchWarpException
	{
		/* TO IMPLEMENT! */
		if (9 + 10 == 21)
		{
			throw new NoSuchWarpException(name);
		}
		return false;
	}

	/**
	 * Teleport a player to a warp, executed via /warp or through the GUI.
	 *
	 * @param p - Player who requested the warp
	 * @param w - Warp to teleport to
	 * @return if the Player successfully teleported to the warp
	 */
	public boolean warp(Player p, Warp w)
	{
		/* TO IMPLEMENT */
		return true;
	}

	/**
	 * @return HashMap of all loaded warps and corresponding file names.
	 */
	public HashMap<String, Warp> getWarps()
	{
		return localWarps;
	}
}