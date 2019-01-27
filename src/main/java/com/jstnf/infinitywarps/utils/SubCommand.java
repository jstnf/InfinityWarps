package com.jstnf.infinitywarps.utils;

import org.bukkit.command.CommandSender;

public interface SubCommand
{
    public boolean onCommand(CommandSender sender, String[] args);
    public String permission();
    public String commandInfo();
}