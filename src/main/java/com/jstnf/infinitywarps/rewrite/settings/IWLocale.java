package com.jstnf.infinitywarps.rewrite.settings;

import org.bukkit.ChatColor;

/**
 * Holds all of the paths and default values for locale values in the localization files.
 *
 * @author jstnf
 * 20 April 2019
 */
public enum IWLocale
{
	INFINITYWARPS("infinitywarps.infinitywarps", "InfinityWarps"),

	WARP_SET("infinitywarps.warp-set", "&fWarp {0}&f set."),
	WARP_SET_WITH_COST("infinitywarps.warp-set-with-cost", "&fWarp {0}&f set with cost {1}&f."),
	WARP_SIMILAR_NAME("infinitywarps.warp-similar-name", "&cA warp with a similar name already exists."),
	WARP_SET_ERROR_OCCURRED("infinitywarps.warp-set-error-occurred", "&cAn error occurred setting the warp. Please see console."),

	UNKNOWN_SUBCOMMAND("infinitywarps.unknown-subcommand", "&fUnknown subcommand. Type &b/iw help&f for command help."),
	FEATURE_NOT_IMPLEMENTED("infinitywarps.feature-not-implemented", "&cThis feature is currently not implemented!"),
	MUST_BE_PLAYER("infinitywarps.must-be-player", "&cYou must be a player to run this command."),

	NO_PERMISSION_SETWARP("infinitywarps.no-permission-setwarp", "&cYou do not have permission to set a warp."),
	NO_PERMISSION_WARP("infinitywarps.no-permission-warp", "&cYou do not have permission to warp."),

	TEMPORARY("infinitywarps.temporary", "&fThis is a temporary test message."),

	CONFIG_VERSION("do-not-change-this-value.config-version", 1);

	private String path;
	private Object value;

	IWLocale(String path, Object value)
	{
		this.path = path;
		this.value = value;
	}

	public String getPath()
	{
		return path;
	}

	public Object getDefaultValue()
	{
		return value;
	}

	public static String format(String s, String... replace)
	{
		String manip = s;
		int index = 0;
		for (String rep : replace)
		{
			manip = manip.replaceAll("\\{" + index + "\\}", rep);
			index++;
		}
		return ChatColor.translateAlternateColorCodes('&', manip);
	}}