package com.jstnf.infinitywarps.rewrite.commands;

import com.jstnf.infinitywarps.rewrite.InfinityWarps;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Class for the /iw command
 *
 * @author jstnf
 * 18 April 2019
 */
public class IWCmd extends IWExecutor implements CommandExecutor
{
	private final String header = ChatColor.AQUA + "Infinity" + ChatColor.LIGHT_PURPLE + "Warps " + ChatColor.WHITE + "by jstnf";
	private final String version = ChatColor.WHITE + "v" + plugin.getDescription().getVersion();
	private final String credits = ChatColor.translateAlternateColorCodes('&', "&6&lSpigot &6jstnf &5&lGitHub &5jstnf");
	private final String help = ChatColor.WHITE + "Use " + ChatColor.AQUA + "/iw help" + ChatColor.WHITE + " for command help.";

	public IWCmd(InfinityWarps plugin)
	{
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (args == null || args.length == 0)
		{
			sender.sendMessage("");
			sender.sendMessage(header);
			sender.sendMessage(version);
			sender.sendMessage(credits);
			sender.sendMessage(help);
			sender.sendMessage("");
		}
		else
		{

		}

		return true;
	}
}