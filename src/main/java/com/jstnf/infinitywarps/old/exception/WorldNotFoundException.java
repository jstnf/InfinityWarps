package com.jstnf.infinitywarps.old.exception;

public class WorldNotFoundException extends Exception
{
	public WorldNotFoundException(String worldName)
	{
		super("The world " + worldName + " was not found.");
	}
}