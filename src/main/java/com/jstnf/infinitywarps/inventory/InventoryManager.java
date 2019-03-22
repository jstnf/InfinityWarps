package com.jstnf.infinitywarps.inventory;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.IWUtils;
import com.jstnf.infinitywarps.data.Warp;
import com.jstnf.infinitywarps.data.WarpGroup;
import javafx.util.Pair;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * InfinityWarps by jstnf
 * InventoryManager
 * Constructs Inventory objects for players using InventoryDefinitions.
 * Inventory Listener for InfinityWarps GUIs.
 *
 * @author jstnf / pokeball92870
 */
public class InventoryManager implements Listener
{
	private IWMain plugin;
	private ArrayList<InventoryDefinition> definitions;

	public InventoryManager(IWMain plugin, HashMap<String, Warp> warps, HashMap<String, WarpGroup> groups)
	{
		this.plugin = plugin;
		this.definitions = IWUtils.defineInventoryDefinitions(warps, groups);
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
			inv.setItem(i, ConstantItemStacks.border(plugin));
		}
		for (int i = 45; i < 54; i++)
		{
			switch (i)
			{
				case 48: /* Places item for previous page if there is one */
					if (index > 0)
					{
						inv.setItem(i, ConstantItemStacks.previousPage(plugin));
					}
					else
					{
						inv.setItem(i, ConstantItemStacks.border(plugin));
					}
					break;
				case 49:
					if (definition.definitionType == DefinitionType.WARPS)
					{
						switch (definition.identifier)
						{
							case "all_warps":
								inv.setItem(i, ConstantItemStacks.allWarps(plugin));
								break;
							case "public_warps":
								inv.setItem(i, ConstantItemStacks.publicWarps(plugin));
								break;
							case "private_warps":
								inv.setItem(i, ConstantItemStacks.privateWarps(plugin));
								break;
							default:
								inv.setItem(i, ConstantItemStacks.border(plugin));
								break;
						}
					}
					else if (definition.definitionType == DefinitionType.GROUPS)
					{
						inv.setItem(i, ConstantItemStacks.groupList(plugin));
					}
					else if (definition.definitionType == DefinitionType.WARPGROUP)
					{
						/* Do warpgroup stuff here! */
					}
					else
					{
						inv.setItem(i, ConstantItemStacks.border(plugin));
					}
					break;
				case 50: /* Places item for next page if there is one */
					if (index < definition.getBounds() - 1)
					{
						inv.setItem(i, ConstantItemStacks.nextPage(plugin));
					}
					else
					{
						inv.setItem(i, ConstantItemStacks.border(plugin));
					}
					break;
				case 53:
					inv.setItem(i, ConstantItemStacks.borderIdentifier(plugin, id, index));
					break;
				default:
					inv.setItem(i, ConstantItemStacks.border(plugin));
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

	public boolean updateInventoryDefinitions(HashMap<String, Warp> warps, HashMap<String, WarpGroup> groups)
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
		try
		{
			Inventory i = e.getInventory();

			// Check if inventory is a double chest
			if (i == null || i.getSize() != 54)
			{
				return;
			}

			// Check for InfinityWarps inventory identifier
			ItemStack identifier = i.getItem(53);
			if (identifier == null)
			{
				return;
			}
			else
			{
				if (!identifier.getItemMeta().getDisplayName().equals(" ") || !identifier.hasItemMeta() || !identifier
						.getItemMeta().hasLore())
				{
					return;
				}

				ArrayList<String> lore = (ArrayList<String>) identifier.getItemMeta().getLore();
				Pair<String, Integer> id = IWUtils.validateInventoryId(lore);
				if (id == null)
				{
					return;
				}
				else
				{
					e.setCancelled(true);
					Player p = (Player) e.getWhoClicked();
					/* temp switch case */
					switch (e.getSlot())
					{
						case 48:
							if (id.getValue() > 0)
							{
								p.openInventory(construct(id.getKey(), id.getValue() - 1));
							}
							break;
						case 49:
							/* temp code! */
							int index = IWUtils.getDefinitionSlot(definitions, id.getKey());
							index++;
							if (index >= definitions.size())
							{
								index = 0;
							}
							p.openInventory(construct(definitions.get(index).identifier, 0));
							break;
						case 50:
							InventoryDefinition def = IWUtils.getDefinition(definitions, id.getKey());
							if (id.getValue() < def.getBounds() - 1)
							{
								p.openInventory(construct(id.getKey(), id.getValue() + 1));
							}
							break;
					}
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return;
		}
	}
}