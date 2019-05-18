package com.jstnf.infinitywarps;

import com.jstnf.infinitywarps.commands.debug.IWDebugInvCmd;
import com.jstnf.infinitywarps.commands.iw.IWCmd;
import com.jstnf.infinitywarps.commands.setwarp.SetwarpCmd;
import com.jstnf.infinitywarps.commands.warp.WarpCmd;
import com.jstnf.infinitywarps.managers.ConfigManager;
import com.jstnf.infinitywarps.managers.DatabaseManager;
import fr.minuskube.inv.InventoryManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin class for InfinityWarps
 *
 * @author jstnf
 * 16 April 2019
 */
public class InfinityWarps extends JavaPlugin
{
	public ConfigManager configManager;
	public DatabaseManager databaseManager;
	public InventoryManager inventoryManager;

	@Override
	public void onEnable()
	{
		/* Configuration */
		if (!initConfigs())
		{
			return;
		}

		/* Database */
		if (!initDatabaseManager())
		{
			return;
		}

		initInventoryManager(); /* Inventory and GUI */
		registerCommands(); /* Commands */
	}

	@Override
	public void onDisable()
	{
		getLogger().info("InfinityWarps has been disabled.");
	}

	private void registerCommands()
	{
		getLogger().info("Registering commands...");

		/* Main commands */
		IWCmd iwCmd = new IWCmd(this);
		getCommand("iw").setExecutor(iwCmd);

		SetwarpCmd setwarpCmd = new SetwarpCmd(this);
		getCommand("setwarp").setExecutor(setwarpCmd);

		WarpCmd warpCmd = new WarpCmd(this);
		getCommand("warp").setExecutor(warpCmd);

		/* Debug commands */
		IWDebugInvCmd iwDebugInvCmd = new IWDebugInvCmd(this);
		getCommand("iwdebuginv").setExecutor(iwDebugInvCmd);
	}

	/**
	 * Initialize the configuration files.
	 *
	 * @return true if the initialization was successful, false if there were errors.
	 */
	private boolean initConfigs()
	{
		getLogger().info("Initializing configuration files...");
		configManager = new ConfigManager(this);
		return configManager.generateConfigs();
	}

	private boolean initDatabaseManager()
	{
		getLogger().info("Initializing database manager...");
		databaseManager = new DatabaseManager(this);
		return databaseManager.loadDatabase();
	}

	private void initInventoryManager()
	{
		getLogger().info("Initializing inventory manager...");
		InventoryManager invMan = new InventoryManager(this);
		invMan.init();
		inventoryManager = invMan;
	}
}