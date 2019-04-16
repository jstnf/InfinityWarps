package com.jstnf.infinitywarps.old.config;

/**
 * InfinityWarps by jstnf
 * ConfigPaths
 * Contains default comments and values for the main configuration file's paths, in the order in which they appear in the file.
 *
 * @author jstnf / pokeball92870
 */
public enum ConfigPaths
{
	CONFIG_VERSION("config-version", 1, new String[] { "Config Version (do not change this value!)" }),
	CENTER_ON_BLOCK("center-on-block", true,
			new String[] { "On teleport, should InfinityWarps center the player", "on the warp location block?" }),
	SAFE_TELEPORT("safe-teleport", true,
			new String[] { "Should InfinityWarps try to find a safe location", "to warp to?",
					"If false, InfinityWarps will warp the player", "directly to the location, regardless of safety.",
					"This includes in the air, in liquids, and inside of blocks." }),
	DEFAULT_WARP_ITEM("default-warp-item", "ENDER_PEARL",
			new String[] { "By default, InfinityWarps will use this item as", "the warp item icon in the warps GUI.",
					"A complete list of possible items can be found at",
					"https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html" }),
	DEFAULT_WARP_GROUP_ITEM("default-warp-group-item", "BOOK", null),
	PER_WARP_PERMISSIONS("per-warp-permissions", false,
			new String[] { "Should players need a permission in order to use", "each warp?",
					"The permission required would be infinitywarps.warp.<warpname>" }),
	TELEPORT_DELAY("teleport-delay", 5, new String[] { "In seconds, the time before a warp is initiated.",
			"Warps are cancelled if the player moves at any time", "during this timer." }),
	TELEPORT_COOLDOWN("teleport-cooldown", 30, new String[] { "In seconds, the cooldown timer between warps." }),
	USE_ECONOMY("use-economy", false,
			new String[] { "Should InfinityWarps use the economy?", "This requires Vault as well as an economy plugin.",
					"If false, any costs associated to warps will not show", "up in menus or when using a warp." }),
	LOCALE("locale", "en-US", new String[] { "What locale should InfinityWarps use?" }),
	SHOW_COORDS("show-coords", true,
			new String[] { "Should the location of a warp ", "be shown in the warp GUI and list?" }),
	BORDER_ITEM("border-item", "LIGHT_BLUE_STAINED_GLASS_PANE", new String[] { "Default items for the warps GUI." }),
	PREV_PAGE_ITEM("prev-page-item", "FEATHER", null),
	NEXT_PAGE_ITEM("next-page-item", "FEATHER", null);

	private String path;
	private Object defaultValue;
	private String[] comments;

	ConfigPaths(String path, Object defaultValue, String[] comments)
	{
		this.path = path;
		this.defaultValue = defaultValue;
		this.comments = comments;
	}

	public String getPath()
	{
		return path;
	}

	public Object getDefaultValue()
	{
		return defaultValue;
	}

	public String[] getComments()
	{
		return comments;
	}
}