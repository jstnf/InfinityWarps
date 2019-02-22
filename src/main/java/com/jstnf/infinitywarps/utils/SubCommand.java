package com.jstnf.infinitywarps.utils;

import com.jstnf.infinitywarps.IWMain;
import org.bukkit.command.CommandSender;

public interface SubCommand
{
	boolean onCommand(CommandSender sender, String[] args, IWMain plugin);

	String permission();

	String commandInfo();
}