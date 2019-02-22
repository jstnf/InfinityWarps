package com.jstnf.infinitywarps;

import com.jstnf.infinitywarps.data.WarpManager;
import com.jstnf.infinitywarps.gui.GUIHandler;
import com.jstnf.infinitywarps.gui.TestGUIHandler;
import com.jstnf.infinitywarps.utils.CommandUtils;
import com.jstnf.infinitywarps.utils.config.ConfigUtils;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class IWMain extends JavaPlugin
{
	public final int CONFIG_VERSION = 1;
	public PluginDescriptionFile pdf;
	public GUIHandler gui;
	public WarpManager warpMan;
	public ConfigUtils configs;

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
		configs = new ConfigUtils(this);

		/* Data */
		getLogger().info("Getting warp data...");
		warpMan = new WarpManager(this);
		warpMan.importWarps();

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