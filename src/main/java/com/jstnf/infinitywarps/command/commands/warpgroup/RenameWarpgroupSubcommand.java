package com.jstnf.infinitywarps.command.commands.warpgroup;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.command.SubCommand;
import org.bukkit.command.CommandSender;

public class RenameWarpgroupSubcommand implements SubCommand
{
    @Override
    public boolean onCommand(CommandSender sender, String[] args, IWMain plugin)
    {
        return false;
    }

    @Override
    public String permission()
    {
        return "infinitywarps.command.warpgroup.rename";
    }

    @Override
    public String commandInfo()
    {
        return "Rename a warp group.";
    }
}