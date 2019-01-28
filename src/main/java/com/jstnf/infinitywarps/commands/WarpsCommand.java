package com.jstnf.infinitywarps.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.jstnf.infinitywarps.IWMain;
import org.bukkit.entity.Player;

public class WarpsCommand implements CommandExecutor
{
    private IWMain plugin;

    public WarpsCommand(IWMain plugin)
    {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        plugin.getLogger().info("[/warps] To implement!");
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Command must be executed by a Player.");
            return true;
        }
        Player p = (Player) sender;
        p.openInventory(plugin.gui.warpSelection[0]);
        return true;
    }
}