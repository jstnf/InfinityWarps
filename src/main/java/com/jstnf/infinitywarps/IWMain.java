package com.jstnf.infinitywarps;

import com.jstnf.infinitywarps.data.WarpManager;
import com.jstnf.infinitywarps.gui.GUIHandler;
import com.jstnf.infinitywarps.gui.TestGUIHandler;
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
	public GUIHandler gui;
	public WarpManager warpManager;
	public ConfigManager configs;
	public boolean useEconomy;

	public IWMain()
	{
		pdf = this.getDescription();
	}

	public void onEnable()
	{
		/* Commands */
		CommandUtils.setupListeners(this);
		CommandUtils.setupTestListener(this);

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
				getLogger().warning("Please restart the plugin with the missing plugin(s) or set 'useEconomy' in config.yml to false.");
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
			getLogger().severe("Unable to import warps from config file.");
			getLogger().severe("You may need to generate a new warps.yml file.");
			getLogger().severe("InfinityWarps is disabling...");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			return;
		}

		/* GUIs */
		getLogger().info("Initializing test GUI...");
		TestGUIHandler tgh = new TestGUIHandler();
		getServer().getPluginManager().registerEvents(tgh, this);

		getLogger().info("Initializing GUI...");
		gui = new GUIHandler(this);
		gui.constructGUIs();
		getServer().getPluginManager().registerEvents(gui, this);

		/* etc. */
		this.getLogger().info("InfinityWarps v" + pdf.getVersion() + " successfully enabled.");
	}

	public void onDisable()
	{
		this.getLogger().info("InfinityWarps successfully disabled.");
	}
}