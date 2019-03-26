package com.jstnf.infinitywarps.command.commands;

import com.jstnf.infinitywarps.IWMain;
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
		if (!(sender instanceof Player))
		{
			sender.sendMessage("Command must be executed by a Player.");
			return true;
		}
		Player p = (Player) sender;
		if (p.hasPermission("infinitywarps.command.warps"))
		{
			p.openInventory(plugin.getInventoryManager().construct("all", 0));
		}
		else
		{
			p.sendMessage("[HARD_CODED_LANG] No permission to use /warps.");
		}
		return true;
	}
}