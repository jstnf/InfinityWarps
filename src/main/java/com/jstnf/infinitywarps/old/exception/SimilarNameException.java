package com.jstnf.infinitywarps.old.exception;

public class SimilarNameException extends Exception
{
	public SimilarNameException()
	{
		super("A warp with a similar name already exists.");
	}
}