package com.jstnf.infinitywarps.rewrite.managers;

import com.jstnf.infinitywarps.old.exception.SimilarNameException;
import com.jstnf.infinitywarps.rewrite.InfinityWarps;
import com.jstnf.infinitywarps.rewrite.database.objects.Warp;
import com.jstnf.infinitywarps.rewrite.database.objects.WarpGroup;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;

/**
 * Manages database objects for InfinityWarps
 *
 * @author jstnf
 * 19 April 2019
 */
public class DatabaseManager
{
	private InfinityWarps plugin;
	private HashMap<String, Warp> loadedWarps;
	private HashMap<String, WarpGroup> loadedWarpGroups;

	public DatabaseManager(InfinityWarps plugin)
	{
		this.plugin = plugin;
		loadedWarps = new HashMap<String, Warp>();
		loadedWarpGroups = new HashMap<String, WarpGroup>();
	}

	public boolean loadDatabase()
	{
		return true;
	}

	/**
	 * Generate a warp's configuration file and then add it to the local database.
	 * If the warp is to replace a warp with a similar name and 'replace' is false, a SimilarNameException is thrown.
	 * If another error (IO, etc.) is encountered, then the method returns false and the warp is not added or updated.
	 *
	 * @param w       - The new warp to be added/rewritten.
	 * @param replace - Whether or not to replace a warp with a similar name.
	 */
	public boolean addWarp(Warp w, boolean replace) throws SimilarNameException
	{
		String id = w.getName();
		File warpFile = new File(plugin.getDataFolder() + File.separator + "warps", id + ".yml");
		if (warpFile.exists())
		{
			if (!replace)
			{
				throw new SimilarNameException();
			}
		}
		else
		{
			try
			{
				warpFile.delete();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				plugin.getLogger().warning("There was an error adding or updating the warp " + w.getName() + ".");
				return false;
			}
		}

		try
		{
			YamlConfiguration warpConfig = new YamlConfiguration();
			warpConfig.set("name", w.getName());
			warpConfig.set("alias", w.getAlias());
			warpConfig.set("location.world", w.getWorldName());
			warpConfig.set("location.x", w.getX());
			warpConfig.set("location.y", w.getY());
			warpConfig.set("location.z", w.getZ());
			warpConfig.set("location.pitch", w.getPitch());
			warpConfig.set("location.yaw", w.getYaw());
			warpConfig.set("item.material", w.getItemIcon().getItemMaterial());
			warpConfig.set("item.name", w.getItemIcon().getItemName());
			warpConfig.set("item.lore", w.getItemIcon().getItemLore());
			warpConfig.set("item.glow", w.getItemIcon().isEnchanted());
			warpConfig.save(warpFile);
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
			plugin.getLogger().warning("There was an error adding or updating the warp " + w.getName() + ".");
			return false;
		}

		loadedWarps.put(w.getName(), w);
		return true;
	}
}