package com.jstnf.infinitywarps.config;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.config.log_out.SimpleConfig;

/**
 * InfinityWarps by jstnf
 * LocaleManager
 * Container for the language configuration file.
 *
 * @author jstnf / pokeball92870
 */
public class LocaleManager
{
	private IWMain plugin;
	private SimpleConfig lang;

	public LocaleManager(IWMain plugin)
	{
		this.plugin = plugin;
		lang = null;
	}

	public boolean loadLocale(String locale)
	{
		/* Need to fix so that it checks for a current lang file based on the main config,
		as well as update with newer config versions */
		try
		{
			plugin.getLogger().info("Attempting to load locale " + locale + "...");
			plugin.saveResource("lang_" + locale + ".yml", true);
			lang = plugin.getConfigManager().manager.getNewConfig("lang_" + locale + ".yml");
			return true;
		}
		catch (Exception e)
		{
			plugin.getLogger().warning("There was an error loading the locale " + locale);
			plugin.getLogger().warning("Defaulting to current locale!");
			e.printStackTrace();
			return false;
		}
	}

	public SimpleConfig getLangConfig()
	{
		return lang;
	}
}