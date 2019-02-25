package com.jstnf.infinitywarps.utils.config;

import com.jstnf.infinitywarps.IWMain;

import java.io.File;

public class LocaleManager
{
    private IWMain plugin;
    private SimpleConfigManager langManager;
    private String currentLocale;
    private SimpleConfig lang;

    public LocaleManager(IWMain plugin)
    {
        this.plugin = plugin;
        this.langManager = new SimpleConfigManager(plugin);

        /* Assume that config has been loaded already. */
        this.currentLocale = plugin.configs.main.getString("locale", "en_US");
    }

    public LocaleManager(IWMain plugin, String locale)
    {
        this.plugin = plugin;
        this.langManager = new SimpleConfigManager(plugin);
        this.currentLocale = locale;
    }

    public boolean loadLocale(String newLocale)
    {
        try
        {
            /* TO IMPLEMENT */
            plugin.getLogger().info("Attempting to load locale " + newLocale + "...");
            plugin.saveResource("lang_" + newLocale + ".yml", true);
            currentLocale = newLocale;
            lang = plugin.configs.manager.getNewConfig("locale" + File.separator + "lang_" + newLocale);
            return true;
        }
        catch (Exception e)
        {
            plugin.getLogger().warning("There was an error loading the locale " + newLocale);
            plugin.getLogger().warning("Defaulting to current locale!");
            e.printStackTrace();
            return false;
        }
    }
}
