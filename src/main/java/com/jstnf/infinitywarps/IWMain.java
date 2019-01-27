package com.jstnf.infinitywarps;

import com.jstnf.infinitywarps.utils.CommandUtils;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class IWMain extends JavaPlugin
{
	public PluginDescriptionFile pdf;

	public IWMain()
	{
		pdf = this.getDescription();
	}

	public void onEnable()
	{
		CommandUtils.setupListeners(this);
		CommandUtils.setupTestListener(this);
		this.getLogger().info("InfinityWarps v" + pdf.getVersion() + " successfully enabled.");
	}

	public void onDisable()
	{
		this.getLogger().info("InfinityWarps successfully disabled.");
	}
}