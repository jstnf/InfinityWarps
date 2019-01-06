package com.jstnf.infinitywarps;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.jstnf.infinitywarps.commands.IWCommandUtil;

public class IWMain extends JavaPlugin
{
	public PluginDescriptionFile pdf;
	
	public IWMain()
	{
		pdf = this.getDescription();
	}
	
	public void onEnable()
	{
		IWCommandUtil.setupListeners(this);
		this.getLogger().info("InfinityWarps v" + pdf.getVersion() + " successfully enabled.");
	}
	
	public void onDisable()
	{
		this.getLogger().info("InfinityWarps successfully disabled.");
	}
}
