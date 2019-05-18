package com.jstnf.infinitywarps.commands;

import com.jstnf.infinitywarps.InfinityWarps;

/**
 * Abstract class for an InfinityWarps command
 *
 * @author jstnf
 * 18 April 2019
 */
public abstract class IWExecutor
{
	protected InfinityWarps plugin;

	public IWExecutor(InfinityWarps plugin)
	{
		this.plugin = plugin;
	}
}