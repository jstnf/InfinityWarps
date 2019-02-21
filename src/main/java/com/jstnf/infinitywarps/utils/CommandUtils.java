package com.jstnf.infinitywarps.utils;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.commands.*;
import com.jstnf.infinitywarps.commands.iwtest.IWTestCommand;
import com.jstnf.infinitywarps.commands.iwtest.MylocationSubCommand;
import com.jstnf.infinitywarps.commands.iwtest.TestSetwarpSubCommand;
import com.jstnf.infinitywarps.commands.iwtest.TestguiSubCommand;
import com.jstnf.infinitywarps.commands.manwarp.ManwarpCommand;

public class CommandUtils
{
	/**
	 * Register all command and subcommand listeners and executors.
	 */
	public static void setupListeners(IWMain plugin)
	{
		plugin.getLogger().info("Hooking commands...");

		/* Declare commands and command listeners */
		InfinitywarpsCommand iwc = new InfinitywarpsCommand(plugin);
		WarpCommand warpc = new WarpCommand(plugin);
		SetwarpCommand setwarpc = new SetwarpCommand(plugin);
		DelwarpCommand delwarpc = new DelwarpCommand(plugin);
		WarpsCommand warpsc = new WarpsCommand(plugin);
		ManwarpCommand manwarpc = new ManwarpCommand(plugin);
		SetpwarpCommand setpwarpc = new SetpwarpCommand(plugin);

		/* Register SubCommands */


		/* Set executors */
		plugin.getCommand("infinitywarps").setExecutor(iwc);
		plugin.getCommand("warp").setExecutor(warpc);
		plugin.getCommand("setwarp").setExecutor(setwarpc);
		plugin.getCommand("delwarp").setExecutor(delwarpc);
		plugin.getCommand("warps").setExecutor(warpsc);
		plugin.getCommand("manwarp").setExecutor(manwarpc);
		plugin.getCommand("setpwarp").setExecutor(setpwarpc);
	}

	/**
	 * Register /iwtest command listener for testing and debug purposes.
	 */
	public static void setupTestListener(IWMain plugin)
	{
		/* Declare commands and command listeners */
		IWTestCommand iwtestc = new IWTestCommand(plugin);

		/* Register SubCommands */
		iwtestc.registerSubCommand("mylocation", new MylocationSubCommand());
		iwtestc.registerSubCommand("testgui", new TestguiSubCommand());
		iwtestc.registerSubCommand("setwarp", new TestSetwarpSubCommand());

		/* Set executors */
		plugin.getCommand("iwtest").setExecutor(iwtestc);
	}
}