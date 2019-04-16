package com.jstnf.infinitywarps.old.command;

import com.jstnf.infinitywarps.old.IWMain;
import org.bukkit.command.CommandSender;

public interface SubCommand
{
	boolean onCommand(CommandSender sender, String[] args, IWMain plugin);

	String permission();

	String commandInfo();
}