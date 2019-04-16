package com.jstnf.infinitywarps.old.command.commands.warpgroup;

import com.jstnf.infinitywarps.old.IWMain;
import com.jstnf.infinitywarps.old.command.SubCommand;
import org.bukkit.command.CommandSender;

public class RemoveWarpgroupSubcommand implements SubCommand
{
    @Override
    public boolean onCommand(CommandSender sender, String[] args, IWMain plugin)
    {
        return false;
    }

    @Override
    public String permission()
    {
        return "infinitywarps.command.warpgroup.remove";
    }

    @Override
    public String commandInfo()
    {
        return "Remove a warp group.";
    }
}