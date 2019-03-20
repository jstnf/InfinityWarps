package com.jstnf.infinitywarps.data;

import com.jstnf.infinitywarps.IWMain;
import com.jstnf.infinitywarps.config.SimpleConfig;

import java.io.File;
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
        File groupsFolder = new File(plugin.getDataFolder() + File.separator + "groups");
        if (!groupsFolder.exists())
        {
            groupsFolder.mkdir();
        }
        String[] keys = groupsFolder.list();

        for (String key : keys)
        {
            SimpleConfig config = plugin.getConfigManager().manager.getNewConfig("groups" + File.separator + key);
        }
        return true;
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