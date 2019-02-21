package com.jstnf.infinitywarps;

import com.jstnf.infinitywarps.gui.GUIHandler;
import com.jstnf.infinitywarps.gui.TestGUIHandler;
import com.jstnf.infinitywarps.utils.CommandUtils;
import com.jstnf.infinitywarps.utils.config.ConfigUtils;
import com.jstnf.infinitywarps.utils.config.SimpleConfig;
import com.jstnf.infinitywarps.utils.config.SimpleConfigManager;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class IWMain extends JavaPlugin
{
	public PluginDescriptionFile pdf;
	public GUIHandler gui;
	public final int CONFIG_VERSION = 1;

	public ConfigUtils configs;

	public SimpleConfigManager manager;

	public SimpleConfig config;
	public SimpleConfig messages;

	public IWMain()
	{
		pdf = this.getDescription();
	}

	public void onEnable()
	{
		/* Commands */
		CommandUtils.setupListeners(this);
		CommandUtils.setupTestListener(this);

		/* GUIs */
		TestGUIHandler tgh = new TestGUIHandler();
		getServer().getPluginManager().registerEvents(tgh, this);
		gui = new GUIHandler();
		gui.constructGUIs();
		getServer().getPluginManager().registerEvents(gui, this);

		/* Configs */
		configs = new ConfigUtils(this);

		/* etc. */
		this.getLogger().info("InfinityWarps v" + pdf.getVersion() + " successfully enabled.");
	}

	public void onDisable()
	{
		this.getLogger().info("InfinityWarps successfully disabled.");
	}

	public void constructConfigs()
	{

	}
}