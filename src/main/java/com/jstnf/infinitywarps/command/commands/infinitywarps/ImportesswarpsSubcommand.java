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
	@Override
	public boolean onCommand(CommandSender sender, String[] args, IWMain plugin)
	{
		boolean playerNotify = sender instanceof Player;

		if (sender.hasPermission(permission()))
		{
			if (!plugin.getServer().getPluginManager().isPluginEnabled("Essentials"))
			{
				if (playerNotify)
				{
					sender.sendMessage("Essentials was not found. Import aborted.");
				}
				plugin.getLogger().warning("Essentials was not found. Import aborted.");
				return true;
			}

			boolean groupWarps = false;
			String groupDivider = "";

			if (args.length > 1)
			{
				groupWarps = true;
				groupDivider = args[1];
			}

			try
			{
				if (playerNotify)
				{
					sender.sendMessage("Commencing warp importing from Essentials...");
				}
				plugin.getLogger().info("Commencing warp importing from Essentials...");

				if (groupWarps)
				{
					if (playerNotify)
					{
						sender.sendMessage(
								"Warps will be placed in groups by the syntax GROUP" + groupDivider + "WARP");
					}
					plugin.getLogger()
							.info("Warps will be placed in groups by the syntax GROUP" + groupDivider + "WARP");
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
							sender.sendMessage("Found warp config " + fileName);
						}
						plugin.getLogger().info("Found warp config " + fileName);
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
						sender.sendMessage("Could not find Essentials warps folder. Import aborted.");
					}
					plugin.getLogger().warning("Could not find Essentials warps folder. Import aborted.");
					return true;
				}

				sender.sendMessage("Importing completed!");
				plugin.getLogger().info("Importing completed!");
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