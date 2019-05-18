package com.jstnf.infinitywarps.commands.debug;

import com.jstnf.infinitywarps.InfinityWarps;
import com.jstnf.infinitywarps.commands.IWExecutor;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Debug command for SmartInvs' InventoryManager
 *
 * @author jstnf
 * 19 April 2019
 */
public class IWDebugInvCmd extends IWExecutor implements CommandExecutor, InventoryProvider
{
	public IWDebugInvCmd(InfinityWarps plugin)
	{
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage(ChatColor.RED + "Command must be run by a player!");
			return true;
		}

		Player player = (Player) sender;

		SmartInventory inv = SmartInventory.builder()
				.manager(plugin.inventoryManager)
				.id("debug-inventory")
				.provider(this)
				.title(ChatColor.RED + "InfinityWarps Debug Inv")
				.build();

		inv.open(player);
		return true;
	}

	@Override
	public void init(Player player, InventoryContents contents)
	{

	}

	@Override
	public void update(Player player, InventoryContents contents)
	{

	}
}
