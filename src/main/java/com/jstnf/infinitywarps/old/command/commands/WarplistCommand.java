package com.jstnf.infinitywarps.old.command.commands;

import com.jstnf.infinitywarps.old.IWMain;
import com.jstnf.infinitywarps.old.IWUtils;
import com.jstnf.infinitywarps.old.database.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class WarplistCommand implements CommandExecutor
{
	private IWMain plugin;

	public WarplistCommand(IWMain plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		/* temporary */
		// This will be JSON text that you can hover/click!
		ArrayList<Warp> alphaWarps = IWUtils.alphaSort(plugin.getWarpManager().getWarps());
		for (Warp w : alphaWarps)
		{
			sender.sendMessage(w.getWarpName());
		}
		return true;
	}
}