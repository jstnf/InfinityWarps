package com.jstnf.infinitywarps.old.command.commands.warpgroup;

import com.jstnf.infinitywarps.old.IWMain;
import com.jstnf.infinitywarps.old.command.SubCommand;
import org.bukkit.command.CommandSender;

public class InfoWarpgroupSubcommand implements SubCommand
{
    @Override
    public boolean onCommand(CommandSender sender, String[] args, IWMain plugin)
    {
        return false;
    }

    @Override
    public String permission()
    {
        return "infinitywarps.command.warpgroup.info";
    }

    @Override
    public String commandInfo()
    {
        return "Display info about a warp group.";
    }
}