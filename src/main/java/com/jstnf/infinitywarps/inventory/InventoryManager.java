package com.jstnf.infinitywarps.inventory;

import com.jstnf.infinitywarps.data.Warp;
import com.jstnf.infinitywarps.data.WarpGroup;
import com.jstnf.infinitywarps.IWUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryManager implements Listener
{
	private ArrayList<InventoryDefinition> definitions;

	public InventoryManager(HashMap<String, Warp> warps, ArrayList<WarpGroup> groups)
	{
		definitions = IWUtils.defineInventoryDefinitions(warps, groups);
	}

	public Inventory construct(String id, int index)
	{
		InventoryDefinition definition = IWUtils.getDefinition(definitions, id);
		if (definition == null)
		{
			return null;
		}

		Inventory inv = Bukkit.getServer().createInventory(null, 54, "InfinityWarps Menu");

		/* Create default, non-warp items */
		for (int i = 0; i < 9; i++)
		{
			inv.setItem(i, ConstantItemStacks.border());
		}
		for (int i = 45; i < 54; i++)
		{
			switch (i)
			{
				case 48: /* Places item for previous page if there is one */
					if (index > 0)
					{
						inv.setItem(i, ConstantItemStacks.previousPage());
					}
					else
					{
						inv.setItem(i, ConstantItemStacks.border());
					}
					break;
				case 49:
					if (definition.definitionType == DefinitionType.WARPS)
					{
						switch (definition.identifier)
						{
							case "all_warps":
								inv.setItem(i, ConstantItemStacks.allWarps());
								break;
							case "public_warps":
								inv.setItem(i, ConstantItemStacks.publicWarps());
								break;
							case "private_warps":
								inv.setItem(i, ConstantItemStacks.privateWarps());
								break;
							default:
								inv.setItem(i, ConstantItemStacks.border());
								break;
						}
					}
					else
					{
						inv.setItem(i, ConstantItemStacks.border());
					}
					break;
				case 50: /* Places item for next page if there is one */
					if (index < definition.getBounds() - 1)
					{
						inv.setItem(i, ConstantItemStacks.nextPage());
					}
					else
					{
						inv.setItem(i, ConstantItemStacks.border());
					}
					break;
				case 53:
					inv.setItem(i, ConstantItemStacks.borderIdentifier(id, index));
					break;
				default:
					inv.setItem(i, ConstantItemStacks.border());
					break;
			}
		}

		int invIndex = 9;
		int warpIndex = index * 36;

		while (invIndex < 45 && warpIndex < definition.warps.size() && warpIndex < (index + 1) * 36)
		{
			inv.setItem(invIndex, definition.warps.get(warpIndex).getItemIcon());
			invIndex++;
			warpIndex++;
		}

		return inv;
	}

	public boolean updateInventoryDefinitions(HashMap<String, Warp> warps, ArrayList<WarpGroup> groups)
	{
		try
		{
			definitions = IWUtils.defineInventoryDefinitions(warps, groups);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@EventHandler
	public void inventoryClick(InventoryClickEvent e)
	{

	}
}
