package com.jstnf.infinitywarps.commands.test;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.data.Warp;
import com.jstnf.infinitywarps.gui.TestGUIHandler;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IWTestCommand implements CommandExecutor
{
	private IWMain plugin;

	public IWTestCommand(IWMain plugin)
	{
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		plugin.getLogger().info("[/iwtest] test command");
		switch (args.length)
		{
			case 0:
				sender.sendMessage("Ran with no args. \n/iwtest testgui");
				break;
			case 1:
				switch (args[0].toLowerCase())
				{
					case "testgui":
						if (!(sender instanceof Player))
						{
							sender.sendMessage("Command must be executed by a Player.");
							return true;
						}
						((Player) sender).openInventory(new TestGUIHandler().getInventory((Player) sender));
						break;
					case "mylocation":
						if (!(sender instanceof Player))
						{
							sender.sendMessage("Command must be executed by a Player.");
							return true;
						}
						Player p = (Player) sender;
						Location currLoc = p.getLocation();
						p.sendMessage("X: " + currLoc.getX() + ", Y: " + currLoc.getY() + ", Z: " + currLoc.getZ()
								+ ", Pitch: " + currLoc.getPitch() + ", Yaw: " + currLoc.getYaw());
						break;
					case "setwarp":
						sender.sendMessage("Not enough args.");
						break;
					default:
						break;
				}
				break;
			default:
				if (args[0].equalsIgnoreCase("setwarp"))
				{
					if (!(sender instanceof Player))
					{
						sender.sendMessage("Command must be executed by a Player.");
						return true;
					}
					Player p = (Player) sender;
					Location playerLocation = p.getLocation();
					Warp newWarp = new Warp(args[1], playerLocation, p);
					plugin.gui.addWarp(newWarp);
					plugin.gui.constructGUIs();
				}
				break;
		}
		return true;
	}
}