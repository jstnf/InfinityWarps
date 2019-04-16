package com.jstnf.infinitywarps.old.exception;

public class NoSuchWarpException extends Exception
{
	public NoSuchWarpException(String warpName)
	{
		super(warpName + " - A warp with the given name was not found.");
	}
}