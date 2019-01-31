package com.jstnf.infinitywarps.commands.iwtest;

import com.jstnf.infinitywarps.utils.SubCommand;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MylocationSubCommand implements SubCommand
{
	@Override public boolean onCommand(CommandSender sender, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage("Command must be executed by a Player.");
			return true;
		}
		Player p = (Player) sender;
		Location currLoc = p.getLocation();
		p.sendMessage("X: " + currLoc.getX() + ", Y: " + currLoc.getY() + ", Z: " + currLoc.getZ()
				+ ", Pitch: " + currLoc.getPitch() + ", Yaw: " + currLoc.getYaw());
		return true;
	}

	@Override public String permission()
	{
		return "infinitywarps.command.iwtest.mylocation";
	}

	@Override public String commandInfo()
	{
		return "Prints your current location in chat.";
	}
}