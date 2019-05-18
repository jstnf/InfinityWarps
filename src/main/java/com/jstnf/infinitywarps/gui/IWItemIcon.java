package com.jstnf.infinitywarps.gui;

import com.jstnf.infinitywarps.InfinityWarps;
import com.jstnf.infinitywarps.settings.IWSettings;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * Holds information for item icons to be used in GUI interfaces in InfinityWarps
 *
 * @author jstnf
 * 18 April 2019
 */
public class IWItemIcon
{
	private String itemMaterial, itemName;
	private ArrayList<String> itemLore;
	private boolean isEnchanted;

	public IWItemIcon(InfinityWarps plugin, String material, String name, ArrayList<String> lore, boolean isEnchanted)
	{
		/**
		 * Check valid item
		 */
		if (Material.getMaterial(material) == null)
		{
			itemMaterial = plugin.configManager.main.getString(IWSettings.DEFAULT_WARP_ICON.getPath(),
					(String) IWSettings.DEFAULT_WARP_ICON.getDefaultValue());
		}
		else
		{
			itemMaterial = material;
		}
		itemName = name;
		itemLore = lore;
		this.isEnchanted = isEnchanted;
	}

	public ItemStack getItemStack()
	{
		ItemStack stack = new ItemStack(Material.getMaterial(itemMaterial));
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));
		meta.setLore(itemLore);
		if (isEnchanted)
		{
			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		stack.setItemMeta(meta);
		return stack;
	}

	public String getItemMaterial()
	{
		return itemMaterial;
	}

	public String getItemName()
	{
		return itemName;
	}

	public ArrayList<String> getItemLore()
	{
		return itemLore;
	}

	public boolean isEnchanted()
	{
		return isEnchanted;
	}
}