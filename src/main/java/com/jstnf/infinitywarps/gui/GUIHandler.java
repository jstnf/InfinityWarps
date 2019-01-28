package com.jstnf.infinitywarps.gui;

import com.jstnf.infinitywarps.data.Warp;
import com.jstnf.infinitywarps.data.WarpPusher;
import com.jstnf.infinitywarps.utils.ConstantItemStacks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class GUIHandler implements Listener
{
	public Inventory[] warpSelection;
	private WarpPusher pusher;
	private ArrayList<Warp> warps;

	public GUIHandler()
	{

	}

	public Inventory[] constructGUIs()
	{
		int pages = calcNumPages();

		Inventory[] sel = new Inventory[pages];
		int warpsIndex = 0;
		for (int selIndex = 0; selIndex < sel.length; selIndex++)
		{
			sel[selIndex] = Bukkit.getServer()
					.createInventory(null, 54, "Warps (" + (selIndex + 1) + " of " + sel.length + ")");

			/* Create default, non-warp items */
			for (int i = 0; i < 9; i++)
			{
				sel[selIndex].setItem(i, ConstantItemStacks.border());
			}
			for (int i = 45; i < 54; i++)
			{
				switch (i)
				{
					case 48: /* Places item for previous page if there is one */
						if (selIndex > 0)
						{
							sel[selIndex].setItem(i, ConstantItemStacks.previousPage());
						}
						else
						{
							sel[selIndex].setItem(i, ConstantItemStacks.border());
						}
						break;
					case 50: /* Places item for next page if there is one */
						if (selIndex < sel.length - 1)
						{
							sel[selIndex].setItem(i, ConstantItemStacks.nextPage());
						}
						else
						{
							sel[selIndex].setItem(i, ConstantItemStacks.border());
						}
						break;
					default:
						sel[selIndex].setItem(i, ConstantItemStacks.border());
						break;
				}
			}

			/* Set items for warps */
			if (warps != null)
			{
				int slotIndex = 9;
				while (warpsIndex < warps.size() && slotIndex < 45)
				{
					sel[selIndex].setItem(slotIndex, warps.get(warpsIndex).getItemIcon());
				}
			}
		}
		warpSelection = sel;
		return sel;
	}

	@EventHandler
	public void inventoryClick(InventoryClickEvent e)
	{
		Player whoClicked = (Player) e.getWhoClicked();
		String title = whoClicked.getOpenInventory().getTitle();

		/* Determine if inventory is the custom inventory */
		if (title.contains("Warps (") && title.contains(" of "))
		{
			e.setCancelled(true);
			try
			{
				int page = Integer.parseInt(title.substring(7, title.indexOf(" of ")));
				int slot = e.getRawSlot();
				/* Do nothing when clicking borders */
				if (slot < 9 || (slot > 44 && slot < 48) || slot == 49 || slot > 50)
				{
					return;
				}
				/* Check if there is a previous page */
				else if (slot == 48 && page - 1 > 0)
				{
					whoClicked.openInventory(warpSelection[page - 2]);
				}
				/* Check if there is a second page */
				else if (slot == 50 && page < calcNumPages())
				{
					whoClicked.openInventory(warpSelection[page]);
				}
				else
				{
					/* TO IMPLEMENT - DO WARP STUFF HERE! */
				}
			}
			/* In cases where players rename other inventories to match */
			catch (Exception ex)
			{
				ex.printStackTrace();
				whoClicked.sendMessage("Invalid inventory. Please contact server administration if you see this.");
			}

		}
	}

	/**
	 * Returns the number of pages based on the number of loaded warps.
	 */
	private int calcNumPages()
	{
		int pages = 1;
		if (warps != null)
		{
			pages = warps.size() / 36;
			if (warps.size() % 36 != 0)
			{
				pages += 1;
			}
			if (warps.size() == 0)
			{
				pages = 1;
			}
		}
		return pages;
	}
}