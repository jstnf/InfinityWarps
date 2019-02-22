package com.jstnf.infinitywarps.commands.iwtest;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.data.Warp;
import com.jstnf.infinitywarps.utils.SubCommand;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestSetwarpSubCommand implements SubCommand
{
	@Override
	public boolean onCommand(CommandSender sender, String[] args, IWMain plugin)
	{
		if (args.length > 1)
		{
			if (!(sender instanceof Player))
			{
				sender.sendMessage("Command must be executed by a Player.");
				return true;
			}
			Player p = (Player) sender;
			Location playerLocation = p.getLocation();
			Warp newWarp = new Warp(args[1], playerLocation, p.getUniqueId().toString(), plugin);
			plugin.gui.addWarp(newWarp);
			plugin.gui.constructGUIs();
		}
		else
		{
			sender.sendMessage("Invalid arguments.");
			sender.sendMessage("/iwtest setwarp <warpName>");
		}
		return true;
	}

	@Override
	public String permission()
	{
		return "infinitywarps.command.iwtest.setwarp";
	}

	@Override
	public String commandInfo()
	{
		return "Testing the setwarp functionality.";
	}
}