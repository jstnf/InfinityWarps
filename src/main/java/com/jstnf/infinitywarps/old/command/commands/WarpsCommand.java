package com.jstnf.infinitywarps.old.command.commands;

import com.jstnf.infinitywarps.old.IWMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpsCommand implements CommandExecutor
{
	private IWMain plugin;

	public WarpsCommand(IWMain plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		/* Check if player */
		if (!(sender instanceof Player))
		{
			sender.sendMessage("Command must be executed by a Player.");
			return true;
		}

		/* Check if permission */
		if (sender.hasPermission("infinitywarps.command.warps"))
		{
			Player p = (Player) sender;
			p.openInventory(plugin.getInventoryManager().construct("all", 0));
		}
		else
		{
			sender.sendMessage("[HARD_CODED] No permission to open the warp GUI.");
		}

		return true;
	}
}