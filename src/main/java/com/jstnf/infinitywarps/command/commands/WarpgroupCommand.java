package com.jstnf.infinitywarps.command.commands;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.command.IWExecutor;
import com.jstnf.infinitywarps.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class WarpgroupCommand extends IWExecutor implements CommandExecutor
{
	public WarpgroupCommand(IWMain plugin)
	{
		super(plugin);
		commandMap = new HashMap<String, SubCommand>();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		sender.sendMessage("[/warpgroup] To implement!");
		if (args.length > 0 && args[0] != null && hasCommand(args[0]))
		{
			return commandMap.get(args[0]).onCommand(sender, args, plugin);
		}
		else
		{
			sender.sendMessage("Invalid arguments.");
		}
		return true;
	}
}
