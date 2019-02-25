package com.jstnf.infinitywarps.exception;

public class WarpNotFoundException extends Exception
{
	public WarpNotFoundException(String warpName)
	{
		super(warpName + " - A warp with the given name was not found.");
	}
}
