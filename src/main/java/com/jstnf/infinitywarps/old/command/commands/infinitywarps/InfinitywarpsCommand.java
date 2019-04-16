package com.jstnf.infinitywarps.old.command.commands.infinitywarps;

import com.jstnf.infinitywarps.old.IWMain;
import com.jstnf.infinitywarps.old.command.IWExecutor;
import com.jstnf.infinitywarps.old.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class InfinitywarpsCommand extends IWExecutor implements CommandExecutor
{
	public InfinitywarpsCommand(IWMain plugin)
	{
		super(plugin);
		commandMap = new HashMap<String, SubCommand>();
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		sender.sendMessage("[/iw] To implement!");
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