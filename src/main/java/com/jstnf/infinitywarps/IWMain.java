package com.jstnf.infinitywarps;

import com.jstnf.infinitywarps.gui.GUIHandler;
import com.jstnf.infinitywarps.gui.TestGUIHandler;
import com.jstnf.infinitywarps.utils.CommandUtils;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class IWMain extends JavaPlugin
{
	public PluginDescriptionFile pdf;
	public GUIHandler gui;
	public final int CONFIG_VERSION = 1;

	public IWMain()
	{
		pdf = this.getDescription();
	}

	public void onEnable()
	{
		CommandUtils.setupListeners(this);
		CommandUtils.setupTestListener(this);
		this.getLogger().info("InfinityWarps v" + pdf.getVersion() + " successfully enabled.");

		TestGUIHandler tgh = new TestGUIHandler();
		getServer().getPluginManager().registerEvents(tgh, this);

		gui = new GUIHandler();
		gui.constructGUIs();
		getServer().getPluginManager().registerEvents(gui, this);
	}

	public void onDisable()
	{
		this.getLogger().info("InfinityWarps successfully disabled.");
	}
}