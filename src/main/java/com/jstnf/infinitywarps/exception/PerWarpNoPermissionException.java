package com.jstnf.infinitywarps.exception;

public class PerWarpNoPermissionException extends Exception
{
	public PerWarpNoPermissionException()
	{
		super("The player does not have permission to teleport to this warp.");
	}
}