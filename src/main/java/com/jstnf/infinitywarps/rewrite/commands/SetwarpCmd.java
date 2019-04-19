package com.jstnf.infinitywarps.rewrite.commands;

import com.jstnf.infinitywarps.rewrite.InfinityWarps;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetwarpCmd extends IWExecutor implements CommandExecutor
{
	public SetwarpCmd(InfinityWarps plugin)
	{
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		return true;
	}
}