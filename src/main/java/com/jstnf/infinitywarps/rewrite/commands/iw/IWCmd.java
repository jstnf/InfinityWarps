package com.jstnf.infinitywarps.rewrite.commands.iw;

import com.jstnf.infinitywarps.rewrite.InfinityWarps;
import com.jstnf.infinitywarps.rewrite.commands.IWExecutor;
import com.jstnf.infinitywarps.rewrite.settings.IWLocale;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
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
	/* Info */
	private final String header =
			ChatColor.AQUA + "Infinity" + ChatColor.LIGHT_PURPLE + "Warps " + ChatColor.WHITE + "by jstnf";
	private final String version = ChatColor.WHITE + "v" + plugin.getDescription().getVersion();
	private final String credits = ChatColor.translateAlternateColorCodes('&', "&6&lSpigot &6jstnf &5&lGitHub &5jstnf");
	private final String help =
			ChatColor.WHITE + "Use " + ChatColor.AQUA + "/iw help" + ChatColor.WHITE + " for command help.";

	public IWCmd(InfinityWarps plugin)
	{
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (args == null || args.length <= 0)
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
			String sub = args[0];
			switch (sub.toLowerCase())
			{
				case "importesswarps":
					/* Feature currently not implemented */
					sender.sendMessage(plugin.configManager.getMessage(IWLocale.FEATURE_NOT_IMPLEMENTED));
					break;
				case "help":
					/* Implement this! */

					/* Some test JSON stuff */
					TextComponent msg = new TextComponent(ChatColor.RED + "Test component!");
					msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
							new ComponentBuilder(ChatColor.RED + "This text is shown on hover!").create()));
					msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/iwdebuginv"));
					sender.spigot().sendMessage(msg);

					break;
				default:
					sender.sendMessage(plugin.configManager.getMessage(IWLocale.UNKNOWN_SUBCOMMAND));
					break;
			}
		}

		return true;
	}

	/**
	 * Send the InfinityWarps command help to sender.
	 *
	 * @param sender - The command sender.
	 * @param page   - The page of help requested.
	 */
	private void sendCommandHelp(CommandSender sender, int page)
	{

	}
}