package com.jstnf.infinitywarps.utils;

import com.jstnf.infinitywarps.IWMain;

import java.util.HashMap;

public abstract class IWExecutor
{
	protected IWMain plugin;
	protected HashMap<String, SubCommand> commandMap;

	public IWExecutor(IWMain plugin)
	{
		this.plugin = plugin;
	}

	public void registerSubCommand(String command, SubCommand executor)
	{
		commandMap.put(command, executor);
	}

	public boolean hasCommand(String command)
	{
		return commandMap.containsKey(command.toLowerCase());
	}
}