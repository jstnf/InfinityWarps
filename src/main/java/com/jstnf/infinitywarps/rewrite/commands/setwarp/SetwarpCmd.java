package com.jstnf.infinitywarps.rewrite.commands.setwarp;

import com.jstnf.infinitywarps.old.exception.SimilarNameException;
import com.jstnf.infinitywarps.rewrite.commands.IWExecutor;
import com.jstnf.infinitywarps.rewrite.settings.IWLocale;
import com.jstnf.infinitywarps.rewrite.InfinityWarps;
import com.jstnf.infinitywarps.rewrite.database.objects.Warp;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class for the /setwarp command
 *
 * @author jstnf
 * 20 April 2019
 */
public class SetwarpCmd extends IWExecutor implements CommandExecutor
{
	private final String help = "Usage: /setwarp <name>";

	public SetwarpCmd(InfinityWarps plugin)
	{
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		/* Check if player */
		if (!(sender instanceof Player))
		{
			sender.sendMessage(plugin.configManager.getMessage(IWLocale.MUST_BE_PLAYER));
			return true;
		}

		Player p = (Player) sender;

		/* Check permission */
		if (!p.hasPermission("infinitywarps.setwarp"))
		{
			sender.sendMessage(plugin.configManager.getMessage(IWLocale.NO_PERMISSION_SETWARP));
			return true;
		}

		/* Check arguments */
		if (args.length != 1)
		{
			sender.sendMessage(help);
			return true;
		}

		Location l = p.getLocation();
		Warp w = new Warp(plugin, args[0], l.getWorld().getName(), l.getX(), l.getY(), l.getZ(), l.getPitch(),
				l.getYaw());
		try
		{
			if (plugin.databaseManager.addWarp(w, false))
			{
				sender.sendMessage(plugin.configManager.getMessage(IWLocale.WARP_SET, args[0]));
			}
			else
			{
				sender.sendMessage(plugin.configManager.getMessage(IWLocale.WARP_SET_ERROR_OCCURRED));
			}
		}
		catch (SimilarNameException e)
		{
			sender.sendMessage(plugin.configManager.getMessage(IWLocale.WARP_SIMILAR_NAME));
		}

		return true;
	}
}