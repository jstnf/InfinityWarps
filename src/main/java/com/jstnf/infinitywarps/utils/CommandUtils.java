package com.jstnf.infinitywarps.utils;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.commands.*;
import com.jstnf.infinitywarps.commands.test.IWTestCommand;

public class CommandUtils
{
	/* Register command listeners */
	public static void setupListeners(IWMain plugin)
	{
		plugin.getLogger().info("Hooking commands...");
		plugin.getCommand("infinitywarps").setExecutor(new InfinitywarpsCommand(plugin));
		plugin.getCommand("warp").setExecutor(new WarpCommand(plugin));
		plugin.getCommand("setwarp").setExecutor(new SetwarpCommand(plugin));
		plugin.getCommand("delwarp").setExecutor(new DelwarpCommand(plugin));
		plugin.getCommand("warps").setExecutor(new WarpsCommand(plugin));
	}

	/* Register test command listener */
	public static void setupTestListener(IWMain plugin)
	{
		plugin.getCommand("iwtest").setExecutor(new IWTestCommand(plugin));
	}
}