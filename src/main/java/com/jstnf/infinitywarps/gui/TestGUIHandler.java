package com.jstnf.infinitywarps.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TestGUIHandler implements Listener
{
	public TestGUIHandler()
	{

	}

	public Inventory getInventory(Player p)
	{
		Inventory test = Bukkit.getServer().createInventory(p, 54, "Warps");
		for (int i = 0; i < test.getSize(); i++)
		{
			ItemStack stack = new ItemStack(Material.ENDER_PEARL, i);
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName("Slot " + i);
			stack.setItemMeta(meta);

			test.setItem(i, stack);
		}
		return test;
	}

	@EventHandler
	private void inventoryClick(InventoryClickEvent e)
	{
		Player whoClicked = (Player) e.getWhoClicked();

		if (whoClicked.getOpenInventory().getTitle().equalsIgnoreCase("Warps"))
		{
			e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
			{
				return;
			}
			else
			{
				Bukkit.getServer().broadcastMessage(whoClicked + " clicked slot " + e.getSlot() + " - " + e.getRawSlot());
			}
		}
	}
}