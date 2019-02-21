package com.jstnf.infinitywarps.commands.iwtest;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.gui.TestGUIHandler;
import com.jstnf.infinitywarps.utils.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestguiSubCommand implements SubCommand
{
	@Override
	public boolean onCommand(CommandSender sender, String[] args, IWMain plugin)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage("Command must be executed by a Player.");
			return true;
		}
		((Player) sender).openInventory(new TestGUIHandler().getInventory((Player) sender));
		return true;
	}

	@Override
	public String permission()
	{
		return "infinitywarps.command.iwtest.testgui";
	}

	@Override
	public String commandInfo()
	{
		return "Creates a dummy, test GUI.";
	}
}