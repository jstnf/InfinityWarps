package com.jstnf.infinitywarps.data;

import com.jstnf.infinitywarps.IWMain;

import java.util.HashMap;

public class WarpGroupManager
{
    private HashMap<String, WarpGroup> localWarpGroups;
    private IWMain plugin;

    public WarpGroupManager(IWMain plugin)
    {
        this.plugin = plugin;
        localWarpGroups = new HashMap<String, WarpGroup>();
    }

    public boolean importGroups()
    {
        /* TO IMPLEMENT! */
        return false;
    }

    public boolean addGroup(String groupName)
    {
        /* TO IMPLEMENT! */
        return false;
    }

    public boolean removeGroup(String groupName)
    {
        /* TO IMPLEMENT! */
        return false;
    }

    public boolean addWarpToGroup(String groupName, String warpName)
    {
        /* TO IMPLEMENT! */
        return false;
    }

    public boolean renameWarpInGroups(String warpName, String newWarpName)
    {
        /* TO IMPLEMENT! */
        return false;
    }

    public boolean removeWarpFromGroup(String groupName, String warpName)
    {
        /* TO IMPLEMENT! */
        return false;
    }
}