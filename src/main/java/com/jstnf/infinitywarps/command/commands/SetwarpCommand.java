package com.jstnf.infinitywarps.command.commands;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.command.IWExecutor;
import com.jstnf.infinitywarps.exception.InvalidCostException;
import com.jstnf.infinitywarps.exception.SimilarNameException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/* /setwarp <name> [cost] */
public class SetwarpCommand extends IWExecutor implements CommandExecutor
{
	private final String help = "Usage: /setwarp <name> [cost]";

	public SetwarpCommand(IWMain plugin)
	{
		super(plugin);
		commandMap = null; // No subcommands
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage("You must be a Player to use this command.");
		}
		else
		{
			Player p = (Player) sender;
			if (p.hasPermission("infinitywarps.command.setwarp"))
			{
				if (args.length > 0 && args.length < 3)
				{
					String cost;
					if (args.length == 1)
					{
						cost = "0.0";
					}
					else
					{
						cost = args[1];
					}
					try
					{
						plugin.getWarpManager().addWarp(args[0], cost, p, false, new ArrayList<String>());
						sender.sendMessage("Warp " + ChatColor.translateAlternateColorCodes('&', args[0]) + " &fset.");
					}
					catch (InvalidCostException ex)
					{
						sender.sendMessage("[cost] must be double.");
					}
					catch (SimilarNameException ex)
					{
						sender.sendMessage("Similar warp name exists.");
					}
				}
				else
				{
					sender.sendMessage(help);
				}
			}
			else
			{
				sender.sendMessage("You do not have permission to use this command.");
			}
		}

		return true;
	}
}