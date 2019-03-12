package com.jstnf.infinitywarps.exception;

public class NoSuchWarpException extends Exception
{
	public NoSuchWarpException(String warpName)
	{
		super(warpName + " - A warp with the given name was not found.");
	}
}