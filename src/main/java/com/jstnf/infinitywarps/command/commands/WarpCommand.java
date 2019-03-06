package com.jstnf.infinitywarps.command.commands;

import com.jstnf.infinitywarps.IWMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WarpCommand implements CommandExecutor
{
	private IWMain plugin;

	public WarpCommand(IWMain plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		sender.sendMessage("[/warp] To implement!");
		return true;
	}
}