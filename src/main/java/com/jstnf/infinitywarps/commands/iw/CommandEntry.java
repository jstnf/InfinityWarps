package com.jstnf.infinitywarps.commands.iw;

import com.jstnf.infinitywarps.settings.IWLocale;

/**
 * Enum that contains constants for JSON components in /iw help and default permissions
 *
 * @author jstnf
 * 18 May 2019
 */
public enum CommandEntry
{
	IW(IWLocale.HELP_COMMANDS_IW, IWLocale.HELP_DESCRIPTION_IW, false, "", ""),
	IW_HELP(IWLocale.HELP_COMMANDS_IW_HELP, IWLocale.HELP_DESCRIPTION_IW_HELP, true, "/iw help ", "infinitywarps.help"),
	IW_IMPORTESSWARPS(IWLocale.HELP_COMMANDS_IW_IMPORTESSWARPS, IWLocale.HELP_DESCRIPTION_IW_IMPORTESSWARPS, false, "", "infinitywarps.importesswarps"),
	IW_RELOAD(IWLocale.HELP_COMMANDS_IW_RELOAD, IWLocale.HELP_DESCRIPTION_IW_RELOAD, false, "", "infinitywarps.reload"),
	DELWARP(IWLocale.HELP_COMMANDS_DELWARP, IWLocale.HELP_DESCRIPTION_DELWARP, true, "/delwarp ", "infinitywarps.reload"),
	EDITWARP_INFO(IWLocale.HELP_COMMANDS_EDITWARP_INFO, IWLocale.HELP_DESCRIPTION_EDITWARP_INFO, false, "", "infinitywarps.editwarp.info"),
	EDITWARP_LORE_ADD(IWLocale.HELP_COMMANDS_EDITWARP_LORE_ADD, IWLocale.HELP_DESCRIPTION_EDITWARP_LORE_ADD, false, "", "infinitywarps.editwarp.lore.add"),
	EDITWARP_LORE_EDIT(IWLocale.HELP_COMMANDS_EDITWARP_LORE_EDIT, IWLocale.HELP_DESCRIPTION_EDITWARP_LORE_EDIT, false, "", "infinitywarps.editwarp.lore.edit"),
	EDITWARP_LORE_LIST(IWLocale.HELP_COMMANDS_EDITWARP_LORE_LIST, IWLocale.HELP_DESCRIPTION_EDITWARP_LORE_LIST, false, "", "infinitywarps.editwarp.lore.list"),
	EDITWARP_LORE_REMOVE(IWLocale.HELP_COMMANDS_EDITWARP_LORE_REMOVE, IWLocale.HELP_DESCRIPTION_EDITWARP_LORE_REMOVE, false, "", "infinitywarps.editwarp.lore.remove"),
	EDITWARP_RENAME(IWLocale.HELP_COMMANDS_EDITWARP_RENAME, IWLocale.HELP_DESCRIPTION_EDITWARP_RENAME, false, "", "infinitywarps.editwarp.rename"),
	EDITWARP_SETITEM(IWLocale.HELP_COMMANDS_EDITWARP_SETITEM, IWLocale.HELP_DESCRIPTION_EDITWARP_SETITEM, false, "", "infinitywarps.editwarp.setitem"),
	SETWARP(IWLocale.HELP_COMMANDS_SETWARP, IWLocale.HELP_DESCRIPTION_SETWARP, true, "/setwarp ", "infinitywarps.setwarp"),
	WARP(IWLocale.HELP_COMMANDS_WARP, IWLocale.HELP_DESCRIPTION_WARP, true, "/warp ", "infinitywarps.warp"),
	WARPGROUP_ADD(IWLocale.HELP_COMMANDS_WARPGROUP_ADD, IWLocale.HELP_DESCRIPTION_WARPGROUP_ADD, true, "/warpgroup add ", "infinitywarps.warpgroup.add"),
	WARPGROUP_ADDWARP(IWLocale.HELP_COMMANDS_WARPGROUP_ADDWARP, IWLocale.HELP_DESCRIPTION_WARPGROUP_ADDWARP, false, "", "infinitywarps.warpgroup.addwarp"),
	WARPGROUP_INFO(IWLocale.HELP_COMMANDS_WARPGROUP_INFO, IWLocale.HELP_DESCRIPTION_WARPGROUP_INFO, false, "", "infinitywarps.warpgroup.info"),
	WARPGROUP_LIST(IWLocale.HELP_COMMANDS_WARPGROUP_LIST, IWLocale.HELP_DESCRIPTION_WARPGROUP_LIST, true, "/warpgroup list ", "infinitywarps.warpgroup.list"),
	WARPGROUP_LORE_ADD(IWLocale.HELP_COMMANDS_WARPGROUP_LORE_ADD, IWLocale.HELP_DESCRIPTION_WARPGROUP_LORE_ADD, false, "", "infinitywarps.warpgroup.lore.add"),
	WARPGROUP_LORE_EDIT(IWLocale.HELP_COMMANDS_WARPGROUP_LORE_EDIT, IWLocale.HELP_DESCRIPTION_WARPGROUP_LORE_EDIT, false, "", "infinitywarps.warpgroup.lore.edit"),
	WARPGROUP_LORE_LIST(IWLocale.HELP_COMMANDS_WARPGROUP_LORE_LIST, IWLocale.HELP_DESCRIPTION_WARPGROUP_LORE_LIST, false, "", "infinitywarps.warpgroup.lore.list"),
	WARPGROUP_LORE_REMOVE(IWLocale.HELP_COMMANDS_WARPGROUP_LORE_REMOVE, IWLocale.HELP_DESCRIPTION_WARPGROUP_LORE_REMOVE, false, "", "infinitywarps.warpgroup.lore.remove"),
	WARPGROUP_REMOVE(IWLocale.HELP_COMMANDS_WARPGROUP_REMOVE, IWLocale.HELP_DESCRIPTION_WARPGROUP_REMOVE, true, "/warpgroup remove ", "infinitywarps.warpgroup.remove"),
	WARPGROUP_REMOVEWARP(IWLocale.HELP_COMMANDS_WARPGROUP_REMOVEWARP, IWLocale.HELP_DESCRIPTION_WARPGROUP_REMOVEWARP, false, "", "infinitywarps.warpgroup.removewarp"),
	WARPGROUP_RENAME(IWLocale.HELP_COMMANDS_WARPGROUP_RENAME, IWLocale.HELP_DESCRIPTION_WARPGROUP_RENAME, false, "", "infinitywarps.warpgroup.rename"),
	WARPGROUP_SETITEM(IWLocale.HELP_COMMANDS_WARPGROUP_SETITEM, IWLocale.HELP_DESCRIPTION_WARPGROUP_SETITEM, false, "", "infinitywarps.warpgroup.setitem"),
	WARPLIST(IWLocale.HELP_COMMANDS_WARPLIST, IWLocale.HELP_DESCRIPTION_WARPLIST, true, "/warplist ", "infinitywarps.warplist"),
	WARPMENU(IWLocale.HELP_COMMANDS_WARPMENU, IWLocale.HELP_DESCRIPTION_WARPMENU, false, "", "infinitywarps.warpmenu");

	private IWLocale command, description;
	private boolean useCustomSuggestion;
	private String customSuggestion, permission;

	CommandEntry(IWLocale command, IWLocale description, boolean useCustomSuggestion, String customSuggestion, String permission)
	{
		this.command = command;
		this.description = description;
		this.useCustomSuggestion = useCustomSuggestion;
		this.customSuggestion = customSuggestion;
		this.permission = permission;
	}

	public IWLocale command()
	{
		return command;
	}

	public IWLocale description()
	{
		return description;
	}

	public String suggestion()
	{
		if (useCustomSuggestion)
		{
			return customSuggestion;
		}
		else
		{
			return (String) command.getDefaultValue();
		}
	}

	public String permission()
	{
		return permission;
	}
}