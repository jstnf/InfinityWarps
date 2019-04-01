package com.jstnf.infinitywarps.command.commands.infinitywarps;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ImportesswarpsSubcommand implements SubCommand
{
	private String ERROR_MESSAGE = "Essentials plugin was not found. Import aborted.";
	private String NOTIFY = "Commencing warp importing from Essentials...";
	private String GROUP_SYNTAX_NOTIF = "Warps will be placed in groups by the syntax GROUP";
	private String WARP = "WARP";
	private String FOUND_CONFIG = "Found Essentials warp config ";
	private String FOLDER_NOT_FOUND = "Could not find Essentials warps folder. Import aborted.";
	private String IMPORT_COMPLETE = "Importing from Essentials completed!";

	@Override
	public boolean onCommand(CommandSender sender, String[] args, IWMain plugin)
	{
		/* Check if player */
		boolean playerNotify = sender instanceof Player;

		/* Check permission */
		if (sender.hasPermission(permission()))
		{
			boolean groupWarps = false;
			String groupDivider = "";

			/* Check for Essentials plugin */
			if (!plugin.getServer().getPluginManager().isPluginEnabled("Essentials"))
			{
				if (playerNotify)
				{
					sender.sendMessage(ERROR_MESSAGE);
				}
				plugin.getLogger().warning(ERROR_MESSAGE);
				return true;
			}

			/* Check arguments */

			if (args.length == 1)
			{
				groupWarps = true;
				groupDivider = args[0];
			}

			try
			{
				if (playerNotify)
				{
					sender.sendMessage(NOTIFY);
				}
				plugin.getLogger().info(NOTIFY);

				if (groupWarps)
				{
					if (playerNotify)
					{
						sender.sendMessage(GROUP_SYNTAX_NOTIF + groupDivider + WARP);
					}
					plugin.getLogger().info(GROUP_SYNTAX_NOTIF + groupDivider + WARP);
				}

				Plugin ess = plugin.getServer().getPluginManager().getPlugin("Essentials");
				String pathToFolder = ess.getDataFolder() + File.separator + "warps";
				File dataFolder = new File(pathToFolder);
				if (dataFolder.exists() && dataFolder.isDirectory())
				{
					String[] files = dataFolder.list();
					for (int i = 0; i < files.length; i++)
					{
						String fileName = files[i];
						if (playerNotify)
						{
							sender.sendMessage(FOUND_CONFIG + fileName);
						}
						plugin.getLogger().info(FOUND_CONFIG + fileName);
						FileConfiguration conf = YamlConfiguration
								.loadConfiguration(new File(pathToFolder + File.separator + fileName));
						if (!plugin.getWarpManager().importFromEssentialsConfig(conf))
						{
							if (playerNotify)
							{
								sender.sendMessage("Error importing from " + fileName + ". See console.");
							}
						}
					}
				}
				else
				{
					if (playerNotify)
					{
						sender.sendMessage(FOLDER_NOT_FOUND);
					}
					plugin.getLogger().warning(FOLDER_NOT_FOUND);
					return true;
				}

				if (playerNotify)
				{
					sender.sendMessage(IMPORT_COMPLETE);
				}
				plugin.getLogger().info(IMPORT_COMPLETE);
			}
			/* Something messed up! */
			catch (Exception e)
			{
				e.printStackTrace();
				if (playerNotify)
				{
					sender.sendMessage("An error occurred when importing the warps. Please see console.");
				}
				plugin.getLogger()
						.severe("Importing warps failed. See stack trace above and report to plugin developer for help.");
			}
		}
		return true;
	}

	@Override
	public String permission()
	{
		return "infinitywarps.importesswarps";
	}

	@Override
	public String commandInfo()
	{
		return "Import warps from the plugin Essentials";
	}
}