package com.jstnf.infinitywarps.old.config;

import com.jstnf.infinitywarps.old.IWMain;
import com.jstnf.infinitywarps.old.config.log_out.SimpleConfig;

import java.io.File;

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
		try
		{
			plugin.getLogger().info("Attempting to load locale " + locale + "...");

			/* Need to fix so that it checks for a current lang file based on the main config, as well as update with newer config versions */
			/* This needs to change, look for existing locales and update instead from grabbing resource every time! */
			plugin.saveResource("old_resources/locales" + File.separator + "lang_" + locale + ".yml", true);
			/* -------------- */

			lang = plugin.getConfigManager().manager
					.getNewConfig("old_resources/locales" + File.separator + "lang_" + locale + ".yml");
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			plugin.getLogger().warning("There was an error loading the locale " + locale);
			plugin.getLogger().warning("Defaulting to current locale!");
			return false;
		}
	}

	public SimpleConfig getLangConfig()
	{
		return lang;
	}

	/* TO IMPLEMENT! */
	public String getMessage(String message, String... replace)
	{
		return null;
	}
}