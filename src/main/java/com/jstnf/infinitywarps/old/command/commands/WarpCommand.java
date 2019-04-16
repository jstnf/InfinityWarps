package com.jstnf.infinitywarps.old.command.commands;

import com.jstnf.infinitywarps.old.IWMain;
import com.jstnf.infinitywarps.old.IWUtils;
import com.jstnf.infinitywarps.old.exception.NoSuchWarpException;
import com.jstnf.infinitywarps.old.exception.PerWarpNoPermissionException;
import com.jstnf.infinitywarps.old.exception.PrivateWarpNotAddedException;
import com.jstnf.infinitywarps.old.exception.WorldNotFoundException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor
{
	private final String help = "Usage: /warp <warpName>";
	private IWMain plugin;

	public WarpCommand(IWMain plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		/* Check if player */
		if (!(sender instanceof Player))
		{
			sender.sendMessage("Command must be executed by a Player!");
			return false;
		}

		/* Check arguments */
		if (!(args != null && args.length == 1))
		{
			sender.sendMessage(help);
			return true;
		}

		/* WarpManager handles permissions */
		try
		{
			String warpName = IWUtils.iwFormatString(args[0]);
			plugin.getWarpManager().warp((Player) sender, warpName, true, false, false);
			sender.sendMessage("[HARD_CODED] Warped to " + warpName + ".");
		}
		catch (NoSuchWarpException e)
		{
			sender.sendMessage("[HARD_CODED] No such warp exists!");
		}
		catch (PerWarpNoPermissionException e)
		{
			sender.sendMessage("[HARD_CODED] No permissions (per warp perms)");
		}
		catch (PrivateWarpNotAddedException e)
		{
			sender.sendMessage("[HARD_CODED] You do not have permission to this private warp!");
		}
		catch (WorldNotFoundException e)
		{
			sender.sendMessage("[HARD_CODED] World was not found!");
		}

		return true;
	}
}