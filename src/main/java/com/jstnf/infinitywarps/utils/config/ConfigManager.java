package com.jstnf.infinitywarps.utils.config;

import com.jstnf.infinitywarps.IWMain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ConfigManager
{
	public SimpleConfig main, warps, lang;
	private SimpleConfigManager manager;

	public ConfigManager(IWMain plugin)
	{
		manager = new SimpleConfigManager(plugin);
		initMainConfig(plugin);
		initWarpsConfig(plugin);
		initLangConfig(plugin);
	}

	public void initMainConfig(IWMain plugin)
	{
		if (!new File(plugin.getDataFolder(), "config.yml").exists())
		{
			String[] header = { "", "InfinityWarps by jstnf/pokeball92870", "Configuration File", "" };
			main = manager.getNewConfig("config.yml", header);

			/* configVersion */
			main.set("configVersion", 1, "Config Version (do not change this value!)");

			/* centerOnBlock */
			String[] centerComs = { "On teleport, should InfinityWarps center the player",
					"on the warp location block?" };
			main.set("centerOnBlock", true, centerComs);

			/* safeTeleport */
			String[] safeTpComs = { "Should InfinityWarps try to find a safe location", "to warp to?",
					"If false, InfinityWarps will warp the player", "directly to the location, regardless of safety.",
					"This includes in the air, in liquids, and inside of blocks." };
			main.set("safeTeleport", true, safeTpComs);

			/* defaultItemIcon */
			String[] defItemComs = { "By default, InfinityWarps will use this item as",
					"the warp item icon in the warps GUI.", "A complete list of possible items can be found at",
					"https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html" };
			main.set("defaultItemIcon", "ENDER_PEARL", defItemComs);

			/* perWarpPermissions */
			String[] perWarpComs = { "Should players need a permission in order to use", "each warp?",
					"The permission required would be infinitywarps.warp.<warpname>" };
			main.set("perWarpPermissions", false, perWarpComs);

			/* teleportDelay */
			String[] tpDelayComs = { "In seconds, the time before a warp is initiated.",
					"Warps are cancelled if the player moves at any time during this timer." };
			main.set("teleportDelay", 5, tpDelayComs);

			/* teleportCooldown */
			main.set("teleportCooldown", 30, "In seconds, the cooldown timer between warps.");

			/* useEconomy */
			String[] useEcoComs = { "Should InfinityWarps use the economy?",
					"This requires Vault as well as an economy plugin.",
					"If false, any costs associated to warps will not show", "up in menus or when using a warp." };
			main.set("useEconomy", false, useEcoComs);

			/* Misc default item icons */
			main.set("borderItemIcon", "BLUE_STAINED_GLASS_PANE", "Default items for the warps GUI.");
			main.set("prevPageItemIcon", "FEATHER");
			main.set("nextPageItemIcon", "FEATHER");

			main.saveConfig();
		}
		else
		{
			String[] header = { "", "InfinityWarps by jstnf/pokeball92870", "Configuration File", "" };
			main = manager.getNewConfig("config.yml", header);
		}
	}

	public void initWarpsConfig(IWMain plugin)
	{
		if (!new File(plugin.getDataFolder(), "warps.yml").exists())
		{
			try
			{
				String[] header = { "", "InfinityWarps by jstnf/pokeball92870", "Warps File", "" };
				warps = manager.getNewConfig("warps.yml", header);

				/* configVersion */
				warps.set("configVersion", 1, "Config Version (do not change this value!)");

				/* warps */
				warps.set("warps", null, "Warps List");

				warps.saveConfig();

				BufferedWriter bw = new BufferedWriter(
						new FileWriter(new File(plugin.getDataFolder(), "warps.yml"), true));
				bw.write("warps:");
				bw.close();

				/* Reload warps after appending 'warps:' */
				warps = manager.getNewConfig("warps.yml", header);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				plugin.getLogger().severe("Error generating warps.yml file.");
			}
		}
		else
		{
			String[] header = { "", "InfinityWarps by jstnf/pokeball92870", "Warps File", "" };
			warps = manager.getNewConfig("warps.yml", header);
		}
	}

	public void initLangConfig(IWMain plugin)
	{
		if (!new File(plugin.getDataFolder(), "lang.yml").exists())
		{
			lang = manager.getNewConfig("lang.yml");
			/* Setup new config below */
		}
		else
		{
			lang = manager.getNewConfig("lang.yml");
		}
	}
}