package com.jstnf.infinitywarps.database.objects;

import com.jstnf.infinitywarps.InfinityWarps;
import com.jstnf.infinitywarps.database.IWDatabaseObject;
import com.jstnf.infinitywarps.gui.IWItemIcon;
import com.jstnf.infinitywarps.settings.IWSettings;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Class for an InfinityWarps warp group
 *
 * @author jstnf
 * 21 April 2019
 */
public class WarpGroup extends IWDatabaseObject
{
	private InfinityWarps plugin;

	/* Warps */
	private ArrayList<String> warps;

	/* Item */
	private IWItemIcon itemIcon;

	/**
	 * Constructor used when creating a new warp group. (Importing Essentials warps or /warpgroup command)
	 */
	public WarpGroup(InfinityWarps plugin, String alias)
	{
		super(alias);

		this.plugin = plugin;

		/* Item */
		itemIcon = new IWItemIcon(plugin, plugin.configManager.main
				.getString(IWSettings.DEFAULT_WARP_GROUP_ICON.getPath(),
						(String) IWSettings.DEFAULT_WARP_GROUP_ICON.getDefaultValue()), alias, new ArrayList<String>(),
				false);
	}

	/**
	 * Constructor used when importing a warp group from InfinityWarps config files.
	 */
	public WarpGroup(InfinityWarps plugin, String alias, ArrayList<String> warps, String itemMaterial, String itemName,
			ArrayList<String> lore, boolean isEnchanted)
	{
		super(alias);

		this.plugin = plugin;

		this.warps = warps;

		/* Item */
		itemIcon = new IWItemIcon(plugin, itemMaterial, itemName, lore, isEnchanted);
	}

	/* GET METHODS */

	/**
	 * Get the list of warp identifiers in this warp group.
	 *
	 * @return the list of warps in the warp group (by identifier).
	 */
	public ArrayList<String> getWarps()
	{
		return warps;
	}

	/**
	 * Get the ItemStack to be used in the InfinityWarps GUIs.
	 *
	 * @return ItemStack used in InfinityWarps GUIs.
	 */
	public ItemStack getItemStack()
	{
		return itemIcon.getItemStack();
	}
}