package com.jstnf.infinitywarps.commands;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.utils.IWExecutor;
import com.jstnf.infinitywarps.utils.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class WarpsCommand extends IWExecutor implements CommandExecutor
{
    public WarpsCommand(IWMain plugin)
    {
        super(plugin);
        commandMap = new HashMap<String, SubCommand>();
    }

    @Override
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