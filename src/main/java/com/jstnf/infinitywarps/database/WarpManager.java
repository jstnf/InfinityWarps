package com.jstnf.infinitywarps.database;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.IWUtils;
import com.jstnf.infinitywarps.config.ConfigPaths;
import com.jstnf.infinitywarps.config.log_out.SimpleConfig;
import com.jstnf.infinitywarps.exception.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * InfinityWarps by jstnf
 * WarpManager
 * Stores loaded warps from configuration files and manages player teleporting.
 *
 * @author jstnf / pokeball92870
 */
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

			File warpGroupFolder = new File(plugin.getDataFolder() + File.separator + "groups");
			if (!warpGroupFolder.exists())
			{
				warpGroupFolder.mkdir();
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
				boolean isPrivate = config.getBoolean("isPrivate");
				String ownerUUID = config.getString("warpOwner");
				ArrayList<String> addedPlayers = (ArrayList<String>) config.getList("added");
				String itemMat = config.getString("itemIcon");
				ArrayList<String> lore = (ArrayList<String>) config.getList("lore");
				double cost = config.getDouble("cost");

				Warp w = new Warp(alias, worldName, x, y, z, pitch, yaw, isPrivate, ownerUUID, addedPlayers,
						Material.getMaterial(itemMat), lore, cost, plugin);
				localWarps.put(IWUtils.iwFormatString(alias), w);
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
	 * Import data from an Essentials warp YML config.
	 *
	 * @param essConfig - The Essentials warp config
	 * @return if import was successful.
	 */
	public boolean importFromEssentialsConfig(FileConfiguration essConfig, boolean doGroupDivision, String groupDivider)
	{
		try
		{
			File warpFolder = new File(plugin.getDataFolder() + File.separator + "warps");
			if (!warpFolder.exists())
			{
				warpFolder.mkdir();
			}

			File warpGroupFolder = new File(plugin.getDataFolder() + File.separator + "groups");
			if (!warpGroupFolder.exists())
			{
				warpGroupFolder.mkdir();
			}

			String alias = essConfig.getString("name");
			String world = essConfig.getString("world");
			double x = essConfig.getDouble("x");
			double y = essConfig.getDouble("y");
			double z = essConfig.getDouble("z");
			double yaw = essConfig.getDouble("yaw");
			double pitch = essConfig.getDouble("pitch");
			String ownerUUID = essConfig.getString("lastowner");

			String fileName = IWUtils.iwFormatString(alias) + ".yml";
			String[] warps = warpFolder.list();
			if (IWUtils.hasStringConflictArray(warps, fileName))
			{
				plugin.getLogger()
						.warning("Warp " + alias + " was not imported because a warp with a similar name exists.");
				return false; /* Warp import failed, similar name */
			}
			SimpleConfig warpConfig = plugin.getConfigManager().manager
					.getNewConfig("warps" + File.separator + fileName);
			warpConfig.set("alias", alias);
			warpConfig.createSection("location");
			warpConfig.set("location.world", world);
			warpConfig.set("location.x", x);
			warpConfig.set("location.y", y);
			warpConfig.set("location.z", z);
			warpConfig.set("location.pitch", pitch);
			warpConfig.set("location.yaw", yaw);
			warpConfig.set("isPrivate", false);
			warpConfig.set("warpOwner", ownerUUID);
			warpConfig.set("added", new ArrayList<String>());
			warpConfig.set("itemIcon", plugin.getConfigManager().main.getString(ConfigPaths.DEFAULT_WARP_ITEM.getPath(),
					(String) ConfigPaths.DEFAULT_WARP_ITEM.getDefaultValue()));
			warpConfig.set("lore", new ArrayList<String>());
			warpConfig.set("cost", 0.0);
			warpConfig.saveConfig();

			Warp w = new Warp(alias, world, x, y, z, (float) pitch, (float) yaw, ownerUUID, plugin);
			localWarps.put(IWUtils.iwFormatString(alias), w);
			plugin.getInventoryManager()
					.updateInventoryDefinitions(localWarps, plugin.getWarpGroupManager().getWarpGroups());
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

		String fileName = IWUtils.iwFormatString(name) + ".yml";
		String[] warps = warpFolder.list();
		if (IWUtils.hasStringConflictArray(warps, fileName))
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
		warpConfig.set("itemIcon", plugin.getConfigManager().main.getString(ConfigPaths.DEFAULT_WARP_ITEM.getPath(),
				(String) ConfigPaths.DEFAULT_WARP_ITEM.getDefaultValue()));
		warpConfig.set("lore", new ArrayList<String>());
		warpConfig.set("cost", c);
		warpConfig.saveConfig();

		Warp w = new Warp(name, l.getWorld().getName(), l.getX(), l.getY(), l.getZ(), l.getPitch(), l.getYaw(),
				p.getUniqueId().toString(), plugin);
		localWarps.put(IWUtils.iwFormatString(name), w);
		plugin.getInventoryManager()
				.updateInventoryDefinitions(localWarps, plugin.getWarpGroupManager().getWarpGroups());
	}

	/**
	 * Remove a warp, executed via /delwarp.
	 *
	 * @param name - Warp alias
	 * @return if a warp was successfully removed
	 */
	public void removeWarp(String name) throws NoSuchWarpException
	{
		String wn = IWUtils.iwFormatString(name);

		if (isValidWarp(wn))
		{
			localWarps.remove(wn);

			File f = new File(plugin.getDataFolder() + File.separator + "warps" + File.separator + wn + ".yml");

			try
			{
				if (f.delete())
				{
					plugin.getLogger().info("Successfully deleted file " + f.getPath());
				}
				else
				{
					plugin.getLogger().warning("Unable to delete file " + f.getPath());
					plugin.getLogger().warning("Perhaps the file does not exist?");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				plugin.getLogger().warning("There was a security error when deleting the file " + f.getPath());
			}
		}
		else
		{
			throw new NoSuchWarpException(name);
		}
	}

	public void renameWarp(String oldName, String newAlias)
	{

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

	public void warp(Player p, String warpName, boolean center, boolean safe, boolean unsafeConfirm)
			throws NoSuchWarpException, PerWarpNoPermissionException, PrivateWarpNotAddedException,
			WorldNotFoundException
	{
		if (!isValidWarp(warpName))
		{
			throw new NoSuchWarpException(null);
		}

		Warp w = localWarps.get(warpName);

		if (w.isPrivate())
		{
			String pUUID = p.getUniqueId().toString();
			if (!IWUtils.listContainsStringIgnoreCase(w.getAddedPlayers(), pUUID))
			{
				throw new PrivateWarpNotAddedException();
			}
		}

		if (plugin.getConfigManager().main.getBoolean(ConfigPaths.PER_WARP_PERMISSIONS.getPath(),
				(boolean) ConfigPaths.PER_WARP_PERMISSIONS.getDefaultValue()))
		{
			if (!p.hasPermission("infinitywarps.warp." + w.getWarpName()))
			{
				throw new PerWarpNoPermissionException();
			}
		}

		if (Bukkit.getWorld(w.getWorld()) == null)
		{
			throw new WorldNotFoundException(w.getWorld());
		}

		Location l = new Location(Bukkit.getWorld(w.getWorld()), w.getX(), w.getY(), w.getZ(), w.getYaw(),
				w.getPitch());

		if (center)
		{
			l.setX((int) l.getX() + 0.5);
			l.setY((int) l.getY());
			l.setZ((int) l.getZ() + 0.5);
		}

		/* implement safe teleportation here */
		/* implement teleport timer */

		p.teleport(l);
	}

	public boolean isValidWarp(String warpName)
	{
		return localWarps.get(warpName) != null;
	}
}