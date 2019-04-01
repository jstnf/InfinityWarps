package com.jstnf.infinitywarps.command.commands;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.exception.InvalidCostException;
import com.jstnf.infinitywarps.exception.SimilarNameException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SetwarpCommand implements CommandExecutor
{
	private final String help = "Usage: /setwarp <name> [cost]";
	private IWMain plugin;

	public SetwarpCommand(IWMain plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		/* Check if player */
		if (!(sender instanceof Player))
		{
			sender.sendMessage("You must be a Player to use this command.");
			return true;
		}

		/* Check permission */
		if (sender.hasPermission("infinitywarps.command.setwarp"))
		{
			/* Check arguments */
			if (!(args.length > 0 && args.length < 3))
			{
				sender.sendMessage(help);
				return true;
			}

			/* Cost argument */
			String cost = "0.0";
			if (args.length != 1)
			{
				cost = args[1];
			}

			try
			{
				Player p = (Player) sender;
				plugin.getWarpManager().addWarp(args[0], cost, p, false, new ArrayList<String>());
				sender.sendMessage(
						"[HARD_CODED] Warp " + ChatColor.translateAlternateColorCodes('&', args[0] + "&f set."));
			}
			catch (InvalidCostException ex)
			{
				sender.sendMessage("[HARD_CODED] [cost] must be a double.");
			}
			catch (SimilarNameException ex)
			{
				sender.sendMessage("[HARD_CODED] Similar warp name already exists.");
			}
		}
		else
		{
			sender.sendMessage("[HARD_CODED_LANG] You do not have permission to use this command.");
		}

		return true;
	}
}