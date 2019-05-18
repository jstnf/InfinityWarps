package com.jstnf.infinitywarps.rewrite.commands.iw;

import com.jstnf.infinitywarps.rewrite.IWUtils;
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

	private final int HELP_ENTRIES_PER_PAGE = 3;

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
					sender.sendMessage(plugin.configManager.getMessage(IWLocale.FEATURE_NOT_IMPLEMENTED, true));
					break;
				case "help":
					int page = 1;
					if (args.length > 1)
					{
						if (IWUtils.isInt(args[1]))
						{
							page = Integer.parseInt(args[1]);
						}
					}

					sendCommandHelp(sender, page);

					break;
				default:
					sender.sendMessage(plugin.configManager.getMessage(IWLocale.UNKNOWN_SUBCOMMAND, true));
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
		/* Grab entries, colors, and tooltips */
		HelpEntry[] entries = HelpEntry.values();

		String cmdColor = plugin.configManager.getMessage(IWLocale.HELP_COMMAND_COLOR, false);
		String descColor = plugin.configManager.getMessage(IWLocale.HELP_DESCRIPTION_COLOR, false);
		String commandTooltip = plugin.configManager.getMessage(IWLocale.HELP_TOOLTIP_COMMAND, false);
		String prevTooltip = plugin.configManager.getMessage(IWLocale.HELP_TOOLTIP_PREV_PAGE, false);
		String nextTooltip = plugin.configManager.getMessage(IWLocale.HELP_TOOLTIP_NEXT_PAGE, false);

		/* Do page calculations */
		int pageToDisplay = page;

		int pages = entries.length / HELP_ENTRIES_PER_PAGE;
		if (entries.length % HELP_ENTRIES_PER_PAGE != 0)
		{
			pages += 1;
		}

		if (pageToDisplay < 1 || pageToDisplay > pages)
		{
			pageToDisplay = 1;
		}

		int lowIndex = (pageToDisplay - 1) * HELP_ENTRIES_PER_PAGE;
		int highIndex = lowIndex + HELP_ENTRIES_PER_PAGE;

		int prevPageIndex = pageToDisplay - 1;
		int nextPageIndex = pageToDisplay + 1;

		if (prevPageIndex < 1)
		{
			prevPageIndex = pages;
		}
		if (nextPageIndex > pages)
		{
			nextPageIndex = 1;
		}

		/* Constructing TextComponent */
		TextComponent newLine = new TextComponent("\n");
		TextComponent header = new TextComponent(plugin.configManager.getMessage(IWLocale.HELP_HEADER, false));

		TextComponent separator = new TextComponent(plugin.configManager.getMessage(IWLocale.HELP_SEPARATOR, false, "" + pageToDisplay, "" + pages));

		TextComponent prev = new TextComponent(plugin.configManager.getMessage(IWLocale.HELP_PREV_PAGE, false));
		prev.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(prevTooltip).create()));
		prev.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/iw help " + prevPageIndex));

		TextComponent next = new TextComponent(plugin.configManager.getMessage(IWLocale.HELP_NEXT_PAGE, false));
		next.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(nextTooltip).create()));
		next.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/iw help " + nextPageIndex));

		ComponentBuilder helpPage = new ComponentBuilder(header).append(newLine);

		for (int i = lowIndex; i < highIndex && i < entries.length; i++)
		{
			String command = plugin.configManager.getMessage(entries[i].command(), false);
			String description = plugin.configManager.getMessage(entries[i].description(), false);
			String suggestion = entries[i].suggestion();

			TextComponent cmd = new TextComponent(cmdColor + command);
			cmd.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(commandTooltip).create()));
			cmd.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, suggestion));

			TextComponent desc = new TextComponent(descColor + description);
			desc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(commandTooltip).create()));
			desc.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, suggestion));

			helpPage.append(newLine).append(cmd).append(newLine).append(desc);
		}

		helpPage.append(newLine).append(newLine).append(prev).append(separator, ComponentBuilder.FormatRetention.NONE).append(next);
		sender.spigot().sendMessage(helpPage.create());
	}
}