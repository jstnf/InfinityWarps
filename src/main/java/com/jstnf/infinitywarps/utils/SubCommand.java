package com.jstnf.infinitywarps.utils;

import com.jstnf.infinitywarps.IWMain;
import org.bukkit.command.CommandSender;

public interface SubCommand
{
    public boolean onCommand(CommandSender sender, String[] args, IWMain plugin);
    public String permission();
    public String commandInfo();
}