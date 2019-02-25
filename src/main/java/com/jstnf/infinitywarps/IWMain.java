package com.jstnf.infinitywarps;

import com.jstnf.infinitywarps.data.WarpManager;
import com.jstnf.infinitywarps.gui.GUIHandler2;
import com.jstnf.infinitywarps.utils.CommandUtils;
import com.jstnf.infinitywarps.utils.config.ConfigManager;
import com.jstnf.infinitywarps.utils.economy.EconomyUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class IWMain extends JavaPlugin
{
	public final int CONFIG_VERSION = 1;
	public PluginDescriptionFile pdf;
	public GUIHandler2 gui2;
	public WarpManager warpManager;
	public ConfigManager configs;
	public boolean useEconomy;

	public IWMain()
	{
		pdf = this.getDescription();
	}

	public void onEnable()
	{
		/* Configs */
		getLogger().info("Initializing configs...");
		configs = new ConfigManager(this);

		/* Economy */
		if (configs.main.getBoolean("useEconomy", false))
		{
			useEconomy = EconomyUtils.hasPrerequisites(this);
			if (!useEconomy)
			{
				getLogger().warning("An attempt was made to use the economy.");
				getLogger().warning("However, either Vault or an economy plugin were not found.");
				getLogger().warning(
						"Please restart the plugin with the missing plugin(s) or set 'useEconomy' in config.yml to false.");
			}
			else
			{
				getLogger().info("Economy features successfully initialized.");
			}
		}
		else
		{
			useEconomy = false;
		}

		/* Data */
		getLogger().info("Getting warp data...");
		warpManager = new WarpManager(this);
		boolean success = warpManager.importWarps();
		if (!success)
		{
			getLogger().severe("There was an error importing warps.");
			getLogger().severe("InfinityWarps is disabling...");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			return;
		}

		/* GUIs */
		getLogger().info("Initializing GUI...");
		gui2 = new GUIHandler2(warpManager.localWarps, null);

		/* Commands */
		CommandUtils.setupListeners(this);
		CommandUtils.setupTestListener(this);

		/* etc. */
		this.getLogger().info("InfinityWarps v" + pdf.getVersion() + " successfully enabled.");
	}

	public void onDisable()
	{
		this.getLogger().info("InfinityWarps successfully disabled.");
	}
}