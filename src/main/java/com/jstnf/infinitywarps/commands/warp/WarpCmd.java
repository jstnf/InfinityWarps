package com.jstnf.infinitywarps.commands.warp;

import com.jstnf.infinitywarps.InfinityWarps;
import com.jstnf.infinitywarps.commands.IWExecutor;
import com.jstnf.infinitywarps.database.IWDatabaseUtils;
import com.jstnf.infinitywarps.settings.IWLocale;
import com.jstnf.infinitywarps.settings.IWSettings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class for the /warp command
 *
 * @author jstnf
 * 28 April 2019
 */
public class WarpCmd extends IWExecutor implements CommandExecutor
{
	private final String help = "Usage: /warp <name>";

	public WarpCmd(InfinityWarps plugin)
	{
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		/* Check if player */
		if (!(sender instanceof Player))
		{
			sender.sendMessage(plugin.configManager.getMessage(IWLocale.MUST_BE_PLAYER, true));
			return true;
		}

		boolean perWarpPerms = plugin.configManager.main.getBoolean(IWSettings.PER_WARP_PERMISSIONS.getPath(),
				(boolean) IWSettings.PER_WARP_PERMISSIONS.getDefaultValue());

		Player p = (Player) sender;

		/* Check permission */
		if (!p.hasPermission("infinitywarps.warp"))
		{
			sender.sendMessage(plugin.configManager.getMessage(IWLocale.NO_PERMISSION_WARP, true));
			return true;
		}

		/* Check arguments */
		if (args.length != 1)
		{
			sender.sendMessage(help);
			return true;
		}

		String warp = args[0];
		String formatted = IWDatabaseUtils.convertToDatabaseName(warp);

		/* Validate the warp */
		if (plugin.databaseManager.isWarp(formatted))
		{

		}
		else
		{
			/* Warp was not found */
			sender.sendMessage(plugin.configManager.getMessage(IWLocale.WARP_NOT_FOUND, true, args[0]));
		}

		return true;
	}
}