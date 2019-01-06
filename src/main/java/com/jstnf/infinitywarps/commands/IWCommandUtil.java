package com.jstnf.infinitywarps.commands;

import com.jstnf.infinitywarps.IWMain;

public class IWCommandUtil
{
	public static void setupListeners(IWMain plugin)
	{
		plugin.getLogger().info("Hooking commands...");
		plugin.getCommand("infinitywarps").setExecutor(new InfinityWarpsCommand(plugin));
	}
}
