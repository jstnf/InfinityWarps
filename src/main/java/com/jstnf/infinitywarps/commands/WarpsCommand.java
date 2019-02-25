package com.jstnf.infinitywarps.commands;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.utils.IWExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpsCommand extends IWExecutor implements CommandExecutor
{
	public WarpsCommand(IWMain plugin)
	{
		super(plugin);
		commandMap = null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		plugin.getLogger().info("[/warps] To implement!");
		if (!(sender instanceof Player))
		{
			sender.sendMessage("Command must be executed by a Player.");
			return true;
		}
		Player p = (Player) sender;
		p.openInventory(plugin.gui2.construct("all_warps", 0));
		return true;
	}
}