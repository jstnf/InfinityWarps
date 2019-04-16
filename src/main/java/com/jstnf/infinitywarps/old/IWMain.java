package com.jstnf.infinitywarps.old;

import com.jstnf.infinitywarps.old.config.ConfigManager;
import com.jstnf.infinitywarps.old.config.ConfigPaths;
import com.jstnf.infinitywarps.old.config.LocaleManager;
import com.jstnf.infinitywarps.old.database.WarpGroupManager;
import com.jstnf.infinitywarps.old.database.WarpManager;
import com.jstnf.infinitywarps.old.economy.EconomyManager;
import com.jstnf.infinitywarps.old.inventory.InventoryManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * InfinityWarps by jstnf
 * IWMain
 * Main plugin class for InfinityWarps.
 *
 * @author jstnf / pokeball92870
 */
public class IWMain extends JavaPlugin
{
	public final int CONFIG_VERSION = 1;
	public boolean useEconomy;
	public boolean isLegacy;

	private PluginDescriptionFile pdf;

	private Economy economy;

	private ConfigManager configManager;
	private EconomyManager economyManager;
	private InventoryManager inventoryManager;
	private LocaleManager localeManager;
	private WarpGroupManager warpGroupManager;
	private WarpManager warpManager;

	public IWMain()
	{
		pdf = this.getDescription();
	}

	public void onEnable()
	{
		/* Legacy version (pre 1.13) check */
		isLegacy = Material.getMaterial("RED_WOOL") == null;

		/* Configuration */
		getLogger().info("Initializing configuration files...");
		configManager = new ConfigManager(this);
		getLogger().info("Initializing language files...");
		localeManager = new LocaleManager(this);
		localeManager.loadLocale(configManager.main
				.getString(ConfigPaths.LOCALE.getPath(), (String) ConfigPaths.LOCALE.getDefaultValue()));

		/* Economy */
		getLogger().info("Initializing economy...");
		boolean useEconomyConfig = configManager.main
				.getBoolean(ConfigPaths.USE_ECONOMY.getPath(), (boolean) ConfigPaths.USE_ECONOMY.getDefaultValue());
		boolean vaultLoaded = Bukkit.getPluginManager().isPluginEnabled("Vault");
		if (useEconomyConfig)
		{
			if (!vaultLoaded || !setupEconomy())
			{
				useEconomy = false;
				getLogger().warning("An attempt was made to use the economy.");
				getLogger().warning("However, either Vault or a Vault dependent economy plugin were not found.");
				getLogger().warning(
						"Please restart the plugin with the missing plugin(s) or set 'useEconomy' in config.yml to false.");
				getLogger().info("InfinityWarps is disabling...");
				Bukkit.getServer().getPluginManager().disablePlugin(this);
				return;
			}
			else
			{
				useEconomy = true;
				economyManager = new EconomyManager(economy);
				getLogger().info("Economy features successfully initialized.");
			}
		}
		else
		{
			getLogger().info("useEconomy = false");
			getLogger().info("Economy is disabled.");
			useEconomy = false;
		}

		/* Warp & Warp Group Data */
		getLogger().info("Parsing warp data...");
		warpManager = new WarpManager(this);
		warpGroupManager = new WarpGroupManager(this);
		if (!warpManager.importWarps())
		{
			getLogger().severe("There was an error importing warps.");
			getLogger().severe("InfinityWarps is disabling...");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			return;
		}
		if (!warpGroupManager.importGroups())
		{
			getLogger().severe("There was an error importing warp groups");
			getLogger().severe("InfinityWarps is disabling...");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			return;
		}

		/* GUIs */
		getLogger().info("Initializing inventory and inventory listeners...");
		inventoryManager = new InventoryManager(this, warpManager.getWarps(), warpGroupManager.getWarpGroups());
		Bukkit.getPluginManager().registerEvents(inventoryManager, this);

		/* Commands */
		getLogger().info("Hooking commands...");
		IWUtils.setupListeners(this);

		/* etc. */
		getLogger().info("InfinityWarps v" + pdf.getVersion() + " successfully enabled.");
	}

	public void onDisable()
	{
		this.getLogger().info("InfinityWarps successfully disabled.");
	}

	/**
	 * Find a Vault dependent economy plugin.
	 * Code courtesy of VaultAPI
	 *
	 * @return if a plugin was found
	 */
	private boolean setupEconomy()
	{
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null)
		{
			economy = economyProvider.getProvider();
		}

		return (economy != null);
	}

	public EconomyManager getEconomyManager()
	{
		if (!useEconomy)
		{
			return null;
		}
		else
		{
			return economyManager;
		}
	}

	public InventoryManager getInventoryManager()
	{
		return inventoryManager;
	}

	public WarpManager getWarpManager()
	{
		return warpManager;
	}

	public ConfigManager getConfigManager()
	{
		return configManager;
	}

	public LocaleManager getLocaleManager()
	{
		return localeManager;
	}

	public WarpGroupManager getWarpGroupManager()
	{
		return warpGroupManager;
	}
}