package com.jstnf.infinitywarps.rewrite.commands;

import com.jstnf.infinitywarps.rewrite.InfinityWarps;
import com.jstnf.infinitywarps.rewrite.database.IWDatabaseUtils;
import com.jstnf.infinitywarps.rewrite.settings.IWLocale;
import com.jstnf.infinitywarps.rewrite.settings.IWSettings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCmd extends IWExecutor implements CommandExecutor
{
	private final String help = "Usage: /setwarp <name>";

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
			sender.sendMessage(IWLocale.format(plugin.configManager.lang
					.getString(IWLocale.MUST_BE_PLAYER.getPath(), (String) IWLocale.MUST_BE_PLAYER.getDefaultValue())));
			return true;
		}

		boolean perWarpPerms = plugin.configManager.main.getBoolean(IWSettings.PER_WARP_PERMISSIONS.getPath(),
				(boolean) IWSettings.PER_WARP_PERMISSIONS.getDefaultValue());

		Player p = (Player) sender;

		/* Check permission */
		if (!p.hasPermission("infinitywarps.warp"))
		{
			sender.sendMessage(IWLocale.format(plugin.configManager.lang
					.getString(IWLocale.NO_PERMISSION_WARP.getPath(),
							(String) IWLocale.NO_PERMISSION_WARP.getDefaultValue())));
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

		/* Validate warp */

		return true;
	}
}