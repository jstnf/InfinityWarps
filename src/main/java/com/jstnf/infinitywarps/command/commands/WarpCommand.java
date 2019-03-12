package com.jstnf.infinitywarps.command.commands;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.IWUtils;
import com.jstnf.infinitywarps.exception.NoSuchWarpException;
import com.jstnf.infinitywarps.exception.PerWarpNoPermissionException;
import com.jstnf.infinitywarps.exception.PrivateWarpNotAddedException;
import com.jstnf.infinitywarps.exception.WorldNotFoundException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor
{
	private IWMain plugin;

	public WarpCommand(IWMain plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage("Command must be executed by a Player!");
			return false;
		}
		if (args != null && args.length > 0)
		{
			try
			{
				String warpName = IWUtils.iwFormatString(args[0]);
				plugin.getWarpManager().warp((Player) sender, warpName, true, false, false);
				sender.sendMessage("HARD CODED - warped to " + warpName + ".");
			}
			catch (NoSuchWarpException e)
			{
				sender.sendMessage("HARD CODED - no such warp exists!");
			}
			catch (PerWarpNoPermissionException e)
			{
				sender.sendMessage("HARD CODED - no permissions (per warp perms)");
			}
			catch (PrivateWarpNotAddedException e)
			{
				sender.sendMessage("HARD CODED - private warp not added");
			}
			catch (WorldNotFoundException e)
			{
				sender.sendMessage("HARD CODED - world was not found!");
			}
		}
		else
		{
			sender.sendMessage("Usage: /warp <warpName>");
		}
		return true;
	}
}