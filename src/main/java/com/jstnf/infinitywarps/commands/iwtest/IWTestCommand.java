package com.jstnf.infinitywarps.commands.iwtest;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.utils.IWExecutor;
import com.jstnf.infinitywarps.utils.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class IWTestCommand extends IWExecutor implements CommandExecutor
{
	public IWTestCommand(IWMain plugin)
	{
		super(plugin);
		commandMap = new HashMap<String, SubCommand>();
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		sender.sendMessage("[/iwtest] Testing...");
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