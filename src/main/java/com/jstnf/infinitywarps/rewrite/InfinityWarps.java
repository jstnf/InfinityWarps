package com.jstnf.infinitywarps.rewrite;

import com.jstnf.infinitywarps.rewrite.commands.IWCmd;
import com.jstnf.infinitywarps.rewrite.commands.SetwarpCmd;
import com.jstnf.infinitywarps.rewrite.commands.debug.IWDebugInvCmd;
import com.jstnf.infinitywarps.rewrite.managers.ConfigManager;
import com.jstnf.infinitywarps.rewrite.managers.DatabaseManager;
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

	public InfinityWarps()
	{

	}

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

		/* Inventory and GUI */
		initInventoryManager();

		/* Commands */
		registerCommands();
	}

	@Override
	public void onDisable()
	{

	}

	private void registerCommands()
	{
		getLogger().info("Registering commands...");
		/* Main commands */
		IWCmd iwCmd = new IWCmd(this);
		getCommand("iw").setExecutor(iwCmd);

		SetwarpCmd setwarpCmd = new SetwarpCmd(this);
		getCommand("setwarp").setExecutor(setwarpCmd);

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