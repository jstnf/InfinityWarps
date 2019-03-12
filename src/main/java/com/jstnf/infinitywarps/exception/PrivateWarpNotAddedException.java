package com.jstnf.infinitywarps.exception;

public class PrivateWarpNotAddedException extends Exception
{
	public PrivateWarpNotAddedException()
	{
		super("The player is not added to this private warp.");
	}
}