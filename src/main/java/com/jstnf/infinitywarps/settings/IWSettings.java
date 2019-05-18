package com.jstnf.infinitywarps.settings;

/**
 * Holds all of the settings and default config values for InfinityWarps
 *
 * @author jstnf
 * 19 April 2019
 */
public enum IWSettings
{
	PER_WARP_PERMISSIONS("infinitywarps.per-warp-permissions", false),
	CENTER_ON_BLOCK("infinitywarps.center-on-block", true),
	SHOW_COORDS("infinitywarps.show-coords", true),
	LOCALE("infinitywarps.locale", "en_US"),
	SHOW_NO_PERMISSION_COMMANDS("infinitywarps.snow-no-permission-commands", true),
	ENTRIES_PER_HELP_PAGE("infinitywarps.entries-per-help-page", 7),

	DEFAULT_WARP_ICON("item.default-warp-icon", "ENDER_PEARL"),
	DEFAULT_WARP_GROUP_ICON("item.default-warp-group-icon", "BOOK"),
	DEFAULT_BORDER("item.default-border", "GRAY_STAINED_GLASS_PANE"),

	CONFIG_VERSION("do-not-change-this-value.config-version", 1);

	private String path;
	private Object value;

	IWSettings(String path, Object value)
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
}