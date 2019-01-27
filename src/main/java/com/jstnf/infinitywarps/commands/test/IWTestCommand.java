package com.jstnf.infinitywarps.commands.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.jstnf.infinitywarps.IWMain;

public class IWTestCommand implements CommandExecutor
{
    private IWMain plugin;

    public IWTestCommand(IWMain plugin)
    {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        plugin.getLogger().info("[/iwtest] test command");
        switch (args.length)
        {
            case 0:
                break;
            default:
                break;
        }
        return true;
    }
}