package com.jstnf.infinitywarps.rewrite;

/**
 * Holds all of the settings and default config values for InfinityWarps
 *
 * @author jstnf
 * 19 April 2019
 */
public enum IWSettings
{
	PER_WARP_PERMISSIONS("infinitywarps.per-warp-permissions", false),
	SHOW_COORDS("infinitywarps.show-coords", true),
	LOCALE("infinitywarps.locale", "en_US"),
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