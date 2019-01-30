package com.jstnf.infinitywarps.commands;

import com.jstnf.infinitywarps.IWMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class InfinitywarpsCommand implements CommandExecutor
{
    private IWMain plugin;

    public InfinitywarpsCommand(IWMain plugin)
    {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        plugin.getLogger().info("[/iw] To implement!");
        return true;
    }
}