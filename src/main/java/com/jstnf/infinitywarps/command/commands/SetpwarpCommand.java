package com.jstnf.infinitywarps.command.commands;

import com.jstnf.infinitywarps.IWMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetpwarpCommand implements CommandExecutor
{
	private IWMain plugin;

	public SetpwarpCommand(IWMain plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		sender.sendMessage("[/setpwarp] To implement!");
		return true;
	}
}