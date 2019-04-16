package com.jstnf.infinitywarps.old.command.commands;

import com.jstnf.infinitywarps.old.IWMain;
import com.jstnf.infinitywarps.old.exception.NoSuchWarpException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DelwarpCommand implements CommandExecutor
{
	private final String help = "Usage: /delwarp <warp>";
	private IWMain plugin;

	public DelwarpCommand(IWMain plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		/* Check permission */
		if (sender.hasPermission("infinitywarps.command.delwarp"))
		{
			/* Check arguments */
			if (args == null || args.length != 1 || args[0] == null)
			{
				sender.sendMessage(help);
				return true;
			}

			try
			{
				plugin.getWarpManager().removeWarp(args[0]);
				sender.sendMessage("[HARD_CODED] Warp " + args[0] + " deleted!");
			}
			catch (NoSuchWarpException e)
			{
				sender.sendMessage("[HARD_CODED] No such warp!");
			}
		}
		else
		{
			sender.sendMessage("[HARD_CODED] No permission to delete a warp!");
		}

		return true;
	}
}