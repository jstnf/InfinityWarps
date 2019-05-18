package com.jstnf.infinitywarps.rewrite.settings;

import org.bukkit.ChatColor;

/**
 * Holds all of the paths and default values for locale values in the localization files
 *
 * @author jstnf
 * 20 April 2019
 */
public enum IWLocale
{
	INFINITYWARPS("infinitywarps.infinitywarps", "InfinityWarps"),
	PREFIX("infinitywarps.prefix", "&7[&bInfinity&dWarps&7] "),

	WARP_SUCCESS("infinitywarps.warp-success", "&fWarped to {0}&f."),

	WARP_NOT_FOUND("infinitywarps.warp-not-found", "&cCould not find the warp {0}&c."),

	WARP_SET("infinitywarps.warp-set", "&fWarp {0}&f set."),
	WARP_SET_WITH_COST("infinitywarps.warp-set-with-cost", "&fWarp {0}&f set with cost {1}&f."),
	WARP_SIMILAR_NAME("infinitywarps.warp-similar-name", "&cA warp with a similar name already exists."),
	WARP_SET_ERROR_OCCURRED("infinitywarps.warp-set-error-occurred",  "&cAn error occurred setting the warp. Please see console."),

	UNKNOWN_SUBCOMMAND("infinitywarps.unknown-subcommand", "&fUnknown subcommand. Type &b/iw help&f for command help."),
	FEATURE_NOT_IMPLEMENTED("infinitywarps.feature-not-implemented", "&cThis feature is currently not implemented!"),
	MUST_BE_PLAYER("infinitywarps.must-be-player", "&cYou must be a player to run this command."),

	NO_PERMISSION_SETWARP("infinitywarps.no-permission-setwarp", "&cYou do not have permission to set a warp."),
	NO_PERMISSION_WARP("infinitywarps.no-permission-warp", "&cYou do not have permission to warp."),

	TEMPORARY("infinitywarps.temporary", "&fThis is a temporary test message."),

	HELP_COMMAND_COLOR("infinitywarps.help.colors.command-color", "&b"),
	HELP_DESCRIPTION_COLOR("infinitywarps.help.colors.description-color", "&f&o"),

	HELP_TOOLTIP_COMMAND("infinitywarps.help.tooltips.command", "&bClick me to insert the command into chat!"),
	HELP_TOOLTIP_PREV_PAGE("infinitywarps.help.tooltips.prev-page", "&bClick me to go to the previous page!"),
	HELP_TOOLTIP_NEXT_PAGE("infinitywarps.help.tooltips.next-page", "&bClick me to go to the next page!"),
	HELP_TOOLTIP_START_PAGE("infinitywarps.help.tooltips.start-page", "&bClick me to go to the starting page!"),

	HELP_HEADER("infinitywarps.help.page.header", "&b&lInfinity&d&lWarps &f&lCommand Help"),
	HELP_PREV_PAGE("infinitywarps.help.page.prev-page", "&f&lPrev"),
	HELP_SEPARATOR("infinitywarps.help.page.separator", "&b ----[{0}&b/{1}&b]---- "),
	HELP_NEXT_PAGE("infinitywarps.help.page.next-page", "&f&lNext"),

	HELP_COMMANDS_IW("infinitywarps.help.commands.iw", "/iw"),
	HELP_COMMANDS_IW_HELP("infinitywarps.help.commands.iw-help", "/iw help [page]"),
	HELP_COMMANDS_IW_IMPORTESSWARPS("infinitywarps.help.commands.iw-importesswarps", "/iw importesswarps [groupSeparator]"),
	HELP_COMMANDS_IW_RELOAD("infinitywarps.help.commands.iw-reload", "/iw reload"),
	HELP_COMMANDS_DELWARP("infinitywarps.help.commands.delwarp", "/delwarp <warp>"),
	HELP_COMMANDS_EDITWARP_INFO("infinitywarps.help.commands.editwarp-info", "/editwarp info <warp>"),
	HELP_COMMANDS_EDITWARP_LORE_ADD("infinitywarps.help.commands.editwarp-lore-add", "/editwarp lore <warp> add <line>"),
	HELP_COMMANDS_EDITWARP_LORE_EDIT("infinitywarps.help.commands.editwarp-lore-edit", "/editwarp lore <warp> edit <#> <line>"),
	HELP_COMMANDS_EDITWARP_LORE_LIST("infinitywarps.help.commands.editwarp-lore-list", "/editwarp lore <warp> list"),
	HELP_COMMANDS_EDITWARP_LORE_REMOVE("infinitywarps.help.commands.editwarp-lore-remove", "/editwarp lore <warp> remove <#>"),
	HELP_COMMANDS_EDITWARP_RENAME("infinitywarps.help.commands.editwarp-rename", "/editwarp rename <warp> <newName>"),
	HELP_COMMANDS_EDITWARP_SETITEM("infinitywarps.help.commands.editwarp-setitem", "/editwarp setitem <warp> [hand|inv]"),
	HELP_COMMANDS_SETWARP("infinitywarps.help.commands.setwarp", "/setwarp <warp>"),
	HELP_COMMANDS_WARP("infinitywarps.help.commands.warp", "/warp <warp>"),
	HELP_COMMANDS_WARPGROUP_ADD("infinitywarps.help.commands.warpgroup-add", "/warpgroup add <warpgroup>"),
	HELP_COMMANDS_WARPGROUP_ADDWARP("infinitywarps.help.commands.warpgroup-addwarp", "/warpgroup addwarp <warpgroup> <warp> [warp…]"),
	HELP_COMMANDS_WARPGROUP_INFO("infinitywarps.help.commands.warpgroup-info", "/warpgroup info <warpgroup>"),
	HELP_COMMANDS_WARPGROUP_LIST("infinitywarps.help.commands.warpgroup-list", "/warpgroup list [page]"),
	HELP_COMMANDS_WARPGROUP_LORE_ADD("infinitywarps.help.commands.warpgroup-lore-add","/warpgroup lore <warpgroup> add <line>"),
	HELP_COMMANDS_WARPGROUP_LORE_EDIT("infinitywarps.help.commands.warpgroup-lore-edit","/warpgroup lore <warpgroup> edit <#> <line>"),
	HELP_COMMANDS_WARPGROUP_LORE_LIST("infinitywarps.help.commands.warpgroup-lore-list","/warpgroup lore <warpgroup> list"),
	HELP_COMMANDS_WARPGROUP_LORE_REMOVE("infinitywarps.help.commands.warpgroup-lore-remove","/warpgroup lore <warpgroup> remove <#>"),
	HELP_COMMANDS_WARPGROUP_REMOVE("infinitywarps.help.commands.warpgroup.remove", "/warpgroup remove <warpgroup>"),
	HELP_COMMANDS_WARPGROUP_REMOVEWARP("infinitywarps.help.commands.warpgroup.removewarp", "/warpgroup removewarp <warpgroup> <warp> [warp…]"),
	HELP_COMMANDS_WARPGROUP_RENAME("infinitywarps.help.commands.warpgroup.rename", "/warpgroup rename <warpgroup> <newName>"),
	HELP_COMMANDS_WARPGROUP_SETITEM("infinitywarps.help.commands.warpgroup.setitem", "/warpgroup setitem <warpgroup> [hand|inv]"),
	HELP_COMMANDS_WARPLIST("infinitywarps.help.commands.warplist", "/warplist [page]"),
	HELP_COMMANDS_WARPMENU("infinitywarps.help.commands.warpmenu", "/warpmenu"),

	HELP_DESCRIPTION_IW("infinitywarps.help.description.iw", ""),
	HELP_DESCRIPTION_IW_HELP("infinitywarps.help.description.iw-help", ""),
	HELP_DESCRIPTION_IW_IMPORTESSWARPS("infinitywarps.help.description.iw-importesswarps", ""),
	HELP_DESCRIPTION_IW_RELOAD("infinitywarps.help.description.iw-reload", ""),
	HELP_DESCRIPTION_DELWARP("infinitywarps.help.description.delwarp", ""),
	HELP_DESCRIPTION_EDITWARP_INFO("infinitywarps.help.description.editwarp-info", ""),
	HELP_DESCRIPTION_EDITWARP_LORE_ADD("infinitywarps.help.description.editwarp-lore-add", ""),
	HELP_DESCRIPTION_EDITWARP_LORE_EDIT("infinitywarps.help.description.editwarp-lore-edit", ""),
	HELP_DESCRIPTION_EDITWARP_LORE_LIST("infinitywarps.help.description.editwarp-lore-list", ""),
	HELP_DESCRIPTION_EDITWARP_LORE_REMOVE("infinitywarps.help.description.editwarp-lore-remove", ""),
	HELP_DESCRIPTION_EDITWARP_RENAME("infinitywarps.help.description.editwarp-rename", ""),
	HELP_DESCRIPTION_EDITWARP_SETITEM("infinitywarps.help.description.editwarp-setitem", ""),
	HELP_DESCRIPTION_SETWARP("infinitywarps.help.description.setwarp", ""),
	HELP_DESCRIPTION_WARP("infinitywarps.help.description.warp", ""),
	HELP_DESCRIPTION_WARPGROUP_ADD("infinitywarps.help.description.warpgroup-add", ""),
	HELP_DESCRIPTION_WARPGROUP_ADDWARP("infinitywarps.help.description.warpgroup-addwarp", ""),
	HELP_DESCRIPTION_WARPGROUP_INFO("infinitywarps.help.description.warpgroup-info", ""),
	HELP_DESCRIPTION_WARPGROUP_LIST("infinitywarps.help.description.warpgroup-list", ""),
	HELP_DESCRIPTION_WARPGROUP_LORE_ADD("infinitywarps.help.description.warpgroup-lore-add",""),
	HELP_DESCRIPTION_WARPGROUP_LORE_EDIT("infinitywarps.help.description.warpgroup-lore-edit",""),
	HELP_DESCRIPTION_WARPGROUP_LORE_LIST("infinitywarps.help.description.warpgroup-lore-list",""),
	HELP_DESCRIPTION_WARPGROUP_LORE_REMOVE("infinitywarps.help.description.warpgroup-lore-remove",""),
	HELP_DESCRIPTION_WARPGROUP_REMOVE("infinitywarps.help.description.warpgroup.remove", ""),
	HELP_DESCRIPTION_WARPGROUP_REMOVEWARP("infinitywarps.help.description.warpgroup.removewarp", ""),
	HELP_DESCRIPTION_WARPGROUP_RENAME("infinitywarps.help.description.warpgroup.rename", ""),
	HELP_DESCRIPTION_WARPGROUP_SETITEM("infinitywarps.help.description.warpgroup.setitem", ""),
	HELP_DESCRIPTION_WARPLIST("infinitywarps.help.description.warplist", ""),
	HELP_DESCRIPTION_WARPMENU("infinitywarps.help.description.warpmenu", ""),

	CONFIG_VERSION("do-not-change-this-value.config-version", 1);

	private String path;
	private Object defaultValue;

	IWLocale(String path, Object defaultValue)
	{
		this.path = path;
		this.defaultValue = defaultValue;
	}

	public String getPath()
	{
		return path;
	}

	public Object getDefaultValue()
	{
		return defaultValue;
	}

	public static String format(String s, String... args)
	{
		String manip = s;
		int index = 0;
		for (String rep : args)
		{
			String replaceMarker = "{" + index + "}";

			int indexOf = manip.indexOf(replaceMarker);
			if (indexOf != -1)
			{
				manip = manip.substring(0, indexOf) + "&f" /* Replace color back to white */ + rep + manip
						.substring(indexOf + replaceMarker.length());
			}

			index++;
		}
		return ChatColor.translateAlternateColorCodes('&', manip);
	}
}