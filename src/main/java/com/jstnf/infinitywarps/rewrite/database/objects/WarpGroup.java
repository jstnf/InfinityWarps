package com.jstnf.infinitywarps.rewrite.database.objects;

import com.jstnf.infinitywarps.rewrite.InfinityWarps;
import com.jstnf.infinitywarps.rewrite.database.IWDatabaseObject;
import com.jstnf.infinitywarps.rewrite.gui.IWItemIcon;

import java.util.ArrayList;

/**
 * Class for an InfinityWarps warp group
 *
 * @author jstnf
 * 21 April 2019
 */
public class WarpGroup extends IWDatabaseObject
{
    private InfinityWarps plugin;

    /* Warps */
    private ArrayList<String> warps;

    /* Item */
    private IWItemIcon itemIcon;

    /**
     * Constructor used when creating a new warp group. (Importing Essentials warps or /warpgroup command)
     */
    public WarpGroup(InfinityWarps plugin, String alias)
    {
        super(alias);

        this.plugin = plugin;
    }

    /**
     * Constructor used when importing a warp group from InfinityWarps config files.
     */
    public WarpGroup(InfinityWarps plugin, String alias, ArrayList<String> warps, String itemMaterial, String itemName, ArrayList<String> lore, boolean isEnchanted)
    {
        super(alias);

        this.plugin = plugin;
    }
}