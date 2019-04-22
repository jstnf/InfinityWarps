package com.jstnf.infinitywarps.rewrite.managers;

import com.jstnf.infinitywarps.rewrite.settings.IWSettings;
import com.jstnf.infinitywarps.rewrite.InfinityWarps;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

/**
 * Manager for configuration files
 *
 * @author jstnf
 * 18 April 2019
 */
public class ConfigManager
{
	private final InfinityWarps plugin;
	private final int MAIN_CONFIG_VERSION = 1, LOCALE_VERSION = 1;
	private File mainConfigFile, langConfigFile;
	private String loadedLocale;

	public YamlConfiguration main, lang;

	public ConfigManager(InfinityWarps plugin)
	{
		this.plugin = plugin;
		loadedLocale = "en_US";
	}

	private void generateDataFolders()
	{
		if (!plugin.getDataFolder().exists())
		{
			plugin.getDataFolder().mkdir();
		}

		File warpFolder = new File(plugin.getDataFolder() + File.separator + "warps");
		File warpGroupFolder = new File(plugin.getDataFolder() + File.separator + "warpgroups");

		if (!warpFolder.exists())
		{
			warpFolder.mkdir();
		}

		if (!warpGroupFolder.exists())
		{
			warpGroupFolder.mkdir();
		}
	}

	/**
	 * Generates or version checks the main configuration file (config.yml).
	 * If the process fails, the plugin will be disabled.
	 *
	 * @return true if successfully created/checked configs, false if there is an error.
	 */
	private boolean generateMainConfig()
	{
		main = new YamlConfiguration();
		mainConfigFile = new File(plugin.getDataFolder(), "config.yml");

		/* Check if config.yml has been generated already */
		if (!mainConfigFile.exists())
		{
			/* config.yml hasn't been generated yet */
			try
			{
				/* Try generating config.yml from resources and assigning it */
				plugin.saveResource("config.yml", false);
				main.load(mainConfigFile);
			}
			catch (Exception e)
			{
				/* Something went wrong! */
				e.printStackTrace();
				plugin.getLogger().severe("Could not generate config files.");
				plugin.getLogger().severe("InfinityWarps will now be disabled.");
				plugin.getServer().getPluginManager().disablePlugin(plugin);
				return false;
			}
		}
		else
		{
			/* Check config version */
			try
			{
				/* Match config version */
				main.load(mainConfigFile);
				int currentConfigVersion = main.getInt(IWSettings.CONFIG_VERSION.getPath(), 0);
				if (currentConfigVersion != MAIN_CONFIG_VERSION)
				{
					/* Uh oh! The config version does not match! */
					plugin.getLogger().warning("Your config version (" + currentConfigVersion
							+ ") does not match the current config version (" + MAIN_CONFIG_VERSION + ").");
					plugin.getLogger().warning("Your current configuration will be saved to config.yml.backup.");

					/* Begin config backup */
					plugin.getLogger().info("Commencing backup...");

					File backup = new File(plugin.getDataFolder(), "config.yml.backup");
					backup.createNewFile();

					BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(mainConfigFile)));
					BufferedWriter out = new BufferedWriter(new FileWriter(backup, true));

					String currLine = "";
					while ((currLine = in.readLine()) != null)
					{
						out.write(currLine);
						out.newLine();
					}

					in.close();
					out.close();

					plugin.getLogger().info("Configuration backup successful!");

					/* Generate new config.yml */
					plugin.getLogger().info("Generating a new config.yml...");
					plugin.saveResource("config.yml", true);

					main.load(mainConfigFile);
				}
			}
			catch (Exception e)
			{
				/* Something went wrong loading the config! */
				e.printStackTrace();
				plugin.getLogger().severe("Could not load config files.");
				plugin.getLogger().severe("InfinityWarps will now be disabled.");
				plugin.getServer().getPluginManager().disablePlugin(plugin);
				return false;
			}
		}
		return true;
	}

	/**
	 * Generates or version checks the main configuration file (config.yml).
	 * If the process fails, the plugin will be disabled.
	 *
	 * @return true if successfully created/checked configs, false if there is an error.
	 */
	private boolean generateLangConfig()
	{
		lang = new YamlConfiguration();
		langConfigFile = new File(plugin.getDataFolder() + File.separator + "locales", loadedLocale + ".yml");

		/* Check if locale file has been generated already */
		if (!langConfigFile.exists())
		{
			/* Locale file hasn't been generated yet */
			try
			{
				/* Try generating locale file from resources and assigning it */
				plugin.saveResource("locales" + File.separator + loadedLocale + ".yml", false);
				lang.load(langConfigFile);
			}
			catch (Exception e)
			{
				/* Could not find locale! */
				e.printStackTrace();
				plugin.getLogger().severe("Could not generate locale files.");
				plugin.getLogger().severe("The locale " + loadedLocale + " was not found in the plugin's resources.");
				plugin.getLogger().info("InfinityWarps will now default to en_US.");
				loadedLocale = "en_US";
				try
				{
					plugin.saveResource("locales" + File.separator + loadedLocale + ".yml", false);
					lang.load(langConfigFile);
					return true;
				}
				catch (Exception e2)
				{
					/* Something went wrong! */
					e2.printStackTrace();
					plugin.getLogger().severe("The locale could not be loaded.");
					plugin.getLogger().severe("Contact the developer!");
					plugin.getLogger().severe("InfinityWarps will now be disabled.");
					return false;
				}
			}
		}
		else
		{
			/* Check locale version */
			try
			{
				/* Match locale version */
				lang.load(langConfigFile);
				int currentConfigVersion = lang.getInt(IWSettings.CONFIG_VERSION.getPath(), 0);
				if (currentConfigVersion != LOCALE_VERSION)
				{
					/* Uh oh! The locale version does not match! */
					plugin.getLogger().warning("Your locale version (" + currentConfigVersion
							+ ") does not match the current locale version (" + LOCALE_VERSION + ").");
					plugin.getLogger()
							.warning("Your current configuration will be saved to " + loadedLocale + ".yml.backup.");

					/* Begin locale backup */
					plugin.getLogger().info("Commencing backup...");

					File backup = new File(plugin.getDataFolder() + File.separator + "locales",
							loadedLocale + ".yml.backup");
					backup.createNewFile();

					BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(mainConfigFile)));
					BufferedWriter out = new BufferedWriter(new FileWriter(backup, true));

					String currLine = "";
					while ((currLine = in.readLine()) != null)
					{
						out.write(currLine);
						out.newLine();
					}

					in.close();
					out.close();

					plugin.getLogger().info("Locale backup successful!");

					/* Generate new config.yml */
					plugin.getLogger().info("Generating a new locale file...");
					plugin.saveResource("locales" + File.separator + loadedLocale + ".yml", true);

					lang.load(langConfigFile);
				}
			}
			catch (Exception e)
			{
				/* Something went wrong loading the locale! */
				e.printStackTrace();
				plugin.getLogger().severe("Could not load locale files.");
				plugin.getLogger().severe("InfinityWarps will now be disabled.");
				plugin.getServer().getPluginManager().disablePlugin(plugin);
				return false;
			}
		}
		return true;
	}

	/**
	 * Try generating/updating all InfinityWarps configs.
	 *
	 * @return true if generation/updating was successful, false otherwise.
	 */
	public boolean generateConfigs()
	{
		generateDataFolders();
		boolean mainSuccess = generateMainConfig();
		if (mainSuccess)
		{
			loadedLocale = main.getString(IWSettings.LOCALE.getPath(), (String) IWSettings.LOCALE.getDefaultValue());
			return generateLangConfig();
		}
		return false;
	}

	/**
	 * Load a YAML configuration file
	 *
	 * @param path - Path to the configuration file
	 * @return FileConfiguration if the file was found, null if the file was not found
	 */
	public FileConfiguration loadConfiguration(String path)
	{
		File f = new File(path);
		if (!f.exists())
		{
			return null;
		}
		else
		{
			try
			{
				YamlConfiguration yamlConfiguration = new YamlConfiguration();
				yamlConfiguration.load(f);
				return yamlConfiguration;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
	}
}