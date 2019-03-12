package com.jstnf.infinitywarps.exception;

public class WorldNotFoundException extends Exception
{
	public WorldNotFoundException(String worldName)
	{
		super("The world " + worldName + " was not found.");
	}
}