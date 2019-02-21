package com.jstnf.infinitywarps.commands.manwarp;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.utils.IWExecutor;
import com.jstnf.infinitywarps.utils.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class ManwarpCommand extends IWExecutor implements CommandExecutor
{
	public ManwarpCommand(IWMain plugin)
	{
		super(plugin);
		commandMap = new HashMap<String, SubCommand>();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		sender.sendMessage("[/manwarp] Testing...");
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