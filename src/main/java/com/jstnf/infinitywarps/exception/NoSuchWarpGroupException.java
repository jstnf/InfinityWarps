package com.jstnf.infinitywarps.exception;

public class NoSuchWarpGroupException extends Exception
{
    public NoSuchWarpGroupException()
    {
        super("A warp group with the specified name was not found.");
    }
}