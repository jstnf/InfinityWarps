package com.jstnf.infinitywarps.old.inventory;

import com.jstnf.infinitywarps.old.IWMain;
import com.jstnf.infinitywarps.old.config.ConfigPaths;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * InfinityWarps by jstnf
 * ConstantItemStacks
 * Util class for grabbing commonly used ItemStacks in inventories, uses config and locale for customization.
 *
 * @author jstnf / pokeball92870
 */
public class ConstantItemStacks
{
	/**
	 * Returns the default ItemStack for the borders of the warp selection menu.
	 */
	public static ItemStack border(IWMain plugin)
	{
		String material = plugin.getConfigManager().main
				.getString(ConfigPaths.BORDER_ITEM.getPath(), (String) ConfigPaths.BORDER_ITEM.getDefaultValue());
		ItemStack border = new ItemStack(Material.getMaterial(material));
		ItemMeta metaRef = border.getItemMeta();
		metaRef.setDisplayName(" ");
		border.setItemMeta(metaRef);
		return border;
	}

	/**
	 * Returns the default ItemStack for the border identifier of the warp selection menu.
	 */
	public static ItemStack borderIdentifier(IWMain plugin, String identifier, int index)
	{
		String material = plugin.getConfigManager().main
				.getString(ConfigPaths.BORDER_ITEM.getPath(), (String) ConfigPaths.BORDER_ITEM.getDefaultValue());
		ItemStack borderIdentifier = new ItemStack(Material.getMaterial(material));
		ItemMeta metaRef = borderIdentifier.getItemMeta();
		metaRef.setDisplayName(" ");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("" + ChatColor.DARK_GRAY + identifier);
		lore.add("" + ChatColor.DARK_GRAY + index);
		metaRef.setLore(lore);
		borderIdentifier.setItemMeta(metaRef);
		return borderIdentifier;
	}

	/**
	 * Returns the default ItemStack for the 'previous page' button of the warp selection menu.
	 */
	public static ItemStack previousPage(IWMain plugin)
	{
		String material = plugin.getConfigManager().main
				.getString(ConfigPaths.PREV_PAGE_ITEM.getPath(), (String) ConfigPaths.PREV_PAGE_ITEM.getDefaultValue());
		String itemName = plugin.getLocaleManager().getLangConfig().getString("invPrevPage", "&fPrevious Page");
		ItemStack prevPage = new ItemStack(Material.getMaterial(material));
		ItemMeta metaRef = prevPage.getItemMeta();
		metaRef.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));
		prevPage.setItemMeta(metaRef);
		return prevPage;
	}

	/**
	 * Returns the default ItemStack for the 'next page' button of the warp selection menu.
	 */
	public static ItemStack nextPage(IWMain plugin)
	{
		String material = plugin.getConfigManager().main
				.getString(ConfigPaths.NEXT_PAGE_ITEM.getPath(), (String) ConfigPaths.NEXT_PAGE_ITEM.getDefaultValue());
		String itemName = plugin.getLocaleManager().getLangConfig().getString("invNextPage", "&Next Page");
		ItemStack nextPage = new ItemStack(Material.getMaterial(material));
		ItemMeta metaRef = nextPage.getItemMeta();
		metaRef.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));
		nextPage.setItemMeta(metaRef);
		return nextPage;
	}

	/**
	 * Returns the default ItemStack for the 'All Warps' indicator of the warp selection menu.
	 */
	public static ItemStack allWarps(IWMain plugin)
	{
		ItemStack allWarps = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
		ItemMeta metaRef = allWarps.getItemMeta();
		metaRef.setDisplayName(ChatColor.WHITE + "All Warps");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("" + ChatColor.GRAY + ChatColor.ITALIC + "Click to change category.");
		metaRef.setLore(lore);
		allWarps.setItemMeta(metaRef);
		return allWarps;
	}

	/**
	 * Returns the default ItemStack for the 'Public Warps' indicator of the warp selection menu.
	 */
	public static ItemStack publicWarps(IWMain plugin)
	{
		ItemStack publicWarps = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		ItemMeta metaRef = publicWarps.getItemMeta();
		metaRef.setDisplayName(ChatColor.GREEN + "Public Warps");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("" + ChatColor.GRAY + ChatColor.ITALIC + "Click to change category.");
		metaRef.setLore(lore);
		publicWarps.setItemMeta(metaRef);
		return publicWarps;
	}

	/**
	 * Returns the default ItemStack for the 'Private Warps' indicator of the warp selection menu.
	 */
	public static ItemStack privateWarps(IWMain plugin)
	{
		ItemStack privateWarps = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		ItemMeta metaRef = privateWarps.getItemMeta();
		metaRef.setDisplayName(ChatColor.RED + "Private Warps");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("" + ChatColor.GRAY + ChatColor.ITALIC + "Click to change category.");
		metaRef.setLore(lore);
		privateWarps.setItemMeta(metaRef);
		return privateWarps;
	}

	/**
	 * Returns the default ItemStack for the 'Warp Groups' indicator of the warp selection menu.
	 */
	public static ItemStack groupList(IWMain plugin)
	{
		ItemStack groupList = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
		ItemMeta metaRef = groupList.getItemMeta();
		metaRef.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp Groups");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("" + ChatColor.GRAY + ChatColor.ITALIC + "Click to change category.");
		metaRef.setLore(lore);
		groupList.setItemMeta(metaRef);
		return groupList;
	}
}